
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;




public class test {

	private final static String listName ="testNumbe13";
	
	private final static String changeListName ="testNumber13Rename";
	private final static String prospectemail ="testNumber13@gmail.com";
	
	private final static String username ="pardot.applicant@pardot.com";
	private final static String password ="Applicant2012";
	
	
	public static void main(String[] args) throws InterruptedException {
		
		 //Create a new instance of Firefox Browser
	       WebDriver driver = new FirefoxDriver();
	       //Open the URL in firefox browser	      
	       
	       driver.get("https://pi.pardot.com/");	
	       driver.manage().window().maximize();	
	        
	       execution.login(driver, username, password);	       
	       execution.createList(driver, listName);
	       execution.createList(driver, listName);
	       execution.renameList(driver, listName, changeListName);
	       Thread.sleep(5000);
	       execution.createList(driver, listName);
	       Thread.sleep(5000);
	       execution.addProspect(driver, listName, prospectemail);
	       Thread.sleep(5000);
	       execution.sendEmail(driver, listName);	
	       Thread.sleep(2000);
	       execution.logout(driver);
	       
	}
}
