# Makefile -*- mode: Fundamental;-*-
# $Header: /space/home/eng/cjm/cvs/ngat/fits/Makefile,v 0.1 2000-06-01 10:46:35 cjm Exp $ 

include		../../Makefile.common
include		../Makefile.common

PACKAGEDIR = ngat/fits
PACKAGENAME = ngat.fits
DIRS = c
JAVACFLAGS = -d $(LIBDIR) -sourcepath ../.. -classpath $(LIBDIR):$(CLASSPATH)
DOCSDIR = $(NGAT_DOC_HOME)/javadocs/$(PACKAGEDIR)
DOCFLAGS = -version -author -private

SRCS = FitsHeader.java FitsHeaderCardImage.java FitsHeaderException.java \
FitsHeaderCardImageOrderNumberComparator.java FitsHeaderDefaults.java

OBJS = $(SRCS:%.java=$(LIBDIR)/$(PACKAGEDIR)/%.class)
DOCS = $(SRCS:%.java=$(DOCSDIR)/$(PACKAGENAME).%.html)

top: $(OBJS) docs
	@for i in $(DIRS); \
	do \
		(echo making in $$i...; cd $$i; $(MAKE) ); \
	done; \

$(LIBDIR)/$(PACKAGEDIR)/%.class: %.java
	$(JAVAC) $(JAVAC_OPTIONS) $(JAVACFLAGS) $<

docs:
	$(JAVADOC) -sourcepath ../..:$(CLASSPATH) -d $(DOCSDIR) $(DOCFLAGS) $(PACKAGENAME)

checkout:
	$(CO) $(CO_OPTIONS) $(SRCS)
	@for i in $(DIRS); \
	do \
		(echo checkout in $$i...; cd $$i; $(CO) $(CO_OPTIONS) Makefile; $(MAKE) checkout); \
	done;

checkin:
	-$(CI) $(CI_OPTIONS) $(SRCS)
	-@for i in $(DIRS); \
	do \
		(echo checkin in $$i...; cd $$i; $(MAKE) checkin; $(CI) $(CI_OPTIONS) Makefile); \
	done;

depend:
	(echo depend in c...; cd c; $(MAKE) depend);

clean:
	-$(RM) $(RM_OPTIONS) $(OBJS) $(TIDY_OPTIONS)
	@for i in $(DIRS); \
	do \
		(echo cleaning in $$i...; cd $$i; $(MAKE) clean); \
	done;

tidy:
	-$(RM) $(RM_OPTIONS) $(TIDY_OPTIONS)
	@for i in $(DIRS); \
	do \
		(echo cleaning in $$i...; cd $$i; $(MAKE) tidy); \
	done;

backup: tidy
	-$(RM) $(RM_OPTIONS) $(OBJS)
	@for i in $(DIRS); \
	do \
		(echo backup in $$i...; cd $$i; $(MAKE) backup); \
	done;

#
# $Log: not supported by cvs2svn $
#

