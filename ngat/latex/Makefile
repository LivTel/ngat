# $Header: /space/home/eng/cjm/cvs/ngat/latex/Makefile,v 0.1 1999-08-26 10:53:36 cjm Exp $

include ../../Makefile.common
include ../Makefile.common

DOCSDIR 	= $(NGAT_DOC_HOME)/latex

TEXSRCS 	= ngat.net.tex ngat.net_error_machanisms.tex
FIGSRCS 	= ngat.net.protocol.fig

DVIOBJS		= $(TEXSRCS:%.tex=%.dvi)
PSOBJS		= $(TEXSRCS:%.tex=$(DOCSDIR)/%.ps)

EPSOBJS		= $(FIGSRCS:%.fig=%.eps)

top: $(PSOBJS)


$(DOCSDIR)/%.ps: %.dvi
	$(DVIPS) $< -o
	$(MV) $*.ps $(DOCSDIR)

$(DVIOBJS): $(TEXSRCS) $(EPSOBJS)

%.dvi: %.tex
	$(LATEX) $<
	$(LATEX) $<
	$(LATEX) $<

%.eps: %.fig
	$(FIG2DEV) -L ps $< $@

depend:
	echo "NO depend rule for latex files."

clean:
	$(RM) $(RM_OPTIONS) $(DVIOBJS) $(PSOBJS) $(EPSOBJS) $(LATEX_TIDY_OPTIONS) $(TIDY_OPTIONS)

tidy:
	$(RM) $(RM_OPTIONS) $(LATEX_TIDY_OPTIONS) $(TIDY_OPTIONS)

checkin:
	-$(CI) $(TEXSRCS) $(FIGSRCS)

checkout:
	-$(CO) $(CO_OPTIONS) $(TEXSRCS) $(FIGSRCS)

#
# $Log: not supported by cvs2svn $
#

# DO NOT DELETE
