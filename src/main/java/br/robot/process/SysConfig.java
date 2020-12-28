package br.robot.process;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.robot.Browser;

/**
 * System configuration entity 
 * @author Rafael Costi <rafaelcosti@outlook.com>
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysConfig {	
	/**
	 * Constructor
	 */
	public SysConfig() {
		super();
	}

	/**
	 * Constructor
	 * @param systemName
	 * @param systemVersion
	 * @param map
	 */
	public SysConfig(String systemName, String systemVersion, Browser browser
			, String driverVersion, Map<Integer, StepProcess> map) {
		super();
		this.systemName = systemName;
		this.systemVersion = systemVersion;
		this.browser = browser;
		this.map = map;
		this.driverVersion = driverVersion;
	}

	/**
	 * System name
	 */
	@JsonProperty("systemName")
	private String systemName;

	/**
	 * @return the systemName
	 */
	public String getSystemName() {
		return systemName;
	}

	/**
	 * @param systemName the systemName to set
	 */
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
	/**
	 * System version
	 */
	@JsonProperty("version")
	private String systemVersion;

	/**
	 * @return the systemVersion
	 */
	public String getSystemVersion() {
		return systemVersion;
	}

	/**
	 * @param systemVersion the systemVersion to set
	 */
	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	/**
	 * System browser
	 */
	@JsonProperty("browser")
	private Browser browser;

	/**
	 * @return the browser
	 */
	public Browser getBrowser() {
		return this.browser;
	}

	/**
	 * @param browser the browser to set
	 */
	public void setBrowser(Browser browser) {
		this.browser = browser;
	}
	
	/**
	 * Driver version
	 */
	@JsonProperty("driverVersion")
	private String driverVersion;

	/**
	 * @return the driverVersion
	 */
	public String getDriverVersion() {
		return this.driverVersion;
	}

	/**
	 * @param driverVersion the driverVersion to set
	 */
	public void setDriverVersion(String driverVersion) {
		this.driverVersion = driverVersion;
	}

	/**
	 * System url
	 */
	@JsonProperty("systemUrl")
	private String systemUrl;

	/**
	 * @return the systemUrl
	 */
	public String getSystemUrl() {
		return this.systemUrl;
	}

	/**
	 * @param systemUrl the systemUrl to set
	 */
	public void setSystemUrl(String systemUrl) {
		this.systemUrl = systemUrl;
	}
	
	/**
	 * Map process
	 */
	@JsonIgnore
	private Map<Integer, StepProcess> map;

	/**
	 * @return the map
	 */
	public Map<Integer, StepProcess> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<Integer, StepProcess> map) {
		this.map = map;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "{ \"systemName\": \"" + this.systemName + "\""
				+ ", \"systemVersion\": \"" + this.systemVersion + "\""
				+ ", \"browser\": \"" + this.browser + "\""
				+ ", \"driverVersion\": \"" + this.driverVersion + "\""
				+ ", \"systemUrl\": \"" + this.systemUrl + "\""
				+ ", map\": " + this.map + " }";
	}
}
