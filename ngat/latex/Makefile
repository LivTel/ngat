# $Header: /space/home/eng/cjm/cvs/ngat/latex/Makefile,v 0.7 2000-06-01 10:42:33 cjm Exp $

include ../../Makefile.common
include ../Makefile.common

DOCSDIR 	= $(NGAT_DOC_HOME)/latex

TEXSRCS 	= ngat.net.tex ngat.swing.tex ngat.ccd.tex ngat.fits.tex ngat.net_error_machanisms.tex
FIGSRCS 	= ngat.net.protocol.fig ngat.net.MAprotocol.fig ngat.fits.dirtree.fig

DVIOBJS		= $(TEXSRCS:%.tex=%.dvi)
PSOBJS		= $(TEXSRCS:%.tex=$(DOCSDIR)/%.ps)

EPSOBJS		= $(FIGSRCS:%.fig=%.eps)

top: $(PSOBJS)

$(DOCSDIR)/%.ps: %.dvi
	$(DVIPS) $< -o
	$(MV) $*.ps $(DOCSDIR)

$(DVIOBJS): $(EPSOBJS)

%.dvi: %.tex
	$(LATEX) $<
	$(LATEX) $<
	$(LATEX) $<

%.eps: %.fig
	$(FIG2DEV) -L ps $< $@

depend:
	echo "NO depend rule for latex files."

clean:
	-$(RM) $(RM_OPTIONS) $(DVIOBJS) $(PSOBJS) $(EPSOBJS) $(LATEX_TIDY_OPTIONS) $(TIDY_OPTIONS)

tidy:
	-$(RM) $(RM_OPTIONS) $(LATEX_TIDY_OPTIONS) $(TIDY_OPTIONS)

backup: tidy
	-$(RM) $(RM_OPTIONS) $(DVIOBJS) $(EPSOBJS)

checkin:
	-$(CI) $(CI_OPTIONS) $(TEXSRCS) $(FIGSRCS)

checkout:
	-$(CO) $(CO_OPTIONS) $(TEXSRCS) $(FIGSRCS)

#
# $Log: not supported by cvs2svn $
# Revision 0.6  2000/02/03 17:07:48  cjm
# Addded ngat.ccd.tex source file.
#
# Revision 0.5  1999/12/10 14:31:07  cjm
# Backup target added.
#
# Revision 0.4  1999/11/29 14:35:59  cjm
# added ngat.swing.tex source file.
#
# Revision 0.3  1999/09/23 14:57:12  cjm
# Added ngat.net.MAprotocol.fig source.
#
# Revision 0.2  1999/09/08 14:33:28  cjm
# Changed checkin target to use CI_OPTIONS.
#
# Revision 0.1  1999/08/26 10:53:36  cjm
# initial revision
#
#

# DO NOT DELETE
