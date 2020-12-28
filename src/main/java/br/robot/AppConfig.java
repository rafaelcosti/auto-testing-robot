package br.robot;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

/**
 * AppConfig
 * Read properties file
 * @author Rafael Costi <rafaelcosti@outlook.com>
 * @version 1.0.0
 */
public class AppConfig {
	/**
	 * Properties
	 */
	private Properties properties;
	
	/**
	 * Constructor
	 */
	private AppConfig() {
		this.properties = new Properties();
		InputStream iStream = null;
	    try {
	    	iStream = new FileInputStream("./src/main/resources/app.properties");
	    	this.properties.load(iStream);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
	    		if(iStream != null){
	    			iStream.close();
	    		}
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
	    }
	}
	
	/**
	 * Get AppConfig new instance
	 * @return
	 */
	public static AppConfig getInstance() {
		return new AppConfig();
	}
		
	/**
	 * Get system.file.read.path.execute.process
	 * @return
	 */
	public String getSystemFileReadPathExecuteProcess() {
		if (this.properties == null) {
			return StringUtils.EMPTY;
		}
		return this.properties.getProperty("system.file.read.path.execute.process"); 
	}
	
	/**
	 * Get system.file.xlsx.configuration.worksheet
	 * @return
	 */
	public String getSystemFileXlsxConfigurationWorksheet() {
		if (this.properties == null) {
			return StringUtils.EMPTY;
		}
		return this.properties.getProperty("system.file.xlsx.configuration.worksheet"); 
	}
	
	/**
	 * Get system.file.drivers.path
	 * @return
	 */
	public String getSystemFileDriversPath() {
		if (this.properties == null) {
			return StringUtils.EMPTY;
		}
		return this.properties.getProperty("system.file.drivers.path"); 
	}
	
	/**
	 * Get system.file.xlsx.data.worksheet
	 * @return
	 */
	public String getSystemFileXlsxDataWorksheet() {
		if (this.properties == null) {
			return StringUtils.EMPTY;
		}
		return this.properties.getProperty("system.file.xlsx.data.worksheet"); 
	}	
}
