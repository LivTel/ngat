# $Header$

include ../../../../Makefile.common

PACKAGEDIR 		= ngat/astrometry
PACKAGENAME 		= ngat.astrometry
LIBNAME			= ngatastrojni

NGATASTROLIBNAME	= ngatastro

NGATASTROLIBINCDIR	= $(LT_SRC_HOME)/ngatastro/include/
NGATASTROLIBLINKDIR	= $(LT_LIB_HOME)

INCDIR 		= ../include
DOCSDIR 	= $(LT_DOC_HOME)/ngat/cdocs/$(PACKAGEDIR)/ngatastrojni
ODIR		= $(LT_BIN_HOME)/$(PACKAGEDIR)/ngatastrojni/$(HOSTTYPE)
CFLAGS 		= -g $(CCHECKFLAG) $(SHARED_LIB_CFLAGS) -I$(INCDIR) -I$(JNIINCDIR) -I$(JNIMDINCDIR) \
		-I$(NGATASTROLIBINCDIR) -L$(LT_LIB_HOME) -L$(NGATASTROLIBLINKDIR)
LINTFLAGS 	= -I$(INCDIR) -I$(JNIINCDIR) -I$(JNIMDINCDIR) -I$(NGATASTROLIBINCDIR)
DOCFLAGS 	= -static
SRCS 		= 	ngat_astrometry_NGATAstro.c
HEADERS		=	$(SRCS:%.c=$(INCDIR)/%.h)
OBJS		=	$(SRCS:%.c=$(ODIR)/%.o)
DOCS 		= 	$(SRCS:%.c=$(DOCSDIR)/%.html)

top: $(INCDIR)/ngat_astrometry_NGATAstro.h shared docs

shared: $(LT_LIB_HOME)/lib$(LIBNAME).so

$(LT_LIB_HOME)/lib$(LIBNAME).so: $(OBJS)
	$(CC) $(CCSHAREDFLAG) $(CFLAGS) $(OBJS) -o $@ $(TIMELIB) -l$(NGATASTROLIBNAME)

$(ODIR)/%.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

$(INCDIR)/ngat_astrometry_NGATAstro.h: $(LT_JAVALIB_HOME)/ngat_astrometry.jar
	$(JAVAH) -classpath $(CLASSPATH):$(LT_JAVALIB_HOME)/ngat_astrometry.jar -jni -d $(INCDIR) ngat.astrometry.NGATAstro
	$(TOUCH) $@

docs: $(DOCS)

$(DOCS): $(SRCS)
	-$(CDOC) -d $(DOCSDIR) -h $(INCDIR) $(DOCFLAGS) $(SRCS)

checkout:
	$(CO) $(CO_OPTIONS) $(SRCS)

checkin:
	-$(CI) $(CI_OPTIONS) $(SRCS)

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

