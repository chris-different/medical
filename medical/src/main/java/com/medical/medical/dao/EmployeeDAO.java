package com.medical.medical.dao;

import com.medical.medical.model.Department;
import com.medical.medical.model.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeDAO {

    String TABLE_NAME= " employee ";


    @Insert("insert into" + TABLE_NAME + " (name,password,salt,department_id,head_url) values (#{name},#{password},#{salt},#{departmentId})")
    int addEmployee(Employee employee);

    @Select("select * from " + TABLE_NAME + "where name=#{name}")
    Employee selectByName(String name);

    //根据id查询雇员
    @Select("select * from " + TABLE_NAME + "where id=#{id}")
    Employee selectById(int id);

    @Update("update" + TABLE_NAME + "set password=#{password} where name=#{name}")
    void updatePassword(Employee employee);


    @Select("select name from " + " department " + "where id=#{departmentId}")
    String selectDepartmentName(Employee employee);

    //查找科室下面所有人员
    @Select("select * from " + TABLE_NAME + "where department_id=#{id}")
    List<Employee> selectEmployeeList(Department department);

    @Select("select * from " + TABLE_NAME + "where department_id=#{id}")
    List<Employee> selectEmployeeListByDepartmentId(int id);


    @Delete("delete from "+TABLE_NAME+"where name=#{name}")
    void deleteUser(Employee employee);

}
