# Makefile -*- mode: Fundamental;-*-
# $Header: /space/home/eng/cjm/cvs/ngat/util/Makefile,v 1.10 2000-09-06 13:16:22 cjm Exp $ 

include		../../Makefile.common
include		../Makefile.common

PACKAGEDIR = ngat/util
PACKAGENAME = ngat.util
JAVACFLAGS = -d $(LIBDIR) -sourcepath ../..
DOCSDIR = $(NGAT_DOC_HOME)/javadocs/$(PACKAGEDIR)
DOCFLAGS = -version -author -private
SRCS = ThreadMonitor.java ThreadMonitorFrame.java CircularListNode.java CircularList.java CPUScheduler.java \
ExecuteCommand.java StringUtilites.java NGATProperties.java NGATPropertyException.java
OBJS = $(SRCS:%.java=$(LIBDIR)/$(PACKAGEDIR)/%.class)
DOCS = $(SRCS:%.java=$(DOCSDIR)/$(PACKAGENAME).%.html)

top: $(OBJS) docs

$(LIBDIR)/$(PACKAGEDIR)/%.class: %.java
	$(JAVAC) $(JAVAC_OPTIONS) $(JAVACFLAGS) $<

#docs: $(DOCS)

#$(DOCS): $(SRCS)
docs:
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

# $Log: not supported by cvs2svn $
# Revision 1.9  2000/02/15 13:41:15  cjm
# Added ExecuteCommand.java source file.
#
# Revision 1.8  1999/12/10 14:28:12  cjm
# Added backup target.
#
# Revision 1.7  1999/11/29 10:13:21  cjm
# Changed docs rule to call javadoc only once.
#
# Revision 1.6  1999/09/08 14:32:43  cjm
# Changed checkin target to accept failure(due to files not being checked out locked).
# Changed checkin target to use CI_OPTIONS.
#
# Revision 1.5  1999/08/26 13:20:14  cjm
# Changed to support new directory structure on ltccd1.
#
# Revision 1.4  1999/06/24 12:46:00  dev
# "Backup"
#
# Revision 1.3  1999/04/27 11:26:23  dev
# Backup
#
# Revision 1.2  1999/03/25 14:01:31  dev
# Backup
#
# Revision 1.1  1999/03/19 16:22:33  dev
# Backup

