/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastship.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Deniz
 */
@Entity
@Table(name = "FS_EVENT")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e")
    , @NamedQuery(name = "Event.findByEventId", query = "SELECT e FROM Event e WHERE e.eventId = :eventid")
    , @NamedQuery(name = "Event.findByEventTime", query = "SELECT e FROM Event e WHERE e.eventTime = :eventtime")
    , @NamedQuery(name = "Event.findByTrackingNo", query = "SELECT e FROM Event e WHERE e.trackingNo = :trackingno")
    , @NamedQuery(name = "Event.findByLocation", query = "SELECT e FROM Event e WHERE e.location = :location")
    , @NamedQuery(name = "Event.findByStatus", query = "SELECT e FROM Event e WHERE e.status = :status")
    , @NamedQuery(name = "Event.findByActivity", query = "SELECT e FROM Event e WHERE e.activity = :activity")
    , @NamedQuery(name = "Event.findByAdditionalInfo", query = "SELECT e FROM Event e WHERE e.additionalInfo = :additionalinfo")
})
public class Event implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "EVENTID")
    private String eventId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EVENTTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar eventTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "TRACKINGNO")
    private String trackingNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "LOCATION")
    private String location;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "ACTIVITY")
    private String activity;
    @Size(max = 128)
    @Column(name = "ADDITIONALINFO")
    private String additionalInfo;

    public Event()
    {
    }

    public Event(String eventId)
    {
        this.eventId = eventId;
    }

    public Event(String eventId, Calendar eventTime, String trackingNo, String location, String status, String activity)
    {
        this.eventId = eventId;
        this.eventTime = eventTime;
        this.trackingNo = trackingNo;
        this.location = location;
        this.status = status;
        this.activity = activity;
    }

    public String getEventId()
    {
        return eventId;
    }

    public void setEventId(String eventId)
    {
        this.eventId = eventId;
    }

    public Calendar getEventTime()
    {
        return eventTime;
    }

    public void setEventTime(Calendar eventTime)
    {
        this.eventTime = eventTime;
    }

    public String getTrackingNo()
    {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo)
    {
        this.trackingNo = trackingNo;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getActivity()
    {
        return activity;
    }

    public void setActivity(String activity)
    {
        this.activity = activity;
    }

    public String getAdditionalInfo()
    {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo)
    {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (eventId != null ? eventId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event))
        {
            return false;
        }
        Event other = (Event) object;
        if ((this.eventId == null && other.eventId != null) || (this.eventId != null && !this.eventId.equals(other.eventId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.fastship.entity.Event[ eventId=" + eventId + " ]";
    }
    
}
