include		$(LT_SRC_HOME)/ngat/Makefile.ngat

PACKAGE_DIR     = ngat/net/cil/test
BINDIR 		= $(LT_HOME)/classes/$(PACKAGE_DIR)
DOCSDIR 	= $(LT_DOC_HOME)/ngat/javadocs/$(PACKAGE_DIR)
DOCFLAGS 	= -version -author -private

SRCS = TestServer.java
OBJS = $(SRCS:%.java=$(BINDIR)/%.class)
DOCS = $(SRCS:%.java=$(DOCSDIR)/%.html)

top: $(OBJS) docs

$(BINDIR)/%.class: %.java
	$(JAVAC) $(JAVAC_OPTIONS) -d $(BINDIR)/ $<

docs: $(DOCS)

$(DOCSDIR)/%.html: %.java
	mkdir -p $(DOCSDIR);
	$(JAVADOC) -d $(DOCSDIR) -classpath $(LT_HOME)/classes $(DOCFLAGS) $(SRCS)

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
# Revision 1.2  2002/11/15 11:41:12  cjm
# Changed Makefile.common to Makefile.ngat
#
# Revision 1.1  2002/11/15 11:40:15  cjm
# Initial revision
#
# Revision 0.1  2000/10/16 17:39:24  cjm
# initial revision.
#
#
