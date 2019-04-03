package com.medical.medical.service;

import com.medical.medical.dao.DepartmentDAO;
import com.medical.medical.dao.DepartmentParentDAO;
import com.medical.medical.model.Department;
import com.medical.medical.model.DepartmentParent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DepartmentParentService {

    @Autowired
    DepartmentParentDAO departmentParentDAO;

    @Autowired
    DepartmentDAO departmentDAO;

    //得到总科室分类下所有科室
    public List<DepartmentParent> getParent(){
        return departmentParentDAO.selectDepartmentParentList();
    }
    public List<Department> getChildren(DepartmentParent departmentParent){
        return departmentParentDAO.selectChildren(departmentParent);
    }

    public Map<String,List<Department>> getParentChildren(){
        Map<String,List<Department>> map = new HashMap<String,List<Department>>();
        for (DepartmentParent departmentParent:getParent()){
//            map.put("name",departmentParent.getName());
//            map.put("list",getChildren(departmentParent));
            map.put(departmentParent.getName(),getChildren(departmentParent));
        }
        return map;
    }


}
