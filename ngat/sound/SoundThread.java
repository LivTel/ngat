// AttributedTextArea.java
// $Header: /space/home/eng/cjm/cvs/ngat/sound/SoundThread.java,v 1.1 2003-09-19 14:59:14 cjm Exp $
package ngat.sound;

import java.applet.*;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Vector;

import ngat.util.logging.*;

/**
 * A Runnable to handle sound generation in a swing GUI.
 * The class should be used as follows:
 * <pre>
 * Thread t = null;
 * SoundThread st = null;
 * st = new SoundThread();
 * t = new Thread(st);
 * t.start();
 * st.load("hello",hello.wav);
 * st.load("bye",goodbye.wav);
 * st.play("hello");
 * st.play("bye");
 * st.beep();
 * </pre>
 * Note all loading and playing of wavs is done in a different thread to the one issuing the play sequence.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class SoundThread implements Runnable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: SoundThread.java,v 1.1 2003-09-19 14:59:14 cjm Exp $");
	/**
	 * A boolean deciding when to stop the Runnable.
	 * @see #quit
	 */
	protected boolean quit;
	/**
	 * A list of loaded audio clips that can be played.
	 */
	protected List audioClipList = null;
	/**
	 * A list of URL's of audio clips to load.
	 */
	protected List audioClipLoadList = null;
	/**
	 * A list of names of audio clips to play.
	 */
	protected List audioClipPlayList = null;
	/**
	 * The logger to log messages to.
	 */
	protected Logger logger = null;

	/**
	 * Default constructor.
	 * @see #logger
	 * @see #audioClipList
	 * @see #audioClipLoadList
	 * @see #audioClipPlayList
	 */
	public SoundThread()
	{
		super();
		logger = LogManager.getLogger(this);
		audioClipList = new Vector();
		audioClipLoadList = new Vector();
		audioClipPlayList = new Vector();
	}

	/**
	 * Load a sound clip from the specified URL.
	 * Associate the given name with it.Actually adds it to audioClipLoadList,
	 * to be loaded in the audio thread.
	 * @param name The name to give the audio clip.
	 * @param url The URL to load the clip from.
	 * @see #audioClipLoadList
	 */
	public void load(String name,URL url)
	{
		SoundThreadAudioClip stac = null;

		logger.log(1,"load("+name+","+url+")");
		stac = new SoundThreadAudioClip();
		stac.setName(name);
		stac.setURL(url);
		synchronized(audioClipLoadList)
		{
			audioClipLoadList.add(stac);
		}
		synchronized(this)
		{
			notify();
		}
	}

	/**
	 * Load a sound clip from the specified file.
	 * Associate the given name with it. Actually adds it to audioClipLoadList,
	 * to be loaded in the audio thread.
	 * @param name The name to give the audio clip.
	 * @param file The File to load the clip from.
	 * @see #audioClipLoadList
	 * @exception MalformedURLException Thrown if the file can't be comverted to a suitable URL.
	 */
	public void load(String name,File file) throws MalformedURLException
	{
		SoundThreadAudioClip stac = null;
		URL url = null;

		logger.log(1,"load("+name+","+file+")");
		url = file.toURL();
		logger.log(2,"load("+name+","+url+")");
		stac = new SoundThreadAudioClip();
		stac.setName(name);
		stac.setURL(url);
		synchronized(audioClipLoadList)
		{
			audioClipLoadList.add(stac);
		}
		synchronized(this)
		{
			notify();
		}
	}

	/**
	 * Play a previously loaded audio clip.
	 * Actually adds it to audioClipPlayList, to be played in the audio thread.
	 * @param name The name of a previously <i>load</i>ed clip to play, or "beep".
	 * @see #audioClipPlayList
	 */
	public void play(String name)
	{
		logger.log(1,"play("+name+")");
		synchronized(audioClipPlayList)
		{
			audioClipPlayList.add(name);
		}
		synchronized(this)
		{
			notify();
		}
	}

	/**
	 * Play a beep.
	 * Actually adds it to audioClipPlayList, to be played in the audio thread.
	 * @see #audioClipPlayList
	 */
	public void beep()
	{
		logger.log(1,"beep()");
		synchronized(audioClipPlayList)
		{
			audioClipPlayList.add("beep");
		}
		synchronized(this)
		{
			notify();
		}
	}

	/**
	 * Run method. Should be run from a different thread to any GUI activities,
	 * so sounds to not stop the GUI working.
	 * Runs until told to quit.
	 * <ul>
	 * <li>Enter a loop until <i>done</i> is true.
	 * <li><i>wait</i>s to be notified by a load,play or quit command.
	 * <li>Looks for any new clips to load in the audioClipLoadList, loads them and adds them to audioClipList.
	 * <li>Looks for any new clips to play in audioClipPlayList, finds the relevant clip in audioClipList
	 *     and plays it.
	 * </ul>
	 * @see #quit
	 * @see #audioClipList
	 * @see #audioClipLoadList
	 * @see #audioClipPlayList
	 */
	public void run()
	{
		SoundThreadAudioClip stac = null;
		String name = null;
		int count,index;

		logger.log(1,"run(start)");
		while(quit == false)
		{
			logger.log(1,"run(wait)");
			// wait for something to do.
			synchronized(this)
			{
				try
				{
					wait();
				}
				catch (InterruptedException e)
				{
					// diddly use logger?
				}
			}
			//
			// load any audio clips to be loaded
			//
			logger.log(1,"run(load audio clips)");
			synchronized(audioClipLoadList)
			{
				count = audioClipLoadList.size();
			}
			logger.log(2,"run:number of load clips:"+count);
			while(count > 0)
			{
				// get the first audio clip in the load list, and remove it from the list.
				synchronized(audioClipLoadList)
				{
					stac = (SoundThreadAudioClip)audioClipLoadList.get(0);
					audioClipLoadList.remove(0);
				}
				logger.log(3,"run:trying to load clip:"+stac);
				// try to load the clip
				stac.load();
				logger.log(3,"run:loaded clip:"+stac);
				// add it to the list of loaded clips.
				synchronized(audioClipList)
				{
					audioClipList.add(stac);
				}
				// find out if we have more clips to load
				synchronized(audioClipLoadList)
				{
					count = audioClipLoadList.size();
				}
				logger.log(4,"run:number of load clips:"+count);
			}// end while loadind clips
			//
			// play any audio clips to be played
			//
			logger.log(1,"run(play audio clips)");
			synchronized(audioClipPlayList)
			{
				count = audioClipPlayList.size();
			}
			logger.log(2,"run:number of play clips:"+count);
			while(count > 0)
			{
				// get the first audio clip in the list, and remove it from the list.
				synchronized(audioClipPlayList)
				{
					name = (String)(audioClipPlayList.get(0));
					audioClipPlayList.remove(0);
				}
				logger.log(3,"run:trying to find clip:"+name);
				if(name ==null)
				{
					logger.log(4,"run:name was null:ignoring.");
				}
				else if(name.equals("beep"))
				{
					logger.log(3,"run:name was beep:beeping.");
					Toolkit.getDefaultToolkit().beep();
				}
				else
				{
					logger.log(3,"run:trying to find clip with name:"+name);
					stac = new SoundThreadAudioClip();
					stac.setName(name);
					synchronized(audioClipList)
					{
						index = audioClipList.indexOf(stac);
						logger.log(4,"run:clip:"+name+" has index "+index);
						if(index > -1)
							stac = (SoundThreadAudioClip)(audioClipList.get(index));
						else
							stac = null;
					}
					logger.log(3,"run:trying to play clip:"+stac);
					// try to play the clip
					if(stac != null)
					{
						stac.play();
						logger.log(3,"run:played clip:"+stac);
					}
					else
					{
						logger.log(3,"run:failed to play clip:"+stac);
					}
				}// end if
				// find out if we have more clips to play
				synchronized(audioClipPlayList)
				{
					count = audioClipPlayList.size();
				}
				logger.log(3,"run:number of play clips:"+count);
			}// end while playing clips
		}// end while done is false
		logger.log(1,"run(end)");
	}

	/**
	 * Quit the run method. Sets quit to true, and notifies.
	 * @see #quit
	 */
	public void stop()
	{
		quit = true;
		synchronized(this)
		{
			notify();
		}
	}

	/**
	 * Inner class describing a particular audio clip in use by this runnable.
	 */
	public class SoundThreadAudioClip
	{
		/**
		 * The name of the audio clip.
		 */
		protected String name = null;
		/**
		 * The URL of the audio clip.
		 */
		protected URL url = null;
		/**
		 * The actual audio clip to play.
		 */
		protected AudioClip audioClip = null;

		/**
		 * Set the name.
		 * @param s The name.
		 * @see #name
		 */
		public void setName(String s)
		{
			name = s;
		}

		/**
		 * Set the URL.
		 * @param u The url.
		 * @see #url
		 */
		public void setURL(URL u)
		{
			url = u;
		}

		/**
		 * Load the audio clip.
		 * @see #url
		 * @see #audioClip
		 * @see Applet#newAudioClip
		 */
		public void load()
		{
			audioClip = Applet.newAudioClip(url);
		}

		/**
		 * Return the audio clip.
		 */
		public AudioClip getClip()
		{
			return audioClip;
		}

		/**
		 * Plays the loaded audio clip.
		 * @see #audioClip
		 * @see Applet#newAudioClip
		 */
		public void play()
		{
			if(audioClip != null)
				audioClip.play();
		}

		/**
		 * Equals method. Comparator on name field.
		 * If o is not a SoundThreadAudioClip, return false.
		 * If this object and o's name is null, return true.
		 * Otherwise compare name field strings.
		 * @param o The other object.
		 * @return true if equal, else false.
		 */
		public boolean equals(Object o)
		{
			SoundThreadAudioClip otherSTAC = null;

			if(o instanceof SoundThreadAudioClip)
			{
				otherSTAC = (SoundThreadAudioClip)o;
				if(name != null)
					return name.equals(otherSTAC.name);
				else
					return (otherSTAC.name == null);
			}
			return false;
		}

		/**
		 * toString method.
		 * @see #name
		 * @see #url
		 * @see #audioClip
		 */
		public String toString()
		{
			return new String(this.getClass().getName()+":name = "+name+
					  ":url = "+url+":audioClip = "+audioClip);
		}
	}

}
//
// $Log: not supported by cvs2svn $
//
