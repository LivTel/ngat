package ngat.util.logging;

public class XmlTableLogFormatter extends LogFormatter {
    
    final String START = 
	"<?xml version = \"1.0\" encoding = \"UTF-8\">"+
	"\n<!DOCTYPE ";
    
    /** The title and top level XML element.*/
    protected String title;

    /** The name of an individual record element.*/
    protected String element;
    
    /** The list of record subelements.*/
    protected String[] headers;
    
    /** Create an XmlTableLogFormatter for record style logging using thw
     * supplied title, record and headers.
     * @param title The title and top level XML element.
     * @param record The name of an individual record element.
     * @param headers The list of record subelements.
     */
    public XmlTableLogFormatter(String title, String element, String[] headers) {
	super();
        this.title = title;
	this.element = element;
        this.headers = headers;
    }
    
    /** Format a LogRecord into an xml table row.
     * @param record The LogRecord to format.
     * @return The formatted record.*/
    public String format(LogRecord record) {
        Object[] params = record.getParams();
        StringBuffer line = new StringBuffer("\n<"+element+">");
        for (int i = 0; i < params.length; i++) {
            line.append("\n\t<"+headers[i]+">"+params[i]+"</"+headers[i]+">");
        }
        line.append("\n</"+element+">");
        return line.toString();
    }

    /** @return The xml table header.*/
    public String getHead() {
        StringBuffer header = new StringBuffer(START);

	header.append(title+" ["+
		      "\n<!ELEMENT "+title+"("+element+"*) >"+
		      "\n<!ELEMENT "+element+"(");
	
        for (int i = 0; i < headers.length; i++) {
            header.append(headers[i]+", ");
        }
	header.append(") >");

	for (int i = 0; i < headers.length; i++) {
            header.append("\n<!ELEMENT "+headers[i]+" #PCDATA > ");
        }

        header.append("\n]>");

        return header.toString();
    }
    
    /** @return The xml closing sequence.*/
    public String getTail() {
	return "\n</"+title+">";
    }

    /** Return the file name extension for the formatter.*/
    public String getExtensionName() { return "xml"; }
    
}
