# Makefile -*- mode: Fundamental;-*-
# $Header: /space/home/eng/cjm/cvs/ngat/util/Makefile,v 1.3 1999-04-27 11:26:23 dev Exp $ 

include		../../Makefile.common
include		../Makefile.common

PACKAGEDIR = ngat/util
PACKAGENAME = ngat.util
JAVACFLAGS = -d $(LIBDIR)
DOCSDIR = ../javadocs/$(PACKAGEDIR)
DOCFLAGS = -version -author -private
SRCS = ThreadMonitor.java ThreadMonitorFrame.java
OBJS = $(SRCS:%.java=$(LIBDIR)/$(PACKAGEDIR)/%.class)
DOCS = $(SRCS:%.java=$(DOCSDIR)/$(PACKAGENAME).%.html)

top: $(OBJS) $(DOCS)

$(LIBDIR)/$(PACKAGEDIR)/ThreadMonitor.class: ThreadMonitor.java
	$(JAVAC) $(JAVACFLAGS) ThreadMonitor.java

$(LIBDIR)/$(PACKAGEDIR)/ThreadMonitorFrame.class: ThreadMonitorFrame.java
	$(JAVAC) $(JAVACFLAGS) ThreadMonitorFrame.java

$(DOCS): $(SRCS)
	$(JAVADOC) -sourcepath ../..:$(CLASSPATH) -d $(DOCSDIR) $(DOCFLAGS) $(PACKAGENAME)

checkout:
	$(CO) $(CO_OPTIONS) $(SRCS)

checkin:
	$(CI) $(SRCS)

clean:
	$(RM) $(RM_OPTIONS) $(OBJS) $(TIDY_OPTIONS)

tidy:
	$(RM) $(RM_OPTIONS) $(TIDY_OPTIONS)

# $Log: not supported by cvs2svn $
# Revision 1.2  1999/03/25 14:01:31  dev
# Backup
#
# Revision 1.1  1999/03/19 16:22:33  dev
# Backup

