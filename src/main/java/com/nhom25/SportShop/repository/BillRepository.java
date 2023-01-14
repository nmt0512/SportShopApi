package com.nhom25.SportShop.repository;

import com.nhom25.SportShop.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.security.Timestamp;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    @Query(value = "SELECT * FROM Bill b WHERE DATEDIFF(day, b.Time, :time) = 0", nativeQuery = true)
    List<Bill> findByDay(@Param("time") String time);

    @Query(value = "SELECT * FROM Bill WHERE MONTH(Time) = :month AND YEAR(Time) = :year", nativeQuery = true)
    List<Bill> findByMonth(@Param("month") String month, @Param("year") String year);

    List<Bill> findByConfirmAndStatus(Boolean confirm, Boolean status);
    List<Bill> findByStatus(Boolean status);
}
