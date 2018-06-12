package cc.momas.badword.excel.to.sql;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {
	public static void main(String[] args){


		String oldFilename = "C:\\Users\\sothe\\Desktop\\get done\\敏感词屏蔽\\【20180428更新】查看屏蔽关键词.xlsx";
		String newFileName = "C:\\Users\\sothe\\Desktop\\查看屏蔽关键词0528.xlsx";
		String distFileName = "D:/dest.sql";

		try {
			Set<Key> oldWords = readExcel(oldFilename);
			System.out.println(oldWords.size());
			
			Set<Key> newWords = new HashSet<>();
			if(newFileName != null){
				newWords = readExcel(newFileName);
			}
			System.out.println(newWords.size());
			
			newWords.removeAll(oldWords);
			System.out.println(newWords.size());
			
			String sql = generateSql(newWords);
			writeToFile(distFileName,sql);
			System.out.println("success process, destinct file : " + distFileName);
			if(sql.contains("{ERROR}")){
				System.out.println("there are some error in sql,please check. use {ERROR} string to find error position");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 输出到文件 
	private static void writeToFile(String distFileName, String sql) throws IOException {
		File file = new File(distFileName);
		if(!file.exists()){
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(sql.getBytes());
		fos.flush();
		fos.close();
	}
	
	// 用来生成sql
	private static String generateSql(Set<Key> keys) {
//		INSERT INTO hlx_badword.tb_wj_verb VALUE(null,'销售',0,NOW());
		StringBuilder sb = new StringBuilder();
		for (Key key : keys) {
			sb.append("INSERT INTO ");
			switch (key.getWordType()) {
			case Key.VERB:
				sb.append(" hlx_badword.tb_wj_verb ");
				break;
			case Key.NOUN:
				sb.append(" hlx_badword.tb_wj_noun ");
				break;
			case Key.KEYWORD:
				sb.append(" hlx_badword.tb_wj_keyword ");
				break;
			default:
				sb.append("{ERROR}");
				break;
			}
			
			sb.append(" VALUE(null,'");
			sb.append(key.getWord());
			sb.append("',");
			sb.append(key.getType());
			sb.append(",NOW());\r\n");
		}
		return sb.toString();
	}

	private static Set<Key> readExcel(String filepath) throws IOException{
		FileInputStream in = new FileInputStream(filepath);
		Workbook workbook = new XSSFWorkbook(in);
		Sheet sheet = workbook.getSheetAt(0);
		// Cell cell = row.getCell(1);
		// System.out.println(cell.getStringCellValue());
		Set<Key> set = new HashSet<>(6000);
		//System.out.println(sheet.getLastRowNum());
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
//					if (key.getType().equals(1)) {
//						System.out.println(key);
//					}
				}
			}
		}
		return set;
	}

}
