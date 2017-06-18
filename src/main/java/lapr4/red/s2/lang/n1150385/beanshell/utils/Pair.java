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

    public T getKey(){
        return key;
    }
    
    public E getValue(){
        return value;
    }

    @Override
    public int hashCode() {
        return super.hashCode() +
                7 * key.hashCode() +
                11 * value.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if((o != null) && (o instanceof Pair)){
            Pair obj = (Pair) o;
            return obj.getKey().equals(this.getKey()) && obj.getValue().equals(this.getValue());
        }
        return false;
    }
}
