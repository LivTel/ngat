# $Header: /space/home/eng/cjm/cvs/ngat/lamp-df1-arcomess/Makefile,v 1.1 2013-06-28 12:34:03 cjm Exp $
# $Author$
# $Id$
PACKAGENAME    	= ngat.lamp
PACKAGEDIR     	= ngat/lamp
JAR_FILE       	= ngat_lamp.jar

SRCS           	= ConnectionParameters.java PLCValueSetter.java LampInterface.java LampUnitInterface.java \
		LTLamp.java LTAGLampUnit.java
CONFIG_SRCS	= lt.lamp.properties
CONFIG_OBJS	= $(CONFIG_SRCS:%.properties=$(LT_BIN_HOME)/config/%.properties)
DIRS           	= test
DIRS_TARGET    	= dirs configs

include $(LT_SRC_HOME)/ngat/Makefile.ngat

configs: $(CONFIG_OBJS)

$(LT_BIN_HOME)/config/%.properties : %.properties
	/bin/cp $< $@

#
# $Log: not supported by cvs2svn $
#
