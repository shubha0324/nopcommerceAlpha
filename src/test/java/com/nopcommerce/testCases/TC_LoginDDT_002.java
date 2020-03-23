package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.Loginpage;
import com.nopcommerce.testBase.BaseClass;
import com.nopcommerce.utilities.XLUtils;


public class TC_LoginDDT_002 extends BaseClass
{
		
	@Test(dataProvider="LoginData")
	public void loginTest(String user,String pwd,String exp) throws IOException, InterruptedException
	{
		logger.info("**************** Starting TC_LoginDDT_002 ************* ");
		
		driver.get(configPropObj.getProperty("baseURL"));
		Loginpage lp=new Loginpage(driver);
		
		logger.info("**************** Proving login details ************* ");
		
		lp.setUserName(user);
		lp.setPassword(pwd);
		lp.clickLogin();
		Thread.sleep(5000);
		
		String exp_title="Dashboard / nopCommerce administration";
		String act_title=driver.getTitle();
		
		if(exp_title.equals(act_title))
		{
			if(exp.equals("Pass"))
			{
				logger.info("**************** loginTest is Passed ************* ");
				lp.clickLogout();
				Assert.assertTrue(true);
			}
			else if(exp.equals("Fail"))
			{
				logger.warn("**************** loginTest is Failed************* ");
				lp.clickLogout();
				Assert.assertTrue(false);
			}
					
		}
		else if(!exp_title.equals(act_title))
		{
			if(exp.equals("Pass"))
			{
				logger.warn("**************** loginTest is Failed************* ");
				Assert.assertTrue(false);
			}
			else if(exp.equals("Fail"))
			{
				logger.info("**************** loginTest is Passed ************* ");
				Assert.assertTrue(true);
			}

		}
		logger.info("**************** Finished TC_LoginTest_001 ************* ");
	}

	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/Testdata/LoginData.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "Sheet1");	
		int colcount=XLUtils.getCellCount(path,"Sheet1",1);
				
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{		
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]= XLUtils.getCellData(path, "Sheet1",i, j);  //1,0
			}
		}
	
		return logindata;
		
		
	}
	
	
}