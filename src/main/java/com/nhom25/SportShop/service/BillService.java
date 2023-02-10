package com.nhom25.SportShop.service;

import com.nhom25.SportShop.dto.BillDetail;
import com.nhom25.SportShop.dto.RevenueDto;
import com.nhom25.SportShop.entity.Cart;

import java.util.List;

public interface BillService {
    List<BillDetail> findAllBill();
    List<BillDetail> findCancelledBill();
    List<BillDetail> findBillByDay(String day);
    List<BillDetail> findBillByMonth(String date);
    List<BillDetail> confirmBillById(List<Integer> billIdList);
    List<BillDetail> undoConfirmBillById(List<Integer> billIdList);
    List<BillDetail> cancelBillById(List<Integer> billIdList);
    List<BillDetail> findBillByConfirm(Boolean confirm);
    BillDetail saveBillFromCart(List<Cart> listCart);
    Integer getRevenueByMonth(String month);
    List<RevenueDto> getAllMonthTotalRevenue();
    List<BillDetail> findByCurrentUsername();
    List<BillDetail> setDeliveredBillById(List<Integer> billIdList);
    List<BillDetail> undoDeliveredBillById(List<Integer> billIdList);
    List<BillDetail> getDeliveredBill();
    List<BillDetail> getDeliveringBill();
    BillDetail getBillById(Integer id);
}
