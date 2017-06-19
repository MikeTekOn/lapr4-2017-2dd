package lapr4.green.s3.lang.n1150738.macros;

import csheets.core.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
public class ParameterList {

    private List<Parameter> params;

    public ParameterList() {
        params = new ArrayList();
    }

    public void addParameter(Parameter p) {
        if (params.contains(p)) {
            throw new IllegalArgumentException("Parameter already Exists");
        }
        params.add(p);
    }


    public int size() {
        return params.size();
    }

    public Parameter get(int idx) {
        return params.get(idx);
    }

    public Value getParameterValue(String id) {
        for (Parameter p : params) {
            if(p.id().equals(id)){
                return p.value();
            }
        }
        return null;
    }
}
