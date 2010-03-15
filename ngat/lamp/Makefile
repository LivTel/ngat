# $Header: /space/home/eng/cjm/cvs/ngat/lamp/Makefile,v 1.1 2010-03-15 14:43:05 cjm Exp $
# $Author: cjm $
# $Id: Makefile,v 1.1 2010-03-15 14:43:05 cjm Exp $
PACKAGENAME    	= ngat.lamp
PACKAGEDIR     	= ngat/lamp
JAR_FILE       	= ngat_lamp.jar

SRCS           	= PLCConnection.java PLCValueSetter.java LampInterface.java LampUnitInterface.java \
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
