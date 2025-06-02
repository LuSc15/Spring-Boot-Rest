package com.example.springapidemo.northwindAPI.models;

import java.util.Date;

public class Order {
    private int orderId;
    private String customerId;
    private int employeeId;
    private Date orderDate;
    private Date requiredDate;
    private Date shippedDate;
    private int shipVia;
    private double freight;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipRegion;
    private String shipPostalCode;
    private String shipCountry;
    private int orderYear;
    private int orderMonth;
    private double totalPrice;

    public Order(int orderId, String customerId, int employeeId, 
                 Date orderDate, Date requiredDate, Date shippedDate, int shipVia, double freight, 
                 String shipName, String shipAddress, String shipCity, String shipRegion, 
                 String shipPostalCode, String shipCountry, int orderYear, int orderMonth, double totalPrice) {
        this.orderId = orderId;
        this.customerId = customerId;
        
        this.employeeId = employeeId;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.shipVia = shipVia;
        this.freight = freight;
        this.shipName = shipName;
        this.shipAddress = shipAddress;
        this.shipCity = shipCity;
        this.shipRegion = shipRegion;
        this.shipPostalCode = shipPostalCode;
        this.shipCountry = shipCountry;
        this.orderYear = orderYear;
        this.orderMonth = orderMonth;
        this.totalPrice = totalPrice;
    }

    // Getter und Setter für alle Properties
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public Date getRequiredDate() { return requiredDate; }
    public void setRequiredDate(Date requiredDate) { this.requiredDate = requiredDate; }

    public Date getShippedDate() { return shippedDate; }
    public void setShippedDate(Date shippedDate) { this.shippedDate = shippedDate; }

    public int getShipVia() { return shipVia; }
    public void setShipVia(int shipVia) { this.shipVia = shipVia; }

    public double getFreight() { return freight; }
    public void setFreight(double freight) { this.freight = freight; }

    public String getShipName() { return shipName; }
    public void setShipName(String shipName) { this.shipName = shipName; }

    public String getShipAddress() { return shipAddress; }
    public void setShipAddress(String shipAddress) { this.shipAddress = shipAddress; }

    public String getShipCity() { return shipCity; }
    public void setShipCity(String shipCity) { this.shipCity = shipCity; }

    public String getShipRegion() { return shipRegion; }
    public void setShipRegion(String shipRegion) { this.shipRegion = shipRegion; }

    public String getShipPostalCode() { return shipPostalCode; }
    public void setShipPostalCode(String shipPostalCode) { this.shipPostalCode = shipPostalCode; }

    public String getShipCountry() { return shipCountry; }
    public void setShipCountry(String shipCountry) { this.shipCountry = shipCountry; }

    public int getOrderYear() { return orderYear; }
    public void setOrderYear(int orderYear) { this.orderYear = orderYear; }

    public int getOrderMonth() { return orderMonth; }
    public void setOrderMonth(int orderMonth) { this.orderMonth = orderMonth; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}