package erp.client.javafx.config;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

public class ConfigurationManager {

	private static final Logger logger = LogManager.getLogger(ConfigurationManager.class);
	
	public static Configuration getConfiguration(){
		Yaml yaml = new Yaml();
		BufferedInputStream inputStream = null;
		File configurationFile = null;
		try {
			String path = Path.of("").toAbsolutePath() + File.separator + "config" + File.separator + "configurations.yml";
			configurationFile = new File(path);
			inputStream = new BufferedInputStream(new FileInputStream(configurationFile));
		} catch (FileNotFoundException e) {
			logger.info("Configuration file not found hence creating new file");
			try {
				if(configurationFile != null && configurationFile.createNewFile()) {
					Configuration configuration = new Configuration();
					configuration.createDefaultConfiguration();
					FileWriter fileWriter = new FileWriter(configurationFile);
					yaml.dump(configuration, fileWriter);
					logger.info("Configuration file created successfully");
					inputStream = new BufferedInputStream(new FileInputStream(configurationFile));
				}
			} catch (IOException e2) {
				logger.error("Something went wrong while creating configuration file !");
				e2.printStackTrace();
			}
		}
		return yaml.loadAs(inputStream, Configuration.class);
	}

	public static PrinterConfiguration getPrinterConfiguration(){
		Yaml yaml = new Yaml();
		BufferedInputStream inputStream = null;
		File printerConfigurationFile = null;
		try {
			printerConfigurationFile = new File("config/printer.yml");
			inputStream = new BufferedInputStream(new FileInputStream(printerConfigurationFile));
		} catch (FileNotFoundException e) {
			logger.info("Printer configuration file not found hence creating new file");
			try {
				if(printerConfigurationFile != null && printerConfigurationFile.createNewFile()) {
					PrinterConfiguration printerConfiguration = new PrinterConfiguration();
					printerConfiguration.createDefaultPrinterConfiguration();
					FileWriter fileWriter = new FileWriter(printerConfigurationFile);
					yaml.dump(printerConfiguration, fileWriter);
					logger.info("Printer configuration file created successfully");
					inputStream = new BufferedInputStream(new FileInputStream(printerConfigurationFile));
				}
			} catch (IOException e1) {
				logger.error("Something went wrong while creating printer configuration file !");
				e1.printStackTrace();
			}
		}
		return yaml.loadAs(inputStream, PrinterConfiguration.class);
	}
}
