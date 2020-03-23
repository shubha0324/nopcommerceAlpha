package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.nopcommerce.pageObjects.Loginpage;
import com.nopcommerce.testBase.BaseClass;
//my first test case//
public class TC_LoginTest_001 extends BaseClass
{
	
	@Test
	public void loginTest() throws IOException
	{
		logger.info("**************** Starting TC_LoginTest_001 ************* ");
		
		driver.get(configPropObj.getProperty("baseURL"));
		Loginpage lp=new Loginpage(driver);
		
		logger.info("**************** Providing login details ************* ");
		lp.setUserName(configPropObj.getProperty("useremail"));
		lp.setPassword(configPropObj.getProperty("password"));
		lp.clickLogin();
		
		String exp_title="Dashboard / nopCommerce administration";
		String act_title=driver.getTitle();
		
		if(exp_title.equals(act_title))
		{
			logger.info("**************** loginTest is Passed ************* ");
			Assert.assertTrue(true);
		}
		else
		{
			logger.warn("**************** loginTest is Failed************* ");
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);

		}
		logger.info("**************** Finished TC_LoginTest_001 ************* ");
		
	}
	

}