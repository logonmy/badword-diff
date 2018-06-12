package cc.momas.badword.excel.to.sql;

import org.ho.yaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Config {

	private String configFileName =  "config.yml";
	private Map<String,String> config ;

	{
		// 读取配置文件
		InputStream path = Config.class.getClassLoader().getResourceAsStream(configFileName);
		try {
			config = Yaml.loadType(path, HashMap.class);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("config read fail");
		}
	}

	public InputStream getOldFile(){
		return getFromClassPath(config.get("oldFilePath"));
	}

	public InputStream getNewFile(){
		return getFromClassPath(config.get("newFilePath"));
	}

	public String getDestFilePath(){
		return config.get("destFilePath");
	}

	// 从classpath中读取输入流
	private InputStream getFromClassPath(String fileName){
		return Config.class.getClassLoader().getResourceAsStream(fileName);
	}
}
