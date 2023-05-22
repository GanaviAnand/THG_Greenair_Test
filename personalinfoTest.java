import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.Assertion;
import org.testng.annotations.Test;

public class PersonalinfoTest extends Basetest {


//verifying loginURL
@Test
public void Logintest(){
	String ExpectedURL= "https://thg.greenair.dev.wgv.onpier.de/onboarding";
	String actualURL=driver.getCurrentUrl();
	Assert.assertEquals(actualURL,ExpectedURL);
}

//Test verifies all fields can take values.(click/edit/submit) are tested
@Test
public void PersonalDatafeildtest() {
try {
{
	//Radio button private person
driver.findElement(By.xpath("//input[@id="mat-radio-2-input"])").click();

//drop down of salutation
Select salutation=new Select(driver.findElement(By.id(“id="mat-select-value-1”)));
salutation.selectByVisibleText(“Frau”);
boolean salutationbox=salutation.isdisplayed();
driver.findElement(By.xpath("//input [@placeholder="Max"]")).sendKeys("Ganavi");// firstname
driver.findElement(By.xpath("//input [@placeholder="Mustermann"]")).sendKeys("Anandappa");//lastname
driver.findElement(By.xpath("//input [@placeholder="max.mustermann@muster.de"]")).sendKeys("gana@gmail.com");//email
driver.findElement(By.xpath("//input [@placeholder="Max Mustermann"]")).sendKeys("Ganavi Anandappa"); // ownername
driver.findElement(By.xpath("//input [@placeholder="z.B. DE45 7890 8965 5643 3454 00"]")).sendKeys("DE45 7890 8965 5643 3454 05"); //IBAN no
Thread.sleep(1000);
//clear the email field
//driver.findElement(By.id(“email_1”)).clear();

//click on submit button
driver.findElement(By.xpath("//div [contains(text(),"Weiter")]")).click();
}
} catch (InterruptedException e) {
e.printStackTrace();
}
}

//testing by providing empty input to email.
@Test
public void emptyemailTest(){
	driver.findElement(By.xpath("//input[@id="mat-radio-2-input"])").click();

//drop down of salutation
Select salutation=new Select(driver.findElement(By.id(“id="mat-select-value-1”)));
salutation.selectByVisibleText(“Frau”);
boolean salutationbox=salutation.isdisplayed();
driver.findElement(By.xpath("//input [@placeholder="Max"]")).sendKeys("Ganavi");// firstname
driver.findElement(By.xpath("//input [@placeholder="Mustermann"]")).sendKeys("Anandappa");//lastname
driver.findElement(By.xpath("//input [@placeholder="max.mustermann@muster.de"]")).sendKeys("");//email
driver.findElement(By.xpath("//input [@placeholder="Max Mustermann"]")).sendKeys("Ganavi Anandappa"); // ownername
driver.findElement(By.xpath("//input [@placeholder="z.B. DE45 7890 8965 5643 3454 00"]")).sendKeys("DE45 7890 8965 5643 3454 05"); //IBAN no
Thread.sleep(1000);
String expectedErrmsg="please enter a valid email address";
WebElement exp=driver.findElement(By.xpath("//input [@placeholder="max.mustermann@muster.de"]"));
String actualErrmsg=exp.getText();
Assert.assertEquals(actualErrmsg,expectedErrmsg);
}

//testing by providing invalid input to IBAN number.
@Test
public void invalidIBANTest(){
	driver.findElement(By.xpath("//input[@id="mat-radio-2-input"])").click();
//drop down of salutation
Select salutation=new Select(driver.findElement(By.id(“id="mat-select-value-1”)));
salutation.selectByVisibleText(“Frau”);
driver.findElement(By.xpath("//input [@placeholder="Max"]")).sendKeys("Ganavi");// firstname
driver.findElement(By.xpath("//input [@placeholder="Mustermann"]")).sendKeys("Anandappa");//lastname
driver.findElement(By.xpath("//input [@placeholder="max.mustermann@muster.de"]")).sendKeys("gana@gmail.com");//email
driver.findElement(By.xpath("//input [@placeholder="Max Mustermann"]")).sendKeys("Ganavi Anandappa"); // ownername
driver.findElement(By.xpath("//input [@placeholder="z.B. DE45 7890 8965 5643 3454 00"]")).sendKeys("DE45"); //invalid IBAN no
Thread.sleep(1000);
String expectedErrmsg="please enter a valid IBAN number";
WebElement exp=driver.findElement(By.xpath("//input [@placeholder="z.B. DE45 7890 8965 5643 3454 00"]")));
String actualErrmsg=exp.getText();
Assert.assertEquals(actualErrmsg,expectedErrmsg);
}
}



                                                
