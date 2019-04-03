package com.medical.medical.dao;

import com.medical.medical.model.Department;
import com.medical.medical.model.DepartmentParent;
import com.medical.medical.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface DepartmentParentDAO {
    String TABLE_NAME= " department_parent ";


    @Insert("insert into" + TABLE_NAME + " (name) values (#{name})")
    int addDepartmentParent(DepartmentParent departmentParent);

    @Select("select * from " + TABLE_NAME + "where name=#{name}")
    User selectByName(String name);

    @Select("select * from " + TABLE_NAME + "where id=#{id}")
    User selectById(int id);

    @Update("update" + TABLE_NAME + "set name=#{name} where id=#{id}")
    void updateDepartmentParent(DepartmentParent departmentParent);

    @Delete("delete from "+TABLE_NAME+"where name=#{name}")
    void deleteDepartmentParent(DepartmentParent departmentParent);

    @Select("select * from " + TABLE_NAME )
    List<DepartmentParent> selectDepartmentParentList();

    @Select("select * from " + " department " + "where parent_id=#{id}")
    List<Department> selectChildren(DepartmentParent departmentParent);



}
