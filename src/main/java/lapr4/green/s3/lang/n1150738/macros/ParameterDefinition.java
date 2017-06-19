package lapr4.green.s3.lang.n1150738.macros;

import eapli.framework.domain.ValueObject;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by henri on 6/18/2017.
 */
public class ParameterDefinition implements ValueObject {

    List<String> params;

    public ParameterDefinition() {
        params = new LinkedList<>();
    }

    public ParameterDefinition(List<String> paramNames) {
        params = new LinkedList();
        paramNames.forEach(params::add);
    }

    public ParameterDefinition addParameter(String name) {
        if (params.contains(name)) {
            throw new IllegalArgumentException("Parameter already exits");
        }
        ParameterDefinition newDefinition = new ParameterDefinition(params);
        newDefinition.params.add(name);
        return newDefinition;
    }

    public ParameterDefinition addParameterFirst(String name){
        if (params.contains(name)) {
            throw new IllegalArgumentException("Parameter already exits");
        }
        ParameterDefinition newDefinition = new ParameterDefinition(params);
        ((LinkedList<String>)newDefinition.params).addFirst(name); //DEQUE behaviour
        return newDefinition;
    }

    public int count() {
        return params.size();
    }

    public String getParameter(int i) {
        return params.get(i);
    }

    public boolean contains(String name) {
        return params.contains(name);
    }

    public void validateParameterList(ParameterList list) throws InvalidParameterList {
        if(this.params.size() != list.size()){
            throw new InvalidParameterList("Invalid parameter list size");
        }
        for(int i= 0; i < list.size(); i++){
            if(!params.contains(list.get(i).id())){
                throw new InvalidParameterList("Invalid parameter "+list.get(i).id());
            }
        }
    }
}
