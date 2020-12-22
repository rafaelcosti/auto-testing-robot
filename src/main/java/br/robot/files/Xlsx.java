package br.robot.files;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.robot.AppConfig;
import br.robot.StepProcess;
import br.robot.SysConfig;

/**
 * Execute Xslx process
 * @author Rafael Costi <rafaelcosti@outlook.com>
 * @version 1.0.0
 */
public class Xlsx extends BaseFile {
	/**
	 * Cosntructor
	 */
	public Xlsx() {
		super();
		this.stepProcess = new HashMap<Integer, StepProcess>();
	}

	/**
	 * Xlsx workbook
	 */
	private Workbook workbook;
	
	/**
	 * 
	 */
	private Map<Integer, StepProcess> stepProcess;

	/**
	 * Execute xlsx process
	 */
	@Override
	public SysConfig execute(File file, Extensions ext) throws Exception {
		SysConfig sysConfig = super.execute(file, ext);
		this.workbook = new XSSFWorkbook(super.fis);
		Sheet system = this.workbook.getSheet(AppConfig.getInstance()
				.getSystemFileXlsxConfigurationWorksheet());
		int system_row = 0;
		for (Row row : system) {
			if(system_row == 0 || system_row == 2 || system_row == 3) {
				system_row++;
				continue;
			}
			if (system_row == 1) {
				getSysConfig(row, sysConfig);
			} else {
				getCommandsAndValues(row, sysConfig);
			}
			system_row++;
		}
		sysConfig.setMap(this.stepProcess);
		System.out.println(sysConfig.toString());
		return sysConfig;
	}

	/**
	 * 
	 * @param row
	 * @param sysConfig
	 */
	private void getCommandsAndValues(Row row, SysConfig sysConfig) {
		Cell cellOrder = row.getCell(XlsxConstants.IX_STEP_PROCESS_ORDER);
		int order = (int)cellOrder.getNumericCellValue();
		
		Cell cellWebId = row.getCell(XlsxConstants.IX_STEP_PROCESS_WEB_ID);
		String webId = StringUtils.EMPTY;
		if(cellWebId != null) {
			webId = cellWebId.getStringCellValue();
		}
		
		Cell cellField = row.getCell(XlsxConstants.IX_STEP_PROCESS_FIELD);
		String field = StringUtils.EMPTY;
		if(cellWebId != null) {
			field = cellField.getStringCellValue();
		}
		
		Cell cellCommand = row.getCell(XlsxConstants.IX_STEP_PROCESS_COMMAND);
		String command = StringUtils.EMPTY;
		if(cellCommand != null) {
			command = cellCommand.getStringCellValue();
		}
			
		Cell cellValue = row.getCell(XlsxConstants.IX_STEP_PROCESS_VALUE);
		String value = StringUtils.EMPTY;
		if(cellValue != null) {
			value = cellValue.getStringCellValue();
		}

		Cell cellElementType = row.getCell(XlsxConstants.IX_STEP_PROCESS_ELEMENT_TYPE);	
		String elementType = StringUtils.EMPTY;
		if(cellElementType != null) {
			elementType = cellElementType.getStringCellValue();
		}

		Cell cellElementBy = row.getCell(XlsxConstants.IX_STEP_PROCESS_ELEMENT_BY);		
		String elementBy = StringUtils.EMPTY;
		if(cellElementType != null) {
			elementBy = cellElementBy.getStringCellValue();
		}
		
		this.stepProcess.put(order, new StepProcess(order, webId, command, value, elementType, elementBy, field));
	}

	/**
	 * Get system process configuration 
	 * @param row
	 * @throws Exception 
	 */
	private SysConfig getSysConfig(Row row, SysConfig sysConfig) throws Exception {
		Cell cellSystemName = row.getCell(XlsxConstants.IX_SYSTEM_NAME);
		sysConfig.setSystemName(cellSystemName
				.getStringCellValue()
				.toLowerCase()
				.trim()
				.replace(" ", "_"));
		Cell cellVersion = row.getCell(XlsxConstants.IX_SYSTEM_VERSION);
		sysConfig.setSystemVersion(cellVersion
				.getStringCellValue()
				.trim()
				.replace(" ", "_"));
		Cell cellBrowser = row.getCell(XlsxConstants.IX_BROWSER);
		sysConfig.setBrowser(cellBrowser
				.getStringCellValue()
				.trim()
				.replace(" ", "_"));
		Cell cellDriverVersion = row.getCell(XlsxConstants.IX_DRIVER_VERSION);
		sysConfig.setDriverVersion(cellDriverVersion
				.getStringCellValue()
				.trim()
				.replace(" ", "_"));
		Cell cellSystemUrl = row.getCell(XlsxConstants.IX_SYSTEM_URL);
		sysConfig.setSystemUrl(cellSystemUrl
				.getStringCellValue()
				.trim()
				.replace(" ", "_"));
		return sysConfig;
	}
}
