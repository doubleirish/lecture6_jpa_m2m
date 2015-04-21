package edu.uw.data.lecture6m2m.embedded.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * User can have one Address but many Phone Numbers
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;


    @Embedded
    private CreditCard creditCard;


    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public String toString() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
        return new ToStringBuilder(this)
                .append("username", username)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("creditCard", creditCard)
                .append("billingAddr", billingAddr)
                .append("shippingAddr", shippingAddr)
                .toString();
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="street", column=@Column(name="BILLING_ADDR_STREET")),
            @AttributeOverride(name="city",   column=@Column(name="BILLING_ADDR_CITY")),
            @AttributeOverride(name="state",  column=@Column(name="BILLING_ADDR_STATE")),
            @AttributeOverride(name="zip",    column=@Column(name="BILLING_ADDR_ZIP")),
    })
    private    Address  billingAddr ;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="street", column=@Column(name="SHIPPING_ADDR_STREET")),
            @AttributeOverride(name="city",   column=@Column(name="SHIPPING_ADDR_CITY")),
            @AttributeOverride(name="state",  column=@Column(name="SHIPPING_ADDR_STATE")),
            @AttributeOverride(name="zip",    column=@Column(name="SHIPPING_ADDR_ZIP")),
    })
    private    Address  shippingAddr ;

    public Address getBillingAddr() {
        return billingAddr;
    }

    public void setBillingAddr(Address billingAddr) {
        this.billingAddr = billingAddr;
    }

    public Address getShippingAddr() {
        return shippingAddr;
    }

    public void setShippingAddr(Address shippingAddr) {
        this.shippingAddr = shippingAddr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }
}
