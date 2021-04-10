package com.zjh.notice;

import com.zjh.notice.mapper.NoticeMapper;
import com.zjh.notice.mapper.TodoMapper;
import com.zjh.notice.model.Notice;
import com.zjh.notice.model.Todo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class NoticeApplicationTests {

    @Autowired
    TodoMapper todoMapper;
    @Autowired
    NoticeMapper noticeMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void findNoticeTest() {
        List<Notice> notices = noticeMapper.findNoticeByUser("1");
        for (Notice notice : notices) {
            System.out.println(notice);
        }
    }
}
