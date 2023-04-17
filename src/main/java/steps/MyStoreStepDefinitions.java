package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import java.time.Duration;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;

public class MyStoreStepDefinitions {


    WebDriver driver;
    WebDriverWait wait;
    String url = "https://mystore-testlab.coderslab.pl/";
    WebElement signInBtn;
    WebElement emailInput;
    WebElement passwordInput;

    WebElement aliasInput;
    WebElement cityInput;
    WebElement addressInput;
    WebElement postcodeInput;
    WebElement phoneNumberInput;
    WebElement submitBtn;


    @Given("Uzytkownik znajduje sie na stronie glownej aplikacji")
    public void userIsOnHomePage() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments(("--remote-allow-origins=*"));
        driver = new ChromeDriver(options);

       // if (driver != null) {
        //    System.out.println("ChromeDriver został poprawnie zainicjalizowany.");
       // } else {
       //     System.out.println("ChromeDriver nie został zainicjalizowany lub wystąpił błąd.");
        //}

        driver.manage().window().maximize();
        driver.get(url);
        Duration timeout = Duration.ofSeconds(10);
        wait = new WebDriverWait(driver, timeout);

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='hidden-sm-down'])[2]")));

    }
    @When("Uzytkownik wybiera opcje SignIn")
    public void goToSignInPage(){
        signInBtn=driver.findElement(By.xpath("(//span[@class='hidden-sm-down'])[2]"));
        signInBtn.click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SubmitLogin")));
        //ZROB ASSERT EQUALS czy widoczny jest element Log in to your account
    }

    @Then("Uzytkownik wpisuje poprawny adres email oraz haslo a nastepnie zatwierdza dane logowania")
    public void logIntoApp() {

        //emailInput = driver.findElement(By.cssSelector("input.form-control(1)"));
        emailInput = driver.findElement(By.cssSelector("input.form-control:nth-of-type(1)"));
        emailInput.clear();
        emailInput.sendKeys("qthlcmimmwmoaqzqfu@bbitf.com");

        //passwordInput = driver.findElement(By.cssSelector("input.form-control.js-child-focus.js-visible-password"));
        passwordInput = driver.findElement(By.cssSelector("input.form-control.js-child-focus.js-visible-password"));
        passwordInput.clear();
        passwordInput.sendKeys("Haslo1234");

        driver.findElement(By.id("submit-login")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Your account')]")));

        String actualTitle = driver.findElement(By.xpath("(//h1)[1]")).getText();
        String expectedTitle = "Your account";
        assertEquals(expectedTitle, actualTitle);
        //assertEquals("Your account", driver.findElement(By.className("account")).getText()); // drugi sposób na ta samą asercję
    }


    @And("Uzytkownik klika w kafelek Addresses a nastepnie klika w Create new address i zostaje przeniesiony do formularza z miejscami do wpisania danych")
    public void userClicksAddressBtn() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='Addresses']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Your addresses')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(., 'Create new address')]"))).click();
        assertEquals("Create new address", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(., 'Create new address')]"))).getText());
        //Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/section/div/div/section/header/h1"))).isDisplayed());

    }


    @Then("Uzytkownik uzupelnia pola {string}, {string}, {string}, {string}, {string} danymi oraz zatwierdza przyciskiem save")
    public void fillInAddress(String alias, String address, String city, String zipCode, String number) {

        aliasInput = driver.findElement(By.cssSelector("[id='field-alias']"));
        aliasInput.sendKeys(alias);
        addressInput = driver.findElement(By.cssSelector("[id='field-address1']"));
        addressInput.sendKeys(address);
        cityInput = driver.findElement(By.cssSelector("[id='field-city']"));
        cityInput.sendKeys(city);
        postcodeInput = driver.findElement(By.cssSelector("[id='field-postcode']"));
        postcodeInput.sendKeys(zipCode);
        phoneNumberInput = driver.findElement(By.cssSelector("[id='field-phone']"));
        phoneNumberInput.sendKeys(number);
        submitBtn = driver.findElement(By.cssSelector("button.form-control-submit"));
        submitBtn.submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Your addresses')]")));
        assertEquals("Your addresses", driver.findElement(By.xpath("//h1[contains(text(), 'Your addresses')]")).getText());
        driver.quit();
    }

}



