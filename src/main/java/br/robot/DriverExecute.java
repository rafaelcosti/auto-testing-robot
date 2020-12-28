package br.robot;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import br.robot.files.Extensions;
import br.robot.files.Xlsx;

/**
 * Driver execute
 * @author Rafael Costi <rafaelcosti@outlook.com>
 * @version 1.0.0
 */
public class DriverExecute extends BaseClass {	
	/**
	 * Execute robot process
	 */
	public void execute() {
		int executeReturn = 0;
		try {
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
								Xlsx xlsx = new Xlsx(file);
								xlsx.execute();
							default:
								return;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			executeReturn = -1;
		} finally {
			System.exit(executeReturn);
		}
	}	
}
