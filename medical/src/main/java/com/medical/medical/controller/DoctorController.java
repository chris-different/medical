package com.medical.medical.controller;


import com.medical.medical.dao.DepartmentDAO;
import com.medical.medical.dao.EmployeeDAO;
import com.medical.medical.model.Department;
import com.medical.medical.model.DoctorObject;
import com.medical.medical.model.Employee;
import com.medical.medical.service.DepartmentService;
import com.medical.medical.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    EmployeeService employeeService;




    @RequestMapping(path = {"/doctor"})
    public String doctor(){
        return "doctor";
    }

//    @RequestMapping(path = {"/get_doctor"})
//    @ResponseBody
//    public

    @RequestMapping(path = {"/get_doctor_list"})
    @ResponseBody
    public List<DoctorObject> getDoctorList(@RequestParam("department_name") String departmentName){
        return employeeService.selectEmployeeList(departmentName);
    }


}
