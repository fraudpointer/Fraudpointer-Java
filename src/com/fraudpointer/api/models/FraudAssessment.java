package com.fraudpointer.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * This is an object that is returned by FraudPointer Server when requesting a Fraud Assessment. </br>
 * <br/>
 * {@link com.fraudpointer.api.models.FraudAssessment FraudAssessment} is returned back to you when you ask to create for a Fraud Assessment or when you ask to get a Fraud Assessment.
 * The API to create a Fraud Assessment is {@link com.fraudpointer.api.IClient#createFraudAssessment(AssessmentSession, boolean)}. The API to get a Fraud Assessment is
 * {@link com.fraudpointer.api.IClient#getFraudAssessment(AssessmentSession, String)}</br>
 * <br/>
 * <br/>
 * The most important properties of the object that you need to take into account are:<br/>
 * <br/>
 * <ul>
 *   <li><code>result</code>: Gives the Assessment Result. Can take one of the values:
 *    <ul>
 *        <li>"Accept"</li>
 *        <li>"Review"</li>
 *        <li>"Reject"</li>
 *    </ul>
 *   </li>
 *
 *   <li><code>score</code>: An integer value that gives the Assessment Score</li>
 *
 *   <li><code>decidingFactor</code>: Gives a hint about why the Assessment gave such a <code>result</code>. Takes the values:
 *      <ul>
 *          <li>"Profile thresholds": This means that the FraudAssessment.Score fell into a range that, according to Profile thresholds,
 *          returned a result equal to the value that exists in the FraudAssessment.Result property.</li>
 *          <li>or the name of the Deciding Rule.</li>
 *      </ul>
 *   </li>
 *
 *   <li><code>profile</code>: The name of the {@link com.fraudpointer.api.models.Profile Profile} that has been used to carry out the assessment.</li>
 *
 * </ul>
 * <br/>
 * It is up to your business to decide what to do with this <code>result</code>. For example, if the <code>result</code> is "Reject",
 * you can stop from carrying out the actual transaction with your bank.
 */
public class FraudAssessment {
    /**
     * Unique <code>Id</code> as returned by the FraudPointer Server. <br/>
     */
    public String id;

    /**
     * The Score of the Fraud Assessment. <br/>
     * <br/>
     * The Score of the Fraud Assessment has significance only if the Deciding Factor is "Profile thresholds".
     * If the Deciding Factor has the name of a Rule, then do not take into account Score.
     */
    public String score;

    /**
     * Boolean value to indicate interim or final (non-interim) Fraud Assessment. <br/>
     * <br/>
     *
     * Indicates whether the Fraud Assessment is final (non-interim) or not (interim). <code>true</code> means that
     * the Fraud Assessment is interim (non-final) whereas <code>false</code> means that Fraud Assessment is
     * final (non-interim). Note that final Fraud Assessments create a Case in Fraud Pointer Application.
     */
    public boolean interim;

    /**
     * An indicator about how the FraudAssessment Result has been calculated. <br/>
     * <br/>
     * The Deciding Factor takes two values:<br/>
     * <br/>
     * <ul>
     *     <li>"Profile thresholds"</li>
     *     <li>Or the name of the Deciding Rule that matched</li>
     * </ul>
     * <br/>
     * If the Deciding Factor has the value "Profile thresholds", then you can see the FraudAssessment Score that the
     * Fraud Assessment calculated. This FraudAssessment Score normally falls into one of the ranges that are defined in the
     * Profile level that has been used to carry out the Fraud Assessment. According to this range, the
     * Result is set.<br/>
     * <br/>
     * If the Deciding Factor has a value different from "Profile thresholds", then it has the value of
     * the Rule that matched. That Rule is a Deciding Rule and its FraudAssessment.Result was set as Result of the Fraud Assessment
     * at hand.<br/>
     * <br/>
     */
    @SerializedName("deciding_factor") public String decidingFactor;

    /**
     * The Result of the Fraud Assessment. <br/>
     * <br/>
     * Takes the values:<br/>
     * <br/>
     * <ul>
     *    <li>"Accept"</li>
     *    <li>"Reject"</li>
     *    <li>"Review"</li>
     * </ul>
     * <br/>
     * You need to evaluate the Result of the Fraud Assessment and take necessary actions.
     */
    public String result;

    /**
     * The Profile used to carry out the Fraud Assessment. <br/>
     * <br/>
     *  The Fraud Assessment uses a set of Fraud Assessment Rules that belong to one specific Profile. The Profile is
     *  selected at run-time using the Profile Selection Rules. This property returns a Profile object
     *  that has been selected in order to carry out the Fraud Assessment at hand.
     */
    public Profile profile;

    /**
     * The case generated with the assessment. <br/>
     * <br/>
     * When a final Fraud Assessment is created a Case is created too. This field will contain the Case information
     * when you created a Final Assessment or when you get a previous Fraud Assessment.
     */
    @SerializedName("case") public Case kase;

    /**
     * Last time this Fraud Assessment was updated.
     */
    @SerializedName("updated_at") public Date updatedAt;

} // class FraudAssessment
//-------------------------
