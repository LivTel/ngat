#include <jni.h>
#include <stdio.h>
#include "Linux_sla/include/slalib.h"
//#include "/Linux_sla/slamac.h"   
#include "ngat_astrometry_JSlalib.h"


JNIEXPORT jobject JNICALL Java_ngat_astrometry_JSlalib_callSlaPlante
    (JNIEnv *env, jclass slaclass, jdouble date, jdouble elong, jdouble phi, jint jform, 
     jdouble epoch, jdouble orbinc, jdouble anode, 
     jdouble perih, jdouble aorq, jdouble e, 
     jdouble aorl, jdouble dm) 
{    
    jclass    posnClass;
    jobject   posn;
    jmethodID cid;
    jdouble p;
    double fra;
    double fdec;
    double r;
    int jstat;

    posnClass = (*env)->FindClass(env, "ngat/astrometry/Position");
    if (posnClass == NULL) {
	return NULL;
    }
    
    cid = (*env)->GetMethodID(env, posnClass, "<init>", "(DD)V");
    if (cid == NULL) {
	return NULL;
    }

    //printf("\ncallSlaPlante: entered with epoch:%.3f, orbinc:%.3f, anode:%.3f, perih:%.3f, aorq:%.3f, e:%.3f", epoch,orbinc,anode,perih,aorq,e);

    slaPlante ( date, elong, phi, jform,
		epoch, orbinc, anode, perih,
		aorq, e, aorl, dm,
		&fra, &fdec, &r, &jstat );

   
    posn = (*env)->NewObject(env, posnClass, cid, fra, fdec);
    if (posn == NULL) {
	return NULL;
    }
    
    return posn;
}

JNIEXPORT jobject JNICALL Java_ngat_astrometry_JSlalib_callSlaDtp2s
    (JNIEnv *env, jclass slaclass, jdouble xi, jdouble eta, jdouble raz, jdouble decz) 
{    
    jclass    posnClass;
    jobject   posn;
    jmethodID cid;
    jdouble p;
    double fra;
    double fdec;
   
    posnClass = (*env)->FindClass(env, "ngat/astrometry/Position");
    if (posnClass == NULL) {
	return NULL;
    }
    
    cid = (*env)->GetMethodID(env, posnClass, "<init>", "(DD)V");
    if (cid == NULL) {
	return NULL;
    }
    printf("\ncallSlaDtp2s: entered with raz:%.3f, decz:%.3f", raz, decz);

    slaDtp2s ( xi, eta, raz, decz, &fra, &fdec);

    printf("\ncallSlaDtp2s: returned ra:%.3f, dec:%.3f \n", fra, fdec);
    posn = (*env)->NewObject(env, posnClass, cid, fra, fdec);
    if (posn == NULL) {
	return NULL;
    }
    
    return posn;
}

JNIEXPORT jobject JNICALL Java_ngat_astrometry_JSlalib_callSlaMap
    (JNIEnv *env, jclass slaclass, 
     jdouble ram, jdouble decm, 
     jdouble pmra, jdouble pmdec, 
     jdouble para, jdouble rv,
     jdouble eq, jdouble  mjd) 
{       
    jclass    posnClass;
    jobject   posn;
    jmethodID cid;
    jdouble p;
    double fra;
    double fdec;
   
    posnClass = (*env)->FindClass(env, "ngat/astrometry/Position");
    if (posnClass == NULL) {
	return NULL;
    }
    
    cid = (*env)->GetMethodID(env, posnClass, "<init>", "(DD)V");
    if (cid == NULL) {
	return NULL;
    }
  
    slaMap(ram, decm, pmra, pmdec, para, rv, eq, mjd, &fra, &fdec);

    printf("\ncallSlaMap: returned ra:%.3f, dec:%.3f \n", fra, fdec);
    posn = (*env)->NewObject(env, posnClass, cid, fra, fdec);
    if (posn == NULL) {
	return NULL;
    }
    
    return posn;
}

JNIEXPORT jobject JNICALL Java_ngat_astrometry_JSlalib_callSlaPlanel
 (JNIEnv *env, jclass slaclass, 
  jdouble date, jint jform,  jdouble epoch,  jdouble orbinc, jdouble anode, 
  jdouble perih, jdouble aorq, jdouble e,  jdouble aorl, jdouble dm)
{
  jclass    pvClass;
  jobject   pv;
  jmethodID cid;
  jdouble   dx;
  jdouble   dy;
  jdouble   dz;
  jdouble   vx;
  jdouble   vy;
  jdouble   vz;
  int jstat;

  double pvs[6];
  
  slaPlanel(date, jform, epoch, orbinc, anode, perih, aorq, e, aorl, dm, pvs, &jstat);
  // printf("\ncallSlaPlanel: jstat:%i\n", jstat);
 
  dx = pvs[0];
  dy = pvs[1];
  dz = pvs[2];
  vx = pvs[3];
  vy = pvs[4];
  vz = pvs[5];

  pvClass = (*env)->FindClass(env, "ngat/astrometry/PositionVelocity");
  if (pvClass == NULL) {
    return NULL;
  }
  
  cid = (*env)->GetMethodID(env, pvClass, "<init>", "(DDDDDD)V");
  if (cid == NULL) {
    return NULL;
  }

  pv = (*env)->NewObject(env, pvClass, cid, dx, dy, dz, vx, vy, vz );
  if (pv == NULL) {
    return NULL;
  }
  
  return pv;

}

JNIEXPORT jobject JNICALL Java_ngat_astrometry_JSlalib_callSlaRdplan
(JNIEnv *env, jclass pclass, jdouble date, jint iplanet, jdouble elong, jdouble phi)
{
    jclass    posnClass;
    jobject   posn;
    jmethodID cid;
    jdouble p;
    double fra;
    double fdec;
    double fdiam;

    //printf("\ncallSlaRdplan: entered with ip: %i, date:% .3f\n", iplanet, date);
    //fflush(stdout);

    posnClass = (*env)->FindClass(env, "ngat/astrometry/Position");
    if (posnClass == NULL) {
	return NULL;
    }
    
    cid = (*env)->GetMethodID(env, posnClass, "<init>", "(DD)V");
    if (cid == NULL) {
	return NULL;
    }
    //printf("\ncallSlaRdplan: here we go\n");
    //fflush(stdout);

    slaRdplan(date, iplanet, elong, phi, &fra, &fdec, &fdiam);

    //printf("\ncallSlaRdplan: returned ra:%.3f, dec:%.3f \n", fra, fdec);

    posn = (*env)->NewObject(env, posnClass, cid, fra, fdec);
    if (posn == NULL) {
	return NULL;
    }
    
    return posn;
}

JNIEXPORT jobject JNICALL Java_ngat_astrometry_JSlalib_callSlaPlanet
(JNIEnv *env, jclass planetclass, jdouble mjd, jint iplanet)
{ 
  jclass    pvClass;
  jobject   pv;
  jmethodID cid;
  jdouble   dx;
  jdouble   dy;
  jdouble   dz;
  jdouble   vx;
  jdouble   vy;
  jdouble   vz;
  int jstat;

  double pvs[6];

  slaPlanet(mjd, iplanet, pvs, &jstat);
  printf("\ncallSlaPlanet: jstat:%i\n", jstat);
  dx = pvs[0];
  dy = pvs[1];
  dz = pvs[2];
  vx = pvs[3];
  vy = pvs[4];
  vz = pvs[5];

  pvClass = (*env)->FindClass(env, "ngat/astrometry/PositionVelocity");
  if (pvClass == NULL) {
    return NULL;
  }
  
  cid = (*env)->GetMethodID(env, pvClass, "<init>", "(DDDDDD)V");
  if (cid == NULL) {
    return NULL;
  }

  pv = (*env)->NewObject(env, pvClass, cid, dx, dy, dz, vx, vy, vz );
  if (pv == NULL) {
    return NULL;
  }
  
  return pv;
}







JNIEXPORT jdouble JNICALL Java_ngat_astrometry_JSlalib_callSlaCldj
  (JNIEnv *env, jclass cldjclass, jint iy, jint im, jint id)
{
  double fmjd;
  int jstat;

  slaCldj(iy, im, id, &fmjd, &jstat);
 
  return fmjd;

}

JNIEXPORT jdouble JNICALL Java_ngat_astrometry_JSlalib_callSlaDbear
  (JNIEnv *env, jclass bearclass, jdouble az1, jdouble alt1, jdouble az2, jdouble alt2)
{
  double bear = slaDbear(az1, alt1, az2, alt2);
 
  return bear;
}


JNIEXPORT jdouble JNICALL Java_ngat_astrometry_JSlalib_callSlaGmsta
  (JNIEnv *env, jclass gmstclass, jdouble mjd, jdouble ut1)
{
  double gmst = slaGmsta(mjd, ut1);
 
  return gmst;
}

JNIEXPORT jdouble JNICALL Java_ngat_astrometry_JSlalib_callSlaDtt
(JNIEnv *env, jclass dttclass, jdouble dju)
{
  double ttut = slaDtt(dju);
 
  return ttut;
}


JNIEXPORT jdouble JNICALL Java_ngat_astrometry_JSlalib_callSlaEqeqx
  (JNIEnv *env, jclass eqeqxclass, jdouble mjd)
{
  double eqeqx = slaEqeqx(mjd);
 
  return eqeqx;
}

JNIEXPORT jobject JNICALL Java_ngat_astrometry_JSlalib_callSlaDmoon
(JNIEnv *env, jclass dmoonclass, jdouble mjd)
{ 
  jclass    pvClass;
  jobject   pv;
  jmethodID cid;
  jdouble   dx;
  jdouble   dy;
  jdouble   dz;
  jdouble   vx;
  jdouble   vy;
  jdouble   vz;
  int j;

  double pvs[6];

  slaDmoon(mjd, pvs);

  dx = pvs[0];
  dy = pvs[1];
  dz = pvs[2];
  vx = pvs[3];
  vy = pvs[4];
  vz = pvs[5];

  pvClass = (*env)->FindClass(env, "ngat/astrometry/PositionVelocity");
  if (pvClass == NULL) {
    return NULL;
  }
  
  cid = (*env)->GetMethodID(env, pvClass, "<init>", "(DDDDDD)V");
  if (cid == NULL) {
    return NULL;
  }

  pv = (*env)->NewObject(env, pvClass, cid, dx, dy, dz, vx, vy, vz );
  if (pv == NULL) {
    return NULL;
  }
  
  return pv;
}


JNIEXPORT jobject JNICALL Java_ngat_astrometry_JSlalib_callSlaPvobs
(JNIEnv *env, jclass pvobsclass, jdouble lat, jdouble height, jdouble last)
{ 
  jclass    pvClass;
  jobject   pv;
  jmethodID cid;
  jdouble   dx;
  jdouble   dy;
  jdouble   dz;
  jdouble   vx;
  jdouble   vy;
  jdouble   vz;
  int j;

  double pvs[6];

  slaPvobs(lat, height, last, pvs);

  dx = pvs[0];
  dy = pvs[1];
  dz = pvs[2];
  vx = pvs[3];
  vy = pvs[4];
  vz = pvs[5];

  pvClass = (*env)->FindClass(env, "ngat/astrometry/PositionVelocity");
  if (pvClass == NULL) {
    return NULL;
  }
  
  cid = (*env)->GetMethodID(env, pvClass, "<init>", "(DDDDDD)V");
  if (cid == NULL) {
    return NULL;
  }

  pv = (*env)->NewObject(env, pvClass, cid, dx, dy, dz, vx, vy, vz );
  if (pv == NULL) {
    return NULL;
  }
  
  return pv;
}


/*
 * Class:     ngat_astrometry_JSlalib
 * Method:    callSlaEvp
 * Signature: (D)Lngat/astrometry/PositionVelocity;
 */
JNIEXPORT jobject JNICALL Java_ngat_astrometry_JSlalib_callSlaEvp
(JNIEnv *env, jclass evpclass, jdouble mjd)
{
  jclass    pvClass;
  jobject   pv;
  jmethodID cid;
  jdouble   dx;
  jdouble   dy;
  jdouble   dz;
  jdouble   vx;
  jdouble   vy;
  jdouble   vz;
  int j;
  jdouble   deqx;

  double dvb[3];
  double dpb[3];
  double dvh[3];
  double dph[3];

  deqx = 2000.0;
  
  slaEvp(mjd, deqx, dvb, dpb, dvh, dph);
  
  dx = -dph[0];
  dy = -dph[1];
  dz = -dph[2];
  vx = dvh[0];
  vy = dvh[1];
  vz = dvh[2];

  pvClass = (*env)->FindClass(env, "ngat/astrometry/PositionVelocity");
  if (pvClass == NULL) {
    return NULL;
  }
  
  cid = (*env)->GetMethodID(env, pvClass, "<init>", "(DDDDDD)V");
  if (cid == NULL) {
    return NULL;
  }
  
  pv = (*env)->NewObject(env, pvClass, cid, dx, dy, dz, vx, vy, vz );
  if (pv == NULL) {
    return NULL;
  }
  
  return pv;

}




