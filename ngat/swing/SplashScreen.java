// SplashScreen.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/swing/SplashScreen.java,v 0.2 1999-12-14 12:29:34 cjm Exp $
package ngat.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * This class provides a splash screen to be used when a program is starting up.
 * The splash screen stays up for a user definable length of time.
 * Clicking the mouse on the splash screen unmanages it.
 * @author Chris Mottram
 * @version $Revision: 0.2 $
 */
public class SplashScreen implements Runnable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: SplashScreen.java,v 0.2 1999-12-14 12:29:34 cjm Exp $");
	/**
	 * The window the splash screen appears in.
	 */
	private JWindow splashWindow;
	/**
	 * The content panel of the splash window.
	 */
	private JPanel contentPanel;
	/**
	 * The desired width of the splash screen.
	 */
	private int width = 240;
	/**
	 * The desired height of the splash screen.
	 */
	private int height = 120;
	/**
	 * Filename for the image.
	 */
	private String imageFilename = null;
	/**
	 * Message String underneath the image.
	 */
	private String messageString = null;
	/**
	 * The border to put round the splash.
	 */
	private Border border = null;
	/**
	 * The length of time to show thw splash screen for.
	 */
	private int duration = 1000;

	/**
	 * Constructor. Creates the window and content panel.
	 */
	public SplashScreen()
	{
		splashWindow = new JWindow();
		contentPanel = (JPanel)splashWindow.getContentPane();
	}

	/**
	 * Constructor. Creates the window and content panel. Sets other internal fields up.
	 * @param w The width of the Splash Screen.
	 * @param h The height of the splash screen.
	 * @param iFilename The filename of an image to display.
	 * @param m A message string to display.
	 * @param b A border to surround the splash screen.
	 */
	public SplashScreen(int w,int h,String iFilename,String m,Border b)
	{
		splashWindow = new JWindow();
		contentPanel = (JPanel)splashWindow.getContentPane();
		width = w;
		height = h;
		imageFilename = new String(iFilename);
		messageString = new String(m);
		border = b;
	}

	/**
	 * Set the width of the splash screen.
	 * @param w the width.
	 */
	public void setWidth(int w)
	{
		width = w;
	}

	/**
	 * Set the height of the splash screen.
	 * @param h The height.
	 */
	public void setHeight(int h)
	{
		height = h;
	}

	/**
	 * Set the image filename to display on the splash screen.
	 * @param s A valid image filename.
	 */
	public void setImageFilename(String s)
	{
		imageFilename = new String(s);
	}

	/**
	 * Set the message to display on the splash screen.
	 * @param s A message to display.
	 */
	public void setMessage(String s)
	{
		messageString = new String(s);
	}

	/**
	 * Set the border to surround the splash screen.
	 * @param b The border.
	 */
	public void setBorder(Border b)
	{
		border = b;
	}

	/**
	 * Method to display a splash screen for a given time.
	 * Sets up the window. Starts a thread that calls this classes run method.
	 * @param d The duration to display the splash screen in milliseconds.
	 */
	public void show(int d)
	{
		duration = d;

		// centre the window
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-width)/2;
		int y = (screen.height-height)/2;
		splashWindow.setBounds(x,y,width,height);

		// build the splash screen
		if(imageFilename != null)
		{
			JLabel label = new JLabel(new ImageIcon(imageFilename));
			contentPanel.add(label,BorderLayout.CENTER);
		}
		if(messageString != null)
		{
			JLabel copyrt = new JLabel(messageString,JLabel.CENTER);
			copyrt.setFont(new Font("Sans-Serif",Font.BOLD,12));
			contentPanel.add(copyrt,BorderLayout.SOUTH);
		}
		if(border == null)
			contentPanel.setBorder(BorderFactory.createLineBorder(Color.red,10));
		else
			contentPanel.setBorder(border);
		contentPanel.addMouseListener(new SplashScreenMouseListener());

		// start a thread to display it
		Thread t = new Thread(this);
		t.start();
	}

	/**
	 * Run method, called from show method to actually display the splash (in a separate thread).
	 * Sets the splashWindow visible, sleeps for a duration and then makes it non-visible.
	 * @see #splashWindow
	 * @see #duration
	 */
	public void run()
	{
		splashWindow.setVisible(true);
		try
		{
			Thread.sleep(duration);
		}
		catch(Exception e)
		{
		}
		splashWindow.setVisible(false);
	}

	/**
	 * Inner class providing a mouse listener that listens for mouse clicks on the splash screen.
	 */
	class SplashScreenMouseListener extends MouseAdapter
	{
		/**
		 * Method called when the mouse is clicked on the splash window.
		 * Unmanages the splash window.
		 * @see #splashWindow
		 */
		public void mouseClicked(MouseEvent e)
		{
			splashWindow.setVisible(false);
		}
	}

}
//
// $Log: not supported by cvs2svn $
// Revision 0.1  1999/12/14 11:58:36  cjm
// initial revision.
//
//
