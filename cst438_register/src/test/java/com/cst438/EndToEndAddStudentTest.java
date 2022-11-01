package com.cst438;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.cst438.domain.Course;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;
import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;


public class EndToEndAddStudentTest {
	
	public static final String CHROME_DRIVER_FILE_LOCATION 
    = "/Users/sujihancock/Downloads/chromedriver";
	public static final String URL = "http://localhost:3000";
	public static final String TEST_USER_NAME = "Test Student";
	public static final String TEST_USER_EMAIL = "test123@csumb.edu";
	public static final int TEST_COURSE_ID = 12345;
	public static final int SLEEP_DURATION = 1000; // 1 second.
	
	
	
	@Test
	public void addStudent() throws Exception {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		WebDriver driver = new ChromeDriver();
	  
        // fetching and downloading the page
        driver.get(URL);
        Thread.sleep(SLEEP_DURATION);
        
     	// finds and clicks on the add student button
        WebElement we = driver.findElement(By.id("addSbutton"));
        we.click();
        Thread.sleep(SLEEP_DURATION);
        we = driver.findElement(By.name("name"));
        we.sendKeys(TEST_USER_NAME);
        we = driver.findElement(By.name("email"));
        we.sendKeys(TEST_USER_EMAIL);
        
        
        we = driver.findElement(By.id("Add"));
        we.click();
        Thread.sleep(SLEEP_DURATION);
        
        String toast_text_sucess = driver.findElement(By.cssSelector(".Toastify__toast-body div:nth-child(2)")).getText();
        assertEquals("Student successfully added", toast_text_sucess);
//        
//        String toast_text_error = driver.findElement(By.cssSelector(".Toastify__toast-body div:nth-child(2)")).getText();
//        assertEquals("Error when adding", toast_text_error);
//        driver.quit();
	}

}
