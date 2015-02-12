import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AolMail {

	public static void main(String[] args) throws InterruptedException {

		createAolAcc();

	}

	private static int getRandomNum(int low, int high) {
		Random r = new Random();
		int rand = r.nextInt(high - low) + low;

		return rand;
	}
	
	
	private static void createAolAcc(){
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.aol.co.uk/");
		driver.findElement(By.linkText("Sign up")).click();

		try {
			Thread.sleep(7000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			byte[] imgArr = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.BYTES);
			BufferedImage imageScreen = ImageIO.read(new ByteArrayInputStream(
					imgArr));

			String fileName1 = "img/input1.png";
			ImageIO.write(imageScreen, "png", new File(fileName1));

			WebElement cap = driver.findElement(By.id("regImageCaptcha"));
			Dimension capDimension = cap.getSize();
			Point capLocation = cap.getLocation();

			System.out.println(capLocation.x + "\t" + capLocation.y + "\t"
					+ capDimension.width + "\t" + capDimension.height);

			BufferedImage imgCap = imageScreen.getSubimage(capLocation.x,
					capLocation.y, capDimension.width, capDimension.height);

			/*
			 * BufferedImage imgCap = imageScreen.getSubimage(650, 470, 300,
			 * 200);
			 */

			String fileName = "img/input.png";
			ImageIO.write(imgCap, "png", new File(fileName));
			String value = js_api.getCaptchaValue();

			driver.findElement(By.id("wordVerify")).sendKeys(value);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		char[] alphabets = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z' };

		// get 10 char string
		RandomString randStr = new RandomString(5);
		String firstName = randStr.nextString();

		// replace numbers with word
		for (int ind = 0; ind < firstName.length(); ind++) {
			char ch = firstName.charAt(ind);
			// System.out.println((int)ch);
			if (ch >= 48 && ch <= 57) {
				firstName = firstName.replace(ch, alphabets[ind]);
			}

		}

		randStr = new RandomString(7);
		String lastName = randStr.nextString();
		// replace numbers with word
		for (int ind = 0; ind < lastName.length(); ind++) {
			char ch = lastName.charAt(ind);
			// System.out.println((int)ch);
			if (ch >= 48 && ch <= 57) {
				lastName = lastName.replace(ch, alphabets[ind]);
			}

		}

		// firstName
		driver.findElement(By.id("firstName")).sendKeys(firstName);

		// lastName
		driver.findElement(By.id("lastName")).sendKeys(lastName);

		randStr = new RandomString(16);
		String emailId = randStr.nextString();

		char ch = emailId.charAt(0);
		// System.out.println((int)ch);
		if (ch >= 48 && ch <= 57) {
			emailId = emailId.replace(ch, 'f');
		}

		driver.findElement(By.id("desiredSN")).sendKeys(emailId);

		// password
		String password = "Password@876W";
		driver.findElement(By.id("password")).sendKeys(password);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		driver.findElement(By.id("verifyPassword")).sendKeys(password);


		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// dob
		WebElement dobElement = driver.findElement(By.id("dobDay"));
		String dob = getRandomNum(5, 20) + "";
		dobElement.sendKeys(dob);

		// month
		// dobMonthSelectBoxItArrow
		driver.findElement(By.id("dobMonthSelectBoxItArrow")).click();
		driver.findElement(By.id("dobMonthSelectBoxIt")).sendKeys(Keys.DOWN);
		driver.findElement(By.id("dobMonthSelectBoxIt")).sendKeys(Keys.DOWN);
		driver.findElement(By.id("dobMonthSelectBoxIt")).sendKeys(Keys.DOWN);
		driver.findElement(By.id("dobMonthSelectBoxIt")).sendKeys(Keys.ENTER);

		driver.findElement(By.id("genderSelectBoxIt")).sendKeys(Keys.ENTER);

		int year = getRandomNum(1960, 1985);
		driver.findElement(By.id("dobYear")).sendKeys(year + "");

		// gender
		driver.findElement(By.id("genderSelectBoxItArrow")).click();

		int rand = getRandomNum(2, 8);

		if (rand % 2 == 0) {
			driver.findElement(By.id("genderSelectBoxIt")).sendKeys(Keys.DOWN);
		}

		else {
			driver.findElement(By.id("genderSelectBoxIt")).sendKeys(Keys.DOWN);
			driver.findElement(By.id("genderSelectBoxIt")).sendKeys(Keys.DOWN);
		}
		driver.findElement(By.id("genderSelectBoxIt")).sendKeys(Keys.ENTER);

		// zipCode
		int zipMidNo = getRandomNum(15, 90);
		int rnd1 = getRandomNum(3, 10);
		int rnd2 = getRandomNum(16, 23);
		String zipCode = (String) (new String(alphabets[rnd1] + "")
				+ new String(alphabets[rnd2] + "") + zipMidNo
				+ new String(alphabets[rnd1] + "") + new String(alphabets[rnd2]
				+ ""));// "kk23yy";
		driver.findElement(By.id("zipCode")).sendKeys(zipCode);

		// security question
		driver.findElement(By.id("acctSecurityQuestionSelectBoxItArrow"))
				.click();

		if (rand % 2 == 0) {
			driver.findElement(By.id("acctSecurityQuestionSelectBoxIt"))
					.sendKeys(Keys.DOWN);
		} else {
			driver.findElement(By.id("acctSecurityQuestionSelectBoxIt"))
					.sendKeys(Keys.DOWN);
			driver.findElement(By.id("acctSecurityQuestionSelectBoxIt"))
					.sendKeys(Keys.DOWN);
		}

		driver.findElement(By.id("acctSecurityQuestionSelectBoxIt")).sendKeys(
				Keys.ENTER);

		// security answer
		driver.findElement(By.id("acctSecurityAnswer")).sendKeys(
				getRandomNum(2000, 5000) + "");

		// submit
		driver.findElement(By.id("signup-btn")).click();

		try {
			Thread.sleep(5000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (driver.findElement(By.id("dltoolbar")).isSelected()) {
			driver.findElement(By.id("dltoolbar")).click();
		}

		// press ok button
		driver.findElement(By.className("signin-btn")).click();

		driver.get("http://mail.aol.co.uk/");
		
		//login
		//this screen may come sometime
		try{
			
			driver.findElement(By.id("lgnId1")).sendKeys(emailId);;
			driver.findElement(By.id("pwdId1")).sendKeys(password);
			//submit
			driver.findElement(By.id("submitID")).click();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		
		
		

		// handle signature
		try {
			// WSModalPaneInner
			/*WebElement modalElement = driver.findElement(By
					.className("WSModalPaneInner"));*/
			
			WebElement modalElement = (new WebDriverWait(driver, 30))
					  .until(ExpectedConditions.presenceOfElementLocated(By.className("WSModalPaneInner")));
			
			
			try{
				WebElement startBtnHldr = modalElement.findElement(By.className("startBtnHolder"));
				startBtnHldr.findElement(By.className("btnLabel")).click();
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
			
			
			
			
			
			
			
			modalElement.findElement(By.tagName("img")).click();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// click on inboxNode
		driver.findElement(By.id("inboxNode")).click();
	}

}
