package com.lixin.campusforum;

import com.lixin.campusforum.model.form.CommentForm;
import com.lixin.campusforum.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lixin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void commentTest() {
        CommentForm form = new CommentForm();
        String author = "6PKSv8XZ";
        form.setContent("评论");
        form.setTopicId("XRTMuPwX");
        commentService.comment(form, author);
    }

    @Test
    public void listTest() {
        System.out.println(commentService.list("XRTMuPwX", 1));
    }
}
