/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastship.session;

import com.fastship.entity.Event;
import com.fastship.entity.EventDTO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Deniz
 */
@Stateless
public class EventFacade
{

    @PersistenceContext(unitName = "ED-FASTSHIP-ejbPU")
    private EntityManager em;

    public EventFacade()
    {
    }

    private void create(Event o)
    {
        em.persist(o);
    }

    private void edit(Event o)
    {
        em.merge(o);
    }

    private void remove(Event o)
    {
        em.remove(o);
    }

    private Event find(String id)
    {
        return em.find(Event.class, id);
    }

    public boolean hasEvent(String eventId)
    {
        return (find(eventId) != null);
    }

    public String createEvent(EventDTO eventDTO)
    {
        try
        {
            String eventId = getNewEventId();
            Event event = ORConv.eventDTO2DAO(eventDTO);
            event.setEventId(eventId);
            this.create(event);
            return eventId;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in EventFacade.createEvent()");
            return null;
        }
    }

    public boolean removeEvent(String eventId)
    {
        try
        {
            Event event = find(eventId);
            if (event == null)
            {
                //event not exists
                return false;
            }
            this.remove(event);
            return true;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in EventFacade.removeEvent()");
            return false;
        }
    }

    public boolean updateEvent(EventDTO eventDTO)
    {
        try
        {
            if (find(eventDTO.getEventId()) == null)
            {
                //event not exists
                return false;
            }
            Event event = ORConv.eventDTO2DAO(eventDTO);
            this.edit(event);
            return true;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in EventFacade.updateEvent()");
            return false;
        }
    }

    public EventDTO displayEvent(String eventId)
    {
        try
        {
            Event event = this.find(eventId);
            if (event == null)
            {
                //event not exists
                return null;
            }
            return ORConv.eventDAO2DTO(event);
        }
        catch (Exception ex)
        {
            System.out.println("Ex in EventFacade.findEvent()");
            return null;
        }
    }

    public EventDTO displayLastEventForParcel(String trackingNo)
    {
        try
        {
            Query query = em.createNamedQuery("Event.findByTrackingNo").setParameter("trackingno", trackingNo);
            List<Event> eventList = (List<Event>) query.getResultList();
            Collections.sort(eventList, (Event e1, Event e2) ->
            {
                if (e1.getEventTime() == null || e2.getEventTime() == null)
                {
                    return 0;
                }
                return e1.getEventTime().compareTo(e2.getEventTime());
            });
            return ORConv.eventDAO2DTO(eventList.get(eventList.size() - 1));
        }
        catch (Exception ex)
        {
            System.out.println("Ex in EventFacade.displayLastEventForParcel()");
            return null;
        }
    }

    public ArrayList<EventDTO> displayAllEvent()
    {
        try
        {
            ArrayList<EventDTO> eventDTOList = null;
            Query query = em.createNamedQuery("Event.findAll");
            List<Event> eventList = (List<Event>) query.getResultList();
            if (!eventList.isEmpty())
            {
                eventDTOList = new ArrayList<>();
                for (Event p : eventList)
                {
                    eventDTOList.add(ORConv.eventDAO2DTO(p));
                }
            }
            return eventDTOList;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in EventFacade.displayAllEvent()");
            return null;
        }
    }

    public ArrayList<EventDTO> displayEventsForParcel(String trackingNo)
    {
        try
        {
            ArrayList<EventDTO> eventDTOList = null;
            Query query = em.createNamedQuery("Event.findByTrackingNo").setParameter("trackingno", trackingNo);
            List<Event> eventList = (List<Event>) query.getResultList();
            if (!eventList.isEmpty())
            {
                eventDTOList = new ArrayList<>();
                for (Event e : eventList)
                {
                    eventDTOList.add(ORConv.eventDAO2DTO(e));
                }
            }
            return eventDTOList;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in EventFacade.displayEventsForParcel()");
            return null;
        }
    }

    public String getNewEventId()
    {
        try
        {
            int totalEvents = displayAllEvent().size();
            int newEventIndex = totalEvents + 1;
            String newEventNoIndex = String.format("%06d", newEventIndex);
            String newEventNo = "EV" + newEventNoIndex;
            System.out.println("NEW EVENT NO: " + newEventNo);
            return newEventNo;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in EventFacade.getNewEventId()");
            return null;
        }
    }
}
