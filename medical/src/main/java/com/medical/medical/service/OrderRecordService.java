package com.medical.medical.service;


import com.medical.medical.dao.DoctorRecordDAO;
import com.medical.medical.dao.OrderRecordDAO;
import com.medical.medical.dao.RegisterRecordDAO;
import com.medical.medical.model.DoctorRecord;
import com.medical.medical.model.HostHolder;
import com.medical.medical.model.OrderRecord;
import com.medical.medical.model.RegisterRecord;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class OrderRecordService {

    @Autowired
    OrderRecordDAO orderRecordDAO;

    @Autowired
    HostHolder hostHolder;
    @Autowired
    RegisterRecordDAO registerRecordDAO;

    @Autowired
    DoctorRecordDAO doctorRecordDAO;


    public List<OrderRecord> getOrderRecordList(int userId){
        return orderRecordDAO.getUserOrderRecordList(userId);
    }

    public List<OrderRecord> getUOrderRecordList(int userId){
        return orderRecordDAO.getUserUOrderRecordList(userId);
    }

    public OrderRecord getOrderRecord(int userId,int departmentId,Date scheduleDate,String scheduleName)
    {
        return orderRecordDAO.getOrderRecord(userId,departmentId,scheduleDate,scheduleName);
    }

//增加挂号订单
    public int addOrderRecord(OrderRecord orderRecord){
        if (orderRecordDAO.getOrderRecord(hostHolder.getUser().getId(),orderRecord.getDepartmentId(),orderRecord.getScheduleDate(),orderRecord.getScheduleOrder())!=null){
            return 0;
        }
        RegisterRecord number = registerRecordDAO.getScheduleNumber(orderRecord.getScheduleDate(),orderRecord.getDepartmentId());
        if (orderRecord.getScheduleOrder().equals("morning")){
            number.setMorning(number.getMorning()-1);
        }else if (orderRecord.getScheduleOrder().equals("afternoon")){
            number.setAfternoon(number.getAfternoon()-1);
        }else if (orderRecord.getScheduleOrder().equals("night")){
            number.setNight(number.getNight()-1);
        }
        registerRecordDAO.updateScheduleNumber(number);
        return orderRecordDAO.addOrderRecord(orderRecord);
    }

    public int deleteOrderRecordList(String orderId){
        OrderRecord orderRecord = orderRecordDAO.getOrderByOrderId(orderId);

        RegisterRecord num = registerRecordDAO.getScheduleNumber(orderRecord.getScheduleDate(),orderRecord.getDepartmentId());
        if (orderRecord.getScheduleOrder().equals("morning")){
            num.setMorning(num.getMorning()+1);
        }else if (orderRecord.getScheduleOrder().equals("afternoon")){
            num.setAfternoon(num.getAfternoon()+1);
        }else if (orderRecord.getScheduleOrder().equals("night")){
            num.setNight(num.getNight()+1);
        }
        registerRecordDAO.updateScheduleNumber(num);

        return orderRecordDAO.deleteOrderRecordList(orderId);
    }
}
