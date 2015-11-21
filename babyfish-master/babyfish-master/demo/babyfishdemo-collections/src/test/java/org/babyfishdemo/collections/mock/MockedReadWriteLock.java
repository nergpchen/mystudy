package org.babyfishdemo.collections.mock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

import org.babyfish.lang.Arguments;

/**
 * @author Tao Chen
 */
public class MockedReadWriteLock implements ReadWriteLock {
    
    private String name;
    
    private StringBuilder builder;
    
    public MockedReadWriteLock(String name, StringBuilder builder) {
        this.name = Arguments.mustNotBeEmpty("name", Arguments.mustNotBeNull("name", name));
        this.builder = Arguments.mustNotBeNull("builder", builder);
    }

    @Override
    public Lock readLock() {
        return new AbstractLockImpl() {
            @Override
            public void lock() {
                MockedReadWriteLock that = MockedReadWriteLock.this;
                that.builder.append(that.name).append(".readLock().lock();");
            }
            @Override
            public void unlock() {
                MockedReadWriteLock that = MockedReadWriteLock.this;
                that.builder.append(that.name).append(".readLock().unlock();");
            }
        };
    }

    @Override
    public Lock writeLock() {
        return new AbstractLockImpl() {
            @Override
            public void lock() {
                MockedReadWriteLock that = MockedReadWriteLock.this;
                that.builder.append(that.name).append(".writeLock().lock();");
            }
            @Override
            public void unlock() {
                MockedReadWriteLock that = MockedReadWriteLock.this;
                that.builder.append(that.name).append(".writeLock().unlock();");
            }
        };
    }

    private static abstract class AbstractLockImpl implements Lock {

        @Override
        public void lockInterruptibly() throws InterruptedException {
            throw new UnsupportedOperationException("Mocked Lock does not supported this method");
        }

        @Override
        public boolean tryLock() {
            throw new UnsupportedOperationException("Mocked Lock does not supported this method");
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            throw new UnsupportedOperationException("Mocked Lock does not supported this method");
        }

        @Override
        public Condition newCondition() {
            throw new UnsupportedOperationException("Mocked Lock does not supported this method");
        }
    }
}
