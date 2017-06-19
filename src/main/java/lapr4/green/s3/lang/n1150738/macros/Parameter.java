package lapr4.green.s3.lang.n1150738.macros;

import csheets.core.Value;
import eapli.framework.domain.DomainEntity;
import eapli.framework.domain.ValueObject;
import eapli.util.Strings;

/**
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
public class Parameter implements DomainEntity<String> {

    private Value value;
    private String name;

    public Parameter(String name, Value value){
        if(Strings.isNullOrEmpty(name)){
            throw new IllegalArgumentException("Invalid Parameter's name");
        }
        this.name = name;
        this.value = value;
    }


    @Override
    public boolean is(String id) {
        return name.equals(id);
    }

    @Override
    public String id() {
        return name;
    }

    public Value value(){
        return value;
    }

    public void updateValue(Value val){
        this.value = val;
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Parameter parameter = (Parameter) other;

        if (!value.equals(parameter.value)) return false;
        return name.equals(parameter.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parameter parameter = (Parameter) o;
        return name.equals(parameter.name);
    }

    @Override
    public int hashCode() {
        int result = 31 + name.hashCode();
        return result;
    }
}
