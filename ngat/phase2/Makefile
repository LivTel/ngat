# Makefile
# $Header: /space/home/eng/cjm/cvs/ngat/phase2/Makefile,v 1.1 2000-12-01 18:39:25 snf Exp $
# $Author: snf $

include		../../Makefile.common
include		../Makefile.common
include		./Makefile.common

# Note this is a more complicated Makefile than most in ngat,
# it creates two jars:
# ngat_phase2_nonpersist.jar - containing nonpersist directories,
#	used in phase2 tool which has no access to PSE Pro.
# ngat_phase2.jar - The main jar, using post-processed class files.

PACKAGEDIR 	=ngat/$(PHASE2_NAME)
PACKAGENAME 	=ngat.$(PHASE2_NAME)
JAR_FILE	=$(NGAT_HOME)_$(PHASE2_NAME).jar
PRE_PROCESS_DIR	=pre_processed
DIRS 		=nonpersist util
JAVACFLAGS 	=-d $(LIBDIR)/$(PRE_PROCESS_DIR) -sourcepath ../.. \
		-classpath $(LIBDIR)/$(PRE_PROCESS_DIR):$(LIBDIR):$(CLASSPATH)
DOCSDIR 	=$(NGAT_DOC_HOME)/javadocs/$(PACKAGEDIR)
DOCFLAGS 	=-version -author -private
OSJCFP		=osjcfp
OSJCFPFLAGS	=-persistcapable
SRCS 		= CCDConfig.java CCDDetector.java Comet.java DBObject.java \
		DBObjectDescriptor.java Detector.java EphemerisGroup.java ExtraSolarSource.java FixedGroup.java \
		Group.java HiResSpecConfig.java InstrumentConfig.java IRCamConfig.java LowResSpecConfig.java \
		MajorPlanet.java MinorPlanet.java MonitorGroup.java Moon.java Observation.java \
		OrbitalPosition.java PipelineConfig.java Position.java Proposal.java Root.java \
		ScheduleDescriptor.java Source.java SpecConfig.java Tag.java TelescopeConfig.java User.java Window.java
OBJS 		= $(SRCS:%.java=$(LIBDIR)/$(PRE_PROCESS_DIR)/$(PACKAGEDIR)/%.class)
POST_PROCESS_OBJS= $(SRCS:%.java=$(LIBDIR)/$(PACKAGEDIR)/%.class)
DOCS 		= $(SRCS:%.java=$(DOCSDIR)/$(PACKAGEDIR)/%.html)

top: jars docs

jars: nonpersist_top $(JARLIBDIR)/$(JAR_FILE)

nonpersist_top:
	(echo making in nonpersist...; cd nonpersist; $(MAKE) );

# Note these cheat a bit at the moment
$(JARLIBDIR)/$(JAR_FILE): util_top $(POST_PROCESS_OBJS)
	(cd $(LIBDIR); $(JAR) $(JAR_OPTIONS) $(JAR_FILE) $(PACKAGEDIR)/*.class $(PACKAGEDIR)/util ; $(MV) $(JAR_FILE) $(JARLIBDIR))

util_top:
	(echo making in util...; cd util; $(MAKE) );

# osjcfp from pre-processed to post-processed directory
$(POST_PROCESS_OBJS):$(OBJS)
	(cd $(LIBDIR)/$(PRE_PROCESS_DIR) ; $(OSJCFP) $(OSJCFPFLAGS) -dest $(LIBDIR) $(OBJS) );

# compile java from source to pre-processed directory
$(LIBDIR)/$(PRE_PROCESS_DIR)/$(PACKAGEDIR)/%.class: %.java
	$(JAVAC) $(JAVAC_OPTIONS) $(JAVACFLAGS) $<


docs: $(DOCS)

$(DOCSDIR)/$(PACKAGEDIR)/%.html: %.java 
	$(JAVADOC) -sourcepath ../..:$(CLASSPATH) -d $(DOCSDIR) $(DOCFLAGS) $(PACKAGENAME) $(PACKAGENAME).util $(PACKAGENAME).nonpersist

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

clean:
	-$(RM) $(RM_OPTIONS) $(OBJS) $(POST_PROCESS_OBJS) $(TIDY_OPTIONS)
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
	-$(RM) $(RM_OPTIONS) $(OBJS) $(POST_PROCESS_OBJS)
	@for i in $(DIRS); \
	do \
		(echo backup in $$i...; cd $$i; $(MAKE) backup); \
	done;

#
# $Log: not supported by cvs2svn $
#
