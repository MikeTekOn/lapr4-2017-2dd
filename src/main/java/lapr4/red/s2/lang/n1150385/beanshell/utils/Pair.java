package lapr4.red.s2.lang.n1150385.beanshell.utils;

/**
 * Created by pyska on 6/9/17.
 */
public class Pair<T, E> {

    private T key;
    private E value;

    public Pair(T key, E value){
        this.key = key;
        this.value = value;
    }

    public E getValue(){
        return value;
    }
}
