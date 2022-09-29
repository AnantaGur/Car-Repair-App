package com.techelevator.controller;

import com.techelevator.dao.RepairEstimateDao;
import com.techelevator.model.RepairEstimate;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class RepairEstimateController {

    private RepairEstimateDao repairEstimateDao;

    public RepairEstimateController(RepairEstimateDao repairEstimateDao) {
        this.repairEstimateDao = repairEstimateDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/create_repair_estimate", method = RequestMethod.POST)
    public void createRequest (@RequestBody RepairEstimate repairEstimate){
        repairEstimateDao.createRepairEstimate(repairEstimate,repairEstimate.getRequestId());
    }



}
