import java.util.Arrays;
import java.util.Comparator;
import model.Person;
import sort.PersonSort;
import sort.impl.HeapSort;
import sort.impl.InsertionSort;
import sort.impl.MergeSort;
import sort.impl.QuickSort;

public class MainClass {

  public static void main(String[] args) {
//    Person[] people = {
//        new Person(190, 86),
//        new Person(180, 80),
//        new Person(155, 55),
//        new Person(160, 60),
//        new Person(155, 65)
//    };
//    PersonSort personSort = new QuickSort();
//    long before = System.currentTimeMillis();
//    Person[] result = personSort.sort(people, Comparator.comparingInt(Person::getWeight));
//    System.out.println(System.currentTimeMillis() - before);
    int[] input1 = {78, 345, 3, 15, 45};
     radixSort(input1);

    for(int item: input1) {
      System.out.println(item);
    }

  }

  private static void radixSort(int[] input) {
    int maxElement = findMaxElement(input);
    int maxRadix = getMaxRadix(maxElement);
    int grade = 1;
    for(int i=0; i<maxRadix; i++) {
      countingSort(input, grade);
      grade = grade *10;
    }
  }

  private static int getMaxRadix(int maxElement) {
    int r = 1;
    while(maxElement>10) {
      maxElement/=10;
      r++;
    }
    return r;
  }

  private static int findMaxElement(int[] input) {
    int max = input[0];
    for(int i=1; i<input.length; i++) {
      max = Math.max(max, input[i]);
    }
    return max;
  }

  private static void countingSort(int[] input, int grade) {
    int range = 10;
    int n=input.length;
    int[] result = new int[n];
    int[] count = new int[range];

    for (int i =0; i<n; i++) {
      int digit = (input[i] /grade) % range; // 1, 2, 2, 1 => 0, 1, 2, ...9 => 0(0), 1(2), 2(2)... => 0(0), 1(2), 2(4)
      count[digit]++;
    }

    for(int i=1; i<range; i++) {
      count[i] = count[i-1] + count[i];
    }

    for(int i=n-1; i>=0; i--) {
      int digit =  (input[i] /grade) % range;
      int position = count[digit];
      result[position -1] = input[i];
      count[digit]--; //fix for radix sort
    }

   for(int i =0; i<n; i++) {
     input[i] = result[i];
   }
  }

}
