package com.fraudpointer.api;

import com.fraudpointer.api.clients.Client;

/**
 * This is a helping class that instantiates an object that implements IClient interface. <br/>
 * <br/>
 * Use this method to start with FraudPointer API. It will give you an object that properly implements IClient interface.
 */
public class ClientFactory {

    /**
     * Instantiates an object that properly implements IClient interface. <br/>
     * <br/>
     * Start your FraudPointer API usage from here. It will give you an instance of an object that properly implements the IClient interface.<br/>
     * <br/>
     * Here is an example of usage:<br/>
     * <br/>
     * <pre>
     * IClient client = ClientFactory.construct("https://production.fraudpointer.com/api", "111118312453678901abcdef123456789328");
     * </pre>
     * <br/>
     * @param baseUrl This is the URL of the FraudPointer API Service. It has to have the value: <code>https://production.fraudpointer.com/api</code>
     * @param apiKey This should be the API KEY that corresponds to the domain that you integrate FraudPointer API for. The API KEY is automatically generated when
     * you register an Account in <a href="https://production.fraudpointer.com/a/new" target="_blank">FraudPointer Registration Web Form</a>.
     * Note that your Account in FraudPointer service might have more than one domains registered. You need to provide here the API KEY that
     * corresponds to the domain that you are going to integrate this client with.
     * @return A valid IClient instantiated object. This is a newly created object to work with the FraudPointer Service.
     */
    public static IClient construct (String baseUrl, String apiKey)
    {
        return new Client(baseUrl, apiKey);

    } // construct()
    // -------------

    private ClientFactory(){}

} // class ClientFactory
//-----------------------

