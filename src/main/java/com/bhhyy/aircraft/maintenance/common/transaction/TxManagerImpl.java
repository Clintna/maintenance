package com.bhhyy.aircraft.maintenance.common.transaction;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * 事务执行单元
 *
 * @author zhongchunyan
 */
@Component
public class TxManagerImpl implements TxManager {

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public void execute(TransactionExecutor executor) {
        executor.execute();
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public <R> R call(TransactionCallable<R> callable) {
        return callable.call();
    }
}