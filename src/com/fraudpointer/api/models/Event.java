package com.fraudpointer.api.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

/**
 * Event object groups a set of Data under an Event Type.&nbsp;This whole set, is then associated to an
 * {@link com.fraudpointer.api.models.AssessmentSession AssessmentSession} by calling {@link com.fraudpointer.api.IClient#appendEventToAssessmentSession(AssessmentSession, Event)}<br/>
 * <br/>
 * When you are using the FraudPointer Service, you need to collect data and sent it over to FraudPointer Server. In order to do that you instantiate an Event object and then you
 * call {@link com.fraudpointer.api.models.Event addData() } method. Then you call {@link com.fraudpointer.api.IClient#appendEventToAssessmentSession(AssessmentSession, Event)}
 * and Event packaged data are sent over.<br/>
 * <br/>
 * It is important, though, to instantiate the appropriate Event Type. The following event types are supported:<br/>
 * <br/>
 * <ul>
 *     <li>{@link com.fraudpointer.api.models.Event#GenericEvent GenericEvent}</li>
 *     <li>{@link com.fraudpointer.api.models.Event#CheckoutEvent CheckoutEvent}</li>
 *     <li>{@link com.fraudpointer.api.models.Event#FailedPayment FailedPayment}</li>
 *     <li>{@link com.fraudpointer.api.models.Event#PurchaseEvent PurchaseEvent}</li>
 * </ul>
 */
public class Event {

    /**
     * Use this constant to instantiate an Event that is not one of the other types. <br/>
     * <br/>
     * Usually, you group your data into a more specific Type. However, if the data that you want
     * to group cannot be categorized below one of the specific Types, you can use the
     * GenericEvent Type.
     */
    public static final String GenericEvent = "GenericEvent";

    /**
     * Use this constant to instantiate an Event that is related to Checkout data. <br/>
     * <br/>
     * You can create a CheckoutEvent when you get data from a check out form. You essentially want
     * to store in FraudPointer server the fact that your customer is trying to check out together with
     * relevant check out data. Example of Data that you might want to store below the CheckoutEvent Type are:<br/>
     * <br/>
     * <ul>
     *     <li>Firstname</li>
     *     <li>Lastname</li>
     *     <li>Customer e-mail</li>
     *     <li>Customer address</li>
     *     <li>Product to Purchase</li>
     *     <li>Price</li>
     *- e.t.c.
     * </ul>
     */
    public static final String CheckoutEvent = "CheckoutEvent";

    /**
     * Use this constant to instantiate an Event to mark a Failed Payment. <br/>
     * <br/>
     * When you decide to proceed with charging your customer, with whatever data you have at your hand,
     * your attempt to charge its credit card might fail. In that case, record this failed payment by
     * instantiating a FailedPayment Event Type and sending it over to FraudPointer, without necessarily
     * attaching any data to it. Only the FailedPayment Event is enough to mark the fact that the payment
     * carried out during this session failed.<br/>
     * You can, and you probably should, instantiate this event many times. One for each failed payment attempt
     * during the same assessment session.
     */
    public static final String FailedPayment = "FailedPaymentEvent";

    /**
     * Use this constant to instantiate an Event to mark a Successful Payment. <br/>
     * <br/>
     * When you decide to proceed with charging your customer, with whatever data you have at your hand,
     * your attempt to charge its credit card might succeed. In that case, record this successful payment by
     * instantiating a Purchase Event Type and sending it over to FraudPointer, without necessarily
     * attaching any data to it. Only the PurchaseEvent Event is enough to mark the fact that the payment
     * carried out during this session has succeeded.<br/>
     * <br/>
     */
    public static final String PurchaseEvent = "PurchaseEvent";

    /**
     * Event Type to send to the server for event creation.
     */
    public String type;

    /**
     * Returns the Data appended to the particular Event. <br/>
     * <br/>
     * If you want to get read access to the Data that you have appended to an Event you can use this.
     * Note that if you want to append data to the Event use one of the methods AddData()
     */
    public Map<String, String> data;

    /**
     * Instantiate an Event by calling <code>new</code> on this Constructor. <br/>
     * <br/>
     * Use<br/>
     * <br/>
     * <code>
     *     Event anEvent = new Event(....)
     * </code>
     * to instantiate a new Event before start filling it with data. The argument should be one of the
     * valid event types.
     * <br/>
     * @param eventType One of: <br/>
     * <ul>
     *     <li>{@link com.fraudpointer.api.models.Event#CheckoutEvent CheckoutEvent}</li>
     *     <li>{@link com.fraudpointer.api.models.Event#FailedPayment FailedPayment}</li>
     *     <li>{@link com.fraudpointer.api.models.Event#GenericEvent GenericEvent}</li>
     *     <li>{@link com.fraudpointer.api.models.Event#PurchaseEvent PurchaseEvent}</li>
     * </ul>
     */
    public Event (String eventType)
    {
        type = eventType;
        data = new Hashtable<String, String>();
    } // constructor Event ()
    //-------------------------

    /**
     * Adds one datum to the particular Event. <br/>
     * <br/>
     * The datum is actually a value of an Attribute and value given is a <code>string</code> instance or literal.
     * Use this method if you want to add a datum of type <code>string</code> in your Event at hand.
     * <br/>
     * Here is an example:<br/>
     * <pre>
     * <code>
     * Event anEvent = new Event(Event.CheckoutEvent);
     * anEvent.addData("BILLING_COUNTRY", "FR");
     * anEvent.addData("BILLING_CITY", "Paris");
     * </code>
     * </pre>
     * @param key KEY value of the Attribute that you want to use. The Attributes are either System
     * or Custom Account Attributes. The System Attributes can be found <a href="http://documentation.fraudpointer.com/">in the official Fraudpointer documentation.</a>
     * The Custom Account Attributes should be given to you by the person who has created them in the FraudPointer Application.
     * @param value Value of the Attribute. A <code>string</code> instance or literal should be given.
     * @return The current Event instance, in order to continue adding data.
     */
    public Event addData (String key, String value)
    {
        data.put(key, value);
        return this;
    } // addData for string
    //----------------------

    /**
     * Adds one datum to the particular Event. <br/>
     * <br/>
     * The datum is actually a value of an Attribute and value given is an <code>int</code> instance or literal.<br/>
     * <br/>
     * Use this method if you want to add a datum of type <code>int</code> in your Event at hand.<br/>
     * <br/>
     * Here is an example:<br/>
     * <pre>
     * <code>
     * Event anEvent = new Event(Event.CheckoutEvent);
     * anEvent.addData("NUMBER_OF_PURCHASED_GOODS", 4);
     * </code>
     * </pre>
     *
     * @param key KEY value of the Attribute that you want to use. The Attributes are either System
     * or Custom Account Attributes. The System Attributes can be found <a href="http://documentation.fraudpointer.com/">in the official Fraudpointer documentation.</a>
     * The Custom Account Attributes should be given to you by the person who has created them in the FraudPointer Application.
     * @param value Value of the Attribute. An <code>int</code> instance or literal should be given.
     * @return The current Event instance, in order to continue adding data.
     */
    public Event addData (String key, int value)
    {
        data.put(key, Integer.toString(value));
        return this;
    } // addData for int
    //-------------------

    /**
     * Adds one datum to the particular Event. <br/>
     * <br/>
     * The datum is actually a value of an Attribute and value given is a <code>boolean</code> instance or literal.<br/>
     * <br/>
     * Use this method if you want to add a datum of type <code>boolean</code> in your Event at hand.<br/>
     * <br/>
     * Here is an example:<br/>
     * <pre>
     * <code>
     * Event anEvent = new Event(Event.CheckoutEvent);
     * anEvent.addData("TWO_WAY_TRIP", false);
     * </code>
     * </pre>
     *
     * @param key KEY value of the Attribute that you want to use. The Attributes are either System
     * or Custom Account Attributes. The System Attributes can be found <a href="http://documentation.fraudpointer.com/">in the official Fraudpointer documentation.</a>
     * The Custom Account Attributes should be given to you by the person who has created them in the FraudPointer Application.
     * @param value Value of the Attribute. A <code>boolean</code> instance or literal should be given.
     * @return The current Event instance, in order to continue adding data.
     */
    public Event addData (String key, boolean value)
    {
        data.put(key, value ? "true" : "false");
        return this;
    } // addData for boolean
    //----------------------

    /**
     * Adds one datum to the particular Event. <br/>
     * <br/>
     * The datum is actually a value of an Attribute and value given is a <code>double</code> instance or literal.<br/>
     * <br/>
     * Use this method if you want to add a datum of type <code>double</code> in your Event at hand.<br/>
     * <br/>
     * Here is an example:<br/>
     * <pre>
     * <code>
     * Event anEvent = new Event(Event.CheckoutEvent);
     * anEvent.AddData("PURCHASE_AMOUNT", 32.56);
     * </code>
     * </pre>
     *
     * @param key KEY value of the Attribute that you want to use. The Attributes are either System
     * or Custom Account Attributes. The System Attributes can be found <a href="http://documentation.fraudpointer.com/">in the official Fraudpointer documentation.</a>
     * The Custom Account Attributes should be given to you by the person who has created them in the FraudPointer Application.
     * @param value Value of the Attribute. A <code>double</code> instance or literal should be given.
     * @return The current Event instance, in order to continue adding data.
     */
    public Event addData (String key, double value)
    {
        data.put(key, Double.toString(value));
        return this;
    } // addData for double
    //----------------------

    /**
     * Adds one datum to the particular Event. <br/>
     * <br/>
     * The datum is actually a value of an Attribute and value given is a <code>Calendar</code> instance.<br/>
     * <br/>
     * Use this method if you want to add a datum of type <code>Calendar</code> in your Event at hand.<br/>
     * <br/>
     * Here is an example:<br/>
     * <pre>
     * <code>
     * // 30-June-2011 23:55:59 local time zone
     * Calendar startDate = Calendar.getInstance();
     * startDate.set(Calendar.YEAR, 2011);
     * startDate.set(Calendar.MONTH, 6-1); // June
     * startDate.set(Calendar.DAY_OF_MONTH, 30);
     * startDate.set(Calendar.HOUR_OF_DAY, 23);
     * startDate.set(Calendar.MINUTE, 55);
     * startDate.set(Calendar.SECOND, 59);
     * event.addData("StartDate", startDate);
     * </code>
     * </pre>
     * Note that internally, the given input is converted to a string that represents a date time point using the
     * ISO 8601 standard (extended format). Hence, the actual string sent over to Fraudpointer Server for the example given before
     * is "2011-06-30T23:55:59+02:00" assuming a time zone 2 hours ahead of UTC. <br/>
     * <br/>
     * @param key KEY value of the Attribute that you want to use. The Attributes are either System
     * or Custom Account Attributes. The System Attributes can be found <a href="http://documentation.fraudpointer.com/">in the official Fraudpointer documentation.</a>
     * The Custom Account Attributes should be given to you by the person who has created them in the FraudPointer Application.
     * @param value Value of the Attribute. A <code>Calendar</code> instance should be given.
     * @return The current Event instance, in order to continue adding data.
     */
    public Event addData (String key, Calendar value)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String dtValueFormatted = Event.correctISO8601SimpleDateFormat(sdf.format(value.getTime()));
        data.put(key, dtValueFormatted);
        return this;
    } // addData for Calendar
    //------------------------

    /**
     * Adds one datum to the particular Event. <br/>
     * <br/>
     * The datum is actually a value of an Attribute and value given is a <code>Date</code> instance. Everything that applies to {@link com.fraudpointer.api.models.Event#addData(String, java.util.Calendar)}
     * applies accordingly to this method too.<br/>
     * <br/>
     * @param key KEY value of the Attribute that you want to use. The Attributes are either System
     * or Custom Account Attributes. The System Attributes can be found <a href="http://documentation.fraudpointer.com/">in the official Fraudpointer documentation.</a>
     * The Custom Account Attributes should be given to you by the person who has created them in the FraudPointer Application.
     * @param value Value of the Attribute. A <code>Date</code> instance should be given.
     * @return The current Event instance, in order to continue adding data.
     */
    public Event addData (String key, Date value)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String dtValueFormatted = Event.correctISO8601SimpleDateFormat(sdf.format(value));
        data.put(key, dtValueFormatted);
        return this;
    } // addData for Date
    //------------------------

    protected static String correctISO8601SimpleDateFormat (String wrongFormat)
    {
        String result = wrongFormat;
        //convert YYYYMMDDTHH:mm:ss+HH00 into YYYYMMDDTHH:mm:ss+HH:00
        //- note the added colon for the Timezone
        result = result.substring(0, result.length()-2)
                    + ":" + result.substring(result.length()-2);
        return result;
    } // correctISO8601SimpleDateFormat ()
    //--------------------------------------

} // class Event
//--------------
