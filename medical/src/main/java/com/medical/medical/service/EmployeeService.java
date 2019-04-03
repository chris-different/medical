package com.medical.medical.service;


import com.medical.medical.dao.DepartmentDAO;
import com.medical.medical.dao.EmployeeDAO;
import com.medical.medical.model.Department;
import com.medical.medical.model.DoctorObject;
import com.medical.medical.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {


    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    DepartmentDAO departmentDAO;


    public String getDepartmentName(Employee employee){

        return employeeDAO.selectDepartmentName(employee);
    }

    //根据id获取单个医生
    public Employee selectById(int id){
        return employeeDAO.selectById(id);
    }

    public List<Employee> selectDoctorId(int departmentId){
        List<Employee> employeeList = employeeDAO.selectEmployeeListByDepartmentId(departmentId);
        return employeeList;
    }

    //获得医生名单
    public List<DoctorObject> selectEmployeeList(String departmentName){
        Department t_department = departmentDAO.selectByName(departmentName);
        List<Employee> employeeList = employeeDAO.selectEmployeeList(t_department);

        List<DoctorObject> doctorObjects = new ArrayList<DoctorObject>();
        for (Employee employee:employeeList){
            DoctorObject doctorObject = new DoctorObject();
            doctorObject.setId(employee.getId());
            doctorObject.setName(employee.getName());
            doctorObject.setDepartmentName(departmentName);
            doctorObjects.add(doctorObject);
        }
        return doctorObjects;
    }

}
