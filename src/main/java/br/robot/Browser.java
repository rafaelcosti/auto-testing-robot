package br.robot;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author Rafael Costi <rafaelcosti@outlook.com>
 * @version 1.0.0
 */
public enum Browser {
	Chrome("chromedriver", 1 , "webdriver.chrome.driver"),
	Default("chromedriver", 0, ""),
	Edge(null, 2, ""),
	FireFox(null, 3, ""),
	IE("iedriver", 4, "webdriver.ie.driver"),
	NotFound(null, 0, ""),
	Opera(null, 5, ""),
	Safari(null, 6, ""),
	Vivald(null, 7, "");
	
	/**
	 * Path driver 
	 */
	private String pathDriver;
	
	/**
	 * Web driver 
	 */
	private String webdriver;
	
	/**
	 * Driver code 
	 */
	private int code;
	
	/**
	 * Constructor
	 * @param driveInstance
	 */
	private Browser(String pathDriver, int code, String webdriver) {
		this.pathDriver = pathDriver;
		this.code = code;
		this.webdriver = webdriver;
	}
	
	/**
	 * Get path driver
	 * @return
	 */
	public String getPathDriver() {
		return AppConfig.getInstance().getSystemFileDriversPath()
				+ this.pathDriver
				+ "\\";
	}
	
	/**
	 * Get driver code
	 * @return
	 */
	public int getCode() {
		return this.code;
	}
	
	/**
	 * Get web driver
	 * @return
	 */
	public String getWebDriver() {
		return this.webdriver;
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public static Browser getByNameOrCode(String value) {
		if (StringUtils.isEmpty(value)) {
			return NotFound;
		}

		int code = 0;
		if(StringUtils.isNumeric(value)) {
			code = Integer.parseInt(value);
		} 
		String name = value.toUpperCase().trim();
		for (Browser browser : values()) {
			if (code > 0) {
				if (browser.getCode() == code) {
					return browser;
				}
			} else {
				if (browser.name().toUpperCase().equals(name)) {
					return browser;
				}
			}
		}
		return NotFound;
	}
}
