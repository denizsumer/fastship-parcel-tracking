package com.fastship.entity;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-07T11:45:31")
@StaticMetamodel(Event.class)
public class Event_ { 

    public static volatile SingularAttribute<Event, String> eventId;
    public static volatile SingularAttribute<Event, String> activity;
    public static volatile SingularAttribute<Event, String> trackingNo;
    public static volatile SingularAttribute<Event, Calendar> eventTime;
    public static volatile SingularAttribute<Event, String> additionalInfo;
    public static volatile SingularAttribute<Event, String> location;
    public static volatile SingularAttribute<Event, String> status;

}