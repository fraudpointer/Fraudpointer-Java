package com.fraudpointer.api.models;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

/**
 * The Profile used to carry out the Fraud Assessment.<br/>
 * <br/>
 * A {@link com.fraudpointer.api.models.FraudAssessment FraudAssessment} carries out Assessment using the rules that belong to a specific Profile.
 * There are might be many Profile configured in your Account, but only one is selected for the {@link com.fraudpointer.api.models.FraudAssessment FraudAssessment}
 * at hand. This is done at run-time using the Profile Selection Rules.
 */
public class Profile {

    /**
     * The unique identifier of the Profile used for {@link com.fraudpointer.api.models.FraudAssessment FraudAssessment}.
     */
    public String id;

    /**
     * The name of the Profile used for {@link com.fraudpointer.api.models.FraudAssessment FraudAssessment}.
     */
    public String name;

    /**
     * Last time this Profile was updated.
     */
    @SerializedName("updated_at") public Date updatedAt;

} // class Profile
//----------------

