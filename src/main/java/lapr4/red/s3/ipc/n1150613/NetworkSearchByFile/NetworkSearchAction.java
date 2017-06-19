package lapr4.red.s3.ipc.n1150613.NetworkSearchByFile;

import csheets.ui.ctrl.BaseAction;
import java.awt.event.ActionEvent;
import java.util.Observer;
import lapr4.green.s1.ipc.n1150532.comm.CommUDPClient;

/**
 * An action to perform a UDP broadcast searching for workbooks (by name) open
 * in other instances of CleanSheets.
 *
 * @author Diogo Guedes - 1150613@isep.ipp.pt
 */
public class NetworkSearchAction extends BaseAction {

    /**
     * The action's name.
     */
    private static final String NAME = "Search Network Workbooks";

    /**
     * The reply time out.
     */
    private static final int TIMEOUT = 50000;

    /**
     * The server's port number.
     */
    private final int portNumber;

    /**
     * The observer who will collect the found workbooks.
     */
    private final Observer table;

    /**
     * The name of the workbook to search.
     */
    private final String namePattern;
    
    private final String contentPattern;
    

    /**
     * The full constructor of the action.
     *
     * @param table the table
     * @param portNumber the port number
     * @param workbookName the workbook name
     * @param content
     */
    public NetworkSearchAction(Observer table, int portNumber, String workbookName,String content) {
        this.table = table;
        this.portNumber = portNumber;
        this.namePattern = workbookName;
        this.contentPattern=content;
      
    }

    @Override
    protected String getName() {
        return NAME;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        SearchWorkbookRequestDTO searchRequest = new SearchWorkbookRequestDTO(namePattern,contentPattern);
        CommUDPClient workers = new CommUDPClient(searchRequest, portNumber, TIMEOUT);
        HandlerSearchWorkbookResponseDTO handler = new HandlerSearchWorkbookResponseDTO();
        handler.addObserver(table);
        workers.addHandler(SearchWorkbookResponseDTO.class, handler);
        workers.start();
    }

}
