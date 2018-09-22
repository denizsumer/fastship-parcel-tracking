/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastship.session;

import com.fastship.entity.*;

/**
 *
 * @author Deniz
 */
class ORConv
{

    static EventDTO eventDAO2DTO(Event event)
    {
        EventDTO eventDTO = new EventDTO(event.getEventId(), event.getEventTime(), event.getTrackingNo(), event.getLocation(), event.getStatus(), event.getActivity(), event.getAdditionalInfo());
        return eventDTO;
    }

    static ClientDTO clientDAO2DTO(Client client)
    {
        ClientDTO clientDTO = new ClientDTO(client.getClientId(), client.getName(), client.getPic(), client.getPhone(), client.getAddress(), client.getEmail(), client.getUsername(), client.getPassword(), client.getUserGroup());
        return clientDTO;
    }

    static ParcelDTO parcelDAO2DTO(Parcel parcel)
    {
        ParcelDTO parcelDTO = new ParcelDTO(parcel.getTrackingNo(), parcel.getFromClientId(), parcel.getToClientId(), parcel.getVolume(), parcel.getWeight(), parcel.getPaymentId(), parcel.getService());
        return parcelDTO;
    }

    static PaymentDTO paymentDAO2DTO(Payment payment)
    {
        PaymentDTO paymentDTO = new PaymentDTO(payment.getPaymentId(), payment.getDebtor(), payment.getAmount(), payment.getIsPaid(), payment.getReference());
        return paymentDTO;
    }

    static Client clientDTO2DAO(ClientDTO clientDTO)
    {
        Client client = new Client();
        client.setClientId(clientDTO.getClientId());
        client.setName(clientDTO.getName());
        client.setPic(clientDTO.getPic());
        client.setPhone(clientDTO.getPhone());
        client.setAddress(clientDTO.getAddress());
        client.setEmail(clientDTO.getEmail());
        client.setUsername(clientDTO.getUsername());
        client.setPassword(clientDTO.getPassword());
        client.setUserGroup(clientDTO.getUsergroup());
        return client;
    }

    static Event eventDTO2DAO(EventDTO eventDTO)
    {
        Event event = new Event();
        event.setEventId(eventDTO.getEventId());
        event.setEventTime(eventDTO.getEventTime());
        event.setTrackingNo(eventDTO.getTrackingNo());
        event.setLocation(eventDTO.getLocation());
        event.setStatus(eventDTO.getStatus());
        event.setActivity(eventDTO.getActivity());
        event.setAdditionalInfo(eventDTO.getAdditionalInfo());
        return event;
    }

    static Parcel parcelDTO2DAO(ParcelDTO parcelDTO)
    {
        Parcel parcel = new Parcel();
        parcel.setTrackingNo(parcelDTO.getTrackingNo());
        parcel.setFromClientId(parcelDTO.getFromClientId());
        parcel.setToClientId(parcelDTO.getToClientId());
        parcel.setVolume(parcelDTO.getVolume());
        parcel.setWeight(parcelDTO.getWeight());
        parcel.setPaymentId(parcelDTO.getPaymentId());
        parcel.setService(parcelDTO.getService());
        return parcel;
    }

    static Payment paymentDTO2DAO(PaymentDTO paymentDTO)
    {
        Payment payment = new Payment();
        payment.setPaymentId(paymentDTO.getPaymentId());
        payment.setDebtor(paymentDTO.getDebtor());
        payment.setAmount(paymentDTO.getAmount());
        payment.setIsPaid(paymentDTO.getIsPaid());
        payment.setReference(paymentDTO.getReference());
        return payment;
    }

}
