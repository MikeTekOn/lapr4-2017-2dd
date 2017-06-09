package lapr4.green.s2.core.n1150532.sort.algorithms;

import lapr4.green.s2.core.n1150532.sort.sortingDTOs.SortableDTO;

/**
 * A testing DTO to sort integers.
 *
 * @author Manuel Meireles (1150532)
 */
public class SortableIntegerDTO implements SortableDTO<SortableIntegerDTO> {

    /**
     * The encapsulated integer.
     */
    private int myNumber;

    /**
     * The full constructor of the DTO.
     *
     * @param theNumber The integer to be encapsulated.
     */
    public SortableIntegerDTO(int theNumber) {
        myNumber = theNumber;
    }

    /**
     * It provides the encapsulated integer.
     *
     * @return It returns the DTO's integer.
     */
    public int getMyNumber() {
        return myNumber;
    }

    /**
     * It exchanges integers between DTOs.
     *
     * @param theOther The other DTO with whom to exchange the integers.
     */
    @Override
    public void swap(SortableIntegerDTO theOther) {
        int temp = myNumber;
        myNumber = theOther.myNumber;
        theOther.myNumber = temp;
    }

}
