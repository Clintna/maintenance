package com.bhhyy.aircraft.maintenance.common.transaction;

@FunctionalInterface
public interface TransactionExecutor {
    /**
     * 执行具体方法
     */
    void execute();
}