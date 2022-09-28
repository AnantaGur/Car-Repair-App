package com.techelevator.dao;

import com.techelevator.model.ServiceRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcServiceRequestDao implements ServiceRequestDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcServiceRequestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ServiceRequest createRequest(ServiceRequest serviceRequest) {
        ServiceRequest createdRequest = new ServiceRequest();
        String sql = "INSERT INTO service_request " +
                "(vehicle_make, vehicle_model, vehicle_color, vehicle_year, service_name, full_name, phone_number, email, user_id)" +
                " VALUES (?, ?, ?, ?, ? , ?, ?, ?, ?) RETURNING request_id;";
        Integer serviceRequestId = jdbcTemplate.queryForObject(sql, Integer.class, serviceRequest.getVehicleMake(), serviceRequest.getVehicleModel(),
                serviceRequest.getVehicleColor(), serviceRequest.getVehicleYear(), serviceRequest.getServiceName(), serviceRequest.getFullName(),
                serviceRequest.getPhoneNumber(), serviceRequest.getEmail(), serviceRequest.getUserId());

        return getServiceRequest(serviceRequestId);
    }

    @Override
    public ServiceRequest getServiceRequest(int requestId) {
        ServiceRequest serviceRequest = null;
        String sql = "SELECT * FROM service_request WHERE request_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, requestId);
        if (results.next()){
            serviceRequest = mapRowToServiceRequest(results);
        }
        return serviceRequest;
    }

    @Override
    public List<ServiceRequest> submittedRequestInfo(int userId) {
        List<ServiceRequest> serviceRequestList = new ArrayList<>();

        String sql = "SELECT * FROM service_request WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()){
            ServiceRequest serviceRequest = mapRowToServiceRequest(results);
            serviceRequestList.add(serviceRequest);
        }
        return serviceRequestList;
    }

    private ServiceRequest mapRowToServiceRequest (SqlRowSet rs){
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setVehicleMake(rs.getString("vehicle_make"));
        serviceRequest.setVehicleModel(rs.getString("vehicle_model"));
        serviceRequest.setVehicleColor(rs.getString("vehicle_color"));
        serviceRequest.setVehicleYear(rs.getInt("vehicle_year"));
        serviceRequest.setServiceName(rs.getString("service_name"));
        serviceRequest.setFullName(rs.getString("full_name"));
        serviceRequest.setPhoneNumber(rs.getString("phone_number"));
        serviceRequest.setEmail(rs.getString("email"));
        serviceRequest.setRequestId(rs.getInt("request_id"));
        serviceRequest.setUserId(rs.getInt("user_id"));
        serviceRequest.setRequestStatus(rs.getString("request_status"));
        return serviceRequest;
    }
}
