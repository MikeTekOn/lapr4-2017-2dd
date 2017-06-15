package lapr4.green.s2.core.n1150532.sort.sortingDTOs;

import csheets.core.*;
import csheets.core.Value;
import csheets.core.formula.compiler.FormulaCompilationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Data Transfer Object to be used on sorting algorithms. It encapsulates a
 * row of cells.
 *
 * @author Manuel Meireles (1150532)
 */
public class RangeRowDTO implements SortableDTO<RangeRowDTO> {

    /**
     * The encapsulated row of cells.
     */
    private final Cell[] cells;

    /**
     * The index on the row of the cell to be compared.
     */
    private int sortingCellIndex;

    /**
     * The full constructor of the DTO.
     *
     * @param theCells The row of cells to encapsulated.
     * @param theSortingCellIndex The index within the row of the cell to be
     * compared.
     */
    public RangeRowDTO(Cell[] theCells, int theSortingCellIndex) {
        cells = theCells;
        sortingCellIndex = theSortingCellIndex;
    }

    /**
     * The value of the cell to be compared.
     *
     * @return It returns the value of the selected cell to be compared.
     */
    public Value getComparableValue() {
        return cells[sortingCellIndex].getValue();
    }

    public boolean containsCellAsPivot(Cell cell)
    {
        return cells[sortingCellIndex].equals(cell);
    }
            
    public void updateSortingColumn(int sortingCellIndex)
    {
        this.sortingCellIndex=sortingCellIndex;
    }
    /**
     * It provides the encapsulated cells.
     *
     * @return It returns the row of cells encapsulated.
     */
    public Cell[] getRow() {
        return cells;
    }

    /**
     * It swaps the content of both RangeRowDTOs. This means that each cell in
     * the row will exchange its content with the cell in the same index from
     * the other RangeRowDTO.
     *
     * @param that The other RangeRowDTO with whom to exchange the cell's
     * content.
     */
    @Override
    public void swap(RangeRowDTO that) {
        Cell[] those = that.getRow();
        if (cells.length != those.length) {
            throw new IllegalArgumentException("Rows have different length.");
        }
        for (int i = 0; i < cells.length; i++) {
            
            String tempContent = cells[i].getContent();
            try {
                cells[i].setContentWithoutFiringEvents(those[i].getContent());
                those[i].setContentWithoutFiringEvents(tempContent);
            } catch (FormulaCompilationException ex) {
                Logger.getLogger(RangeRowDTO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
