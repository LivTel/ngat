/**
 * 
 *  @author $Author: cjm $
 * @version $Revision: 1.1 $
 */

/*     $Date: 2013-07-04 09:59:11 $
 *  $RCSfile: astro_structs.h,v $
 *   $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/c/astro_structs.h,v $
 *      $Log: not supported by cvs2svn $
*/

#ifndef _ASTRO_STRUCTS_H
#define _ASTRO_STRUCTS_H


/**
 * Define data type Calendar.
 */
typedef int Calendar;

/**
 * Define data type Equinox.
 */
typedef int Equinox;

/**
 * Define data Timescale.
 */
typedef int Timescale;

/**
 *
 */
typedef int Rotator_mode;

/**
 *
 */
struct timestamp_struct
{
  /* current timestamp */
  long secs;
  long nanosecs;
  double epoch_secs;
  double epoch_years;
  Calendar cal_int;
  Timescale ts_int;
  /* values to be filled for astrometry */
  double lst;
  double mjd_ut1;
  double jep_ut1;
  double bep_ut1;
  double mjd_tai;
  double jep_tai;
  double bep_tai;
  double mjd_tdt;
  double jep_tdt;
  double bep_tdt;
  double mjd_tdb;
  double jep_tdb;
  double bep_tdb;
};


struct epoch_struct
{
  double    date;
  Calendar  cal_int;
  Timescale ts_int;
};


struct equinox_struct
{
  Equinox eqx_int;
  struct epoch_struct epo;
};


struct target_struct
{
  double ra;
  double dec;
  double pm_ra;
  double pm_dec;
  double rad_vel;
  double plx;
  double off_ra;
  double off_dec;
  double ns_ra;
  double ns_dec;
  long   ns_t_utc_secs;
  long   ns_t_utc_nanosecs;
  double ns_utc_all_secs;
  struct equinox_struct eqx;
  struct epoch_struct epo;
};


struct site_struct
{
  double longitude;
  double latitude;
  double altitude;
};


struct iers_struct
{
  double ut1_utc;
  double tt_ut1;
  int    tai_utc;
  double polmo_x;
  double polmo_y;
};


struct met_struct
{
  double temp_k;
  double pres;
  double hum;
  double tlr;
};


struct reported_target_struct
{
  struct equinox_struct output_eqx;
  double obs[3];
  double unref[3];
  double obs_radec[3];
  double topo_hadec[3];
  double app_hadec[3];
  double app_radec[3];
  double sys_radec[3];
  double output_radec[3];
  double airmass;
};


struct all_data_struct
{
  double longitude;
  double latitude;
  double sin_lat;
  double cos_lat;
  double e2h[3][3];
  double azimuth_correction;
  double cos_lst;
  double sin_lst;
  double observatory[3];
  double uau;
  double vau;
  double target_dist_au;
  double helio_earth[3];
  double helio_earth_vel[3];
  double bary_earth[3];
  double bary_earth_vel[3];
  double app_radec[3];
  double app_hadec[3];
  double observed[3];
  double lambda;
  struct timestamp_struct user_eqx;
  struct timestamp_struct user_epo;
  struct equinox_struct rot_eqx;
  struct target_struct target;
  struct timestamp_struct timestamp;
  struct site_struct site;
  struct iers_struct iers;
  struct met_struct met;
  struct reported_target_struct reported;
};

#endif
