package sort.impl;

import java.util.Comparator;
import model.Person;
import sort.PersonSort;

public class InsertionSort implements PersonSort {

  @Override
  public Person[] sort(Person[] people, Comparator<Person> personComparator) {
    for(int i=1; i< people.length; i++) { // c0 * n
      Person person = people[i]; //c1 * (n-1)
      int j = i-1; //c2 *(n-1)
      while(j>=0 && personComparator.compare(person, people[j])<0) { //c3 => (sum(1+2+ 3 ... (n-2)))* c3 => sum =(1+ n-1)* (n-1)/2
        //(n*n -n)* c3/2
        people[j+1] = people[j]; //c4/2 * (n*n -n)
        j--; //c5/2 * (n*n -n)
      }
      people[j+1] = person; //c6  *(n-1)
    }
    return people;
  }
}
//best case(n) = n * (c0 + c1+ c2+ c3+ c6) - c1+ c2+ c3+ c6 = a * n - b => O(n)
//worst case(n) = n * (c0 + c1+ c2+ c6) - c1+ c2+  c6 + (c3 + c4 + c5)*(n*n -n)/2 =  n * n * (c3/2 + c4/2 + c5/2)
// + n * (c0 + c1+ c2+ c6 - c3/2 - c4/2 - c5/2) + c1+ c2+  c6 = a * n*n + b*n + c => O(n*n)

//190, 180, 155, 160, 155
//190, 190, 155, 160, 155
//180, 190, 155, 160, 155
//180, 180, 190, 160, 155
//155, 180, 190, 160, 155
//155, 180, 190, 190, 155
//155, 180, 180, 190, 155
//155, 160, 180, 190, 155
//155, 160, 180, 190, 190
//155, 160, 180, 180, 190
//155, 160, 160, 180, 190
//155, 155, 160, 180, 190

// 1, 2, 3, 4