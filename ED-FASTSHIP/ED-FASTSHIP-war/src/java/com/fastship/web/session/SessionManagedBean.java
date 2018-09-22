/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastship.web.session;

import com.fastship.entity.*;
import com.fastship.session.ManagementFacadeRemote;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Deniz
 */
@Named(value = "sessionManagedBean")
@SessionScoped
public class SessionManagedBean implements Serializable
{

    @EJB
    private ManagementFacadeRemote facade;

    private ParcelDTO parcel;
    private EventDTO event;
    private PaymentDTO payment;
    private ClientDTO myClient;
    private ClientDTO toClient;
    private ClientDTO fromClient;

    private ArrayList<ParcelDTO> parcelList;
    private ArrayList<EventDTO> eventList;
    private ArrayList<PaymentDTO> paymentList;

    public SessionManagedBean()
    {
        flushVariables();
        System.out.println("Session Bean Initialized");
    }

    private void flushVariables()
    {
        parcel = null;
        event = null;
        payment = null;
        myClient = null;
        toClient = null;
        fromClient = null;
        parcelList = null;
        eventList = null;
        paymentList = null;
    }

    public ParcelDTO getParcel()
    {
        return parcel;
    }

    public EventDTO getEvent()
    {
        return event;
    }

    public PaymentDTO getPayment()
    {
        return payment;
    }

    public ClientDTO getMyClient()
    {
        return myClient;
    }

    public ClientDTO getToClient()
    {
        return toClient;
    }

    public ClientDTO getFromClient()
    {
        return fromClient;
    }

    public ArrayList<ParcelDTO> getParcelList()
    {
        return parcelList;
    }

    public ArrayList<EventDTO> getEventList()
    {
        return eventList;
    }

    public ArrayList<PaymentDTO> getPaymentList()
    {
        return paymentList;
    }

    public String getParcelForClient()
    {
        flushVariables();
        parcelList = facade.displayParcelForClient(getUserId());
        if (parcelList == null)
        {
            //no parcel found
            return "displayParcelNone.xhtml";
        }
        else
        {
            return "displayParcel.xhtml";
        }
    }

    public String getPaymentForClient()
    {
        flushVariables();
        paymentList = facade.displayPaymentForClient(getUserId());
        if (paymentList == null)
        {
            //no payments found
            return "displayPaymentNone.xhtml";
        }
        else
        {
            return "displayPayment.xhtml";
        }
    }

    public String getDetailForClient()
    {
        flushVariables();
        myClient = facade.displayClient(getUserId());
        return "displayDetail.xhtml";
    }

    public String pickUpParcel()
    {
        getDetailForClient();
        return "pickupParcel.xhtml";
    }

    public String pickUpParcelConfirm()
    {
        ParcelDTO newParcel = new ParcelDTO("TBAXXXXX", getUserId(), "TBAXXXXX", 0, 0, "TBAXXXXX", "TBAXXXXX");
        String trackingNumber = facade.createParcel(newParcel);
        EventDTO newEvent = new EventDTO("TBAXXXXX", Calendar.getInstance(), trackingNumber, "Online", "Awaiting Pickup", "Pickup Ordered", "Pickup ordered online by Client");
        String eventId = facade.createEvent(newEvent);
        parcel = facade.displayParcel(trackingNumber);
        return "pickupParcelConfirm.xhtml";
    }

    public String getUserId()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        String username = request.getUserPrincipal().getName();
        String clientId = facade.displayClientForUsername(username).getClientId();
        return clientId;
    }

    public String performLogout()
    {
        // invalidate the session
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        try
        {
            request.logout();
        }
        catch (ServletException ex)
        {
            System.out.println("Servlet EX in SessionManagedBean.logoutResult()");
        }
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        return "logout";
    }
}
