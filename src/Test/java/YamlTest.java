import entity.Person;
import org.ho.yaml.Yaml;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YamlTest {

	@Test
	public void writeYaml(){
		Person person = getPerson();
		String dumpfile = Yaml.dump(person);
		System.out.println(dumpfile);
	}

	@Test
	public void readYaml() throws URISyntaxException {
		String fileName = "person.yml";
		File ymlfile = new File(YamlTest.class.getResource(fileName).toURI());
		try {
			Person father = Yaml.loadType(ymlfile, Person.class);
			System.out.println(father.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void readMap() throws FileNotFoundException {
		String fileName = "map.yml";
		String path = YamlTest.class.getResource(fileName).getPath();
		Map<String,String> map =  Yaml.loadType(new File(path),HashMap.class);
		for (String s :map.keySet()) {
			System.out.println( s + " : " + map.get(s));
		}
	}

	// 产生一个对象出来
	private Person getPerson(){
		Person father = new Person();
		father.setName("simon.zhang");
		father.setAge(23);
		father.setSex("man");
		List<Person> children=new ArrayList<Person>();
		for (int i = 8; i < 10; i++) {
			Person child = new Person();
			if (i % 2 == 0) {
				child.setSex("man");
				child.setName("mary" + (i - 7));
			} else {
				child.setSex("female");
				child.setName("simon" + (i - 7));
			}
			child.setAge(i);
			children.add(child);
		}
		father.setChildren(children);

		return father;
	}
}
