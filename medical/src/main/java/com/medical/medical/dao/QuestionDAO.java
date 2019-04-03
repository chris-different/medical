package com.medical.medical.dao;

import com.medical.medical.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionDAO {
    String TABLE_NAME = " question ";
    String INSERT_FIELDS = " title, content, created_date, user_id, comment_count ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{title},#{content},#{createdDate},#{userId},#{commentCount})"})
    int addQuestion(Question question);

    @Select({"select", SELECT_FIELDS, " from ", TABLE_NAME, " order by created_date desc limit 0,10"})
    List<Question> selectLatestQuestions();


    @Select({"select", SELECT_FIELDS, " from ", TABLE_NAME, " order by comment_count desc limit 0,10"})
    List<Question> selectLatestQuestionsRe();
//    List<Question> selectLatestQuestions(@Param("userId") int userId, @Param("offset") int offset,
//                                         @Param("limit") int limit);
    @Select({"select ", "id ,title, content, created_date, date_format(created_date, '%Y-%m-%d') as created_date_string, user_id, comment_count ", " from ", TABLE_NAME, " where id=#{id}"})
    Question getById(int id);

    @Update({"update ", TABLE_NAME, " set comment_count = #{commentCount} where id=#{id}"})
    int updateCommentCount(@Param("id") int id, @Param("commentCount") int commentCount);

}
