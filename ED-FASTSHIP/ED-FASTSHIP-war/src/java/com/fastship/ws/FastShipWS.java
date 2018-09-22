/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastship.ws;

import com.fastship.entity.EventDTO;
import com.fastship.entity.ParcelDTO;
import com.fastship.entity.PaymentDTO;
import com.fastship.session.ManagementFacadeRemote;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Deniz
 */
@WebService(serviceName = "FastShipWS")
public class FastShipWS
{

    @EJB
    private ManagementFacadeRemote ejbRef;

    @WebMethod(operationName = "displayParcel")
    public ParcelDTO displayParcel(@WebParam(name = "trackingNo") String trackingNo)
    {
        return ejbRef.displayParcel(trackingNo);
    }

    @WebMethod(operationName = "displayParcelForClient")
    public ArrayList<ParcelDTO> displayParcelForClient(@WebParam(name = "clientId") String clientId)
    {
        return ejbRef.displayParcelForClient(clientId);
    }

    @WebMethod(operationName = "displayEventsForParcel")
    public ArrayList<EventDTO> displayEventsForParcel(@WebParam(name = "trackingNo") String trackingNo)
    {
        return ejbRef.displayEventsForParcel(trackingNo);
    }

    @WebMethod(operationName = "displayPayment")
    public PaymentDTO displayPayment(@WebParam(name = "paymentId") String paymentId)
    {
        return ejbRef.displayPayment(paymentId);
    }

    @WebMethod(operationName = "displayPaymentForClient")
    public ArrayList<PaymentDTO> displayPaymentForClient(@WebParam(name = "clientId") String clientId)
    {
        return ejbRef.displayPaymentForClient(clientId);
    }

}
