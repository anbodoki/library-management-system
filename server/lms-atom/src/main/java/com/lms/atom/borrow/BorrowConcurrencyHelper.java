package com.lms.atom.borrow;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class BorrowConcurrencyHelper {

    private final static Map<Long, ReentrantLock> locks = new ConcurrentHashMap<>();

    private static synchronized ReentrantLock getLock(Long resourceId) {
        if (!locks.containsKey(resourceId)) {
            locks.put(resourceId, new ReentrantLock());
        }
        return locks.get(resourceId);
    }

    public static void lock(Long resourceId) {
        ReentrantLock lock = getLock(resourceId);
        lock.lock();
    }

    public static void unlock(Long resourceId) {
        ReentrantLock lock = getLock(resourceId);
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }
}
