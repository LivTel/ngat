# $Header: /space/home/eng/cjm/cvs/ngat/message/Makefile,v 1.2 2002-10-02 11:03:00 je Exp $ 

include	../Makefile.common
include	Makefile.common

DIRS = base OSS RCS_TCS INST_DP ISS_INST

top:
	@for i in $(DIRS); \
	do \
		(echo making in $$i...; cd $$i; $(MAKE) ); \
	done; \

checkin:
	-@for i in $(DIRS); \
	do \
		(echo checkin in $$i...; cd $$i; $(MAKE) checkin; $(CI) $(CI_OPTIONS) Makefile); \
	done;

checkout:
	@for i in $(DIRS); \
	do \
		(echo checkout in $$i...; cd $$i; $(CO) $(CO_OPTIONS) Makefile; $(MAKE) checkout); \
	done;

clean:
	-$(RM) $(RM_OPTIONS) $(TIDY_OPTIONS)
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

# this only works if the checkin target leaves the sub-dirs makefiles checked out unlocked (ci -u)
backup: tidy
	@for i in $(DIRS); \
	do \
		(echo backup in $$i...; cd $$i; $(MAKE) backup); \
	done;
# $Log: not supported by cvs2svn $
# Revision 1.1  2000/12/01 18:39:12  snf
# Initial revision
#
