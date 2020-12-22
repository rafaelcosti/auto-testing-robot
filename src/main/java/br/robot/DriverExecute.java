package br.robot;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import br.robot.files.Extensions;
import br.robot.files.Xlsx;

/**
 * Driver execute
 * @author Rafael Costi <rafaelcosti@outlook.com>
 * @version 1.0.0
 */
public class DriverExecute {
	/**
	 * Execute robot process
	 */
	public void execute() {
		WebDriver webDriver = null;
		int executeReturn = 0;
		try {
			SysConfig sysConfig = loadFiles();
			Browser browser = getbrowser(sysConfig);
			initProperties(sysConfig, browser);
			
			webDriver = checkBrowserDriver(sysConfig, browser);
			webDriver.get(sysConfig.getSystemUrl());
			webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(100000));
			executeProcess(webDriver, sysConfig, browser);
		} catch (Exception e) {
			e.printStackTrace();
			executeReturn = -1;
		} finally {
			if(webDriver != null) {
				webDriver.quit();
			}
			System.exit(executeReturn);
		}
	}

	/**
	 * Execute process
	 * @param webDriver
	 * @param sysConfig
	 * @param browser
	 */
	private void executeProcess(WebDriver webDriver, SysConfig sysConfig, Browser browser) {
		if(sysConfig.getMap() != null && !sysConfig.getMap().isEmpty()) {
			List<StepProcess> steps = new ArrayList<StepProcess>(sysConfig.getMap().values());
			Collections.sort(steps) ;
			for (StepProcess stepProcess : steps) {
				WebElement we = null;
				if ("className".equalsIgnoreCase(stepProcess.getElementBy())) {
					we = webDriver.findElement(By.className(stepProcess.getWebId()));
				}
				if ("id".equalsIgnoreCase(stepProcess.getElementBy())) {
					we = webDriver.findElement(By.id(stepProcess.getWebId()));
				}
				if ("name".equalsIgnoreCase(stepProcess.getElementBy())) {
					we = webDriver.findElement(By.name(stepProcess.getWebId()));
				}
				if ("tag".equalsIgnoreCase(stepProcess.getElementBy())) {
					we = webDriver.findElement(By.tagName(stepProcess.getElementType()));
				}
				
				if(we == null) {
					break;
				}
				
				ElementCommand ec = ElementCommand.getByName(stepProcess.getCommand());
				if (ec == ElementCommand.Click) {
					we.click();
				}
				if (ec == ElementCommand.InputText) {
					we.sendKeys(stepProcess.getValue());
				}
				if (ec == ElementCommand.Submit) {
					we.submit();
				}
			}
		}		
	}

	/**
	 * Check browser driver
	 * @param string
	 * @return
	 */
	private WebDriver checkBrowserDriver(SysConfig sysConfig, Browser browser) {
		switch (browser) {
		case Chrome:
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-extensions");
			return new ChromeDriver(options);
		case Edge:
			return new EdgeDriver();
		case FireFox:
			return new FirefoxDriver();
		case IE:
			return new InternetExplorerDriver();
		case Opera:
			return new OperaDriver();
		case Safari:
			return new SafariDriver();
		default:
			return new ChromeDriver();
		}
	}

	/**
	 * Load config process
	 * @throws Exception 
	 */
	private SysConfig loadFiles() throws Exception {
		List<File> files = Arrays.asList(new File(AppConfig
				.getInstance().getSystemFileReadPathExecuteProcess()).listFiles());
		if (files != null && files.size() > 0) {
			for (File file : files) {
				if (file.isFile()) {						
					Extensions ext = Extensions
							.getByExtensions(FilenameUtils.getExtension(file.getName()));
					switch (ext) {
						case Csv:
						case Json:
						case Xls:
						case Xlsx:
							Xlsx xlsx = new Xlsx();
							return xlsx.execute(file, ext);
						default:
							return null;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Get Browser
	 * @param sysConfig
	 * @return
	 */
	private Browser getbrowser(SysConfig sysConfig) {
		return Browser.getByNameOrCode(sysConfig.getBrowser());
	}

	/**
	 * Initialize properties
	 */
	private void initProperties(SysConfig sysConfig, Browser browser) {
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
		if(System.getProperty(Properties.OS_NAME)
				.toUpperCase().contains("WINDOWS")
				 && browser != Browser.NotFound) {
			File file = new File(browser.getPathDriver()
					+ sysConfig.getDriverVersion() + ".exe");
			System.setProperty(browser.getWebDriver(), file.getAbsolutePath());
		}
	}
}
