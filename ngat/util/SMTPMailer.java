package ngat.util;

import java.net.*;
import java.text.*;
import java.util.*;
import java.io.*;

import ngat.phase2.*;

/** Test class for automatically mailing PI when a Proposal is inserted in the PhaseII database.*/
public class SMTPMailer {

    protected String recipient;

    protected Proposal proposal;

    public SMTPMailer(String recipient, Proposal proposal, String password) {
	this.recipient = recipient;
	this.proposal = proposal;
	String line ="";
	try {
	    Socket sock = new Socket("ltccd1",25);
	    BufferedInputStream in = new BufferedInputStream(sock.getInputStream());
	    BufferedOutputStream out = new BufferedOutputStream(sock.getOutputStream());
	    recv(in);
	    send(out,"MAIL FROM: OSS_Server@ltccd1.livjm.ac.uk\r\n");
	    line = recv(in);
	    send(out,"RCPT TO:"+recipient+"\r\n");
	    line = recv(in);
	    send(out,"DATA\r\n");
	    line = recv(in);
	    send(out,"subject:Your Proposal ["+proposal.getName()+"]\r\n\r\n");
	    send(out,"** OSS Server automatically generated notification **\r\n\r\n");
	    send(out,"** Your Proposal ["+proposal.getName()+"] has been entered and is now ready for editing **\r\n\r\n");
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String dateString = sdf.format(new Date(proposal.getActivationDate()));
	    send(out,"** The Proposal will become active for scheduling on "+dateString+" **\r\n\r\n");
	    send(out,"** The total allocated time is: "+proposal.getAllocatedTime()+" hrs **\r\n\r\n");
	    send(out,"** Divided as follows:**\r\n\r\n");
	    send(out,"** Bright Moon /  Good Seeing: "+proposal.getAllocatedFraction_BX()+" **\r\n\r\n");
	    send(out,"** Bright Moon / Avge Seeing: "+proposal.getAllocatedFraction_BA()+" **\r\n\r\n");
	    send(out,"** Bright Moon / Poor Seeing: "+proposal.getAllocatedFraction_BP()+" **\r\n\r\n");
	    send(out,"** Dark Moon /  Good Seeing: "+proposal.getAllocatedFraction_DX()+" **\r\n\r\n");
	    send(out,"** Dark Moon / Avge Seeing: "+proposal.getAllocatedFraction_DA()+" **\r\n\r\n");
	    send(out,"** Dark Moon / Poor Seeing: "+proposal.getAllocatedFraction_DP()+" **\r\n\r\n");	
	    send(out,"*********************************************\r\n\r\n");
	    send(out,"** The initial password is \""+password+"\" **\r\n\r\n");
	    send(out,"** You should change this before you start editing **\r\n\r\n");
	    send(out,"*********************************************\r\n\r\n");
	    send(out,"** THIS IS A TEST MESSAGE ** \r\n\r\n");
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
