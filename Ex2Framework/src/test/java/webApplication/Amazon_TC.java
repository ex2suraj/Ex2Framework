/**
 * 
 */
package webApplication;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.Amazon;
import resources.Base;

/**
 * @author skumar
 *
 */
public class Amazon_TC extends Base {
	
	@BeforeMethod
	public void setUp() throws Exception {
		String applicationUrl = prop.getProperty("AppUrl");
		driver = initDriver();
		driver.get(applicationUrl);
		System.out.println("Navigated to :"+applicationUrl);
		driver.manage().window().maximize();
	}
	
	@Test
	public void do_amazon_register() {
		String name = prop.getProperty("customername");
		String phone = prop.getProperty("phone");
		String signUpemail = prop.getProperty("signUpemail");
		String password = prop.getProperty("passwd");
		String regpageTitle = prop.getProperty("signUppageTitle");
		
		Amazon az1 = new Amazon(driver);
		az1.navigateToMenu();
		az1.NavtoSignUp();
		az1.verifyPageTitle(regpageTitle);
		az1.verifyFormLogo();
		az1.do_appSignup(name, phone , signUpemail, password);
		System.out.println("New User registeration form is filled successfully.");
		// Some time the phone number field appears and sometimes not in the form.
		// Can't automate further on --> since Visual Puzzle is appearing.
	}
	
	@Test
	public void do_amazon_login() {
		String sipageTitle = prop.getProperty("signInpageTitle");
		String email = prop.getProperty("email");
		String password = prop.getProperty("passwd");
		String SignedInUserMsg = prop.getProperty("expectedUserMsg");
		
		Amazon az2 = new Amazon(driver);
		az2.navigateToMenu();
		az2.verifyFormLogo();
		az2.verifyPageTitle(sipageTitle);
		az2.do_appLogin(email, password);
		az2.verifyLoggedUser(SignedInUserMsg);
		az2.do_appSignOut();
	}

	
	@AfterMethod
	public void tearDown() {
		driver.close();
		System.out.println("teardown --> browser is closed");
	}
}
