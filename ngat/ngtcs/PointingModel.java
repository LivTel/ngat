package ngat.ngtcs;

import java.util.*;

import ngat.ngtcs.common.*;

/**
 * The top-level pointing model class.  This abstract class contains generic
 * fields and methods and provides the generic class-cast.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public abstract class PointingModel
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: PointingModel.java,v 1.1 2003-07-01 10:11:30 je Exp $" );


    /**
     * List of coefficient objects to be used when applying/removing
     * corrections.
     */
    protected List coefficientList;


    /**
     *
     */
    public PointingModel()
    {

    }


    /**
     *
     */
    public List getCoefficientList()
    {
	return coefficientList;
    }


    /**
     *
     */
    public void addCoefficients( PointingModelCoefficients p )
    {
	coefficientList.add( p );
    }


    /**
     *
     */
    public void removeCoefficients( PointingModelCoefficients p )
    {
	coefficientList.remove( p );
    }


    /**
     * Initialise this pointing model.
     */
    public abstract void initialise( Telescope t )
	throws InitialisationException;

    /**
     *
     */
    public abstract XYZMatrix applyAttitudeCorrection( XYZMatrix m );

    public abstract XYZMatrix applyPositionCorrection( XYZMatrix m );

    public abstract double applyRotatorCorrection( double d );

    public abstract XYZMatrix removeAttitudeCorrection( XYZMatrix m );

    public abstract XYZMatrix removePositionCorrection( XYZMatrix m );

    public abstract double removeRotatorCorrection( double d );






    // Added by je 02/12/02
    //
    // based on having a set of different coefficients:
    // permanent, session, accurate
    // that are created by the pointing model (so that the PM has a reference)
    // and can be set/got externally to the pointing model and used in the
    // pointing model object.

    public abstract void updateCoefficients( PointingModelCoefficients p,
					     XYZMatrix desired,
					     XYZMatrix achieved );

}
/*
 *    $Date: 2003-07-01 10:11:30 $
 * $RCSfile: PointingModel.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/PointingModel.java,v $
 *      $Id: PointingModel.java,v 1.1 2003-07-01 10:11:30 je Exp $
 *     $Log: not supported by cvs2svn $
 */
