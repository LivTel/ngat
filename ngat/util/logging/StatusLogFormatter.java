package ngat.util.logging;

import ngat.util.*;

import java.io.*;

public interface StatusLogFormatter {

    /** Output details of cat to out.*/
    public void output(StatusCategory cat, PrintStream out);

}
