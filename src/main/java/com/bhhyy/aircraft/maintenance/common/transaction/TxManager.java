package com.bhhyy.aircraft.maintenance.common.transaction;

/**
 * 事务执行器
 */
public interface TxManager {
    /**
     * 事务执行单位
     */
    <T> void execute(TransactionExecutor executor);

    /**
     * 事务执行, 需要返回结果
     *
     * @param callable
     * @param <R>
     * @return
     */
    <R> R call(TransactionCallable<R> callable);
}