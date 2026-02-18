package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.BasePage2;

public class ContactPage extends BasePage2 {

    // Constructor
    public ContactPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ================== Web Elements ==================

    @FindBy(xpath = "//input[@id='ContactForm-name']")
    private WebElement txtName;

    @FindBy(xpath = "//input[@id='ContactForm-email']")
    private WebElement txtEmail;

    @FindBy(xpath = "//input[@id='ContactForm-phone']")
    private WebElement txtPhone;

    @FindBy(xpath = "//textarea[@id='ContactForm-body']")
    private WebElement txtComment;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSubmit;

    // ================== Actions ==================

    public void enterName(String name) {
        type(txtName, name);
    }

    

	public void enterEmail(String email) {
        type(txtEmail, email);
    }

    public void enterPhone(String phone) {
        type(txtPhone, phone);
    }

    public void enterComment(String comment) {
        type(txtComment, comment);
    }

    public void clickSend() {
        click(btnSubmit);
    }

    

	public boolean isSendButtonDisplayed() {
        return isElementDisplayed(btnSubmit);
    }
}
