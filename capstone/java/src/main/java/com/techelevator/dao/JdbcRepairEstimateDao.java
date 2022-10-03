package com.techelevator.dao;

import com.techelevator.model.RepairEstimate;
import com.techelevator.model.ServiceRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRepairEstimateDao implements RepairEstimateDao {
    private JdbcTemplate jdbcTemplate;
    private UserDao userDao;
    private ServiceRequestDao serviceRequestDao;

    public JdbcRepairEstimateDao(JdbcTemplate jdbcTemplate, UserDao userDao, ServiceRequestDao serviceRequestDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
        this.serviceRequestDao = serviceRequestDao;
    }

    @Override
    public RepairEstimate createRepairEstimate(RepairEstimate repairEstimate, int requestId) {

        String sql = "INSERT INTO repair_estimate " +
                "(request_id, parts_cost, labor_cost, total_time, pick_up_date, pick_up_time) " +
                "VALUES " +
                "(?, ?, ?, ?, ?, ?) RETURNING repair_id;";
        Integer repairEstimateId = jdbcTemplate.queryForObject(sql, Integer.class, requestId, repairEstimate.getPartsCost(),
                repairEstimate.getLaborCost(), repairEstimate.getTotalTime(), repairEstimate.getPickUpDate(), repairEstimate.getPickUpTime());


        return getRepairEstimate(repairEstimateId);
    }

    @Override
    public RepairEstimate getRepairEstimate(int repairId) {
        RepairEstimate repairEstimate = null;
        String sql = "SELECT * FROM repair_estimate WHERE repair_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, repairId);
        if(results.next()){
            repairEstimate = mapRowToRepairEstimate(results);
        }
        return repairEstimate;
    }

    @Override
    public List<RepairEstimate> myRepairEstimate(int userId) {
        List<RepairEstimate> listOfRepairEstimate = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM repair_estimate " +
                "JOIN service_request ON service_request.request_id = repair_estimate.request_id " +
                "JOIN users ON users.user_id = service_request.user_id " +
                "WHERE users.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()){
            listOfRepairEstimate.add(mapRowToRepairEstimate(results));
        }
        return listOfRepairEstimate;
    }

    private RepairEstimate mapRowToRepairEstimate(SqlRowSet rs){
        RepairEstimate repairEstimate = new RepairEstimate();
        repairEstimate.setRepairId(rs.getInt("repair_id"));
        repairEstimate.setRequestId(rs.getInt("request_id"));
        repairEstimate.setPartsCost(rs.getBigDecimal("parts_cost"));
        repairEstimate.setLaborCost(rs.getBigDecimal("labor_cost"));
        repairEstimate.setTotalTime(rs.getDouble("total_time"));
        repairEstimate.setPickUpDate(rs.getDate("pick_up_date"));
        repairEstimate.setPickUpTime(rs.getTime("pick_up_time").toLocalTime());
        return repairEstimate;
    }
}
