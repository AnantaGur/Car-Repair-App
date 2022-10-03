package com.techelevator.controller;

import com.techelevator.dao.RepairEstimateDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.RepairEstimate;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class RepairEstimateController {

    private RepairEstimateDao repairEstimateDao;
    private UserDao userDao;

    public RepairEstimateController(RepairEstimateDao repairEstimateDao, UserDao userDao) {
        this.repairEstimateDao = repairEstimateDao;
        this.userDao = userDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @RequestMapping(path = "/create_repair_estimate", method = RequestMethod.POST)
    public void createRequest (@RequestBody RepairEstimate repairEstimate){
        repairEstimateDao.createRepairEstimate(repairEstimate,repairEstimate.getRequestId());
    }
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @RequestMapping(path = "/get_repair_byId/{repairId}", method = RequestMethod.GET)
    public RepairEstimate getRepairById (@PathVariable int repairId){
        return repairEstimateDao.getRepairEstimate(repairId);
    }

    @RequestMapping(path = "/my_repair_estimates", method = RequestMethod.GET)
    public List<RepairEstimate> myRepairEstimates (Principal principal){
        int myId = userDao.findIdByUsername(principal.getName());
        return repairEstimateDao.myRepairEstimate(myId);
    }


}
