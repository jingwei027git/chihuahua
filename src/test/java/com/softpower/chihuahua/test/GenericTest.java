package com.softpower.chihuahua.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-datasource.xml", "classpath:spring-context.xml" })
@TransactionConfiguration(transactionManager = "coreTransactionManager", defaultRollback = true)
@Transactional("coreTransactionManager")
@Ignore
public class GenericTest extends AbstractTest {

	protected Logger log = LoggerFactory.getLogger(getClass());

	static void jndiSetup() throws Exception {
//		SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
//		builder.bind("java:comp/env/jdbc/core",new SimpleDriverDataSource(new SQLServerDriver(),
//				"jdbc:sqlserver://localhost;database=russianblue", "sa", "admin") );
//		builder.activate();
	}

	@BeforeClass
	public static void beforeClass() throws Exception {
//		jndiSetup();
	}

	@AfterClass
	public static void afterClass() {
		// reserved
	}

}
