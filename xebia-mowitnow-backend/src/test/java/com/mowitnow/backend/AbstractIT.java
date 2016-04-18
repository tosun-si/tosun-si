package com.mowitnow.backend;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Abstract superclass of integrations tests.
 * 
 * @author Mazlum TOSUN
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring/xebia-mowitnow-backend-context.xml" })
public abstract class AbstractIT {
}
