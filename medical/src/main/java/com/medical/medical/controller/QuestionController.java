package com.medical.medical.controller;


import com.medical.medical.model.*;
import com.medical.medical.service.CommentService;
import com.medical.medical.service.LikeService;
import com.medical.medical.service.QuestionService;
import com.medical.medical.service.UserService;
import com.medical.medical.util.WendaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class QuestionController {
    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    QuestionService questionService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    @RequestMapping(value = "/question/{qid}", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> questionDetail(Model model, @PathVariable("qid") int qid) {
        Map<String,Object> map= new HashMap<String,Object>();
        Question question = questionService.getById(qid);
        map.put("question",question);
        List<Comment> commentList = commentService.getCommentsByEntity(qid, EntityType.ENTITY_QUESTION);
        List<CommentObject> commentObjectList = new ArrayList<CommentObject>();
//        List<ViewObject> comments = new ArrayList<ViewObject>();
//        for (Comment comment : commentList) {
//            ViewObject vo = new ViewObject();
//            vo.set("comment", comment);
//            if (hostHolder.getUser() == null) {
//                vo.set("liked", 0);
//            } else {
//                vo.set("liked", likeService.getLikeStatus(hostHolder.getUser().getId(), EntityType.ENTITY_COMMENT, comment.getId()));
//            }
//
//            vo.set("likeCount", likeService.getLikeCount(EntityType.ENTITY_COMMENT, comment.getId()));
//            vo.set("user", userService.getUser(comment.getUserId()));
//            comments.add(vo);
//        }
        for (Comment comment : commentList) {
            CommentObject commentObject = new CommentObject();
            commentObject.setContent(comment.getContent());
            commentObject.setCreatedDate(comment.getCreatedDate());
            commentObject.setEntityId(comment.getEntityId());
            commentObject.setEntityType(comment.getEntityType());
            commentObject.setId(comment.getId());
            commentObject.setUserId(comment.getUserId());
            commentObject.setStatus(comment.getStatus());
//            commentObject.setUserName(userService.getUser(comment.getUserId()).getUsername());
            if (hostHolder.getUser() == null) {
                commentObject.setLiked(0);
            } else {
                commentObject.setLiked(likeService.getLikeStatus(hostHolder.getUser().getId(), EntityType.ENTITY_COMMENT, comment.getId()));
            }
            commentObject.setLikeCount(likeService.getLikeCount(EntityType.ENTITY_COMMENT, comment.getId()));
            commentObject.setUser(userService.getUser(comment.getUserId()));
            commentObjectList.add(commentObject);
        }
        map.put("comments",commentObjectList);
        return map;
    }



    @RequestMapping(value = "/question/add", method = {RequestMethod.POST})
    @ResponseBody
    public String addQuestion(@RequestParam("title") String title, @RequestParam("content") String content) {
        try {
            Question question = new Question();
            question.setContent(content);
            question.setCreatedDate(new Date());
            question.setTitle(title);
            if (hostHolder.getUser() == null) {
                question.setUserId(WendaUtil.ANONYMOUS_USERID);
            } else {
                question.setUserId(hostHolder.getUser().getId());
            }
//            if (questionService.addQuestion(question) > 0) {
//                eventProducer.fireEvent(new EventModel(EventType.ADD_QUESTION)
//                        .setActorId(question.getUserId()).setEntityId(question.getId())
//                        .setExt("title", question.getTitle()).setExt("content", question.getContent()));
//                return WendaUtil.getJSONString(0);
//            }
            if (questionService.addQuestion(question) > 0){
                return WendaUtil.getJSONString(0);
            }
        } catch (Exception e) {
            logger.error("增加题目失败" + e.getMessage());
        }

        return WendaUtil.getJSONString(1,"失败");
    }

    private List<Question> getQuestions() {
        List<Question> questionList = questionService.getLatestQuestions();

        return questionList;
    }

    @RequestMapping(path = {"/get_questions"}, method = {RequestMethod.GET})
    @ResponseBody
    public List getLatestQuestion() {
        return getQuestions();
    }


    private List<Question> getQuestionsRe() {
        List<Question> questionList = questionService.getLatestQuestionsRe();

        return questionList;
    }
    @RequestMapping(path = {"/get_questions_re"}, method = {RequestMethod.GET})
    @ResponseBody
    public List getLatestQuestionRe() {
        return getQuestionsRe();
    }
}
