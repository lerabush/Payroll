package third;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EmployeeComparator implements MyComparator{
    int num;

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
    public EmployeeComparator(){
        num = 0;
    }
    public static List<Employee> sortEmployeeList(MyComparator myComparator, List<Employee> someList){
       List<Employee> unsorted = new ArrayList<>(someList);
       for(int i = 0; i<unsorted.size();i++){
           Employee min = unsorted.get(i);
           int minIndex = i;
           for(int j = i+1;j<unsorted.size()-1;j++){
               if(myComparator.compare(min.getSurname(),(unsorted.get(j)).getSurname())>0){
                   min = unsorted.get(j);
                   minIndex = j;
               }
           }
           if(i!=minIndex){
               Employee tmpEmp = unsorted.get(i);
               unsorted.set(i,min);
               unsorted.set(minIndex,tmpEmp);
           }
       }
       return unsorted;
    }


}
