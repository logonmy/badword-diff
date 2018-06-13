import org.junit.Test;

import java.io.File;

public class FileTest {


	@Test
	public void newFile(){
		String path = this.getClass().getResource("").getPath();
		System.out.println(path);

		File file = new File(path + "/excel/dest.sql");
		System.out.println(file.getAbsolutePath());
	}

	@Test
	public void location(){
		String s = "/excel/dest.sql";
		s = s.substring(1);
		System.out.println(s);
	}
}
