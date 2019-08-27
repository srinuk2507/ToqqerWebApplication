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
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Toq_Desktop 
{
	
	public ArrayList <String> filename = new ArrayList<String>();
	public	String randomFile = null;

	WebDriver driver;
	
	@Parameters({ "browser" })
	@Test(priority=1)
	public void launchBrowser( String browser) throws Exception 
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
	public void login(String username, String password) throws Exception
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
	public void javaRobot() throws Exception
	{
		Thread.sleep(3000);
		//random file pick and added to filename ArrayList
		indexDataGet();		
		StringSelection stringSelection = new StringSelection("F:\\Download\\Video\\Linkedin\\"+randomFile);
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
	
			/* This below method uses to select Random file from folder */
	private void indexDataGet()
	{
		File[] files = new File("F:\\Download\\Video\\Linkedin").listFiles();
		//System.out.println("print the files in specified location: "+files.toString().trim());	
		for (int i = 0; i < files.length; i++) 
		{			
			for (File f : files) 
				{
					if (!f.getName().isEmpty())
					filename.add(f.getName());
				}
		}
		Random random = new Random();
		int randomIndex = random.nextInt(filename.size());
		System.out.println("print file index number "+randomIndex);
		randomFile = filename.get(randomIndex);
		System.out.println("print file name "+randomFile);
	}
			/* Method Ends after selecting Random file from folder */    
	
	@Test(priority=5)
	public void selectCategory() throws Exception
	{
		Random random = new Random();
		int index = random.nextInt(15);
		System.out.println("Index is "+index);
		WebElement listBox = driver.findElement(By.xpath("//select[@id='vidcat']"));
		Select list = new Select(listBox);
		list.selectByIndex(index);
	}
									
	@Test(priority=6)
	public void vidDetails() throws Exception 
	{
		Thread.sleep(5000);
		String vidTitle = null;
		vidTitle = randomFile.replaceAll("\\.\\w+", "");
		System.out.println("video Title Print: "+vidTitle);
		driver.findElement(By.xpath("//input[@id='video_title']")).sendKeys(vidTitle);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='video_tag']")).sendKeys("Description"+vidTitle);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='video_desc']")).sendKeys(vidTitle);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		Thread.sleep(2000);
		try
			{	
				driver.switchTo().alert().accept();
				System.out.println("Submit button has displayed");
				Thread.sleep(3000);
				driver.close();	
			}
		catch(Exception e)
			{
				System.out.println("Submit button not displayed");
			}
		WebElement dclick2 = driver.findElement(By.id("uploadDialogHide"));
		dclick2.click();
	}
	
	@Test(priority=7)
	public void viduploadcheck() throws Exception
	{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//i[@class='fa fa-video-camera']//span[contains(text(),'Videos')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'My Videos')]")).click();
	}
	
	@Test(priority=8)
	public void sikuli() throws Exception
	{
		/* Move Mouse to recently uploaded video or latest video */
		Thread.sleep(3000);
		Actions builder = new Actions(driver);  
		WebElement Element = driver.findElement(By.xpath("//div[@id='div0']//i[@class='fa fa-play']")); 
		builder.moveToElement(Element, 372, 153).click().build().perform();
		
		/* Wait until pause button display */
		Screen s = new Screen();
		s.exists("E:\\Toqqer-Desktop-Web\\sikuli images\\pause button.JPG");
		
		/* To close current active window */
		Thread.sleep(3000);
		Set<String> handlesSet = driver.getWindowHandles();
        List<String> handlesList = new ArrayList<String>(handlesSet);
        driver.switchTo().window(handlesList.get(1));
        driver.switchTo().window(handlesList.get(0));
	}
}
