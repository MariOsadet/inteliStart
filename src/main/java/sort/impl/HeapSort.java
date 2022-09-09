package sort.impl;

import java.util.Comparator;
import model.Person;
import sort.PersonSort;

public class HeapSort implements PersonSort {

  @Override
  public Person[] sort(Person[] people, Comparator<Person> personComparator) {
    int n = people.length;
    for(int i=n/2-1; i>=0; i--) {
      makeHeap(i, people, personComparator, n);
    }

    for(int i=n-1; i>=0; i--) {
      swap(people, 0, i);
      makeHeap(0, people, personComparator, i);
    }

    return people;
  }

  private static void makeHeap(int parentIndex, Person[] people, Comparator<Person> personComparator, int n){
    Person parentElement = people[parentIndex];
    int leftIndex = parentIndex*2+1;
    int rightIndex = parentIndex*2+2;
    Person biggerElement = parentElement;
    int biggerElementIndex = parentIndex;
    if(leftIndex<n && personComparator.compare(people[leftIndex], biggerElement)>0) {
      biggerElement = people[leftIndex];
      biggerElementIndex = leftIndex;
    }
    if(rightIndex<n &&personComparator.compare(people[rightIndex], biggerElement)>0) {
      biggerElementIndex = rightIndex;
    }
    if(biggerElementIndex != parentIndex) {
      swap(people, parentIndex, biggerElementIndex);
      makeHeap(biggerElementIndex, people, personComparator, n);
    }

  }

  private static void swap(Person[] people, int i, int j) {
    Person temp = people[i];
    people[i] = people[j];
    people[j] = temp;
  }
}
