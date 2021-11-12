/**
 * 
 */
package pageObjects;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * @author skumar
 *
 */
public class Amazon {

	WebDriver driver; // Local driver for this class

	public Amazon(WebDriver driver) {
		this.driver = driver; // WebDriver is assigned to the local driver
		PageFactory.initElements(driver, this); // Invoke the objects of this class
	}

	@FindBy(id = "nav-link-accountList")
	WebElement navigationlink;
	@FindBy(id = "ap_email")
	WebElement app_email;
	@FindBy(id = "ap_password")
	WebElement app_password;
	@FindBy(id = "continue")
	WebElement continueBtn;
	@FindBy(id = "signInSubmit")
	WebElement submitBtn;
	@FindBy(xpath = "//body/div[@id='a-page']/div[1]/div[1]/div[1]/a[1]/i[1]")
	WebElement formLogo;
	@FindBy(css = "#nav-link-accountList-nav-line-1")
	WebElement loggeduserName;
	@FindBy(xpath = "//span[contains(text(),'Sign Out')]")
	WebElement signOut;
	@FindBy(id = "createAccountSubmit")
	WebElement createAccountbtn;
	@FindBy(id = "ap_customer_name")
	WebElement username;
	@FindBy(id = "ap_password_check")
	WebElement confirmPasswd;
	@FindBy(id = "ap_phone_number")
	WebElement phoneNo;

	// Methods
	public void navigateToMenu() {
		navigationlink.isEnabled();
		navigationlink.click();
	}

	public void do_appLogin(String useremail, String password) {
		app_email.clear();
		app_email.sendKeys(useremail);
		continueBtn.isDisplayed();
		continueBtn.click();
		app_password.clear();
		app_password.sendKeys(password);
		submitBtn.isDisplayed();
		submitBtn.click();
		System.out.println("Login is successful for " + useremail);
	}

	public void NavtoSignUp() {
		createAccountbtn.click();
	}

	public void do_appSignup(String name, String phone, String email, String password) {
		username.clear();
		username.sendKeys(name);
		app_email.clear();
		app_email.sendKeys(email);
		try {
			phoneNo.clear();
			phoneNo.sendKeys(phone);
		} catch (Exception e) {
			System.out.println("phone no. field is not present on the signup form");
		}
		app_password.sendKeys(password);
		continueBtn.click();
	}

	public void verifyPageTitle(String expectedTitle) {
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	public void verifyFormLogo() {
		assertTrue(formLogo.isDisplayed());
		System.out.println("Amazon Form logo is present.");
	}

	public void verifyLoggedUser(String ExpectedMsg) {
		String actualMsg = loggeduserName.getText();
		Assert.assertEquals(actualMsg, ExpectedMsg);
		System.out.println("Home page is verified for the user " + actualMsg);
	}

	public void do_appSignOut() {
		Actions mouse = new Actions(driver);
		WebElement ele = navigationlink;
		mouse.moveToElement(ele).build().perform();
		signOut.click();
	}

}
