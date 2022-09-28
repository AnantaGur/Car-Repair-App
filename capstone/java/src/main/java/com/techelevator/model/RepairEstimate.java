package com.techelevator.model;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

public class RepairEstimate {
    private int repairId;
    private int requestId;
    private BigDecimal partsCost;
    private BigDecimal laborCost;
    private double totalTime;
    private Date pickUpDate;
    private Time pickUpTime;

    public RepairEstimate(int repairId, int requestId, BigDecimal partsCost, BigDecimal laborCost, double totalTime, Date pickUpDate, Time pickUpTime) {
        this.repairId = repairId;
        this.requestId = requestId;
        this.partsCost = partsCost;
        this.laborCost = laborCost;
        this.totalTime = totalTime;
        this.pickUpDate = pickUpDate;
        this.pickUpTime = pickUpTime;
    }

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public BigDecimal getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(BigDecimal partsCost) {
        this.partsCost = partsCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public Time getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(Time pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

}
