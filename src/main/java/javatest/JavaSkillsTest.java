package javatest;

import java.io.FileReader;
import java.util.Collection;
import java.util.Set;

import javatest.bo.VisitBO;
import javatest.persistence.model.Visit;

public class JavaSkillsTest {

	private static String REQUEST_TYPE_CITY = "CITY";
	private static String REQUEST_TYPE_ID = "ID";
	
	public static void main( String [] args ) throws Exception
	{					
		if(args.length < 3) throw new IllegalArgumentException("3 arguments required but only "+args.length+" present");
		
		String file = args[0];
		String requestType = args[1].trim().toUpperCase();
		String value = args[2].trim().toUpperCase();
		
		VisitBO visitBO = new VisitBO();
		Set<Visit> visits = visitBO.getVisitsFromFile(new FileReader(file));
		
		if(REQUEST_TYPE_CITY.equals(requestType)) {
			Collection<Visit> list = visitBO.filterVisitsByCity(visits, value);
			printPersonsFromVisits(list);
		}else if(REQUEST_TYPE_ID.equals(requestType)) {
			Collection<Visit> list = visitBO.filterVisitsById(visits, value);
			printCitiesFromVisits(list);
		}
	}
	
	
	public static void printCitiesFromVisits(Collection<Visit> visits) {
		for(Visit visit : visits) {
			if(visit!=null && visit.getCity()!=null) {
				System.out.println(visit.getCity().getNom());
			}
		}
	}
	
	public static void printPersonsFromVisits(Collection<Visit> visits) {
		for(Visit visit : visits) {
			if(visit!=null && visit.getPerson()!=null) {
				System.out.println(visit.getPerson().getName()+","+visit.getPerson().getId());
			}
		}
	}
}
