/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastship.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Deniz
 */
@Entity
@Table(name = "FS_PAYMENT")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p")
    , @NamedQuery(name = "Payment.findByPaymentId", query = "SELECT p FROM Payment p WHERE p.paymentId = :paymentid")
    , @NamedQuery(name = "Payment.findByDebtor", query = "SELECT p FROM Payment p WHERE p.debtor = :debtor")
    , @NamedQuery(name = "Payment.findByAmount", query = "SELECT p FROM Payment p WHERE p.amount = :amount")
    , @NamedQuery(name = "Payment.findByIsPaid", query = "SELECT p FROM Payment p WHERE p.isPaid = :ispaid")
    , @NamedQuery(name = "Payment.findByReference", query = "SELECT p FROM Payment p WHERE p.reference = :reference")
})
public class Payment implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "PAYMENTID")
    private String paymentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "DEBTOR")
    private String debtor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISPAID")
    private Boolean isPaid;
    @Size(max = 128)
    @Column(name = "REFERENCE")
    private String reference;

    public Payment()
    {
    }

    public Payment(String paymentId)
    {
        this.paymentId = paymentId;
    }

    public Payment(String paymentId, String debtor, BigDecimal amount, Boolean isPaid)
    {
        this.paymentId = paymentId;
        this.debtor = debtor;
        this.amount = amount;
        this.isPaid = isPaid;
    }

    public String getPaymentId()
    {
        return paymentId;
    }

    public void setPaymentId(String paymentId)
    {
        this.paymentId = paymentId;
    }

    public String getDebtor()
    {
        return debtor;
    }

    public void setDebtor(String debtor)
    {
        this.debtor = debtor;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public Boolean getIsPaid()
    {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid)
    {
        this.isPaid = isPaid;
    }

    public String getReference()
    {
        return reference;
    }

    public void setReference(String reference)
    {
        this.reference = reference;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (paymentId != null ? paymentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payment))
        {
            return false;
        }
        Payment other = (Payment) object;
        if ((this.paymentId == null && other.paymentId != null) || (this.paymentId != null && !this.paymentId.equals(other.paymentId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.fastship.entity.Payment[ paymentId=" + paymentId + " ]";
    }
    
}
