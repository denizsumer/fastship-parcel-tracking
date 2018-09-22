/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastship.entity;

import java.io.Serializable;
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
@Table(name = "FS_PARCEL")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Parcel.findAll", query = "SELECT p FROM Parcel p")
    , @NamedQuery(name = "Parcel.findByTrackingNo", query = "SELECT p FROM Parcel p WHERE p.trackingNo = :trackingno")
    , @NamedQuery(name = "Parcel.findByFromClientId", query = "SELECT p FROM Parcel p WHERE p.fromClientId = :fromclientid")
    , @NamedQuery(name = "Parcel.findByToClientId", query = "SELECT p FROM Parcel p WHERE p.toClientId = :toclientid")
    , @NamedQuery(name = "Parcel.findByVolume", query = "SELECT p FROM Parcel p WHERE p.volume = :volume")
    , @NamedQuery(name = "Parcel.findByWeight", query = "SELECT p FROM Parcel p WHERE p.weight = :weight")
    , @NamedQuery(name = "Parcel.findByPaymentId", query = "SELECT p FROM Parcel p WHERE p.paymentId = :paymentid")
    , @NamedQuery(name = "Parcel.findByService", query = "SELECT p FROM Parcel p WHERE p.service = :service")
})
public class Parcel implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "TRACKINGNO")
    private String trackingNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "FROMCLIENTID")
    private String fromClientId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "TOCLIENTID")
    private String toClientId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VOLUME")
    private int volume;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WEIGHT")
    private int weight;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "PAYMENTID")
    private String paymentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "SERVICE")
    private String service;

    public Parcel()
    {
    }

    public Parcel(String trackingNo)
    {
        this.trackingNo = trackingNo;
    }

    public Parcel(String trackingNo, String fromClientId, String toClientId, int volume, int weight, String paymentId, String service)
    {
        this.trackingNo = trackingNo;
        this.fromClientId = fromClientId;
        this.toClientId = toClientId;
        this.volume = volume;
        this.weight = weight;
        this.paymentId = paymentId;
        this.service = service;
    }

    public String getTrackingNo()
    {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo)
    {
        this.trackingNo = trackingNo;
    }

    public String getFromClientId()
    {
        return fromClientId;
    }

    public void setFromClientId(String fromClientId)
    {
        this.fromClientId = fromClientId;
    }

    public String getToClientId()
    {
        return toClientId;
    }

    public void setToClientId(String toClientId)
    {
        this.toClientId = toClientId;
    }

    public int getVolume()
    {
        return volume;
    }

    public void setVolume(int volume)
    {
        this.volume = volume;
    }

    public int getWeight()
    {
        return weight;
    }

    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    public String getPaymentId()
    {
        return paymentId;
    }

    public void setPaymentId(String paymentId)
    {
        this.paymentId = paymentId;
    }

    public String getService()
    {
        return service;
    }

    public void setService(String service)
    {
        this.service = service;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (trackingNo != null ? trackingNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parcel))
        {
            return false;
        }
        Parcel other = (Parcel) object;
        if ((this.trackingNo == null && other.trackingNo != null) || (this.trackingNo != null && !this.trackingNo.equals(other.trackingNo)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.fastship.entity.Parcel[ trackingNo=" + trackingNo + " ]";
    }
    
}
