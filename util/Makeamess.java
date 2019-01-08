package ngat.util;

import java.io.*;
import java.util.*;

/** Java version of makeamess utility for automatic generation of 
 * Java (TM) code for ngat.message subclasses from .obj description
 * files. Meta file format:-<br>
 * <table>
 * <tr><td>Meta line</td> <td> Example </td></tr>
 * <tr><td>#<header-description-text></td><td> #Description of class </td></tr>
 * <td>%<type>;<field-name>;[<values-list>];<description></td>
 * <td> %int;mode;{ OPEN | CLOSE | STOP };The mode setting </td></tr>
 * <td>$<const-name>;<const-description></td>
 * <td> $OPEN;The mechanism is in the OPEN state. </td></tr>
 * <td>*<done-param-name>;<done-param-description></td>
 * <td> *old_state;The previous state of the mechanism. </td></tr>
 * </table><br>
 * ## This utility is not completed only works for RCS_TCS. ##
 * <br>
 * $Id$
 */
public class Makeamess {

    protected String command;

    String line;

    String [] head = new String[20];
    String [] sort = new String[20];
    String [] name = new String[20];
    String [] vals = new String[20];
    String [] desc = new String[20];
    String [] cnam = new String[20];     
    String [] cdsc = new String[20];
    String [] dparm = new String[20];
    String [] ddesc = new String[20];
    String [] hand  = new String[50];
    String[] tokens = new String[6];

    int tcount = 0;
    int ndesc  = 0;
    int nparm  = 0;
    int nconst = 0;
    int ndparm = 0;
    int nhand  = 0;

    public static void main(String args[]) {
        if (args.length == 0) {
            usage();
            return;
        }
        // Get the name of the command.
        String command = args[0];
        new Makeamess(command);
    }

    public Makeamess(String command) {

        this.command = command;

        BufferedReader in;

        PrintWriter out;

        
        // Open the .mess file.
        try {
           in = new BufferedReader(new FileReader(command+".mess"));
        } catch (FileNotFoundException e) {
            log("Could not find .mess file: "+command+".mess");
            return;
        }

        // Create the .java file.
        try {
            out = new PrintWriter(new FileWriter(command+".java"));
        } catch (IOException e) {
            log("Could not create .java file: "+command+".java");
            return;
        }

        // Read the .mess file.
        try {
            // Read description lines.
            while ((line = in.readLine()) != null) {
                log("Read command description: "+line);
                if (line.startsWith("#")) {                  
                    tokenize(line);
                    head[ndesc] = tokens[0];
                    ndesc++;
                } 
                if (line.startsWith("%")) {                   
                    tokenize(line);
                    sort[nparm] = tokens[0];
                    name[nparm] = tokens[1];
                    vals[nparm] = tokens[2];
                    desc[nparm] = tokens[3];
                    nparm++;
                } 
                if (line.startsWith("$")) {           
                    tokenize(line);
                    cnam[nconst] = tokens[0];
                    cdsc[nconst] = tokens[1];
                    nconst++;
                } 
                if (line.startsWith("*")) {
                    tokenize(line);
                    dparm[ndparm] = tokens[0];
                    ddesc[ndparm] = tokens[1];
                    log("DONE:parm:"+dparm[ndparm]);
                    log("DONE:desc:"+ddesc[ndparm]);
                    ndparm++;
                }

                if (line.startsWith("!")) {
		    if (line.length() == 1) {
			hand[nhand] = "";
		    } else {
			hand[nhand] = line.substring(1);
		    }
                    nhand++;
                }
            }  // end while

        } catch (IOException e) {
            log("IO error reading file: "+command+".java");
            return;
        }



        // Generate the .java file.
        // Write the header and imports.
        out.println("package ngat.message.RCS_TCS;\n");
        out.println("import ngat.phase2.*;\n");

        // Write the class javadoc.
        out.println("/** TCS command: "+command+".<br>");
        for (int i = 0; i < ndesc; i++) {
            out.println(" * "+head[i]+"<br>");
        }

	out.println(" * <dl>");
        out.println(" * <dt>command params: </dt>");
	if (nparm != 0) {
	    for (int i = 0; i < nparm; i++) {
		out.println(" * <dd>"+name[i]+" - "+desc[i]+"</dd>");
		if (!vals[i].equals("")) {
		    out.println(" * <dd>&nbsp;values: "+vals[i]+"</dd>");
		}
	    }
	} else {
	    out.println(" *    none.");
	}

        out.println(" * <dt>done params: </dt>");
	if (ndparm != 0) {
	    for (int i = 0; i < ndparm; i++) {
		out.println(" * <dd>"+dparm[i]+" - "+ddesc[i]+"</dd>");
	    }
	} else {
	    out.println(" *    none.");
	}
	out.println(" * </dl>");

	out.println(" * <br>");
	out.println(" * $Id$");
	out.println(" */");
        out.println("public class "+command+" extends RCS_TO_TCS {\n");

        // Write the constants.
	if (nconst != 0) {
	    out.println("\t// Constants.\n");
	    for (int i = 0; i < nconst; i++) {
		out.println("\t/** Constant: Indicates "+cdsc[i]+"*/");
		out.println("\tpublic static final int "+cnam[i]+" = "+i+";\n");
	    }
	}

        // Write the variables.
	if (nparm != 0) {
	    out.println("\t// Variables.\n");
	    for (int i = 0; i < nparm; i++) {
		out.println("\t/** The "+desc[i]+"*/");
		out.println("\tprotected "+sort[i]+" "+name[i]+";\n");
	    }
	}

        // Write the constructor.
        out.println("\t/** Create a "+command+" with specified id.*/");
        out.println("\tpublic "+command+"(String id) { super(id); }\n");

        // Write the accessors.
        for (int i = 0; i < nparm; i++) {
            out.println("\t/** Set the "+desc[i]+"*/");
            out.println("\tpublic void set"+name[i].substring(0,1).toUpperCase()+
                     name[i].substring(1)+"("+sort[i]+" "+name[i]+
                     ") { this."+name[i]+" = "+name[i]+"; }\n");
            out.println("\t/** Get the "+desc[i]+"*/");
            out.println("\tpublic "+sort[i]+" get"+name[i].substring(0,1).toUpperCase()+
                     name[i].substring(1)+"() { return "+name[i]+"; }\n");
        }

        // Write the handcoded stuff.
        out.println("\t// Hand generated code.\n");
        for (int i = 0; i < nhand; i++) {
            out.println("\t//"+hand[i]);
        }
        // Write the end of class.
        out.println("} // class def. ["+command+"].\n");
        out.println("/** $Log: not supported by cvs2svn $ */");

        // Close the .java file.
        out.close();
       
    }

    /** Parse the meta text and divide into tokens.*/
    protected void tokenize(String text) {
        tcount = 0;
        StringTokenizer parser = new StringTokenizer(text, ";");
        while (parser.hasMoreTokens()) {
            tokens[tcount] = parser.nextToken();
            tcount++;
        }
        tokens[0] = tokens[0].substring(1);
    }

    public void log(String text) {
        System.out.println(text);
    }

    public static void usage() {
        System.out.println("Usage: java Makeamess <command-name> ");
    }

}


/** $Log: not supported by cvs2svn $ */
