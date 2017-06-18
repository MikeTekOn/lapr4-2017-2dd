package lapr4.blue.s3.core.n1141570.insertImage.ui;

import csheets.ui.ctrl.UIController;
import lapr4.blue.s3.core.n1141570.insertImage.ImageableCell;

/**
 *
 * @author Eric
 */
public class InsertImageController {

    /**
     * The user interface controller
     */
    private UIController uiController;

    /**
     * User interface panel
     */
    private ImagesPanel uiPanel;

    /**
     * Creates a new insert image controller.
     *
     * @param uiController the user interface controller
     * @param uiPanel the user interface panel
     */
    public InsertImageController(UIController uiController, ImagesPanel uiPanel) {
        this.uiController = uiController;
        this.uiPanel = uiPanel;
    }

    /**
     * Adds the image to the image list.
     *
     * @param imageableCell the imageable cell
     * @param imagePath the image path
     * @return true if the image was added
     */
    public boolean setImageToImages(ImageableCell imageableCell, String imagePath) {
        // Clears imagePath, if insufficient input
        if (imagePath == null || imagePath.equals("")) {
            imageableCell.setImageToImages(null);
            return true;
        }

        // Stores the image
        imageableCell.setImageToImages(imagePath);
        uiController.setWorkbookModified(imageableCell.getSpreadsheet().getWorkbook());

        return true;
    }

    /**
     * Obtains true if image was removed from the images list.
     *
     * @param imageableCell the active imageable cell
     * @param imagePath the image path to remove from images list
     * @return true if image was removed, false otherwise
     */
    public boolean removeImageFromImages(ImageableCell imageableCell, String imagePath) {
        return imageableCell.removeImageFromImages(imagePath);
    }

    /**
     * Updates the images of selected cell.
     *
     * @param imageableCell the selected imageable cell.
     */
    public void cellSelected(ImageableCell imageableCell) {
        // Updates the image and validates the image, if any
        if (imageableCell.hasAnyImage()) {
            uiPanel.setUserImage(imageableCell.getImage());
        } else {
            uiPanel.setUserImage("");
        }
    }

}
