package com.techelevator.controller;

import com.techelevator.dao.ServiceRequestDao;
import com.techelevator.model.ServiceRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ServiceRequestController {

    private ServiceRequestDao serviceRequestDao;

    public ServiceRequestController(ServiceRequestDao serviceRequestDao) {
        this.serviceRequestDao = serviceRequestDao;
    }

    @RequestMapping(path = "/service_requests", method = RequestMethod.GET)
    public void createRequest(@Valid @RequestBody ServiceRequest serviceRequest){

    }
}
