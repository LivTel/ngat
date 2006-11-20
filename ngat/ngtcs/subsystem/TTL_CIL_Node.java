package ngat.ngtcs.subsystem;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type TTL_CIL_Node.
 * <p>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.1 $
 */
public class TTL_CIL_Node
  implements ngat.net.cil.CIL_Node, java.io.Serializable
{
  /*=========================================================================*/
  /*                                                                         */
  /* CLASS FIELDS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id: TTL_CIL_Node.java,v 1.1 2006-11-20 14:42:25 cjm Exp $" );

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

  /*=========================================================================*/
  /*                                                                         */
  /* ENUMERATIONS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Master Control Process
   */
  public static TTL_CIL_Node E_CIL_MCP =
    new TTL_CIL_Node( "E_CIL_MCP", 1 );
 
  /**
   * Continuous Heartbeat (part of MCP package)
   */
  public static TTL_CIL_Node E_CIL_CHB =
    new TTL_CIL_Node( "E_CIL_CHB", 2 );
 
  /**
   * Un interruptable power supply Interface task 1
   */
  public static TTL_CIL_Node E_CIL_UI1 =
    new TTL_CIL_Node( "E_CIL_UI1", 3 );
 
  /**
   * Un interruptable power supply Interface task 2
   */
  public static TTL_CIL_Node E_CIL_UI2 =
    new TTL_CIL_Node( "E_CIL_UI2", 4 );
 
  /**
   * Addressable power switch Interface task 1
   */
  public static TTL_CIL_Node E_CIL_AI1 =
    new TTL_CIL_Node( "E_CIL_AI1", 5 );
 
  /**
   * Addressable power switch Interface task 2
   */
  public static TTL_CIL_Node E_CIL_AI2 =
    new TTL_CIL_Node( "E_CIL_AI2", 6 );
 
  /**
   * Addressable power switch Interface task 3
   */
  public static TTL_CIL_Node E_CIL_AI3 =
    new TTL_CIL_Node( "E_CIL_AI3", 7 );
 
  /**
   * Modem Interface Task
   */
  public static TTL_CIL_Node E_CIL_MIT =
    new TTL_CIL_Node( "E_CIL_MIT", 8 );
 
  /**
   * Master Command Broker
   */
  public static TTL_CIL_Node E_CIL_MCB =
    new TTL_CIL_Node( "E_CIL_MCB", 9 );
 
  /**
   * Status Database
   */
  public static TTL_CIL_Node E_CIL_SDB =
    new TTL_CIL_Node( "E_CIL_SDB", 10 );
 
  /**
   * Status database File Recovery task
   */
  public static TTL_CIL_Node E_CIL_SFR =
    new TTL_CIL_Node( "E_CIL_SFR", 11 );
 
  /**
   * Services PLC Task
   */
  public static TTL_CIL_Node E_CIL_SPT =
    new TTL_CIL_Node( "E_CIL_SPT", 12 );
 
  /**
   * Enclosure PLC Task
   */
  public static TTL_CIL_Node E_CIL_EPT =
    new TTL_CIL_Node( "E_CIL_EPT", 13 );
 
  /**
   * Enclosure PLC Simulator
   */
  public static TTL_CIL_Node E_CIL_EPS =
    new TTL_CIL_Node( "E_CIL_EPS", 14 );
 
  /**
   * Weather Monitoring System
   */
  public static TTL_CIL_Node E_CIL_WMS =
    new TTL_CIL_Node( "E_CIL_WMS", 15 );
 
  /**
   * Audio Visual System
   */
  public static TTL_CIL_Node E_CIL_AVS =
    new TTL_CIL_Node( "E_CIL_AVS", 16 );
 
  /**
   * Telescope Control System
   */
  public static TTL_CIL_Node E_CIL_TCS =
    new TTL_CIL_Node( "E_CIL_TCS", 17 );
 
  /**
   * Robotic Control System
   */
  public static TTL_CIL_Node E_CIL_RCS =
    new TTL_CIL_Node( "E_CIL_RCS", 18 );
 
  /**
   * Observatory Control System
   */
  public static TTL_CIL_Node E_CIL_OCS =
    new TTL_CIL_Node( "E_CIL_OCS", 19 );
 
  /**
   * Autoguider system
   */
  public static TTL_CIL_Node E_CIL_AGS =
    new TTL_CIL_Node( "E_CIL_AGS", 20 );
 
  /**
   * Autoguider guide packets
   */
  public static TTL_CIL_Node E_CIL_AGP =
    new TTL_CIL_Node( "E_CIL_AGP", 21 );
 
  /**
   * Autoguider guide process
   */
  public static TTL_CIL_Node E_CIL_AGG =
    new TTL_CIL_Node( "E_CIL_AGG", 22 );
 
  /**
   * Autoguider graphical interface
   */
  public static TTL_CIL_Node E_CIL_AGI =
    new TTL_CIL_Node( "E_CIL_AGI", 23 );
 
  /**
   * Engineering control Interface (session 0)
   */
  public static TTL_CIL_Node E_CIL_EI0 =
    new TTL_CIL_Node( "E_CIL_EI0", 24 );
 
  /**
   * Engineering control Interface (session 1)
   */
  public static TTL_CIL_Node E_CIL_EI1 =
    new TTL_CIL_Node( "E_CIL_EI1", 25 );
 
  /**
   * Engineering control Interface (session 2)
   */
  public static TTL_CIL_Node E_CIL_EI2 =
    new TTL_CIL_Node( "E_CIL_EI2", 26 );
 
  /**
   * Engineering control Interface (session 3)
   */
  public static TTL_CIL_Node E_CIL_EI3 =
    new TTL_CIL_Node( "E_CIL_EI3", 27 );
 
  /**
   * Engineering control Interface (session 4)
   */
  public static TTL_CIL_Node E_CIL_EI4 =
    new TTL_CIL_Node( "E_CIL_EI4", 28 );
 
  /**
   * Engineering Reporting Task (session 0)
   */
  public static TTL_CIL_Node E_CIL_ER0 =
    new TTL_CIL_Node( "E_CIL_ER0", 29 );
 
  /**
   * Engineering Reporting Task (session 1)
   */
  public static TTL_CIL_Node E_CIL_ER1 =
    new TTL_CIL_Node( "E_CIL_ER1", 30 );
 
  /**
   * Engineering Reporting Task (session 2)
   */
  public static TTL_CIL_Node E_CIL_ER2 =
    new TTL_CIL_Node( "E_CIL_ER2", 31 );
 
  /**
   * Engineering Reporting Task (session 3)
   */
  public static TTL_CIL_Node E_CIL_ER3 =
    new TTL_CIL_Node( "E_CIL_ER3", 32 );
 
  /**
   * Engineering Reporting Task (session 4)
   */
  public static TTL_CIL_Node E_CIL_ER4 =
    new TTL_CIL_Node( "E_CIL_ER4", 33 );
 
  /**
   * Azimuth ACN Comms In
   */
  public static TTL_CIL_Node E_CIL_AZC =
    new TTL_CIL_Node( "E_CIL_AZC", 34 );
 
  /**
   * Azimuth ACN Control Node
   */
  public static TTL_CIL_Node E_CIL_AZN =
    new TTL_CIL_Node( "E_CIL_AZN", 35 );
 
  /**
   * Azimuth ACN Comms Out
   */
  public static TTL_CIL_Node E_CIL_AZS =
    new TTL_CIL_Node( "E_CIL_AZS", 36 );
 
  /**
   * Azimuth ACN SDB Reporting
   */
  public static TTL_CIL_Node E_CIL_AZR =
    new TTL_CIL_Node( "E_CIL_AZR", 37 );
 
  /**
   * Azimuth ACN Test (formerly Simulator AZS)
   */
  public static TTL_CIL_Node E_CIL_AZT =
    new TTL_CIL_Node( "E_CIL_AZT", 38 );
 
  /**
   * Azimuth ACN Logger
   */
  public static TTL_CIL_Node E_CIL_AZL =
    new TTL_CIL_Node( "E_CIL_AZL", 39 );
 
  /**
   * Elevation ACN Comms In
   */
  public static TTL_CIL_Node E_CIL_ELC =
    new TTL_CIL_Node( "E_CIL_ELC", 40 );
 
  /**
   * Elevation ACN Control Node
   */
  public static TTL_CIL_Node E_CIL_ELN =
    new TTL_CIL_Node( "E_CIL_ELN", 41 );
 
  /**
   * Elevation ACN Comms Out
   */
  public static TTL_CIL_Node E_CIL_ELS =
    new TTL_CIL_Node( "E_CIL_ELS", 42 );
 
  /**
   * Elevation ACN SDB Reporting
   */
  public static TTL_CIL_Node E_CIL_ELR =
    new TTL_CIL_Node( "E_CIL_ELR", 43 );
 
  /**
   * Elevation ACN Test (formerly Simulator ELS)
   */
  public static TTL_CIL_Node E_CIL_ELT =
    new TTL_CIL_Node( "E_CIL_ELT", 44 );
 
  /**
   * Elevation ACN Logger
   */
  public static TTL_CIL_Node E_CIL_ELL =
    new TTL_CIL_Node( "E_CIL_ELL", 45 );
 
  /**
   * Cassegrain ACN Comms In
   */
  public static TTL_CIL_Node E_CIL_CSC =
    new TTL_CIL_Node( "E_CIL_CSC", 46 );
 
  /**
   * Cassegrain ACN Control Node
   */
  public static TTL_CIL_Node E_CIL_CSN =
    new TTL_CIL_Node( "E_CIL_CSN", 47 );
 
  /**
   * Cassegrain ACN Comms Out
   */
  public static TTL_CIL_Node E_CIL_CSS =
    new TTL_CIL_Node( "E_CIL_CSS", 48 );
 
  /**
   * Cassegrain ACN SDB Reporting
   */
  public static TTL_CIL_Node E_CIL_CSR =
    new TTL_CIL_Node( "E_CIL_CSR", 49 );
 
  /**
   * Cassegrain ACN Test (formerly Simulator CSS)
   */
  public static TTL_CIL_Node E_CIL_CST =
    new TTL_CIL_Node( "E_CIL_CST", 50 );
 
  /**
   * Cassegrain ACN Logger
   */
  public static TTL_CIL_Node E_CIL_CSL =
    new TTL_CIL_Node( "E_CIL_CSL", 51 );
 
  /**
   * Auxiliary (Optical) Mechanism Comms In
   */
  public static TTL_CIL_Node E_CIL_OMC =
    new TTL_CIL_Node( "E_CIL_OMC", 52 );
 
  /**
   * Auxiliary (Optical) Mechanism Control Node
   */
  public static TTL_CIL_Node E_CIL_OMN =
    new TTL_CIL_Node( "E_CIL_OMN", 53 );
 
  /**
   * Auxiliary (Optical) Mechanism Comms Out
   */
  public static TTL_CIL_Node E_CIL_OMS =
    new TTL_CIL_Node( "E_CIL_OMS", 54 );
 
  /**
   * Auxiliary (Optical) Mechanism SDB Reporting
   */
  public static TTL_CIL_Node E_CIL_OMR =
    new TTL_CIL_Node( "E_CIL_OMR", 55 );
 
  /**
   * Auxiliary (Optical) Mechanism Test (formerly AMS)
   */
  public static TTL_CIL_Node E_CIL_OMT =
    new TTL_CIL_Node( "E_CIL_OMT", 56 );
 
  /**
   * Auxiliary (Optical) Mechanism Logger
   */
  public static TTL_CIL_Node E_CIL_OML =
    new TTL_CIL_Node( "E_CIL_OML", 57 );
 
  /**
   * Primary Mirror Support Comms In
   */
  public static TTL_CIL_Node E_CIL_MSC =
    new TTL_CIL_Node( "E_CIL_MSC", 58 );
 
  /**
   * Primary Mirror Support Control Node
   */
  public static TTL_CIL_Node E_CIL_MSN =
    new TTL_CIL_Node( "E_CIL_MSN", 59 );
 
  /**
   * Primary Mirror Support Comms Out
   */
  public static TTL_CIL_Node E_CIL_MSS =
    new TTL_CIL_Node( "E_CIL_MSS", 60 );
 
  /**
   * Primary Mirror Support SDB Reporting
   */
  public static TTL_CIL_Node E_CIL_MSR =
    new TTL_CIL_Node( "E_CIL_MSR", 61 );
 
  /**
   * Primary Mirror Support Test
   */
  public static TTL_CIL_Node E_CIL_MST =
    new TTL_CIL_Node( "E_CIL_MST", 62 );
 
  /**
   * Primary Mirror Support Logger
   */
  public static TTL_CIL_Node E_CIL_MSL =
    new TTL_CIL_Node( "E_CIL_MSL", 63 );
 
  /**
   * Test Unit 0 (for testing
   */
  public static TTL_CIL_Node E_CIL_TU0 =
    new TTL_CIL_Node( "E_CIL_TU0", 64 );
 
  /**
   * Test Unit 1 (for testing
   */
  public static TTL_CIL_Node E_CIL_TU1 =
    new TTL_CIL_Node( "E_CIL_TU1", 65 );
 
  /**
   * Test Unit 2 (for testing
   */
  public static TTL_CIL_Node E_CIL_TU2 =
    new TTL_CIL_Node( "E_CIL_TU2", 66 );
 
  /**
   * Test Unit 3 (for testing
   */
  public static TTL_CIL_Node E_CIL_TU3 =
    new TTL_CIL_Node( "E_CIL_TU3", 67 );
 
  /**
   * Test Unit 4 (for testing
   */
  public static TTL_CIL_Node E_CIL_TU4 =
    new TTL_CIL_Node( "E_CIL_TU4", 68 );
 
  /**
   * Test Unit 5 (for testing
   */
  public static TTL_CIL_Node E_CIL_TU5 =
    new TTL_CIL_Node( "E_CIL_TU5", 69 );
 
  /**
   * Test Unit 6 (for testing
   */
  public static TTL_CIL_Node E_CIL_TU6 =
    new TTL_CIL_Node( "E_CIL_TU6", 70 );
 
  /**
   * Test Unit 7 (for testing
   */
  public static TTL_CIL_Node E_CIL_TU7 =
    new TTL_CIL_Node( "E_CIL_TU7", 71 );
 
  /**
   * Test Unit 8 (for testing
   */
  public static TTL_CIL_Node E_CIL_TU8 =
    new TTL_CIL_Node( "E_CIL_TU8", 72 );
 
  /**
   * Test Unit 9 (for testing
   */
  public static TTL_CIL_Node E_CIL_TU9 =
    new TTL_CIL_Node( "E_CIL_TU9", 73 );
 
  /**
   * Test Echo Server (for testing only)
   */
  public static TTL_CIL_Node E_CIL_TES =
    new TTL_CIL_Node( "E_CIL_TES", 74 );
 
  /**
   * Test Message Broker (for testing only)
   */
  public static TTL_CIL_Node E_CIL_TMB =
    new TTL_CIL_Node( "E_CIL_TMB", 75 );
 
  /**
   * IUCAA Prototype TCS
   */
  public static TTL_CIL_Node E_CIL_IPT =
    new TTL_CIL_Node( "E_CIL_IPT", 76 );
 
  /**
   * Test Scripting Tool (for testing only)
   */
  public static TTL_CIL_Node E_CIL_TST =
    new TTL_CIL_Node( "E_CIL_TST", 77 );
 
  /**
   * System logging (syslogd)
   */
  public static TTL_CIL_Node E_CIL_LOG =
    new TTL_CIL_Node( "E_CIL_LOG", 78 );
 
  /**
   * Data Analysis Tool (session 0)
   */
  public static TTL_CIL_Node E_CIL_DA0 =
    new TTL_CIL_Node( "E_CIL_DA0", 79 );
 
  /**
   * Data Analysis Tool (session 1)
   */
  public static TTL_CIL_Node E_CIL_DA1 =
    new TTL_CIL_Node( "E_CIL_DA1", 80 );
 
  /**
   * Data Analysis Tool (session 2)
   */
  public static TTL_CIL_Node E_CIL_DA2 =
    new TTL_CIL_Node( "E_CIL_DA2", 81 );
 
  /**
   * Data Analysis Tool (session 3)
   */
  public static TTL_CIL_Node E_CIL_DA3 =
    new TTL_CIL_Node( "E_CIL_DA3", 82 );
 
  /**
   * Data Analysis Tool (session 4)
   */
  public static TTL_CIL_Node E_CIL_DA4 =
    new TTL_CIL_Node( "E_CIL_DA4", 83 );
 
  /**
   * Data Analysis Tool (session 5)
   */
  public static TTL_CIL_Node E_CIL_DA5 =
    new TTL_CIL_Node( "E_CIL_DA5", 84 );
 
  /**
   * Data Analysis Tool (session 6)
   */
  public static TTL_CIL_Node E_CIL_DA6 =
    new TTL_CIL_Node( "E_CIL_DA6", 85 );
 
  /**
   * Data Analysis Tool (session 7)
   */
  public static TTL_CIL_Node E_CIL_DA7 =
    new TTL_CIL_Node( "E_CIL_DA7", 86 );
 
  /**
   * Data Analysis Tool (session 8)
   */
  public static TTL_CIL_Node E_CIL_DA8 =
    new TTL_CIL_Node( "E_CIL_DA8", 87 );
 
  /**
   * Data Analysis Tool (session 9)
   */
  public static TTL_CIL_Node E_CIL_DA9 =
    new TTL_CIL_Node( "E_CIL_DA9", 88 );
 
  /**
   * Computer Monitoring Task (session 0)
   */
  public static TTL_CIL_Node E_CIL_CM0 =
    new TTL_CIL_Node( "E_CIL_CM0", 89 );
 
  /**
   * Computer Monitoring Task (session 1)
   */
  public static TTL_CIL_Node E_CIL_CM1 =
    new TTL_CIL_Node( "E_CIL_CM1", 90 );
 
  /**
   * Computer Monitoring Task (session 2)
   */
  public static TTL_CIL_Node E_CIL_CM2 =
    new TTL_CIL_Node( "E_CIL_CM2", 91 );
 
  /**
   * Computer Monitoring Task (session 3)
   */
  public static TTL_CIL_Node E_CIL_CM3 =
    new TTL_CIL_Node( "E_CIL_CM3", 92 );
 
  /**
   * Computer Monitoring Task (session 4)
   */
  public static TTL_CIL_Node E_CIL_CM4 =
    new TTL_CIL_Node( "E_CIL_CM4", 93 );
 
  /**
   * Computer Monitoring Task (session 5)
   */
  public static TTL_CIL_Node E_CIL_CM5 =
    new TTL_CIL_Node( "E_CIL_CM5", 94 );
 
  /**
   * Computer Monitoring Task (session 6)
   */
  public static TTL_CIL_Node E_CIL_CM6 =
    new TTL_CIL_Node( "E_CIL_CM6", 95 );
 
  /**
   * Computer Monitoring Task (session 7)
   */
  public static TTL_CIL_Node E_CIL_CM7 =
    new TTL_CIL_Node( "E_CIL_CM7", 96 );
 
  /**
   * Computer Monitoring Task (session 8)
   */
  public static TTL_CIL_Node E_CIL_CM8 =
    new TTL_CIL_Node( "E_CIL_CM8", 97 );
 
  /**
   * Computer Monitoring Task (session 9)
   */
  public static TTL_CIL_Node E_CIL_CM9 =
    new TTL_CIL_Node( "E_CIL_CM9", 98 );
 
  /**
   * Computer Control Task (session 0)
   */
  public static TTL_CIL_Node E_CIL_CC0 =
    new TTL_CIL_Node( "E_CIL_CC0", 99 );
 
  /**
   * Computer Control Task (session 1)
   */
  public static TTL_CIL_Node E_CIL_CC1 =
    new TTL_CIL_Node( "E_CIL_CC1", 100 );
 
  /**
   * Computer Control Task (session 2)
   */
  public static TTL_CIL_Node E_CIL_CC2 =
    new TTL_CIL_Node( "E_CIL_CC2", 101 );
 
  /**
   * Computer Control Task (session 3)
   */
  public static TTL_CIL_Node E_CIL_CC3 =
    new TTL_CIL_Node( "E_CIL_CC3", 102 );
 
  /**
   * Computer Control Task (session 4)
   */
  public static TTL_CIL_Node E_CIL_CC4 =
    new TTL_CIL_Node( "E_CIL_CC4", 103 );
 
  /**
   * Computer Control Task (session 5)
   */
  public static TTL_CIL_Node E_CIL_CC5 =
    new TTL_CIL_Node( "E_CIL_CC5", 104 );
 
  /**
   * Computer Control Task (session 6)
   */
  public static TTL_CIL_Node E_CIL_CC6 =
    new TTL_CIL_Node( "E_CIL_CC6", 105 );
 
  /**
   * Computer Control Task (session 7)
   */
  public static TTL_CIL_Node E_CIL_CC7 =
    new TTL_CIL_Node( "E_CIL_CC7", 106 );
 
  /**
   * Computer Control Task (session 8)
   */
  public static TTL_CIL_Node E_CIL_CC8 =
    new TTL_CIL_Node( "E_CIL_CC8", 107 );
 
  /**
   * Computer Control Task (session 9)
   */
  public static TTL_CIL_Node E_CIL_CC9 =
    new TTL_CIL_Node( "E_CIL_CC9", 108 );

  /**
   * Array to allow serialization.
   */
  protected final static TTL_CIL_Node[] array =
  {
    E_CIL_MCP,
    E_CIL_CHB,
    E_CIL_UI1,
    E_CIL_UI2,
    E_CIL_AI1,
    E_CIL_AI2,
    E_CIL_AI3,
    E_CIL_MIT,
    E_CIL_MCB,
    E_CIL_SDB,
    E_CIL_SFR,
    E_CIL_SPT,
    E_CIL_EPT,
    E_CIL_EPS,
    E_CIL_WMS,
    E_CIL_AVS,
    E_CIL_TCS,
    E_CIL_RCS,
    E_CIL_OCS,
    E_CIL_AGS,
    E_CIL_AGP,
    E_CIL_AGG,
    E_CIL_AGI,
    E_CIL_EI0,
    E_CIL_EI1,
    E_CIL_EI2,
    E_CIL_EI3,
    E_CIL_EI4,
    E_CIL_ER0,
    E_CIL_ER1,
    E_CIL_ER2,
    E_CIL_ER3,
    E_CIL_ER4,
    E_CIL_AZC,
    E_CIL_AZN,
    E_CIL_AZS,
    E_CIL_AZR,
    E_CIL_AZT,
    E_CIL_AZL,
    E_CIL_ELC,
    E_CIL_ELN,
    E_CIL_ELS,
    E_CIL_ELR,
    E_CIL_ELT,
    E_CIL_ELL,
    E_CIL_CSC,
    E_CIL_CSN,
    E_CIL_CSS,
    E_CIL_CSR,
    E_CIL_CST,
    E_CIL_CSL,
    E_CIL_OMC,
    E_CIL_OMN,
    E_CIL_OMS,
    E_CIL_OMR,
    E_CIL_OMT,
    E_CIL_OML,
    E_CIL_MSC,
    E_CIL_MSN,
    E_CIL_MSS,
    E_CIL_MSR,
    E_CIL_MST,
    E_CIL_MSL,
    E_CIL_TU0,
    E_CIL_TU1,
    E_CIL_TU2,
    E_CIL_TU3,
    E_CIL_TU4,
    E_CIL_TU5,
    E_CIL_TU6,
    E_CIL_TU7,
    E_CIL_TU8,
    E_CIL_TU9,
    E_CIL_TES,
    E_CIL_TMB,
    E_CIL_IPT,
    E_CIL_TST,
    E_CIL_LOG,
    E_CIL_DA0,
    E_CIL_DA1,
    E_CIL_DA2,
    E_CIL_DA3,
    E_CIL_DA4,
    E_CIL_DA5,
    E_CIL_DA6,
    E_CIL_DA7,
    E_CIL_DA8,
    E_CIL_DA9,
    E_CIL_CM0,
    E_CIL_CM1,
    E_CIL_CM2,
    E_CIL_CM3,
    E_CIL_CM4,
    E_CIL_CM5,
    E_CIL_CM6,
    E_CIL_CM7,
    E_CIL_CM8,
    E_CIL_CM9,
    E_CIL_CC0,
    E_CIL_CC1,
    E_CIL_CC2,
    E_CIL_CC3,
    E_CIL_CC4,
    E_CIL_CC5,
    E_CIL_CC6,
    E_CIL_CC7,
    E_CIL_CC8,
    E_CIL_CC9
  };

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

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

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Return an object reference of the TTL_CIL_Node with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the TTL_CIL_Node
   * @return a reference to the TTL_CIL_Node specified by the argument
   */
  public static TTL_CIL_Node getReference( String s )
  {
    return( (TTL_CIL_Node)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the TTL_CIL_Node with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the TTL_CIL_Node
   * @return a reference to the TTL_CIL_Node specified by the argument
   */
  public static TTL_CIL_Node getReference( int i )
  {
    return( (TTL_CIL_Node)( intHash.get( new Integer( i ) ) ) );
  }

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

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
  protected TTL_CIL_Node( String s )
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
  protected TTL_CIL_Node( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this TTL_CIL_Node.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this TTL_CIL_Node.
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
 *    $Date: 2006-11-20 14:42:25 $
 * $RCSfile: TTL_CIL_Node.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_CIL_Node.java,v $
 *      $Id: TTL_CIL_Node.java,v 1.1 2006-11-20 14:42:25 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:00:50  je
 *     Initial revision
 *
 *
 */
