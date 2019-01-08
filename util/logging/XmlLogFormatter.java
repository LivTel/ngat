package ngat.util.logging;

import java.util.*;
import java.text.*;

public class XmlLogFormatter extends LogFormatter {

    public XmlLogFormatter() {
	super();
    }

    public String format(LogRecord record) {
	return 
	    "\t<record>"+
	    "\n\t\t<logger>"+record.getLoggerName()+"</logger>"+
	    "\n\t\t<sequence>"+record.getSeqno()+"</sequence>"+
	    "\n\t\t<level>"+record.getLevel()+"</level>"+
	    "\n\t\t<cat>"+record.getCat()+"</cat>"+
	    "\n\t\t<date>"+df.format(new Date(record.getTime()))+"</date>"+
	    "\n\t\t<class>"+record.getClazz()+"</class>"+
	    "\n\t\t<source>"+record.getSource()+"</source>"+
	    "\n\t\t<method>"+record.getMethod()+"</method>"+
	    "\n\t\t<thread>"+record.getThread()+"</thread>"+
	    "\n\t\t<message>"+record.getMessage()+"</message>"+
	    "\n\t</record>";
    }
    
    public String getHead() {
	return 
	    "<?xml version = \"1.0\" encoding = \"UTF-8\" standalone = \"yes\">"+
	    "\n<!DOCTYPE log ["+
	    "\n<!ELEMENT log (record*)>"+
	    "\n<!ELEMENT record (logger, sequence, level, cat, date, class, source, method, thread, message) >"+
	    "\n<!ELEMENT logger   (#PCDATA) >"+
	    "\n<!ELEMENT sequence (#PCDATA) >"+
	    "\n<!ELEMENT level    (#PCDATA) >"+
	    "\n<!ELEMENT cat      (#PCDATA) >"+
	    "\n<!ELEMENT date     (#PCDATA) >"+
	    "\n<!ELEMENT class    (#PCDATA) >"+
	    "\n<!ELEMENT source   (#PCDATA) >"+
	    "\n<!ELEMENT method   (#PCDATA) >"+
	    "\n<!ELEMENT thread   (#PCDATA) >"+
	    "\n<!ELEMENT message  (#PCDATA) >"+
	    "\n]>"+
	    "\n<log>";
    }
    
    public String getTail() {
	return "\n</log>";
    }

    /** Return the file name extension for the formatter.*/
    public String getExtensionName() { return "xml"; }

} 
