/* ngat_astrometry_WCSTools.c
** Implementation of Java Class ngat.astrometry.WCSTools  native interfaces
** $Header: /space/home/eng/cjm/cvs/ngat/astrometry/ngatastrometrywcstools/c/ngat_astrometry_WCSTools.c,v 1.2 2007-08-13 10:42:26 cjm Exp $
*/
/**
 * ngat_astrometry_WCSTools.c is the 'glue' between Doug Mink's libwcs.a,and ngat.astrometry.WCSTools.
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
#include "wcs.h"

/* why this isn't declared in wcs.h I don't know */
extern struct WorldCoor *GetWCSFITS(char *filename,int verbosity);	/* Read WCS from FITS or IRAF file */

/* hash definitions */
/**
 * Hash define for the size of the array holding WCSToolsWorldCoorHandle Instance (jobject) maps 
 * to 'struct WorldCoor*'.
 * Set to 5.
 */
#define HANDLE_MAP_SIZE         (5)

#ifndef TRUE
#define TRUE (1)
#endif
#ifndef FALSE
#define FALSE (0)
#endif

/* internal structures */
/**
 * Handle map mapping between Java objects and instances of the 'struct WorldCoor*' libwcs handle.
 * <dl>
 * <dt>Java_Handle</dt><dd>The jobject reference for the WCSToolsWorldCoorHandle handle instance.</b></dd>
 * <dt>LibWCS_Handle</dt><dd>The 'struct WorldCoor' pointer for the opened FITS file.</b></dd>
 * </dl>
 */
struct Handle_Map_Struct
{
	jobject Java_Handle;
	struct WorldCoor* LibWCS_Handle;
};


/* internal variables */
/**
 * Revision Control System identifier.
 */
static char rcsid[] = "$Id$";

/**
 * Internal list of maps between WCSToolsWorldCoorHandle jobject's (i.e. Java references), and
 * 'struct WorldCoor*' handles (libwcs).
 */
static struct Handle_Map_Struct Handle_Map_List[HANDLE_MAP_SIZE] = 
{
	{NULL,NULL},
	{NULL,NULL},
	{NULL,NULL},
	{NULL,NULL},
	{NULL,NULL}
};

/* internal routines */
static jobject WCSTools_Create_WorldCoor_Handle(JNIEnv *env);
static jobject WCSTools_Create_WCSToolsCoordinate(JNIEnv *env,double x,double y);
static void WCSTools_Throw_Exception_String(JNIEnv *env,char *function_name,char *error_string);
static int WCSTools_Handle_Map_Add(JNIEnv *env,jobject java_handle,struct WorldCoor* libwcs_handle);
static int WCSTools_Handle_Map_Delete(JNIEnv *env,jobject java_handle);
static int WCSTools_Handle_Map_Find(JNIEnv *env,jobject java_handle,struct WorldCoor** libwcs_handle);

/* ------------------------------------------------------------------------------
** 		External routines
** ------------------------------------------------------------------------------ */
/**
 * Class:     ngat_astrometry_WCSTools<br>
 * Method:    GetWCSFITS<br>
 * Signature: (Ljava/lang/String;I)Lngat/astrometry/WCSToolsWorldCoorHandle;<br>
 * @see #WCSTools_Create_WorldCoor_Handle
 * @see #WCSTools_Handle_Map_Add
 */
JNIEXPORT jobject JNICALL Java_ngat_astrometry_WCSTools_GetWCSFITS(JNIEnv *env,jclass class,
								   jstring jfilename,jint verbosity)
{
	jobject handle = NULL;
	struct WorldCoor *wcs = NULL;
	const char *cfilename = NULL;

	/* Get the filename froma java string to a c null terminated string
	** If the java String is null the cfilename should be null as well */
	if(jfilename != NULL)
		cfilename = (*env)->GetStringUTFChars(env,jfilename,0);
	/* create wcs pointer */
	wcs = GetWCSFITS((char*)cfilename,(int)verbosity);
	/* If we created the cfilename string we need to free the memory it uses */
	if(jfilename != NULL)
		(*env)->ReleaseStringUTFChars(env,jfilename,cfilename);
	/* check wcs exists and contains a wcs */
	if(wcs == NULL)
	{
		WCSTools_Throw_Exception_String(env,"GetWCSFITS","wcs returned NULL.");
		return NULL;
	}
	if(nowcs(wcs))
	{
		WCSTools_Throw_Exception_String(env,"GetWCSFITS","no wcs found in filename.");
		return NULL;
	}
	if(wcs->sysout != WCS_J2000)
	{
		WCSTools_Throw_Exception_String(env,"GetWCSFITS","Wrong sysout found in filename.");
		return NULL;
	}
	/* initialise wcs output for pix2wcs*/
	wcsoutinit(wcs, "J2000");
	/* initialise wcs input for wcs2pix */
	wcsininit(wcs,"J2000");
	/* create new instance of WCSToolsWorldCoorHandle */
	handle = WCSTools_Create_WorldCoor_Handle(env);
	if(handle == NULL)
	{
		/* exception should already have been thrown */
		return NULL;
	}
	/* associate handle with wcs */
	if(!WCSTools_Handle_Map_Add(env,handle,wcs))
	{
		/* exception should already have been thrown */
		return NULL;
	}
	/* return WCSToolsWorldCoorHandle instance handle */
	return handle;
}

/**
 * Class:     ngat_astrometry_WCSTools<br>
 * Method:    wcsfree<br>
 * Signature: (Lngat/astrometry/WCSToolsWorldCoorHandle;)V<br>
 * @see #WCSTools_Handle_Map_Find
 * @see #WCSTools_Handle_Map_Delete
 */
JNIEXPORT void JNICALL Java_ngat_astrometry_WCSTools_wcsfree(JNIEnv *env, jclass class, jobject handle)
{
	struct WorldCoor *wcs = NULL;

	/* find libwcs handle from WCSToolsWorldCoorHandle handle */
	if(!WCSTools_Handle_Map_Find(env,handle,&wcs))
	{
		/* throws an exception on error */
		return;
	}
	/* delete handle map */
	if(!WCSTools_Handle_Map_Delete(env,handle))
	{
		wcsfree(wcs);
		/* throws an exception on error */
		return;
	}
	/* free libwcs structure */
	wcsfree(wcs);
}

/**
 * Class:     ngat_astrometry_WCSTools<br>
 * Method:    pix2wcs<br>
 * Signature: (Lngat/astrometry/WCSToolsWorldCoorHandle;DD)Lngat/astrometry/WCSToolsCoordinate;<br>
 * @see #WCSTools_Handle_Map_Find
 * @see #WCSTools_Create_WCSToolsCoordinate
 */
JNIEXPORT jobject JNICALL Java_ngat_astrometry_WCSTools_pix2wcs(JNIEnv *env, jclass class, jobject handle, 
								jdouble xpix, jdouble ypix)
{
	struct WorldCoor *wcs = NULL;
	jobject coordinate = NULL;
	double xpos,ypos;

	/* find libwcs handle from WCSToolsWorldCoorHandle handle */
	if(!WCSTools_Handle_Map_Find(env,handle,&wcs))
	{
		/* throws an exception on error */
		return NULL;
	}
	/* do wcslib conversion */
	pix2wcs(wcs,(double)xpix,(double)ypix,&xpos,&ypos);
	/* create return object */
	coordinate = WCSTools_Create_WCSToolsCoordinate(env,xpos,ypos);
	if(coordinate == NULL)
	{
		/* throws an exception on error */
		return NULL;
	}
	/* return return coordinates */
	return coordinate;
}

/**
 * Class:     ngat_astrometry_WCSTools<br>
 * Method:    wcs2pix<br>
 * Signature: (Lngat/astrometry/WCSToolsWorldCoorHandle;DD)Lngat/astrometry/WCSToolsCoordinate;<br>
 * @param xpos The X position, world coordinates in decimal degrees.
 * @param ypos The Y position, world coordinates in decimal degrees.
 * @see #WCSTools_Handle_Map_Find
 * @see #WCSTools_Create_WCSToolsCoordinate
 */
JNIEXPORT jobject JNICALL Java_ngat_astrometry_WCSTools_wcs2pix(JNIEnv *env, jclass class, jobject handle, 
								jdouble xpos, jdouble ypos)
{
	struct WorldCoor *wcs = NULL;
	jobject coordinate = NULL;
	char error_string[256];
	double xpix,ypix;
	int offscl;

	/* find libwcs handle from WCSToolsWorldCoorHandle handle */
	if(!WCSTools_Handle_Map_Find(env,handle,&wcs))
	{
		/* throws an exception on error */
		return NULL;
	}
	/* do wcslib conversion
	** V3.6.8 adds offscl parameter. */
	wcs2pix(wcs,(double)xpos,(double)ypos,&xpix,&ypix,&offscl);
	if(offscl != 0)
	{
		sprintf(error_string,"Position RA %.2f degs, Dec %.2f degs was off bounds (offscl=%d).",
			xpos,ypos,offscl);
		WCSTools_Throw_Exception_String(env,"wcs2pix",error_string);
		return NULL;
	}
	/* create return object with pixel x,y pos */
	coordinate = WCSTools_Create_WCSToolsCoordinate(env,xpix,ypix);
	if(coordinate == NULL)
	{
		/* throws an exception on error */
		return NULL;
	}
	/* return return coordinates */
	return coordinate;
}

/* ------------------------------------------------------------------------------
** 		Internal routines
** ------------------------------------------------------------------------------ */
/**
 * This routine creates a new WCSToolsWorldCoorHandle java object.
 * @param env The JNI environment pointer.
 * @return A jobject, this should be an instance of ngat.astrometry.WCSToolsWorldCoorHandle.
 */
static jobject WCSTools_Create_WorldCoor_Handle(JNIEnv *env)
{
	jclass worldcoor_class = NULL;
	jobject worldcoor_instance = NULL;
	jmethodID mid;

	worldcoor_class = (*env)->FindClass(env,"ngat/astrometry/WCSToolsWorldCoorHandle");
	if(worldcoor_class == NULL)
	{
		/* One of the following exceptions has been thrown:
		** ClassFormatError: if the class data does not specify a valid class.
		** ClassCircularityError: if a class or interface would be its own superclass or superinterface.
		** NoClassDefFoundError: if no definition for a requested class or interface can be found.
		* OutOfMemoryError: if the system runs out of memory. */
		fprintf(stderr,"WCSTools_Create_WorldCoor_Handle:FindClass failed.\n");
		return NULL;
	}
	/* get WCSToolsWorldCoorHandle constructor */
	mid = (*env)->GetMethodID(env,worldcoor_class,"<init>","()V");
	if(mid == 0)
	{
		/* One of the following exceptions has been thrown:
		** NoSuchMethodError, ExceptionInInitializerError, OutOfMemoryError */
		fprintf(stderr,"WCSTools_Create_WorldCoor_Handle:GetMethodID failed.\n");
		return NULL;
	}
	/* call constructor */
	worldcoor_instance = (*env)->NewObject(env,worldcoor_class,mid);
	if(worldcoor_instance == NULL)
	{
		/* One of the following exceptions has been thrown:
		** InstantiationException, OutOfMemoryError */
		fprintf(stderr,"WCSTools_Create_WorldCoor_Handle:NewObject failed.\n");
		return NULL;
	}
	return worldcoor_instance;
}

/**
 * This routine creates a new WCSToolsCoordinate java object.
 * @param env The JNI environment pointer.
 * @param x The value of the x coordinate.
 * @param y The value of the y coordinate.
 * @return A jobject, this should be an instance of ngat.astrometry.WCSToolsCoordinate.
 */
static jobject WCSTools_Create_WCSToolsCoordinate(JNIEnv *env,double x,double y)
{
	jclass coordinate_class = NULL;
	jobject coordinate_instance = NULL;
	jmethodID mid;

	coordinate_class = (*env)->FindClass(env,"ngat/astrometry/WCSToolsCoordinate");
	if(coordinate_class == NULL)
	{
		/* One of the following exceptions has been thrown:
		** ClassFormatError: if the class data does not specify a valid class.
		** ClassCircularityError: if a class or interface would be its own superclass or superinterface.
		** NoClassDefFoundError: if no definition for a requested class or interface can be found.
		* OutOfMemoryError: if the system runs out of memory. */
		fprintf(stderr,"WCSTools_Create_WCSToolsCoordinate:FindClass failed.\n");
		return NULL;
	}
	/* get WCSToolsCoordinate constructor */
	mid = (*env)->GetMethodID(env,coordinate_class,"<init>","(DD)V");
	if(mid == 0)
	{
		/* One of the following exceptions has been thrown:
		** NoSuchMethodError, ExceptionInInitializerError, OutOfMemoryError */
		fprintf(stderr,"WCSTools_Create_WCSToolsCoordinate:GetMethodID failed.\n");
		return NULL;
	}
	/* call constructor */
	coordinate_instance = (*env)->NewObject(env,coordinate_class,mid,x,y);
	if(coordinate_instance == NULL)
	{
		/* One of the following exceptions has been thrown:
		** InstantiationException, OutOfMemoryError */
		fprintf(stderr,"WCSTools_Create_WCSToolsCoordinate:NewObject failed.\n");
		return NULL;
	}
	return coordinate_instance;
}

/**
 * This routine throws an exception of class ngat/astrometry/WCSToolsNativeException.
 * This is used to report all libwcs error messages back to the Java layer.
 * @param env The JNI environment pointer.
 * @param function_name The name of the function in which this exception is being generated for.
 * @param error_string The string to pass to the constructor of the exception.
 */
static void WCSTools_Throw_Exception_String(JNIEnv *env,char *function_name,char *error_string)
{
	jclass exception_class = NULL;
	jobject exception_instance = NULL;
	jstring error_jstring = NULL;
	jmethodID mid;
	int retval;

	exception_class = (*env)->FindClass(env,"ngat/astrometry/WCSToolsNativeException");
	if(exception_class != NULL)
	{
	/* get WCSToolsNativeException constructor */
		mid = (*env)->GetMethodID(env,exception_class,"<init>","(Ljava/lang/String;)V");
		if(mid == 0)
		{
			/* One of the following exceptions has been thrown:
			** NoSuchMethodError, ExceptionInInitializerError, OutOfMemoryError */
			fprintf(stderr,"WCSTools_Throw_Exception_String:GetMethodID failed:%s:%s\n",function_name,
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
			fprintf(stderr,"WCSTools_Throw_Exception_String:NewObject failed %s:%s\n",
				function_name,error_string);
			return;
		}
	/* throw instance */
		retval = (*env)->Throw(env,(jthrowable)exception_instance);
		if(retval !=0)
		{
			fprintf(stderr,"WCSTools_Throw_Exception_String:Throw failed %d:%s:%s\n",retval,
				function_name,error_string);
		}
	}
	else
	{
		fprintf(stderr,"WCSTools_Throw_Exception_String:FindClass failed:%s:%s\n",function_name,
			error_string);
	}
}


/**
 * Routine to add a mapping from the WCSToolsWorldCoorHandle instance to the opened libwcs struct WorldCoor* pointer,
 * in the Handle_Map_List.
 * @param env The JNI environment.
 * @param java_handle The WCSToolsWorldCoorHandle instance.
 * @param libwcs_handle The 'struct WorldCoor*' libwcs handle.
 * @return The routine returns TRUE if the map is added (or updated), FALSE if there was no room left
 *         in the mapping list. 
 *         WCSTools_Throw_Exception_String is used to throw a Java exception if the routine returns FALSE.
 * @see #HANDLE_MAP_SIZE
 * @see #Handle_Map_List
 * @see #WCSTools_Throw_Exception_String
 */
static int WCSTools_Handle_Map_Add(JNIEnv *env,jobject java_handle,struct WorldCoor* libwcs_handle)
{
	int i,done;
	jobject global_java_handle = NULL;

	/* does the map already exist? */
	i = 0;
	done = FALSE;
	while((i < HANDLE_MAP_SIZE)&&(done == FALSE))
	{
		if((*env)->IsSameObject(env,Handle_Map_List[i].Java_Handle,java_handle))
			done = TRUE;
		else
			i++;
	}
	if(done == TRUE)/* found an existing libwcs handle for this WCSToolsWorldCoorHandle instance */
	{
		/* update handle */
		Handle_Map_List[i].LibWCS_Handle = libwcs_handle;
	}
	else
	{
		/* look for a blank index to put the map */
		i = 0;
		done = FALSE;
		while((i < HANDLE_MAP_SIZE)&&(done == FALSE))
		{
			if(Handle_Map_List[i].Java_Handle == NULL)
				done = TRUE;
			else
				i++;
		}
		if(done == FALSE)
		{
			WCSTools_Throw_Exception_String(env,"WCSTools_Handle_Map_Add",
							  "No empty slots in handle map.");
			return FALSE;
		}
		/* index i is free, add handle map here */
		global_java_handle = (*env)->NewGlobalRef(env,java_handle);
		if(global_java_handle == NULL)
		{
			WCSTools_Throw_Exception_String(env,"WCSTools_Handle_Map_Add",
							  "Failed to create Global reference of java handle.");
			return FALSE;
		}
		fprintf(stdout,"WCSTools_Handle_Map_Add:"
			"Adding java handle %p with WorldCoor handle %p at map index %d.\n",
			(void*)global_java_handle,(void*)libwcs_handle,i);
		Handle_Map_List[i].Java_Handle = global_java_handle;
		Handle_Map_List[i].LibWCS_Handle = libwcs_handle;
	}
	return TRUE;
}

/**
 * Routine to delete a mapping from the WCSToolsWorldCoorHandle instance instance to the opened libwcs handle,
 * in the Handle_Map_List.
 * @param java_handle The WCSToolsWorldCoorHandle instance to remove from the list.
 * @return The routine returns TRUE if the map is deleted (or updated), FALSE if the mapping could not be found
 *         in the mapping list.
 *         WCSTools_Throw_Exception_String is used to throw a Java exception if the routine returns FALSE.
 * @see #HANDLE_MAP_SIZE
 * @see #Handle_Map_List
 * @see #WCSTools_Throw_Exception_String
 */
static int WCSTools_Handle_Map_Delete(JNIEnv *env,jobject java_handle)
{
	int i,done;

  	/* does the map already exist? */
	i = 0;
	done = FALSE;
	while((i < HANDLE_MAP_SIZE)&&(done == FALSE))
	{
		if((*env)->IsSameObject(env,Handle_Map_List[i].Java_Handle,java_handle))
			done = TRUE;
		else
			i++;
	}
	if(done == FALSE)
	{
		WCSTools_Throw_Exception_String(env,"WCSTools_Handle_Map_Delete",
						  "Failed to find WCSToolsWorldCoorHandle instance in handle map.");
		return FALSE;
	}
	/* found an existing interface handle for this WCSToolsWorldCoorHandle instance at index i */
	/* delete this map at index i */
	fprintf(stdout,"WCSTools_Handle_Map_Delete:Deleting Java handle %p with libwcs handle %p at map index %d.\n",
		(void*)Handle_Map_List[i].Java_Handle,(void*)Handle_Map_List[i].LibWCS_Handle,i);
	(*env)->DeleteGlobalRef(env,Handle_Map_List[i].Java_Handle);
	Handle_Map_List[i].Java_Handle = NULL;
	Handle_Map_List[i].LibWCS_Handle = NULL;
	return TRUE;
}

/**
 * Routine to find a mapping from the WCSToolsWorldCoorHandle instance instance to the opened libwcs
 * handle, in the Handle_Map_List.
 * @param java_handle The WCSToolsWorldCoorHandle instance.
 * @param libwcs_handle The address of an libwcs handle, to fill with the libwcs handle for
 *        this WCSToolsWorldCoorHandle instance, if one is successfully found.
 * @return The routine returns TRUE if the mapping is found and returned,, FALSE if there was no mapping
 *         for this WCSToolsWorldCoorHandle instance, or the libwcs_handle pointer was NULL.
 *         WCSTools_Throw_Exception_String is used to throw a Java exception if the routine returns FALSE.
 * @see #HANDLE_MAP_SIZE
 * @see #Handle_Map_List
 * @see #WCSTools_Throw_Exception_String
 */
static int WCSTools_Handle_Map_Find(JNIEnv *env,jobject java_handle,struct WorldCoor** libwcs_handle)
{
	int i,done;

	if(java_handle == NULL)
	{
		WCSTools_Throw_Exception_String(env,"WCSTools_Handle_Map_Find","libwcs handle was NULL.");
		return FALSE;
	}
	i = 0;
	done = FALSE;
	while((i < HANDLE_MAP_SIZE)&&(done == FALSE))
	{
		if((*env)->IsSameObject(env,Handle_Map_List[i].Java_Handle,java_handle))
			done = TRUE;
		else
			i++;
	}
	if(done == FALSE)
	{
		fprintf(stdout,"WCSTools_Handle_Map_Find:Failed to find Java handle %p.\n",(void*)java_handle);
		WCSTools_Throw_Exception_String(env,"WCSTools_Handle_Map_Find","WCSTools Java handle was not found.");
		return FALSE;
	}
	(*libwcs_handle) = Handle_Map_List[i].LibWCS_Handle;
	return TRUE;
}
/*
** $Log: not supported by cvs2svn $
** Revision 1.1  2007/08/10 11:02:21  cjm
** Initial revision
**
*/
