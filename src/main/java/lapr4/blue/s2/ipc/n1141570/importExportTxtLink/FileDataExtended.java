
package lapr4.blue.s2.ipc.n1141570.importExportTxtLink;

import csheets.core.Spreadsheet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.CellDTO;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.green.s1.ipc.n1150800.importexportTXT.FileData;

/**
 *
 * @author Eric
 */
public class FileDataExtended extends FileData {
    
    public FileDataExtended(File file, char separatorCharacter, CellRange cellRange, boolean firstLineRepresentsHeaders) {
        super(file, separatorCharacter, cellRange, firstLineRepresentsHeaders);
    }


    
}
