package ngat.util;

/**
 * Abstract super-class used to implement type-safe enumerations.  To be
 * sub-classed by any class requiring finite, predefined instances in the same
 * manner as the C type <code>enum</code>.
 * <p>
 * The Object methods have been over-ridden and finalized to ensure they cannot
 * be malformed by sub-classes, and the Serializable method
 * <code>readResolve</code> has been over-ridden to allow serialization of any
 * sub-class.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public abstract class TypeSafeEnumeration implements java.io.Serializable
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
	new String( "$Id: TypeSafeEnumeration.java,v 1.1 2002-10-29 16:56:36 je Exp $" );

    /**
     * Hashtable of instances for retrieval by the enumeration's String name.
     */
    protected static java.util.Hashtable nameHash = new java.util.Hashtable();

    /**
     * Hashtable of instances for retrieval by the enumeration's int value.
     */
    protected static java.util.Hashtable intHash = new java.util.Hashtable();

    /**
     * Array of all the enumerations in this class.  This field <b>MUST</b> be
     * assigned to the complete list of enumerations to enable correct
     * serialization of this class.
     */
    protected static final TypeSafeEnumeration[] array = {};

    /**
     * List of all the enumerations in this class.
     */
    public static final java.util.List list =
	java.util.Collections.unmodifiableList
	( java.util.Arrays.asList( array ) );

    /**
     * Ordinal of the next enumeration to be added.
     */
    protected static int nextOrdinal = 0;

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * String name of this type-safe enumeration.
     */
    protected String name;

    /**
     *
     */
    protected int intValue;

    /**
     * Assign an ordinal to this enumeration.
     */
    protected final int ordinal = nextOrdinal++;

    /*=======================================================================*/
    /*                                                                       */
    /* CLASS METHODS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Return an object reference of the TypeSafeEnumeration with the String
     * name specified.
     * <p>
     * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
     * returned.
     * @param s the name of the TypeSafeEnumeration
     * @return a reference to the TypeSafeEnumeration specified by the argument
     */
    public static TypeSafeEnumeration getReference( String s )
    {
        return( (TypeSafeEnumeration)( nameHash.get( s ) ) );
    }


    /**
     * Return an object reference of the TypeSafeEnumeration with the int value
     * specified.
     * <p>
     * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
     * returned.
     * @param i the int representation of the TypeSafeEnumeration
     * @return a reference to the TypeSafeEnumeration specified by the argument
     */
    public static TypeSafeEnumeration getReference( int i )
    {
        return( (TypeSafeEnumeration)( intHash.get( new Integer( i ) ) ) );
    }


    /**
     * Return a java.util.List of enumerations in this class.
     * @return list
     * @see #list
     */
    public static java.util.List getList()
    {
	return list;
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
     * enumeration is assigned to the ordinal (index) of this enumeration in
     * the array.
     * @param s the name of this enumeration
     * @see #name
     * @see #intValue
     * @see #array
     */
    protected TypeSafeEnumeration( String s )
    {
        name = s;
        nameHash.put( s, this );
	intValue = ordinal;
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
    protected TypeSafeEnumeration( String s, int i )
    {
        name = s;
        nameHash.put( s, this );
	intValue = i;
        intHash.put( new Integer( intValue ), this );
    }


    /**
     * Return the name of this TypeSafeEnumeration.
     * @return name
     * @see #name
     */
    public final String getName()
    {
	return name;
    }


    /**
     * Return the int representation of this TypeSafeEnumeration.
     * @return intValue
     * @see #intValue
     */
    public final int getInt()
    {
	return intValue;
    }


    /**
     * Over-ride the Serializable method to ensure the same Object references
     * are returned after Serialization.
     */
    protected final Object readResolve() throws java.io.ObjectStreamException
    {
	return array[ ordinal ];
    }


    /**
     * Implemented to prevent over-riding in sub-classes.  This method calls
     * the same method in Object.
     */
    public final boolean equals( Object o )
    {
	return super.equals( o );
    }


    /**
     * Implemented to prevent over-riding in sub-classes.  This method calls
     * the same method in Object.
     */
    public final int hashCode()
    {
	return super.hashCode(); 
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
 *    $Date: 2002-10-29 16:56:36 $
 * $RCSfile: TypeSafeEnumeration.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/util/TypeSafeEnumeration.java,v $
 *      $Id: TypeSafeEnumeration.java,v 1.1 2002-10-29 16:56:36 je Exp $
 *     $Log: not supported by cvs2svn $
 */
