package com.nhom25.SportShop.repository;

import com.nhom25.SportShop.dto.RevenueDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RevenueRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<RevenueDto> getAllMonthTotalRevenue()
    {
        String query = "SELECT FORMAT(Time, 'MM/yyyy') MonthTime, SUM(TotalPrice) TotalRevenue " +
                "FROM Bill GROUP BY FORMAT(Time, 'MM/yyyy')";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(RevenueDto.class));
    }
}
