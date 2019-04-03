package com.medical.medical.controller;


import com.medical.medical.model.Comment;
import com.medical.medical.model.EntityType;
import com.medical.medical.model.HostHolder;
import com.medical.medical.model.Question;
import com.medical.medical.service.CommentService;
import com.medical.medical.util.WendaUtil;
import com.sun.media.jfxmediaimpl.HostUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    CommentService commentService;


    @Autowired
    HostHolder hostHolder;

    @RequestMapping(value = "/comment/add", method = {RequestMethod.POST})
    @ResponseBody
    public String addComment(@RequestParam("entity_id") int entityId, @RequestParam("context") String content) {
        try {
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setCreatedDate(new Date());
            comment.setEntityId(entityId);
            comment.setEntityType(EntityType.ENTITY_QUESTION);
            comment.setStatus(1);
//            comment.setUserId(hostHolder.getUser().getId());

            if (hostHolder.getUser() != null) {
                comment.setUserId(hostHolder.getUser().getId());

            } else {
                comment.setUserId(WendaUtil.ANONYMOUS_USERID);

            }
            if (commentService.addComment(comment) > 0){
                return WendaUtil.getJSONString(0);
            }
        } catch (Exception e) {
            logger.error("增加题目失败" + e.getMessage());
        }

        return WendaUtil.getJSONString(1,"失败");
    }

}
