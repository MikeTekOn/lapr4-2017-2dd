package lapr4.blue.s3.core.n1141570.insertImage;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Test class to test ImageableCell.
 *
 * @see ImageableCell
 * @author Eric
 */
public class ImageableCellTest {

    private boolean isNotified = false;

    /**
     * A method that tests the property hasComment.
     */
    @Test
    public void testHasAnyImage() {
        // create a workbook with 2 sheets
        Workbook workbook = new Workbook(2, null);
        Spreadsheet spreadsheet = workbook.getSpreadsheet(0);
        // get the first cell
        Cell cell = spreadsheet.getCell(0, 0);

        // activate the images on the first cell
        ImageableCell imageableCell = new ImageableCell(cell);

        // create a list with images paths
        List<String> imagesPaths = new ArrayList<>();
        imagesPaths.add("imagePath1");
        imagesPaths.add("imagePath2");

        boolean hasAnyImage = imageableCell.hasAnyImage();
        assertTrue(hasAnyImage == false);

        imageableCell.setImageToImages(imagesPaths.get(0));
        imageableCell.setImageToImages(imagesPaths.get(1));

        hasAnyImage = imageableCell.hasAnyImage();
        assertTrue(hasAnyImage);
    }

    /**
     * A method that tests the setter and getter of the user images.
     */
    @Test
    public void testSetGetUserImages() {
        // create a workbook with 2 sheets
        Workbook workbook = new Workbook(2, null);
        Spreadsheet spreadsheet = workbook.getSpreadsheet(0);
        // get the first cell
        Cell cell = spreadsheet.getCell(0, 0);

        // activate the images on the first cell
        ImageableCell imageableCell = new ImageableCell(cell);

        // create a list with images paths
        List<String> imagesPaths = new ArrayList<>();
        imagesPaths.add("imagePath1");
        imagesPaths.add("imagePath2");

        imageableCell.setImageToImages(imagesPaths.get(0));
        imageableCell.setImageToImages(imagesPaths.get(1));

        assertTrue(imagesPaths.equals(imageableCell.getImages()));
    }

    /**
     * A method that tests remove image removes from the images.
     */
    @Test
    public void testRemoveImageFromImages() {
        // create a workbook with 2 sheets
        Workbook workbook = new Workbook(2, null);
        Spreadsheet spreadsheet = workbook.getSpreadsheet(0);
        // get the first cell
        Cell cell = spreadsheet.getCell(0, 0);

        // activate the images on the first cell
        ImageableCell imageableCell = new ImageableCell(cell);

        // create a list with images paths
        List<String> imagesPaths = new ArrayList<>();
        imagesPaths.add("imagePath1");
        imagesPaths.add("imagePath2");

        imageableCell.setImageToImages(imagesPaths.get(0));
        imageableCell.setImageToImages(imagesPaths.get(1));

        imageableCell.removeImageFromImages(imagesPaths.get(1));

        assertFalse(imagesPaths.equals(imageableCell.getImages()));
    }
    
    /**
     * A method that tests remove image removes from the images.
     */
    @Test
    public void testEnsureCellDoesNotHaveImageWhichWasRemoved(){
        // create a workbook with 2 sheets
        Workbook workbook = new Workbook(2, null);
        Spreadsheet spreadsheet = workbook.getSpreadsheet(0);
        // get the first cell
        Cell cell = spreadsheet.getCell(0, 0);

        // activate the images on the first cell
        ImageableCell imageableCell = new ImageableCell(cell);

        // create a list with images paths
        List<String> imagesPaths = new ArrayList<>();
        imagesPaths.add("imagePath1");
        imagesPaths.add("imagePath2");

        imageableCell.setImageToImages(imagesPaths.get(0));
        imageableCell.setImageToImages(imagesPaths.get(1));

        imageableCell.removeImageFromImages(imagesPaths.get(1));
        
        List<String> imagesPaths2 = new ArrayList<>();
        imagesPaths2.add("imagePath1");

        assertTrue(imagesPaths2.equals(imageableCell.getImages()));
    }

    /**
     * A method that tests the notifications for imageable cell listeners.
     *
     * @see ImageableCellListener
     */
    @Test
    public void testCommentableCellListenner() {
        // create a workbook with 2 sheets
        Workbook workbook = new Workbook(2, null);
        Spreadsheet spreadsheet = workbook.getSpreadsheet(0);
        // get the first cell
        Cell cell = spreadsheet.getCell(0, 0);

        // activate the images on the first cell
        ImageableCell imageableCell = new ImageableCell(cell);

        ImageableCellListener listener = new ImageableCellListenerImpl();

        imageableCell.addImageableCellListener(listener);

        // modify the cell... this should create an event
        imageableCell.setImageToImages("ImagePath1");

        assertTrue(isNotified);
    }

    /**
     * A inner utility class used by the method testCommentableCellListenner.
     */
    class ImageableCellListenerImpl implements ImageableCellListener {

        @Override
        public void imageChanged(ImageableCell cell) {
            isNotified = true;
        }
    }

}
