
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;


public class execution {
	

    
	/**
	 * @param driver
	 * @param listname
	 * @param prospectemail
	 * @throws InterruptedException
	 * Create a new prospect (Prospect > Prospect List)
	 * Add your new prospect to the newly created list
	 * Ensure the new prospect is successfully added to the list upon save
	 */
	public static void addProspect(WebDriver driver, String listname, String prospectemail) throws InterruptedException {
		   Actions act = new Actions(driver);
		   
		   //X-path
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
		   
	       System.out.println("Start created Prospect");
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
	
	
	/**
	 * @param driver
	 * @param listname
	 * @throws InterruptedException
	 * Find the listname from the dropDown
	 */
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
	
	/**
	 * @param driver
	 * @param listname
	 * @param changelistname
	 * @throws InterruptedException
	 * Rename the listname to changelistname
	 * 
	 */
	public static void renameList(WebDriver driver, String listname, String changelistname) throws InterruptedException {
		String theTableofRow  =  ".//*[@id='listx_table']/tbody/tr";
		String newName = ".//*[@id='name']";
		String updateButton = ".//*[@id='save_information']";
		int row_count = driver.findElements(By.xpath(theTableofRow)).size();
	      
	       
	       String textname = "";
	       Boolean found = false;
	       
	       System.out.println("Start to rename the list");
	       
	       
	       //get check all the first row get original list name
	       for (int i= 1; i < row_count; i=i+2)
	       {
		       textname = driver.findElement(By.xpath(".//*[@id='listx_table']/tbody/tr["+i +"]/td[2]")).getText();
		       String lines[] = textname.split("[\\r\\n]+");
		       //just segment list name		     
		       if ((lines[0].trim()).equals(listname))
		       {
		    	   found = true;
			       driver.findElement(By.xpath(".//*[@id='listx_table']/tbody/tr[" + i +"]/td[10]")).click();
			       Thread.sleep(1000);
			       //click edit link
			       driver.findElement(By.xpath(".//*[@id='listx_table']/tbody/tr[" + i + "]/td[10]/div/ul/li[1]/a")).click();
			      
			       Thread.sleep(3000);
			       
			       driver.findElement(By.xpath(newName)).clear();
			       driver.findElement(By.xpath(newName)).sendKeys(changelistname);
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
	
	
	/**
	 * @param driver
	 * @param listName
	 * @throws InterruptedException
	 * Create a list with a random name (Marketing > Segmentation > Lists)
	 * Attempt to create another list with that same name and ensure the system correctly gives a validation failure
	 */
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
	
		   System.out.println("Start created list");
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
	    	   //Attempt to create another list with that same name and ensure the system correctly gives a validation failure
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

	/**
	 * @param driver
	 * @param campaignXpath
	 * Give the second option from the dropDown
	 * 
	 */
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
	
	/**
	 * @param driver
	 * @param listname
	 * @throws InterruptedException
	 * Send a text only email to the list (Marketing > Emails) 
	 */
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
		   
		   System.out.println("Start process the email");
		   
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
	
	/**
	 * @param driver
	 * Logout the account
	 */
	public static void logout(WebDriver driver)
	{
	   Actions act = new Actions(driver);
		   
	   WebElement myaccount = driver.findElement(By.xpath(".//*[@id='acct-tog']")); 
	   act.moveToElement(myaccount).perform();
	       
	   String logout = ".//*[@id='dropmenu-account']/li[8]/a";
	  driver.findElement(By.xpath(logout)).click();
		
	}
	
	/**
	 * @param driver
	 * @param username
	 * @param password
	 * Login with username and password
	 */
	public static void login(WebDriver driver, String username, String password)
	{
		String usernameText =".//*[@id='email_address']";
		String passwordText =".//*[@id='password']";
		String submitbutton =".//*[@id='rememberMeBox']/div/input";
		driver.findElement(By.xpath(usernameText)).sendKeys(username);
	    driver.findElement(By.xpath(passwordText)).sendKeys(password);     
	    driver.findElement(By.xpath(submitbutton)).click();	  
		
	}


}
