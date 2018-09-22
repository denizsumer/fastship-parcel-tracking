/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastship.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Deniz
 */
@XmlRootElement(name = "PaymentDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentDTO implements Serializable
{
    @XmlElement(required = true)
    private String paymentId;
    @XmlElement(required = true)
    private String debtor;
    @XmlElement(required = true)
    private BigDecimal amount;
    @XmlElement(required = true)
    private boolean isPaid;
    @XmlElement(required = true)
    private String reference;

    public PaymentDTO(String paymentId, String debtor, BigDecimal amount, boolean isPaid, String reference)
    {
        this.paymentId = paymentId;
        this.debtor = debtor;
        this.amount = amount;
        this.isPaid = isPaid;
        this.reference = reference;
    }

    public PaymentDTO()
    {
    }

    public String getPaymentId()
    {
        return paymentId;
    }

    public String getDebtor()
    {
        return debtor;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public boolean getIsPaid()
    {
        return isPaid;
    }

    public String getReference()
    {
        return reference;
    }

    public void setPaymentId(String paymentId)
    {
        this.paymentId = paymentId;
    }

    public void setDebtor(String debtor)
    {
        this.debtor = debtor;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public void setIsPaid(boolean isPaid)
    {
        this.isPaid = isPaid;
    }

    public void setReference(String reference)
    {
        this.reference = reference;
    }

}
