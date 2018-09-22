/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastship.entity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Deniz
 */
@XmlRootElement(name = "ParcelDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParcelDTO implements Serializable
{

    @XmlElement(required = true)
    private String trackingNo;
    @XmlElement(required = true)
    private String fromClientId;
    @XmlElement(required = true)
    private String toClientId;
    @XmlElement(required = true)
    private int volume;
    @XmlElement(required = true)
    private int weight;
    @XmlElement(required = true)
    private String paymentId;
    @XmlElement(required = true)
    private String service;

    public ParcelDTO(String trackingNo, String fromClientId, String toClientId, int volume, int weight, String paymentId, String service)
    {
        this.trackingNo = trackingNo;
        this.fromClientId = fromClientId;
        this.toClientId = toClientId;
        this.volume = volume;
        this.weight = weight;
        this.paymentId = paymentId;
        this.service = service;
    }
    
    public ParcelDTO()
    {
    }

    public String getTrackingNo()
    {
        return trackingNo;
    }

    public String getFromClientId()
    {
        return fromClientId;
    }

    public String getToClientId()
    {
        return toClientId;
    }

    public int getVolume()
    {
        return volume;
    }

    public int getWeight()
    {
        return weight;
    }

    public String getPaymentId()
    {
        return paymentId;
    }

    public String getService()
    {
        return service;
    }

    public void setTrackingNo(String trackingNo)
    {
        this.trackingNo = trackingNo;
    }

    public void setFromClientId(String fromClientId)
    {
        this.fromClientId = fromClientId;
    }

    public void setToClientId(String toClientId)
    {
        this.toClientId = toClientId;
    }

    public void setVolume(int volume)
    {
        this.volume = volume;
    }

    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    public void setPaymentId(String paymentId)
    {
        this.paymentId = paymentId;
    }

    public void setService(String service)
    {
        this.service = service;
    }

}
