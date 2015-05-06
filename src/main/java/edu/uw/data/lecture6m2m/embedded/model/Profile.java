package edu.uw.data.lecture6m2m.embedded.model;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by Conor on 4/18/2015.
 */
// TODO Embedded LAB : annotate this class so it can be embedded into the User entity
public class Profile {
    public Profile() {
    }

    @Column(name = "PROFILE_NAME")
    private String profileName;


    @Column(name = "PROFILE_NUMBER")
    private String profileNumber;


    @Temporal(value = TemporalType.DATE)
    @Column(name = "PROFILE_JOIN_DATE")
    private Date joinDate;

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileNumber() {
        return profileNumber;
    }

    public void setProfileNumber(String profileNumber) {
        this.profileNumber = profileNumber;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang.builder.ToStringBuilder(this)
                .append("profileName", profileName)
                .append("profileNumber", profileNumber)
                .append("joinDate", joinDate)
                .toString();
    }
}