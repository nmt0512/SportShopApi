package com.nhom25.SportShop.service;

import com.nhom25.SportShop.dto.BillDetail;
import com.nhom25.SportShop.dto.RevenueDto;
import com.nhom25.SportShop.entity.Bill;
import com.nhom25.SportShop.entity.Cart;

import java.util.Date;
import java.util.List;

public interface BillService {
    List<BillDetail> findAllBill();
    List<BillDetail> findCancelledBill();
    List<BillDetail> findBillByDay(String day);
    List<BillDetail> findBillByMonth(String date);
    List<BillDetail> confirmBillById(List<Integer> listBillId);
    List<BillDetail> undoConfirmBillById(List<Integer> listBillId);
    List<BillDetail> cancelBillById(List<Integer> listBillId);
    List<BillDetail> findBillByConfirm(Boolean confirm);
    BillDetail saveBillFromCart(List<Cart> listCart);
    Integer getRevenueByMonth(String month);
    List<RevenueDto> getAllMonthTotalRevenue();
}
