package com.medical.medical.controller;

import com.medical.medical.dao.DepartmentDAO;
import com.medical.medical.model.HostHolder;
import com.medical.medical.model.OrderRecord;
import com.medical.medical.service.OrderRecordService;
import com.medical.medical.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.List;
import java.util.Random;


@Controller
public class OrderController {

    @Autowired
    OrderRecordService orderRecordService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    DepartmentDAO departmentDAO;

    @RequestMapping(path = {"/get_order_list"}, method = {RequestMethod.POST})
    @ResponseBody
    public List getOrderRecordList(@RequestParam("user_id") int userId) {
        return orderRecordService.getOrderRecordList(userId);
    }

    @RequestMapping(path = {"/get_u_order_list"}, method = {RequestMethod.POST})
    @ResponseBody
    public List getUOrderRecordList(@RequestParam("user_id") int userId) {
        return orderRecordService.getUOrderRecordList(userId);
    }

    @RequestMapping(path = {"/delete_order"}, method = {RequestMethod.POST})
    @ResponseBody
    public int getOrderRecordList(@RequestParam("order_id") String orderId) {
        return orderRecordService.deleteOrderRecordList(orderId);
    }


    @RequestMapping(path = {"/get_order_record"}, method = {RequestMethod.POST})
    @ResponseBody
    public OrderRecord getOrderRecordList(@RequestParam("user_id") int userId,
                                          @RequestParam("department_id") int departmentId,
                                          @RequestParam("schedule_date") Date scheduleDate,
                                          @RequestParam("schedule_name") String scheduleName) {
        return orderRecordService.getOrderRecord(userId, departmentId, scheduleDate, scheduleName);
    }



    @RequestMapping(path = {"/add_order_record"}, method = {RequestMethod.POST})
    @ResponseBody
    public int addOrderRecord(@RequestParam("department_id") int departmentId,
                              @RequestParam("doctor_id") int doctorId,
                              @RequestParam("schedule_date") Date scheduleDate,
                              @RequestParam("schedule_order") String scheduleOrder) {

        OrderRecord orderRecord = new OrderRecord();
        orderRecord.setUserId(hostHolder.getUser().getId());
        orderRecord.setOrderId(OrderUtil.getOrderNum());

        orderRecord.setDepartmentId(departmentId);
        orderRecord.setDepartmentName(departmentDAO.selectById(departmentId).getName());
        orderRecord.setDoctorId(doctorId);
        orderRecord.setScheduleDate(scheduleDate);
        orderRecord.setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
        orderRecord.setScheduleOrder(scheduleOrder);
        orderRecord.setStatus(1);
        return orderRecordService.addOrderRecord(orderRecord);
    }
}