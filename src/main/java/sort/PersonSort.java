package sort;

import java.util.Comparator;
import model.Person;

public interface PersonSort {

  Person[] sort(Person[] person, Comparator<Person> personComparator);

}
