# $Header: /space/home/eng/cjm/cvs/ngat/astrometry/ngatastrometrywcstools/c/Makefile,v 1.3 2012-11-12 17:59:21 cjm Exp $

include ../../../../Makefile.common

PACKAGEDIR 	= ngat/astrometry
PACKAGENAME 	= ngat.astrometry
LIBNAME		= ngatastrometrywcstools

WCSLIBNAME	= wcs
# Use wcstools-3.8.1 in src
# This has modified Makefiles to put libwcs.a in a hosttype specific directory.
WCSLIBINCDIR	= $(LT_SRC_HOME)/wcstools-3.8.1/libwcs/
WCSLIBLINKDIR	= $(LT_LIB_HOME)

INCDIR = ../include
DOCSDIR = $(LT_DOC_HOME)/ngat/cdocs/$(PACKAGEDIR)/ngatastrometrywcstools
ODIR	= $(LT_BIN_HOME)/$(PACKAGEDIR)/ngatastrometrywcstools/$(HOSTTYPE)
CFLAGS = -g $(CCHECKFLAG) -I$(INCDIR) -I$(JNIINCDIR) -I$(JNIMDINCDIR) -I$(WCSLIBINCDIR) \
	-L$(LT_LIB_HOME) -L$(WCSLIBLINKDIR)
LINTFLAGS = -I$(INCDIR) -I$(JNIINCDIR) -I$(JNIMDINCDIR) -I$(CFITSIOINCDIR)
DOCFLAGS = -static
SRCS 		= 	ngat_astrometry_WCSTools.c
HEADERS		=	$(SRCS:%.c=$(INCDIR)/%.h)
OBJS		=	$(SRCS:%.c=$(ODIR)/%.o)
DOCS 		= 	$(SRCS:%.c=$(DOCSDIR)/%.html)

top: shared $(INCDIR)/ngat_astrometry_WCSTools.h docs

shared: $(LT_LIB_HOME)/lib$(LIBNAME).so

$(LT_LIB_HOME)/lib$(LIBNAME).so: $(OBJS)
	$(CC) $(CCSHAREDFLAG) $(CFLAGS) $(OBJS) -o $@ $(TIMELIB) -l$(WCSLIBNAME)

$(ODIR)/%.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

$(INCDIR)/ngat_astrometry_WCSTools.h: $(LT_JAVALIB_HOME)/ngat_astrometry.jar
	$(JAVAH) -classpath $(CLASSPATH):$(LT_JAVALIB_HOME)/ngat_astrometry.jar -jni -d $(INCDIR) ngat.astrometry.WCSTools
	$(TOUCH) $@

docs: $(DOCS)

$(DOCS): $(SRCS)
	-$(CDOC) -d $(DOCSDIR) -h $(INCDIR) $(DOCFLAGS) $(SRCS)

checkout:
	$(CO) $(CO_OPTIONS) $(SRCS)
	#(cd $(INCDIR); $(CO) $(CO_OPTIONS) $(HEADERS);) (machine generated headers only atm)

checkin:
	-$(CI) $(CI_OPTIONS) $(SRCS)
	#-(cd $(INCDIR); $(CI) $(HEADERS);) (machine generated headers only atm)

depend:
	makedepend $(MAKEDEPENDFLAGS) -- $(CFLAGS) -- $(SRCS)

lint:
	$(LINT)	$(LINTFLAGS) $(SRCS)

clean: tidy
	-$(RM) $(RM_OPTIONS) $(OBJS) $(LT_LIB_HOME)/lib$(LIBNAME).so

tidy:
	-$(RM) $(RM_OPTIONS) $(TIDY_OPTIONS)
	cd $(INCDIR); $(RM) $(RM_OPTIONS) $(TIDY_OPTIONS)

backup: tidy
	-$(RM) $(RM_OPTIONS) $(OBJS)

#
# $Log: not supported by cvs2svn $
# Revision 1.2  2008/06/27 17:35:23  cjm
# Added MAKEDEPENDFLAGS.
#
# Revision 1.1  2007/09/27 15:35:14  cjm
# Initial revision
#
#
# DO NOT DELETE

ngat_astrometry_WCSTools.o: /usr/include/stdio.h /usr/include/features.h
ngat_astrometry_WCSTools.o: /usr/include/sys/cdefs.h
ngat_astrometry_WCSTools.o: /usr/include/bits/wordsize.h
ngat_astrometry_WCSTools.o: /usr/include/gnu/stubs.h
ngat_astrometry_WCSTools.o: /usr/include/gnu/stubs-32.h
ngat_astrometry_WCSTools.o: /usr/lib/gcc/i386-redhat-linux/4.1.2/include/stddef.h
ngat_astrometry_WCSTools.o: /usr/include/bits/types.h
ngat_astrometry_WCSTools.o: /usr/include/bits/typesizes.h
ngat_astrometry_WCSTools.o: /usr/include/libio.h /usr/include/_G_config.h
ngat_astrometry_WCSTools.o: /usr/include/wchar.h /usr/include/bits/wchar.h
ngat_astrometry_WCSTools.o: /usr/include/gconv.h
ngat_astrometry_WCSTools.o: /usr/lib/gcc/i386-redhat-linux/4.1.2/include/stdarg.h
ngat_astrometry_WCSTools.o: /usr/include/bits/stdio_lim.h
ngat_astrometry_WCSTools.o: /usr/include/bits/sys_errlist.h
ngat_astrometry_WCSTools.o: /usr/include/stdlib.h /usr/include/string.h
ngat_astrometry_WCSTools.o: /usr/lib/gcc/i386-redhat-linux/4.1.2/include/jni.h
ngat_astrometry_WCSTools.o: /usr/lib/gcc/i386-redhat-linux/4.1.2/include/jni_md.h
ngat_astrometry_WCSTools.o: /usr/lib/gcc/i386-redhat-linux/4.1.2/include/gcj/libgcj-config.h
ngat_astrometry_WCSTools.o: /usr/include/time.h /usr/include/bits/time.h
ngat_astrometry_WCSTools.o: /home/dev/src/wcstools-3.8.1/libwcs/wcs.h
ngat_astrometry_WCSTools.o: /home/dev/src/wcstools-3.8.1/libwcs/wcslib.h
ngat_astrometry_WCSTools.o: /home/dev/src/wcstools-3.8.1/libwcs/fitshead.h
ngat_astrometry_WCSTools.o: /usr/include/sys/types.h
