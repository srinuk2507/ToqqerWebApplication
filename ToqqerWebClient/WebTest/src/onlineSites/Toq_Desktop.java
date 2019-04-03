package onlineSites;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Toq_Desktop 
{
	WebDriver driver;
	
	@Parameters({ "browser" })
	@Test(priority=1)
	public void LaunchBrowser( String browser) throws Exception 
	{	
		System.out.println("Running Browser" + browser);
		if (browser.equalsIgnoreCase("c")) 
		{
			System.setProperty("webdriver.chrome.driver", "E:\\Appium_Desktop\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} 
		else if (browser.equalsIgnoreCase("f")) 
		{
			System.setProperty("webdriver.gecko.driver", "E:\\Appium_Desktop\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} 
		else if (browser.equalsIgnoreCase("i")) 
		{
			System.setProperty("webdriver.ie.driver", "E:\\Appium_Desktop\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
			driver.manage().window().maximize();

			// URL Details
			driver.get("http://192.168.0.3:8080/");
	}
	
	@Parameters({"username", "password"})
	@Test(priority=2)
	public void Login(String username, String password) throws Exception
	{
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.findElement(By.id("userId")).sendKeys(username);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder='enter password']")).sendKeys(password);
		Thread.sleep(1000);
		driver.findElement(By.id("login_btn")).click();
	}
	
	@Test(priority=3)
	public void uploadvid() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Upload Video')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='choose_btn']")).click();
	}
	
	@Test(priority=4)
	public void JavaRobot() throws Exception
	{
		Thread.sleep(3000);
		
		StringSelection stringSelection = new StringSelection("F:\\Download\\Video\\Linkedin\\In Ice Cream Factory.mp4");
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		System.out.println("Message copied" + clipboard);
		clipboard.setContents(stringSelection, null);
		Robot robot = null;
		try 
		{
            robot = new Robot();
        } 
		catch (AWTException e) 
		{
            e.printStackTrace();
        }
		robot.delay(250);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(150);
        robot.keyRelease(KeyEvent.VK_ENTER);
	}
	                                 
	@Test(priority=5)
	public void SelectCategory() throws Exception
	{
		Random random = new Random();
		int index = random.nextInt(10); 
		System.out.println("Index is "+index);
		WebElement listBox = driver.findElement(By.xpath("//select[@id='vidcat']"));
		Select list = new Select(listBox);
		list.selectByIndex(index);
	}
										/* End of Select Category */
	@Test(priority=6)
	public boolean vidDetails() throws Exception 
	{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='video_title']")).sendKeys("In Ice Cream Factory");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='video_tag']")).sendKeys("");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='video_desc']")).sendKeys("123");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		Thread.sleep(2000);
		try
			{
				WebElement closebutton=driver.findElement(By.id("uploadDialogHide"));
				if(closebutton.isDisplayed())
					{
						driver.switchTo().alert(); 
						return true;
					}
				else
					{
						driver.findElement(By.id("uploadDialogHide")).click();
					}
				Thread.sleep(2000);
				return false;
			}	
			catch (NoAlertPresentException Ex) 
		    { 				
		        return false;
		    }	
	}
	@SuppressWarnings("unlikely-arg-type")
	@Test(priority=7)
	public void viduploadcheck() throws Exception
	{
		Thread.sleep(4000);
		driver.findElement(By.xpath("//i[@class='fa fa-video-camera']//span[contains(text(),'Videos')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'My Videos')]")).click();
		WebElement divtitle=driver.findElement(By.className("title-div"));
		Thread.sleep(2000);
		driver.close();
	}
}
