package com.medical.medical.service;

import com.medical.medical.dao.DepartmentDAO;
import com.medical.medical.model.Department;
import com.medical.medical.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentDAO departmentDAO;
//得到所有在该科室工作的员工
    public List<Employee> selectChildren(Department department){
        return departmentDAO.selectChildren(department);
    }

    public List<Employee> selectChildrenById(int departmentId){
        return departmentDAO.selectChildrenById(departmentId);
    }

    public List<Department> selectIndexDepartment(){
        return departmentDAO.selectIndexDepartment();
    }

    public List<Integer> getAllDepartmentId(){
        return departmentDAO.selectAllDepartmentId();
    }
}
