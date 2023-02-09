package com.nhom25.SportShop.repository;

import com.nhom25.SportShop.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    @Query(value = "SELECT * FROM Bill WHERE FORMAT(Time, 'dd/MM/yyyy') = :time", nativeQuery = true)
    List<Bill> findByDay(@Param("time") String time);
    @Query(value = "SELECT * FROM Bill WHERE FORMAT(Time, 'MM/yyyy') = :time", nativeQuery = true)
    List<Bill> findByMonth(@Param("time") String time);
    List<Bill> findByConfirmAndStatus(Boolean confirm, Boolean status);
    List<Bill> findByStatus(Boolean status);
    @Query(value = "SELECT SUM(TotalPrice) FROM Bill WHERE FORMAT(Time, 'MM/yyyy') = :time AND Status = 1", nativeQuery = true)
    Integer findSumTotalPriceByMonth(@Param("time") String time);
    List<Bill> findByUsername(String username);
}
