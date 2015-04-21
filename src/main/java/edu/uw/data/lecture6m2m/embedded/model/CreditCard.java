package edu.uw.data.lecture6m2m.embedded.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by Conor on 4/18/2015.
 */
@Embeddable
public class CreditCard {
    public CreditCard() {
    }

    @Column(name = "CREDIT_CARD_NAME")
    private String cardName;


    @Column(name = "CREDIT_CARD_NUMBER")
    private String cardNumber;


    @Temporal(value = TemporalType.DATE)
    @Column(name = "CREDIT_CARD_EXPIRATION_DATE")
    private Date expirationDate;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang.builder.ToStringBuilder(this)
                .append("cardName", cardName)
                .append("cardNumber", cardNumber)
                .append("expirationDate", expirationDate)
                .toString();
    }
}