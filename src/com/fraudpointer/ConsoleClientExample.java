package com.fraudpointer;

import com.fraudpointer.api.ClientFactory;
import com.fraudpointer.api.IClient;
import com.fraudpointer.api.models.AssessmentSession;
import com.fraudpointer.api.models.Event;
import com.fraudpointer.api.models.FraudAssessment;
import java.util.Calendar;

/**
 * This is a java console application that communicates with Fraudpointer Server. It basically does the following:<br/>
 * <br/>
 * <ol>
 *     <li>Creates an instance of an object that implements the IClient interface.</li>
 *     <li>Creates an AssessmentSession.</li>
 *     <li>Creates one or more Events and fills them in with data.</li>
 *     <li>For each Event that is ready, it sends it to Fraudpointer Server</li>
 *     <li>Creates and gets back a final FraudAssessment.</li>
 *     <li>For API demonstration reasons, at the end, it only gets back the FraudAssessment previously created.</li>
 * </ol>
 * <br/>
 * Note that this class is packaged in <code>fraudpointer_client.jar</code> too, as the Main class of the jar.
 */
public class ConsoleClientExample {

    public static void main (String[] args)
    {
        if ( args.length != 2 )
        {
            wrongSyntax();
            return;
        }

        String baseFraudpointerApiUrl = args[0];
        String apiKey = args[1];

        try {
            IClient fraudpointerClient = ClientFactory.construct(baseFraudpointerApiUrl, apiKey);
            AssessmentSession assessmentSession = fraudpointerClient.createAssessmentSession();

            System.out.println("Assessment Session returned: id " + assessmentSession.id);

            // 1st Event: GenericEvent

            Event event = new Event(Event.GenericEvent);

            event.addData("BookingCode", "1234");
            event.addData("MERCHANT_REFERENCE", "4321");

            Calendar startDate = Calendar.getInstance();
            startDate.set(Calendar.YEAR, 2011);
            startDate.set(Calendar.MONTH, 6-1); /* June */
            startDate.set(Calendar.DAY_OF_MONTH, 30);
            startDate.set(Calendar.HOUR_OF_DAY, 23);
            startDate.set(Calendar.MINUTE, 55);
            startDate.set(Calendar.SECOND, 59);
            event.addData("StartDate", startDate);

            Calendar reservationDate = Calendar.getInstance();
            reservationDate.set(Calendar.YEAR, 2011);
            reservationDate.set(Calendar.MONTH, 6-1); /* June */
            reservationDate.set(Calendar.DAY_OF_MONTH, 21);
            reservationDate.set(Calendar.HOUR_OF_DAY, 8);
            reservationDate.set(Calendar.MINUTE, 10);
            reservationDate.set(Calendar.SECOND, 0);
            event.addData("ReservationDate", reservationDate);

            fraudpointerClient.appendEventToAssessmentSession(assessmentSession, event);
            //---------------------

            // 2nd Event:  Failed Payment

            event = new Event(Event.FailedPayment);

            Calendar attemptDate = Calendar.getInstance();
            attemptDate.set(Calendar.YEAR, 2011);
            attemptDate.set(Calendar.MONTH, 6-1); /* June */
            attemptDate.set(Calendar.DAY_OF_MONTH, 21);
            attemptDate.set(Calendar.HOUR_OF_DAY, 8);
            attemptDate.set(Calendar.MINUTE, 11);
            attemptDate.set(Calendar.SECOND, 0);
            event.addData("E_TRAVEL_SA_PURCHASE_DATE", attemptDate)
                 .addData("CC_HASH", fraudpointerClient.creditCardHash("5431771356443683"))
                 .addData("BILLING_ADDRESS_STREET_NAME", "Voulis")
                 .addData("PURCHASE_AMOUNT", 49.9 );

            fraudpointerClient.appendEventToAssessmentSession(assessmentSession, event);
            //---------------------

            FraudAssessment fraudAssessment = fraudpointerClient.createFraudAssessment(assessmentSession, false);
            System.out.println("FraudAssessment: ID: " + fraudAssessment.id + ", Score: " + fraudAssessment.score);

            // just in case you want to retrieve the details of a fraud assessment later on
            FraudAssessment fraudAssessmentRetrievedFromServer = fraudpointerClient.getFraudAssessment(assessmentSession, fraudAssessment.id);
            System.out.println("FraudAssessment: ID: " + fraudAssessmentRetrievedFromServer.id + ", Score: " + fraudAssessmentRetrievedFromServer.score);

        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }

    } // main ()
    //-----------

    protected static void wrongSyntax()
    {
        System.err.println("Run as: com.fraudpointer.ConsoleClientExample <url to fraud pointer api> <your api key>");
        System.err.println("For 'url to fraudpointer api', why don't you try this: https://production.fraudpointer.com/api");
        System.err.println("For 'your api key', in case you do not have one, you need to register a new Account in https://production.fraudpointer.com/a/new, and follow the directions from there.");
    } // wrongSyntax()
    //-----------------

} // class ConsoleClientExample ()
//---------------------------------

