# Makefile -*- mode: Fundamental;-*-
# $Header: /space/home/eng/cjm/cvs/ngat/util/Makefile,v 1.1 1999-03-19 16:22:33 dev Exp $ 

PACKAGEDIR = ngat/net
PACKAGENAME = ngat.net
LIBDIR = ../../javalib
JAVACFLAGS = -d $(LIBDIR)
DOCSDIR = ../javadocs/$(PACKAGEDIR)
DOCFLAGS = -version -author -private
SRCS = TCPServer.java TCPServerConnectionThread.java TCPClientConnectionThread.java
OBJS = $(SRCS:%.java=$(LIBDIR)/$(PACKAGEDIR)/%.class)
DOCS = $(SRCS:%.java=$(DOCSDIR)/$(PACKAGENAME).%.html)

top: $(OBJS) $(DOCS)

$(LIBDIR)/$(PACKAGEDIR)/TCPServer.class: TCPServer.java $(LIBDIR)/$(PACKAGEDIR)/TCPServerConnectionThread.class
	javac $(JAVACFLAGS) TCPServer.java

$(LIBDIR)/$(PACKAGEDIR)/TCPServerConnectionThread.class: TCPServerConnectionThread.java
	javac $(JAVACFLAGS) TCPServerConnectionThread.java

$(LIBDIR)/$(PACKAGEDIR)/TCPClientConnectionThread.class: TCPClientConnectionThread.java
	javac $(JAVACFLAGS) TCPClientConnectionThread.java

$(DOCS): $(SRCS)
	javadoc -d $(DOCSDIR) $(DOCFLAGS) $(SRCS)

# $Log: not supported by cvs2svn $
# Revision 1.2  1999/03/19 11:51:09  dev
# Backup
#
# Revision 1.1  1999/03/16 17:06:23  dev
# Backup
#

