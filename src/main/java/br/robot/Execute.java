package br.robot;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Execute Web process
 * @author Rafael Costi <rafaelcosti@outlook.com>
 * @version 1.0.0
 */
@SpringBootApplication
public class Execute {
	/**
	 * Initialize precess
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		new DriverExecute().execute();
	}
}
