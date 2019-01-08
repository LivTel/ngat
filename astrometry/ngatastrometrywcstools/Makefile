# Makefile
# $Header

include ../../../Makefile.common

PACKAGEDIR = ngat/astrometry
PACKAGENAME = ngat.astrometry
DIRS = c

top:
	@for i in $(DIRS); \
	do \
		(echo making in $$i...; cd $$i; $(MAKE) ); \
	done; \

checkout:
	@for i in $(DIRS); \
	do \
		(echo checkout in $$i...; cd $$i; $(CO) $(CO_OPTIONS) Makefile; $(MAKE) checkout); \
	done;

checkin:
	-@for i in $(DIRS); \
	do \
		(echo checkin in $$i...; cd $$i; $(MAKE) checkin; $(CI) $(CI_OPTIONS) Makefile); \
	done;

depend:
	@for i in $(DIRS); \
	do \
		(echo depend in $$i...; cd $$i; $(MAKE) depend); \
	done;

clean:
	-$(RM) $(RM_OPTIONS) $(TIDY_OPTIONS)
	@for i in $(DIRS); \
	do \
		(echo cleaning in $$i...; cd $$i; $(MAKE) clean); \
	done;

tidy:
	-$(RM) $(RM_OPTIONS) $(TIDY_OPTIONS)
	-@for i in $(DIRS); \
	do \
		(echo cleaning in $$i...; cd $$i; $(MAKE) tidy); \
	done;

backup: tidy
	@for i in $(DIRS); \
	do \
		(echo backup in $$i...; cd $$i; $(MAKE) backup); \
	done;
	$(TAR) cvf $(BACKUP_DIR)/ngatastrometrywcstools.tar .
	$(COMPRESS) $(BACKUP_DIR)/ngatastrometrywcstools.tar

#
# $Log: not supported by cvs2svn $
#
