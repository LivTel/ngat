package ngat.ngtcs.subsystem.spt;

import ngat.ngtcs.subsystem.TTL_Package;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type SPT_DataType.  These are:
 * <ul>
 * <li>D_SPT_DATAID_BOL</li>
 * <li>D_SPT_PROC_STATE</li>
 * <li>D_SPT_AUTH_STATE</li>
 * <li>D_SPT_SYS_REQUEST</li>
 * <li>D_SPT_APP_VERSION</li>
 * <li>D_SPT_THIS_APPLICATION</li>
 * <li>D_SPT_THIS_TELESCOPE</li>
 * <li>D_SPT_SW_SIMULATE</li>
 * <li>D_SPT_DMD_BOL</li>
 * <li>D_SPT_DMD_OBS</li>
 * <li>D_SPT_DMD_SAFE</li>
 * <li>D_SPT_DMD_HYD_SYS</li>
 * <li>D_SPT_DMD_AZ_HYD</li>
 * <li>D_SPT_DMD_EL_HYD</li>
 * <li>D_SPT_DMD_AIR_CMP</li>
 * <li>D_SPT_DMD_SW_THTG</li>
 * <li>D_SPT_DMD_SW_AGD</li>
 * <li>D_SPT_DMD_SW_ALL_LTG</li>
 * <li>D_SPT_DMD_SW_ENC_LTG</li>
 * <li>D_SPT_DMD_SW_PRM_LTG</li>
 * <li>D_SPT_DMD_SW_WLA_LTG</li>
 * <li>D_SPT_DMD_SW_ITR_LTG</li>
 * <li>D_SPT_DMD_SW_AMN_PWR</li>
 * <li>D_SPT_DMD_SW_ALL_PWR</li>
 * <li>D_SPT_DMD_SW_PMC_PWR</li>
 * <li>D_SPT_DMD_SW_MCO_PWR</li>
 * <li>D_SPT_DMD_SW_ACC_PWR</li>
 * <li>D_SPT_DMD_SW_AZC_PWR</li>
 * <li>D_SPT_DMD_SW_ELC_PWR</li>
 * <li>D_SPT_DMD_SW_CAC_PWR</li>
 * <li>D_SPT_DMD_SW_AZS_PWR</li>
 * <li>D_SPT_DMD_SW_ELS_PWR</li>
 * <li>D_SPT_DMD_SW_CAS_PWR</li>
 * <li>D_SPT_DMD_SW_NDS_PWR</li>
 * <li>D_SPT_DMD_SW_NWS_PWR</li>
 * <li>D_SPT_DMD_SW_BBX_PWR</li>
 * <li>D_SPT_DMD_SW_CR1_PWR</li>
 * <li>D_SPT_DMD_SW_AXD_PWR</li>
 * <li>D_SPT_DMD_SW_SVR_PWR</li>
 * <li>D_SPT_DMD_MIR_COV</li>
 * <li>D_SPT_DMD_FLT</li>
 * <li>D_SPT_DMD_ESTOP</li>
 * <li>D_SPT_DMD_SW_SEC_PWR</li>
 * <li>D_SPT_DMD_SW_TMP_PWR</li>
 * <li>D_SPT_DMD_SW_CR2_PWR</li>
 * <li>D_SPT_DMD_SW_BBL_PWR</li>
 * <li>D_SPT_DMD_SW_BBF_PWR</li>
 * <li>D_SPT_DMD_SW_BBP_PWR</li>
 * <li>D_SPT_DMD_QRY_STATUS</li>
 * <li>D_SPT_DMD_EOL</li>
 * <li>D_SPT_STATUS_BOL</li>
 * <li>D_SPT_AZM_WD</li>
 * <li>D_SPT_ALT_WD</li>
 * <li>D_SPT_CAS_WD</li>
 * <li>D_SPT_AMN_WD</li>
 * <li>D_SPT_HYD_F1</li>
 * <li>D_SPT_HYD_F2</li>
 * <li>D_SPT_FA</li>
 * <li>D_SPT_SA</li>
 * <li>D_SPT_DR_ILK</li>
 * <li>D_SPT_ITR_OUT_DR</li>
 * <li>D_SPT_PRM_OUT_DR</li>
 * <li>D_SPT_PER_GT</li>
 * <li>D_SPT_LTG_LOC_OR</li>
 * <li>D_SPT_LTG_PRM</li>
 * <li>D_SPT_LTG_ENC</li>
 * <li>D_SPT_LTG_WLA</li>
 * <li>D_SPT_LTG_ITR</li>
 * <li>D_SPT_ESTOP_CTRS</li>
 * <li>D_SPT_SPLC_MODE</li>
 * <li>D_SPT_SPLC_STATE</li>
 * <li>D_SPT_SPLC_UPS</li>
 * <li>D_SPT_PWR_AZM</li>
 * <li>D_SPT_PWR_ALT</li>
 * <li>D_SPT_PWR_CAS</li>
 * <li>D_SPT_PWR_AMN</li>
 * <li>D_SPT_PWR_NDS</li>
 * <li>D_SPT_PWR_NWS</li>
 * <li>D_SPT_PWR_ACC</li>
 * <li>D_SPT_PWR_AZM_SW</li>
 * <li>D_SPT_PWR_ALT_SW</li>
 * <li>D_SPT_PWR_CAS_SW</li>
 * <li>D_SPT_CSE_SPLY</li>
 * <li>D_SPT_ENC_DRS</li>
 * <li>D_SPT_ENC_RSD</li>
 * <li>D_SPT_HTG_ENC_SEAL</li>
 * <li>D_SPT_MODE</li>
 * <li>D_SPT_PWR_AG</li>
 * <li>D_SPT_PWR_AXIS</li>
 * <li>D_SPT_PWR_CRY1</li>
 * <li>D_SPT_PWR_CRY2</li>
 * <li>D_SPT_PWR_LV</li>
 * <li>D_SPT_PWR_MAIN</li>
 * <li>D_SPT_PWR_MCO</li>
 * <li>D_SPT_PWR_MV</li>
 * <li>D_SPT_PWR_OFM</li>
 * <li>D_SPT_PWR_ONM</li>
 * <li>D_SPT_PWR_PMC</li>
 * <li>D_SPT_PWR_PRES</li>
 * <li>D_SPT_PWR_SEC</li>
 * <li>D_SPT_PWR_SRV</li>
 * <li>D_SPT_PWR_TEMP</li>
 * <li>D_SPT_SPLC_CONTROL</li>
 * <li>D_SPT_AZM_LIMITS</li>
 * <li>D_SPT_ALT_LIMITS</li>
 * <li>D_SPT_CAS_LIMITS</li>
 * <li>D_SPT_AZM_M1_TEMP</li>
 * <li>D_SPT_AZM_M1_TEMP_VAL</li>
 * <li>D_SPT_AZM_M2_TEMP</li>
 * <li>D_SPT_AZM_M2_TEMP_VAL</li>
 * <li>D_SPT_ALT_M1_TEMP</li>
 * <li>D_SPT_ALT_M1_TEMP_VAL</li>
 * <li>D_SPT_ALT_M2_TEMP</li>
 * <li>D_SPT_ALT_M2_TEMP_VAL</li>
 * <li>D_SPT_CAS_M1_TEMP</li>
 * <li>D_SPT_CAS_M1_TEMP_VAL</li>
 * <li>D_SPT_CAS_M2_TEMP</li>
 * <li>D_SPT_CAS_M2_TEMP_VAL</li>
 * <li>D_SPT_AMP_RK_TEMP</li>
 * <li>D_SPT_AMP_RK_TEMP_VAL</li>
 * <li>D_SPT_SPARE_2_TEMP</li>
 * <li>D_SPT_SPARE_2_TEMP_VAL</li>
 * <li>D_SPT_SPARE_3_TEMP</li>
 * <li>D_SPT_SPARE_3_TEMP_VAL</li>
 * <li>D_SPT_SPARE_4_TEMP</li>
 * <li>D_SPT_SPARE_4_TEMP_VAL</li>
 * <li>D_SPT_AMN_TEMP</li>
 * <li>D_SPT_AMN_TEMP_VAL</li>
 * <li>D_SPT_CHK_PNL_TEMP</li>
 * <li>D_SPT_CHK_PNL_TEMP_VAL</li>
 * <li>D_SPT_AG_TEMP</li>
 * <li>D_SPT_AG_TEMP_VAL</li>
 * <li>D_SPT_SPARE_8_TEMP</li>
 * <li>D_SPT_SPARE_8_TEMP_VAL</li>
 * <li>D_SPT_ENC_AMB_TEMP</li>
 * <li>D_SPT_ENC_AMB_TEMP_VAL</li>
 * <li>D_SPT_HTG_TT1_TEMP</li>
 * <li>D_SPT_HTG_TT1_TEMP_VAL</li>
 * <li>D_SPT_HTG_TT2_TEMP</li>
 * <li>D_SPT_HTG_TT2_TEMP_VAL</li>
 * <li>D_SPT_HTG_TT3_TEMP</li>
 * <li>D_SPT_HTG_TT3_TEMP_VAL</li>
 * <li>D_SPT_HTG_TT4_TEMP</li>
 * <li>D_SPT_HTG_TT4_TEMP_VAL</li>
 * <li>D_SPT_HTG_TT5_TEMP</li>
 * <li>D_SPT_HTG_TT5_TEMP_VAL</li>
 * <li>D_SPT_HTG_TT6_TEMP</li>
 * <li>D_SPT_HTG_TT6_TEMP_VAL</li>
 * <li>D_SPT_HYD_CHL_TEMP</li>
 * <li>D_SPT_HYD_CHL_TEMP_VAL</li>
 * <li>D_SPT_HYD_FT1_FLOW</li>
 * <li>D_SPT_HYD_FT1_FLOW_VAL</li>
 * <li>D_SPT_HYD_FT2_FLOW</li>
 * <li>D_SPT_HYD_FT2_FLOW_VAL</li>
 * <li>D_SPT_HYD_FT3_FLOW</li>
 * <li>D_SPT_HYD_FT3_FLOW_VAL</li>
 * <li>D_SPT_HYD_FT4_FLOW</li>
 * <li>D_SPT_HYD_FT4_FLOW_VAL</li>
 * <li>D_SPT_HYD_MAIN_LVL</li>
 * <li>D_SPT_HYD_MAIN_LVL_VAL</li>
 * <li>D_SPT_HYD_PT1A_PRES</li>
 * <li>D_SPT_HYD_PT1A_PRES_VAL</li>
 * <li>D_SPT_HYD_PT1B_PRES</li>
 * <li>D_SPT_HYD_PT1B_PRES_VAL</li>
 * <li>D_SPT_HYD_PT1C_PRES</li>
 * <li>D_SPT_HYD_PT1C_PRES_VAL</li>
 * <li>D_SPT_HYD_PT1D_PRES</li>
 * <li>D_SPT_HYD_PT1D_PRES_VAL</li>
 * <li>D_SPT_HYD_PT2A_PRES</li>
 * <li>D_SPT_HYD_PT2A_PRES_VAL</li>
 * <li>D_SPT_HYD_PT2B_PRES</li>
 * <li>D_SPT_HYD_PT2B_PRES_VAL</li>
 * <li>D_SPT_HYD_PT3A_PRES</li>
 * <li>D_SPT_HYD_PT3A_PRES_VAL</li>
 * <li>D_SPT_HYD_PT3B_PRES</li>
 * <li>D_SPT_HYD_PT3B_PRES_VAL</li>
 * <li>D_SPT_HYD_PT3C_PRES</li>
 * <li>D_SPT_HYD_PT3C_PRES_VAL</li>
 * <li>D_SPT_HYD_PT4A_PRES</li>
 * <li>D_SPT_HYD_PT4A_PRES_VAL</li>
 * <li>D_SPT_HYD_PT4B_PRES</li>
 * <li>D_SPT_HYD_PT4B_PRES_VAL</li>
 * <li>D_SPT_HYD_PT4C_PRES</li>
 * <li>D_SPT_HYD_PT4C_PRES_VAL</li>
 * <li>D_SPT_HYD_SCV_LVL</li>
 * <li>D_SPT_HYD_SCV_LVL_VAL</li>
 * <li>D_SPT_HYD_OIL_TEMP</li>
 * <li>D_SPT_HYD_OIL_TEMP_VAL</li>
 * <li>D_SPT_HYD_TRY_LVL</li>
 * <li>D_SPT_HYD_TRY_LVL_VAL</li>
 * <li>D_SPT_IT_HUMD</li>
 * <li>D_SPT_IT_HUMD_VAL</li>
 * <li>D_SPT_IT_RM_TEMP</li>
 * <li>D_SPT_IT_RM_TEMP_VAL</li>
 * <li>D_SPT_IT_TEMP</li>
 * <li>D_SPT_IT_TEMP_VAL</li>
 * <li>D_SPT_MCO_PRES</li>
 * <li>D_SPT_MCO_PRES_VAL</li>
 * <li>D_SPT_PMC_PRES</li>
 * <li>D_SPT_PMC_PRES_VAL</li>
 * <li>D_SPT_PRM_TEMP</li>
 * <li>D_SPT_PRM_TEMP_VAL</li>
 * <li>D_SPT_AZM_M1</li>
 * <li>D_SPT_AZM_M1_HRS</li>
 * <li>D_SPT_AZM_M2</li>
 * <li>D_SPT_AZM_M2_HRS</li>
 * <li>D_SPT_ALT_M1</li>
 * <li>D_SPT_ALT_M1_HRS</li>
 * <li>D_SPT_ALT_M2</li>
 * <li>D_SPT_ALT_M2_HRS</li>
 * <li>D_SPT_CAS_M1</li>
 * <li>D_SPT_CAS_M1_HRS</li>
 * <li>D_SPT_CAS_M2</li>
 * <li>D_SPT_CAS_M2_HRS</li>
 * <li>D_SPT_PRM_FAN</li>
 * <li>D_SPT_PRM_FAN_HRS</li>
 * <li>D_SPT_AIR_COMP</li>
 * <li>D_SPT_AIR_COMP_HRS</li>
 * <li>D_SPT_HYD_PMP</li>
 * <li>D_SPT_HYD_PMP_HRS</li>
 * <li>D_SPT_HYD_CHL_PMP</li>
 * <li>D_SPT_HYD_CHL_PMP_HRS</li>
 * <li>D_SPT_HYD_CHL</li>
 * <li>D_SPT_HYD_CHL_HRS</li>
 * <li>D_SPT_HYD_PSCV</li>
 * <li>D_SPT_HYD_PSCV_HRS</li>
 * <li>D_SPT_HYD_SCV_PMP</li>
 * <li>D_SPT_HYD_SCV_PMP_HRS</li>
 * <li>D_SPT_HYD_AZM_GB</li>
 * <li>D_SPT_HYD_AZM_GB_OPS</li>
 * <li>D_SPT_HYD_AZM_LB</li>
 * <li>D_SPT_HYD_AZM_LB_OPS</li>
 * <li>D_SPT_HYD_ALT_GB</li>
 * <li>D_SPT_HYD_ALT_GB_OPS</li>
 * <li>D_SPT_HYD_ALT_LB</li>
 * <li>D_SPT_HYD_ALT_LB_OPS</li>
 * <li>D_SPT_MCO</li>
 * <li>D_SPT_MCO_OPS</li>
 * <li>D_SPT_SPLC_STATUS</li>
 * <li>D_SPT_SPLC_VER_MAJOR</li>
 * <li>D_SPT_SPLC_VER_MINOR</li>
 * <li>D_SPT_STATUS_EOL</li>
 * <li>D_SPT_DATAID_EOL</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.1 $
 */
public final class SPT_DataType
  implements java.io.Serializable, ngat.ngtcs.subsystem.TTL_DataType
{
  /*=========================================================================*/
  /*                                                                         */
  /* CLASS FIELDS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String RevisionString =
    new String( "$Id: SPT_DataType.java,v 1.1 2006-11-20 14:47:01 cjm Exp $" );

  /**
   * Hashtable of instances for retrieval by the enumeration's String name.
   */
  private static java.util.Hashtable nameHash = new java.util.Hashtable();

  /**
   * Hashtable of instances for retrieval by the enumeration's int value.
   */
  private static java.util.Hashtable intHash = new java.util.Hashtable();

  /**
   * Index of the next enumeration to be added.
   */
  private static int nextIndex = 0;

  /**
   * TTL package to which this data refers.
   */
  private static final TTL_Package ttlPackage = TTL_Package.SPT;

  /*=========================================================================*/
  /*                                                                         */
  /* ENUMERATIONS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * 
   */
  public final static SPT_DataType D_SPT_DATAID_BOL =
      new SPT_DataType( "D_SPT_DATAID_BOL", 0 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_PROC_STATE =
      new SPT_DataType( "D_SPT_PROC_STATE", 1 );

  /**
   * granted authorisation state
   */
  public final static SPT_DataType D_SPT_AUTH_STATE =
      new SPT_DataType( "D_SPT_AUTH_STATE", 2 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_SYS_REQUEST =
      new SPT_DataType( "D_SPT_SYS_REQUEST", 3 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_APP_VERSION =
      new SPT_DataType( "D_SPT_APP_VERSION", 4 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_THIS_APPLICATION =
      new SPT_DataType( "D_SPT_THIS_APPLICATION", 5 );

  /**
   * Identity of this telescope
   */
  public final static SPT_DataType D_SPT_THIS_TELESCOPE =
      new SPT_DataType( "D_SPT_THIS_TELESCOPE", 6 );

  /**
   * Software simulate mode
   */
  public final static SPT_DataType D_SPT_SW_SIMULATE =
      new SPT_DataType( "D_SPT_SW_SIMULATE", 7 );

  /**
   * Beginning of demand list
   */
  public final static SPT_DataType D_SPT_DMD_BOL =
      new SPT_DataType( "D_SPT_DMD_BOL", 8 );

  /**
   * Start
   */
  public final static SPT_DataType D_SPT_DMD_OBS =
      new SPT_DataType( "D_SPT_DMD_OBS", 9 );

  /**
   * Switch Services PLC into Activated
   */
  public final static SPT_DataType D_SPT_DMD_SAFE =
      new SPT_DataType( "D_SPT_DMD_SAFE", 10 );

  /**
   * Start
   */
  public final static SPT_DataType D_SPT_DMD_HYD_SYS =
      new SPT_DataType( "D_SPT_DMD_HYD_SYS", 11 );

  /**
   * Start
   */
  public final static SPT_DataType D_SPT_DMD_AZ_HYD =
      new SPT_DataType( "D_SPT_DMD_AZ_HYD", 12 );

  /**
   * Start
   */
  public final static SPT_DataType D_SPT_DMD_EL_HYD =
      new SPT_DataType( "D_SPT_DMD_EL_HYD", 13 );

  /**
   * Air compressor
   */
  public final static SPT_DataType D_SPT_DMD_AIR_CMP =
      new SPT_DataType( "D_SPT_DMD_AIR_CMP", 14 );

  /**
   * Switch Trace Heating On
   */
  public final static SPT_DataType D_SPT_DMD_SW_THTG =
      new SPT_DataType( "D_SPT_DMD_SW_THTG", 15 );

  /**
   * Switch A & G Box Drives Power On
   */
  public final static SPT_DataType D_SPT_DMD_SW_AGD =
      new SPT_DataType( "D_SPT_DMD_SW_AGD", 16 );

  /**
   * Switch All Lights On
   */
  public final static SPT_DataType D_SPT_DMD_SW_ALL_LTG =
      new SPT_DataType( "D_SPT_DMD_SW_ALL_LTG", 17 );

  /**
   * Switch Enclosure Lights On
   */
  public final static SPT_DataType D_SPT_DMD_SW_ENC_LTG =
      new SPT_DataType( "D_SPT_DMD_SW_ENC_LTG", 18 );

  /**
   * Switch Pump Room Lights On
   */
  public final static SPT_DataType D_SPT_DMD_SW_PRM_LTG =
      new SPT_DataType( "D_SPT_DMD_SW_PRM_LTG", 19 );

  /**
   * Switch Well Area Lights On
   */
  public final static SPT_DataType D_SPT_DMD_SW_WLA_LTG =
      new SPT_DataType( "D_SPT_DMD_SW_WLA_LTG", 20 );

  /**
   * Switch IT Room Lights On
   */
  public final static SPT_DataType D_SPT_DMD_SW_ITR_LTG =
      new SPT_DataType( "D_SPT_DMD_SW_ITR_LTG", 21 );

  /**
   * Switch Aux Mech Computer Power On
   */
  public final static SPT_DataType D_SPT_DMD_SW_AMN_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_AMN_PWR", 22 );

  /**
   * Switch All Power On
   */
  public final static SPT_DataType D_SPT_DMD_SW_ALL_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_ALL_PWR", 23 );

  /**
   * Switch Primary Mirror Cell Power On
   */
  public final static SPT_DataType D_SPT_DMD_SW_PMC_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_PMC_PWR", 24 );

  /**
   * Switch Mirror Cover Power On
   */
  public final static SPT_DataType D_SPT_DMD_SW_MCO_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_MCO_PWR", 25 );

  /**
   * Switch Autoguider Computer Power On
   */
  public final static SPT_DataType D_SPT_DMD_SW_ACC_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_ACC_PWR", 26 );

  /**
   * Switch Azimuth Computer Power On
   */
  public final static SPT_DataType D_SPT_DMD_SW_AZC_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_AZC_PWR", 27 );

  /**
   * Switch Elevation Computer Power On
   */
  public final static SPT_DataType D_SPT_DMD_SW_ELC_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_ELC_PWR", 28 );

  /**
   * Switch Cassegrain Computer Power On
   */
  public final static SPT_DataType D_SPT_DMD_SW_CAC_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_CAC_PWR", 29 );

  /**
   * Switch Azimuth Switchgear Power On
   */
  public final static SPT_DataType D_SPT_DMD_SW_AZS_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_AZS_PWR", 30 );

  /**
   * Switch Elevation Switchgear Power On
   */
  public final static SPT_DataType D_SPT_DMD_SW_ELS_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_ELS_PWR", 31 );

  /**
   * Switch Cassegrain Switchgear Power On
   */
  public final static SPT_DataType D_SPT_DMD_SW_CAS_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_CAS_PWR", 32 );

  /**
   * Switch Nasmyth Drive On
   */
  public final static SPT_DataType D_SPT_DMD_SW_NDS_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_NDS_PWR", 33 );

  /**
   * Switch Nasmyth Wrap On
   */
  public final static SPT_DataType D_SPT_DMD_SW_NWS_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_NWS_PWR", 34 );

  /**
   * Switch Basebox Power On
   */
  public final static SPT_DataType D_SPT_DMD_SW_BBX_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_BBX_PWR", 35 );

  /**
   * Switch Cryotiger 1 Power On
   */
  public final static SPT_DataType D_SPT_DMD_SW_CR1_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_CR1_PWR", 36 );

  /**
   * Switch Axis Drives Power On
   */
  public final static SPT_DataType D_SPT_DMD_SW_AXD_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_AXD_PWR", 37 );

  /**
   * Switch Servo Valve Power On
   */
  public final static SPT_DataType D_SPT_DMD_SW_SVR_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_SVR_PWR", 38 );

  /**
   * Open Mirror Cover Open
   */
  public final static SPT_DataType D_SPT_DMD_MIR_COV =
      new SPT_DataType( "D_SPT_DMD_MIR_COV", 39 );

  /**
   * Fault Reset (no disable)
   */
  public final static SPT_DataType D_SPT_DMD_FLT =
      new SPT_DataType( "D_SPT_DMD_FLT", 40 );

  /**
   * Emergency Stop Reset (no disable)
   */
  public final static SPT_DataType D_SPT_DMD_ESTOP =
      new SPT_DataType( "D_SPT_DMD_ESTOP", 41 );

  /**
   * Switch Secondary Mirror Power On
   */
  public final static SPT_DataType D_SPT_DMD_SW_SEC_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_SEC_PWR", 42 );

  /**
   * Switch Motor Temperature Panel Power On
   */
  public final static SPT_DataType D_SPT_DMD_SW_TMP_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_TMP_PWR", 43 );

  /**
   * Switch Cryotiger 2 Power On
   */
  public final static SPT_DataType D_SPT_DMD_SW_CR2_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_CR2_PWR", 44 );

  /**
   * Switch Basebox Low Voltage Power On
   */
  public final static SPT_DataType D_SPT_DMD_SW_BBL_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_BBL_PWR", 45 );

  /**
   * Switch Amplifier Fans On
   */
  public final static SPT_DataType D_SPT_DMD_SW_BBF_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_BBF_PWR", 46 );

  /**
   * Switch Basebox Pressure Sensors On
   */
  public final static SPT_DataType D_SPT_DMD_SW_BBP_PWR =
      new SPT_DataType( "D_SPT_DMD_SW_BBP_PWR", 47 );

  /**
   * Query Status
   */
  public final static SPT_DataType D_SPT_DMD_QRY_STATUS =
      new SPT_DataType( "D_SPT_DMD_QRY_STATUS", 48 );

  /**
   * End of demand list
   */
  public final static SPT_DataType D_SPT_DMD_EOL =
      new SPT_DataType( "D_SPT_DMD_EOL", 49 );

  /**
   * Beginning of status list
   */
  public final static SPT_DataType D_SPT_STATUS_BOL =
      new SPT_DataType( "D_SPT_STATUS_BOL", 50 );

  /**
   * Azimuth Node Watchdog
   */
  public final static SPT_DataType D_SPT_AZM_WD =
      new SPT_DataType( "D_SPT_AZM_WD", 51 );

  /**
   * Altitude Node Watchdog
   */
  public final static SPT_DataType D_SPT_ALT_WD =
      new SPT_DataType( "D_SPT_ALT_WD", 52 );

  /**
   * Cassegrain Node Watchdog
   */
  public final static SPT_DataType D_SPT_CAS_WD =
      new SPT_DataType( "D_SPT_CAS_WD", 53 );

  /**
   * Auxiliary Mechanisms Node Watchdog
   */
  public final static SPT_DataType D_SPT_AMN_WD =
      new SPT_DataType( "D_SPT_AMN_WD", 54 );

  /**
   * Hydrostatic Filter No 1 Press Switch
   */
  public final static SPT_DataType D_SPT_HYD_F1 =
      new SPT_DataType( "D_SPT_HYD_F1", 55 );

  /**
   * Hydrostatic Filter No 2 Press Switch
   */
  public final static SPT_DataType D_SPT_HYD_F2 =
      new SPT_DataType( "D_SPT_HYD_F2", 56 );

  /**
   * Fire Alarm
   */
  public final static SPT_DataType D_SPT_FA =
      new SPT_DataType( "D_SPT_FA", 57 );

  /**
   * Security Alarm
   */
  public final static SPT_DataType D_SPT_SA =
      new SPT_DataType( "D_SPT_SA", 58 );

  /**
   * Door Interlock Override
   */
  public final static SPT_DataType D_SPT_DR_ILK =
      new SPT_DataType( "D_SPT_DR_ILK", 59 );

  /**
   * IT Room Outer Door
   */
  public final static SPT_DataType D_SPT_ITR_OUT_DR =
      new SPT_DataType( "D_SPT_ITR_OUT_DR", 60 );

  /**
   * Pump Room Outer Door
   */
  public final static SPT_DataType D_SPT_PRM_OUT_DR =
      new SPT_DataType( "D_SPT_PRM_OUT_DR", 61 );

  /**
   * Perimeter Gates
   */
  public final static SPT_DataType D_SPT_PER_GT =
      new SPT_DataType( "D_SPT_PER_GT", 62 );

  /**
   * Lighting Override
   */
  public final static SPT_DataType D_SPT_LTG_LOC_OR =
      new SPT_DataType( "D_SPT_LTG_LOC_OR", 63 );

  /**
   * Pump Room Lights
   */
  public final static SPT_DataType D_SPT_LTG_PRM =
      new SPT_DataType( "D_SPT_LTG_PRM", 64 );

  /**
   * Enclosure Lights
   */
  public final static SPT_DataType D_SPT_LTG_ENC =
      new SPT_DataType( "D_SPT_LTG_ENC", 65 );

  /**
   * Well Area Lights
   */
  public final static SPT_DataType D_SPT_LTG_WLA =
      new SPT_DataType( "D_SPT_LTG_WLA", 66 );

  /**
   * IT Room Lights
   */
  public final static SPT_DataType D_SPT_LTG_ITR =
      new SPT_DataType( "D_SPT_LTG_ITR", 67 );

  /**
   * Emergency Stop Contactors
   */
  public final static SPT_DataType D_SPT_ESTOP_CTRS =
      new SPT_DataType( "D_SPT_ESTOP_CTRS", 68 );

  /**
   * Services Panel Control Mode
   */
  public final static SPT_DataType D_SPT_SPLC_MODE =
      new SPT_DataType( "D_SPT_SPLC_MODE", 69 );

  /**
   * Services Panel General Status
   */
  public final static SPT_DataType D_SPT_SPLC_STATE =
      new SPT_DataType( "D_SPT_SPLC_STATE", 70 );

  /**
   * Services PLC UPS
   */
  public final static SPT_DataType D_SPT_SPLC_UPS =
      new SPT_DataType( "D_SPT_SPLC_UPS", 71 );

  /**
   * Azimuth Computer Power
   */
  public final static SPT_DataType D_SPT_PWR_AZM =
      new SPT_DataType( "D_SPT_PWR_AZM", 72 );

  /**
   * Altitude Computer Power
   */
  public final static SPT_DataType D_SPT_PWR_ALT =
      new SPT_DataType( "D_SPT_PWR_ALT", 73 );

  /**
   * Cassegrain Computer Power
   */
  public final static SPT_DataType D_SPT_PWR_CAS =
      new SPT_DataType( "D_SPT_PWR_CAS", 74 );

  /**
   * Auxiliary Mechanism Node Power
   */
  public final static SPT_DataType D_SPT_PWR_AMN =
      new SPT_DataType( "D_SPT_PWR_AMN", 75 );

  /**
   * Nasmyth Drive Side Power
   */
  public final static SPT_DataType D_SPT_PWR_NDS =
      new SPT_DataType( "D_SPT_PWR_NDS", 76 );

  /**
   * Nasmyth Wrap Side Power
   */
  public final static SPT_DataType D_SPT_PWR_NWS =
      new SPT_DataType( "D_SPT_PWR_NWS", 77 );

  /**
   * Autoguider Computer Power
   */
  public final static SPT_DataType D_SPT_PWR_ACC =
      new SPT_DataType( "D_SPT_PWR_ACC", 78 );

  /**
   * Azimuth Switchgear Power
   */
  public final static SPT_DataType D_SPT_PWR_AZM_SW =
      new SPT_DataType( "D_SPT_PWR_AZM_SW", 79 );

  /**
   * Altitude Switchgear Power
   */
  public final static SPT_DataType D_SPT_PWR_ALT_SW =
      new SPT_DataType( "D_SPT_PWR_ALT_SW", 80 );

  /**
   * Cassegrain Switchgear Power
   */
  public final static SPT_DataType D_SPT_PWR_CAS_SW =
      new SPT_DataType( "D_SPT_PWR_CAS_SW", 81 );

  /**
   * Customers Instrument Supply
   */
  public final static SPT_DataType D_SPT_CSE_SPLY =
      new SPT_DataType( "D_SPT_CSE_SPLY", 82 );

  /**
   * Enclosure Doors
   */
  public final static SPT_DataType D_SPT_ENC_DRS =
      new SPT_DataType( "D_SPT_ENC_DRS", 83 );

  /**
   * Enclosure Roller Shutter Door
   */
  public final static SPT_DataType D_SPT_ENC_RSD =
      new SPT_DataType( "D_SPT_ENC_RSD", 84 );

  /**
   * Enclosure Seals Trace Heating
   */
  public final static SPT_DataType D_SPT_HTG_ENC_SEAL =
      new SPT_DataType( "D_SPT_HTG_ENC_SEAL", 85 );

  /**
   * Mode Selector Switch
   */
  public final static SPT_DataType D_SPT_MODE =
      new SPT_DataType( "D_SPT_MODE", 86 );

  /**
   * A & G Box Drives Power
   */
  public final static SPT_DataType D_SPT_PWR_AG =
      new SPT_DataType( "D_SPT_PWR_AG", 87 );

  /**
   * Axis Drives Power
   */
  public final static SPT_DataType D_SPT_PWR_AXIS =
      new SPT_DataType( "D_SPT_PWR_AXIS", 88 );

  /**
   * Cryotiger 1 Power
   */
  public final static SPT_DataType D_SPT_PWR_CRY1 =
      new SPT_DataType( "D_SPT_PWR_CRY1", 89 );

  /**
   * Cryotiger 2 Power
   */
  public final static SPT_DataType D_SPT_PWR_CRY2 =
      new SPT_DataType( "D_SPT_PWR_CRY2", 90 );

  /**
   * Basebox LV Power
   */
  public final static SPT_DataType D_SPT_PWR_LV =
      new SPT_DataType( "D_SPT_PWR_LV", 91 );

  /**
   * Services Main Power
   */
  public final static SPT_DataType D_SPT_PWR_MAIN =
      new SPT_DataType( "D_SPT_PWR_MAIN", 92 );

  /**
   * Mirror Cover Power
   */
  public final static SPT_DataType D_SPT_PWR_MCO =
      new SPT_DataType( "D_SPT_PWR_MCO", 93 );

  /**
   * Basebox MV Power
   */
  public final static SPT_DataType D_SPT_PWR_MV =
      new SPT_DataType( "D_SPT_PWR_MV", 94 );

  /**
   * Off Mount Power
   */
  public final static SPT_DataType D_SPT_PWR_OFM =
      new SPT_DataType( "D_SPT_PWR_OFM", 95 );

  /**
   * On Mount Power
   */
  public final static SPT_DataType D_SPT_PWR_ONM =
      new SPT_DataType( "D_SPT_PWR_ONM", 96 );

  /**
   * Primary Mirror Cell Power
   */
  public final static SPT_DataType D_SPT_PWR_PMC =
      new SPT_DataType( "D_SPT_PWR_PMC", 97 );

  /**
   * Basebox Pressure Sensors Power
   */
  public final static SPT_DataType D_SPT_PWR_PRES =
      new SPT_DataType( "D_SPT_PWR_PRES", 98 );

  /**
   * Secondary Mirror Power
   */
  public final static SPT_DataType D_SPT_PWR_SEC =
      new SPT_DataType( "D_SPT_PWR_SEC", 99 );

  /**
   * Servo Valve Power
   */
  public final static SPT_DataType D_SPT_PWR_SRV =
      new SPT_DataType( "D_SPT_PWR_SRV", 100 );

  /**
   * Motor Temperature Panel Power
   */
  public final static SPT_DataType D_SPT_PWR_TEMP =
      new SPT_DataType( "D_SPT_PWR_TEMP", 101 );

  /**
   * Services Panel Control Source
   */
  public final static SPT_DataType D_SPT_SPLC_CONTROL =
      new SPT_DataType( "D_SPT_SPLC_CONTROL", 102 );

  /**
   * Azimuth Limits
   */
  public final static SPT_DataType D_SPT_AZM_LIMITS =
      new SPT_DataType( "D_SPT_AZM_LIMITS", 103 );

  /**
   * Altitude Limits
   */
  public final static SPT_DataType D_SPT_ALT_LIMITS =
      new SPT_DataType( "D_SPT_ALT_LIMITS", 104 );

  /**
   * Cassegrain Limits
   */
  public final static SPT_DataType D_SPT_CAS_LIMITS =
      new SPT_DataType( "D_SPT_CAS_LIMITS", 105 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_AZM_M1_TEMP =
      new SPT_DataType( "D_SPT_AZM_M1_TEMP", 106 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_AZM_M1_TEMP_VAL =
      new SPT_DataType( "D_SPT_AZM_M1_TEMP_VAL", 107 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_AZM_M2_TEMP =
      new SPT_DataType( "D_SPT_AZM_M2_TEMP", 108 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_AZM_M2_TEMP_VAL =
      new SPT_DataType( "D_SPT_AZM_M2_TEMP_VAL", 109 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_ALT_M1_TEMP =
      new SPT_DataType( "D_SPT_ALT_M1_TEMP", 110 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_ALT_M1_TEMP_VAL =
      new SPT_DataType( "D_SPT_ALT_M1_TEMP_VAL", 111 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_ALT_M2_TEMP =
      new SPT_DataType( "D_SPT_ALT_M2_TEMP", 112 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_ALT_M2_TEMP_VAL =
      new SPT_DataType( "D_SPT_ALT_M2_TEMP_VAL", 113 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_CAS_M1_TEMP =
      new SPT_DataType( "D_SPT_CAS_M1_TEMP", 114 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_CAS_M1_TEMP_VAL =
      new SPT_DataType( "D_SPT_CAS_M1_TEMP_VAL", 115 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_CAS_M2_TEMP =
      new SPT_DataType( "D_SPT_CAS_M2_TEMP", 116 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_CAS_M2_TEMP_VAL =
      new SPT_DataType( "D_SPT_CAS_M2_TEMP_VAL", 117 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_AMP_RK_TEMP =
      new SPT_DataType( "D_SPT_AMP_RK_TEMP", 118 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_AMP_RK_TEMP_VAL =
      new SPT_DataType( "D_SPT_AMP_RK_TEMP_VAL", 119 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_SPARE_2_TEMP =
      new SPT_DataType( "D_SPT_SPARE_2_TEMP", 120 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_SPARE_2_TEMP_VAL =
      new SPT_DataType( "D_SPT_SPARE_2_TEMP_VAL", 121 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_SPARE_3_TEMP =
      new SPT_DataType( "D_SPT_SPARE_3_TEMP", 122 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_SPARE_3_TEMP_VAL =
      new SPT_DataType( "D_SPT_SPARE_3_TEMP_VAL", 123 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_SPARE_4_TEMP =
      new SPT_DataType( "D_SPT_SPARE_4_TEMP", 124 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_SPARE_4_TEMP_VAL =
      new SPT_DataType( "D_SPT_SPARE_4_TEMP_VAL", 125 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_AMN_TEMP =
      new SPT_DataType( "D_SPT_AMN_TEMP", 126 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_AMN_TEMP_VAL =
      new SPT_DataType( "D_SPT_AMN_TEMP_VAL", 127 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_CHK_PNL_TEMP =
      new SPT_DataType( "D_SPT_CHK_PNL_TEMP", 128 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_CHK_PNL_TEMP_VAL =
      new SPT_DataType( "D_SPT_CHK_PNL_TEMP_VAL", 129 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_AG_TEMP =
      new SPT_DataType( "D_SPT_AG_TEMP", 130 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_AG_TEMP_VAL =
      new SPT_DataType( "D_SPT_AG_TEMP_VAL", 131 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_SPARE_8_TEMP =
      new SPT_DataType( "D_SPT_SPARE_8_TEMP", 132 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_SPARE_8_TEMP_VAL =
      new SPT_DataType( "D_SPT_SPARE_8_TEMP_VAL", 133 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_ENC_AMB_TEMP =
      new SPT_DataType( "D_SPT_ENC_AMB_TEMP", 134 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_ENC_AMB_TEMP_VAL =
      new SPT_DataType( "D_SPT_ENC_AMB_TEMP_VAL", 135 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HTG_TT1_TEMP =
      new SPT_DataType( "D_SPT_HTG_TT1_TEMP", 136 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HTG_TT1_TEMP_VAL =
      new SPT_DataType( "D_SPT_HTG_TT1_TEMP_VAL", 137 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HTG_TT2_TEMP =
      new SPT_DataType( "D_SPT_HTG_TT2_TEMP", 138 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HTG_TT2_TEMP_VAL =
      new SPT_DataType( "D_SPT_HTG_TT2_TEMP_VAL", 139 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HTG_TT3_TEMP =
      new SPT_DataType( "D_SPT_HTG_TT3_TEMP", 140 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HTG_TT3_TEMP_VAL =
      new SPT_DataType( "D_SPT_HTG_TT3_TEMP_VAL", 141 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HTG_TT4_TEMP =
      new SPT_DataType( "D_SPT_HTG_TT4_TEMP", 142 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HTG_TT4_TEMP_VAL =
      new SPT_DataType( "D_SPT_HTG_TT4_TEMP_VAL", 143 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HTG_TT5_TEMP =
      new SPT_DataType( "D_SPT_HTG_TT5_TEMP", 144 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HTG_TT5_TEMP_VAL =
      new SPT_DataType( "D_SPT_HTG_TT5_TEMP_VAL", 145 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HTG_TT6_TEMP =
      new SPT_DataType( "D_SPT_HTG_TT6_TEMP", 146 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HTG_TT6_TEMP_VAL =
      new SPT_DataType( "D_SPT_HTG_TT6_TEMP_VAL", 147 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_CHL_TEMP =
      new SPT_DataType( "D_SPT_HYD_CHL_TEMP", 148 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_CHL_TEMP_VAL =
      new SPT_DataType( "D_SPT_HYD_CHL_TEMP_VAL", 149 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_FT1_FLOW =
      new SPT_DataType( "D_SPT_HYD_FT1_FLOW", 150 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_FT1_FLOW_VAL =
      new SPT_DataType( "D_SPT_HYD_FT1_FLOW_VAL", 151 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_FT2_FLOW =
      new SPT_DataType( "D_SPT_HYD_FT2_FLOW", 152 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_FT2_FLOW_VAL =
      new SPT_DataType( "D_SPT_HYD_FT2_FLOW_VAL", 153 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_FT3_FLOW =
      new SPT_DataType( "D_SPT_HYD_FT3_FLOW", 154 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_FT3_FLOW_VAL =
      new SPT_DataType( "D_SPT_HYD_FT3_FLOW_VAL", 155 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_FT4_FLOW =
      new SPT_DataType( "D_SPT_HYD_FT4_FLOW", 156 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_FT4_FLOW_VAL =
      new SPT_DataType( "D_SPT_HYD_FT4_FLOW_VAL", 157 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_MAIN_LVL =
      new SPT_DataType( "D_SPT_HYD_MAIN_LVL", 158 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_MAIN_LVL_VAL =
      new SPT_DataType( "D_SPT_HYD_MAIN_LVL_VAL", 159 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT1A_PRES =
      new SPT_DataType( "D_SPT_HYD_PT1A_PRES", 160 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT1A_PRES_VAL =
      new SPT_DataType( "D_SPT_HYD_PT1A_PRES_VAL", 161 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT1B_PRES =
      new SPT_DataType( "D_SPT_HYD_PT1B_PRES", 162 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT1B_PRES_VAL =
      new SPT_DataType( "D_SPT_HYD_PT1B_PRES_VAL", 163 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT1C_PRES =
      new SPT_DataType( "D_SPT_HYD_PT1C_PRES", 164 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT1C_PRES_VAL =
      new SPT_DataType( "D_SPT_HYD_PT1C_PRES_VAL", 165 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT1D_PRES =
      new SPT_DataType( "D_SPT_HYD_PT1D_PRES", 166 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT1D_PRES_VAL =
      new SPT_DataType( "D_SPT_HYD_PT1D_PRES_VAL", 167 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT2A_PRES =
      new SPT_DataType( "D_SPT_HYD_PT2A_PRES", 168 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT2A_PRES_VAL =
      new SPT_DataType( "D_SPT_HYD_PT2A_PRES_VAL", 169 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT2B_PRES =
      new SPT_DataType( "D_SPT_HYD_PT2B_PRES", 170 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT2B_PRES_VAL =
      new SPT_DataType( "D_SPT_HYD_PT2B_PRES_VAL", 171 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT3A_PRES =
      new SPT_DataType( "D_SPT_HYD_PT3A_PRES", 172 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT3A_PRES_VAL =
      new SPT_DataType( "D_SPT_HYD_PT3A_PRES_VAL", 173 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT3B_PRES =
      new SPT_DataType( "D_SPT_HYD_PT3B_PRES", 174 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT3B_PRES_VAL =
      new SPT_DataType( "D_SPT_HYD_PT3B_PRES_VAL", 175 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT3C_PRES =
      new SPT_DataType( "D_SPT_HYD_PT3C_PRES", 176 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT3C_PRES_VAL =
      new SPT_DataType( "D_SPT_HYD_PT3C_PRES_VAL", 177 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT4A_PRES =
      new SPT_DataType( "D_SPT_HYD_PT4A_PRES", 178 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT4A_PRES_VAL =
      new SPT_DataType( "D_SPT_HYD_PT4A_PRES_VAL", 179 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT4B_PRES =
      new SPT_DataType( "D_SPT_HYD_PT4B_PRES", 180 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT4B_PRES_VAL =
      new SPT_DataType( "D_SPT_HYD_PT4B_PRES_VAL", 181 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT4C_PRES =
      new SPT_DataType( "D_SPT_HYD_PT4C_PRES", 182 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PT4C_PRES_VAL =
      new SPT_DataType( "D_SPT_HYD_PT4C_PRES_VAL", 183 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_SCV_LVL =
      new SPT_DataType( "D_SPT_HYD_SCV_LVL", 184 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_SCV_LVL_VAL =
      new SPT_DataType( "D_SPT_HYD_SCV_LVL_VAL", 185 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_OIL_TEMP =
      new SPT_DataType( "D_SPT_HYD_OIL_TEMP", 186 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_OIL_TEMP_VAL =
      new SPT_DataType( "D_SPT_HYD_OIL_TEMP_VAL", 187 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_TRY_LVL =
      new SPT_DataType( "D_SPT_HYD_TRY_LVL", 188 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_TRY_LVL_VAL =
      new SPT_DataType( "D_SPT_HYD_TRY_LVL_VAL", 189 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_IT_HUMD =
      new SPT_DataType( "D_SPT_IT_HUMD", 190 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_IT_HUMD_VAL =
      new SPT_DataType( "D_SPT_IT_HUMD_VAL", 191 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_IT_RM_TEMP =
      new SPT_DataType( "D_SPT_IT_RM_TEMP", 192 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_IT_RM_TEMP_VAL =
      new SPT_DataType( "D_SPT_IT_RM_TEMP_VAL", 193 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_IT_TEMP =
      new SPT_DataType( "D_SPT_IT_TEMP", 194 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_IT_TEMP_VAL =
      new SPT_DataType( "D_SPT_IT_TEMP_VAL", 195 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_MCO_PRES =
      new SPT_DataType( "D_SPT_MCO_PRES", 196 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_MCO_PRES_VAL =
      new SPT_DataType( "D_SPT_MCO_PRES_VAL", 197 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_PMC_PRES =
      new SPT_DataType( "D_SPT_PMC_PRES", 198 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_PMC_PRES_VAL =
      new SPT_DataType( "D_SPT_PMC_PRES_VAL", 199 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_PRM_TEMP =
      new SPT_DataType( "D_SPT_PRM_TEMP", 200 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_PRM_TEMP_VAL =
      new SPT_DataType( "D_SPT_PRM_TEMP_VAL", 201 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_AZM_M1 =
      new SPT_DataType( "D_SPT_AZM_M1", 202 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_AZM_M1_HRS =
      new SPT_DataType( "D_SPT_AZM_M1_HRS", 203 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_AZM_M2 =
      new SPT_DataType( "D_SPT_AZM_M2", 204 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_AZM_M2_HRS =
      new SPT_DataType( "D_SPT_AZM_M2_HRS", 205 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_ALT_M1 =
      new SPT_DataType( "D_SPT_ALT_M1", 206 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_ALT_M1_HRS =
      new SPT_DataType( "D_SPT_ALT_M1_HRS", 207 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_ALT_M2 =
      new SPT_DataType( "D_SPT_ALT_M2", 208 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_ALT_M2_HRS =
      new SPT_DataType( "D_SPT_ALT_M2_HRS", 209 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_CAS_M1 =
      new SPT_DataType( "D_SPT_CAS_M1", 210 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_CAS_M1_HRS =
      new SPT_DataType( "D_SPT_CAS_M1_HRS", 211 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_CAS_M2 =
      new SPT_DataType( "D_SPT_CAS_M2", 212 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_CAS_M2_HRS =
      new SPT_DataType( "D_SPT_CAS_M2_HRS", 213 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_PRM_FAN =
      new SPT_DataType( "D_SPT_PRM_FAN", 214 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_PRM_FAN_HRS =
      new SPT_DataType( "D_SPT_PRM_FAN_HRS", 215 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_AIR_COMP =
      new SPT_DataType( "D_SPT_AIR_COMP", 216 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_AIR_COMP_HRS =
      new SPT_DataType( "D_SPT_AIR_COMP_HRS", 217 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PMP =
      new SPT_DataType( "D_SPT_HYD_PMP", 218 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PMP_HRS =
      new SPT_DataType( "D_SPT_HYD_PMP_HRS", 219 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_CHL_PMP =
      new SPT_DataType( "D_SPT_HYD_CHL_PMP", 220 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_CHL_PMP_HRS =
      new SPT_DataType( "D_SPT_HYD_CHL_PMP_HRS", 221 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_CHL =
      new SPT_DataType( "D_SPT_HYD_CHL", 222 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_CHL_HRS =
      new SPT_DataType( "D_SPT_HYD_CHL_HRS", 223 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PSCV =
      new SPT_DataType( "D_SPT_HYD_PSCV", 224 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_PSCV_HRS =
      new SPT_DataType( "D_SPT_HYD_PSCV_HRS", 225 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_SCV_PMP =
      new SPT_DataType( "D_SPT_HYD_SCV_PMP", 226 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_SCV_PMP_HRS =
      new SPT_DataType( "D_SPT_HYD_SCV_PMP_HRS", 227 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_AZM_GB =
      new SPT_DataType( "D_SPT_HYD_AZM_GB", 228 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_AZM_GB_OPS =
      new SPT_DataType( "D_SPT_HYD_AZM_GB_OPS", 229 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_AZM_LB =
      new SPT_DataType( "D_SPT_HYD_AZM_LB", 230 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_AZM_LB_OPS =
      new SPT_DataType( "D_SPT_HYD_AZM_LB_OPS", 231 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_ALT_GB =
      new SPT_DataType( "D_SPT_HYD_ALT_GB", 232 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_ALT_GB_OPS =
      new SPT_DataType( "D_SPT_HYD_ALT_GB_OPS", 233 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_ALT_LB =
      new SPT_DataType( "D_SPT_HYD_ALT_LB", 234 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_HYD_ALT_LB_OPS =
      new SPT_DataType( "D_SPT_HYD_ALT_LB_OPS", 235 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_MCO =
      new SPT_DataType( "D_SPT_MCO", 236 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_MCO_OPS =
      new SPT_DataType( "D_SPT_MCO_OPS", 237 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_SPLC_STATUS =
      new SPT_DataType( "D_SPT_SPLC_STATUS", 238 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_SPLC_VER_MAJOR =
      new SPT_DataType( "D_SPT_SPLC_VER_MAJOR", 239 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_SPLC_VER_MINOR =
      new SPT_DataType( "D_SPT_SPLC_VER_MINOR", 240 );

  /**
   * End of status list
   */
  public final static SPT_DataType D_SPT_STATUS_EOL =
      new SPT_DataType( "D_SPT_STATUS_EOL", 241 );

  /**
   * 
   */
  public final static SPT_DataType D_SPT_DATAID_EOL =
      new SPT_DataType( "D_SPT_DATAID_EOL", 242 );

  /**
   * Array allowing serialization.
   */
  protected static final SPT_DataType[] array =
  {
    D_SPT_DATAID_BOL,
    D_SPT_PROC_STATE,
    D_SPT_AUTH_STATE,
    D_SPT_SYS_REQUEST,
    D_SPT_APP_VERSION,
    D_SPT_THIS_APPLICATION,
    D_SPT_THIS_TELESCOPE,
    D_SPT_SW_SIMULATE,
    D_SPT_DMD_BOL,
    D_SPT_DMD_OBS,
    D_SPT_DMD_SAFE,
    D_SPT_DMD_HYD_SYS,
    D_SPT_DMD_AZ_HYD,
    D_SPT_DMD_EL_HYD,
    D_SPT_DMD_AIR_CMP,
    D_SPT_DMD_SW_THTG,
    D_SPT_DMD_SW_AGD,
    D_SPT_DMD_SW_ALL_LTG,
    D_SPT_DMD_SW_ENC_LTG,
    D_SPT_DMD_SW_PRM_LTG,
    D_SPT_DMD_SW_WLA_LTG,
    D_SPT_DMD_SW_ITR_LTG,
    D_SPT_DMD_SW_AMN_PWR,
    D_SPT_DMD_SW_ALL_PWR,
    D_SPT_DMD_SW_PMC_PWR,
    D_SPT_DMD_SW_MCO_PWR,
    D_SPT_DMD_SW_ACC_PWR,
    D_SPT_DMD_SW_AZC_PWR,
    D_SPT_DMD_SW_ELC_PWR,
    D_SPT_DMD_SW_CAC_PWR,
    D_SPT_DMD_SW_AZS_PWR,
    D_SPT_DMD_SW_ELS_PWR,
    D_SPT_DMD_SW_CAS_PWR,
    D_SPT_DMD_SW_NDS_PWR,
    D_SPT_DMD_SW_NWS_PWR,
    D_SPT_DMD_SW_BBX_PWR,
    D_SPT_DMD_SW_CR1_PWR,
    D_SPT_DMD_SW_AXD_PWR,
    D_SPT_DMD_SW_SVR_PWR,
    D_SPT_DMD_MIR_COV,
    D_SPT_DMD_FLT,
    D_SPT_DMD_ESTOP,
    D_SPT_DMD_SW_SEC_PWR,
    D_SPT_DMD_SW_TMP_PWR,
    D_SPT_DMD_SW_CR2_PWR,
    D_SPT_DMD_SW_BBL_PWR,
    D_SPT_DMD_SW_BBF_PWR,
    D_SPT_DMD_SW_BBP_PWR,
    D_SPT_DMD_QRY_STATUS,
    D_SPT_DMD_EOL,
    D_SPT_STATUS_BOL,
    D_SPT_AZM_WD,
    D_SPT_ALT_WD,
    D_SPT_CAS_WD,
    D_SPT_AMN_WD,
    D_SPT_HYD_F1,
    D_SPT_HYD_F2,
    D_SPT_FA,
    D_SPT_SA,
    D_SPT_DR_ILK,
    D_SPT_ITR_OUT_DR,
    D_SPT_PRM_OUT_DR,
    D_SPT_PER_GT,
    D_SPT_LTG_LOC_OR,
    D_SPT_LTG_PRM,
    D_SPT_LTG_ENC,
    D_SPT_LTG_WLA,
    D_SPT_LTG_ITR,
    D_SPT_ESTOP_CTRS,
    D_SPT_SPLC_MODE,
    D_SPT_SPLC_STATE,
    D_SPT_SPLC_UPS,
    D_SPT_PWR_AZM,
    D_SPT_PWR_ALT,
    D_SPT_PWR_CAS,
    D_SPT_PWR_AMN,
    D_SPT_PWR_NDS,
    D_SPT_PWR_NWS,
    D_SPT_PWR_ACC,
    D_SPT_PWR_AZM_SW,
    D_SPT_PWR_ALT_SW,
    D_SPT_PWR_CAS_SW,
    D_SPT_CSE_SPLY,
    D_SPT_ENC_DRS,
    D_SPT_ENC_RSD,
    D_SPT_HTG_ENC_SEAL,
    D_SPT_MODE,
    D_SPT_PWR_AG,
    D_SPT_PWR_AXIS,
    D_SPT_PWR_CRY1,
    D_SPT_PWR_CRY2,
    D_SPT_PWR_LV,
    D_SPT_PWR_MAIN,
    D_SPT_PWR_MCO,
    D_SPT_PWR_MV,
    D_SPT_PWR_OFM,
    D_SPT_PWR_ONM,
    D_SPT_PWR_PMC,
    D_SPT_PWR_PRES,
    D_SPT_PWR_SEC,
    D_SPT_PWR_SRV,
    D_SPT_PWR_TEMP,
    D_SPT_SPLC_CONTROL,
    D_SPT_AZM_LIMITS,
    D_SPT_ALT_LIMITS,
    D_SPT_CAS_LIMITS,
    D_SPT_AZM_M1_TEMP,
    D_SPT_AZM_M1_TEMP_VAL,
    D_SPT_AZM_M2_TEMP,
    D_SPT_AZM_M2_TEMP_VAL,
    D_SPT_ALT_M1_TEMP,
    D_SPT_ALT_M1_TEMP_VAL,
    D_SPT_ALT_M2_TEMP,
    D_SPT_ALT_M2_TEMP_VAL,
    D_SPT_CAS_M1_TEMP,
    D_SPT_CAS_M1_TEMP_VAL,
    D_SPT_CAS_M2_TEMP,
    D_SPT_CAS_M2_TEMP_VAL,
    D_SPT_AMP_RK_TEMP,
    D_SPT_AMP_RK_TEMP_VAL,
    D_SPT_SPARE_2_TEMP,
    D_SPT_SPARE_2_TEMP_VAL,
    D_SPT_SPARE_3_TEMP,
    D_SPT_SPARE_3_TEMP_VAL,
    D_SPT_SPARE_4_TEMP,
    D_SPT_SPARE_4_TEMP_VAL,
    D_SPT_AMN_TEMP,
    D_SPT_AMN_TEMP_VAL,
    D_SPT_CHK_PNL_TEMP,
    D_SPT_CHK_PNL_TEMP_VAL,
    D_SPT_AG_TEMP,
    D_SPT_AG_TEMP_VAL,
    D_SPT_SPARE_8_TEMP,
    D_SPT_SPARE_8_TEMP_VAL,
    D_SPT_ENC_AMB_TEMP,
    D_SPT_ENC_AMB_TEMP_VAL,
    D_SPT_HTG_TT1_TEMP,
    D_SPT_HTG_TT1_TEMP_VAL,
    D_SPT_HTG_TT2_TEMP,
    D_SPT_HTG_TT2_TEMP_VAL,
    D_SPT_HTG_TT3_TEMP,
    D_SPT_HTG_TT3_TEMP_VAL,
    D_SPT_HTG_TT4_TEMP,
    D_SPT_HTG_TT4_TEMP_VAL,
    D_SPT_HTG_TT5_TEMP,
    D_SPT_HTG_TT5_TEMP_VAL,
    D_SPT_HTG_TT6_TEMP,
    D_SPT_HTG_TT6_TEMP_VAL,
    D_SPT_HYD_CHL_TEMP,
    D_SPT_HYD_CHL_TEMP_VAL,
    D_SPT_HYD_FT1_FLOW,
    D_SPT_HYD_FT1_FLOW_VAL,
    D_SPT_HYD_FT2_FLOW,
    D_SPT_HYD_FT2_FLOW_VAL,
    D_SPT_HYD_FT3_FLOW,
    D_SPT_HYD_FT3_FLOW_VAL,
    D_SPT_HYD_FT4_FLOW,
    D_SPT_HYD_FT4_FLOW_VAL,
    D_SPT_HYD_MAIN_LVL,
    D_SPT_HYD_MAIN_LVL_VAL,
    D_SPT_HYD_PT1A_PRES,
    D_SPT_HYD_PT1A_PRES_VAL,
    D_SPT_HYD_PT1B_PRES,
    D_SPT_HYD_PT1B_PRES_VAL,
    D_SPT_HYD_PT1C_PRES,
    D_SPT_HYD_PT1C_PRES_VAL,
    D_SPT_HYD_PT1D_PRES,
    D_SPT_HYD_PT1D_PRES_VAL,
    D_SPT_HYD_PT2A_PRES,
    D_SPT_HYD_PT2A_PRES_VAL,
    D_SPT_HYD_PT2B_PRES,
    D_SPT_HYD_PT2B_PRES_VAL,
    D_SPT_HYD_PT3A_PRES,
    D_SPT_HYD_PT3A_PRES_VAL,
    D_SPT_HYD_PT3B_PRES,
    D_SPT_HYD_PT3B_PRES_VAL,
    D_SPT_HYD_PT3C_PRES,
    D_SPT_HYD_PT3C_PRES_VAL,
    D_SPT_HYD_PT4A_PRES,
    D_SPT_HYD_PT4A_PRES_VAL,
    D_SPT_HYD_PT4B_PRES,
    D_SPT_HYD_PT4B_PRES_VAL,
    D_SPT_HYD_PT4C_PRES,
    D_SPT_HYD_PT4C_PRES_VAL,
    D_SPT_HYD_SCV_LVL,
    D_SPT_HYD_SCV_LVL_VAL,
    D_SPT_HYD_OIL_TEMP,
    D_SPT_HYD_OIL_TEMP_VAL,
    D_SPT_HYD_TRY_LVL,
    D_SPT_HYD_TRY_LVL_VAL,
    D_SPT_IT_HUMD,
    D_SPT_IT_HUMD_VAL,
    D_SPT_IT_RM_TEMP,
    D_SPT_IT_RM_TEMP_VAL,
    D_SPT_IT_TEMP,
    D_SPT_IT_TEMP_VAL,
    D_SPT_MCO_PRES,
    D_SPT_MCO_PRES_VAL,
    D_SPT_PMC_PRES,
    D_SPT_PMC_PRES_VAL,
    D_SPT_PRM_TEMP,
    D_SPT_PRM_TEMP_VAL,
    D_SPT_AZM_M1,
    D_SPT_AZM_M1_HRS,
    D_SPT_AZM_M2,
    D_SPT_AZM_M2_HRS,
    D_SPT_ALT_M1,
    D_SPT_ALT_M1_HRS,
    D_SPT_ALT_M2,
    D_SPT_ALT_M2_HRS,
    D_SPT_CAS_M1,
    D_SPT_CAS_M1_HRS,
    D_SPT_CAS_M2,
    D_SPT_CAS_M2_HRS,
    D_SPT_PRM_FAN,
    D_SPT_PRM_FAN_HRS,
    D_SPT_AIR_COMP,
    D_SPT_AIR_COMP_HRS,
    D_SPT_HYD_PMP,
    D_SPT_HYD_PMP_HRS,
    D_SPT_HYD_CHL_PMP,
    D_SPT_HYD_CHL_PMP_HRS,
    D_SPT_HYD_CHL,
    D_SPT_HYD_CHL_HRS,
    D_SPT_HYD_PSCV,
    D_SPT_HYD_PSCV_HRS,
    D_SPT_HYD_SCV_PMP,
    D_SPT_HYD_SCV_PMP_HRS,
    D_SPT_HYD_AZM_GB,
    D_SPT_HYD_AZM_GB_OPS,
    D_SPT_HYD_AZM_LB,
    D_SPT_HYD_AZM_LB_OPS,
    D_SPT_HYD_ALT_GB,
    D_SPT_HYD_ALT_GB_OPS,
    D_SPT_HYD_ALT_LB,
    D_SPT_HYD_ALT_LB_OPS,
    D_SPT_MCO,
    D_SPT_MCO_OPS,
    D_SPT_SPLC_STATUS,
    D_SPT_SPLC_VER_MAJOR,
    D_SPT_SPLC_VER_MINOR,
    D_SPT_STATUS_EOL,
    D_SPT_DATAID_EOL
  };


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String name of this type-safe enumeration.
   */
  private transient String name;

  /**
   * Optional integer for int representation of this Type-safe Enumeration.
   */
  private transient int intValue;

  /**
   * Assign an index to this enumeration.
   */
  private final int index = nextIndex++;

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Return an object reference of the SPT_DataType with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the SPT_DataType
   * @return a reference to the SPT_DataType specified by the argument
   */
  public static SPT_DataType getReference( String s )
  {
    return( (SPT_DataType)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the SPT_DataType with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the SPT_DataType
   * @return a reference to the SPT_DataType specified by the argument
   */
  public static SPT_DataType getReference( int i )
  {
    return( (SPT_DataType)( intHash.get( new Integer( i ) ) ) );
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
  private SPT_DataType( String s )
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
  private SPT_DataType( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i + ( ttlPackage.getInt() << 16 );
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this SPT_DataType.
   * @return name
   * @see #name
   */
  public final String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this SPT_DataType.
   * @return intValue
   * @see #intValue
   */
  public final int getInt()
  {
    return intValue;
  }


  /**
   * Return the TTL_Package for which this data refers.
   * @return ttlPackage
   * @see #ttlPackage
   */
  public TTL_Package getTTL_Package()
  {
    return( ttlPackage );
  }


  /**
   * Over-ride the Serializable method to ensure the same Object references
   * are returned after Serialization.
   */
  private Object readResolve() throws java.io.ObjectStreamException
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
 *    $Date: 2006-11-20 14:47:01 $
 * $RCSfile: SPT_DataType.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/spt/SPT_DataType.java,v $
 *      $Id: SPT_DataType.java,v 1.1 2006-11-20 14:47:01 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
