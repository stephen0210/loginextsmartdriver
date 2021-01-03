package com.example.loginext.entity.order;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ORDER_DETAILS")
public class OrderDetails {
    @Id
    @SequenceGenerator(name="ORDER_NO_SEQ", sequenceName="ORDER_NO_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDER_NO_SEQ")
    @Column(name = "ORDER_NO")
    private int orderNumber;

    @Column(name = "CUST_ID")
    private String customerName;

    @Column(name = "CUST_LAT")
    private double customerLatitude;

    @Column(name = "CUST_LONG")
    private double customerLongitude;

}
