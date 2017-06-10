package lapr4.blue.s2.ipc.n1151031.searchnetwork;

import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.HandlerConnectionDetailsRequestDTO;
import lapr4.green.s1.ipc.n1150532.comm.connection.PacketEncapsulatorDTO;

/**
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class HandlerSearchWorkbookRequestDTO implements CommHandler, Serializable {

    private final UIController uiController;

    public HandlerSearchWorkbookRequestDTO(UIController uiController) {
        this.uiController = uiController;
    }

    /**
     * The last DTO received.
     */
    private Object lastReceivedDTO;

    /**
     * It stored the received DTO as the last received DTO. It sends back a
     * SearchWorkbookResponseDTO.
     *
     * @param dto The received DTO. It is suppose to be an
     * SearchWorkbookRequestDTO.
     * @param outStream The output stream to write the reply.
     */
    @Override
    public void handleDTO(Object dto, ObjectOutputStream outStream) {
        lastReceivedDTO = dto;

        PacketEncapsulatorDTO encapsulator = (PacketEncapsulatorDTO) dto;
        SearchWorkbookRequestDTO request = (SearchWorkbookRequestDTO) encapsulator.getDTO();
        String workbookName = request.getWorkbookName();

        //searches for active workbooks with received name pattern
        //returns a list of workbooks found with the name and spreadsheet list
        List<SearchResults> results = new ArrayList();
        Stack<Workbook> activeWorkbooks = uiController.getActiveWorkbooks();
        for (Workbook workbook : activeWorkbooks) {
            if (uiController.getFile(workbook) != null) {
                String name = uiController.getFile(workbook).getName();
                List<Spreadsheet> spreadsheetList = new ArrayList();
                if (name.contains(workbookName) && !workbookName.equals("")) {
                    int numSpreadsheets = workbook.getSpreadsheetCount();
                    for (int i = 0; i < numSpreadsheets; i++) {
                        spreadsheetList.add(workbook.getSpreadsheet(i));
                    }
                    SearchResults searchResult = new SearchResults(name, spreadsheetList, null);
                    results.add(searchResult);
                }
            }
        }

        SearchWorkbookResponseDTO reply = new SearchWorkbookResponseDTO(results);
        try {
            outStream.writeObject(reply);
            outStream.flush();
        } catch (IOException ex) {
            Logger.getLogger(HandlerConnectionDetailsRequestDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * A getter of the last received DTO.
     *
     * @return It returns the last received DTO.
     */
    @Override
    public Object getLastReceivedDTO() {
        return lastReceivedDTO;
    }

}
