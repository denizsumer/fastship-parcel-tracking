/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastship.session;

import com.fastship.entity.Payment;
import com.fastship.entity.PaymentDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Deniz
 */
@Stateless
public class PaymentFacade
{

    @PersistenceContext(unitName = "ED-FASTSHIP-ejbPU")
    private EntityManager em;

    public PaymentFacade()
    {
    }

    private void create(Payment o)
    {
        em.persist(o);
    }

    private void edit(Payment o)
    {
        em.merge(o);
    }

    private void remove(Payment o)
    {
        em.remove(o);
    }

    private Payment find(String id)
    {
        return em.find(Payment.class, id);
    }

    public boolean hasPayment(String paymentId)
    {
        return (find(paymentId) != null);
    }

    public String createPayment(PaymentDTO paymentDTO)
    {
        try
        {
            String paymentId = getNewPaymentId();
            Payment payment = ORConv.paymentDTO2DAO(paymentDTO);
            payment.setPaymentId(paymentId);
            this.create(payment);
            return paymentId;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in PaymentFacade.createPayment()");
            return null;
        }
    }

    public boolean removePayment(String paymentId)
    {
        try
        {
            Payment payment = find(paymentId);
            if (payment == null)
            {
                //payment not exists
                return false;
            }
            this.remove(payment);
            return true;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in PaymentFacade.removePayment()");
            return false;
        }
    }

    public boolean updatePayment(PaymentDTO paymentDTO)
    {
        try
        {
            if (find(paymentDTO.getPaymentId()) == null)
            {
                //payment not exists
                return false;
            }
            Payment payment = ORConv.paymentDTO2DAO(paymentDTO);
            this.edit(payment);
            return true;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in PaymentFacade.updatePayment()");
            return false;
        }
    }

    public PaymentDTO displayPayment(String paymentId)
    {
        try
        {
            Payment payment = this.find(paymentId);
            if (payment == null)
            {
                //payment not exists
                return null;
            }
            return ORConv.paymentDAO2DTO(payment);
        }
        catch (Exception ex)
        {
            System.out.println("Ex in PaymentFacade.findPayment()");
            return null;
        }
    }
    
    public ArrayList<PaymentDTO> displayAllPayment()
    {
        try
        {
            ArrayList<PaymentDTO> paymentDTOList = null;
            Query query = em.createNamedQuery("Payment.findAll");
            List<Payment> paymentList = (List<Payment>) query.getResultList();
            if (!paymentList.isEmpty())
            {
                paymentDTOList = new ArrayList<>();
                for (Payment p : paymentList)
                {
                    paymentDTOList.add(ORConv.paymentDAO2DTO(p));
                }
            }
            return paymentDTOList;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in PaymentFacade.displayAllPayment()");
            return null;
        }
    }

    public ArrayList<PaymentDTO> displayPaymentForClient(String clientId)
    {
        try
        {
            ArrayList<PaymentDTO> paymentDTOList = null;
            Query query = em.createNamedQuery("Payment.findByDebtor").setParameter("debtor", clientId);
            List<Payment> paymentList = (List<Payment>) query.getResultList();
            if (!paymentList.isEmpty())
            {
                paymentDTOList = new ArrayList<>();
                for (Payment p : paymentList)
                {
                    paymentDTOList.add(ORConv.paymentDAO2DTO(p));
                }
            }
            return paymentDTOList;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in PaymentFacade.displayPaymentByClient()");
            return null;
        }
    }
    
    public String getNewPaymentId()
    {
        try
        {
            int totalPayment = displayAllPayment().size();
            int newPaymentIndex = totalPayment + 1;
            String newPaymentIdIndex = String.format("%06d", newPaymentIndex);
            String newPaymentId = "PY" + newPaymentIdIndex;
            return newPaymentId;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in PaymentFacade.getNewPaymentId()");
            return null;
        }
    }
}
