package ngat.util;

import java.util.*;

/** Encapsulates the process of parsing a command list to extract options.*/
public class CommandTokenizer {

    /** Default token delimiter.*/
    public static final String DEFAULT_DELIM = "-";

    /** Token delimiter.*/
    String delim;

    /** Records current token.*/
    int pos;

    /** Counts number of tokens.*/
    int nt;

    /** Records curent text parse position.*/

    /** Stores the tokens.*/
    ConfigurationProperties tokens;

    /** Arguments.*/
    String [] arglist;

    public static CommandTokenizer use(String delim) {
	CommandTokenizer ct = new CommandTokenizer(delim);
	return ct;
    }

    /** Create a new CommandTokenizer.*/
    public CommandTokenizer(String delim) {
	this.delim = delim;
    }
    
    /** Parse the supplied arglist.*/
    public ConfigurationProperties parse(String [] arglist) {

	this.arglist = arglist;

	tokens = new ConfigurationProperties();

	nt = arglist.length;
	pos = 0;

	tokens.clear();

	String token = null;

	while(moreTokens()) {

	    token = getNextToken();

	    //System.err.println("1; Got Token: "+token);

	    if (isKey(token)) {
		
		//System.err.println("2; IS KEY");

		if (moreTokens()) {
		    
		    //System.err.println("3; Are more");

		    if (isKey(nextToken())) {
			
			//System.err.println("4a; Saving as a switch: "+strip(token));
			// This token is a switch.
			
			if (isNegativeSwitch(token))		
			    tokens.put(strip(token), "false");
			else
			    tokens.put(strip(token), "true");
			
		    } else {
			

			String param = getNextToken();
			//System.err.println("4b; Saving: "+strip(token)+", "+param);
			
			// This token is a parameter.
			tokens.put(strip(token), param);
			
		    }
			
		} else {

		    //System.err.println("5; Saving Last token as a switch: "+strip(token));

		    // Last token is a switch.

		    if (isNegativeSwitch(token))		
			tokens.put(strip(token), "false");
		    else
			tokens.put(strip(token), "true");
		    
		}
		
	    } else {
		
		//System.err.println("6; Skipped unexpected token: "+token);
		
	    }
	    	    
	}

	return tokens;
	
    }

    /** Returns a string representation of the token map.*/
    public String toString() {

	StringBuffer buffer = new StringBuffer();
					       
	Iterator it = tokens.keySet().iterator();

	String key = null;

	while (it.hasNext()) {
	    
	    key = (String)it.next();
	    
	    buffer.append(key+" = "+tokens.get(key)+", ");
	    
	}

	return buffer.toString();

    }

    /** True if there are more tokens to process.*/
    protected boolean moreTokens() { return pos < nt; }

    /** Returns the next token without advancing the position counter.*/
    protected String nextToken() { return arglist[pos]; }

    /** Returns the next token and advances the position counter.*/
    protected String getNextToken() { return arglist[pos++]; }

    /** True if the token is a key (rather than a value).*/
    protected boolean isKey(String token) { return token.startsWith(delim); }

    /** Strips delimiter and negator off a token.*/
    protected String strip(String token) { 
	if (token.startsWith(delim)) 
	    if (token.startsWith(delim+"no-"))
		return token.substring((delim+"no-").length());
	    else
		return token.substring(delim.length());
	return token;
    }
    
    /** True if the token is negated.*/
    protected boolean isNegativeSwitch(String token) { 
	return token.startsWith(delim+"no-");
    }

    /** Returns the map of tokens and values.*/
    public ConfigurationProperties getMap() {return tokens; }

    /** Test using command line args. Prints the mapping out.*/
    public static void main(String args[]) {
	
	CommandTokenizer ct = new CommandTokenizer("--");

	ct.parse(args);
	
	System.err.println("CommandTokenizer: Output: ["+ct.toString()+"]");
	
    }

}
