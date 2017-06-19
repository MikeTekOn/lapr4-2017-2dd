package lapr4.blue.s3.core.n1141570.insertImage;

import csheets.core.Cell;
import csheets.ext.CellExtension;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eric
 */
public class ImageableCell extends CellExtension {

    /**
     * The unique version identifier used for serialization
     */
    private static final long serialVersionUID = 1L;

    /**
     * The cell's user-specified image path
     */
    private String imagePath;

    /**
     * The list with the images paths
     */
    private List<String> imagesPaths = new ArrayList();

    /**
     * The listeners registered to receive events from the imageable cell
     */
    private transient List<ImageableCellListener> listeners
            = new ArrayList<ImageableCellListener>();

    /**
     * Creates a imageable cell extension for the given cell.
     *
     * @param cell the cell to extend
     */
    public ImageableCell(Cell cell) {
        super(cell, ImagesExtension.NAME);
    }

//    /*
// * DATA UPDATES
//     */
//	public void contentChanged(Cell cell) {
//	}
    /*
 * IMAGE ACCESSORS
     */
    /**
     * Get the cell's image.
     *
     * @return the image for the cell or <code>null</code> if no user supplied
     * image exists.
     */
    public String getImage() {
        return imagePath;
    }

    /**
     * Obtains a list with the images paths of a given cell.
     *
     * @return the list with the images paths of a given cell
     */
    public List<String> getImages() {
        return imagesPaths;
    }

    /**
     * Removes the image passed as parameter from the list of images.
     *
     * @param imagePath the image path to remove
     */
    public boolean removeImageFromImages(String imagePath) {
        boolean flag = true;
        List<String> newImagesPaths = new ArrayList();
        for (String imgPath : imagesPaths) {
            if (!imgPath.equalsIgnoreCase(imagePath)) {
                newImagesPaths.add(imgPath);
            }
        }
        if (imagesPaths.size() == newImagesPaths.size()) {
            flag = false;
        }
        imagesPaths.clear();
        imagesPaths = newImagesPaths;

        return flag;
    }

    /**
     * Obtains true if the cell has any image, false otherwise.
     *
     * @return true if the cell has any image, false otherwise
     */
    public boolean hasAnyImage() {
        return imagesPaths != null && imagesPaths.size() > 0;
    }

    /*
 * IMAGE MODIFIERS

    /**
     * Adds the image to the images list.
     *
     * @param imagePath the image to add to the list
     */
    public void setImageToImages(String imagePath) {
        this.imagesPaths.add(imagePath);
        // Notifies the listeners
        fireImagesChanged();
    }

    /*
 * EVENT LISTENING SUPPORT
     */
    /**
     * Registers the given listener on the cell.
     *
     * @param listener the listener to be added
     */
    public void addImageableCellListener(ImageableCellListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes the given listener from the cell.
     *
     * @param listener the listener to be removed
     */
    public void removeImageableCellListener(ImageableCellListener listener) {
        listeners.remove(listener);
    }

    /**
     * Notifies all registered listeners that the cell's images changed.
     */
    protected void fireImagesChanged() {
        for (ImageableCellListener listener : listeners) {
            listener.imageChanged(this);
        }
    }

    /**
     * Customizes serialization, by recreating the listener list.
     *
     * @param stream the object input stream from which the object is to be read
     * occur
     * @throws ClassNotFoundException If the class of a serialized object cannot
     * be found.
     */
    private void readObject(java.io.ObjectInputStream stream)
            throws java.io.IOException, ClassNotFoundException {
        stream.defaultReadObject();
        listeners = new ArrayList<ImageableCellListener>();
    }

}
