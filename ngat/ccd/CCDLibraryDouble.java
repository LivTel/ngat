// CCDLibraryDouble.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/ccd/CCDLibraryDouble.java,v 0.1 1999-01-21 15:45:18 dev Exp $
class CCDLibraryDouble
{
	private double value;
	public final static String RCSID = new String("$Id: CCDLibraryDouble.java,v 0.1 1999-01-21 15:45:18 dev Exp $");

	public void setValue(double value)
	{
		this.value = value;
	}

	public double getValue()
	{
		return value;
	}
}

//
// $Log: not supported by cvs2svn $
//