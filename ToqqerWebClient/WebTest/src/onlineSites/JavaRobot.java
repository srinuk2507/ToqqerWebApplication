package onlineSites;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Test(priority=1)
public class JavaRobot 
{
	WebDriver driver;
	
  public void fileupload() throws Exception 
  {
	  System.setProperty("webdriver.chrome.driver", "E:\\Appium_Desktop\\Drivers\\chromedriver.exe");
	  driver=new ChromeDriver();
	  driver.get("file:///C:/Users/Srinivas%20K/OneDrive/Desktop/fileupload.html");
	  driver.manage().window().maximize();
	  driver.findElement(By.id("1")).click();
	  //File folder = new File("F:\\Download\\Video\\Linkedin");
	  StringSelection stringSelection = new StringSelection("F:\\Download\\Video\\Linkedin\\Dubai king.mp4");
	  Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
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
	  
      

      
//	  File folder = new File("F:\\Download\\Video\\Linkedin\\");
//	  File[] listOfFiles = folder.listFiles();
//	  for (int i = 0; i < listOfFiles.length; i++) 
//	  {
//	    if (listOfFiles[i].isFile()) 
//	    {
//	      System.out.println("File " + listOfFiles[i].getName());
//	    } 
//	    else if (listOfFiles[i].isDirectory()) 
//	    {
//	      System.out.println("Directory " + listOfFiles[i].getName());
//	    }
//	  }
	  driver.close();
}
}
