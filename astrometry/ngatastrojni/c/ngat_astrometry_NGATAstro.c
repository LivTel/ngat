/* ngat_astrometry_NGATAstro.c
** JNI interface to the NGAT Astro library (used for MJD calculations at the moment).
** $Header$ 
*/
/**
 * ngat_astrometry_NGATAstro.c is the 'glue' between libngatastro,and ngat.astrometry.NGATAstro.
 * @author Chris Mottram LJMU
 * @version $Revision$
 */
/**
 * This hash define is needed before including source files give us POSIX.4/IEEE1003.1b-1993 prototypes
 * for time.
 */
#define _POSIX_SOURCE 1
/**
 * This hash define is needed before including source files give us POSIX.4/IEEE1003.1b-1993 prototypes
 * for time.
 */
#define _POSIX_C_SOURCE 199309L
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <jni.h>
#include <time.h>
#include "ngat_astro.h"
#include "ngat_astro_mjd.h"

/* --------------------------------------------------------- */
/* internal variables */
/* --------------------------------------------------------- */
/**
 * Revision Control System identifier.
 */
static char rcsid[] = "$Id$";
/**
 * Copy of the java virtual machine pointer, used for logging back up to the Java layer from C.
 */
static JavaVM *java_vm = NULL;
/**
 * Cached global reference to the "ngat.astrometry.NGATAstro" logger, 
 * used to log back to the Java layer from C routines.
 */
static jobject logger = NULL;
/**
 * Cached reference to the "ngat.util.logging.Logger" class's 
 * log(int level,String message) method.
 * Used to log C layer log messages, in conjunction with the logger's object reference logger.
 * @see #logger
 */
static jmethodID log_method_id = NULL;

/* internal routines */
static void NGATAstro_Throw_Exception_String(JNIEnv *env,char *function_name,char *error_string);
static void NGATAstro_Log_Handler(int level,char *string);

/* ------------------------------------------------------------------------------
** 		External routines
** ------------------------------------------------------------------------------ */
/**
 * This routine gets called when the native library is loaded. We use this routine
 * to get a copy of the JavaVM pointer of the JVM we are running in. This is used to
 * get the correct per-thread JNIEnv context pointer in NGATAstro_Log_Handler.
 * @see #java_vm
 * @see #NGATAstro_Log_Handler
 */
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved)
{
	java_vm = vm;
	return JNI_VERSION_1_2;
}

/**
 * Class:     ngat_astrometry_NGATAstro<br>
 * Method:    initialiseLoggerReference<br>
 * Signature: (Lngat/util/logging/Logger;)V<br>
 * Java Native Interface implementation NGATAstro's initialiseLoggerReference.
 * This takes the supplied logger object reference and stores it in the logger variable as a global reference.
 * The log method ID is also retrieved and stored. Finally, NGAT_Astro_Set_Log_Handler_Function is used to
 * set the ngatastro libraries C layer logger to NGATAstro_Log_Handler, which pushes the log message
 * into the Java logging layer.
 * @param l The NGATAstro's "ngat.astrometry.NGATAstro" logger.
 * @see #NGATAstro_Log_Handler
 * @see #logger
 * @see #log_method_id
 * @see http://ltdevsrv.astro.livjm.ac.uk/~dev/ngatastro/ngat_astro.html#NGAT_Astro_Set_Log_Handler_Function
 */
JNIEXPORT void JNICALL Java_ngat_astrometry_NGATAstro_initialiseLoggerReference(JNIEnv *env,jobject obj,jobject l)
{
	jclass cls = NULL;

/* save logger instance */
	logger = (*env)->NewGlobalRef(env,l);
/* get the ngat.util.logging.Logger class */
	cls = (*env)->FindClass(env,"ngat/util/logging/Logger");
	/* if the class is null, one of the following exceptions occured:
	** ClassFormatError,ClassCircularityError,NoClassDefFoundError,OutOfMemoryError */
	if(cls == NULL)
		return;
/* get relevant method id to call */
/* log(int level,java/lang/String clazz,java/lang/String source,java/lang/String message) */
	log_method_id = (*env)->GetMethodID(env,cls,"log","(ILjava/lang/String;)V");
	if(log_method_id == NULL)
	{
		/* One of the following exceptions has been thrown:
		** NoSuchMethodError, ExceptionInInitializerError, OutOfMemoryError */
		return;
	}
	/* set NGAT Astro C library to log to JNI log method (which calls back into Java
	** log method retrieved above */
	NGAT_Astro_Set_Log_Handler_Function(NGATAstro_Log_Handler);
}

/**
 * Class:     ngat_astrometry_NGATAstro<br>
 * Method:    finaliseLoggerReference<br>
 * Signature: ()V<br>
 * This native method is called from NGATAstro's finaliser method. It removes the global reference to
 * logger.
 * @see #logger
 */
JNIEXPORT void JNICALL Java_ngat_astrometry_NGATAstro_finaliseLoggerReference(JNIEnv *env, jobject obj)
{
	(*env)->DeleteGlobalRef(env,logger);
}

/**
 * Class:     ngat_astrometry_NGATAstro<br>
 * Method:    GetMJD<br>
 * Signature: (J)D<br>
 * Get the Modified Julian Date for the specified timestamp.
 * time_millis is converted into a struct timespec.
 * We then call NGAT_Astro_Timespec_To_MJD to convert the timespec into MJD.
 * If an error occurs NGAT_Astro_Timespec_To_MJD, and we throw an exception based on NGAT_Astro_Error_String.
 * @see http://ltdevsrv.astro.livjm.ac.uk/~dev/ngatastro/ngat_astro_mjd.html#NGAT_Astro_Timespec_To_MJD
 * @see http://ltdevsrv.astro.livjm.ac.uk/~dev/ngatastro/ngat_astro.html#NGAT_Astro_Error_String
 * @see http://ltdevsrv.astro.livjm.ac.uk/~dev/ngatastro/ngat_astro.html#NGAT_ASTRO_ONE_SECOND_MS
 * @see http://ltdevsrv.astro.livjm.ac.uk/~dev/ngatastro/ngat_astro.html#NGAT_ASTRO_ONE_MILLISECOND_NS
 * @see #NGATAstro_Throw_Exception_String
 */
JNIEXPORT jdouble JNICALL Java_ngat_astrometry_NGATAstro_GetMJD(JNIEnv *env, jclass class, jlong time_millis)
{
	struct timespec time;
	char ngat_astro_error_string[1024];
	double mjd;
	int retval;

	/* convert time_millis into a timespec. */
	time.tv_sec = ((long)time_millis) / NGAT_ASTRO_ONE_SECOND_MS;
	time.tv_nsec = (((long)time_millis) % NGAT_ASTRO_ONE_SECOND_MS)*NGAT_ASTRO_ONE_MILLISECOND_NS;
	retval = NGAT_Astro_Timespec_To_MJD(time,0,&mjd);
	if(retval == FALSE)
	{
		NGAT_Astro_Error_String(ngat_astro_error_string);
		NGATAstro_Throw_Exception_String(env,"GetMJD",ngat_astro_error_string);
		return (jdouble)0.0;
	}
	return (jdouble)mjd;
}

/* ------------------------------------------------------------------------------
** 		Internal routines
** ------------------------------------------------------------------------------ */
/**
 * This routine throws an exception of class ngat/astrometry/NGATAstroNativeException.
 * This is used to report all libngatastro error messages back to the Java layer.
 * @param env The JNI environment pointer.
 * @param function_name The name of the function in which this exception is being generated for.
 * @param error_string The string to pass to the constructor of the exception.
 */
static void NGATAstro_Throw_Exception_String(JNIEnv *env,char *function_name,char *error_string)
{
	jclass exception_class = NULL;
	jobject exception_instance = NULL;
	jstring error_jstring = NULL;
	jmethodID mid;
	int retval;

	exception_class = (*env)->FindClass(env,"ngat/astrometry/NGATAstroNativeException");
	if(exception_class != NULL)
	{
	/* get NGATAstroNativeException constructor */
		mid = (*env)->GetMethodID(env,exception_class,"<init>","(Ljava/lang/String;)V");
		if(mid == 0)
		{
			/* One of the following exceptions has been thrown:
			** NoSuchMethodError, ExceptionInInitializerError, OutOfMemoryError */
			fprintf(stderr,"NGATAstro_Throw_Exception_String:GetMethodID failed:%s:%s\n",function_name,
				error_string);
			return;
		}
	/* convert error_string to JString */
		error_jstring = (*env)->NewStringUTF(env,error_string);
	/* call constructor */
		exception_instance = (*env)->NewObject(env,exception_class,mid,error_jstring);
		if(exception_instance == NULL)
		{
			/* One of the following exceptions has been thrown:
			** InstantiationException, OutOfMemoryError */
			fprintf(stderr,"NGATAstro_Throw_Exception_String:NewObject failed %s:%s\n",
				function_name,error_string);
			return;
		}
	/* throw instance */
		retval = (*env)->Throw(env,(jthrowable)exception_instance);
		if(retval !=0)
		{
			fprintf(stderr,"NGATAstro_Throw_Exception_String:Throw failed %d:%s:%s\n",retval,
				function_name,error_string);
		}
	}
	else
	{
		fprintf(stderr,"NGATAstro_Throw_Exception_String:FindClass failed:%s:%s\n",function_name,
			error_string);
	}
}

/**
 * libngatastrojni's Log Handler for the Java layer interface. 
 * This calls the ngat.astrometry.NGATAstro's logger's 
 * log(int level,String message) method with the parameters supplied to this routine.
 * If the logger instance is NULL, or the log_method_id is NULL the call is not made.
 * Otherwise, A java.lang.String instance is constructed from the string parameter,
 * and the JNI CallVoidMEthod routine called to call log().
 * @param level The log level of the message.
 * @param string The message to log.
 * @see #java_vm
 * @see #logger
 * @see #log_method_id
 */
static void NGATAstro_Log_Handler(int level,char *string)
{
	JNIEnv *env = NULL;
	jstring java_string = NULL;

	if(logger == NULL)
	{
		fprintf(stderr,"NGATAstro_Log_Handler:logger was NULL (%d,%s).\n",level,string);
		return;
	}
	if(log_method_id == NULL)
	{
		fprintf(stderr,"NGATAstro_Log_Handler:log_method_id was NULL (%d,%s).\n",level,string);
		return;
	}
	if(java_vm == NULL)
	{
		fprintf(stderr,"NGATAstro_Log_Handler:java_vm was NULL (%d,%s).\n",level,string);
		return;
	}
/* get java env for this thread */
	(*java_vm)->AttachCurrentThread(java_vm,(void**)&env,NULL);
	if(env == NULL)
	{
		fprintf(stderr,"NGATAstro_Log_Handler:env was NULL (%d,%s).\n",level,string);
		return;
	}
	if(string == NULL)
	{
		fprintf(stderr,"NGATAstro_Log_Handler:string (%d) was NULL.\n",level);
		return;
	}
/* convert C to Java String */
	java_string = (*env)->NewStringUTF(env,string);
/* call log method on logger instance */
	(*env)->CallVoidMethod(env,logger,log_method_id,(jint)level,java_string);
}

