package lapr4.blue.s3.core.n1141570.insertImage;

import java.util.EventListener;

/**
 *
 * @author Eric
 */
public interface ImageableCellListener extends EventListener {

    /**
     * Invoked when an image is added to or removed from a cell.
     *
     * @param cell the cell that was modified
     */
    public void imageChanged(ImageableCell cell);

}
