# Makefile -*- mode: Fundamental;-*-
# $Header: /space/home/eng/cjm/cvs/ngat/ccd/Makefile,v 1.2 1999-09-15 16:10:30 cjm Exp $ 

include		../../Makefile.common
include		../Makefile.common

PACKAGEDIR = ngat/ccd
PACKAGENAME = ngat.ccd
JAVACFLAGS = -d $(LIBDIR) -classpath $(LIBDIR):$(CLASSPATH)
DOCSDIR = $(NGAT_DOC_HOME)/javadocs/$(PACKAGEDIR)
DOCFLAGS = -version -author -private
SRCS = CCDLibraryDouble.java CCDLibraryFormatException.java CCDLibraryNativeException.java CCDLibrary.java
OBJS = $(SRCS:%.java=$(LIBDIR)/$(PACKAGEDIR)/%.class)
DOCS = $(SRCS:%.java=$(DOCSDIR)/$(PACKAGENAME).%.html)

top: $(OBJS) docs

$(LIBDIR)/$(PACKAGEDIR)/%.class: %.java
	$(JAVAC) $(JAVAC_OPTIONS) $(JAVACFLAGS) $<

docs: $(DOCS)

$(DOCS): $(SRCS)
	$(JAVADOC) -sourcepath ../..:$(CLASSPATH) -d $(DOCSDIR) $(DOCFLAGS) $(PACKAGENAME)

checkout:
	$(CO) $(CO_OPTIONS) $(SRCS)

checkin:
	-$(CI) $(CI_OPTIONS) $(SRCS)

clean:
	$(RM) $(RM_OPTIONS) $(OBJS) $(TIDY_OPTIONS)

tidy:
	$(RM) $(RM_OPTIONS) $(TIDY_OPTIONS)

#
# $Log: not supported by cvs2svn $
# Revision 1.1  1999/09/15 15:41:02  cjm
# Initial revision
#
#

