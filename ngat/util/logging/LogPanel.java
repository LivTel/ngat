package ngat.util.logging;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/** 
 * A resizeable panel for displaying logging information. 
 *
 * $Id: LogPanel.java,v 1.1 2007-02-28 06:17:00 snf Exp $
 *
 **/
public class LogPanel extends JPanel implements ActionListener {

    /** Maximum size of the textarea in characters. */
    protected static int MAX_CHARS = 500;

    /** Optimum size of the textarea in characters. */
    protected static int OPT_CHARS = 200;

    /** The area where the text is displayed. */
    protected JTextArea textArea;

    /** A hook to the JFrame which contains this panel. */
    protected JFrame parent;

    /** Keeps track of the number of rows in the text area. */
    protected int rows;

    /** Keeps track of the number of columns in the text area. */
    protected int cols;

    /** Keeps track of the number of characters written to the textarea.
     * The TA is flushed when this becomes too large to avoid memory problems. */
    protected int chars;
    
    /** Creates a LogPanel attached to the specified JFrame. 
     * @param parent The JFrame containing the LogPanel. */
    public LogPanel(JFrame parent) {
	
	this.parent = parent;
	textArea = new JTextArea(10,60);
	rows = 10;
	cols = 60;
	chars = 0;

	// Expands the text area vertically.
	JButton upBtn = new JButton("[-]");
	upBtn.setActionCommand("UP");
	upBtn.setToolTipText("Expand the logging area");
	upBtn.addActionListener(this);	

	// Shrinks the text area vertically.
	JButton downBtn = new JButton("]-[");	
	downBtn.setActionCommand("DOWN");
	downBtn.setToolTipText("Shrink the logging area");
	downBtn.addActionListener(this);

	// Expands the text area horizontally.
	JButton wideBtn = new JButton("<>");
	wideBtn.setActionCommand("WIDE");
	wideBtn.setToolTipText("Widen the logging area");
	wideBtn.addActionListener(this);	

	// Expands the text area horizontally.
	JButton narBtn = new JButton("><");
	narBtn.setActionCommand("NARROW");
	narBtn.setToolTipText("Narrow the logging area");
	narBtn.addActionListener(this);	

	JViewport jv1 = new JViewport();
	jv1.setView(new JLabel("Logging Panel"));
	jv1.setBackground(Color.red);

	JViewport jv2 = new JViewport();
	jv2.setView(new JLabel(". ."));
	jv2.setBackground(Color.red);


	JScrollPane scrollPanel = new JScrollPane(textArea);
	scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	scrollPanel.setCorner(JScrollPane.UPPER_LEFT_CORNER, downBtn);
	scrollPanel.setCorner(JScrollPane.UPPER_RIGHT_CORNER, upBtn);
	scrollPanel.setCorner(JScrollPane.LOWER_LEFT_CORNER, wideBtn);
	scrollPanel.setCorner(JScrollPane.LOWER_RIGHT_CORNER, narBtn);
	scrollPanel.setColumnHeader(jv1);
	scrollPanel.setRowHeader(jv2);
	
	//  JPanel btnPanel = new JPanel();
//  	btnPanel.setLayout(new GridLayout(2,1));
//  	btnPanel.add(upBtn);
//  	btnPanel.add(downBtn);
	
	add(scrollPanel, BorderLayout.CENTER);
	//	add(btnPanel, BorderLayout.EAST);
	
    }

    public void actionPerformed(ActionEvent ae) {
	String cmd = ae.getActionCommand();
	
	if (cmd.equals("UP")) {
	    rows += 4;
	    textArea.setRows(rows);
	    parent.pack();
	    parent.validate();
	} else
	    if (cmd.equals("DOWN")) {
		if (rows < 5) return;
		rows -= 4;
		textArea.setRows(rows);
		parent.pack();
		parent.validate();
	    } else 
		if (cmd.equals("WIDE")) {
		    cols += 4;
		    textArea.setColumns(cols);
		    parent.pack();
		    parent.validate();
		} else 
		    if (cmd.equals("NARROW")) {
			if (cols < 5) return;
			cols -= 4;
			textArea.setColumns(cols);
			parent.pack();
			parent.validate();
		    }
    }

    public void write(String text) {
	textArea.append(text);
	chars += text.length();
	if (chars > MAX_CHARS) {
	    //  textArea.replaceRange("", 0, OPT_CHARS);
	    //  System.out.println("Changing");
	    //  parent.pack();
	    //  parent.validate();
	}
    }
}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2004/01/15 16:03:51  snf
/** Initial revision
/**
/** Revision 1.1  2000/11/21 16:40:40  snf
/** Initial revision
/** */























