package ngat.util;

import java.util.*;
/** Utility to privide a text table generator. A StringBuffer is used internally
 * to create the table using the methods:- putLine(), hline() etc. The result can be
 * retrived for printing via getBuffer().
 */
public class Tabulator {
    
    /** Holds the table column widths.*/
    private int[] tabs;
    
    /** The title for the table (top line).*/
    private String title;
    
    /** Holds the internal (text) representation of the table.*/
    private StringBuffer buffer;
    
    /** Create a Tabulator with the specified title and tabwidths.
     * @param title The title.
     * @param tabs Array of table column widths.
     */
    public Tabulator(String title, int[] tabs) {
	this.title = title;
	this.tabs = tabs;  
	buffer = new StringBuffer();
	hline('=');
	putTitle(title);
	hline('=');
    }
    
    /** Add a row containing the specified string (and no column dividers).
     * @param t The text for this row.
     */
    public void putTitle(String t) {
	buffer.append("\n|"); 
	int count = 0;
	for (int i = 0; i < tabs.length; i++) {
	    count += tabs[i]+1;
	}
	count = count - t.length()-2;
	buffer.append(" "+t+spc(count)+"|");
    }
    
    /** Add a row containing the specified column entries.
     * @param words An array of column entries.
     */
    public void putLine(String[] words) {	   
	buffer.append("\n|");
	for (int i = 0; i < tabs.length; i++) {
	    if (i >= words.length) {
		buffer.append(spc(tabs[i])+"|");
	    } else {
		// Work out padding.
		int spare = tabs[i] - words[i].length();
		int padBefore = 0;
		int padAfter  = 0;
		if (spare <= 1) {
		    padBefore = 1;
		    padAfter  = 0;
		} else {
		    padBefore = 1;
		    padAfter  = spare - padBefore;
		}
		buffer.append(spc(padBefore)+truncate(words[i], tabs[i]-1)+spc(padAfter)+"|");
	    }	
	}    
    }
    
    /** Override to deal with 2-column Tables.
     * Calls putLine().
     * @param col1 Text for the First column.
     * @param col2 Text for the Second column.
     */
    public void putPair(String col1, String col2) {
	putLine(new String[] {col1, col2});
    }
    
    /** Add a seperator line using the specified character symbol.
     * @param c The character sysmbol.
     */
    public void hline(char c) {
	buffer.append("\n|");
	for (int i = 0; i < tabs.length; i++) {
	    int ll = tabs[i]+1;
	    if (i == tabs.length-1)
		ll = ll-1;
	    buffer.append(chars(c, ll));
	}
	buffer.append("|");
    }
    
    /** Return the internal representation as a String.*/
    public String getBuffer() {  return buffer.toString(); }
    
    /** Append a number of spaces to the current column entry.*/
    private String spc(int k) {
	return chars(' ', k);
    }
    
    /** Append a number of the specified char to the current column entry.*/
    private String chars(char c, int k) {
	StringBuffer temp = new StringBuffer();
	for (int i = 0; i < k; i++) {
	    temp.append(c);
	}
	return temp.toString();
    }
    
    /** Return the specified String suitably truncated to width w.*/
    private String truncate(String s, int w) {
	if (s.length() < w)
	    return s;
	else
	    return s.substring(0, w);
    }
           
}
