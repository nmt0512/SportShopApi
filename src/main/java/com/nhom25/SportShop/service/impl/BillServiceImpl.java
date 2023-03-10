package com.nhom25.SportShop.service.impl;

import com.nhom25.SportShop.converter.BillConverter;
import com.nhom25.SportShop.dto.BillDetail;
import com.nhom25.SportShop.dto.RevenueDto;
import com.nhom25.SportShop.entity.Bill;
import com.nhom25.SportShop.entity.BillItem;
import com.nhom25.SportShop.entity.Cart;
import com.nhom25.SportShop.repository.BillItemRepository;
import com.nhom25.SportShop.repository.BillRepository;
import com.nhom25.SportShop.repository.RevenueRepository;
import com.nhom25.SportShop.security.UserDetailsServiceImpl;
import com.nhom25.SportShop.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepo;
    @Autowired
    private BillItemRepository billItemRepo;
    @Autowired
    private RevenueRepository revenueRepo;
    @Autowired
    private BillConverter converter;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public List<BillDetail> findAllBill() {
        return getResult(billRepo.findByStatus(true));
    }

    @Override
    public List<BillDetail> findCancelledBill() {
        return getResult(billRepo.findByStatus(false));
    }

    @Override
    public List<BillDetail> findBillByConfirm(Boolean confirm) {
        return getResult(billRepo.findByConfirmAndStatus(confirm, true));
    }

    @Override
    public List<BillDetail> findBillByDay(String day) {
        List<Bill> listBill = billRepo.findByDay(day);
        return getResult(listBill);
    }

    @Override
    public List<BillDetail> findBillByMonth(String date) {
        List<Bill> listBill = billRepo.findByMonth(date);
        return getResult(listBill);
    }

    @Override
    public List<BillDetail> confirmBillById(List<Integer> billIdList) {
        return handleBillById(billIdList, true, true);
    }

    @Override
    public List<BillDetail> undoConfirmBillById(List<Integer> billIdList) {
        return handleBillById(billIdList, true, false);
    }

    @Override
    public List<BillDetail> cancelBillById(List<Integer> billIdList) {
        return handleBillById(billIdList, false, false);
    }

    @Override
    public BillDetail saveBillFromCart(List<Cart> listCart) {
        //Create new Bill
        Bill bill = new Bill();
        bill.setUsername(listCart.get(0).getUsername());
        bill.setConfirm(false);
        bill.setStatus(true);
        bill.setDelivered(null);
        bill.setTime(new Timestamp(System.currentTimeMillis()));
        Integer totalPrice = 0;
        for (Cart cart : listCart) {
            totalPrice += cart.getPrice();
        }
        bill.setTotalPrice(totalPrice);
        bill = billRepo.save(bill);

        //Create listBillItem for created Bill
        List<BillItem> billItemList = new ArrayList<>();
        BillItem billItem = new BillItem();
        billItem.setBillId(bill.getId());
        for (Cart cart : listCart) {
            billItem.setItemId(cart.getItemId());
            billItem.setQuantity(cart.getQuantity());
            billItem.setPrice(cart.getPrice());
            billItemList.add(billItemRepo.save(billItem));
        }
        return converter.toBillDetail(bill, billItemList);
    }

    @Override
    public Integer getRevenueByMonth(String month) {
        return billRepo.findSumTotalPriceByMonth(month);
    }

    @Override
    public List<RevenueDto> getAllMonthTotalRevenue() {
        return revenueRepo.getAllMonthTotalRevenue();
    }

    @Override
    public List<BillDetail> findByCurrentUsername() {
        String username = userDetailsService.getCurrentUsername();
        return getResult(billRepo.findByUsername(username));
    }

    @Override
    public List<BillDetail> setDeliveredBillById(List<Integer> billIdList) {
        return handleBillById(billIdList, null, true);
    }

    @Override
    public List<BillDetail> undoDeliveredBillById(List<Integer> billIdList) {
        return handleBillById(billIdList, null, false);
    }

    @Override
    public List<BillDetail> getDeliveredBill() {
        return getResult(billRepo.findByDeliveredAndStatus(true, true));
    }

    @Override
    public List<BillDetail> getDeliveringBill() {
        return getResult(billRepo.findByDeliveredAndStatus(false, true));
    }

    @Override
    public BillDetail getBillById(Integer id) {
        Bill bill = billRepo.getById(id);
        List<BillItem> billItemList = billItemRepo.findByBillId(id);
        return converter.toBillDetail(bill, billItemList);
    }

    private List<BillDetail> getResult(List<Bill> billList) {
        List<BillDetail> result = new ArrayList<>();
        for (Bill bill : billList) {
            List<BillItem> billItemList = billItemRepo.findByBillId(bill.getId());
            result.add(converter.toBillDetail(bill, billItemList));
        }
        return result;
    }

    private List<BillDetail> handleBillById(List<Integer> billIdList, Boolean isConfirmBill, Boolean attribute) {
        // true: confirm
        // false: status
        // null: delivered
        List<Bill> listBill = new ArrayList<>();
        for (Integer id : billIdList) {
            Bill bill = billRepo.getById(id);
            if (isConfirmBill != null) {
                if (isConfirmBill) {
                    bill.setConfirm(attribute);
                    if (attribute)
                        bill.setDelivered(false);
                    else
                        bill.setDelivered(null);
                } else
                    bill.setStatus(attribute);
            } else
                bill.setDelivered(attribute);
            listBill.add(billRepo.save(bill));
        }
        return getResult(listBill);
    }
}
