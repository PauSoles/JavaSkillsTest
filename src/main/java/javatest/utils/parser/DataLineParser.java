package javatest.utils.parser;


import javatest.persistence.model.auxiliary.DataLine;
import javatest.utils.StringUtils;

public class DataLineParser {
	
	private String delimiter;
	private String prefix;

	
	public DataLineParser(String delimiter, String prefix) {
		super();
		this.delimiter = delimiter;
		this.prefix = prefix;
	}

	public DataLine parseString(String str) {
		DataLine result = null;
		if(str.toUpperCase().startsWith(prefix)) {
			str=str.substring(2);
		}
		String[] tokenize = str.split(delimiter);
		String name = tokenize[0].trim();
		String city = tokenize[1].trim().toUpperCase();
		String id =  StringUtils.normalizeAlphaNumeric(tokenize[2].trim().toUpperCase());
		result = new DataLine(name, city, id);
		return result;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}	
}
