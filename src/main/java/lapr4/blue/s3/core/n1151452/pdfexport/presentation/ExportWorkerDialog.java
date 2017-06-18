package lapr4.blue.s3.core.n1151452.pdfexport.presentation;

import javax.swing.*;
import java.awt.*;

/**
 * A dialog to show during the export process
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 18/06/17.
 */
public class ExportWorkerDialog extends JDialog {

    private boolean success = false;

    public ExportWorkerDialog(JFrame parent, PdfOptionsDTO dto) {

        super(parent, "Exporting PDF", false);

        super.setMinimumSize(new Dimension(200, 100));
        super.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JProgressBar bar = new JProgressBar();
        bar.setIndeterminate(true);

        setLayout(new GridLayout(2,1, 20, 20));
        add(new JLabel("Exporting...", JLabel.CENTER));
        add(bar);

//        setFocusable(true);
        pack();
        setLocationRelativeTo(parent);
        super.setVisible(true);
        super.setAlwaysOnTop(false);

    }


    public boolean isSuccess() {
        return success;
    }
}
