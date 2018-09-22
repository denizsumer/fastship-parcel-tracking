/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastship.session;

import javax.ejb.Stateless;
import com.fastship.entity.*;
import java.util.ArrayList;
import javax.ejb.EJB;

/**
 *
 * @author Deniz
 */
@Stateless
public class ManagementFacade implements ManagementFacadeRemote
{

    @EJB
    private ParcelFacade parcelFacade;

    @EJB
    private PaymentFacade paymentFacade;

    @EJB
    private EventFacade eventFacade;

    @EJB
    private ClientFacade clientFacade;

    @Override
    public String createParcel(ParcelDTO parcelDTO)
    {
        return parcelFacade.createParcel(parcelDTO);
    }

    @Override
    public boolean removeParcel(String trackingNo)
    {
        return parcelFacade.removeParcel(trackingNo);
    }

    @Override
    public boolean updateParcel(ParcelDTO parcelDTO)
    {
        return parcelFacade.updateParcel(parcelDTO);
    }

    @Override
    public ParcelDTO displayParcel(String trackingNo)
    {
        return parcelFacade.displayParcel(trackingNo);
    }

    @Override
    public ArrayList<ParcelDTO> displayAllParcel()
    {
        return parcelFacade.displayAllParcel();
    }

    @Override
    public ArrayList<ParcelDTO> displayParcelForClient(String clientId)
    {
        return parcelFacade.displayParcelForClient(clientId);
    }

    @Override
    public String createClient(ClientDTO clientDTO)
    {
        return clientFacade.createClient(clientDTO);
    }

    @Override
    public boolean removeClient(String clientId)
    {
        return clientFacade.removeClient(clientId);
    }

    @Override
    public boolean updateClient(ClientDTO clientDTO)
    {
        return clientFacade.updateClient(clientDTO);
    }

    @Override
    public ClientDTO displayClient(String clientId)
    {
        return clientFacade.displayClient(clientId);
    }

    @Override
    public ClientDTO displayClientForUsername(String username)
    {
        return clientFacade.displayClientForUsername(username);
    }
    
    @Override
    public ArrayList<ClientDTO> displayAllClient()
    {
        return clientFacade.displayAllClient();
    }

    @Override
    public String createEvent(EventDTO eventDTO)
    {
        return eventFacade.createEvent(eventDTO);
    }

    @Override
    public boolean removeEvent(String eventId)
    {
        return eventFacade.removeEvent(eventId);
    }

    @Override
    public boolean updateEvent(EventDTO eventDTO)
    {
        return eventFacade.updateEvent(eventDTO);
    }

    @Override
    public EventDTO displayEvent(String eventId)
    {
        return eventFacade.displayEvent(eventId);
    }

    @Override
    public EventDTO displayLastEventForParcel(String trackingNo)
    {
        return eventFacade.displayLastEventForParcel(trackingNo);
    }

    @Override
    public ArrayList<EventDTO> displayAllEvent()
    {
        return eventFacade.displayAllEvent();
    }

    @Override
    public ArrayList<EventDTO> displayEventsForParcel(String trackingNo)
    {
        return eventFacade.displayEventsForParcel(trackingNo);
    }

    @Override
    public String createPayment(PaymentDTO paymentDTO)
    {
        return paymentFacade.createPayment(paymentDTO);
    }

    @Override
    public boolean removePayment(String paymentId)
    {
        return paymentFacade.removePayment(paymentId);
    }

    @Override
    public boolean updatePayment(PaymentDTO paymentDTO)
    {
        return paymentFacade.updatePayment(paymentDTO);
    }

    @Override
    public PaymentDTO displayPayment(String paymentId)
    {
        return paymentFacade.displayPayment(paymentId);
    }

    @Override
    public ArrayList<PaymentDTO> displayAllPayment()
    {
        return paymentFacade.displayAllPayment();
    }

    @Override
    public ArrayList<PaymentDTO> displayPaymentForClient(String clientId)
    {
        return paymentFacade.displayPaymentForClient(clientId);
    }
}
