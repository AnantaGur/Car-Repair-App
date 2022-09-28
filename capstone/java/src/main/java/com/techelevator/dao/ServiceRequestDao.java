package com.techelevator.dao;

import com.techelevator.model.ServiceRequest;

import java.util.List;

public interface ServiceRequestDao {
    //getStatus for user

    ServiceRequest createRequest (ServiceRequest serviceRequest);

    ServiceRequest getServiceRequest (int requestId);

    List<ServiceRequest> submittedRequestInfo(int userId);



    //getTimeForCompletion for user

    //totalCost for user and employee


    //getServiceRequestByUsername for employee

    //getRequestByStatus for employee

}
