package ngat.ngtcs.subsystem;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type TTL_Package.
 * <p>
 * Class defining allowable references of TTL_Package.  These are:
 * <ul>
 * <li>SYS</li>
 * <li>CIL</li>
 * <li>ACN</li>
 * <li>SHM</li>
 * <li>SCL</li>
 * <li>TFP</li>
 * <li>HHE</li>
 * <li>CMT</li>
 * <li>TCS</li>
 * <li>MCB</li>
 * <li>MCP</li>
 * <li>CHB</li>
 * <li>SDB</li>
 * <li>TIM</li>
 * <li>LOG</li>
 * <li>CLU</li>
 * <li>ECI</li>
 * <li>DAT</li>
 * <li>SFR</li>
 * <li>CFU</li>
 * <li>UIT</li>
 * <li>AIT</li>
 * <li>MIT</li>
 * <li>MIF</li>
 * <li>ERT</li>
 * <li>WMS</li>
 * <li>AGT</li>
 * <li>TMF</li>
 * <li>TNF</li>
 * <li>EPT</li>
 * <li>REP</li>
 * <li>CFL</li>
 * <li>VEN</li>
 * <li>AGN</li>
 * <li>AET</li>
 * <li>INC</li>
 * <li>CAN</li>
 * <li>SIF</li>
 * <li>NSC</li>
 * <li>NMC</li>
 * <li>ASC</li>
 * <li>AMC</li>
 * <li>MIR</li>
 * <li>HBA</li>
 * <li>NCI</li>
 * <li>NCO</li>
 * <li>SPT</li>
 * <li>TST</li>
 * <li>AGD</li>
 * <li>AGF</li>
 * <li>SFD</li>
 * <li>SFP</li>
 * <li>SMF</li>
 * <li>ACM</li>
 * <li>TMS</li>
 * <li>AFC</li>
 * <li>DSL</li>
 * <li>AMN</li>
 * <li>CBS</li>
 * <li>HTI</li>
 * <li>TQB</li>
 * <li>DF1</li>
 * <li>ETC</li>
 * <li>PMM</li>
 * <li>TQW</li>
 * <li>OEM</li>
 * <li>QMS</li>
 * <li>STL</li>
 * <li>STD</li>
 * <li>STI</li>
 * <li>AES</li>
 * <li>AEI</li>
 * <li>OID</li>
 * <li>OCI</li>
 * <li>OCO</li>
 * <li>AGI</li>
 * <li>AGC</li>
 * <li>AGG</li>
 * <li>AGS</li>
 * <li>CCT</li>
 * <li>AMS</li>
 * <li>GSC</li>
 * <li>PMS</li>
 * <li>CBI</li>
 * <li>TMU</li>
 * <li>OHG</li>
 * <li>IET</li>
 * <li>MML</li>
 * <li>TDL</li>
 * <li>HNF</li>
 * <li>PID</li>
 * <li>TSF</li>
 * <li>ASI</li>
 * <li>MBL</li>
 * <li>AML</li>
 * <li>HTG</li>
 * <li>MBS</li>
 * <li>MLP</li>
 * <li>SSE</li>
 * <li>AMT</li>
 * <li>AGA</li>
 * <li>ACI</li>
 * <li>MPU</li>
 * <li>MBT</li>
 * </ul>
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class TTL_Package implements java.io.Serializable
{
  /*=======================================================================*/
  /*                                                                       */
  /* CLASS FIELDS.                                                         */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String RevisionString =
    new String( "$Id: TTL_Package.java,v 1.1 2003-09-19 16:01:09 je Exp $" );

  /**
   * Hashtable of instances for retrieval by the enumeration's String name.
   */
  protected static java.util.Hashtable nameHash = new java.util.Hashtable();

  /**
   * Hashtable of instances for retrieval by the enumeration's int value.
   */
  protected static java.util.Hashtable intHash = new java.util.Hashtable();

  /**
   * Index of the next enumeration to be added.
   */
  protected static int nextIndex = 0;

  /*=======================================================================*/
  /*                                                                       */
  /* ENUMERATIONS.                                                         */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * Generic system messages
   */
  public static TTL_Package SYS =
    new TTL_Package( "SYS", 0x0001 );
 
  /**
   * Communications Interface Library
   */
  public static TTL_Package CIL =
    new TTL_Package( "CIL", 0x0002 );
 
  /**
   * Generic Axis Control Node Package
   */
  public static TTL_Package ACN =
    new TTL_Package( "ACN", 0x0003 );
 
  /**
   * Shared memory library
   */
  public static TTL_Package SHM =
    new TTL_Package( "SHM", 0x0004 );
 
  /**
   * Serial Communications Library
   */
  public static TTL_Package SCL =
    new TTL_Package( "SCL", 0x0005 );
 
  /**
   * Time and Frequency Process (IRIG B)
   */
  public static TTL_Package TFP =
    new TTL_Package( "TFP", 0x0006 );
 
  /**
   * Heidanhain Encoder package
   */
  public static TTL_Package HHE =
    new TTL_Package( "HHE", 0x0007 );
 
  /**
   * Computer Monitoring Task
   */
  public static TTL_Package CMT =
    new TTL_Package( "CMT", 0x0008 );
 
  /**
   * Telescope Control System
   */
  public static TTL_Package TCS =
    new TTL_Package( "TCS", 0x0009 );
 
  /**
   * Master Command Broker
   */
  public static TTL_Package MCB =
    new TTL_Package( "MCB", 0x000a );
 
  /**
   * Master Control Process
   */
  public static TTL_Package MCP =
    new TTL_Package( "MCP", 0x000b );
 
  /**
   * Continuous Heartbeats
   */
  public static TTL_Package CHB =
    new TTL_Package( "CHB", 0x000c );
 
  /**
   * Status Database
   */
  public static TTL_Package SDB =
    new TTL_Package( "SDB", 0x000d );
 
  /**
   * Time handling functions
   */
  public static TTL_Package TIM =
    new TTL_Package( "TIM", 0x000e );
 
  /**
   * Message reporting library (Syslog
   */
  public static TTL_Package LOG =
    new TTL_Package( "LOG", 0x000f );
 
  /**
   * Command Line (parsing) Utilities
   */
  public static TTL_Package CLU =
    new TTL_Package( "CLU", 0x0010 );
 
  /**
   * Engineering Control Interface
   */
  public static TTL_Package ECI =
    new TTL_Package( "ECI", 0x0011 );
 
  /**
   * Data Analysis Tool
   */
  public static TTL_Package DAT =
    new TTL_Package( "DAT", 0x0012 );
 
  /**
   * SDB File Retrieval process
   */
  public static TTL_Package SFR =
    new TTL_Package( "SFR", 0x0013 );
 
  /**
   * Configuration File Utilities
   */
  public static TTL_Package CFU =
    new TTL_Package( "CFU", 0x0014 );
 
  /**
   * UPS Interface Task
   */
  public static TTL_Package UIT =
    new TTL_Package( "UIT", 0x0015 );
 
  /**
   * Addressable power switch Interface Task
   */
  public static TTL_Package AIT =
    new TTL_Package( "AIT", 0x0016 );
 
  /**
   * (Callout) Modem Interface Task
   */
  public static TTL_Package MIT =
    new TTL_Package( "MIT", 0x0017 );
 
  /**
   * Motor Inter Face server
   */
  public static TTL_Package MIF =
    new TTL_Package( "MIF", 0x0018 );
 
  /**
   * Engineering interface Reporting Task
   */
  public static TTL_Package ERT =
    new TTL_Package( "ERT", 0x0019 );
 
  /**
   * Weather Monitoring System
   */
  public static TTL_Package WMS =
    new TTL_Package( "WMS", 0x001a );
 
  /**
   * Agent Server function Library
   */
  public static TTL_Package AGT =
    new TTL_Package( "AGT", 0x001b );
 
  /**
   * TTL Wrapper functions for QNX messaging functions
   */
  public static TTL_Package TMF =
    new TTL_Package( "TMF", 0x001c );
 
  /**
   * TTL Wrapper functions for QNX Process Name functions
   */
  public static TTL_Package TNF =
    new TTL_Package( "TNF", 0x001d );
 
  /**
   * Enclosure PLC PC Task
   */
  public static TTL_Package EPT =
    new TTL_Package( "EPT", 0x001e );
 
  /**
   * Node SDB reporter task.
   */
  public static TTL_Package REP =
    new TTL_Package( "REP", 0x001f );
 
  /**
   * Node Controller Fast Logger task.
   */
  public static TTL_Package CFL =
    new TTL_Package( "CFL", 0x0020 );
 
  /**
   * Virtual Encoder task.
   */
  public static TTL_Package VEN =
    new TTL_Package( "VEN", 0x0021 );
 
  /**
   * Agent Node Task.
   */
  public static TTL_Package AGN =
    new TTL_Package( "AGN", 0x0022 );
 
  /**
   * Absolute Encoder Task
   */
  public static TTL_Package AET =
    new TTL_Package( "AET", 0x0023 );
 
  /**
   * Inclinometer monitor
   */
  public static TTL_Package INC =
    new TTL_Package( "INC", 0x0024 );
 
  /**
   * CAN bus controller
   */
  public static TTL_Package CAN =
    new TTL_Package( "CAN", 0x0025 );
 
  /**
   * Support interface (for primary mirror control)
   */
  public static TTL_Package SIF =
    new TTL_Package( "SIF", 0x0026 );
 
  /**
   * Node State Controller
   */
  public static TTL_Package NSC =
    new TTL_Package( "NSC", 0x0027 );
 
  /**
   * Node Motion Controller
   */
  public static TTL_Package NMC =
    new TTL_Package( "NMC", 0x0028 );
 
  /**
   * Axis Stop Controller
   */
  public static TTL_Package ASC =
    new TTL_Package( "ASC", 0x0029 );
 
  /**
   * Axis Motion Controller
   */
  public static TTL_Package AMC =
    new TTL_Package( "AMC", 0x002a );
 
  /**
   * Mirror Cell Controller
   */
  public static TTL_Package MIR =
    new TTL_Package( "MIR", 0x002b );
 
  /**
   * Heart Beat Agent for ACN & AMN Nodes
   */
  public static TTL_Package HBA =
    new TTL_Package( "HBA", 0x002c );
 
  /**
   * Node Communications In Task for ACN nodes
   */
  public static TTL_Package NCI =
    new TTL_Package( "NCI", 0x002d );
 
  /**
   * Node Communications Out Task for ACN nodes
   */
  public static TTL_Package NCO =
    new TTL_Package( "NCO", 0x002e );
 
  /**
   * Services PLC Task
   */
  public static TTL_Package SPT =
    new TTL_Package( "SPT", 0x002f );
 
  /**
   * Test Script Parsing Tool
   */
  public static TTL_Package TST =
    new TTL_Package( "TST", 0x0030 );
 
  /**
   * Autoguider Deployment
   */
  public static TTL_Package AGD =
    new TTL_Package( "AGD", 0x0031 );
 
  /**
   * Autoguider Focus
   */
  public static TTL_Package AGF =
    new TTL_Package( "AGF", 0x0032 );
 
  /**
   * Science Fold Deployment
   */
  public static TTL_Package SFD =
    new TTL_Package( "SFD", 0x0033 );
 
  /**
   * Science Fold Position
   */
  public static TTL_Package SFP =
    new TTL_Package( "SFP", 0x0034 );
 
  /**
   * Secondary Mirror Focus
   */
  public static TTL_Package SMF =
    new TTL_Package( "SMF", 0x0035 );
 
  /**
   * ACADS Mechanism
   */
  public static TTL_Package ACM =
    new TTL_Package( "ACM", 0x0036 );
 
  /**
   * Temperature Monitoring System
   */
  public static TTL_Package TMS =
    new TTL_Package( "TMS", 0x0037 );
 
  /**
   * Autoguider Filter Control
   */
  public static TTL_Package AFC =
    new TTL_Package( "AFC", 0x0038 );
 
  /**
   * Dark Slide
   */
  public static TTL_Package DSL =
    new TTL_Package( "DSL", 0x0039 );
 
  /**
   * Auxiliary Mechanism Node
   */
  public static TTL_Package AMN =
    new TTL_Package( "AMN", 0x003a );
 
  /**
   * Can Bus Server (will supersede CAN package id later)
   */
  public static TTL_Package CBS =
    new TTL_Package( "CBS", 0x003b );
 
  /**
   * Header To ID utility
   */
  public static TTL_Package HTI =
    new TTL_Package( "HTI", 0x003c );
 
  /**
   * TTL QNX Build Files
   */
  public static TTL_Package TQB =
    new TTL_Package( "TQB", 0x003d );
 
  /**
   * Allen Bradley DF1 Protocol Library
   */
  public static TTL_Package DF1 =
    new TTL_Package( "DF1", 0x003e );
 
  /**
   * Configuration Files
   */
  public static TTL_Package ETC =
    new TTL_Package( "ETC", 0x003f );
 
  /**
   * Process Management Module
   */
  public static TTL_Package PMM =
    new TTL_Package( "PMM", 0x0040 );
 
  /**
   * TTL QNX Wrapper Library
   */
  public static TTL_Package TQW =
    new TTL_Package( "TQW", 0x0041 );
 
  /**
   * Externally generated drivers
   */
  public static TTL_Package OEM =
    new TTL_Package( "OEM", 0x0042 );
 
  /**
   * QNX Monitoring System (sysmon)
   */
  public static TTL_Package QMS =
    new TTL_Package( "QMS", 0x0043 );
 
  /**
   * SDB 'testlist' utility
   */
  public static TTL_Package STL =
    new TTL_Package( "STL", 0x0044 );
 
  /**
   * SDB 'testdump' utility
   */
  public static TTL_Package STD =
    new TTL_Package( "STD", 0x0045 );
 
  /**
   * SDB 'testinject' utility
   */
  public static TTL_Package STI =
    new TTL_Package( "STI", 0x0046 );
 
  /**
   * Absolute Encoder (OID) Server
   */
  public static TTL_Package AES =
    new TTL_Package( "AES", 0x0047 );
 
  /**
   * Absolute Encoder (Hardware) Interface
   */
  public static TTL_Package AEI =
    new TTL_Package( "AEI", 0x0048 );
 
  /**
   * Oid Server for Az
   */
  public static TTL_Package OID =
    new TTL_Package( "OID", 0x0049 );
 
  /**
   * Node Communications Task In AMN
   */
  public static TTL_Package OCI =
    new TTL_Package( "OCI", 0x004a );
 
  /**
   * Node Communications Task Out AMN
   */
  public static TTL_Package OCO =
    new TTL_Package( "OCO", 0x004b );
 
  /**
   * Autoguider User Interface
   */
  public static TTL_Package AGI =
    new TTL_Package( "AGI", 0x004c );
 
  /**
   * Autoguider Camera Driver Task
   */
  public static TTL_Package AGC =
    new TTL_Package( "AGC", 0x004d );
 
  /**
   * Autoguider Guiding Task
   */
  public static TTL_Package AGG =
    new TTL_Package( "AGG", 0x004e );
 
  /**
   * Autoguider System Task
   */
  public static TTL_Package AGS =
    new TTL_Package( "AGS", 0x004f );
 
  /**
   * Computer Control Task
   */
  public static TTL_Package CCT =
    new TTL_Package( "CCT", 0x0050 );
 
  /**
   * Auxiliary Mechanism Node State Control Task
   */
  public static TTL_Package AMS =
    new TTL_Package( "AMS", 0x0051 );
 
  /**
   * Generic State Controller (OIDs for NSC
   */
  public static TTL_Package GSC =
    new TTL_Package( "GSC", 0x0052 );
 
  /**
   * Primary Mirror State Controller
   */
  public static TTL_Package PMS =
    new TTL_Package( "PMS", 0x0053 );
 
  /**
   * CAN bus interface
   */
  public static TTL_Package CBI =
    new TTL_Package( "CBI", 0x0054 );
 
  /**
   * Tape Modelling Utility
   */
  public static TTL_Package TMU =
    new TTL_Package( "TMU", 0x0055 );
 
  /**
   * Operational Hours Generator
   */
  public static TTL_Package OHG =
    new TTL_Package( "OHG", 0x0056 );
 
  /**
   * IUCAA Enclosure Interface Task
   */
  public static TTL_Package IET =
    new TTL_Package( "IET", 0x0057 );
 
  /**
   * Matrix Manipulation Library
   */
  public static TTL_Package MML =
    new TTL_Package( "MML", 0x0058 );
 
  /**
   * TTL Development Library
   */
  public static TTL_Package TDL =
    new TTL_Package( "TDL", 0x0059 );
 
  /**
   * H iNFinity servo controller
   */
  public static TTL_Package HNF =
    new TTL_Package( "HNF", 0x005a );
 
  /**
   * Proportional Integral Derivative servo controller
   */
  public static TTL_Package PID =
    new TTL_Package( "PID", 0x005b );
 
  /**
   * Test Script Tool Files
   */
  public static TTL_Package TSF =
    new TTL_Package( "TSF", 0x005c );
 
  /**
   * Node Agent Server Interface
   */
  public static TTL_Package ASI =
    new TTL_Package( "ASI", 0x005d );
 
  /**
   * MODBUS Interface Library
   */
  public static TTL_Package MBL =
    new TTL_Package( "MBL", 0x005e );
 
  /**
   * Axis Motion Controller Logger
   */
  public static TTL_Package AML =
    new TTL_Package( "AML", 0x005f );
 
  /**
   * HTI Tables Generation
   */
  public static TTL_Package HTG =
    new TTL_Package( "HTG", 0x0060 );
 
  /**
   * ModBus Server
   */
  public static TTL_Package MBS =
    new TTL_Package( "MBS", 0x0061 );
 
  /**
   * MicroLynx Programs
   */
  public static TTL_Package MLP =
    new TTL_Package( "MLP", 0x0062 );
 
  /**
   * SDB String Encoding library
   */
  public static TTL_Package SSE =
    new TTL_Package( "SSE", 0x0063 );
 
  /**
   * Axis Motion Test Tool (formerly known as AIT RDL)
   */
  public static TTL_Package AMT =
    new TTL_Package( "AMT", 0x0064 );
 
  /**
   * Autoguider Artificial Images (images and generation)
   */
  public static TTL_Package AGA =
    new TTL_Package( "AGA", 0x0065 );
 
  /**
   * Autoguider Camera Interface (used to test Agc and Agg)
   */
  public static TTL_Package ACI =
    new TTL_Package( "ACI", 0x0066 );
 
  /**
   * MicroLynx Programming Utility
   */
  public static TTL_Package MPU =
    new TTL_Package( "MPU", 0x0067 );
 
  /**
   * Multi function Board Test utility
   */
  public static TTL_Package MBT =
    new TTL_Package( "MBT", 0x0068 );


  /**
   * Array to allow serialization.
   */
  protected final static TTL_Package[] array =
  {
    SYS,
    CIL,
    ACN,
    SHM,
    SCL,
    TFP,
    HHE,
    CMT,
    TCS,
    MCB,
    MCP,
    CHB,
    SDB,
    TIM,
    LOG,
    CLU,
    ECI,
    DAT,
    SFR,
    CFU,
    UIT,
    AIT,
    MIT,
    MIF,
    ERT,
    WMS,
    AGT,
    TMF,
    TNF,
    EPT,
    REP,
    CFL,
    VEN,
    AGN,
    AET,
    INC,
    CAN,
    SIF,
    NSC,
    NMC,
    ASC,
    AMC,
    MIR,
    HBA,
    NCI,
    NCO,
    SPT,
    TST,
    AGD,
    AGF,
    SFD,
    SFP,
    SMF,
    ACM,
    TMS,
    AFC,
    DSL,
    AMN,
    CBS,
    HTI,
    TQB,
    DF1,
    ETC,
    PMM,
    TQW,
    OEM,
    QMS,
    STL,
    STD,
    STI,
    AES,
    AEI,
    OID,
    OCI,
    OCO,
    AGI,
    AGC,
    AGG,
    AGS,
    CCT,
    AMS,
    GSC,
    PMS,
    CBI,
    TMU,
    OHG,
    IET,
    MML,
    TDL,
    HNF,
    PID,
    TSF,
    ASI,
    MBL,
    AML,
    HTG,
    MBS,
    MLP,
    SSE,
    AMT,
    AGA,
    ACI,
    MPU,
    MBT
  };

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * String name of this type-safe enumeration.
   */
  protected transient String name;

  /**
   * Optional integer for int representation of this Type-safe Enumeration.
   */
  protected transient int intValue;

  /**
   * Assign an index to this enumeration.
   */
  protected final int index = nextIndex++;

  /*=======================================================================*/
  /*                                                                       */
  /* CLASS METHODS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * Return an object reference of the TTL_Package with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the TTL_Package
   * @return a reference to the TTL_Package specified by the argument
   */
  public static TTL_Package getReference( String s )
  {
    return( (TTL_Package)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the TTL_Package with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the TTL_Package
   * @return a reference to the TTL_Package specified by the argument
   */
  public static TTL_Package getReference( int i )
  {
    return( (TTL_Package)( intHash.get( new Integer( i ) ) ) );
  }

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT METHODS.                                                       */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * Create an enumeration of the specified name.
   * <p>
   * <b>NOTE:</b> the <code><b>int</b></code> representation of this
   * enumeration is assigned to the index (index) of this enumeration in
   * the array.
   * @param s the name of this enumeration
   * @see #name
   * @see #intValue
   * @see #array
   */
  protected TTL_Package( String s )
  {
    name = s;
    nameHash.put( s, this );
    intValue = index;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Create an enumeration of the specified name and int representation.
   * @param s the name of this enumeration
   * @param i the int representation of this enumeration
   * @see #name
   * @see #intValue
   * @see #array
   */
  protected TTL_Package( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this TTL_Package.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this TTL_Package.
   * @return intValue
   * @see #intValue
   */
  public int getInt()
  {
    return intValue;
  }


  /**
   * Over-ride the Serializable method to ensure the same Object references
   * are returned after Serialization.
   */
  protected Object readResolve() throws java.io.ObjectStreamException
  {
    return( array[ index ] );
  }


  /**
   * Return the name of this enumeration.
   * @return name
   * @see #name
   */
  public String toString()
  {
    return name;
  }
}
/*
 *    $Date: 2003-09-19 16:01:09 $
 * $RCSfile: TTL_Package.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_Package.java,v $
 *      $Id: TTL_Package.java,v 1.1 2003-09-19 16:01:09 je Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
