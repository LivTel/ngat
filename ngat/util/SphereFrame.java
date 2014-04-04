package ngat.util;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * A JFrame which allows applications to popup a simple SphereView with a
 * set of default control buttons. Classes SphereViewer and SphereViewerPanel
 * will allow these controlled Panels to be added to GenericDlgs. 
 *
 *
 * $Id$
 *
 */
public class SphereFrame extends GraphFrame implements ActionListener {

    /** The panel containing the 3D sphere projection. */
    SpherePlot plot;
    /** True if the view is being rotated by a Rotator Thread. */
    boolean spinning;

    static final double PI = Math.PI;

    public SphereFrame(String title, SpherePlot plot) {
	super(title, plot);
	this.plot = plot;
	spinning = false;
	JToolBar jtb = new JToolBar();
	JButton b1 = new JButton("th +");
	b1.setToolTipText("pan up");
	b1.setActionCommand("pan up");
	b1.addActionListener(this);
	jtb.add(b1);
	b1 = new JButton("th -");
	b1.setToolTipText("pan down");
	b1.setActionCommand("pan down");
	b1.addActionListener(this);
	jtb.add(b1);
	b1 = new JButton("phi >");
	b1.setToolTipText("pan right");
	b1.setActionCommand("pan right");
	b1.addActionListener(this);
	jtb.add(b1);
	b1 = new JButton("< phi");
	b1.setToolTipText("pan left");
	b1.setActionCommand("pan left");
	b1.addActionListener(this);
	jtb.add(b1);
	b1 = new JButton("<<< phi");
	b1.setToolTipText("rotate left");
	b1.setActionCommand("rotate left");
	b1.addActionListener(this);
	jtb.add(b1);
	b1 = new JButton("phi >>>");
	b1.setToolTipText("rotate right");
	b1.setActionCommand("rotate right");
	b1.addActionListener(this);
	jtb.add(b1);
	b1 = new JButton("STOP");
	b1.setToolTipText("stop");
	b1.setActionCommand("stop");
	b1.addActionListener(this);
	jtb.add(b1);
	
	getContentPane().add(jtb, BorderLayout.NORTH);
	getContentPane().add(plot, BorderLayout.CENTER);

    }
	
    public void actionPerformed(ActionEvent ae) {
	String command = ae.getActionCommand();

	if (command.equals("pan up")) {
	    plot.incTh0(.08);
	}

	if (command.equals("pan down")) {
	    plot.decTh0(.08);
	}

	if (command.equals("pan left")) {
	    plot.incPhi0(.08);
	}

	if (command.equals("pan right")) {
	    plot.decPhi0(.08);
	}
	
	if (command.equals("rotate left")) {
	    new Rotator(0.005).start();
	}
	
	if (command.equals("rotate right")) {
	    new Rotator(-0.005).start();
	}
	
	if (command.equals("stop")) {
	    spinning = false;
	}
	
    }

    public SpherePlot getSpherePlot() { return plot;}


    public void putPoint(float x, float y) { plot.putPoint(x,y);}

    class Rotator extends Thread {
	final double delta;
	Rotator(double d) {
	    delta = d;
	    spinning = true;
	}
	public void run() {
	    while (spinning) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
			plot.incPhi0(delta);
		    }
		});
		try {sleep(100);} catch (InterruptedException ie) {}
	    }
	}
    }  // [Rotator]

} // [SphereFrame]

/** $Log: not supported by cvs2svn $ */


