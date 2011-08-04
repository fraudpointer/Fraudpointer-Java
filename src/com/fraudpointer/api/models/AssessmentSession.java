package com.fraudpointer.api.models;

/**
 * An Assessment Session is the object returned when you call {@link com.fraudpointer.api.IClient#createAssessmentSession()}, which is the
 * first method that you need to call to start interacting with the FraudPointer Server.<br/>
 * <br/>
 * When you want to start interaction with FraudPointer Server you need to call {@link com.fraudpointer.api.IClient#createAssessmentSession()}. This
 * method will return to you an instance of AssessmentSession. After its creation, you use this object in the
 * following cases:<br/>
 * <br/>
 * <ul>
 *     <li>When you want to add an Event object to this Assessment Session ({@link com.fraudpointer.api.IClient#appendEventToAssessmentSession(AssessmentSession, Event)}).</li>
 *     <li>When you want to create {@link com.fraudpointer.api.IClient#createFraudAssessment(AssessmentSession, boolean)} }or get a FraudAssessment {@link com.fraudpointer.api.IClient#getFraudAssessment(AssessmentSession, String)}}.</li>
 *     <li>When you want to embed the session id in your html content, in the hidden field value
 *     that is used by Fraudpointer javascript (see: <a href="{@docRoot}/com/fraudpointer/api/package-summary.html#device_fingerprinting">Device Fingerprinting</a>)</li>
 * </ul>
 */
public class AssessmentSession {

    /**
     * Unique id for the created session returned by the server. <br/>
     * <br/>
     * When you call {@link com.fraudpointer.api.IClient#createAssessmentSession()} you get an instantiated object whose only property is
     * AssessmentSession.id. This <code>id</code> itself, is useful when you want to embed it in your html content, in
     * the hidden field value that is used by FraudPointer javascript <c>fp.js</c>
     */
    public String id;

} // class AssessmentSession
//--------------------------



