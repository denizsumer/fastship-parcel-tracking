package com.fastship.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-07T11:45:31")
@StaticMetamodel(Payment.class)
public class Payment_ { 

    public static volatile SingularAttribute<Payment, String> reference;
    public static volatile SingularAttribute<Payment, Boolean> isPaid;
    public static volatile SingularAttribute<Payment, BigDecimal> amount;
    public static volatile SingularAttribute<Payment, String> debtor;
    public static volatile SingularAttribute<Payment, String> paymentId;

}