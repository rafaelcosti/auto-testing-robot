package br.robot.process;

import org.apache.commons.lang3.StringUtils;

/**
 * Dom commands
 * @author Rafael Costi <rafaelcosti@outlook.com>
 * @version 1.0.0
 */
public enum ElementCommand {
	Click,
	InputText,
	NotFound,
	Submit,
	SwitchToFrame;
	
	/**
	 * Get element command by name
	 * @param name
	 * @return ElementCommand
	 */
	public static ElementCommand getByName(String name) {
		if (StringUtils.isEmpty(name)) {
			return NotFound;
		}
		name = name.trim().replace(" ", "");
		for (ElementCommand element : values()) {
			if(element.name().equalsIgnoreCase(name)) {
				return element;
			}
		}
		return NotFound;
	}
}
