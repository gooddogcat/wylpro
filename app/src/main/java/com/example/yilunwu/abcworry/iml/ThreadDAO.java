package com.example.yilunwu.abcworry.iml;

/**
 * Created by yilunwu on 16/12/14.
 */

import com.example.yilunwu.abcworry.utils.ThreadInfo;

import java.util.List;

/**
 * 数据访问接口
 */
public interface ThreadDAO {
    //插入线程信息
    public void insertThread(ThreadInfo threadInfo);

    /**
     * 删除线程
      * @param url
     *
     */
    public void deleteThread(String url);

    /**
     * 更新线程下载进度
     * @param url
     * @param thread_id
     * @param finished
     */
    public void updateThread(String url,int thread_id,int finished);

    /**
     * 查询文件的线程信息
     * @param url
     * @return
     */
    public List<ThreadInfo> getThreads(String url);

    /**
     * 线程信息是否存在
     * @param url
     * @param thread_id
     * @return
     */
    public boolean isExists(String url,int thread_id);

}
