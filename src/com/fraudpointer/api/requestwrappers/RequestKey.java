package com.fraudpointer.api.requestwrappers;

/**
 * Whatever we send over to Fraudpointer Server needs to have the api key. So {@link com.fraudpointer.api.models.Event Event} and
 * {@link com.fraudpointer.api.models.FraudAssessment FraudAssessment} need to be wrapped before serialization to <code>json</code>
 * format and sent over with the api key. And this class is the parent class that will hold the api key part.<br/>
 * <br/>
 * <b>Note: </b>{@link com.fraudpointer.api.ClientFactory#construct ClientFactory.construct()} method returns an object that implements {@link com.fraudpointer.api.IClient IClient} interface
 * and hides all the complexity of sending to and receiving data from Fraudpointer Server. If you use this object, you will not be worry about internals of communications.
 */
public class RequestKey {

    public String key;

} // class RequestKey
//----------------------
