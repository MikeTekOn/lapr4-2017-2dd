package lapr4.red.s3.ipc.n1151094.networkExplorer;

import java.io.Serializable;

/**
 * Created by Eduangelo Ferreira on 16/06/2017.
 */
public class CleansheetResponseDTO implements Serializable {

    public NetworkExplorerDTO cleanSheets;

    public CleansheetResponseDTO(NetworkExplorerDTO cleanSheets) {

        this.cleanSheets = cleanSheets;
        
    }
}
