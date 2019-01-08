package ngat.phase2;

/** A proposal is an observing program or part of a program.  A proposal is owned by
* a TAG (which allocates resources), a user (the principle investigator) and may
* also be part of a larger program. 
* A proposal is in an active state and potentially available for scheduling between 
* its activation and expiry dates. 
* @see IProgram.
*/
public interface IProposal extends IPhase2, IPhase2Identity {

    /** Returns the owning TAG ID.*/
    public String getTagId();

    /** Returns the owning User ID.*/
    public String getUserId();

    /** Returns the owning Program ID.*/
    public String getProgramId();

    /** Returns the Activation date for this Proposal.*/
    public long getActivationDate();

    /** Returns the Expiry date for this Proposal.*/
    public long getExpiryDate();

    /** Returns the PUST abstract for this Proposal. This is a description of the science
     * objectives of the proposal in simple terms for a non-scientific audience.
     * ### This should probably be a javax.swing.text.Document or an org.w3c.dom.Document.
     */
    public String getPustAbstract();

    /** Returns the Science abstract for this Proposal. This is a description of the 
     * science objectives of the proposal for a scientific audience.
     * ### This should probably be a javax.swing.text.Document or an org.w3c.dom.Document.
     */
    public String getScienceAbstract();


}
