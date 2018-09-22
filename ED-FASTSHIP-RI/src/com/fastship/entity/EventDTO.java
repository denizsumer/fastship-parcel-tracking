/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastship.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Deniz
 */
@XmlRootElement(name = "EventDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventDTO  implements Serializable
{
    @XmlElement(required = true)
    private String eventId;
    @XmlElement(required = true)
    private Calendar eventTime;
    @XmlElement(required = true)
    private String trackingNo;
    @XmlElement(required = true)
    private String location;
    @XmlElement(required = true)
    private String status;
    @XmlElement(required = true)
    private String activity;
    @XmlElement(required = true)
    private String additionalInfo;

    public EventDTO(String eventId, Calendar eventTime, String trackingNo, String location, String status, String activity, String additionalInfo)
    {
        this.eventId = eventId;
        this.eventTime = eventTime;
        this.trackingNo = trackingNo;
        this.location = location;
        this.status = status;
        this.activity = activity;
        this.additionalInfo = additionalInfo;
    }
    
    public EventDTO()
    {
    }

    public String getEventId()
    {
        return eventId;
    }

    public Calendar getEventTime()
    {
        return eventTime;
    }

    public String getTrackingNo()
    {
        return trackingNo;
    }

    public String getLocation()
    {
        return location;
    }

    public String getStatus()
    {
        return status;
    }

    public String getActivity()
    {
        return activity;
    }

    public String getAdditionalInfo()
    {
        return additionalInfo;
    }

    public void setEventId(String eventId)
    {
        this.eventId = eventId;
    }

    public void setEventTime(Calendar eventTime)
    {
        this.eventTime = eventTime;
    }

    public void setTrackingNo(String trackingNo)
    {
        this.trackingNo = trackingNo;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setActivity(String activity)
    {
        this.activity = activity;
    }

    public void setAdditionalInfo(String additionalInfo)
    {
        this.additionalInfo = additionalInfo;
    }

}
