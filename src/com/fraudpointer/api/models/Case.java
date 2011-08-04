package com.fraudpointer.api.models;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

/**
 * A Case is created for every <b>final</b> {@link com.fraudpointer.api.models.FraudAssessment FraudAssessment}. <br/>
 * <br/>
 * The Case is meant to be handled by a human interacting with the Fraudpointe Application. When a Case is created, it has
 * an "Open" status (unless you have rules in your Fraudpointer application configuration that automatically put them in other statuses).
 * When a Case is inspected by a human, human might decide to set a {@link com.fraudpointer.api.models.Case#resolution resolution} to it.
 */
public class Case {

    /**
     * The Resolution of a Case as set by human (or automatically according to Fraudpointer application configuration). <br/>
     * <br/>
     * Can take the following values:<br/>
     * <br/>
     * <ul>
     *     <li>"Accept"</li>
     *     <li>"Review"</li>
     *     <li>"Reject"</li>
     * </ul>
     */
    public String resolution;

    /**
     * Case Id for further reference
     */
    public String id;

    /**
     * Case status. <br/>
     * <br/>
     * Can take the following values:<br/>
     * <br/>
     * <ul>
     *     <li>"Open"</li>
     *     <li>"Closed"</li>
     * </ul>
     *
     */
    public String status;

    /**
     * Last time the Case was updated
     */
    @SerializedName("updated_at") public Date updatedAt;

} // class Case
//-------------


