package com.zjh.notice.service.impl;

import com.zjh.notice.kit.ResponseData;
import com.zjh.notice.kit.ResultCodeEnum;
import com.zjh.notice.kit.Utils;
import com.zjh.notice.mapper.NoticeMapper;
import com.zjh.notice.mapper.TodoMapper;
import com.zjh.notice.model.*;
import com.zjh.notice.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zongjunhao
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    private final static Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);
    private final PlatformTransactionManager transactionManager;
    private final NoticeMapper noticeMapper;

    public NoticeServiceImpl(PlatformTransactionManager transactionManager, NoticeMapper noticeMapper) {
        this.transactionManager = transactionManager;
        this.noticeMapper = noticeMapper;
    }

    @Override
    public ResponseData getNoticesByConditions(String userId, int level, int isFinished) {
        ResponseData response = new ResponseData();
        try {
            List<Notice> notices = null;
            switch (isFinished) {
                case 0:
                    notices = noticeMapper.findUnfinishedNotices(userId, level);
                    break;
                case 1:
                    notices = noticeMapper.findFinishedNotices(userId, level);
                    break;
                case 2:
                    notices = noticeMapper.findNoticesByUser(userId, level);
                    break;
                case 3:
                    notices = noticeMapper.findExpiredNotices(userId, level);
                default:
            }
            assert notices != null;
            List<NoticeData> noticeDataList = getNoticeDetails(notices, userId);
            if (noticeDataList.isEmpty()) {
                response.setResult(ResultCodeEnum.DB_FIND_FAILURE);
            } else {
                response.setData(noticeDataList);
                response.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("get notice error", e);
            response.setResult(ResultCodeEnum.SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseData readNotice(String userId, String noticeId) {
        ResponseData response = new ResponseData();
        try {
            RNoticeUser rNoticeUser = noticeMapper.findNoticeUser(Long.parseLong(noticeId), userId);
            // 用户未读，表中无记录，向表中添加记录，有记录不做处理
            if (rNoticeUser == null) {
                int result = noticeMapper.addReadRecord(noticeId, userId);
                // 数据库修改失败
                if (result == 0) {
                    response.setResult(ResultCodeEnum.DB_UPDATE_ERROR);
                    return response;
                }
            }
            Notice notice = noticeMapper.getNotice(noticeId);
            List<String> labels = noticeMapper.findLabelsByNotice(Long.parseLong(noticeId));
            List<File> files = noticeMapper.findFilesByNotice(Long.parseLong(noticeId));
            String unitName = noticeMapper.getUnitName(notice.getNoticeUnitId());
            NoticeDetail noticeDetail = new NoticeDetail(notice, labels, files, unitName, rNoticeUser);
            response.setData(noticeDetail);
            response.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        } catch (Exception e) {
            logger.error("read notice error", e);
            response.setResult(ResultCodeEnum.SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseData finishNotice(String userId, String noticeId) {
        return null;
    }

    /**
     * 获得通知的标签和附件信息
     *
     * @param notices 通知列表
     * @return 详细通知信息
     */
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

    @Override
    public ResponseData getUnits(String userId) {
        ResponseData response = new ResponseData();
        try {
            List<Unit> unitList = noticeMapper.getUnits(userId);
            response.setData(unitList);
            response.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        } catch (Exception e) {
            logger.error("get units error", e);
            response.setResult(ResultCodeEnum.SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseData getLabels() {
        ResponseData response = new ResponseData();
        try {
            List<Label> labelList = noticeMapper.getLabels();
            response.setData(labelList);
            response.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        } catch (Exception e) {
            logger.error("get labels error", e);
            response.setResult(ResultCodeEnum.SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseData addNotice(long unitId, String title, String content, long level, String endTime, String[] labelIds, String[] fileIds) {
        // 创建事务
        TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        ResponseData response = new ResponseData();
        try {
            Timestamp endTimestamp = Utils.strToTimestamp(endTime);
            Notice notice = new Notice(title, content, unitId, level, endTimestamp);
            int result = noticeMapper.addNotice(notice);
            if (result != 1) {
                response.setResult(ResultCodeEnum.DB_UPDATE_ERROR);
                return response;
            }
            long noticeId = notice.getNoticeId();
            for (String labelId : labelIds) {
                noticeMapper.addNoticeLabel(noticeId, labelId);
            }
            for (String fileId: fileIds){
                noticeMapper.updateFile(noticeId, fileId);
            }
            response.setResult(ResultCodeEnum.DB_UPDATE_SUCCESS);
        } catch (Exception e) {
            // 异常回滚
            transactionManager.rollback(txStatus);
            logger.error("add notice error", e);
            response.setResult(ResultCodeEnum.SERVER_ERROR);
        }
        // 提交事务
        transactionManager.commit(txStatus);
        return response;
    }

    @Override
    public ResponseData addFile(String fileName, String filePath) {
        ResponseData response = new ResponseData();
        try {
            File file = new File(fileName, filePath);
            int result = noticeMapper.addNewFile(file);
            if (result == 1) {
                long fileId = file.getFileId();
                response.setData(fileId);
                response.setResult(ResultCodeEnum.FILE_UPLOAD_SUCCESS);
            } else {
                response.setResult(ResultCodeEnum.FILE_UPLOAD_FAILURE);
            }
        } catch (Exception e) {
            logger.error("add file error", e);
            response.setResult(ResultCodeEnum.SERVER_ERROR);
        }
        return response;
    }
}
