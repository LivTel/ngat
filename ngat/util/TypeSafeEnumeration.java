package ngat.util;

/**
 * This interface is the basic form of TypeSafeEnumerations.
 * <p>
 * The instances of each class implementing this interface <i>must</i> be
 * created <i>within</i> each implementing sub-class.  The constructor(s)
 * <i>must</i> be inaccessible (either <code>protected</code> or
 * <code>private</code> preventing extra instances from being created.
 * <p>
 * <b>NOTE:</b><br>
 * When serializing TypeSafeEnumerations, to prevent different references of
 * each enumeration, the <code>implements java.io.Serializable</code> must of
 * course be used, but the <code>readResolve</code> method <i>must</i> be
 * re-implemented in a fashion similar to:
 * <pre>
 * private Object readResolve()
 * {
 *   return( array[ index ] );
 * }
 * </pre>
 * where <code>array</code> contains all the possible references, created as a
 * final static array.
 * @author $Author: snf $ 
 * @version $Revision: 1.4 $
 */
public interface TypeSafeEnumeration
{
  public String getName();

  public int getInt();
}
/*
 *    $Date: 2007-02-28 06:14:24 $
 * $RCSfile: TypeSafeEnumeration.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/util/TypeSafeEnumeration.java,v $
 *      $Id: TypeSafeEnumeration.java,v 1.4 2007-02-28 06:14:24 snf Exp $
 *     $Log: not supported by cvs2svn $
 */
