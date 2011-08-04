package com.fraudpointer.api.clients;

import com.fraudpointer.api.ClientException;
import com.fraudpointer.api.models.Event;
import com.fraudpointer.api.models.FraudAssessment;
import com.fraudpointer.api.requestwrappers.RequestKeyAndEvent;
import com.fraudpointer.api.requestwrappers.RequestKeyAndFraudAssessment;
import com.fraudpointer.api.responsewrappers.ResponseAssessmentSession;
import com.fraudpointer.api.responsewrappers.ResponseEvent;
import com.fraudpointer.api.responsewrappers.ResponseFraudAssessment;
import com.google.gson.*;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.ClientResponse;

import com.fraudpointer.api.IClient;
import com.fraudpointer.api.models.AssessmentSession;
import com.fraudpointer.api.requestwrappers.RequestKey;

import java.io.IOException;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.codec.binary.Base64;

/**
 * This is a class that implements {@link com.fraudpointer.api.IClient} interface. <br/>
 * <br/>
 * You can safely use this implementation and avoid writing everything from scratch. <br/>
 * <br/>
 * <h3>Serializing objects using the json format.</h3>
 * <p>
 * Since Fraudpointer Server offers a RESTful JSON API, we had to find a way to serialize and deserialize our java class instances
 * to this particular format. <a href="http://code.google.com/p/google-gson/">Google GSON</a> library does the magic pretty well and
 * this is what we have used.
 * </p>
 * <h3>Consuming RESTful web services</h3>
 * <p>
 * Consuming the RESTful web services of Fraudpointer, is a piece of case when you use a library like <a href="http://jersey.java.net/">jersey</a>.
 * </p>
 */
public class Client implements IClient {

    private String m_baseUrl;
    private String m_apiKey;

    public Client (String baseUrl, String apiKey)
    {
        m_baseUrl = baseUrl;
        m_apiKey = apiKey;
    }

    public AssessmentSession createAssessmentSession () throws ClientException
    {
        try {
            com.sun.jersey.api.client.Client fraudpointerJerseyClient = com.sun.jersey.api.client.Client.create();
            WebResource webResource = fraudpointerJerseyClient.resource(m_baseUrl + "/assessment_sessions");
            RequestKey requestKey = new RequestKey();
            requestKey.key = m_apiKey;

            Gson gson = getFraudPointerGson();

            ClientResponse response = webResource.type("application/json").accept("application/json").post(ClientResponse.class, gson.toJson(requestKey));

            if ( response.getClientResponseStatus().equals(ClientResponse.Status.CREATED) ) {
                String entity = response.getEntity(String.class);

                ResponseAssessmentSession responseAssessmentSession = gson.fromJson(entity, ResponseAssessmentSession.class);

                return responseAssessmentSession.assessment_session;
            }

            // problem.
            throwProblemException(response);
            return null;
        }
        catch (ClientException ex) { throw ex; }
        catch (Exception ex)
        {
            throw new ClientException("Error while trying to create an assessment session!", ex);
        }

    } // createAssessmentSession ()
    //-----------------------------

    public Event appendEventToAssessmentSession (AssessmentSession assessmentSession, Event paramEvent) throws ClientException
    {
        try {
            com.sun.jersey.api.client.Client fraudpointerJerseyClient = com.sun.jersey.api.client.Client.create();
            WebResource webResource = fraudpointerJerseyClient.resource(m_baseUrl + "/assessment_sessions/" + assessmentSession.id + "/events");
            RequestKeyAndEvent requestKeyAndEvent = new RequestKeyAndEvent();
            requestKeyAndEvent.event = paramEvent;
            requestKeyAndEvent.key = m_apiKey;

            Gson gson = getFraudPointerGson();

            ClientResponse response = webResource.type("application/json").accept("application/json").post(ClientResponse.class, gson.toJson(requestKeyAndEvent));

            if ( response.getClientResponseStatus().equals(ClientResponse.Status.CREATED) ) {
                String entity = response.getEntity(String.class);

                ResponseEvent responseEvent = gson.fromJson(entity, ResponseEvent.class);

                return responseEvent.event;
            }

            // problem.
            throwProblemException(response);
            return null;

        }
        catch (ClientException ex) { throw ex; }
        catch (Exception ex) {
            throw new ClientException("Error while trying to append event to assessment session!", ex);
        }

    } // appendEventToAssessmentSession ()
    //------------------------------------

    public FraudAssessment createFraudAssessment (AssessmentSession assessmentSession, boolean interim) throws ClientException
    {
        try {
            com.sun.jersey.api.client.Client fraudpointerJerseyClient = com.sun.jersey.api.client.Client.create();
            WebResource webResource = fraudpointerJerseyClient.resource(m_baseUrl + "/assessment_sessions/" + assessmentSession.id + "/fraud_assessments");
            RequestKeyAndFraudAssessment requestKeyAndFraudAssessment = new RequestKeyAndFraudAssessment();
            requestKeyAndFraudAssessment.fraudAssessment = new FraudAssessment();
            requestKeyAndFraudAssessment.fraudAssessment.interim = interim;
            requestKeyAndFraudAssessment.key = m_apiKey;

            Gson gson = getFraudPointerGson();

            ClientResponse response = webResource.type("application/json").accept("application/json").post(ClientResponse.class, gson.toJson(requestKeyAndFraudAssessment));

            if ( response.getClientResponseStatus().equals(ClientResponse.Status.CREATED) ) {
                String entity = response.getEntity(String.class);

                ResponseFraudAssessment responseFraudAssessment = gson.fromJson(entity, ResponseFraudAssessment.class);

                return responseFraudAssessment.fraudAssessment;
            }

            // problem.
            throwProblemException(response);
            return null;

        }
        catch (ClientException ex) { throw ex; }
        catch (Exception ex) {
            throw new ClientException("Error while trying to create a fraud assessment!", ex);
        }

    } // createFraudAssessment ()
    //---------------------------

    public FraudAssessment getFraudAssessment (AssessmentSession assessmentSession, String assessmentId) throws ClientException
    {

        try {
            com.sun.jersey.api.client.Client fraudpointerJerseyClient = com.sun.jersey.api.client.Client.create();
            WebResource webResource = fraudpointerJerseyClient.resource(m_baseUrl + "/assessment_sessions/" + assessmentSession.id + "/fraud_assessments/" + assessmentId + "?key=" + m_apiKey);

            Gson gson = Client.getFraudPointerGson();

            ClientResponse response = webResource.type("application/json").accept("application/json").get(ClientResponse.class);

            if ( response.getClientResponseStatus().equals(ClientResponse.Status.OK) ) {
                String entity = response.getEntity(String.class);

                ResponseFraudAssessment responseFraudAssessment = gson.fromJson(entity, ResponseFraudAssessment.class);

                return responseFraudAssessment.fraudAssessment;
            }

            // problem
            throwProblemException(response);
            return null;
        }
        catch (ClientException ex) { throw ex; }
        catch (Exception ex) {
            throw new ClientException("Error while trying to get fraud assessment!", ex);
        }

    } // getFraudAssessment()
    //-----------------------

    public String creditCardHash (String creditCardNumber) throws ClientException
    {
        final String salt = "HVK+gw==";

        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-256");
            md.reset();

            byte [] convertedSalt = base64ToByte(salt);
            byte [] btsCreditCardNumber = creditCardNumber.getBytes();

            byte [] dataAndSalt = new byte[ btsCreditCardNumber.length + convertedSalt.length ];
            System.arraycopy(btsCreditCardNumber, 0, dataAndSalt, 0, btsCreditCardNumber.length);
            System.arraycopy(convertedSalt, 0, dataAndSalt, btsCreditCardNumber.length, convertedSalt.length);

            byte [] creditCardNumberDigest = md.digest(dataAndSalt);

            return byteToBase64(creditCardNumberDigest);
        }
        catch (Exception ex)
        {
            throw new ClientException("ERROR while trying to encrypt Credit Card!", ex);
        }

    } // creditCardHash ()
    //--------------------

    protected static Gson getFraudPointerGson ()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        Gson gson = gsonBuilder.create();
        return gson;
    } // getFraudPointerGson()
    //------------------------

    protected void throwProblemException(ClientResponse response) throws ClientException
    {
        throw new ClientException("Status Code: " + response.getClientResponseStatus().getStatusCode() + " : " + response.getClientResponseStatus().getReasonPhrase(), null);
    } // throwProblemException ()
    //----------------------------

    /**
     * From a base 64 representation, returns the corresponding byte[]
     * @param data String The base64 representation
     * @return byte[]
     * @throws IOException
     */
    public static byte[] base64ToByte(String data) throws IOException {

       return Base64.decodeBase64(data);
    } // base64ToByte ()
    //-------------------

    /**
     * From a byte[] returns a base 64 representation
     * @param data byte[]
     * @return String
     * @throws IOException
     */
    public static String byteToBase64(byte[] data){
       return Base64.encodeBase64String(data);
    } // byteToBase64()
    //------------------

} // class Client
//----------------

/**
 * Standard date deserializer of Google Gson, together with the fact that SimpleDateFormat does not correctly support
 * the ISO 8601 format, is not adequate for the conversion of dates that Fraudpointer sends back to clients.&nbsp;Hence
 * we had to implement a custom deserializer for that.
 */
class DateDeserializer implements JsonDeserializer<Date> {

    public Date deserialize (JsonElement json, Type typeOfT, JsonDeserializationContext context)   throws JsonParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date dtResult = null;
        try {
            dtResult = sdf.parse(CorrectISO8601SimpleDateFormat(json.getAsJsonPrimitive().getAsString()));
        }
        catch (Exception ex) {
            return null;
        }

        return dtResult;

    } // deserialize()
    //------------------

    protected static String CorrectISO8601SimpleDateFormat (String dateString)
    {   // find last ':' and remove it
        int posOfColon = dateString.lastIndexOf(':');
        if ( posOfColon >= 0 ) {
            dateString = dateString.substring(0, posOfColon) + dateString.substring(posOfColon + 1);
        }
        return dateString;
    } // CorrectISO8601SimpleDateFormat ()
    //-----------------------------------

} // class DateTimeDeserializer
//------------------------------
