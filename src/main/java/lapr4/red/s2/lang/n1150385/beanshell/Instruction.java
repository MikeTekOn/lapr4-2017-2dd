package lapr4.red.s2.lang.n1150385.beanshell;

/**
 * Atomic object representing a beanshell instruction.
 *
 * Created by pyska on 6/6/17.
 */
public class Instruction {

    /**
     * Types of instructions
     */
    public enum Type { BEANSHELL, MACRO }

    /**
     * Instruction to execute
     */
    String instruction;

    /**
     * This instruction's type
     */
    Type type;

    /**
     * Default constructor
     *
     * @param instruction the instruction value
     * @param type type of instruction
     */
    public Instruction(String instruction, Type type){
        this.instruction = instruction;
        this.type = type;
    }

    /**
     * Returns the instruction
     * @return the instruction
     */
    public String getInstruction(){
        return instruction;
    }

    /**
     * Returns the instruction type
     * @return the instruction type
     */
    public Type getType(){
        return type;
    }

    @Override
    public int hashCode() {
        return 7 * instruction.hashCode() +
                13 * type.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(o != null && o instanceof Instruction){
            Instruction obj = (Instruction)o;
            return instruction.equals(obj.getInstruction()) & type.equals(((Instruction) o).getType());
        }
        return false;
    }
}
