package com.techelevator.controller;

import com.techelevator.dao.JdbcUserDao;
import com.techelevator.dao.ServiceRequestDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.ServiceRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ServiceRequestController {

    private ServiceRequestDao serviceRequestDao;
    private UserDao userDao;

    public ServiceRequestController(ServiceRequestDao serviceRequestDao, UserDao userDao) {
        this.serviceRequestDao = serviceRequestDao;
        this.userDao = userDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/create_service_request", method = RequestMethod.POST)
    public void createRequest(@RequestBody ServiceRequest serviceRequest, Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        serviceRequestDao.createRequest(serviceRequest, userId);
    }

    @RequestMapping(path = "/my_service_requests", method = RequestMethod.GET)
    public List<ServiceRequest> submittedRequestInfo(Principal principal) {
        //List<ServiceRequest> userRequests;
        int userId = userDao.findIdByUsername(principal.getName());
        return serviceRequestDao.submittedRequestInfo(userId);
    }

    //    @PreAuthorize("isAuthenticated()") //only for employee or admin
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    @RequestMapping(path = "/all_service_requests", method = RequestMethod.GET)
    public List<ServiceRequest> allServiceRequests() {
        return serviceRequestDao.showAllRequests();
    }

    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    @RequestMapping(path = "/all_pending_requests", method = RequestMethod.GET)
    public List<ServiceRequest> allPendingRequests() {
        return serviceRequestDao.showAllPendingRequests();
    }

    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    @RequestMapping(path = "/all_in_progress_requests", method = RequestMethod.GET)
    public List<ServiceRequest> allInProgressRequests() {
        return serviceRequestDao.showAllInProgressRequests();
    }

    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    @RequestMapping(path = "/all_completed_requests", method = RequestMethod.GET)
    public List<ServiceRequest> allCompletedRequests() {
        return serviceRequestDao.showAllCompletedRequests();
    }

    @RequestMapping(path = "/update_request_status", method = RequestMethod.PUT)
    public void updateRequestStatus(@RequestBody ServiceRequest serviceRequest) {
        serviceRequestDao.updateServiceRequestStatus(serviceRequest);
    }

    @RequestMapping(path = "/delete_service_request/{requestId}", method = RequestMethod.DELETE)
    public void deleteServiceRequest(@PathVariable int requestId) {
        serviceRequestDao.deleteServiceRequest(requestId);
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @RequestMapping(path = "/get_request_byId/{requestId}", method = RequestMethod.GET)
    public ServiceRequest getRequestById(@PathVariable int requestId) {
        return serviceRequestDao.getServiceRequest(requestId);
    }


}
