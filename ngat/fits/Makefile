# Makefile -*- mode: Fundamental;-*-
# $Header: /space/home/eng/cjm/cvs/ngat/fits/Makefile,v 0.5 2001-06-21 11:05:55 cjm Exp $ 

include		../../Makefile.common
include		../Makefile.common

FITS_NAME	=fits
PACKAGEDIR 	=ngat/$(FITS_NAME)
PACKAGENAME 	=ngat.$(FITS_NAME)
JAR_FILE	=$(NGAT_HOME)_$(FITS_NAME).jar
LIBDIR		=lib
JAVACFLAGS 	= -d $(LIBDIR) -sourcepath ../.. -classpath $(LIBDIR):$(CLASSPATH)
DOCSDIR 	= $(NGAT_DOC_HOME)/javadocs/$(PACKAGEDIR)
DOCFLAGS 	= -version -author -private

SRCS = FitsHeader.java FitsHeaderCardImage.java FitsHeaderException.java \
	FitsHeaderCardImageOrderNumberComparator.java FitsHeaderDefaults.java \
	FitsFilename.java

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

depend:
	echo "depend not supported for Java sources"

clean:
	-$(RM) $(RM_OPTIONS) $(OBJS) $(TIDY_OPTIONS)

tidy:
	-$(RM) $(RM_OPTIONS) $(TIDY_OPTIONS)

backup: tidy
	-$(RM) $(RM_OPTIONS) $(OBJS)

#
# $Log: not supported by cvs2svn $
# Revision 0.4  2000/11/21 16:40:24  cjm
# Fixed DOCS target.
#
# Revision 0.3  2000/11/21 14:33:00  cjm
# Changed to create jars per ngat package.
#
# Revision 0.2  2000/10/25 10:16:12  cjm
# Shared C library now a separate package.
#
# Revision 0.1  2000/06/01 10:46:35  cjm
# initial revision.
#
#

