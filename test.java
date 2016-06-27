import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;



public class test {

	private final static String listName ="testNumbeSeven";
	
	private final static String changeListName ="testNumberSevenRename";
	private final static String prospectemail ="testNumbert@gmail.com";
	
	public static void main(String[] args) throws InterruptedException {
		
		 //Create a new instance of Firefox Browser
	       WebDriver driver = new FirefoxDriver();
	       //Open the URL in firefox browser
	       driver.get("https://pi.pardot.com/");	       
	       driver.manage().window().maximize();	       
	       driver.findElement(By.xpath(".//*[@id='email_address']")).sendKeys("pardot.applicant@pardot.com");
	       driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("Applicant2012");     
	       driver.findElement(By.xpath(".//*[@id='rememberMeBox']/div/input")).click();	       
	       createList(driver, listName);
	       createList(driver, listName);
	       renameList(driver, listName, changeListName);
	       Thread.sleep(5000);
	       createList(driver, listName);
	       Thread.sleep(5000);
	       addProspect(driver, listName, prospectemail);
	       sendEmail(driver, listName);
	       Actions act = new Actions(driver);
	   
	       WebElement myaccount = driver.findElement(By.xpath(".//*[@id='acct-tog']")); 
	       act.moveToElement(myaccount).perform();
	       
	       String logout = ".//*[@id='dropmenu-account']/li[8]/a";
	       driver.findElement(By.xpath(logout)).click();	       
	       


	}
	
	
	public static void addProspect(WebDriver driver, String listname, String prospectemail) throws InterruptedException {
		   Actions act = new Actions(driver);
		   
		   String Prospect  = ".//*[@id='pro-tog']";
		   String ProspectList =".//*[@id='dropmenu-prospects']/li[1]/a";
		   String addProspect =".//*[@id='pr_link_create']";
		   String firstName =".//*[@id='default_field_3361']";
	       String lastName = ".//*[@id='default_field_3371']";
	       String email =".//*[@id='email'] ";
	       String score =".//*[@id='score']";
	       String campaign= ".//select[@id='campaign_id']/optgroup[@label='Current']/option";
	       String profile =".//*[@id='profile_id']/option";
	       String assign = ".//*[@id='to']/option";
	       String listbutton = ".//*[@id='toggle-inputs-lists-']";
	       String createProspect = ".//*[@id='pr_form_update']/form/div[21]/input";
		   
	       
	       WebElement prospect = driver.findElement(By.xpath(Prospect)); 
	       act.moveToElement(prospect).perform();
	       Thread.sleep(2000);
	       driver.findElement(By.xpath(ProspectList)).click();
	       
	       //add prospect	       
	       Thread.sleep(3000);
	       driver.findElement(By.xpath(addProspect)).click();	       
	       Thread.sleep(3000);	      
	       
	       driver.findElement(By.xpath(firstName)).sendKeys("Testguy");
	       driver.findElement(By.xpath(lastName)).sendKeys("Chen");
	       driver.findElement(By.xpath(email)).sendKeys(prospectemail); 
	       selectSecOption(driver, campaign);
	       selectSecOption(driver, profile);
	       selectSecOption(driver, assign);	       
	       driver.findElement(By.xpath(score)).clear();
	       driver.findElement(By.xpath(score)).sendKeys("100");
	       
	       //add list
	       driver.findElement(By.xpath(listbutton)).click();       
	       scrollDown(driver);	       
	       Thread.sleep(3000);
	       
	       selectListFromDropDown(driver, listname);driver.findElement(By.xpath(createProspect)).click();
	       System.out.println("Prospect was successfully created");
		
	}
	
	
	
	public static void selectListFromDropDown(WebDriver driver, String listname) throws InterruptedException {
		
		 String selectListAdd = ".//*[contains(@id,'sel')]/a/span";
		 String selectedList= ".//*[contains(@id,'sel')]/div/div/input";
	     
	       WebElement addlist = driver.findElement(By.xpath(selectListAdd));
	       System.out.println("The add list is: " + addlist.getText());
	       Thread.sleep(1000);
	       addlist.click();
	       Thread.sleep(3000);      
	       
	       driver.findElement(By.xpath(selectedList)).sendKeys(listname);
	       driver.findElement(By.xpath(selectedList)).sendKeys(Keys.RETURN);
	       System.out.println("list successfully selected and add");
		
		
	}
	public static void renameList(WebDriver driver, String listname, String changelistname) throws InterruptedException {
		String theTableofRow  =  ".//*[@id='listx_table']/tbody/tr";
		String newName = ".//*[@id='name']";
		String updateButton = ".//*[@id='save_information']";
		int row_count = driver.findElements(By.xpath(theTableofRow)).size();
	      
	       
	       String textname = "";
	       Boolean found = false;
	       
	       //get check all the first row get original list name
	       for (int i= 1; i < row_count; i=i+2)
	       {
		       textname = driver.findElement(By.xpath(".//*[@id='listx_table']/tbody/tr["+i +"]/td[2]")).getText();
		       String lines[] = textname.split("[\\r\\n]+");
		       //just segment list name		     
		       if ((lines[0].trim()).equals(listName))
		       {
		    	   found = true;
			       driver.findElement(By.xpath(".//*[@id='listx_table']/tbody/tr[" + i +"]/td[10]")).click();
			       Thread.sleep(1000);
			       //click edit link
			       driver.findElement(By.xpath(".//*[@id='listx_table']/tbody/tr[" + i + "]/td[10]/div/ul/li[1]/a")).click();
			      
			       Thread.sleep(3000);
			       
			       driver.findElement(By.xpath(newName)).clear();
			       driver.findElement(By.xpath(newName)).sendKeys(changeListName);
			       driver.findElement(By.xpath(updateButton)).click();
			       
			       break;
		       }
	       }
	       if (found)
	       {
	    	   System.out.println("Successfully rename");
	       }
	       else
	       {
	    	   System.out.println(listname + " is not exist");
	       }
		
	}
	public static void createList(WebDriver driver, String listName) throws InterruptedException
	{
		   Actions act = new Actions(driver);
		   String marketing = ".//*[@id='mark-tog']";
		   String segmentation = ".//*[@id='dropmenu-marketing']/li[11]/a";
		   String list =".//*[@id='dropmenu-marketing']/li[11]/ul/li[1]/a";
		   String addListbutton= ".//*[@id='listxistx_link_create']";
		   String addName =".//*[@id='name']";
		   String createList =".//*[@id='save_information']";
		   String errortext = ".//*[@id='error_for_name']";
		   String cancelbutton =".//*[@id='dynamicList-form']/div[3]/a";
	
	       WebElement webElement = driver.findElement(By.xpath(marketing)); 
	       act.moveToElement(webElement).perform();	       
	       webElement = driver.findElement(By.xpath(segmentation));	     
	       act.moveToElement(webElement).perform();     
	       
	     
	       Thread.sleep(1000);
	       driver.findElement(By.xpath(list)).click();	    
	       
	     
	       Thread.sleep(3000);
	       driver.findElement(By.xpath(addListbutton)).click();
	     
	       Thread.sleep(3000);
	      
	       driver.findElement(By.xpath(addName)).sendKeys(listName);
	       
	       //save List       
	       
	       driver.findElement(By.xpath(createList)).click();
	       
	       String errorMessage ="";
	       try {
		       WebElement  msg=driver.findElement(By.xpath(errortext));    
		       
		       errorMessage=msg.getText();		       
		       System.out.println("Error: " + errorMessage  );
		       //press cancel button
	    	   driver.findElement(By.xpath(cancelbutton)).click();
	    	   Thread.sleep(3000);
		       
	       }catch (Exception e){
	    	   System.out.println("List successfully created");
	       }		
		
	}

	public static void selectSecOption(WebDriver driver, String campaignXpath)
	{		
		List<WebElement> mySelect=driver.findElements(By.xpath(campaignXpath));
		mySelect.get(2).click();
	}
	
	public static void scrollDown(WebDriver driver)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 250)"); 
	}
	
	public static void sendEmail(WebDriver driver, String listname) throws InterruptedException {
		
		   String Market =".//*[@id='mark-tog']";
		   String emailLink = ".//*[@id='dropmenu-marketing']/li[7]/a";
		   String sendEmailbutton = ".//*[@id='em_module']/div[1]/span/a";
		   String name= ".//*[@id='name']";
		   String testEmailName ="testEmailthree";
		   String campaignChoose = ".//*[@id='information_form']/div[4]/div/div/button";
		   String selectedFirstCampaign = ".//*[@class='ember-view']/div/div/div/div/div";	
		   String chooseSelectCampaign = ".//*[@id='select-asset']";
		   String textradiobutton = ".//*[@id='email_type_text_only']";
		   String emailSave= ".//*[@id='save_information']";
		   String templatesSelect =".//*[@id='template_select_list']/div[2]/ul/li[1]";
		   String applybutton = ".//*[@id='template_confirm']";
		   String sendemail =".//*[@id='flow_sending']";
		   String saveCompleteEmail = ".//*[@id='save_footer']";
		   
		  
		   
		   Actions act = new Actions(driver);
		   WebElement marketing = driver.findElement(By.xpath(Market)); 
	       act.moveToElement(marketing).perform(); 
	       driver.findElement(By.xpath(emailLink)).click();	       
	       Thread.sleep(3000);
	       driver.findElement(By.xpath(sendEmailbutton)).click();
	       Thread.sleep(3000);	       
	       driver.findElement(By.xpath(name)).sendKeys(testEmailName);	
	       Thread.sleep(3000);
	       driver.findElement(By.xpath(campaignChoose)).click();
	       Thread.sleep(4000);
	       selectSecOption(driver, selectedFirstCampaign);
	       driver.findElement(By.xpath(selectedFirstCampaign)).click();
	       driver.findElement(By.xpath(chooseSelectCampaign)).click();
	       driver.findElement(By.xpath(textradiobutton)).click();
	       driver.findElement(By.xpath(emailSave)).click();
	       Thread.sleep(3000);
	       driver.findElement(By.xpath(templatesSelect)).click();
	       driver.findElement(By.xpath(applybutton)).click();
	       Thread.sleep(3000);
	       driver.findElement(By.xpath(sendemail)).click();Thread.sleep(3000);
	       selectListFromDropDown(driver, listname);      
	      
	       	       
	       driver.findElement(By.xpath(saveCompleteEmail)).click();	 
	       System.out.println("email successfully sent");
	}
}
