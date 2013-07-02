package ngat.net.telemetry;

import java.io.*;
import java.util.*;

public interface StatusCategory extends Serializable {

    /** @return The timestamp for this status.*/ 
    public long getStatusTimeStamp();


    /** @return the name of this status category.*/
    public String getCategoryName(); 

}