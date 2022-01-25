// This class is responsible to create a driver
package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;
	
	
// create ThreadLocal object
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	
	/*
	 * This method is used to initialize the driver
	 * This will return the driver */
	
	//public WebDriver init_driver(String browserName) {
	public WebDriver init_driver(Properties prop) {
			
		String browserName = prop.getProperty("browser");
		System.out.println("Browser name is " +browserName );
		
		highlight = prop.getProperty("highlight");
		//highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		
		if (browserName.equals("chrome")){
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		
		else if (browserName.equals("firefox")){
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		
		else if (browserName.equals("safari")){
			//WebDriverManager.firefoxdriver().setup();
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		
		else {
			System.out.println("Please pass the rught browser" + browserName);
		}
		//Level 1:
		//driver().manage().window().fullscreen();
		//driver().manage().deleteAllCookies();
		//driver().get(prop.getProperty("url"));
		//Level 2:
		getDrive().manage().window().fullscreen();
		getDrive().manage().deleteAllCookies();
		//getDrive().get(prop.getProperty("url"));
		
		// openUrl(prop.getProperty("url"));

				URL url;
				try {
					url = new URL(prop.getProperty("url"));
					openUrl(url);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

		
		return getDrive();
	}
	
	/*
	 * getdriver(): it will return a thread local copy of the webdriver
	 * */
	
	public static synchronized WebDriver getDrive() {
		return tlDriver.get();
	}
	
	
	/**
	 * this method is used to initialize the properties
	 * 
	 * @return this will return properties prop reference
	 */
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");// qa/dev/stage/uat

		if (envName == null) {
			System.out.println("Running on PROD env: ");
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Running on environment: " + envName);
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;

				default:
					System.out.println("please pass the right environment.....");
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		try {
			prop.load(ip);// ip means qa/dev/stage/uit
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	
	
	
	/**
	 * take screenshot
	 */

	public String getScreenshot() {
		File src = ((TakesScreenshot)getDrive()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * launch url
	 * 
	 * @param url
	 */
	public void openUrl(String url) {
		try {
			if (url == null) {
				throw new Exception("url is null");
			}
		} catch (Exception e) {

		}
		getDrive().get(url);
	}

	public void openUrl(URL url) {
		try {
			if (url == null) {
				throw new Exception("url is null");
			}
		} catch (Exception e) {

		}
		getDrive().navigate().to(url);
	}

	public void openUrl(String baseUrl, String path) {
		try {
			if (baseUrl == null) {
				throw new Exception("baseUrl is null");
			}
		} catch (Exception e) {

		}
		// http://amazon.com/accpage/users.html
		getDrive().get(baseUrl + "/" + path);
	}

	public void openUrl(String baseUrl, String path, String queryParam) {
		try {
			if (baseUrl == null) {
				throw new Exception("baseUrl is null");
			}
		} catch (Exception e) {

		}
		// http://amazon.com/accpage/users.html?route=account/login
		getDrive().get(baseUrl + "/" + path + "?" + queryParam);
	}
	
	
	

}
