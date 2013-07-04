package ngat.ngtcs.subsystem;

import ngat.ngtcs.*;

/**
 * 
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public abstract class Autoguider extends BasicMechanism
{
  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id: Autoguider.java,v 1.2 2013-07-04 10:52:40 cjm Exp $" );


  /**
   *
   */
  public Autoguider()
  {
    super();
  }


  /**
   *
   */
  public void initialise( Telescope t ) throws InitialisationException
  {
    super.initialise( t );
  }
    
}
/*
 *    $Date: 2013-07-04 10:52:40 $
 * $RCSfile: Autoguider.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/Autoguider.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:46  je
 *     Initial revision
 *
 */
