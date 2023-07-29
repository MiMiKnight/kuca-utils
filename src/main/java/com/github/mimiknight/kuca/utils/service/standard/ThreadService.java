package com.github.mimiknight.kuca.utils.service.standard;

/**
 * 多线程工具类
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-05-07 17:41:54
 */
public interface ThreadService {

    /**
     * 执行异步任务方法
     *
     * @param command Runnable接口实现类
     */
    void async(Runnable command);
}
