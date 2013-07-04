package ngat.ngtcs.common;

import java.lang.Double;
import java.lang.String;

import ngat.util.TypeSafeEnumeration;

/**
 * Definitions of Equinoxes
 * 
 * @version $Revision: 1.2 $
 * @author $Author: cjm $
 */
public class Equinox
  implements java.io.Serializable, ngat.util.TypeSafeEnumeration
{
  /*=========================================================================*/
  /*                                                                         */
  /* CLASS FIELDS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

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

  /*=========================================================================*/
  /*                                                                         */
  /* ENUMERATIONS                                                            */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Equinox B1950
   */
  public static Equinox B1950 = new Equinox
    ( "B1950",
      new Epoch( CalendarType.BESSELIAN, 1950.0, TimescaleType.TDB ),
      2031 );

  /**
   * Equinox FK4
   */
  public static Equinox FK4 = B1950;

  /**
   * Equinox J2000
   */
  public static Equinox J2000 = new Equinox
    ( "J2000", 
      new Epoch( CalendarType.JULIAN, 2000.0, TimescaleType.TDB ),
      2032 );

  /**
   * Equinox FK5
   */
  public static Equinox FK5 = J2000;

  /**
   * ICRF
   */
  public static Equinox ICRF = 
    new Equinox( "ICRF", 2033 );

  /**
   * APPARENT Equinox
   */
  public static Equinox APPARENT = 
    new Equinox( "APPARENT", 2034 );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Name of Equinox
   */
  protected String name = "J2000";

  /**
   * Constant integer representation of the standard Equinoxes, 
   * set to 0 (zero) for non-standard values (B1950, J2000, ICRF, APPARENT ).
   * Primarily implemented for JNI interaction
   */
  protected int eqInt = 2032;

  /**
   * Epoch of this Equinox for non ICRF/APPARENT Equinoxes.
   */
  protected Epoch epoch = new Epoch( 2000.0 );

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
   *
   */
  public static Equinox getReference( String s )
  {
    return( (Equinox)( nameHash.get( s.trim() ) ) );
  }


  /**
   *
   */
  public static Equinox getReference( int i )
  {
    return( (Equinox)( intHash.get( new Integer( i ) ) ) );
  }

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Private constructor used in this class to implement the standard
   * equinoxes.
   * @param newName name of this standard Equinox
   * @param newEpoch epoch of this standard Equinox
   * @param newInt integer representation of this standard Equinox
   */
  protected Equinox( String newName, Epoch newEpoch, int newInt )
  {
    name  = newName;
    epoch = newEpoch;
    eqInt = newInt;
    nameHash.put( newName , this );
    intHash.put( new Integer( newInt ), this );
   }


  /**
   * Private constructor used in this class to implement the standard
   * equinoxes.
   * @param newName name of this standard Equinox
   * @param newInt integer representation of this standard Equinox
   */
  protected Equinox( String newName, int newInt )
  {
    name  = newName;
    eqInt = newInt;
    nameHash.put( newName, this );
    intHash.put( new Integer( newInt ), this );
  }


  /**
   * Public constructor for a standard equinox of the specified name.
   * Non-standard names will throw the IllegalArgumentException.  
   * The test is case insensitve.
   * @param s the name of the Equinox (B1950, FK4, FK5, J2000, ICRF, APPARENT );
   */
  public Equinox( String s ) throws java.lang.IllegalArgumentException
  {
    if( ( s.equalsIgnoreCase( Equinox.B1950.getName() ) ||
	  ( s.equalsIgnoreCase( "fk4" ) ) ) )
    {
      epoch = B1950.getEpoch();
      eqInt = B1950.getInt();
    }
    else if( ( s.equalsIgnoreCase( Equinox.J2000.getName() ) ) ||
	     ( s.equalsIgnoreCase( "fk5" ) ) )
    {
      epoch = J2000.getEpoch();
      eqInt = J2000.getInt();
    }
    else if( s.equalsIgnoreCase( Equinox.ICRF.getName() ) )
    {
      eqInt = ICRF.getInt();
    }
    else if( s.equalsIgnoreCase( Equinox.APPARENT.getName() ) )
    {
      eqInt = APPARENT.getInt();
    }
    else
    {
      throw new java.lang.IllegalArgumentException
	( "unrecognised standard equinox name ["+s+"] " );
    }
  }


  /**
   * Public constructor for an equinox of the specified epoch.
   * Standard epochs (B1950, J2000) are recognised.
   * @param epoch the epoch of this Equinox
   * @see Epoch
   */
  public Equinox( Epoch e )
  {
    if( e.equals( B1950.getEpoch() ) )
    {
      name  = B1950.getName();
      eqInt = B1950.getInt();
    }
    else if( e.equals( Equinox.J2000.getEpoch() ) )
    {
      name  = B1950.getName();
      eqInt = B1950.getInt();
    }
    else 
    {
      /* Non-standard Epochs */
    }
    epoch = e;
  }


  /**
   * Return the name of this Equinox
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the epoch of this Equinox
   * @return epoch
   * @see #epoch
   */
  public Epoch getEpoch()

  {
    return epoch;
  }


  /**
   * Return the integer representation of this epoch.
   * @return eqInt
   * @see #eqInt
   */
  public int getInt()
  {
    return eqInt;
  }


  /**
   * Return a String representation of this Equinox in the form:
   * <code>calendar date timescale</code>.
   * @return string representation of this Equinox
   */
  public String toString()
  {
    return( epoch.getCalendarType().getName()+" (int:"+
	    epoch.getCalendarType().getInt()+") "+
	    epoch.getDate()+" "+
	    epoch.getTimescaleType().getName()+" (int:"+
	    epoch.getTimescaleType().getInt()+")" );
  }
}
/*
 *    $Date: 2013-07-04 10:37:25 $
 * $RCSfile: Equinox.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/Equinox.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:04  je
 *     Initial revision
 *
 */
