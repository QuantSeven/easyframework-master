package com.easyframework.test.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext-*.xml"})//①加载Spring配置文件
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class BaseDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

}