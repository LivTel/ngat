package ngat.util;

import ngat.OSS.client.*;// because it is usually called by Client Dialogs

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** A JFrame container for holding a standalone GraphParamterizer JPanel and
 * supplying a basic set of actions to allow the graph to be interactively modified. 
 *
 *
 * $Id: GraphParameterizerFrame.java,v 1.1 2000-11-21 17:03:04 snf Exp $
 *
 */
public class GraphParameterizerFrame extends JFrame implements ActionListener {

    /** The GraphParameterizer to display. */
    protected GraphParameterizer graph;
    /** The calling object registers here to allow messages to be passed back.*/
    protected CallBackHandler parent;

    public GraphParameterizerFrame(CallBackHandler parent, GraphParameterizer gp) {
	
	this.parent = parent;

	this.graph = gp;
	graph.putPoint(graph.getXLo(), graph.getYHi());
	graph.putPoint(graph.getXHi(), graph.getYHi());

	JToolBar jtb = new JToolBar();
	JButton addBtn = new JButton("ADD ANCHOR");
	addBtn.setActionCommand("add");
	addBtn.addActionListener(this);
	jtb.add(addBtn);
	JButton dragBtn = new JButton("DRAG ANCHOR");
	dragBtn.setActionCommand("drag");
	dragBtn.addActionListener(this);
	jtb.add(dragBtn);
	JButton useBtn = new JButton("PARAMS");
	useBtn.setActionCommand("use");
	useBtn.addActionListener(this);
	jtb.add(useBtn);

	getContentPane().add(jtb, BorderLayout.NORTH);
	getContentPane().add(graph, BorderLayout.CENTER);

	setBounds(200,200,600,600);
	setVisible(true);

    }

    /** Defines 3 basic actions.
     * - Add an anchor to the Graph.
     * - Drag an anchor point around.
     * - Dismiss and send CallBack message to caller.*/
    public void actionPerformed(ActionEvent ae) {
	String command = ae.getActionCommand();

	if (command.equals("add")) {
	    graph.setMode(GraphParameterizer.ADD_NODE);
	} 
	
	if (command.equals("drag")) {
	    graph.setMode(GraphParameterizer.MOVE_NODE_START);
	}

	if (command.equals("use")) {
	    parent.callBack(); 
	}
    }
}

/** $Log: not supported by cvs2svn $ */








