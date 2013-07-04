/**
 * These values are used throughout the astrometry modules, and should be
 * checked to ensure they are the same values as those defined in the Java
 * code that calls these functions.
 *
 * @version $Revision: 1.1 $
 * @author $Author: cjm $
 *
 *    $Date: 2013-07-04 09:59:11 $
 * $RCSfile: astro_values.h,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/c/astro_values.h,v $
 *     $Log: not supported by cvs2svn $
 *      $Id: astro_values.h,v 1.1 2013-07-04 09:59:11 cjm Exp $
 */

#ifndef _ASTRO_DATA
#define _ASTRO_DATA

#define RADS_2_DEGS                      ( 5.72957795130823229E+01 )
#define RADS_2_HRS                       ( 3.81971863420548807E+00 )
#define RADS_2_SECS                      ( 1.37509870831397571E+05 )
#define RADS_PER_YEAR_2_SECS_PER_CENT    ( 1.37509870831397572E+06 )
#define RADS_PER_YEAR_2_ARCSECS_PER_CENT ( 2.06264806247096360E+07 )

#define DEGS_2_RADS                      ( 1.74532925199432955E-02 )
#define DEGS_2_MAS                       ( 3.60000000000000000E+06 )

#define ARCS_2_RADS                      ( 4.84813681109535984E-06 )
#define ARCSECS_PER_CENT_2_RADS_PER_YEAR ( 4.84813681109535942E-08 )

#define DAYS_PER_YEAR                    ( 3.65242198781730500E+02 )
#define DAYS_PER_CENTURY                 ( 3.65242198781730500E+04 )

#define HRS_2_RADS                       ( 2.61799387799149408E-01 )
#define HRS_2_MAS                        ( 5.40000000000000000E+07 )

#define SECS_2_RADS                      ( 7.27220521664303985E-05 )
#define SECS_PER_CENT_2_RADS_PER_YEAR    ( 7.27220521664303913E-07 )
#define SECS_PER_YEAR                    ( 3.15569300000000000E+07 )

#define PARSEC_2_METRES                  ( 3.08567760000000000E+16 )
#define AU_2_METRES                      ( 1.49597870000000000E+11 )
#define PARSEC_2_AU                      ( 2.06264800000000000E+05 )

#define C_AU_PER_DAY                     ( 4.99004782000000000E+02 )

#define SOLAR_2_SIDEREAL                 ( 1.00273790926496311E+00 )

#define J2000_BESSELIAN                  ( 2000.00127751367 )

/**
 * Used to define Calendars, defined in [CalendarType.java].
 */
#define BESSELIAN                        ( 2021 )
#define JULIAN                           ( 2022 )
#define GREGORIAN                        ( 2023 )

/**
 * Used to define Equinoxes, defined in [Equinox.java].
 */
#define B1950                            ( 2031 )
#define FK4                              ( 2031 )
#define J2000                            ( 2032 )
#define FK5                              ( 2032 )
#define ICRF                             ( 2033 )
#define APPARENT                         ( 2034 )
#define HORIZON                          ( 2035 )

/**
 * used to define Timescales, define in [TimescaleType.java].
 */
#define UT                               ( 3001 )
#define UT1                              ( 3001 )
#define UTC                              ( 3002 )
#define TAI                              ( 3003 )
#define TDB                              ( 3004 )
#define TDT                              ( 3005 )

/**
 * Used to define Rotator modes, define in [RotatorMode.java].
 */
#define SKY_POS                          ( 4001 )
#define MOUNT_POS                        ( 4002 )
#define FLOAT_SKY_POS                    ( 4003 )
#define VERT_POS                         ( 4004 )
#define FLOAT_VERT_POS                   ( 4005 )

#endif
