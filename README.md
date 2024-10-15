Next Generation Astronomical Telescope library. A general Java library used by a lot of LT projects. This consists of the following sub-packages:
## astrometry
Java astrometry routines, including slalib and wcstools wrappers.
## ccd
CCD library used by RATCam (now deprecated).
## df1
PLC communication library using the DF1 protocol, i.e. a Micrologix 110 via serial or an Arcom ESS ethernet to serial
interface.
## dichroic
Dichroic control code (not installed on the telescope).
## eip
PLC communication library using CIP/EIP over ethernet (i.e. Micrologix 1100).
## fits
FITS handling library, used by instruments.
## flask
Client-side code for interacting with python Flask API servers. We are thinking of using Flask for NRT, and this allows LT Java client-side calls to bemade to these servers.
## instrument
## lamp
This class supports an interface to the LT A&G lamp unit. This unit is a PLC controlled device
 that supports 3 lamps (Tungsten,Neon and Xenon). They are controlled with a Micrologix 1100 PLC
 over Ethernet/IP (controlled via the ngat.eip library). 
## lamp-df1-arcomess
This class supports an interface to the LT A&G lamp unit. This unit is a PLC controlled device
 that supports 3 lamps (Tungsten,Neon and Xenon). They are controlled with a Micrologix 1100 PLC
 (controlled via the ngat.df1 library). The communications are currently done over a serial link
 via an ArcomESS ethernet to serial converter (handled by the ngat.serial.arcomess. library).
This library now deprecated and replaced by the ngat.lamp library.
## latex
Various LaTeX documentation on some of the libraries.
## Makefile
Top level Makefile for the ngat package.
## Makefile.ngat
Makefile includefile for various ngat common variables/functions.
## math
Various math software, including Chi-squared fitting used by focusing code.
## message
NGAT Java message library, used to pass serialized Java message objects between LT Java sub-systems.
## net
Various Java networking code.
## ngtcs
Next generation TCS (incomplete project).
## phase2
Original Phase2 Java objects (superceded by a newer library, but still used in odd places).
## scripts
Various scripts.
## serial
Arcom ESS (Serial to ethernet converter) Java interface library.
## sound
Java sound library, used by icsgui.
## spec
Java interface to the (Meaburn) spectrograph. Now deprecated.
## swing
Java Swing GUI utility library.
## util
General Java utility library.
