/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastship.ws;

import java.util.List;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Deniz
 */
public class FastShipWSAppClient
{

    @WebServiceRef(wsdlLocation = "http://localhost:8080/ED-FASTSHIP-war/FastShipWS?wsdl")
    private static FastShipWS_Service service;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        FastShipWSAppClient client = new FastShipWSAppClient();
        client.run();
    }

    private void run()
    {
        boolean isRun = true;
        while (isRun)
        {
            IOHandler.clearConsole();
            System.out.println("============ FAST TRACK WS CLIENT =============");
            System.out.println("[1] Display parcel by tracking number");
            System.out.println("[2] Display payment by client id");
            System.out.println("[0] Exit");
            int selection = IOHandler.readIntegerRange("Select an option : ", 0, 2);
            switch (selection)
            {
                case 1:
                    askForParameter("tn");
                    break;
                case 2:
                    askForParameter("ci");
                    break;
                case 0:
                    isRun = false;
                    break;
            }
        }
    }

    private void askForParameter(String param)
    {
        if (param.equals("tn"))
        {
            String tn = IOHandler.readString("Enter tracking number : ");
            displayParcel(tn);
        }
        if (param.equals("ci"))
        {
            String ci = IOHandler.readString("Enter client Id : ");
            displayPaymentForClient(ci);
        }
    }

    private void displayParcel(String trackingNo)
    {
        FastShipWS port = service.getFastShipWSPort();
        ParcelDTO parcel = port.displayParcel(trackingNo);
        if (parcel != null)
        {
            printParcel(parcel);
            PaymentDTO payment = port.displayPayment(parcel.getPaymentId());
            printPayment(payment);
            List<EventDTO> eventList = port.displayEventsForParcel(trackingNo);
            for (EventDTO e : eventList)
            {
                printEvent(e);
            }
        }
        else
        {
            System.out.println("================= PARCEL ==================");
            System.out.println("Parcel with tracking number : " + trackingNo + " not found!");
        }
        System.out.println("============================================");
        IOHandler.pressEnter();
    }

    private void displayPaymentForClient(String clientId)
    {
        FastShipWS port = service.getFastShipWSPort();
        List<PaymentDTO> paymentList = port.displayPaymentForClient(clientId);
        if (paymentList.size() > 0)
        {
            for (PaymentDTO p : paymentList)
            {
                printPayment(p);
            }
        }
        else
        {
            System.out.println("================= PAYMENT =================");
            System.out.println("There is no payment for client : " + clientId);
        }
        System.out.println("============================================");
        IOHandler.pressEnter();
    }

    private void printParcel(ParcelDTO parcel)
    {
        System.out.println("================= PARCEL ==================");
        System.out.println("Tracking No : \t\t" + parcel.getTrackingNo());
        System.out.println("Payment ID : \t\t" + parcel.getPaymentId());
        System.out.println("Service : \t\t" + parcel.getService());
        System.out.println("From Client (ID) : \t" + parcel.getFromClientId());
        System.out.println("To Client (ID) : \t" + parcel.getToClientId());
        System.out.println("Volume : \t\t" + parcel.getVolume());
        System.out.println("Weight : \t\t" + parcel.getWeight());

    }

    private void printPayment(PaymentDTO payment)
    {
        System.out.println("================= PAYMENT ==================");
        System.out.println("Payment ID : \t\t" + payment.getPaymentId());
        System.out.println("Debtor : \t\t" + payment.getDebtor());
        System.out.println("Amount : \t\t" + payment.getAmount());
        System.out.println("Is Paid : \t\t" + payment.isIsPaid());
        System.out.println("Description : \t\t" + payment.getReference());
    }

    private void printEvent(EventDTO event)
    {
        System.out.println("============= EVENT : " + event.getEventId() + " ==============");
        System.out.println("Event ID : \t\t" + event.getEventId());
        System.out.println("Tracking No : \t\t" + event.getTrackingNo());
        System.out.println("Event Time : \t\t" + event.getEventTime());
        System.out.println("Location : \t\t" + event.getLocation());
        System.out.println("Status : \t\t" + event.getStatus());
        System.out.println("Activity : \t\t" + event.getActivity());
        System.out.println("Additional Info : \t" + event.getAdditionalInfo());
    }
}
