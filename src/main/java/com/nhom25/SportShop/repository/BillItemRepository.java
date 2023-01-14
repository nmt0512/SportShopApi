package com.nhom25.SportShop.repository;

import com.nhom25.SportShop.entity.BillItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillItemRepository extends JpaRepository<BillItem, Integer> {
    List<BillItem> findByBillId(Integer billId);
}
