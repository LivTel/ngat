package ngat.util;

import java.net.*;
import java.text.*;
import java.util.*;
import java.io.*;

import ngat.phase2.*;

/** Test class for automatically mailing PI when a Proposal is inserted in the PhaseII database.*/
public class SMTPMailer {

    /** Message text.*/
    protected String message;

    /** E-mail address for receiver.*/
    protected String recipient;
    
    public SMTPMailer(String recipient, String message) {
	this.message   = message;
	this.recipient = recipient;
	String line ="";
	try {
	    Socket sock = new Socket("ltccd1",25);
	    BufferedInputStream  in  = new BufferedInputStream(sock.getInputStream());
	    BufferedOutputStream out = new BufferedOutputStream(sock.getOutputStream());
	    recv(in);
	    send(out,"MAIL FROM: RoboticControl@ltccd1.livjm.ac.uk\r\n");
	    line = recv(in);
	    send(out,"RCPT TO:"+recipient+"\r\n");
	    line = recv(in);
	    send(out,"DATA\r\n");
	    line = recv(in);
	    send(out,"subject:mobile\r\n\r\n");
	    send(out,"** From Robotic Controller automatically generated notification **\r\n\r\n");	   
	    send(out,"** MESSAGE -"+message+"\r\n\r\n");
	    send(out,".\r\n"); 
	    line = recv(in); 
	} catch (IOException ie) {
	    System.out.println("Error: "+ie);
	    return;
	}
    }

   
    public void send(OutputStream out, String s) throws IOException{
	for (int i = 0; i < s.length(); i++) {
	    out.write((byte)s.charAt(i));
	}
	out.flush();
    }

    public String recv(InputStream in) throws IOException {
	String result = "";
	int c = in.read();
	while (c >= 0 && c !='\n') {
	    if (c != '\r') {
		result += (char)c;
	    }
	    c = in.read();
	}
	System.out.println("Received: "+result);
	return result;
    }

}
