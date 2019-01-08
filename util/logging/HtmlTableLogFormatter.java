package ngat.util.logging;

import java.text.*;

/** Formatter for formatting record style logging.*/
public class HtmlTableLogFormatter extends LogFormatter {
    
    /** The title to write in the header.*/
    protected String title;
    
    /** The list of column headers.*/
    protected String[] headers;
    
    /** A set of java.text.Formats to use for formatting the columns.*/
    protected Format[] formats;

    /** Create an HtmlTableLogFormatter using the specified title and headers.
     * @param title The title.
     * @param headers The array of column headers.*/
    public HtmlTableLogFormatter(String title, String[] headers) {
	super();
        this.title = title;
        this.headers = headers;
    }
    
    /** Create an HtmlTableLogFormatter using the specified title, headers
     * and Formats.
     * @param title The title.
     * @param headers The array of column headers.
     * @param formats The set of java.text.Formats to use.*/
    public HtmlTableLogFormatter(String title, String[] headers, Format[] formats) {
        super();
	this.title = title;
        this.headers = headers;
	this.formats = formats;
    }

    /** Format a LogRecord into an Html table row. If the Format array
     * contains too few elements, the fields are written unformatted. If
     * the formatter for a column fails for some reason, the field is 
     * written out unformatted.
     * @param record The LogRecord to format.
     * @return The formatted record.*/
    public String format(LogRecord record) {
        Object[] params = record.getParams();
        StringBuffer line = new StringBuffer("<tr>");
        for (int i = 0; i < params.length; i++) {
	    if (formats != null) { 
		if (formats.length < params.length) {
		    line.append("<td>"+params[i]+"</td>");
		} else {   	    
		    try {
			line.append("<td>"+formats[i].format(params[i])+"</td>");
		    } catch (IllegalArgumentException iax) {
			line.append("<td>"+params[i]+"</td>");
		    }
		}
	    } else {
		line.append("<td>"+params[i]+"</td>");
	    }
        }
        line.append("</tr>");
        return line.toString();
    }
    
    /** @return The html table header.*/
    public String getHead() {
        StringBuffer header = new StringBuffer("<html>\n<head>\n<title>"+title+"</title>\n</head>\n"+
					       "<body bgcolor = #3232AA >\n<h2>"+title+"</h2><hr>\n"+
           "<table cols = "+headers.length+" border = 1  >\n");
        header.append("<thead alignment = center verticalalignment = top><tr>");
        for (int i = 0; i < headers.length; i++) {
            header.append("<th>"+headers[i]+"\n");
        }
        header.append("\n</tr>\n<tbody>\n");
        return header.toString();
    }
    
    /** @return The html closing sequence.*/
    public String getTail() {
	return "</table>\n</body>\n</html>";
    }
    
    /** Return the file name extension for the formatter.*/
    public String getExtensionName() { return "html"; }
    
    /** Sets the list of Formats to use for each column.
     * @param formats The array of (column) formatters.*/    
    public void setFormats(Format[] formats) { this.formats = formats; }

}
