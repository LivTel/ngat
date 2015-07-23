/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class ngat_astrometry_NGATAstro */

#ifndef _Included_ngat_astrometry_NGATAstro
#define _Included_ngat_astrometry_NGATAstro
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     ngat_astrometry_NGATAstro
 * Method:    initialiseLoggerReference
 * Signature: (Lngat/util/logging/Logger;)V
 */
JNIEXPORT void JNICALL Java_ngat_astrometry_NGATAstro_initialiseLoggerReference
  (JNIEnv *, jobject, jobject);

/*
 * Class:     ngat_astrometry_NGATAstro
 * Method:    finaliseLoggerReference
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_ngat_astrometry_NGATAstro_finaliseLoggerReference
  (JNIEnv *, jobject);

/*
 * Class:     ngat_astrometry_NGATAstro
 * Method:    GetMJD
 * Signature: (J)D
 */
JNIEXPORT jdouble JNICALL Java_ngat_astrometry_NGATAstro_GetMJD
  (JNIEnv *, jclass, jlong);

#ifdef __cplusplus
}
#endif
#endif
