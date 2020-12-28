package br.robot.process;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Step process
 * @author Rafael Costi <rafaelcosti@outlook.com>
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StepProcess implements Comparable<StepProcess> {
	/**
	 * Constructor
	 */
	public StepProcess() {
		super();
	}
	
	/**
	 * Constructor
	 * @param order
	 */
	public StepProcess(int order) {
		super();
		this.order = order;
	}
	
	/**
	 * Constructor
	 * @param order
	 * @param webId
	 */
	public StepProcess(int order, String webId) {
		super();
		this.order = order;
		this.webId = webId;
	}

	/**
	 * Constructor
	 * @param order
	 * @param webId
	 * @param command
	 */
	public StepProcess(int order, String webId, String command) {
		super();
		this.order = order;
		this.webId = webId;
		this.command = command;
	}

	/**
	 * Constructor
	 * @param order
	 * @param webId
	 * @param command
	 * @param value
	 */
	public StepProcess(int order, String webId, String command, String value) {
		super();
		this.order = order;
		this.webId = webId;
		this.command = command;
		this.value = value;
	}

	/**
	 * Constructor
	 * @param order
	 * @param webId
	 * @param command
	 * @param value
	 * @param elementType
	 */
	public StepProcess(int order, String webId, String command, String value, String elementType) {
		super();
		this.order = order;
		this.webId = webId;
		this.command = command;
		this.value = value;
		this.elementType = elementType;
	}

	/**
	 * Constructor
	 * @param order
	 * @param webId
	 * @param command
	 * @param value
	 * @param elementType
	 * @param field
	 */
	public StepProcess(int order, String webId, String command, String value, String elementType, String elementBy) {
		super();
		this.order = order;
		this.webId = webId;
		this.command = command;
		this.value = value;
		this.elementType = elementType;
		this.elementBy = elementBy;
	}

	/**
	 * Constructor
	 * @param order
	 * @param webId
	 * @param command
	 * @param value
	 * @param elementType
	 * @param elementBy
	 * @param field
	 */
	public StepProcess(int order, String webId, String command, String value, String elementType, String elementBy, String field) {
		super();
		this.order = order;
		this.webId = webId;
		this.command = command;
		this.value = value;
		this.elementType = elementType;
		this.field = field;
		this.elementBy = elementBy;
	}

	/**
	 * Process order
	 */
	private int order;

	/**
	 * @return the order
	 */
	public int getOrder() {
		return this.order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}
	
	/**
	 * Field
	 */
	private String field;

	/**
	 * @return the field
	 */
	public String getField() {
		return this.field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}
	
	/**
	 * Web id
	 */
	private String webId;

	/**
	 * @return the webId
	 */
	public String getWebId() {
		return this.webId;
	}

	/**
	 * @param webId the webId to set
	 */
	public void setWebId(String webId) {
		this.webId = webId;
	}
	
	/**
	 * Command
	 */
	private String command;

	/**
	 * @return the command
	 */
	public String getCommand() {
		return this.command;
	}

	/**
	 * @param command the command to set
	 */
	public void setCommand(String command) {
		this.command = command;
	}
	
	/**
	 * Value 
	 */
	private String value;

	/**
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Element type
	 */
	private String elementType;

	/**
	 * @return the elementType
	 */
	public String getElementType() {
		return this.elementType;
	}

	/**
	 * @param elementType the elementType to set
	 */
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	
	/**
	 * Element by
	 */
	@JsonProperty("elementBy")
	private String elementBy;

	/**
	 * @return the elementBy
	 */
	public String getElementBy() {
		return this.elementBy;
	}

	/**
	 * @param elementBy the elementBy to set
	 */
	public void setElementBy(String elementBy) {
		this.elementBy = elementBy;
	}

	/**
	 * Compare with order
	 */
	@Override
	public int compareTo(StepProcess stepProcess) { 
		if (this.order > stepProcess.getOrder()) { 
			return 1; 
		} if (this.order < stepProcess.getOrder()) { 
			  return -1; 
		} 
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "{ \"order\": " + this.order + "\""
				+ ", \"webId\": \"" + this.webId + "\""
				+ ", \"command\": \"" + this.command + "\""
				+ ", \"value\": \"" + this.value+ "\""
				+ ", \"elementType\": \"" + this.elementType + "\""
				+ ", \"elementBy\": " + this.elementBy + "\""
				+ ", \"field\": \"" + this.field + "\" }";
	}	
}
