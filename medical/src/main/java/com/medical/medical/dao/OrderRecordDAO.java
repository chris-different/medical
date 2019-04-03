package com.medical.medical.dao;


import com.medical.medical.model.DoctorRecord;
import com.medical.medical.model.OrderRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.core.annotation.Order;

import java.sql.Date;
import java.util.List;
@Mapper
public interface OrderRecordDAO {
    String TABLE_NAME = " order_record ";
    String INSERT_FIELDS = "doctor_id, department_id,user_id,order_id,created_date, schedule_date, schedule_order,status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{doctorId},#{departmentId},#{userId},#{orderId},#{createdDate},#{scheduleDate},#{scheduleOrder},#{status})"})
    int addOrderRecord(OrderRecord orderRecord);

//    @Select({"select ",  "id,department_id,WEEKDAY(schedule_date) as real_schedule,date_format(schedule_Date, '%Y-%m-%d') as real_schedule_string, morning, afternoon, night" , " from ", TABLE_NAME, " where department_id=#{departmentId}"})
//    List<RegisterRecord> getRegisterRecordByDepartment(int departmentId);

    @Select({"select ", SELECT_FIELDS , "from ",TABLE_NAME, "where department_id=#{departmentId} and schedule_date=#{scheduleDate} and schedule_order=#{scheduleName} and user_id=#{userId}" })
    OrderRecord getOrderRecord(@Param("userId") int userId , @Param("departmentId")int departmentId , @Param("scheduleDate") Date scheduleDate , @Param("scheduleName") String scheduleName);

    @Select({"select ", SELECT_FIELDS , "from ",TABLE_NAME, "where user_id=#{userId}" })
    List<OrderRecord> getUserOrderRecordList(@Param("userId")int userId );

    @Select({"select ", SELECT_FIELDS , "from ",TABLE_NAME, "where user_id=#{userId} and status=0" })
    List<OrderRecord> getUserUOrderRecordList(@Param("userId")int userId );


    @Select({"select ", SELECT_FIELDS , "from ",TABLE_NAME, "where order_id=#{orderId}" })
    OrderRecord getOrderByOrderId(@Param("orderId")String orderId );

    @Delete({"delete from ",TABLE_NAME, " where order_id=#{orderId}"})
    int deleteOrderRecordList(@Param("orderId") String orderId);

//    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME,
//            " where entity_id=#{entityId} and entity_type=#{entityType} order by created_date desc"})
//    List<Comment> selectCommentByEntity(@Param("entityId") int entityId, @Param("entityType") int entityType);
//
//    @Select({"select count(id) from ", TABLE_NAME, " where entity_id=#{entityId} and entity_type=#{entityType}"})
//    int getCommentCount(@Param("entityId") int entityId, @Param("entityType") int entityType);

}
