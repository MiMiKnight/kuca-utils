package com.github.mimiknight.kuca.common.utils.impl;

import com.github.mimiknight.kuca.common.utils.standard.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * 线程工具类实现类
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-05-07 17:43:33
 */
@Component
public class ThreadServiceImpl implements ThreadService {
    private Executor executor;

    @Autowired
    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void async(Runnable command) {
        executor.execute(command);
    }
}
