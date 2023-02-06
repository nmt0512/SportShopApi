package com.nhom25.SportShop.controller;

import com.nhom25.SportShop.dto.BillDetail;
import com.nhom25.SportShop.dto.RevenueDto;
import com.nhom25.SportShop.response.ResponseData;
import com.nhom25.SportShop.response.ResponseUtils;
import com.nhom25.SportShop.service.BillService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/admin/bill")
public class AdminBillController {
    @Autowired
    private BillService billService;

    @ApiOperation(value = "Lấy thông tin tất cả các Bill")
    @GetMapping("/all")
    public List<BillDetail> getAllBill(@ApiParam(value = "Trạng thái xác nhận của Bill: Đã xác nhận(true), Chưa xác nhận(false)") @RequestParam(name = "param", required = false) Boolean confirm) {
        if(confirm != null)
            return billService.findBillByConfirm(confirm);
        return billService.findAllBill();
    }

    @ApiOperation(value = "Lấy thông tin các Bill đã bị hủy")
    @GetMapping("/cancelled")
    public List<BillDetail> getCancelledBill() {
        return billService.findCancelledBill();
    }

    @ApiOperation(value = "Lấy thông tin các Bill theo ngày")
    @GetMapping("/day")
    public List<BillDetail> getBillByDay(@ApiParam(value = "Ngày", defaultValue = "02/01/2023") @RequestParam(name = "param") String day) {
        return billService.findBillByDay(day);
    }

    @ApiOperation(value = "Lấy thông tin Bill theo tháng")
    @GetMapping("/month")
    public List<BillDetail> getBillByMonth(@ApiParam(value = "Tháng", defaultValue = "01/2023") @RequestParam(name = "param") String month)
    {
        return billService.findBillByMonth(month);
    }

    @ApiOperation(value = "Duyệt Bill")
    @PostMapping("/confirm")
    public List<BillDetail> confirmBill(@RequestBody List<Integer> listBillId)
    {
        return billService.confirmBillById(listBillId);
    }

    @ApiOperation(value = "Hủy duyệt Bill")
    @PostMapping("/unconfirm")
    public List<BillDetail> unconfirmBill(@RequestBody List<Integer> listBillId)
    {
        return billService.undoConfirmBillById(listBillId);
    }

    @ApiOperation(value = "Hủy Bill")
    @PostMapping("/cancel")
    public List<BillDetail> cancelBill(@RequestBody List<Integer> listBillId)
    {
        return billService.cancelBillById(listBillId);
    }

    @ApiOperation(value = "Xem doanh số theo tháng")
    @GetMapping("/revenue")
    public ResponseEntity getRevenueByMonth(@RequestParam("month") String month)
    {
        ResponseData<Integer> data = new ResponseData<>();
        data.setData(billService.getRevenueByMonth(month));
        return ResponseUtils.success(data.getData(), data.getCode(), data.getMessage());
    }

    @ApiOperation(value = "Xem doanh số tất cả các tháng")
    @GetMapping("/revenue/all")
    public ResponseEntity getAllMonthTotalRevenue()
    {
        ResponseData<List<RevenueDto>> data = new ResponseData<>();
        data.setData(billService.getAllMonthTotalRevenue());
        return ResponseUtils.success(data.getData(), data.getCode(), data.getMessage());
    }

}
