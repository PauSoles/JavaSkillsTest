package javatest.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javatest.bo.VisitBO;
import javatest.persistence.model.City;
import javatest.persistence.model.Person;
import javatest.persistence.model.Visit;
import javatest.persistence.model.auxiliary.DataLine;
import javatest.utils.parser.DataLineParser;

public class ProjectTest {
	
	VisitBO visitBo = new VisitBO();
	
	@Test
	@DisplayName("Parsing test")
	public void ParsingTest() throws Exception {
		DataLineParser dataLineParser = new DataLineParser(";", "D ");
		DataLine dataLine = dataLineParser.parseString("D Johnathan Cooper ; PARIS ; 10863096-N");
		assertNotNull(dataLine);
    }
	
	@Test
	@DisplayName("Filter by city test")
	public void FilterByCityTest() {
		List<Visit> visits = new ArrayList<Visit>();
		visits.add(new Visit(new Person("11111111A","Pau"), new City("BARCELONA")));
		visits.add(new Visit(new Person("22222222B","Marc"), new City("GIRONA")));
		visits.add(new Visit(new Person("33333333C","Laia"), new City("BARCELONA")));
		visits.add(new Visit(new Person("44444444D","Anna"), new City("LLEIDA")));
		visits.add(new Visit(new Person("55555555E","Josep"), new City("TARRAGONA")));
		visits.add(new Visit(new Person("66666666F","Laura"), new City("BARCELONA")));
		assertEquals(visitBo.filterVisitsByCity(visits, "BARCELONA").size(),3);
		assertEquals(visitBo.filterVisitsByCity(visits, "LLEIDA").size(),1);
	}
}
