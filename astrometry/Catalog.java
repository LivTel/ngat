package ngat.astrometry;

import java.util.*;
import ngat.phase2.*;

public class Catalog {

    protected String name;

    protected HashMap targets;

    public Catalog(String name) {
	this.name = name;
	targets = new HashMap();
    }

    public String getCatalogName() { return name;}

    public void addTarget(ExtraSolarSource target) {
	targets.put(target.getName(), target);
    }

    public void removeTarget(String tname) {
	targets.remove(tname);
    }

    public ExtraSolarSource getTarget(String tname) {
	return (ExtraSolarSource)targets.get(tname);
    }

    public List listTargets() {
	
	List list = new Vector();
	Iterator it = targets.values().iterator();
	while (it.hasNext()) {
	    list.add(it.next());
	}
	return list;
    }

    public void clear() {
	targets.clear();
    }

    public int size() {
	return targets.size();
    }

}
