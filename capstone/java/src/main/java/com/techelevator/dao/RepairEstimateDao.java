package com.techelevator.dao;

import com.techelevator.model.RepairEstimate;
import com.techelevator.model.ServiceRequest;

import java.util.List;

public interface RepairEstimateDao {

    RepairEstimate createRepairEstimate (RepairEstimate repairEstimate, int requestId);

    RepairEstimate getRepairEstimate (int repairId);

}
