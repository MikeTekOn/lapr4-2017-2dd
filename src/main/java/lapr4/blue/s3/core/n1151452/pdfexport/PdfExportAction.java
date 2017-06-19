package lapr4.blue.s3.core.n1151452.pdfexport;

import csheets.CleanSheets;
import csheets.ui.ctrl.FocusOwnerAction;
import lapr4.blue.s3.core.n1151452.pdfexport.presentation.PdfExportUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * A action to launch PDF export
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 18/06/17.
 */
public class PdfExportAction extends FocusOwnerAction {

    /**
     * The parent frame
     */
    private JFrame parent;

    /**
     * Constructs a PDF export action
     *
     * @param frame the parent frame
     */
    public PdfExportAction (JFrame frame) {
        parent = frame;
    }

    @Override
    protected String getName() {
        return "PDF";
    }

    @Override
    protected void defineProperties() {
        putValue(SMALL_ICON, new ImageIcon(CleanSheets.class.getResource("res/img/pdf.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (focusOwner == null) return;
        new PdfExportUI(parent, focusOwner.getSelectedCellsList());
    }
}
