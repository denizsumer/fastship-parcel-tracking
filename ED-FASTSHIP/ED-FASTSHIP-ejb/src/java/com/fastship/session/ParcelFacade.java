/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastship.session;

import com.fastship.entity.*;
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
public class ParcelFacade
{

    @PersistenceContext(unitName = "ED-FASTSHIP-ejbPU")
    private EntityManager em;

    public ParcelFacade()
    {
    }

    private void create(Parcel o)
    {
        em.persist(o);
    }

    private void edit(Parcel o)
    {
        em.merge(o);
    }

    private void remove(Parcel o)
    {
        em.remove(o);
    }

    private Parcel find(String id)
    {
        return em.find(Parcel.class, id);
    }

    public boolean hasParcel(String trackingNo)
    {
        return (find(trackingNo) != null);
    }

    public String createParcel(ParcelDTO parcelDTO)
    {
        try
        {
            String trackingNo = getNewTrackingNo();
            Parcel parcel = ORConv.parcelDTO2DAO(parcelDTO);
            parcel.setTrackingNo(trackingNo);
            this.create(parcel);
            return trackingNo;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in ParcelFacade.createParcel()");
            return null;
        }
    }

    public boolean removeParcel(String trackingNo)
    {
        try
        {
            Parcel parcel = find(trackingNo);
            if (parcel == null)
            {
                //parcel not exists
                return false;
            }
            this.remove(parcel);
            return true;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in ParcelFacade.removeParcel()");
            return false;
        }
    }

    public boolean updateParcel(ParcelDTO parcelDTO)
    {
        try
        {
            if (find(parcelDTO.getTrackingNo()) == null)
            {
                //parcel not exists
                return false;
            }
            Parcel parcel = ORConv.parcelDTO2DAO(parcelDTO);
            this.edit(parcel);
            return true;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in ParcelFacade.updateParcel()");
            return false;
        }
    }

    public ParcelDTO displayParcel(String trackingNo)
    {
        try
        {
            Parcel parcel = this.find(trackingNo);
            if (parcel == null)
            {
                //parcel not exists
                return null;
            }
            return ORConv.parcelDAO2DTO(parcel);
        }
        catch (Exception ex)
        {
            System.out.println("Ex in ParcelFacade.findParcel()");
            return null;
        }
    }

    public ArrayList<ParcelDTO> displayParcelForClient(String clientId)
    {
        try
        {
            ArrayList<ParcelDTO> parcelDTOList = null;
            Query queryTo = em.createNamedQuery("Parcel.findByToClientId").setParameter("toclientid", clientId);
            List<Parcel> parcelListTo = (List<Parcel>) queryTo.getResultList();
            Query queryFrom = em.createNamedQuery("Parcel.findByFromClientId").setParameter("fromclientid", clientId);
            List<Parcel> parcelListFrom = (List<Parcel>) queryFrom.getResultList();
            if (!parcelListTo.isEmpty() || !parcelListFrom.isEmpty())
            {
                parcelDTOList = new ArrayList<>();
                for (Parcel p : parcelListTo)
                {
                    parcelDTOList.add(ORConv.parcelDAO2DTO(p));
                }
                for (Parcel p : parcelListFrom)
                {
                    parcelDTOList.add(ORConv.parcelDAO2DTO(p));
                }
            }
            return parcelDTOList;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in ParcelFacade.displayParcelByClient()");
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<ParcelDTO> displayAllParcel()
    {
        try
        {
            ArrayList<ParcelDTO> parcelDTOList = null;
            Query query = em.createNamedQuery("Parcel.findAll");
            List<Parcel> parcelList = (List<Parcel>) query.getResultList();
            if (!parcelList.isEmpty())
            {
                parcelDTOList = new ArrayList<ParcelDTO>();
                for (Parcel p : parcelList)
                {
                    parcelDTOList.add(ORConv.parcelDAO2DTO(p));
                }
            }
            return parcelDTOList;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in ParcelFacade.displayAllParcel()");
            return null;
        }
    }

    public String getNewTrackingNo()
    {
        try
        {
            int totalParcels = displayAllParcel().size();
            int newParcelIndex = totalParcels + 1;
            String newTrackingNoIndex = String.format("%06d", newParcelIndex);
            String newTrackingNo = "FS" + newTrackingNoIndex;
            System.out.println("NEW TRACKING NO: " + newTrackingNo);
            return newTrackingNo;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in ParcelFacade.getNewTrackingNo()");
            return null;
        }
    }
}
