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
     * Creates a new comment controller.
     *
     * @param uiController the user interface controller
     * @param uiPanel the user interface panel
     */
    public InsertImageController(UIController uiController, ImagesPanel uiPanel) {
        this.uiController = uiController;
        this.uiPanel = uiPanel;
    }

    /**
     * Attempts to create a new image from the given string. If successful, adds
     * the image to the given cell. If the input string is empty or null, the
     * image is set to null.
     *
     * @param imageableCell the cell for which the image should be set
     * @param imagePath the image, as entered by the user
     * @return true if the cell's image was changed
     */
    public boolean setImage(ImageableCell imageableCell, String imagePath) {
        // Clears imagePath, if insufficient input
        if (imagePath == null || imagePath.equals("")) {
            imageableCell.setImage(null);
            return true;
        }

        // Stores the image
        imageableCell.setImage(imagePath);
        uiController.setWorkbookModified(imageableCell.getSpreadsheet().getWorkbook());

        return true;
    }
    
    /////////////////////////////////////////////////////////////////////////////////
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
    
    ////////////////////////////////////////////////////////////////////////////////
    public void removeImageFromImages(ImageableCell imageableCell, String imagePath){
        imageableCell.removeImageFromImages(imagePath);
    }
    
 
        /////////////////////////////////////////////////////////////////////////// 
        public void cellSelected2(ImageableCell imageableCell) {
		// Updates the image and validates the image, if any
		if (imageableCell.hasAnyImage()) {
			uiPanel.setUserImage(imageableCell.getImage());
		} else {
			uiPanel.setUserImage("");
		}
	}

    public UIController uiController() {
        return this.uiController;
    }
}
