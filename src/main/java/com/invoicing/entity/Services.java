package com.invoicing.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Entity
public class Services {
    private int id;
    private BigDecimal amount;
    private BigDecimal vatAmount;
    private BigDecimal totalAmount;
    private String serviceType;
    private Customer customerByCustomerId;
    private Address addressByAddressId;
    private Timestamp timestamp;
    private int version;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "amount", nullable = false, precision = 2)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "vat_amount", nullable = false, precision = 2)
    public BigDecimal getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(BigDecimal vatAmount) {
        this.vatAmount = vatAmount;
    }

    @Basic
    @Column(name = "total_amount", nullable = false, precision = 2)
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Basic
    @Column(name = "service_type", nullable = false, length = 50)
    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    public Customer getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(Customer customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    public Address getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(Address addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }

    @Basic
    @Column(name = "timestamp", nullable = false)
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Services)) return false;

        Services services = (Services) o;

        if (!serviceType.equals(services.serviceType)) return false;
        if (!customerByCustomerId.equals(services.customerByCustomerId)) return false;
        if (!addressByAddressId.equals(services.addressByAddressId)) return false;
        return timestamp.equals(services.timestamp);

    }

    @Override
    public int hashCode() {
        int result = serviceType.hashCode();
        result = 31 * result + customerByCustomerId.hashCode();
        result = 31 * result + addressByAddressId.hashCode();
        result = 31 * result + timestamp.hashCode();
        return result;
    }

    @Basic
    @Column(name = "version", nullable = false)
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
