package javatest.bo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javatest.persistence.model.Visit;
import javatest.persistence.model.auxiliary.DataLine;
import javatest.utils.parser.DataLineParser;
import mappers.VisitMapper;

public class VisitBO {
	
	private static String FORMAT_LINE_1_TAG = "F1";
	private static String FORMAT_LINE_1_DELIMITER = ",";
	private static String FORMAT_LINE_2_TAG = "F2";
	private static String FORMAT_LINE_2_DELIMITER = ";";
	private static String DATA_LINE_PREFIX = "D ";
	
	private VisitMapper visitMapper = new VisitMapper();
	
	public Set<Visit> getVisitsFromFile(FileReader fileReader) throws FileNotFoundException, IOException, Exception {
		DataLineParser dataLineParser = new DataLineParser(FORMAT_LINE_2_DELIMITER, DATA_LINE_PREFIX);
		Set<Visit> visits = new LinkedHashSet<Visit>();		
		BufferedReader br = new BufferedReader(fileReader);
		String fileRead = br.readLine();
		
		int i = 0;
		while(fileRead != null) {
			fileRead = fileRead.trim();
			if(!fileRead.isEmpty()) {
				if(FORMAT_LINE_1_TAG.equals(fileRead.toUpperCase())) {
					dataLineParser.setDelimiter(FORMAT_LINE_1_DELIMITER);
				}else if(FORMAT_LINE_2_TAG.equals(fileRead.toUpperCase())) {
					dataLineParser.setDelimiter(FORMAT_LINE_2_DELIMITER);
				}else if(fileRead.toUpperCase().startsWith(DATA_LINE_PREFIX)) {
					DataLine dataLine;
					try {
						dataLine = dataLineParser.parseString(fileRead);
					}catch(Exception e){
						throw new ParseException("Error parsing a data line at position "+(i+1), i+1);
					}
					if(dataLine!=null) {
						Visit visit = visitMapper.DataLineToVisit(dataLine);
						visits.add(visit);
					}
				}
			}
			fileRead = br.readLine();
			i++;
		}
		br.close();
		return visits;
	}
	
	public Collection<Visit> filterVisitsByCity(Collection<Visit> visits, String city) {
		Collection<Visit> filteredList = new ArrayList<Visit>();
		if(city!=null) {
			for(Visit visit : visits) {
				if(visit.getCity()!=null && city.equals(visit.getCity().getNom())) {
					filteredList.add(visit);
				}
			}
		}
		return filteredList;
	}
	
	public Collection<Visit> filterVisitsById(Collection<Visit> visits, String id) {
		Collection<Visit> filteredList = new ArrayList<Visit>();
		if(id!=null) {
			for(Visit visit : visits) {
				if(visit.getPerson()!=null && id.equals(visit.getPerson().getId())) {
					filteredList.add(visit);
				}
			}
		}
		return filteredList;
	}
}
