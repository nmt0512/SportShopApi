package com.nhom25.SportShop.service.impl;

import com.nhom25.SportShop.dto.BillDetail;
import com.nhom25.SportShop.entity.Bill;
import com.nhom25.SportShop.entity.BillItem;
import com.nhom25.SportShop.entity.Cart;
import com.nhom25.SportShop.repository.BillItemRepository;
import com.nhom25.SportShop.repository.BillRepository;
import com.nhom25.SportShop.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepo;
    @Autowired
    private BillItemRepository billItemRepo;

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
        try {
            List<Bill> listBill = billRepo.findByDay(parseDate(day));
            return getResult(listBill);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BillDetail> findBillByMonth(String date) {
        String[] dateStr = date.split("/");
        List<Bill> listBill = billRepo.findByMonth(dateStr[0], dateStr[1]);
        return getResult(listBill);
    }

    @Override
    public List<BillDetail> confirmBillById(List<Integer> listBillId) {
        return handleBillById(listBillId, true, true);
    }

    @Override
    public List<BillDetail> undoConfirmBillById(List<Integer> listBillId) {
        return handleBillById(listBillId, true, false);
    }

    @Override
    public List<BillDetail> cancelBillById(List<Integer> listBillId) {
        return handleBillById(listBillId, false, false);
    }

    @Override
    public BillDetail saveBillFromCart(List<Cart> listCart) {
        //Create new Bill
        Bill bill = new Bill();
//        bill.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        bill.setUsername(listCart.get(0).getUsername());
        bill.setConfirm(false);
        bill.setStatus(true);
        bill.setTime(new Timestamp(System.currentTimeMillis()));
        Integer totalPrice = 0;
        for (Cart cart : listCart) {
            totalPrice += cart.getPrice();
        }
        bill.setTotalPrice(totalPrice);
        bill = billRepo.save(bill);

        //Create listBillItem for created Bill
        List<BillItem> listBillItem = new ArrayList<>();
        BillItem billItem = new BillItem();
        billItem.setBillId(bill.getId());
        for (Cart cart : listCart) {
            billItem.setItemId(cart.getItemId());
            billItem.setQuantity(cart.getQuantity());
            billItem.setPrice(cart.getPrice());
            listBillItem.add(billItemRepo.save(billItem));
        }
        return new BillDetail(bill, listBillItem);
    }

    private List<BillDetail> getResult(List<Bill> listBill) {
        List<BillDetail> result = new ArrayList<>();
        BillDetail billDetail = new BillDetail();
        for (Bill bill : listBill) {
            billDetail.setBill(bill);
            billDetail.setListBillItem(billItemRepo.findByBillId(bill.getId()));
            result.add(billDetail);
        }
        return result;
    }

    private List<BillDetail> handleBillById(List<Integer> listBillId, Boolean isConfirmBill, Boolean attribute) {
        List<Bill> listBill = new ArrayList<>();
        for (Integer id : listBillId) {
            Bill bill = billRepo.getById(id);
            if (isConfirmBill)
                bill.setConfirm(attribute);
            else
                bill.setStatus(attribute);
            listBill.add(billRepo.save(bill));
        }
        return getResult(listBill);
    }

    private String parseDate(String date) throws ParseException {
        java.util.Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDay = dateFormat.format(sqlDate);
        return formattedDay;
    }
}
