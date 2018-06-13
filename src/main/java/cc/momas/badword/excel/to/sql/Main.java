package cc.momas.badword.excel.to.sql;

import java.io.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main implements Runnable {

	private Config config = new Config();
	private FileUtil files = new FileUtil();
	private SqlGenerator generator = new SqlGenerator();

	public static void main(String[] args) {
		new Main().run();
	}

	@Override
	public void run() {

		InputStream oldFileInputStream = config.getOldFile();
		InputStream newFileInputStream = config.getNewFile();
		String destFilePath = config.getDestFilePath();

		try {

			Set<Key> oldWords = files.readExcel(oldFileInputStream);
			System.out.println("旧文件词条数 ： " + oldWords.size());

			Set<Key> newWords = new HashSet<>();
			if (newFileInputStream != null) {
				newWords = files.readExcel(newFileInputStream);
			}
			System.out.println("新文件词条数 ： " + newWords.size());

			newWords.removeAll(oldWords);
			System.out.println("新增词条数 ： " + newWords.size());

			System.out.println("开始生成sql");
			String sql = generator.generateSql(newWords);
			System.out.println("生成sql完毕,开始写入文件");
			destFilePath = files.writeToFile(destFilePath, sql);
			if (sql.contains("{ERROR}")) {
				System.out.println("生成的过程中发生了一点问题, 请在文件中搜索 {ERROR} 以定位问题sql所在位置");
			}
			System.out.println("文件写入完毕,路径为 : \r\n" + destFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
