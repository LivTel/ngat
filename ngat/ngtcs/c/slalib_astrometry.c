/**
 * Library using slalib functions to implement the C layer of the 
 * SlalibAstrometricKernel class.  The SlalibAstrometricKernel class
 * is an implementation of the AstrometryCalculator interface used by
 * the NGTCS.
 * 
 *  @author $Author: cjm $
 * @version $Revision: 1.1 $
 *
 *     $Date: 2013-07-04 09:58:36 $
 *  $RCSfile: slalib_astrometry.c,v $
 *   $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/c/slalib_astrometry.c,v $
 *      $Log: not supported by cvs2svn $
*/

#include<time.h>
#include<math.h>

#include<stdlib.h>
#include<stdio.h>

#include"slalib.h"
#include"slamac.h"

#include"ngtcs_astrometry.h"
#include"astro_structs.h"
#include"astro_values.h"

#ifdef DEBUG
void print_coords      ( char *vec_name, double vec[3], char type );
#endif

void parse_epoch       ( double date, Calendar cal, Timescale tscale,
			 struct all_data_struct *all_data,
			 struct timestamp_struct *timestamp );


#define STANDARD_EQUINOX          J2000
#define STANDARD_CALENDAR         JULIAN
#define STANDARD_EPOCH            ( 2000.0 )
#define STANDARD_EPOCH_JULIAN     ( 2000.0 )
#define STANDARD_EPOCH_BESSELIAN  J2000_BESSELIAN

/*****************************************************************************/
/* Limit of altitude for refraction calculations                             */
/*****************************************************************************/
#define SIN_ALT_LIMIT             ( 0.173648178 )

/*****************************************************************************/
/* These values are assigned and calculated in this library only, and do not */
/* get written or read externally to this library.                           */
/*****************************************************************************/
static int days_month[12] = 
{
  31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
};

static double unused_double;
static double unused_vector[3];

/**
 * Initialise data used by both position demand and reporting.
 */
void ast_init_generic_data( struct all_data_struct *all_data )
{
  double position_velocity[6];
  /***************************************************************************/
  /* Correct for Polar Motion                                                */
  /***************************************************************************/
#ifdef DEBUG
  fprintf( stderr, "\nINITIALISING DATA\n=================\n\n" );
  fprintf( stderr, "iers-x = %f, iers-y = %f\n",
	   all_data->iers.polmo_x, all_data->iers.polmo_y );
#endif

  slaPolmo( all_data->site.longitude, all_data->site.latitude, 
	    all_data->iers.polmo_x, all_data->iers.polmo_y, 
	    &(all_data->longitude), &(all_data->latitude), 
	    &(all_data->azimuth_correction) );

#ifdef DEBUG
  fprintf( stderr, "new long = %+010.7f rads = %+010.5f\n", 
	   all_data->longitude, all_data->longitude * DR2D );
  fprintf( stderr, " new lat = %+010.7f rads = %+010.5f\n", 
	   all_data->latitude, all_data->latitude * DR2D );
  fprintf( stderr, "az corr  = %+010.7f rads = %+010.7f arcsec\n",
	   all_data->azimuth_correction,
	   all_data->azimuth_correction* DR2D *3600.0 );
#endif

  all_data->sin_lat = sin( all_data->latitude );
  all_data->cos_lat = cos( all_data->latitude );
  all_data->e2h[ 0 ][ 0 ] = -all_data->sin_lat;
  all_data->e2h[ 0 ][ 1 ] = 0.0;
  all_data->e2h[ 0 ][ 2 ] = all_data->cos_lat;
  all_data->e2h[ 1 ][ 0 ] = 0.0;
  all_data->e2h[ 1 ][ 1 ] = -1.0;
  all_data->e2h[ 1 ][ 2 ] = 0.0;
  all_data->e2h[ 2 ][ 0 ] = all_data->cos_lat;
  all_data->e2h[ 2 ][ 1 ] = 0.0;
  all_data->e2h[ 2 ][ 2 ] = all_data->sin_lat;
  /***************************************************************************/
  /* Calculate observatory vectors                                           */
  /***************************************************************************/
  slaPvobs( all_data->latitude, all_data->site.altitude, 0.0, 
	    position_velocity );
  slaGeoc( all_data->latitude, all_data->site.altitude, 
	   &(all_data->uau), &(all_data->vau) );
  /***************************************************************************/
  /* Parse current timestamp for NOW.                                        */
  /***************************************************************************/
#ifdef DEBUG
  fprintf( stderr, "parsing observation epoch\n" );
#endif

  ast_parse_timestamp( all_data );
  /***************************************************************************/
  /* Rotate observatory vector to allow for LST.                             */
  /***************************************************************************/
  all_data->observatory[ 0 ] = 
    ( position_velocity[ 0 ] * all_data->cos_lst + 
      position_velocity[ 1 ] * all_data->sin_lst );
  all_data->observatory[ 1 ] = 
    ( position_velocity[ 1 ] * all_data->cos_lst - 
      position_velocity[ 0 ] * all_data->sin_lst );
  all_data->observatory[ 2 ] = position_velocity[ 2 ];
  /***************************************************************************/
  /* Obtain Ephemeris Data for the Earth and Sun                             */
  /***************************************************************************/
  slaEvp( all_data->timestamp.mjd_tdb, STANDARD_EPOCH, 
	  all_data->bary_earth_vel, all_data->bary_earth,
	  unused_vector, all_data->helio_earth );
}
/*****************************************************************************/


/**
 * This function transforms a Mean (catalogue) position into a position 
 * measured from the Apparent epoch of equator and equinox.  It results in 
 * the coordinates of an object as would be seen from the centre of Earth.
 *
 * The transformations are executed as follows:
 * <ul>
 * <li>Proper Motion</li>
 * <li>Barycentric to Geocentric</li>
 * <li>Solar Deflection</li>
 * <li>Annual Aberration</li>
 * <li>Precession</li>
 * <li>Nutation</li>
 * </ul>
 */
void ast_mean_2_apparent( struct all_data_struct *all_data )
{
  /***************************************************************************/
  /* Intermediate data holders                                               */
  /***************************************************************************/
  int    i;
  double pos1[3], pos2[3], pos3[3], pos4[3], pos5[3];
  double pos6[3], pos7[3], pos8[3];
  double helio_earth_norm[3];
  double sun_earth_dist;
  double precession_matrix[3][3];
  double nutation_matrix[3][3];
  double temp_ra1, temp_dec1, temp_ra2, temp_dec2;
  double sys_ra, sys_dec, sys_pm_ra, sys_pm_dec, sys_plx, sys_rad_vel;
  double sys_ra_pm, sys_dec_pm, sys_ra_off, sys_dec_off, ns_time;
  struct timestamp_struct user_eqx;
  /***************************************************************************/
  /* Intermediate data holders from slalib                                   */
  /***************************************************************************/
  double gr2e, pde, pdep1, w, abv[3], vm, ab1, p1dv;
  /*
  double ru, sb, xSq_ySq;
  */
  /***************************************************************************/
  /* Intermediate data PTW documentation TCS/PTW/3.17                        */
  /***************************************************************************/
  /*
  double xt, yt, zt, xut, yut, zut, xr, yr, zr, sq, cq, rt, sbt;
  */

#ifdef DEBUG
  fprintf( stderr, "\nMean -> Apparent\n================\n\n" );
#endif

  /***************************************************************************/
  /* Calculate timestructures for user equinox and epoch                     */
  /***************************************************************************/
#ifdef DEBUG
  fprintf( stderr, "parsing Equinox epoch\n" );
#endif

  parse_epoch( all_data->target.eqx.epo.date, all_data->target.eqx.epo.cal_int,
	       all_data->target.eqx.epo.ts_int, all_data, 
	       &( all_data->user_eqx ) );

#ifdef DEBUG
  fprintf( stderr, "parsing observation epoch\n" );
#endif

  parse_epoch( all_data->target.epo.date, all_data->target.epo.cal_int,
	       all_data->target.epo.ts_int, all_data, 
	       &( all_data->user_epo ) );
  /***************************************************************************/
  /* Calculate proper motion                                                 */
  /***************************************************************************/
  slaPm( all_data->target.ra, all_data->target.dec, all_data->target.pm_ra, 
	 all_data->target.pm_dec, all_data->target.plx, 
	 all_data->target.rad_vel, all_data->user_epo.jep_tdb, 
	 all_data->timestamp.jep_tdb, &sys_ra_pm, &sys_dec_pm );
  /***************************************************************************/
  /* Apply user offsets and non-sidereal tracking rates                      */
  /***************************************************************************/
  sys_ra_off  = sys_ra_pm  + all_data->target.off_ra;
  sys_dec_off = sys_dec_pm + all_data->target.off_dec;
  ns_time     = ( ( (double)all_data->timestamp.secs + 
		    (double)all_data->timestamp.nanosecs * 1.0E-09 ) -
		  ( (double)all_data->target.ns_t_utc_secs + 
		    (double)all_data->target.ns_t_utc_nanosecs * 1.0E-09 ) );
  sys_ra      = sys_ra_off  + all_data->target.ns_ra * ns_time;
  sys_dec     = sys_dec_off + all_data->target.ns_dec * ns_time;
  /***************************************************************************/
  /* Convert coordinates into FK5                                            */
  /***************************************************************************/
  switch( all_data->target.eqx.eqx_int )
    {
      /*********************************************************************/
      /* Already FK5 - just transfer values                                */
      /*********************************************************************/
    case J2000:
      sys_pm_ra   = all_data->target.pm_ra;
      sys_pm_dec  = all_data->target.pm_dec;
      sys_plx     = all_data->target.plx;
      sys_rad_vel = all_data->target.rad_vel;
      slaDcs2c( sys_ra, sys_dec, pos1 );
      break;
      
      /*********************************************************************/
      /* Convert B1950 to J2000/FK5                                        */
      /*********************************************************************/
    case B1950:
      slaFk425( sys_ra, sys_dec, 
		all_data->target.pm_ra, all_data->target.pm_dec, 
		all_data->target.plx, all_data->target.rad_vel, 
		&temp_ra1, &temp_dec1, &sys_pm_ra, &sys_pm_dec, &sys_plx, 
		&sys_rad_vel );
      sys_ra = temp_ra1;
      sys_dec = temp_dec1;
      slaDcs2c( sys_ra, sys_dec, pos1 );
      break;
      
      /*********************************************************************/
      /* Convert ICRF to J2000/FK5                                         */
      /*********************************************************************/
    case ICRF:
      slaH2fk5( sys_ra, sys_dec, 
		all_data->target.pm_ra, all_data->target.pm_dec, 
		&temp_ra1, &temp_dec1, &sys_pm_ra, &sys_pm_dec );
      sys_plx = all_data->target.plx;
      sys_rad_vel = all_data->target.rad_vel;
      sys_ra = temp_ra1;
      sys_dec = temp_dec1;
      slaDcs2c( sys_ra, sys_dec, pos1 );
      break;
      
      /*********************************************************************/
      /* Put APPARENT coords into vector                                   */
      /*********************************************************************/
    case APPARENT:
      slaDcs2c( sys_ra, sys_dec, all_data->app_radec );
      return;
      
      /*********************************************************************/
      /* Catch this case and return.  It should NEVER happen.              */
      /*********************************************************************/
    case HORIZON:
      return;
      
      /*********************************************************************/
      /* Convert non-standard equinoxes                                    */
      /*********************************************************************/
    default:
      switch( all_data->target.eqx.epo.cal_int )
	{
	  /*****************************************************************/
	  /* Convert Besselian equinox to J2000/FK5                        */
	  /*****************************************************************/
	case BESSELIAN:
	  slaSubet( sys_ra, sys_dec, 
		    user_eqx.bep_tdb , &temp_ra1, &temp_dec1 );
	  slaDcs2c( temp_ra1, temp_dec1, pos1 );
	  slaPrebn( user_eqx.bep_tdb, 1950.0, 
		    precession_matrix );
	  slaDmxv( precession_matrix, pos1, pos2 );
	  slaDcc2s( pos2, &temp_ra1, &temp_dec1 );
	  slaAddet( temp_ra1, temp_dec1, 1950.0, &temp_ra2, 
		    &temp_dec2 );
	  slaFk425( temp_ra2, temp_dec2, all_data->target.pm_ra, 
		    all_data->target.pm_dec, all_data->target.plx, 
		    all_data->target.rad_vel, &sys_ra, 
		    &sys_dec, &sys_pm_ra, &sys_pm_dec, 
		    &sys_plx, &sys_rad_vel );
	  slaDcs2c( sys_ra, sys_dec, pos1 );
	  break;
	  /*****************************************************************/
	  /* Currently the system used internal in this library is         */
	  /* FK5, so just precess to 2000.0                                */
	  /*****************************************************************/
	case JULIAN:
	  slaPrecl( user_eqx.jep_tdb, STANDARD_EPOCH_JULIAN, 
		    precession_matrix );
	  slaDcs2c( sys_ra, sys_dec, pos1 );
	  slaDmxv( precession_matrix, pos1, pos2 );
	  slaDcc2s( pos2, &sys_ra, &sys_dec );
	  sys_pm_ra   = all_data->target.pm_ra;
	  sys_pm_dec  = all_data->target.pm_dec;
	  sys_plx     = all_data->target.plx;
	  sys_rad_vel = all_data->target.rad_vel;
	  slaDcs2c( sys_ra, sys_dec, pos1 );
	  break;
	  /*****************************************************************/
	  /* Anything Else here.                                           */
	  /*****************************************************************/
	default:
	  break;
	}
      break;
    }
  /***************************************************************************/
  /* Calculate Geocentric position vector                                    */
  /***************************************************************************/
#ifdef DEBUG
  print_coords( "Barycentric", pos1, 'r' );
#endif

  if( all_data->target.plx > 0.0 )
    all_data->target_dist_au = ( 1.0E+07 * PARSEC_2_AU );
  else
    all_data->target_dist_au = ( PARSEC_2_AU / all_data->target.plx );

  pos2[ 0 ] = pos1[ 0 ] - ( all_data->bary_earth[ 0 ] / 
			    all_data->target_dist_au );
  pos2[ 1 ] = pos1[ 1 ] - ( all_data->bary_earth[ 1 ] / 
			    all_data->target_dist_au );
  pos2[ 2 ] = pos1[ 2 ] - ( all_data->bary_earth[ 2 ] / 
			    all_data->target_dist_au );
  slaDvn( pos2, pos3, &unused_double );

#ifdef DEBUG
  print_coords( "Geocentric", pos3, 'r' );
#endif
  /***************************************************************************/
  /* Calculate position deflection from Sun from slalib function slaMapqk    */
  /***************************************************************************/
  slaDvn( all_data->helio_earth, helio_earth_norm, 
	  &sun_earth_dist );
  gr2e = 1.974126E-08 / sun_earth_dist;
  pde = slaDvdv( pos3, helio_earth_norm );
  pdep1 = 1.0 + pde;
  w = gr2e / gmax( pdep1, 1.0E-05 );
  for( i = 0; i < 3; i++ )
    {
      pos4[ i ] = pos3[ i ] + ( w * ( helio_earth_norm[ i ] - 
				      pde * pos3[ i ] ) );
    }
  slaDvn( pos4, pos5, &unused_double );

#ifdef DEBUG
  print_coords( "Deflected", pos5, 'r' );
#endif

  /***************************************************************************/
  /* Calculate Annual Aberration from slalib function slaMapqk               */
  /***************************************************************************/
  abv[ 0 ] = all_data->bary_earth_vel[ 0 ] * C_AU_PER_DAY;
  abv[ 1 ] = all_data->bary_earth_vel[ 1 ] * C_AU_PER_DAY;
  abv[ 2 ] = all_data->bary_earth_vel[ 2 ] * C_AU_PER_DAY;
  slaDvn( abv, unused_vector, &vm );
  ab1 = sqrt( 1.0 - vm * vm );
  p1dv = slaDvdv( pos5, abv );
  w = 1.0 + p1dv / ( ab1 + 1.0 );
  for( i = 0; i < 3; i++ )
    {
      pos6[ i ] = ab1 * pos5[ i ] + w * abv[ i ];
    }
  slaDvn( pos6, pos7, &unused_double );

#ifdef DEBUG
  print_coords( "Annual ab", pos7, 'r' );
#endif

  /***************************************************************************/
  /* Precession                                                              */
  /***************************************************************************/
  slaPrecl( STANDARD_EPOCH, all_data->timestamp.jep_tdb, precession_matrix );
  slaDmxv( precession_matrix, pos7, pos8 );

#ifdef DEBUG
  print_coords( "Precessed", pos8, 'r' );
#endif

  /***************************************************************************/
  /* Nutation                                                                */
  /***************************************************************************/
  slaNut( all_data->timestamp.mjd_tdb, nutation_matrix );
  slaDmxv( nutation_matrix, pos8, all_data->app_radec );

#ifdef DEBUG
  print_coords( "Nutated", all_data->app_radec, 'r' );
#endif
}
/*****************************************************************************/


/**
 * Transformation to account for Local Sidereal Time converting RA,dec into
 * HA,dec.
 */
void ast_LST_rotation( struct all_data_struct *all_data )
{
#ifdef DEBUG
    fprintf( stderr, "\nLST Rotation\n============\n" );
#endif
  /***************************************************************************/
  /* Rotate frame by LST radians from RA, dec to -HA, dec                    */
  /***************************************************************************/
  all_data->app_hadec[ 0 ] = ( all_data->app_radec[ 0 ] * 
			       all_data->cos_lst + 
			       all_data->app_radec[ 1 ] * 
			       all_data->sin_lst );
  all_data->app_hadec[ 1 ] = ( -all_data->app_radec[ 0 ] * 
			       all_data->sin_lst + 
			       all_data->app_radec[ 1 ] * 
			       all_data->cos_lst );
  all_data->app_hadec[ 2 ] =   all_data->app_radec[ 2 ];

#ifdef DEBUG
  print_coords( "HA, Dec", all_data->app_hadec, 'h' );
#endif

}
/*****************************************************************************/


/**
 * This function transforms an Apparent position into an observed position with
 * the vector elements [X,Y,Z] representing North (celestial), West and Zenith
 * respectively.
 *
 * The transformations are executed as follows:
 * <ul>
 * <li>Apparent Geocentric to Apparent Topocentric
 * <li>Diurnal Aberration</li>
 * <li>Refraction</li>
 * </ul>
 */
void ast_apparent_2_observed( struct all_data_struct *all_data )
{
  /***************************************************************************/
  /* Intermediate data holders                                               */
  /***************************************************************************/
  double pos[3];
  double ref_coeff_A, ref_coeff_B;
  double az, alt, altaz[3];
  /*
  double zd, plus_alt, az_up, az_dn, alt_up, alt_dn;
  double xt, yt, zt, xut, yut, zut, xr, yr, zr, sq, cq, rt, sbt;
  */
  /***************************************************************************/
  /* Intermediate data holders from slalib                                   */
  /***************************************************************************/
  double diurab, coeff;

#ifdef DEBUG
  fprintf( stderr, "\nApparent -> Observed\n====================\n" );
#endif

  /***************************************************************************/
  /* Diurnal Aberration from slalib function slaAopqk                        */
  /***************************************************************************/
  diurab = 2.0 * DPI * all_data->uau * SOLAR_2_SIDEREAL / 173.14463331;
  coeff = 1.0 - diurab * all_data->app_hadec[ 1 ];
  pos[ 0 ] = coeff *   all_data->app_hadec[ 0 ];
  pos[ 1 ] = coeff * ( all_data->app_hadec[ 1 ] + diurab );
  pos[ 2 ] = coeff *   all_data->app_hadec[ 2 ];

#ifdef DEBUG
  print_coords( "Diurnal ab", pos, 'h' );
#endif

  /***************************************************************************/
  /* Refraction                                                              */
  /*                                                                         */
  /* Notice the swapping of altaz[ 1 ] to -altaz[ 1 ].  This is to convert   */
  /* the [X,Y,Z] matrix to a left-handed one for the sake of conversion to   */
  /* spherical Alt-Az coordinates.                                           */
  /***************************************************************************/
  slaDmxv( all_data->e2h, pos, altaz );
  altaz[ 1 ] = -altaz[ 1 ];

#ifdef DEBUG
  print_coords( "Alt-Az", altaz, 'a' );
#endif

  alt = asin( altaz[ 2 ] );
  az = atan2( altaz[ 1 ], altaz[ 0 ] );

  if( sin( alt ) >= SIN_ALT_LIMIT )
    {
      double zd;

#ifdef DEBUG
      fprintf( stderr, "REFRACTION:\n" );
      fprintf( stderr, "altitude    = %e\n", all_data->site.altitude );
      fprintf( stderr, "temperature = %e\n", all_data->met.temp_k );
      fprintf( stderr, "pressure    = %e\n", all_data->met.pres );
      fprintf( stderr, "humidity    = %e\n", all_data->met.hum );
      fprintf( stderr, "lambda      = %e\n", all_data->lambda*1.0E+06 );
      fprintf( stderr, "latitude    = %e\n", all_data->latitude );
      fprintf( stderr, "TLR         = %e\n", all_data->met.tlr );
#endif

      slaRefco( all_data->site.altitude, all_data->met.temp_k, 
		all_data->met.pres, all_data->met.hum, 
		all_data->lambda*1.0E+06, 
		all_data->latitude, all_data->met.tlr, 
		1E-08, &ref_coeff_A, &ref_coeff_B );
      slaRefz( ( DPIBY2 - alt ), ref_coeff_A, ref_coeff_B, &zd );

      alt = DPIBY2 - zd;
    }

#ifdef DEBUG
  {
    double tmp[ 3 ];

    slaDcs2c( az, alt, tmp );
    print_coords( "Refracted", tmp, 'a' );
  }
#endif


  /***************************************************************************/
  /* Correct azimuth from celestial to terrestrial                           */
  /*                                                                         */
  /* The earlier swap from [ X, Y, Z ] to [ X, -Y, Z ] is CANCELLED before   */
  /* the final matrix is returned to keep the matrix RIGHT-HANDED.           */
  /***************************************************************************/
  az += all_data->azimuth_correction;
  slaDcs2c( az, alt, all_data->observed );

#ifdef DEBUG
  print_coords( "Az corrected", all_data->observed, 'a' );
#endif

  all_data->observed[ 1 ] = -all_data->observed[ 1 ];
  /***************************************************************************/
}
/*****************************************************************************/


/**
 *
 *
 */
void ast_observed_2_apparent( struct all_data_struct *all_data )
{
  /***************************************************************************/
  /* Intermediate data holders                                               */
  /***************************************************************************/
  double pos1[3], altaz[3];
  double az, alt, refraction, angle1, angle2;
  /***************************************************************************/
  /* Intermediate data holders from slalib                                   */
  /***************************************************************************/
  double diurab, coeff;
  /***************************************************************************/
  /* Correct azimuth from celestial to terrestrial                           */
  /*                                                                         */
  /* NOTE the conversion from a right-handed matrix into a left-handed one   */
  /* for the sake of spherical Alt-Az coordinates:                           */
  /*                                                                         */
  /*     altaz[ 1 ] = -all_data->reported.obs[ 1 ]                           */
  /***************************************************************************/
#ifdef DEBUG
  fprintf( stderr, "\nObserved -> Apparent\n====================\n" );
#endif

  altaz[ 0 ] =  all_data->reported.obs[ 0 ];
  altaz[ 1 ] = -all_data->reported.obs[ 1 ];
  altaz[ 2 ] =  all_data->reported.obs[ 2 ];
  slaDcc2s( altaz, &az, &alt );

  az -= all_data->azimuth_correction;

#ifdef DEBUG
    print_coords( "Az corrected", altaz, 'a' );
#endif
  /***************************************************************************/
  /* Get Mount-reported observed RA,Dec values                               */
  /***************************************************************************/
  slaDh2e( az, alt, all_data->latitude, &angle1, &angle2 );
  slaDcs2c( ( all_data->timestamp.lst - angle1 ), angle2, 
	    all_data->reported.obs_radec );

#ifdef DEBUG
  print_coords( "Raw RA,Dec", all_data->reported.obs_radec, 'r' );
#endif
  /***************************************************************************/
  /* Calculate and set the Airmass                                           */
  /***************************************************************************/
  all_data->reported.airmass = slaAirmas( DPIBY2 - alt );

#ifdef DEBUG
  fprintf( stderr, "Airmass = %10.7f\n", all_data->reported.airmass );
#endif
  /***************************************************************************/
  /* Correct for refraction                                                  */
  /*                                                                         */
  /* After the application of refraction the left-handed matrix is changed   */
  /* back into a right-handed one.                                           */
  /***************************************************************************/
#ifdef DEBUG
  {
    double tmp[ 3 ];
    slaDcs2c( az, alt, tmp );
    print_coords( "Refracted", tmp, 'a' );
  }
#endif

  if( all_data->reported.obs[ 2 ] >= SIN_ALT_LIMIT )
    {
#ifdef DEBUG
      fprintf( stderr, "REFRACTION:\n" );
      fprintf( stderr, "altitude    = %e\n", all_data->site.altitude );
      fprintf( stderr, "temperature = %e\n", all_data->met.temp_k );
      fprintf( stderr, "pressure    = %e\n", all_data->met.pres );
      fprintf( stderr, "humidity    = %e\n", all_data->met.hum );
      fprintf( stderr, "lambda      = %e\n", all_data->lambda*1.0E+06 );
      fprintf( stderr, "latitude    = %e\n", all_data->latitude );
      fprintf( stderr, "TLR         = %e\n", all_data->met.tlr );
#endif

      slaRefro( ( DPIBY2 - alt ), all_data->site.altitude, 
		all_data->met.temp_k, all_data->met.pres, 
		all_data->met.hum, all_data->lambda*1.0E+06, 
		all_data->latitude, all_data->met.tlr, 1.0E-08, 
		&refraction );
      alt -= refraction;
    }

  slaDcs2c( az, alt, altaz );

  all_data->reported.unref[ 0 ] =  altaz[ 0 ];
  all_data->reported.unref[ 1 ] = -altaz[ 1 ];
  all_data->reported.unref[ 2 ] =  altaz[ 2 ];
  slaDimxv( all_data->e2h, all_data->reported.unref,
	    all_data->reported.topo_hadec );
  /***************************************************************************/
  /* Diurnal Aberration from slalib function slaOapqk                        */
  /***************************************************************************/
#ifdef DEBUG
  print_coords( "Diurnal ab", all_data->reported.topo_hadec, 'h' );
#endif

  diurab = -( 2.0 * DPI * all_data->uau * SOLAR_2_SIDEREAL / 
	      173.14463331 );
  coeff = 1.0 - diurab * all_data->reported.topo_hadec[ 1 ];
  pos1[ 0 ] = coeff *   all_data->reported.topo_hadec[ 0 ];
  pos1[ 1 ] = coeff * ( all_data->reported.topo_hadec[ 1 ] + diurab );
  pos1[ 2 ] = coeff *   all_data->reported.topo_hadec[ 2 ];
  slaDvn( pos1, all_data->reported.app_hadec, &unused_double );

#ifdef DEBUG
  print_coords( "HA, Dec", all_data->reported.app_hadec, 'h' );
#endif
  /***************************************************************************/
}
/*****************************************************************************/


/**
 *
 *
 */
void ast_LST_derotation( struct all_data_struct *all_data )
{
#ifdef DEBUG
  fprintf( stderr, "\nLST de-rotation\n===============\n" );
#endif
  /***************************************************************************/
  /* De-rotate frame by LST radians from -HA, dec to RA, dec                 */
  /***************************************************************************/
  all_data->reported.app_radec[ 0 ] = ( all_data->reported.app_hadec[ 0 ] * 
					all_data->cos_lst -
					all_data->reported.app_hadec[ 1 ] * 
					all_data->sin_lst );
  all_data->reported.app_radec[ 1 ] = ( all_data->reported.app_hadec[ 0 ] * 
					all_data->sin_lst + 
					all_data->reported.app_hadec[ 1 ] * 
					all_data->cos_lst );
  all_data->reported.app_radec[ 2 ] = all_data->reported.app_hadec[ 2 ];

#ifdef DEBUG
  print_coords( "Apparent", all_data->reported.app_radec, 'r' );
#endif
  /***************************************************************************/
}
/*****************************************************************************/


/**
 *
 *
 */
void ast_apparent_2_mean( struct all_data_struct *all_data )
{
  /***************************************************************************/
  /* Intermediate data holders                                               */
  /***************************************************************************/
  int    i;
  double temp_ra1, temp_dec1;
  double temp_ra2, temp_dec2;
  double pos1[3], pos2[3], pos3[3], pos4[3], pos5[3];
  double pos6[3], pos7[3], pos8[3], pos9[3];
  double heliocentric_earth_norm[3];
  double sun_earth_dist;
  double precession_matrix[3][3];
  double nutation_matrix[3][3];
  double sys_out_ra, sys_out_dec;
  double output_ra, output_dec;
  double output_pm_ra, output_pm_dec;
  struct timestamp_struct output_eqx;
  /***************************************************************************/
  /* Intermediate data holders from slalib                                   */
  /***************************************************************************/
  double gr2e, pde, pdep1, w, abv[3], vm, ab1, p1dv, p1dvp1;

#ifdef DEBUG
  fprintf( stderr, "\nApparent -> Mean\n================\n" );
  print_coords( "Nutated", all_data->reported.app_radec, 'r' );
#endif
  /***************************************************************************/
  /* De-Nutation                                                             */
  /***************************************************************************/
  slaNut( all_data->timestamp.mjd_tdb, nutation_matrix );
  slaDimxv( nutation_matrix, all_data->reported.app_radec, pos9 );

#ifdef DEBUG
  print_coords( "Precessed", pos9, 'r' );
#endif
  /***************************************************************************/
  /* De-Precession                                                           */
  /***************************************************************************/
  slaPrecl( STANDARD_EPOCH, all_data->timestamp.jep_tdb, precession_matrix );
  slaDimxv( precession_matrix, pos9, pos8 );

#ifdef DEBUG
  print_coords( "Annual ab", pos8, 'r' );
#endif
  /***************************************************************************/
  /* Uncalculate Annual Aberration from slalib function slaAmpqk             */
  /***************************************************************************/
  abv[ 0 ] = all_data->bary_earth_vel[ 0 ] * C_AU_PER_DAY;
  abv[ 1 ] = all_data->bary_earth_vel[ 1 ] * C_AU_PER_DAY;
  abv[ 2 ] = all_data->bary_earth_vel[ 2 ] * C_AU_PER_DAY;
  slaDvn( abv, unused_vector, &vm );
  ab1 = sqrt( 1.0 - vm * vm );
  p1dv = slaDvdv( pos8, abv );
  p1dvp1 = 1.0 + p1dv;
  w = 1.0 + p1dv / ( ab1 + 1.0 );
  for( i = 0; i < 3; i++ )
    {
      pos7[ i ] = ( p1dvp1 * pos8[ i ] - w * abv[ i ] ) / ab1;
    }
  slaDvn( pos7, pos6, &unused_double );

#ifdef DEBUG
  print_coords( "Deflected", pos6, 'r' );
#endif
  /***************************************************************************/
  /* Uncalculate position deflection from Sun from slalib function slaAmpqk  */
  /***************************************************************************/
  slaDvn( all_data->helio_earth, heliocentric_earth_norm, 
	  &sun_earth_dist );
  gr2e = 1.974126E-08 / sun_earth_dist;
  pde = slaDvdv( pos6, heliocentric_earth_norm );
  pdep1 = 1.0 + pde;
  w = pdep1 - gr2e * pde;
  for( i = 0; i < 3; i++ )
    {
      pos5[ i ] = ( pdep1 * pos6[ i ] - gr2e * 
		    heliocentric_earth_norm[ i ] ) / w;

    }
  slaDvn( pos5, pos4, &unused_double );

#ifdef DEBUG
  print_coords( "Barycentric", pos4, 'r' );
#endif
  /***************************************************************************/
  /* Uncalculate position deflection from Sun from slalib function slaAmpqk  */
  /***************************************************************************/
  pos3[ 0 ] = pos4[ 0 ] - ( all_data->bary_earth[ 0 ] / 
			    1.0e+10 * PARSEC_2_AU );
  pos3[ 1 ] = pos4[ 1 ] - ( all_data->bary_earth[ 1 ] / 
			    1.0e+10 * PARSEC_2_AU );
  pos3[ 2 ] = pos4[ 2 ] - ( all_data->bary_earth[ 2 ] / 
			    1.0e+10 * PARSEC_2_AU );
  slaDvn( pos3, all_data->reported.sys_radec, &unused_double );

#ifdef DEBUG
  print_coords( "Geocentric", all_data->reported.sys_radec, 'r' );
  print_coords( "System coords", all_data->reported.sys_radec, 'r' );
#endif

  slaDcc2s( all_data->reported.sys_radec, &sys_out_ra, &sys_out_dec );
  /***************************************************************************/
  /* Convert to output format                                              */
  /***************************************************************************/
  if( all_data->reported.output_eqx.eqx_int == STANDARD_EQUINOX )
    {
      output_ra  = sys_out_ra;
      output_dec = sys_out_dec;
    }
  else
    {
      switch( all_data->reported.output_eqx.eqx_int )
	{
	  /*******************************************************************/
	  /* Convert J2000/FK5 to B1950                                      */
	  /*******************************************************************/
	case B1950:
	  slaFk54z( sys_out_ra, sys_out_dec, 1950.0,
		    &output_ra, &output_dec,
		    &output_pm_ra, &output_pm_dec );
	  break;
	  
	  /*******************************************************************/
	  /* Convert J2000/FK5 to ICRF                                       */
	  /*******************************************************************/
	case ICRF:
	  slaFk52h( sys_out_ra, sys_out_dec, 0.0, 0.0, 
		    &output_ra, &output_dec, 
		    &output_pm_ra, &output_pm_dec );
	  break;
	  
	  /*******************************************************************/
	  /* Convert APPARENT vector into RA,Dec                             */
	  /*******************************************************************/
	case APPARENT:
	  slaDcc2s( all_data->reported.app_radec, &output_ra, &output_dec );
	  break;
	  
	  /*******************************************************************/
	  /* Convert non-standard equinoxes                                  */
	  /*******************************************************************/
	default:
          parse_epoch( all_data->reported.output_eqx.epo.date, 
		       all_data->reported.output_eqx.epo.cal_int, 
		       all_data->reported.output_eqx.epo.ts_int, 
		       all_data, &output_eqx );
	  switch( all_data->reported.output_eqx.epo.cal_int )
	    {
	      /***************************************************************/
	      /* Convert J2000/FK5 output Besselian equinox                  */
	      /***************************************************************/
	    case BESSELIAN:
	      slaFk54z( sys_out_ra, sys_out_dec, output_eqx.bep_tdb,
			&output_pm_ra, &output_pm_dec,
			&temp_ra1, &temp_dec1 );
	      slaSubet( temp_ra1, temp_dec1, 
			STANDARD_EPOCH_BESSELIAN, 
			&temp_ra2, &temp_dec2 );
	      slaDcs2c( temp_ra2, temp_dec2, pos2 );
	      slaPrebn( STANDARD_EPOCH_BESSELIAN, 
			output_eqx.bep_tdb, 
			precession_matrix );
	      slaDmxv( precession_matrix, pos2, pos1 );
	      slaDcc2s( pos1, &temp_ra1, &temp_dec1 );
	      slaAddet( temp_ra1, temp_dec1, 
			output_eqx.bep_tdb, 
			&output_ra, &output_dec );
	      break;
	      
	    case JULIAN:
	      /***************************************************************/
	      /* Currently the system used internal in this                  */
	      /* library is FK5, so just precess to user_eqx_epo_date        */
	      /***************************************************************/
	      slaPrecl( STANDARD_EPOCH, output_eqx.jep_tdb, 
			precession_matrix );
	      slaDcs2c( sys_out_ra, sys_out_dec, pos2 );
	      slaDmxv( precession_matrix, pos2, pos1 );
	      slaDcc2s( pos1, &output_ra, &output_dec );
	      break;
	      
	    default:
	      /***************************************************************/
	      /* Anything Else here.                                         */
	      /***************************************************************/
	      
	      break;
	    }
	  
	  break;
	  
	}
    }
  slaDcs2c( output_ra, output_dec, &(all_data->reported.output_radec[ 0 ]) );

#ifdef DEBUG
  print_coords( "Output coords", all_data->reported.output_radec, 'r' );
#endif
  /***************************************************************************/
}
/*****************************************************************************/


/**
 * Parse the given UTC time into Julian epoch, Modified Julian Day and
 * adjust for UT1, TDB, TDT and TT.
 */
void ast_parse_timestamp( struct all_data_struct *all_data )
{
  /***************************************************************************/
  /* Intermediate data holders                                               */
  /***************************************************************************/
  double     eqn_eqx = 0.0, gmst = 0.0;
  double     whole_days;
  struct tm *t_struct;
  /***************************************************************************/
  /*  Determine relevant UTC time formats                                    */
  /***************************************************************************/
  t_struct = gmtime( (time_t *)&(all_data->timestamp.secs) );

  if( ( ( t_struct->tm_year + 1900 ) % 4 == 0 ) 
      && ( ( ( t_struct->tm_year + 1900 ) % 100 != 0 ) || 
	   ( ( t_struct->tm_year + 1900 ) % 400 == 0 ) ) )
    {
      all_data->timestamp.epoch_years = 
	( ( ( ( ( ( (double)t_struct->tm_sec + 
		    all_data->timestamp.nanosecs * 1.0E-09 ) / 60.0 + 
		  (double)t_struct->tm_min ) / 60.0 +
		(double)t_struct->tm_hour ) / 24.0 +
	      (double)t_struct->tm_yday ) / 366.0 ) +
	  (double)t_struct->tm_year + 1900.0 );
    }
  else
    {
      all_data->timestamp.epoch_years = 
	( ( ( ( ( ( (double)t_struct->tm_sec + 
		    all_data->timestamp.nanosecs * 1.0E-09 ) / 60.0 + 
		  (double)t_struct->tm_min ) / 60.0 +
		(double)t_struct->tm_hour ) / 24.0 +
	      (double)t_struct->tm_yday ) / 365.0 ) +
	  (double)t_struct->tm_year + 1900.0 );
    }

  parse_epoch( all_data->timestamp.epoch_years, all_data->timestamp.cal_int,
	       all_data->timestamp.ts_int, all_data,
	       &(all_data->timestamp) );
  /***************************************************************************/
  /*  Determine LST                                                          */
  /***************************************************************************/
  whole_days = floor( all_data->timestamp.mjd_ut1 );
  gmst = slaGmsta( whole_days, ( all_data->timestamp.mjd_ut1 - whole_days ) );
  eqn_eqx = slaEqeqx( all_data->timestamp.mjd_tdb );
  all_data->timestamp.lst = fmod( ( gmst + eqn_eqx + 
				    all_data->longitude ), D2PI );

  while( all_data->timestamp.lst < 0.0 )
    {
      all_data->timestamp.lst += D2PI;
    }

#ifdef DEBUG
  {
    int hmsf[ 4 ];
    char sign;

    fprintf( stderr, "      LST = %16.10f\n", all_data->timestamp.lst );
    slaDr2tf( 3, all_data->timestamp.lst, &sign, hmsf );
    fprintf( stderr, "      LST = %c%02dh %02dm %02d.%03ds\n",
	     sign, hmsf[ 0 ], hmsf[ 1 ], hmsf[ 2 ], hmsf[ 3 ] );
  }
#endif

  all_data->cos_lst = cos( all_data->timestamp.lst );
  all_data->sin_lst = sin( all_data->timestamp.lst );
  /***************************************************************************/
}
/*****************************************************************************/


/*****************************************************************************/
/* Convert the argument epoch into a full time_struct structure              */
/*****************************************************************************/
void parse_epoch( double epoch, Calendar cal, Timescale tscale,
		  struct all_data_struct *all_data,
		  struct timestamp_struct *timestamp )
{
  int    sla_ret_val;
  int    year, month, days;
  double epoch_mjd, day_fraction;
  double tdb_tdt, old_tdb_tdt;

#ifdef DEBUG
  fprintf(stderr,"epoch = %8.3f ",epoch);
  switch(cal)
      {
      case JULIAN:
	  fprintf(stderr,"JULIAN ");
	  break;
      case BESSELIAN:
	  fprintf(stderr,"BESSELIAN ");
	  break;
      case GREGORIAN:
	  fprintf(stderr,"GREGORIAN ");
	  break;
      default:
	  break;
      }
  switch(tscale)
      {
    case UTC:
	fprintf(stderr,"UTC");
	break;
    case UT1:
	fprintf(stderr,"UT1");
	break;
    case TDB:
	fprintf(stderr,"TDB");
	break;
    case TDT:
	fprintf(stderr,"TDT");
	break;
    case TAI:
	fprintf(stderr,"TAI");
	break;
    default:
      break;
      }
  fprintf( stderr, "\n" );
#endif

  /***************************************************************************/
  /* Convert all calendar systems to the Modified Julian Date system         */
  /***************************************************************************/
  switch( cal )
    {
      /***********************************************************************/
      /* Convert Gregorian calendar to MJD                                   */
      /* Adjust user specified epoch into Julian epoch detecting leap years  */
      /* (every 4 yrs, except every 100 but including every 400)             */
      /***********************************************************************/
    case GREGORIAN:
      year = (int)( floor( epoch ) );
      if( ( year%4 == 0 ) && ( ( year%100 != 0 ) || ( year%400 == 0 ) ) )
	{
	  days_month[ 1 ] = 29;
	  days = ceil( ( epoch - (double)year ) * 366.0 );
	  day_fraction = ( ( epoch - (double)year ) * 366.0 - 
			   (double)days + 1.0 );
	}
      else
	{
	  days_month[ 1 ] = 28;
	  days = ceil( ( epoch - (double)year ) * 365.0 );
	  day_fraction = ( ( epoch - (double)year ) * 365.0 
			   - (double)days + 1.0 );
	}
      month = 0;
      while( days >= days_month[ month ] )
	{
	  days -= days_month[ month ];
	  month++;
	}
      month++;
      slaCaldj( year, month, days, &epoch_mjd, &sla_ret_val );
      epoch_mjd += day_fraction;
      break;
      
      /*********************************************************************/
      /* Convert Julian calendar to MJD                                    */
      /*********************************************************************/
    case JULIAN:
      epoch_mjd = slaEpj2d( epoch );
      break;
      
      /*********************************************************************/
      /* Convert Besselian calendar to MJD                                 */
      /*********************************************************************/
    case BESSELIAN:
      epoch_mjd = slaEpb2d( epoch );
      break;
      
      /*********************************************************************/
      /* Default switch - nothing to be done                               */
      /*********************************************************************/
    default:
      
      break;
    }
  
  /***************************************************************************/
  /* Convert all timescales to UT1                                           */
  /***************************************************************************/
  /*                                                                         */
  /* NOTE                                                                    */
  /* ====                                                                    */
  /* Apart from the top case of UTC it is important to realise that          */
  /* this switch block contains ONLY 1 break, in the default case at the end.*/
  /* This is due to the fact that the conversions all follow in the process  */
  /* of converting to UT1 :                                                  */
  /*                        TDB -> TDT -> TAI -> UT1                         */
  /*                                                                         */
  /* If the timescale (tscale) is anything but TDB, the missing values in    */
  /* the timestamp struct are filled in in the following switch block.       */
  /***************************************************************************/
  switch( tscale )
    {
      /*********************************************************************/
      /* UTC timescale converted to UT1                                    */
      /*********************************************************************/
    case UTC:
      epoch_mjd += all_data->iers.ut1_utc / 86400.0;
      break;
      
      /*********************************************************************/
      /* TDB timescale converted to TDT                                    */
      /*********************************************************************/
    case TDB:
      timestamp->mjd_tdb = epoch_mjd;
      timestamp->jep_tdb = slaEpj( epoch_mjd );
      timestamp->bep_tdb = slaEpb( epoch_mjd );

      tdb_tdt = slaRcc( epoch_mjd, 
			epoch_mjd - floor( epoch_mjd ), 
			-all_data->longitude, 
			( all_data->uau * AU_2_METRES / 1000.0 ), 
			( all_data->vau * AU_2_METRES / 1000.0 ) );

      epoch_mjd -= tdb_tdt / 86400.0;
      /*********************************************************************/
      /* TDT timescale converted to TAI                                    */
      /*********************************************************************/
    case TDT:
      timestamp->mjd_tdt = epoch_mjd;
      timestamp->jep_tdt = slaEpj( epoch_mjd );
      timestamp->bep_tdt = slaEpb( epoch_mjd );
      epoch_mjd -= 32.184 / 86400.0;
      
      /*********************************************************************/
      /* TAI timescale converted to UT1                                    */
      /*********************************************************************/
    case TAI:
      timestamp->mjd_tai = epoch_mjd;
      timestamp->jep_tai = slaEpj( epoch_mjd );
      timestamp->bep_tai = slaEpb( epoch_mjd );
      epoch_mjd -= ( ( all_data->iers.tai_utc + all_data->iers.ut1_utc ) 
		     / 86400.0 );
      
      /*********************************************************************/
      /* Default timescale - already UT1                                   */
      /*********************************************************************/
    default:
      timestamp->mjd_ut1 = epoch_mjd;
      timestamp->jep_ut1 = slaEpj( epoch_mjd );
      timestamp->bep_ut1 = slaEpb( epoch_mjd );
      break;
    }
  
  /***************************************************************************/
  /* Assign missing structure values                                         */
  /***************************************************************************/
  /*                                                                         */
  /* NOTE                                                                    */
  /* ====                                                                    */
  /* This switch block contains ONLY 2 breaks, in the default case at the    */
  /* end.  This is due to the fact that the conversions all follow in the    */
  /* process of converting to TDB :                                          */
  /*                                UTC -> UT1 -> TAI -> TDT -> TDB          */
  /*                                                                         */
  /* The 2 breaks are for: a) TDB (in which case ALL other timescales were   */
  /* calculated) and b) the default switch at the end.                       */
  /***************************************************************************/
  switch( tscale )
    {
      /*********************************************************************/
      /* UTC timescale already converted to UT1 so set and fall-through    */
      /*********************************************************************/
    case UTC:
      timestamp->mjd_ut1 = epoch_mjd;
      timestamp->jep_ut1 = slaEpj( epoch_mjd );
      timestamp->bep_ut1 = slaEpb( epoch_mjd );
      
      /*********************************************************************/
      /* UT1 timescale converted to TAI                                    */
      /*********************************************************************/
    case UT1:
      epoch_mjd += ( ( all_data->iers.tai_utc + all_data->iers.ut1_utc ) 
		     / 86400.0 );
      timestamp->mjd_tai = epoch_mjd;
      timestamp->jep_tai = slaEpj( epoch_mjd );
      timestamp->bep_tai = slaEpb( epoch_mjd );
      
      /*********************************************************************/
      /* TAI timescale converted to TDT                                    */
      /*********************************************************************/
    case TAI:
      epoch_mjd += 32.184 / 86400.0;
      timestamp->mjd_tdt = epoch_mjd;
      timestamp->jep_tdt = slaEpj( epoch_mjd );
      timestamp->bep_tdt = slaEpb( epoch_mjd );

      /*********************************************************************/
      /* TDT timescale converted to TDB                                    */
      /*********************************************************************/
    case TDT:
      tdb_tdt = 0.0;
      while( fabs( old_tdb_tdt - tdb_tdt ) > 0.0001 )
	{
	  old_tdb_tdt = tdb_tdt;
	  tdb_tdt = slaRcc( ( epoch_mjd + tdb_tdt / 86400.0 ),
			    ( timestamp->mjd_ut1 - 
			      floor( timestamp->mjd_ut1 ) ),
			    -all_data->longitude,
			    ( all_data->uau * AU_2_METRES / 1000.0 ), 
			    ( all_data->vau * AU_2_METRES / 1000.0 ) );
	}

      epoch_mjd += tdb_tdt / 86400.0;
      timestamp->mjd_tdb = epoch_mjd;
      timestamp->jep_tdb = slaEpj( epoch_mjd );
      timestamp->bep_tdb = slaEpb( epoch_mjd );
      break;
      
      /*********************************************************************/
      /* If TDB is the timescale, then all other timescales were           */
      /* calculated in the previous switch block                           */
      /*********************************************************************/
    case TDB:
      
      break;
      
      /*********************************************************************/
      /* Nothing to be done                                                */
      /*********************************************************************/
    default:
      
      break;
    }
  /***************************************************************************/

#ifdef DEBUG
  fprintf(stderr,"      MJD UT1 = %16.10f\n",timestamp->mjd_ut1);
  fprintf(stderr,"   Julian UT1 = %16.10f\n",timestamp->jep_ut1);
  fprintf(stderr,"Besselian UT1 = %16.10f\n",timestamp->bep_ut1);
  fprintf(stderr,"      MJD TAI = %16.10f\n",timestamp->mjd_tai);
  fprintf(stderr,"   Julian TAI = %16.10f\n",timestamp->jep_tai);
  fprintf(stderr,"Besselian TAI = %16.10f\n",timestamp->bep_tai);
  fprintf(stderr,"      MJD TDT = %16.10f\n",timestamp->mjd_tdt);
  fprintf(stderr,"   Julian TDT = %16.10f\n",timestamp->jep_tdt);
  fprintf(stderr,"Besselian TDT = %16.10f\n",timestamp->bep_tdt);
  fprintf(stderr,"      MJD TDB = %16.10f\n",timestamp->mjd_tdb);
  fprintf(stderr,"   Julian TDB = %16.10f\n",timestamp->jep_tdb);
  fprintf(stderr,"Besselian TDB = %16.10f\n",timestamp->bep_tdb);
  fprintf(stderr,"\n");
#endif
}
/*****************************************************************************/


#ifdef DEBUG
void print_coords( char *vec_name, double vec[3], char type )
{
  double vec2[3];
  double angle1, angle2;
  int    i, j, hmsf[4], dmsf[4];
  char   ra_sign, ha_sign, dec_sign;
  char   vector_name[ 16 ];
  
  vec2[ 0 ] = vec[ 0 ];
  vec2[ 1 ] = vec[ 1 ];
  vec2[ 2 ] = vec[ 2 ];

  i = 0;
  while( i++ < 15 ) vector_name[ i - 1 ] = ' ';
  j = strlen( vec_name );
  if( j > 14 ) j = 14;
  for( i = 0; i < j; i++ )
      {
	  vector_name[ i + 14 - j ] = vec_name[ i ];
      }
  vector_name[ 15 ] = '\0';

  ha_sign = ' ';
  if( type != 'a' )
    {
      if( type == 'r' )
	{
	  slaDcc2s( vec2, &angle1, &angle2 );
	  angle1 = slaDranrm( angle1 );
	  slaDr2tf( 4, angle1, &ra_sign, hmsf );
	}
      else if( type == 'h' )
	{
	  vec2[ 1 ] = -vec[ 1 ];
	  slaDcc2s( vec2, &angle1, &angle2 );
	  angle1 = slaDrange( angle1 );
	  slaDr2tf( 4, angle1, &ha_sign, hmsf );
	}
      slaDr2af( 3, angle2, &dec_sign, dmsf );
      fprintf( stderr, "%s : %c%02d %02d %02d.%04d   "
	       "%c%02d %02d %02d.%03d\n", 
	       vector_name, 
	       ha_sign,  hmsf[0], hmsf[1], hmsf[2], hmsf[3], 
	       dec_sign, dmsf[0], dmsf[1], dmsf[2], dmsf[3] );
    }
  else
    {
      slaDcc2s( vec2, &angle1, &angle2 );
      angle1 = slaDranrm( angle1 );
      angle1 = angle1 * DR2D;
      angle2 = angle2 * DR2D;
      fprintf( stderr, "%s : %015.11f  %+015.11f\n",
	       vector_name, angle1, angle2 );
    }
}
#endif
