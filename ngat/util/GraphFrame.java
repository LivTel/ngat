package ngat.util;


/**
 * A JFrame to hold a GraphPlot or subclass thereof. Temp, used for standalone
 * applications that need to popup a simple Graph viewer. The classes  GraphViewer
 * and GraphViewerPanel will allow configurable graphs to be displayed.
 *
 *
 * $Id: GraphFrame.java,v 1.1 2000-11-21 16:46:21 snf Exp $
 *
 */
public class GraphFrame extends javax.swing.JFrame {

    GraphPlot graph;

    public GraphFrame(String title, GraphPlot graph) {
	super(title);
	this.graph = graph;
	getContentPane().add(graph);
    }

    public void putPoint(float x, float y) {
	graph.putPoint(x, y);
    }

    public void putPoint(float x, float y, int level) {
	graph.putPoint(x, y, level);
    }

}

/** $Log: not supported by cvs2svn $ */
