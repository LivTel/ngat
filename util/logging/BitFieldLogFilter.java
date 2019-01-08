// BitFieldLogFilter.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/util/logging/BitFieldLogFilter.java,v 1.1 2007-02-28 06:17:00 snf Exp $
package ngat.util.logging;

/** 
 * This is an implementation of LogFilter.
 * It allows log records through based on a bit-wise comparison of their level against
 * a level set in the filter. If a bit matches the record is logged. Note this assumes
 * the Logger/LogHandler this filter is attached to has it's LogLevel set to Logger.ALL,
 * as here the level parameter is being used as a bit-wise comparator rather than an
 * absolute level.
 * @author Chris Mottram
 * @version $Revision$
 */
public class BitFieldLogFilter implements LogFilter
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * The level to filter log records against. If a bit set in this variable is also set
	 * in a LogRecord passed to isLoggable, the LogRecord is passed to the relevant handlers to
	 * be logged.
	 */
	protected int level = 0;

	/** 
	 * Create a BitFieldLogFilter with no bits set (will log nothing).
	 */
	public BitFieldLogFilter()
	{
		level = 0;
	}

	/** 
	 * Create a BitFieldLogFilter with bits set from l.
	 * @param l An integer with bits set, used to configure the level.
	 * @see #level
	 */
	public BitFieldLogFilter(int l)
	{
		level = l;
	}

	/**
	 * Set the level of this BitFieldLogFilter.
	 * @param l An integer with bits set, used to configure the level.
	 * @see #level
	 */
	public void setLevel(int l)
	{
		level = l;
	}

	/**
	 * Get the current level of this BitFieldLogFilter.
	 * @return An integer with the current level.
	 * @see #level
	 */
	public int getLevel()
	{
		return level;
	}

	/**
	 * Method to add the bits set in l to the set of bits in level.
	 * @param l A set of bits to add to level.
	 * @see #level
	 */
	public void addLevelBits(int l)
	{
		level |= l;
	}

	/**
	 * Method to delete the bits set in l from the set of bits in level.
	 * @param l A set of bits to delete from level.
	 * @see #level
	 */
	public void deleteLevelBits(int l)
	{
		level &= ~l;
	}

	/**
	 * Routine to determine whether a LogRecord is to be logged.
	 * @param record The LogRecord to be tested.
	 * @return The method returns true if a bit set in the LogRecord's level field is also
	 * 	set in the BitFieldLogFilter's level. Otherwise false is returned.
	 * @see #level
	 */
	public boolean isLoggable(LogRecord record)
	{
		return ((level & record.getLevel())>0);
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 1.1  2001/03/23 14:29:28  cjm
// Initial revision
//
//
