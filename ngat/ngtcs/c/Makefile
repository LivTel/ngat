PACKAGENAME  = ngat.ngtcs
PACKAGEDIR   = ngat/ngtcs
PACKAGEPRE   = ngat_ngtcs
JAVA_DIR     = $(LT_SRC_HOME)/$(PACKAGEDIR)
CLASS_DIR    = $(LT_HOME)/classes

SRCS         = jni_astrometry.c slalib_astrometry.c

HEADERS      = $(PACKAGEPRE)_JNIAstrometryCalculator.h jni_astrometry.h \
	       ngtcs_astrometry.h astro_structs.h astro_values.h

LIB          = libJNIAstrometryCalculator.so

INCS         = -I$(LT_SRC_HOME)/astrometry/include \
               -I$(JNIINCDIR) -I$(JNIMDINCDIR)

#DEBUG        = -DDEBUG

FLAGS        = $(CCHECKFLAG) $(CCSHAREDFLAG) $(DEBUG)



top: $(LT_LIB_HOME)/$(LIB)

#
# JNI_ASTROMETRY
#
$(LT_LIB_HOME)/$(LIB): $(SRCS) $(HEADERS)
	$(CC) $(FLAGS) -o $@ -lsla -lm $(SRCS) $(INCS)

$(PACKAGEPRE)_JNIAstrometryCalculator.h: $(CLASS_DIR)/$(PACKAGEDIR)/JNIAstrometryCalculator.class
	javah -jni -classpath $(LT_HOME)/classes $(PACKAGENAME).JNIAstrometryCalculator
#	cd -; mv $(JAVA_DIR)/$(PACKAGEPRE)_JNIAstrometryCalculator.h .
	touch $(PACKAGEPRE)_JNIAstrometryCalculator.h

classesonly: top
