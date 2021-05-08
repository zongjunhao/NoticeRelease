package com.zjh.notice;

import com.zjh.notice.kit.Utils;
import com.zjh.notice.mapper.NoticeMapper;
import com.zjh.notice.mapper.TodoMapper;
import com.zjh.notice.mapper.UserMapper;
import com.zjh.notice.model.*;
import com.zjh.notice.service.NoticeService;
import com.zjh.notice.service.impl.IndexServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class NoticeApplicationTests {
    @Autowired
    TodoMapper todoMapper;
    @Autowired
    NoticeMapper noticeMapper;
    @Autowired
    NoticeService noticeService;
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void findUserByRole() {
        List<RUserUnit> userList = userMapper.findUserByRole(2);
        if (userList.isEmpty()) {
            System.out.println("列表为空");
        } else {
            for (RUserUnit user : userList) {
                System.out.println(user.toString());
            }
        }
    }

    @Test
    public void addNotice() {
        String endTime = "2021-5-8";
        String[] labelIds = {"1", "2", "3"};
        String[] fileIds = {"1", "2", "3"};
        noticeService.addNotice(1, "插入测试标题2", "插入测试内容2", 2, endTime, labelIds, fileIds);
    }

    @Test
    public void findNoticeUser() {
        RNoticeUser rNoticeUser = noticeMapper.findNoticeUser(1, "1");
        System.out.println("rNoticeUser = " + rNoticeUser);
    }

    @Test
    public void findTodosByCondition() {
        List<Todo> todos = todoMapper.findExpiredTodos("1", 99);
        for (Todo todo : todos) {
            System.out.println(todo);
        }
    }

    @Test
    public void addReadRecord() {
        int result = noticeMapper.addReadRecord("3", "1");
        System.out.println("result = " + result);
    }

    @Test
    public void finishTodo() {
        todoMapper.finishTodo("1");
    }

    @Test
    public void findNoticeTest() {
        List<Notice> notices = noticeMapper.findNoticesByUser("1", 0);
        for (Notice notice : notices) {
            System.out.println(notice);
        }
        List<NoticeData> noticeDataList = getNoticeDetails(notices, "1");
        for (NoticeData noticeData : noticeDataList) {
            System.out.println(noticeData);
        }
    }

    private List<NoticeData> getNoticeDetails(List<Notice> notices, String userId) {
        List<NoticeData> noticeDataList = new ArrayList<>();
        for (Notice notice : notices) {
            List<String> labels = noticeMapper.findLabelsByNotice(notice.getNoticeId());
            RNoticeUser rNoticeUser = noticeMapper.findNoticeUser(notice.getNoticeId(), userId);
            NoticeData noticeData = new NoticeData(notice, labels, rNoticeUser);
            noticeDataList.add(noticeData);
        }
        return noticeDataList;
    }
}
