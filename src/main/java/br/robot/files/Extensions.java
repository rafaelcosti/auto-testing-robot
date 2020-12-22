package br.robot.files;

import org.apache.commons.lang3.StringUtils;

/**
 * Contain file extensions
 * @author Rafael Costi <rafaelcosti@outlook.com>
 * @version 1.0.0
 */
public enum Extensions {
	Csv("csv"),
	Json("json"),
	NotFound(""),
	Txt("txt"),
	Xls("xls"),
	Xlsx("xlsx");
	
	/**
	 * 
	 */
	private String extension;
	
	/**
	 * Constructor
	 * @param extension
	 */
	private Extensions(String extension) {
		this.extension = extension;
	}
	
	/**
	 * Get extension
	 * @return
	 */
	public String getExtension() {
		return this.extension;
	}
	
	/**
	 * Get enum by extension
	 * @param ext
	 * @return Extensions
	 */
	public static Extensions getByExtensions(String ext) {
		if (StringUtils.isEmpty(ext)) {
			return NotFound;
		}
		ext = ext.trim();
		for (Extensions extension : values()) {
			if (extension.getExtension().equalsIgnoreCase(ext)) {
				return extension;
			}
		}
		return NotFound;
	}
}
