package com.medical.medical.dao;

import com.medical.medical.model.OrderRecord;
import com.medical.medical.model.RegisterRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.core.annotation.Order;

import java.sql.Date;
import java.util.List;


@Mapper
public interface RegisterRecordDAO {
    String TABLE_NAME = " register_record ";
    String INSERT_FIELDS = "department_id, schedule_date, morning, afternoon, night ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME,"( ", INSERT_FIELDS,") values (#{departmentId},#{scheduleDate},#{morning},#{afternoon},#{night})"})
    int addRegisterRecord(RegisterRecord registerRecord);

    @Update({"update ", TABLE_NAME, " set morning = #{morning},afternoon=#{afternoon},night=#{night} where department_id=#{departmentId} and schedule_date=#{scheduleDate}"})
    int updateScheduleNumber(RegisterRecord registerRecord);

    @Select({"select ","* from ", TABLE_NAME, " where schedule_date=#{scheduleDate} and department_id=#{departmentId}"})
    RegisterRecord getScheduleNumber(@Param("scheduleDate") Date scheduleDate,@Param("departmentId") int departmentId);

    @Select({"select ",  "id,department_id,WEEKDAY(schedule_date) as real_schedule,date_format(schedule_Date, '%Y-%m-%d') as real_schedule_string, morning, afternoon, night" , " from ", TABLE_NAME, " where department_id=#{departmentId}"})
    List<RegisterRecord> getRegisterRecordByDepartment(int departmentId);

    @Delete({"delete from ",TABLE_NAME," where 1=1"})
    int deleteAll();
//    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME,
//            " where entity_id=#{entityId} and entity_type=#{entityType} order by created_date desc"})
//    List<Comment> selectCommentByEntity(@Param("entityId") int entityId, @Param("entityType") int entityType);
//
//    @Select({"select count(id) from ", TABLE_NAME, " where entity_id=#{entityId} and entity_type=#{entityType}"})
//    int getCommentCount(@Param("entityId") int entityId, @Param("entityType") int entityType);

}
