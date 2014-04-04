// StringUtilites.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/util/StringUtilites.java,v 0.3 2001-07-10 16:55:40 cjm Exp $
package ngat.util;

import java.io.*;
import java.lang.*;

/**
 * Some useful string routines.
 * @version $Revision$
 * @author Chris Mottram
 */
public class StringUtilites
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");

	/**
	 * This method returns whether character <b>c</b> is in string <b>s</b>.
	 * It simply executes the following code:
	 * <pre>
	 * return (s.indexOf(c) > -1);
	 * </pre>
	 * @param s The string to look for the character in. This should be non-null.
	 * @param c The character to look for in the string.
	 * @return A boolean is returned, which is true if <b>c</b> is in string <b>s</b>,
	 * 	and false if it isn't.
	 */
	public static boolean characterInString(String s,char c)
	{
		return (s.indexOf(c) > -1);
	}

	/**
	 * This method returns whether a character in string <b>characters</b> is in string <b>s</b>.
	 * It simply checks each character in turn.
	 * @param s The string to look for the character in. This should be non-null.
	 * @param characters A string of character to look for in the string <b>s</b>. This should be non-null.
	 * @return A boolean is returned, which is true if a character in <b>characters</b> is in string <b>s</b>,
	 * 	and false if it isn't.
	 */
	public static boolean characterInString(String s,String characters)
	{
		int i = 0;
		boolean found = false;

		while((i < characters.length())&&(found == false))
		{
			found = (s.indexOf(characters.charAt(i)) > -1);
			i++;
		}
		return found;
	}

	/**
	 * This method is a more complicated version of String's replace method.
	 * This method returns a new string, which replaces any of the character in
	 * <b>oldCharacters</b> with the character <b>newCharacter</b>.
	 * @param s The string to replace character's in.
	 * @param oldCharacters A string, which is really a list of characters. Any occurance
	 * 	of any of the characters in this string in <b>s</b> are replaced with <b>newCharacter</b>.
	 * @param newCharacter The character that replaces any of the characters in <b>oldCharacters</b> in the
	 * 	string <b>s</b>.
	 * @return The new string is returned.
	 */
	public static String replace(String s,String oldCharacters, char newCharacter)
	{
		StringBuffer sb = null;

		sb = new StringBuffer(s);

		for(int i = 0; i < sb.length(); i++)
		{
			if(oldCharacters.indexOf(sb.charAt(i)) > -1)
				sb.setCharAt(i,newCharacter);
		}// end for on characters in string buffer

		return sb.toString();
	}

	/**
	 * This method changes the String <b>s</b>, by replacing any occurances of
	 * <b>searchString</b> within the string with <b>replaceString</b>.
	 * <b>Note:</b> This method will enter an infinite loop if <b>replaceString</b> includes
	 * <b>searchString</b> i.e. if <b>replaceString.indexOf(searchString) > -1</b>
	 * @param s The initial string to modify.
	 * @param searchString A string that this method searches for. Each occurance of <b>searchString</b>
	 * 	in <b>s</b> is replaced with <b>replaceString</b>.
	 * @param replaceString Every occurance of <b>searchString</b> in <b>s</b> is replaced with this string.
	 * @return The new string is returned.
	 */
	public static String replace(String s,String searchString, String replaceString)
	{
		String sString, eString;
		int sindex,searchStringLength;

		searchStringLength = searchString.length();
		sindex = s.indexOf(searchString);
		while(sindex > -1)
		{
			sString = s.substring(0,sindex);
			eString = s.substring(sindex+searchStringLength);
			s = new String(sString+replaceString+eString);
			sindex = s.indexOf(searchString);
		}
		return s;
	}

	/**
	 * This method returns a new string, which removes any of the characters in
	 * <b>oldCharacters</b>.
	 * @param s The string to remove characters from.
	 * @param oldCharacters A string, which is really a list of characters. Any occurance
	 * 	of any of the characters in this string in <b>s</b> are removed.
	 * @return The new string is returned.
	 */
	public static String remove(String s,String oldCharacters)
	{
		StringBuffer sb = null;
		int i;

		sb = new StringBuffer(s);
		i = 0;
		while(i < sb.length())
		{
		// does sb[i] exist in the olCharacters string?
			if(oldCharacters.indexOf(sb.charAt(i)) > -1)
			{
			// If it exists delete it. 
			// Note we now want to check index i again next time through the loop.
			// Note this will change the length() of sb.
				sb.deleteCharAt(i);
			}
			else
			{
			// We only increment i once we know it doesn't contain a character in oldCharacters.
				i++;
			}
		}// end for on characters in string buffer

		return sb.toString();
	}

	/**
	 * Method that returns <b>s</b>, padded out with spaces until it is length <b>length</b>.
	 * If the length of <b>s</b> is already greater than <b>length</b>,<b>s</b> is not truncated.
	 * @param s The string to pad with spaces.
	 * @param length The total length of the string to return.
	 * @return A new string of length <b>length</b>, which is <b>s</b> padded out with spaces.
	 * @see #pad(java.lang.String,int,boolean)
	 */
	public static String pad(String s,int length)
	{
		return pad(s,length,false);
	}

	/**
	 * Method that returns <b>s</b>, padded out with spaces until it is length <b>length</b>.
	 * The <b>truncate</b> parameter deals with the case when the length of <b>s</b> is already
	 * greater than <b>length</b>. This is done by creating a string buffer and calling another pad method
	 * with it.
	 * @param s The string to pad with spaces.
	 * @param length The total length of the string to return.
	 * @param truncate A boolean, which takes effect when the length of <b>s</b> is already
	 * 	greater than <b>length</b>. If this is the case and <b>truncate</b> is true,<b>s</b>
	 * 	is returned truncated otherwise <b>s</b> is returned 'as is'.
	 * @return A new string of length <b>length</b>, which is <b>s</b> padded out with spaces.
	 * @see #pad(java.lang.StringBuffer,int,boolean)
	 */
	public static String pad(String s,int length,boolean truncate)
	{
		StringBuffer sb = null;

		sb = new StringBuffer(s);

		pad(sb,length,truncate);
		return sb.toString();
	}

	/**
	 * Method that modifies <b>sb</b>, padded out with spaces until it is length <b>length</b>.
	 * The <b>truncate</b> parameter deals with the case when the length of <b>sb</b> is already
	 * greater than <b>length</b>.
	 * @param sb The string buffer to pad with spaces.
	 * @param length The total length of the string to return.
	 * @param truncate A boolean, which takes effect when the length of <b>sb</b> is already
	 * 	greater than <b>length</b>. If this is the case and <b>truncate</b> is true,<b>sb</b>
	 * 	is truncated otherwise <b>sb</b> is returned 'as is'.
	 */
	public static void pad(StringBuffer sb,int length,boolean truncate)
	{
		if(truncate && (sb.length() > length))
			sb.setLength(length);
		while(sb.length() < length)
			sb.append(' ');
	}

	/**
	 * This method creates a table row, with the elements in <b>stringArray</b>
	 * tabulated into columns of length <b>lengthArray</b>.
	 * e.g. <pre> tabulate({"a","letter a","this string is truncated"},{3,10,10});</pre>
	 * returns the string <pre>"a  letter a  this strin"</pre>.
	 * Note string elements that are longer than their length are truncated.
	 * @param stringArray A list of strings to put into each column.
	 * @param lengthArray A list of lengths, to pad each element to.
	 * @return A tabulated string is returned.
	 * @exception IllegalArgumentException Thrown if the length of the two array parameter's are not the same.
	 * @see #pad(java.lang.StringBuffer,int,boolean)
	 */
	public static String tabulate(String stringArray[], int lengthArray[]) throws IllegalArgumentException
	{
		StringBuffer sb = null;
		StringBuffer nsb = null;

		if(stringArray.length != lengthArray.length)
		{
			throw new IllegalArgumentException("StringUtilites:tabulate:"+
				"stringArray length ["+stringArray.length+
				"] and lengthArray length ["+lengthArray.length+"] don't match.");
		}
		sb = new StringBuffer();
		for(int i = 0; i < stringArray.length; i++)
		{
			nsb = new StringBuffer(stringArray[i]);
			pad(nsb,lengthArray[i],true);
			sb.append(nsb);
		}

		return sb.toString();
	}

	/**
	 * Method to get the last (leaf) element of a path. The path is separated by <b>separator</b>s.
	 * e.g. getLeaf("java.lang.Object",'.') returns "Object".
	 * @param s The string to search.
	 * @param separator The separator character.
	 * @return A string. If no separator's are found the string itself is returned. If one or more
	 * 	separator's are found, the string after the last of the separator's is returned.
	 */
	public static String getLeaf(String s,char separator)
	{
		int lastIndex;

		lastIndex = s.lastIndexOf(s,separator);
		if(lastIndex < 0)
			return s;
		return s.substring(lastIndex+1);
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 0.2  2000/10/06 14:38:25  cjm
// Added getLeaf method.
//
// Revision 0.1  2000/09/06 12:58:42  cjm
// initial revision.
//
//
