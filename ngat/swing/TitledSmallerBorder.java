// TitledSmallerBorder.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/swing/TitledSmallerBorder.java,v 0.1 1999-11-22 09:53:49 cjm Exp $

import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

/**
 * This class extends TitledBorder, but tries to reduce the ridiculously big gap between
 * the title text and the inner contents. It does this by over-riding getBorderInsets.
 */
public class TitledSmallerBorder extends TitledBorder
{
	/**
	 * A Constructor, calls the super constructor.
	 */
	public TitledSmallerBorder(String title)
	{
		super(title);
	}

	/**
	 * Method to calculate the insets for component c. It calls the super getBorderInsets for
	 * left, bottom, right, and uses the title font size (the actual value or the UI manager default)
	 * to calculate a better bottom inset value.
	 * @param c The component to calculate the insets for.
	 * @return Return the insets to use. 
 	 */
	public Insets getBorderInsets(Component c)
	{
		Insets in = null;
		Font font = null;
		int topInset = 20;

		in = super.getBorderInsets(c);
		if(titleFont != null)
			font = titleFont;
		else
			font = UIManager.getFont("TitledBorder.font");
		if(font != null)
			topInset = font.getSize()+TEXT_INSET_H;
		return new Insets(topInset,in.left,in.bottom,in.right);
	}
}
//
// $Log: not supported by cvs2svn $
//
