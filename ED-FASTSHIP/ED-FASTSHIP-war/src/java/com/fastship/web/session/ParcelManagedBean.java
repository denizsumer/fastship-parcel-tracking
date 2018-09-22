/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastship.web.session;

import com.fastship.entity.*;
import com.fastship.session.ManagementFacadeRemote;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

/**
 *
 * @author Deniz
 */
@Named(value = "parcelManagedBean")
@ConversationScoped
public class ParcelManagedBean implements Serializable
{

    @Inject
    private Conversation conversation;

    @EJB
    private ManagementFacadeRemote facade;

    private String trackingNo;
    private String fromClientId;
    private String toClientId;
    private int volume;
    private int weight;
    private String paymentId;
    private String service;
    private String status;
    private ArrayList<EventDTO> eventList;
    private ClientDTO fromClient;
    private ClientDTO toClient;
    private PaymentDTO payment;

    private ParcelDTO parcel;

    private String clName;
    private String clPic;
    private String clPhone;
    private String clAddress;
    private String clEmail;
    private String clUsername;
    private String clPassword;
    private String clCPassword;

    /**
     * Creates a new instance of ParcelManagedBean
     */
    public ParcelManagedBean()
    {
        trackingNo = null;
        fromClientId = null;
        toClientId = null;
        volume = 0;
        weight = 0;
        paymentId = null;
        service = null;

        status = null;
        eventList = null;
        fromClient = null;
        toClient = null;
        payment = null;

        clName = null;
        clPic = null;
        clPhone = null;
        clAddress = null;
        clEmail = null;
        clUsername = null;
        clPassword = null;
        clCPassword = null;

        System.out.println("PARCEL BEAN CREATED");

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

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public ArrayList<EventDTO> getEventList()
    {
        return eventList;
    }

    public void setEventList(ArrayList<EventDTO> eventList)
    {
        this.eventList = eventList;
    }

    public ClientDTO getFromClient()
    {
        return fromClient;
    }

    public void setFromClient(ClientDTO fromClient)
    {
        this.fromClient = fromClient;
    }

    public ClientDTO getToClient()
    {
        return toClient;
    }

    public void setToClient(ClientDTO toClient)
    {
        this.toClient = toClient;
    }

    public PaymentDTO getPayment()
    {
        return payment;
    }

    public void setPayment(PaymentDTO payment)
    {
        this.payment = payment;
    }

    public String getClName()
    {
        return clName;
    }

    public void setClName(String clName)
    {
        this.clName = clName;
    }

    public String getClPic()
    {
        return clPic;
    }

    public void setClPic(String clPic)
    {
        this.clPic = clPic;
    }

    public String getClPhone()
    {
        return clPhone;
    }

    public void setClPhone(String clPhone)
    {
        this.clPhone = clPhone;
    }

    public String getClAddress()
    {
        return clAddress;
    }

    public void setClAddress(String clAddress)
    {
        this.clAddress = clAddress;
    }

    public String getClEmail()
    {
        return clEmail;
    }

    public void setClEmail(String clEmail)
    {
        this.clEmail = clEmail;
    }

    public String getClUsername()
    {
        return clUsername;
    }

    public void setClUsername(String clUsername)
    {
        this.clUsername = clUsername;
    }

    public String getClPassword()
    {
        return clPassword;
    }

    public void setClPassword(String clPassword)
    {
        this.clPassword = clPassword;
    }

    public String getClCPassword()
    {
        return clCPassword;
    }

    public void setClCPassword(String clCPassword)
    {
        this.clCPassword = clCPassword;
    }

    public void startConversation()
    {
        conversation.begin();
    }

    public void endConversation()
    {
        conversation.end();
    }

    public String getParcel()
    {
        if (!validateTrackingNo())
        {
            System.out.println("tracking no : " + trackingNo);
            return "validationFail";
        }

        parcel = facade.displayParcel(trackingNo);
        if (parcel == null)
        {
            //no parcel found
            return "failure";
        }
        else
        {
            this.trackingNo = parcel.getTrackingNo();
            this.fromClientId = parcel.getFromClientId();
            this.toClientId = parcel.getToClientId();
            this.volume = parcel.getVolume();
            this.weight = parcel.getWeight();
            this.paymentId = parcel.getPaymentId();
            this.service = parcel.getService();

            this.status = facade.displayLastEventForParcel(trackingNo).getStatus();
            this.eventList = facade.displayEventsForParcel(trackingNo);
            this.fromClient = facade.displayClient(toClientId);
            this.toClient = facade.displayClient(fromClientId);
            this.payment = facade.displayPayment(paymentId);

            return "success";
        }
    }

    public String addClient()
    {
        if (clPassword.equals(clCPassword) && (facade.displayClientForUsername(clUsername) == null))
        {
            if(clPic == null || clPic.equals(""))
            {
                clPic = clName;
            }
            ClientDTO newClient = new ClientDTO("TBAXXXXX", clName, clPic, clPhone, clAddress, clEmail, clUsername, clPassword, "CLIENT");
            facade.createClient(newClient);
            return "createAccountSuccess.xhtml";
        }
        else
        {
            //password not matched or duplciate username
            return "createAccountFailure.xhtml";
        }

    }

    private boolean validateTrackingNo()
    {
        return !(trackingNo.equals(""));
    }

    private boolean validateFields()
    {
        return !(trackingNo.equals("") || fromClientId.equals("") || toClientId.equals("") || volume > 0.0 || weight > 0.0 || paymentId.equals("") || service.equals(""));
    }
}
