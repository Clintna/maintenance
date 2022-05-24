package com.bhhyy.aircraft.maintenance.common.transaction;

/**
 * 带返回结果的事务单元
 *
 * @author Jinglin
 */
@FunctionalInterface
public interface TransactionCallable<R> {
    /**
     * 事务执行, 返回结果
     *
     * @return
     */
    R call();
}
