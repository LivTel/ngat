package ngat.net.transaction;

import java.util.*;

public class TestHash {

    public static void main(String args[]) {


	// Make some TransactionIDs
	
	TransactionID a = new TransactionID(new ManagerID(333L), new MessageID(777L));
	TransactionID b = new TransactionID(new ManagerID(444L), new MessageID(666L));
	TransactionID c = new TransactionID(new ManagerID(555L), new MessageID(555L));
	TransactionID d = new TransactionID(new ManagerID(666L), new MessageID(555L));
	

	HashMap map = new HashMap();

	map.put(a, "The A thing");
	System.err.println("Placed: Key:"+a);
	map.put(b, "The B thing");
	System.err.println("Placed: Key:"+b);
	map.put(c, "The C thing");
	System.err.println("Placed: Key:"+c);
	map.put(d, "The D thing");
	System.err.println("Placed: Key:"+d);

	// More things.

	TransactionID a2 = new TransactionID(new ManagerID(333L), new MessageID(777L));
	TransactionID b2 = new TransactionID(new ManagerID(444L), new MessageID(666L));
	TransactionID c2 = new TransactionID(new ManagerID(555L), new MessageID(555L));
	TransactionID d2 = new TransactionID(new ManagerID(666L), new MessageID(555L));

	String sa = (String)map.get(a2);
	System.err.println("Found for Key: a2 ("+a2+") = ("+sa+")");

	String sb = (String)map.get(b2);
	System.err.println("Found for Key: b2 ("+b2+") = ("+sb+")");

	String sc = (String)map.get(c2);
	System.err.println("Found for Key: c2 ("+c2+") = ("+sc+")");

	String sd = (String)map.get(d2);
	System.err.println("Found for Key: d2 ("+d2+") = ("+sd+")");
    
	System.err.println("Map is: "+map);

    }

}
