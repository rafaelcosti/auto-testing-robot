package br.robot.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import br.robot.BaseClass;
import br.robot.SysConfig;

/**
 * Base file common process
 * @author Rafael Costi <rafaelcosti@outlook.com>
 * @version 1.0.0
 */
public abstract class BaseFile extends BaseClass {
	/**
	 * File input stream
	 */
	protected FileInputStream fis;
	
	/**
	 * Execute File process
	 * @param file
	 * @throws FileNotFoundException
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 * @throws org.apache.poi.openxml4j.exceptions.InvalidFormatException 
	 */
	protected SysConfig execute (File file, Extensions ext) throws Exception {
		this.fis = new FileInputStream(file);
		return new SysConfig();
	}
}
