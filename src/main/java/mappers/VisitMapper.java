package mappers;

import javatest.persistence.model.City;
import javatest.persistence.model.Person;
import javatest.persistence.model.Visit;
import javatest.persistence.model.auxiliary.DataLine;

public class VisitMapper {
	public Visit DataLineToVisit(DataLine dataLine) {
		Visit visit = null;
		if(dataLine!=null) {
			Person person = new Person(dataLine.getId(),dataLine.getName());
			City city = new City(dataLine.getCity());
			visit = new Visit(person, city);
		}
		return visit;
	}
}
