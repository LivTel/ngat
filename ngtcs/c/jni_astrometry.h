/**
 * Library using slalib functions to implement the C layer of the 
 * SlalibAstrometricKernel class.  The SlalibAstrometricKernel class
 * is an implementation of the AstrometryCalculator interface used by
 * the NGTCS.
 * 
 *  @author $Author: cjm $
 * @version $Revision: 1.1 $
 */

/*     $Date: 2013-07-04 09:59:54 $
 *  $RCSfile: jni_astrometry.h,v $
 *   $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/c/jni_astrometry.h,v $
 *      $Log: not supported by cvs2svn $
*/

#ifndef _JNI_ASTRO_H
#define _JNI_ASTRO_H

#include<jni.h>

#include"astro_structs.h"

/**
 *
 */
#define FAILED      10001
#define SUCCEEDED   10002

/**
 * Parse and return the specified Timestamp object.
 */
int jni_parse_timestamp( JNIEnv *env, jobject timestamp_obj,
			 struct timestamp_struct *timestamp );


/**
 * Parse the specified Target object and assign variables in the 
 * [astrometry_data] module.
 */
int jni_parse_target( JNIEnv *env, jobject target_obj,
		      struct target_struct *target );


/**
 *
 */
int jni_parse_site_data( JNIEnv *env, jobject site_data_obj,
			 struct site_struct *site );


/**
 *
 */
int jni_parse_iers_data( JNIEnv *env, jobject site_data_obj,
			 struct iers_struct *iers );


/**
 *
 */
int jni_parse_met_data( JNIEnv *env, jobject site_data_obj,
			struct met_struct *met );

/**
 *
 */
int jni_parse_equinox( JNIEnv *env, jobject eqx_obj,
		       struct equinox_struct *eqx );

/**
 *
 */
int jni_parse_epoch( JNIEnv *env, jobject epo_obj,
		     struct epoch_struct *epo );

/**
 * 
 */
int jni_parse_xyzmatrix( JNIEnv *env, jobject matrix_obj, double mx[3] );


#endif
