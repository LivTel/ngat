# Makefile -*- mode: Fundamental;-*-
# $Header: /space/home/eng/cjm/cvs/ngat/util/Makefile,v 1.2 1999-03-25 14:01:31 dev Exp $ 

PACKAGEDIR = ngat/util
PACKAGENAME = ngat.util
LIBDIR = ../../javalib
JAVACFLAGS = -d $(LIBDIR)
DOCSDIR = ../javadocs/$(PACKAGEDIR)
DOCFLAGS = -version -author -private
SRCS = ThreadMonitor.java ThreadMonitorFrame.java
OBJS = $(SRCS:%.java=$(LIBDIR)/$(PACKAGEDIR)/%.class)
DOCS = $(SRCS:%.java=$(DOCSDIR)/$(PACKAGENAME).%.html)

top: $(OBJS) $(DOCS)

$(LIBDIR)/$(PACKAGEDIR)/ThreadMonitor.class: ThreadMonitor.java
	javac $(JAVACFLAGS) ThreadMonitor.java

$(LIBDIR)/$(PACKAGEDIR)/ThreadMonitorFrame.class: ThreadMonitorFrame.java
	javac $(JAVACFLAGS) ThreadMonitorFrame.java

$(DOCS): $(SRCS)
	javadoc -sourcepath ../..:$(CLASSPATH) -d $(DOCSDIR) $(DOCFLAGS) $(PACKAGENAME)

# $Log: not supported by cvs2svn $
# Revision 1.1  1999/03/19 16:22:33  dev
# Backup

