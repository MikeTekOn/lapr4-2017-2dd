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
    
    ///////////////////////////////
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

    /*
 * DATA UPDATES
     */
	public void contentChanged(Cell cell) {
	}
    
    /*
 * IMAGE ACCESSORS
     */
    /**
     * Get the cell's image.
     *
     * @return the image for the cell or <code>null</code> if no
     * user supplied image exists.
     */
    public String getImage() {
        return imagePath;
    }
    //////////////////////////////////
    public List<String> getImages(){
        return imagesPaths;
    }
    
    public void removeImageFromImages(String imagePath){
        for (String imgPath : imagesPaths) {
            if (imgPath.equalsIgnoreCase(imagePath)) {
                imagesPaths.remove(imgPath);
            }
        }
    }

    /**
     * Returns whether the cell has a image.
     *
     * @return true if the cell has a image
     */
    public boolean hasImage() {
        return imagePath != null;
    }
    
    //////////////////////////////////////////////////////
    public boolean hasAnyImage() {
        return imagesPaths != null;
        
    }

    /*
 * IMAGE MODIFIERS
     */
    /**
     * Sets the user-specified image for the cell.
     *
     * @param imagePath the user-specified image
     */
    public void setImage(String imagePath) {
        this.imagePath = imagePath;
        // Notifies listeners
        fireImagesChanged();
    }
    
    ////////////////////////////////////////////
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
     * @throws IOException If any of the usual Input/Output related exceptions
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
