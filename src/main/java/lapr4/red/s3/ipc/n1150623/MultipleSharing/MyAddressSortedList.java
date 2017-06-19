package lapr4.red.s3.ipc.n1150623.MultipleSharing;

import csheets.core.Address;

import java.util.*;

/**
 * Created by Guilherme Ferreira 1150623 on 18/06/2017.
 */
public class MyAddressSortedList extends ArrayList{

    List<Address> myList;

    public MyAddressSortedList(){
        myList = new ArrayList<>();
    }

    @Override
    public boolean add(Object o){
        Address toAdd = (Address) o;
        boolean added = myList.add(toAdd);
        Collections.sort(myList);
        return added;
    }

    @Override
    public int size(){
        return myList.size();
    }

    @Override
    public Address get(int i){
        return myList.get(i);
    }

}
