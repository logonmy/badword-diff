package cc.momas.badword.excel.to.sql;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class FileUtil {

	// 输出到文件
	public String writeToFile(String destFileName, String sql) throws IOException {
		// 去除开头的/
//		if(destFileName.startsWith("/")){
//			destFileName = destFileName.substring(1);
//		}
		// 获取相对路径
//		String path = this.getClass().getResource("/").getPath();
//		File file = new File(path + destFileName);
		File file = new File(destFileName);
		if(!file.exists()){
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(sql.getBytes("UTF-8"));
		fos.flush();
		fos.close();
		return file.getAbsolutePath();
	}

	/**
	 * 从路径读取Excel文件
	 * @param filepath 文件路径
	 * @return
	 * @throws IOException
	 */
	public Set<Key> readExcel(String filepath) throws IOException{
		FileInputStream in = new FileInputStream(filepath);
		return readExcel(in);
	}

	public Set<Key> readExcel(InputStream oldFileInputStream) throws IOException {
		Workbook workbook = new XSSFWorkbook(oldFileInputStream);
		Sheet sheet = workbook.getSheetAt(0);
		Set<Key> set = new HashSet<>(6000);
		for (int i = 3; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j <= row.getLastCellNum(); j = j + 2) {
				Cell cell = row.getCell(j);
				if (cell != null && cell.getStringCellValue() != null && !cell.getStringCellValue().trim().equals("")) {
					Key key = new Key();
					key.setCreated(new Date());
					key.setType(j/6);
					key.setWord(cell.getStringCellValue());
					key.setWordType(j % 6);
					set.add(key);
				}
			}
		}
		return set;
	}
}
