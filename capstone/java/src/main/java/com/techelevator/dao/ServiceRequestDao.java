package com.techelevator.dao;

import com.techelevator.model.ServiceRequest;

import java.util.List;

public interface ServiceRequestDao {
    //getStatus for user

    ServiceRequest createRequest (ServiceRequest serviceRequest, int userId);

    ServiceRequest getServiceRequest (int requestId);

    List<ServiceRequest> submittedRequestInfo(int userId);

    List<ServiceRequest> showAllRequests();

    List<ServiceRequest> showAllPendingRequests();

    List<ServiceRequest> showAllInProgressRequests();

    List<ServiceRequest> showAllCompletedRequests();





    //getTimeForCompletion for user

    //totalCost for user and employee


    //getServiceRequestByUsername for employee

    //getRequestByStatus for employee

}
