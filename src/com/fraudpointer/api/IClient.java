package com.fraudpointer.api;

import com.fraudpointer.api.models.AssessmentSession;
import com.fraudpointer.api.models.Event;
import com.fraudpointer.api.models.FraudAssessment;

/**
 * IClient is the interface that each java client should implement in order to be used as a client proxy to access
 * FraudPointer Service. </b>Important Note: </b> java client jar comes with a ready to use implementation of this service. You need to use
 * ClientFactory to instantiate this implementation and start using FraudPointer Service.<br/>
 * <br/>
 * As a user of the FraudPointer Client Library you should declare a variable of IClient type and then use ClientFactory.construct() to get an
 * instance of a class that implements this interface.<br/>
 * <br/>
 * <span id='sample_code'>Below, you can see a sample client that uses IClient to communicate with FraudPointer Server:</span><br/>
 * <br/>
 *
 * <iframe src='{@docRoot}/src-html/com/fraudpointer/ConsoleClientExample.html' width="100%" height="350px"></iframe>
 *
 */
public interface IClient {
    /**
     * Communicates with FraudPointer Server and gets back a ready to use AssessmentSession object.This is the first method that you should call. <br/>
     * <br/>
     * It should be used as soon as you want to start using the FraudPointer service. Without an AssessmentSession object
     * you cannot do anything.<br/>
     * <br/>
     * @return An object of type AssessmentSession as returned by FraudPointer Server. Object should contain the AssessmentSession.id
     * that uniquely identifies this AssessmentSession. You should use this AssessmentSession.id in whichever place the
     * AssessmentSession id is required.<br/>
     * <br/>
     * Method always returns a valid object, unless an exception is thrown.
     * @throws ClientException It may throw an ClientException if an error occurs
     */
    AssessmentSession createAssessmentSession () throws ClientException;

    /**
     * Appends an Event to an AssessmentSession object. <br/>
     * <br/>
     * This method also communicates with the FraudPointer Server, which will store all Event data alongside with the AssessmentSession
     * given.<br/>
     * <br/>
     * You can call this method as many times as you want (as long as you pass a new Event on each call).
     * It is actually recommended to do so. In other words, it is recommended to create various Events, of
     * various types and send them to FraudPointer to be stored against an AssessmentSession.
     * <br/>
     *
     * @param assessmentSession Should be constructed with a call to createAssessmentSession()
     * @param paramEvent Should be constructed with a call to new Event(event_type)
     * @return An object of type Event as returned by the FraudPointer Server.<br/>
     * <br/>
     * Method always returns a valid object, unless an exception is thrown.
     * @throws ClientException It may throw a ClientException if an error occurs
     *
     */
    Event appendEventToAssessmentSession (AssessmentSession assessmentSession, Event paramEvent) throws ClientException;

    /**
     * This method should be called in order to evaluate the fraud level of an AssessmentSession. <br/>
     * <br/>
     * This method should be called after having sent to FraudPointer Server one or more Events. It is used
     * to evaluate the fraud risk of the AssessmentSession. Note that you can ask multiple interim 
     * Fraud Assessments, but you can only ask for one final (non-interim) Fraud Assessment. Interim Fraud Assessments
     * can be requested in a cycle of interactions with the FraudPointer Server such as the following:<br/>
     * <br/>
     * <ul>
     * <li> create assessment session </li>
     * <li> create event  </li>
     * <li> add data to event  </li>
     * <li> add data to event  </li>
     * <li> ...  </li>
     * <li> add data to event  </li>
     * <li> append event to assessment session  </li>
     * <li> create another event </li>
     * <li> add data to new event </li>
     * <li> add data to new event </li>
     * <li> ...  </li>
     * <li> add data to new event  </li>
     * <li> append new event to assessment session </li>
     * <li> create interim fraud assessment </li>
     * <li> create one more event </li>
     * <li> add data to last created event </li>
     * <li> add data to last created event </li>
     * <li> ... </li>
     * <li> add data to last created event </li>
     * <li> append last created event to assessment session </li>
     * <li> create interim fraud assessment </li>
     * <li> .... </li>
     * <li> create final (non<li>interim) fraud assessment </li>
     * </ul>
     * <br/>
     * The last and only final (non-interim) fraud assessment will also create a CASE in FraudPointer Application.
     * <br/>
     * @param assessmentSession A valid AssessmentSession, previously created with createAssessmentSession()
     * @param interim A boolean value true or false. If true, then an interim FraudAssessment will be created.
     * If false, a final (non-interim) FraudAssessment will be created
     * @return A FraudAssessment valid object filled in with information returned by FraudPointer Server. It contains the Fraud
     * Assessment Result.
     * @throws ClientException It may throw a ClientException if an error occurs
     */
    FraudAssessment createFraudAssessment (AssessmentSession assessmentSession, boolean interim) throws ClientException;

    /**
     * Gets the results of a previously assessed session. <br/>
     * <br/>
     * Use this method to retrieve a previously created Fraud assessment.
     * When you create a fraud assessment with createFraudAssessment you receive
     * the result of the assessment and its Id. Using the session id and the assessment id you can retrieve that result
     * with this method.<br/>
     * <br/>
     * @param assessmentSession A valid AssessmentSession, previously created with createAssessmentSession()
     * @param assesmentId A valid assessment id previously obtained with createFraudAssessment()
     * @return A FraudAssessment valid object filled in with information that is related to this Fraud Assessment like Profile and Case
     * @throws ClientException It may throw a ClientException if an error occurs
     */
    FraudAssessment getFraudAssessment (AssessmentSession assessmentSession, String assesmentId) throws ClientException;

    /**
     * Use this method to generate a hash of a credit card number. Use the generated hash to send the encrypted credit card number
     * to FraudPointer Server instead of the credit card number itself. <br/>
     * <br/>
     * FraudPointer Server tries to identify the existence of the same credit card number in various transactions. These
     * transactions either take place during the same session or take place in different sessions, but at the same time, or
     * took place in a session in the past. However, FraudPointer Server does not want to store the credit card numbers in clear format
     * and it does not need to do that in order to accomplish its goal. Hence, you need to encrypt them using the method provided here.
     * Note that hash is one-way encryption method and FraudPointer Server cannot derive the credit card number from the hash.
     * <br/>
     * This method does not communicate with the FraudPointer Server to generate the hash. Works locally.<br/>
     * <br/>
     * Also, note that there is a sample console application that only encrypts a credit card. The class is called EncryptCreditCard
     * and you can see the code here: <a href='{@docRoot}/src-html/com/fraudpointer/EncryptCreditCard.html'>EncryptCreditCard</a>. You can
     * quickly run this utility from the command line with the following command too:<br/>
     * <br/>
     * <code>java -cp fraudpointer_client.jar com.fraudpointer.EncryptCreditCard <a_credit_card_number_to_encrypt></code><br/>
     * <br/>
     * and it will print for you the string that will be sent to Fraudpointer server if that particular card is used in a transaction.
     * <br/>
     * @param creditCardNumber The credit card number that you want to get its hash value.
     * @return The hash of the credit card number. Note that if an empty string is returned, then something has gone wrong during the encryption.
     * in that case, method throws a ClientException.
     */
    String creditCardHash (String creditCardNumber) throws ClientException;

} // interface IClient
//--------------------
