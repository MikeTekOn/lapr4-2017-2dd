package lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate;

import csheets.core.Cell;

import java.util.EventListener;

/**
 * A listener that receives notifications when a cell style listener is changed.
 *
 * @author Ivo Ferro [1151159]
 */
public interface CellStyleListener extends EventListener {

    /**
     * Invoked when a cell style is changed.
     *
     * @param cell the changed cell
     */
    void styleModified(Cell cell);
}
