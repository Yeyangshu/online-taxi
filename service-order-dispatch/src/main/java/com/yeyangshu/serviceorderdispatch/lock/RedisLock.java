package com.yeyangshu.serviceorderdispatch.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Redis锁，单例
 */
@Slf4j
public class RedisLock {

    @Autowired
    private RedisUtil RedisUtil;

    private static class LazyHolder {
        private static RedisLock ins = new RedisLock();
    }

    public static RedisLock ins() {
        RedisLock ins = LazyHolder.ins;
        return ins;
    }

    //自旋
    public void lock(String key) {
        int k = 0;
        for (; ; ) {
            boolean r = RedisUtil.setnx(key, "", 20);
            if (r) {
                return;
            }

            if (k++ >= 300) {
                throw new RuntimeException("lock error key = " + key);
            }

            try {
                TimeUnit.MILLISECONDS.sleep(10 + new Random().nextInt(20));
            } catch (InterruptedException e) {
                log.error("time out");
            }
        }
    }

    public void unlock(String key) {
        RedisUtil.delete(key);
    }
}