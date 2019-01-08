package ngat.message.OSS;

import java.io.*;
import java.util.*;

public class ItemDescriptor implements Serializable {

    private Serializable item;

    private Vector list;

    public ItemDescriptor(Serializable item) {
	this.item = item;
	list = new Vector();
    }
	
    public Serializable getItem() { return item; }

    public Vector getList() { return list; }

    public void add(ItemDescriptor item) {
	list.add(item);
    }

    public String toString() {
	return "ItemDesc: "+item+(list.isEmpty() ? "EMPTY" : ", Size="+list.size());
    }

}
