# Makefile -*- mode: Fundamental;-*-
# $Header: /space/home/eng/cjm/cvs/ngat/math/test/Makefile,v 0.1 2000-09-01 15:31:21 cjm Exp $ 

include ../../../Makefile.common
include ../../Makefile.common

PACKAGE_DIR	= math/test
BINDIR 		= $(NGAT_BIN_HOME)/$(PACKAGE_DIR)
DOCSDIR 	= $(NGAT_DOC_HOME)/javadocs/ngat/$(PACKAGE_DIR)
DOCFLAGS 	= -version -author -private
SRCS 		= QuadraticFitTest.java QuadraticFitTest2.java
OBJS 		= $(SRCS:%.java=$(BINDIR)/%.class)
DOCS 		= $(SRCS:%.java=$(DOCSDIR)/%.html)

# We need ngat.OSS.utility.* for the graph frames.
CLASSPATH_OPTIONS = -classpath ".:$(CLASSPATH):/space/home/snf/BETA/src"

top: $(OBJS) docs

$(BINDIR)/%.class:%.java
	$(JAVAC) $(JAVAC_OPTIONS) $(CLASSPATH_OPTIONS) -d $(BINDIR) $<

docs: $(DOCS)

$(DOCS): $(SRCS)
	$(JAVADOC) $(CLASSPATH_OPTIONS) -d $(DOCSDIR) $(DOCFLAGS) $(SRCS)

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
