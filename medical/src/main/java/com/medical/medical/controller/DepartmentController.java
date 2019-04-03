package com.medical.medical.controller;

import com.medical.medical.model.Department;
import com.medical.medical.service.DepartmentParentService;
import com.medical.medical.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    DepartmentParentService departmentParentService;

    @RequestMapping(path = {"/department"})
    public String department(Model model){

        return "department";
    }


    @RequestMapping(path = {"/get_department_index"})
    @ResponseBody
    public List<Department> getDepartmentIndex(){
        return departmentService.selectIndexDepartment();
    }

    @RequestMapping(path = {"/get_department_parent_children"})
    @ResponseBody
    public Map<String,List<Department>> getParentChildren(){
        return departmentParentService.getParentChildren();
    }



}
