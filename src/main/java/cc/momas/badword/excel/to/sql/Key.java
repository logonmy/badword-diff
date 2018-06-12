package cc.momas.badword.excel.to.sql;

import java.util.Date;

public class Key {
	
	public static final int VERB = 0;
	public static final int NOUN = 2;
	public static final int KEYWORD = 4;
	
	private Integer id;
	private String word;
	private Integer type;
	private Date created;
	private int wordType;// 0是名词，2是动词，4是专属词语

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getWordType() {
		return wordType;
	}

	public void setWordType(Integer wordType) {
		this.wordType = wordType;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		result = prime * result + wordType;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Key other = (Key) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		if (wordType != other.wordType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Key [id=" + id + ", word=" + word + ", type=" + type
				+ ", created=" + created + ", wordType=" + wordType + "]";
	}

}
