/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters.domain;

import csheets.core.Cell;
import java.util.ArrayList;
import java.util.List;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class TableBuilder {

    List<Cell> cells;

    public TableBuilder(List<Cell> cells) {
        this.cells = cells;
    }

    public Table build() {
        List<Cell> bodyCells = new ArrayList();
        List<Header> headerCells = new ArrayList();
        Cell start = cells.get(0);
        Cell end = cells.get(cells.size() - 1);
        CellRange range = new CellRange(start, end);
        int headerRow = start.getAddress().getRow();
        int index = 0;
        int row = -1;
        List<Row> rows = new ArrayList();
        HeaderRow header = new HeaderRow(new ArrayList());
        DataRow dataRow = new DataRow(new ArrayList());
        for (Cell cell : cells) {
            if (cell.getAddress().getRow() == headerRow) {
                header.add(new Header(index, cell));
                index++;
            }
            if (cell.getAddress().getRow() != headerRow) {
                if (row == -1) {
                    rows.add(header);
                    row = cell.getAddress().getRow();
                }
                if (row == cell.getAddress().getRow()) {
                    dataRow.add(cell);

                } else {
                    row = cell.getAddress().getRow();
                    rows.add(dataRow);
                    dataRow = new DataRow(new ArrayList());
                    dataRow.add(cell);
                }
            }
        }
        return new Table(range, rows, new Filter(new ArrayList()));
    }

}
