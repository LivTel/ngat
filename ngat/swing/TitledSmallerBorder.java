/*   
    Copyright 2006, Astrophysics Research Institute, Liverpool John Moores University.

    This file is part of NGAT.

    NGAT is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    NGAT is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with NGAT; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/
// TitledSmallerBorder.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/swing/TitledSmallerBorder.java,v 0.3 2006-05-16 18:15:35 cjm Exp $
package ngat.swing;

import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

/**
 * This class extends TitledBorder, but tries to reduce the ridiculously big gap between
 * the title text and the inner contents. It does this by over-riding getBorderInsets.
 * This may not be needed once the font settings are sorted out.
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
// Revision 0.2  1999/11/29 11:52:46  cjm
// Changed package to ngat.swing.
//
// Revision 0.1  1999/11/22 09:53:49  cjm
// initial revision.
//
//
