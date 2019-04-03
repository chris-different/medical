package com.medical.medical.service;


import com.medical.medical.dao.RegisterRecordDAO;
import com.medical.medical.model.RegisterRecord;
import com.medical.medical.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterRecordService {

    @Autowired
    RegisterRecordDAO registerRecordDAO;

    @Autowired
    DepartmentService departmentService;

    public List<RegisterRecord> getRegisterRecordList (int departmentId){
        return registerRecordDAO.getRegisterRecordByDepartment(departmentId);
    }
    public void autoAddRegisterRecord(){
        try{
            registerRecordDAO.deleteAll();
            addRegisterRecordList();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("数据已存在");
        }
    }


    public void addRegisterRecordList(){
//        Map<String,Integer> dic = new HashMap() {{
//            put("星期一",0);
//            put("星期二",1);
//            put("星期三",2);
//            put("星期四",3);
//            put("星期五",4);
//            put("星期六",5);
//            put("星期日",6);
//
//        }};
//        List<String> dateWeek = DateUtil.returnNextWeekDateString(new Date(),0);
//        List<String> dateWeekName = DateUtil.returnNextWeekName(new Date(),0);
        List<Date> dateWeekDate = DateUtil.returnNextWeekDate(new Date(),0);
        for(Integer departmentId :departmentService.getAllDepartmentId()){
            for (int i=0;i<7;i++){
                RegisterRecord registerRecord = new RegisterRecord();
                registerRecord.setDepartmentId(departmentId);
                registerRecord.setMorning(20);
                registerRecord.setAfternoon(20);
                registerRecord.setNight(20);
                java.sql.Date sqlDate = new java.sql.Date(dateWeekDate.get(i).getTime());
                registerRecord.setScheduleDate(sqlDate);
//                String newVar = dateWeekName.get(i).trim();
//                Integer var = dic.get(newVar);
//                registerRecord.setRealSchedule(var);
//                registerRecord.setRealScheduleString(dateWeek.get(i));
//                registerRecord.setRealScheduleWeekString(dateWeekName.get(i));
                registerRecordDAO.addRegisterRecord(registerRecord);

            }
        }
    }














}
