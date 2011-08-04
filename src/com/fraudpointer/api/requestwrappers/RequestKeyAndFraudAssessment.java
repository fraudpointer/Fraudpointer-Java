package com.fraudpointer.api.requestwrappers;

import com.fraudpointer.api.models.FraudAssessment;
import com.google.gson.annotations.SerializedName;

/**
 * {@link com.fraudpointer.api.models.FraudAssessment FraudAssessment} needs to be wrapped before serialization to <code>json</code>
 * format.This class helps carry out this wrapping.<br/>
 * <br/>
 * <b>Note: </b>{@link com.fraudpointer.api.ClientFactory#construct ClientFactory.construct()} method returns an object that implements {@link com.fraudpointer.api.IClient IClient} interface
 * and hides all the complexity of sending to and receiving data from Fraudpointer Server. If you use this object, you will not be worry about internals of communications.
 */
public class RequestKeyAndFraudAssessment extends RequestKey {

    @SerializedName("fraud_assessment") public FraudAssessment fraudAssessment;

} // class RequestKeyAndFraudAssessment
// -------------------------------------

