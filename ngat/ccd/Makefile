# Makefile -*- mode: Fundamental;-*-
# $Header: /space/home/eng/cjm/cvs/ngat/ccd/Makefile,v 1.9 2000-11-21 16:42:18 cjm Exp $ 

include		../../Makefile.common
include		../Makefile.common

CCD_NAME	=ccd
PACKAGEDIR 	=ngat/$(CCD_NAME)
PACKAGENAME	=ngat.$(CCD_NAME)
JAR_FILE	=$(NGAT_HOME)_$(CCD_NAME).jar
LIBDIR		= lib
JAVACFLAGS 	= -d $(LIBDIR) -sourcepath ../.. -classpath $(LIBDIR):$(CLASSPATH)
DOCSDIR 	= $(NGAT_DOC_HOME)/javadocs/$(PACKAGEDIR)
DOCFLAGS 	= -version -author -private

SRCS = CCDLibraryDouble.java CCDLibraryFormatException.java CCDLibraryNativeException.java \
	CCDLibrarySetupWindow.java CCDLibrary.java

OBJS = $(SRCS:%.java=$(LIBDIR)/$(PACKAGEDIR)/%.class)
DOCS = $(SRCS:%.java=$(DOCSDIR)/$(PACKAGEDIR)/%.html)

top: jar docs

$(LIBDIR)/$(PACKAGEDIR)/%.class: %.java
	$(JAVAC) $(JAVAC_OPTIONS) $(JAVACFLAGS) $<

jar: $(JARLIBDIR)/$(JAR_FILE)

$(JARLIBDIR)/$(JAR_FILE): $(OBJS)
	(cd $(LIBDIR); $(JAR) $(JAR_OPTIONS) $(JAR_FILE) $(PACKAGEDIR); $(MV) $(JAR_FILE) $(JARLIBDIR))

docs: $(DOCS)

$(DOCSDIR)/$(PACKAGEDIR)/%.html: %.java
	$(JAVADOC) -sourcepath ../..:$(CLASSPATH) -d $(DOCSDIR) $(DOCFLAGS) $(PACKAGENAME)

checkout:
	$(CO) $(CO_OPTIONS) $(SRCS)

checkin:
	-$(CI) $(CI_OPTIONS) $(SRCS)

clean:
	-$(RM) $(RM_OPTIONS) $(OBJS) $(TIDY_OPTIONS)

tidy:
	-$(RM) $(RM_OPTIONS) $(TIDY_OPTIONS)

backup: tidy
	-$(RM) $(RM_OPTIONS) $(OBJS)

#
# $Log: not supported by cvs2svn $
# Revision 1.8  2000/11/21 14:27:55  cjm
# Changed to create jars per ngat package.
#
# Revision 1.7  2000/02/02 13:34:19  cjm
# Fixed error in Makefile.
#
# Revision 1.6  2000/02/02 13:32:27  cjm
# Added CCDLibrarySetupWindow source file.
#
# Revision 1.5  1999/12/10 14:23:36  cjm
# Added backup target.
#
# Revision 1.4  1999/11/29 10:13:11  cjm
# Changed docs rule to call javadoc only once.
#
# Revision 1.3  1999/11/22 16:14:35  cjm
# Added sourcepath to JAVACFLAGS.
#
# Revision 1.2  1999/09/15 16:10:30  cjm
# Added CCDLibraryNativeException.java source file.
#
# Revision 1.1  1999/09/15 15:41:02  cjm
# Initial revision
#
#

