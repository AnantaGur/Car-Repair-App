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
        TimeFomat dateFormat = new SimpleDateFormat("hh:mm");

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

    private RepairEstimate mapRowToRepairEstimate(SqlRowSet rs){
        RepairEstimate repairEstimate = new RepairEstimate();
        repairEstimate.setRepairId(rs.getInt("repair_id"));
        repairEstimate.setPartsCost(rs.getBigDecimal("parts_cost"));
        repairEstimate.setLaborCost(rs.getBigDecimal("labor_cost"));
        repairEstimate.setTotalTime(rs.getDouble("total_time"));
        repairEstimate.setPickUpDate(rs.getDate("pick_up_date"));
        repairEstimate.setPickUpTime(rs.getString("pick_up_time"));
        return repairEstimate;
    }
}
