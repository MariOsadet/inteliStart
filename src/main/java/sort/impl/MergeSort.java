package sort.impl;

import java.util.Comparator;
import model.Person;
import sort.PersonSort;

public class MergeSort implements PersonSort {

  @Override
  public Person[] sort(Person[] people, Comparator<Person> personComparator) {
     mergeSort(0, people.length-1, people, personComparator);
     return people;
  }

  static private void mergeSort(int begin, int end, Person[] people, Comparator<Person> personComparator) {
    if(begin<end) {
      int mid = (begin+end)/2; //
      mergeSort(begin, mid, people, personComparator); //
      mergeSort(mid+1, end, people, personComparator); // 4 => 2 2
      merge(begin, mid,  end, people, personComparator); // n+ 2*(n/2 + n/2) + 4 * (n/4 + n/4).. => n + n + ... n = log2(n) * n
    }
  }

  private static void merge(int begin, int mid, int end, Person[] people, Comparator<Person> personComparator) {
    int n1 = mid - begin + 1;
    int n2 = end-mid;
    Person[] left = new Person[n1];
    Person[] right = new Person[n2];
    for(int i=0; i<n1; i++) {
      left[i] = people[begin + i];
    }

    for(int j=0; j<n2; j++) {
      right[j] = people[mid +j+1];
    }

    int k=begin;
    int i=0;
    int j=0;

    while (i<n1 && j<n2) {
      if(personComparator.compare(left[i], right[j])<=0) {
        people[k] = left[i];
        i++;
      } else{
        people[k] = right[j];
        j++;
      }
      k++;
    }

    while(i<n1) {
      people[k] = left[i];
      i++;
      k++;
    }

    while(j<n2) {
      people[k] = right[j];
      j++;
      k++;
    }
  }
}
