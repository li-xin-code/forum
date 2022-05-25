package com.lixin.campusforum;

import com.lixin.campusforum.forum.model.form.TopicForm;
import com.lixin.campusforum.forum.service.TopicService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lixin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicServiceTest {

    @Autowired
    private TopicService topicService;

    @Test
    public void addTest() {
        TopicForm form = new TopicForm();
        String author = "6PKSv8XZ";
        form.setTitle("标题");
        form.setContent("正文");
        topicService.add(form, author);
    }
}
