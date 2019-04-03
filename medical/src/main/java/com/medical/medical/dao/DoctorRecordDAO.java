package com.medical.medical.dao;


import com.medical.medical.model.DoctorRecord;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

@Mapper
public interface DoctorRecordDAO {
    String TABLE_NAME = " doctor_record ";
    String INSERT_FIELDS = "doctor_id, department_id, schedule_date, morning, afternoon, night ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{doctorId},#{departmentId},#{scheduleDate},#{morning},#{afternoon},#{night})"})
    int add(DoctorRecord doctorRecord);

//    @Select({"select ",  "id,department_id,WEEKDAY(schedule_date) as real_schedule,date_format(schedule_Date, '%Y-%m-%d') as real_schedule_string, morning, afternoon, night" , " from ", TABLE_NAME, " where department_id=#{departmentId}"})
//    List<RegisterRecord> getRegisterRecordByDepartment(int departmentId);

    @Select({"select ", SELECT_FIELDS , "from ",TABLE_NAME, "where department_id=#{departmentId} and schedule_date=#{scheduleDate} and ${scheduleName}=1" })
    List<DoctorRecord> getDoctorRecordList(@Param("departmentId")int departmentId , @Param("scheduleDate") Date scheduleDate, @Param("scheduleName") String scheduleName);

    @Delete({"delete from ",TABLE_NAME,"where 1=1"})
    int deleteAllDoctorRecord();

//    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME,
//            " where entity_id=#{entityId} and entity_type=#{entityType} order by created_date desc"})
//    List<Comment> selectCommentByEntity(@Param("entityId") int entityId, @Param("entityType") int entityType);
//
//    @Select({"select count(id) from ", TABLE_NAME, " where entity_id=#{entityId} and entity_type=#{entityType}"})
//    int getCommentCount(@Param("entityId") int entityId, @Param("entityType") int entityType);

}
