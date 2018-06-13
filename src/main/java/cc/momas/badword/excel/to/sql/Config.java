package cc.momas.badword.excel.to.sql;

import org.ho.yaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Config {

	private String configFileName = "/config.yml";
	private String oldFilePath = "oldFilePath";
	private String newFilePath = "newFilePath";
	private String destFilePath = "destFilePath";
	private Map<String, String> config;

	{
		try {
			// 读取配置文件
			InputStream path = getFromClassPath(configFileName);
			config = Yaml.loadType(path, HashMap.class);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(configFileName + " not found");
		}
	}

	/**
	 * 获取旧文件路径
	 *
	 * @return
	 */
	public InputStream getOldFile() {
		return getFromClassPath(config.get(oldFilePath));
	}

	public InputStream getNewFile() {
		String path = config.get(newFilePath);
		if (null != path && !"".equals(path)) {
			return getFromClassPath(path);
		}
		return null;
	}

	public String getDestFilePath() {
		return config.get(destFilePath);
	}

	// 从classpath中读取输入流
	private InputStream getFromClassPath(String fileName) {
		InputStream in = this.getClass().getResourceAsStream(fileName);
		if (null == in) {
			in = this.getClass().getClassLoader().getResourceAsStream(fileName);
		}
		return in;
	}
}
