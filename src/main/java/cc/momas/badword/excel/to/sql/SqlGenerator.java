package cc.momas.badword.excel.to.sql;

import java.util.Set;

public class SqlGenerator {

	/**
	 * 用来生成sql
	 * @param keys 关键词集合
	 * @return sql字符串,一行一条
	 */
	public String generateSql(Set<Key> keys) {
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
}
