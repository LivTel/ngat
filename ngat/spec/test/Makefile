# Makefile -*- mode: Fundamental;-*-
# $Header: /space/home/eng/cjm/cvs/ngat/spec/test/Makefile,v 0.1 2000-10-16 17:39:24 cjm Exp $ 

include		../../../Makefile.common
include		../../Makefile.common

SPEC_NAME 	= spec
PACKAGE_DIR	= $(SPEC_NAME)/test
BINDIR 		= $(NGAT_BIN_HOME)/$(PACKAGE_DIR)
DOCSDIR 	= $(NGAT_DOC_HOME)/javadocs/ngat/$(PACKAGE_DIR)
DOCFLAGS 	= -version -author -private

SRCS = TestSpectrographFilter.java 
OBJS = $(SRCS:%.java=$(BINDIR)/%.class)
DOCS = $(SRCS:%.java=$(DOCSDIR)/%.html)

top: $(OBJS) docs

$(BINDIR)/%.class: %.java
	$(JAVAC) $(JAVAC_OPTIONS) -d $(BINDIR) $<

docs: $(DOCS)

$(DOCSDIR)/%.html: %.java
	$(JAVADOC) -d $(DOCSDIR) $(DOCFLAGS) $(SRCS)

checkout:
	$(CO) $(CO_OPTIONS) $(SRCS)

checkin:
	-$(CI) $(CI_OPTIONS) $(SRCS)

clean: tidy
	-$(RM) $(RM_OPTIONS) $(OBJS)

tidy:
	-$(RM) $(RM_OPTIONS) $(TIDY_OPTIONS)

backup: tidy

#
# $Log: not supported by cvs2svn $
#
