package br.robot.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.robot.AppConfig;
import br.robot.Browser;
import br.robot.process.ElementCommand;
import br.robot.process.StepProcess;

/**
 * Execute Xslx process
 * @author Rafael Costi <rafaelcosti@outlook.com>
 * @version 1.0.0
 */
public class Xlsx extends BaseFile {
	/**
	 * Xlsx workbook
	 */
	private Workbook workbook;
	
	/**
	 * Xlsx workbook data
	 */
	private Sheet workbookSheet;

	/**
	 * Constructor
	 * @throws IOException 
	 */
	public Xlsx(File file) throws IOException {
		super(file);
		this.workbook = new XSSFWorkbook(super.fis);
		this.loadSysConfig();
		super.initProperties();
		super.checkBrowserDriver();
		workbookSheet = this.workbook.getSheet(AppConfig.getInstance()
				.getSystemFileXlsxDataWorksheet());
	}
	
	/**
	 * Load system configuration data
	 */
	private void loadSysConfig() {
		Sheet system = this.workbook.getSheet(AppConfig.getInstance()
				.getSystemFileXlsxConfigurationWorksheet());
		int system_row = 0;
		for (Row row : system) {
			if(system_row == 0 || system_row == 2 || system_row == 3) {
				system_row++;
				continue;
			}
			if (system_row == 1) {
				Cell cellSystemName = row.getCell(XlsxConstants.IX_SYSTEM_NAME);
				super.sysConfig.setSystemName(cellSystemName
						.getStringCellValue()
						.toLowerCase()
						.trim()
						.replace(" ", "_"));
				Cell cellVersion = row.getCell(XlsxConstants.IX_SYSTEM_VERSION);
				super.sysConfig.setSystemVersion(cellVersion
						.getStringCellValue()
						.trim()
						.replace(" ", "_"));
				Cell cellBrowser = row.getCell(XlsxConstants.IX_BROWSER);
				super.sysConfig.setBrowser(Browser.getByNameOrCode(cellBrowser
						.getStringCellValue()
						.trim()
						.replace(" ", "_")));
				Cell cellDriverVersion = row.getCell(XlsxConstants.IX_DRIVER_VERSION);
				super.sysConfig.setDriverVersion(cellDriverVersion
						.getStringCellValue()
						.trim()
						.replace(" ", "_"));
				Cell cellSystemUrl = row.getCell(XlsxConstants.IX_SYSTEM_URL);
				super.sysConfig.setSystemUrl(cellSystemUrl
						.getStringCellValue()
						.trim()
						.replace(" ", "_"));
			} else {
				loadStepProcess(row);
			}
			system_row++;
		}
		super.sysConfig.setMap(this.stepProcess);
		System.out.println(super.sysConfig.toString());
	}
	
	/**
	 * Load step process
	 * @param row
	 */
	private void loadStepProcess(Row row) {
		boolean has_value = false;
		Cell cellOrder = row.getCell(XlsxConstants.IX_STEP_PROCESS_ORDER);
		int order = 0;
		if (cellOrder != null) {
			has_value = true;
			order = (int)cellOrder.getNumericCellValue();
		}
		
		Cell cellWebId = row.getCell(XlsxConstants.IX_STEP_PROCESS_WEB_ID);
		String webId = StringUtils.EMPTY;
		if(cellWebId != null) {
			webId = cellWebId.getStringCellValue();
		}
		
		Cell cellField = row.getCell(XlsxConstants.IX_STEP_PROCESS_FIELD);
		String field = StringUtils.EMPTY;
		if(cellField != null) {
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
		
		if (has_value) {
			this.stepProcess.put(order, new StepProcess(order, webId, command, value, elementType, elementBy, field));	
		}
	}

	/**
	 * Execute process from workbook
	 */
	public void execute() {
		try {
			if(super.sysConfig.getMap() != null && !super.sysConfig.getMap().isEmpty()) {
				List<StepProcess> steps = new ArrayList<StepProcess>(super.sysConfig.getMap().values());
				Collections.sort(steps) ;
				int row_number = 0;
				if (this.workbookSheet != null) {				
					for (Row row : this.workbookSheet) {
						if (row_number > 0) {
							super.setBasicProcessConfig();
							for (StepProcess stepProcess : steps) {
								WebElement we = null;
								if ("className".equalsIgnoreCase(stepProcess.getElementBy())) {
									we = webDriver.findElement(By.className(stepProcess.getWebId()));
								} else if ("id".equalsIgnoreCase(stepProcess.getElementBy())) {
									we = webDriver.findElement(By.id(stepProcess.getWebId()));
								} else if ("name".equalsIgnoreCase(stepProcess.getElementBy())) {
									we = webDriver.findElement(By.name(stepProcess.getWebId()));
								} else if ("tag".equalsIgnoreCase(stepProcess.getElementBy())) {
									we = webDriver.findElement(By.tagName(stepProcess.getElementType()));
								} else if ("linkText".equalsIgnoreCase(stepProcess.getElementBy())) {
									we = webDriver.findElement(By.partialLinkText(stepProcess.getElementType()));
								} else if ("partialLinkText".equalsIgnoreCase(stepProcess.getElementBy())) {
									we = webDriver.findElement(By.partialLinkText(stepProcess.getElementType()));
								} else if ("cssSelector".equalsIgnoreCase(stepProcess.getElementBy())) {
									we = webDriver.findElement(By.cssSelector(stepProcess.getElementType()));
								}
								
								if(we == null) {
									break;
								}
								
								ElementCommand ec = ElementCommand.getByName(stepProcess.getCommand());
								if (ec == ElementCommand.Click) {
									we.click();
								} else if (ec == ElementCommand.InputText) {
									if (StringUtils.isEmpty(stepProcess.getValue())) {
										throw new RuntimeException("Valor de entrada inválido");
									}
									Cell cellValue = 
											row.getCell(Integer.parseInt(stepProcess.getValue()));
									if (cellValue == null) {
										//TODO create validation
										break;
									}
									String value = cellValue.getStringCellValue();
									we.sendKeys(value);
								} else if (ec == ElementCommand.Submit) {
									we.submit();
								} else if (ec == ElementCommand.SwitchToFrame) {
									webDriver.switchTo().frame(we);
								}
							}
						}
						row_number++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(super.webDriver != null) {
				super.webDriver.close();
				super.webDriver.quit();
			}
		}
	}
}
