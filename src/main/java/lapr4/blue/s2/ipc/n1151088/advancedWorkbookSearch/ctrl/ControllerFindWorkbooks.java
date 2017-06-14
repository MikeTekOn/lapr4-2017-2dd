package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ctrl;

import csheets.core.IllegalValueTypeException;
import csheets.core.Workbook;
import eapli.framework.application.Controller;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.Directory;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.PreviewWorkbookBuilder;

/**
 * @author Diana Silva [1151088@isep.ipp.pt]
 * @author nunopinto
 */
public class ControllerFindWorkbooks implements Controller {

    /**
     * The path to search for cls,etc..files
     */
    private Directory rootPath;

    /**
     * The builder of workbook
     */
    private PreviewWorkbookBuilder previewBuilder;
    private Thread files;

    public ControllerFindWorkbooks(String rootPath) {
        this.rootPath = new Directory(new File(rootPath));
    }

    /**
     * The call to the method to search the files
     */
    public void searchFiles() {
        files = new Thread(rootPath);
        files.start();
    }

    public void stopSearch() {
        if (files != null) {
            files.stop();
        }
    }

    /**
     * The method to load a workbook
     *
     * @param filePath to the workbook to load
     * @return the workbook
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Workbook load(String filePath) throws IOException, ClassNotFoundException {
        return this.rootPath.load(new File(filePath));
    }

    /**
     * The method to load a workbook
     *
     * @param filePath to the workbook to load
     * @return the workbook
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Workbook loadPrev(String filePath) throws IOException, ClassNotFoundException {

        File file = new File(filePath);
        String[][] cellObj = new String[3][3];
        Workbook wb = new Workbook();

        for (int i = 0; i < 9; i++) {
            //Vai buscar o atributo de cada celula
            UserDefinedFileAttributeView view = Files.getFileAttributeView(file.toPath(), UserDefinedFileAttributeView.class);
            ByteBuffer buf = ByteBuffer.allocate(view.size("cell" + i));
            view.read("cell" + i, buf);
            buf.flip();
            String cellValue = Charset.defaultCharset().decode(buf).toString();
            String[] cellInfo = cellValue.split(",");
            int row = Integer.parseInt(cellInfo[0]);
            int col = Integer.parseInt(cellInfo[1]);
            //calcula a coluna e a linha
            //se lenght menor que 3 é porque celula tava vazia entao metemos "" para evitar null
            if (cellInfo.length < 3) {
                cellObj[row][col] = " ";
            } else {
                cellObj[row][col] = cellInfo[2];
            }

        }
        try {
            //so adicionar a spreadsheet preenchida com a matriz
            wb.addSpreadsheet(cellObj);

        } catch (Exception ex) {
            Logger.getLogger(ControllerFindWorkbooks.class.getName()).log(Level.SEVERE, null, ex);
        }

        return wb;

    }
}
