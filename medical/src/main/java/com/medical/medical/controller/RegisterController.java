package com.medical.medical.controller;


import com.medical.medical.model.RegisterRecord;
import com.medical.medical.service.RegisterRecordService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.medical.medical.util.DateUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    RegisterRecordService registerRecordService;



    @RequestMapping(path = {"/register"})
    public String register(){
        return "register";
    }



    @RequestMapping(value = "/register_record/{rid}", method = {RequestMethod.GET})
    @ResponseBody
    public List<RegisterRecord> getRegisterRecordList(Model model, @PathVariable("rid") int rid) {
        return registerRecordService.getRegisterRecordList(rid);
    }

    @RequestMapping(path = {"/register_record"},method = {RequestMethod.POST})
    @ResponseBody
    public List<RegisterRecord> getRegisterRecordList(@RequestParam("department_id") int departmentId){
        List<RegisterRecord> registerRecordList = registerRecordService.getRegisterRecordList(departmentId);
        if (registerRecordList==null||registerRecordList.isEmpty()){
            Map<String,Integer> dic = new HashMap() {{
                put("星期一",0);
                put("星期二",1);
                put("星期三",2);
                put("星期四",3);
                put("星期五",4);
                put("星期六",5);
                put("星期日",6);

            }};

            List<String> dateWeek = DateUtil.returnNextWeekDateString(new Date(),0);
            List<String> dateWeekName = DateUtil.returnNextWeekName(new Date(),0);
            List<Date> dateWeekDate = DateUtil.returnNextWeekDate(new Date(),0);
            for (int i=0;i<7;i++){
                RegisterRecord registerRecord = new RegisterRecord();
                registerRecord.setDepartmentId(departmentId);
                registerRecord.setMorning(20);
                registerRecord.setAfternoon(20);
                registerRecord.setNight(02);
                java.sql.Date sqlDate = new java.sql.Date(dateWeekDate.get(i).getTime());
                registerRecord.setScheduleDate(sqlDate);
                String newVar = dateWeekName.get(i).trim();
                Integer var = dic.get(newVar);
                registerRecord.setRealSchedule(var);
                registerRecord.setRealScheduleString(dateWeek.get(i));
                registerRecord.setRealScheduleWeekString(dateWeekName.get(i));
                registerRecordList.add(registerRecord);
            }

            return registerRecordList;
        }else {
            return registerRecordList;
        }
    }
}
