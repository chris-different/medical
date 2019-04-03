package com.medical.medical.dao;

import com.medical.medical.model.Department;
import com.medical.medical.model.Employee;
import com.medical.medical.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartmentDAO {

    String TABLE_NAME= " department ";


    @Insert("insert into" + TABLE_NAME + " (name) values (#{name})")
    int addDepartment(Department department);

    @Select("select * from " + TABLE_NAME + "where name=#{name}")
    Department selectByName(String name);

    @Select("select * from " + TABLE_NAME + "where id=#{id}")
    Department selectById(int id);

    @Update("update" + TABLE_NAME + "set name=#{name} where id=#{id}")
    void updateDepartment(Department department);

    @Delete("delete from "+TABLE_NAME+"where name=#{name}")
    void deleteUser(Department department);

    @Select("select * from "+ " employee "+"where department_id=#{id}")
    List<Employee> selectChildren(Department department);

    @Select("select * from "+ " employee "+"where department_id=#{id}")
    List<Employee> selectChildrenById(int departmentId);


    @Select("select * from " + " department " + "limit 0,3")
    List<Department> selectIndexDepartment();

    @Select("select id from " +TABLE_NAME)
    List<Integer> selectAllDepartmentId();
}
