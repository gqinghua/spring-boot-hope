package com.data.hope.utils;

import com.data.hope.core.common.Application;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @program: report
 * @ClassName TransactionHelper
 * @description: [用一句话描述此类]
 * @author: GUO
 * @create: 2022-08-09 15:29
 **/
public class TransactionHelper {
    public static TransactionStatus getTransaction(int propagationBehavior, int isolationLevel) {
    PlatformTransactionManager transactionManager = Application.getBean(PlatformTransactionManager.class);
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setIsolationLevel(isolationLevel);
    def.setPropagationBehavior(propagationBehavior);
    return transactionManager.getTransaction(def);
}

    public static void commit(TransactionStatus transactionStatus) {
        Application.getBean(PlatformTransactionManager.class).commit(transactionStatus);
    }

    public static void rollback(TransactionStatus transactionStatus) {
        Application.getBean(PlatformTransactionManager.class).rollback(transactionStatus);
    }

}
