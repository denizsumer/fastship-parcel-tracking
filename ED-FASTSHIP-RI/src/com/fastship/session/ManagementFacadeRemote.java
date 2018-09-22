/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastship.session;

import com.fastship.entity.*;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Deniz
 */
@Remote
public interface ManagementFacadeRemote
{
    //PARCEL

    String createParcel(ParcelDTO parcelDTO);

    boolean removeParcel(String trackingNo);

    boolean updateParcel(ParcelDTO parcelDTO);

    ParcelDTO displayParcel(String trackingNo);

    ArrayList<ParcelDTO> displayAllParcel();

    ArrayList<ParcelDTO> displayParcelForClient(String clientId);

    //CLIENT
    String createClient(ClientDTO clientDTO);

    boolean removeClient(String clientId);

    boolean updateClient(ClientDTO clientDTO);

    ClientDTO displayClient(String clientId);

    ClientDTO displayClientForUsername(String username);
    
    ArrayList<ClientDTO> displayAllClient();

    //EVENT
    String createEvent(EventDTO eventDTO);

    boolean removeEvent(String eventId);

    boolean updateEvent(EventDTO eventDTO);

    EventDTO displayEvent(String eventId);

    ArrayList<EventDTO> displayAllEvent();

    EventDTO displayLastEventForParcel(String trackingNo);

    ArrayList<EventDTO> displayEventsForParcel(String trackingNo);

    //PAYMENT
    String createPayment(PaymentDTO paymentDTO);

    boolean removePayment(String paymentId);

    boolean updatePayment(PaymentDTO paymentDTO);

    PaymentDTO displayPayment(String paymentId);

    ArrayList<PaymentDTO> displayAllPayment();

    ArrayList<PaymentDTO> displayPaymentForClient(String clientId);
}
