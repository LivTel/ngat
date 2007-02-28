package ngat.util.logging;

import java.io.*;
import javax.swing.*;

public class TextAreaLogHandler extends LogHandler {

    /** The TextArea to display logging on.*/
    protected JTextArea textArea;

    /** Create a TextAreaLogHandler using the specified JTextArea
     * and formatter.*/
    public TextAreaLogHandler(JTextArea textArea, LogFormatter formatter) {
	super(formatter);
	this.textArea = textArea;
	textArea.append(formatter.getHead());
    }

    /** Publish a LogRecord to the TextArea.*/
    public void publish(LogRecord record) {
	textArea.append(formatter.format(record));
    }
    
    /** Write the tail.*/
    public void close() {
	textArea.append(formatter.getTail());
    }

}
