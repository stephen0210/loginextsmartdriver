CREATE TABLE public.driver_details
(
    driver_id character varying(50)  NOT NULL,
    cust_id character varying(50) ,
    driver_lat double precision,
    driver_long double precision,
    status character varying(20) ,
    version numeric,
    CONSTRAINT driver_namepk PRIMARY KEY (driver_id)
)

CREATE TABLE public.order_details
(
    order_no double precision NOT NULL,
    cust_id character varying(50) ,
    cust_lat double precision,
    cust_long double precision,
    CONSTRAINT order_detailspk PRIMARY KEY (order_no)
)
