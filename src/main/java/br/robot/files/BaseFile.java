package br.robot.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import br.robot.BaseClass;
import br.robot.Browser;
import br.robot.Properties;
import br.robot.process.StepProcess;
import br.robot.process.SysConfig;

/**
 * Base file common process
 * @author Rafael Costi <rafaelcosti@outlook.com>
 * @version 1.0.0
 */
public abstract class BaseFile extends BaseClass {
	/**
	 * Web driver
	 */
	protected WebDriver webDriver;
	
	/**
	 * File input stream
	 */
	protected FileInputStream fis;
	
	/**
	 * System Configuration property
	 */
	protected SysConfig sysConfig;
	
	/**
	 * Steps process property
	 */
	protected Map<Integer, StepProcess> stepProcess;
	
	/**
	 * Constructor
	 * @throws FileNotFoundException 
	 */
	public BaseFile(File file) throws FileNotFoundException {
		super();
		this.fis = new FileInputStream(file);
		this.sysConfig = new SysConfig();
		this.stepProcess = new HashMap<Integer, StepProcess>();
	}

	/**
	 * Initialize properties
	 */
	protected void initProperties() {
		// Execute id
		ZonedDateTime zoneId = LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo"));
		LocalDateTime date = zoneId.toLocalDateTime();
		String value = String.valueOf(date.getYear())  
				+ String.valueOf(date.getMonthValue())
				+ String.valueOf(date.getDayOfMonth())
				+ String.valueOf(date.getMinute())
				+ String.valueOf(date.getSecond());
		value = value + value.hashCode();
		System.setProperty(Properties.EXECUTE_ID, value);
		
		// Web driver
		if(System.getProperty(Properties.OS_NAME)
				.toUpperCase().contains("WINDOWS")
				 && this.sysConfig.getBrowser() != Browser.NotFound) {
			File file = new File(this.sysConfig.getBrowser().getPathDriver()
					+ sysConfig.getDriverVersion() + ".exe");
			System.setProperty(this.sysConfig.getBrowser().getWebDriver(), file.getAbsolutePath());
		}
	}
	
	/**
	 * Check browser driver
	 * @param string
	 * @return
	 */
	protected void checkBrowserDriver() {
		switch (this.sysConfig.getBrowser()) {
		case Chrome:
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-extensions");
			this.webDriver = new ChromeDriver(options);
			break;
		case Edge:
			this.webDriver = new EdgeDriver();
			break;
		case FireFox:
			this.webDriver = new FirefoxDriver();
			break;
		case IE:
			this.webDriver = new InternetExplorerDriver();
			break;
		case Opera:
			this.webDriver = new OperaDriver();
			break;
		case Safari:
			this.webDriver = new SafariDriver();
			break;
		default:
			this.webDriver = new ChromeDriver();
			break;
		}
	}

	/**
	 * 
	 */
	protected void setBasicProcessConfig() {
		this.webDriver.get(this.sysConfig.getSystemUrl());
		this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(100000));
	}
}
