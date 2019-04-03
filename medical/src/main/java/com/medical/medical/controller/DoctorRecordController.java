package com.medical.medical.controller;


import com.medical.medical.model.DoctorRecord;
import com.medical.medical.model.Employee;
import com.medical.medical.service.DoctorRecordService;
import com.medical.medical.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DoctorRecordController {
    @Autowired
    DoctorRecordService doctorRecordService;

    @Autowired
    EmployeeService employeeService;

//    @RequestMapping("/get_doctor_record")
//    @ResponseBody
//    public List<DoctorRecord> getDoctorRecordList(@RequestParam("department_id") int departmentId,
//                                                  @RequestParam("schedule_date") Date scheduleDate,
//                                                  @RequestParam("schedule_name") String scheduleName){
//        return doctorRecordService.getDoctorRecordList(departmentId,scheduleDate,scheduleName);
//    }
    @RequestMapping("/add_doctor_record")
    @ResponseBody
    public int addDoctorRecord(@RequestParam("department_id") int departmentId,
                                          @RequestParam("schedule_date") Date scheduleDate,
                                          @RequestParam("schedule_name") String scheduleName){

        return 1;
    }
    @RequestMapping("/get_doctor_record")
    @ResponseBody
    public List<Employee> getDoctorRecordList(@RequestParam("department_id") int departmentId,
                                              @RequestParam("schedule_date") Date scheduleDate,
                                              @RequestParam("schedule_name") String scheduleName){
        List<DoctorRecord> doctorRecordList = doctorRecordService.getDoctorRecordList(departmentId,scheduleDate,scheduleName);
        List<Employee> employeeList = new ArrayList<>();
        for (DoctorRecord doctorRecord:doctorRecordList){
            Employee employee = employeeService.selectById(doctorRecord.getDoctorId());
            employeeList.add(employee);
        }
        return employeeList;
    }
}
