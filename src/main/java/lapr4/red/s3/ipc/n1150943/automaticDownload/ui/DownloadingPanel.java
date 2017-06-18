package lapr4.red.s3.ipc.n1150943.automaticDownload.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by João Cardoso - 1150943 on 13-06-2017.
 */
public class DownloadingPanel extends JFrame {

    JLabel progressLbl;
    JProgressBar progressBar;
    float progress = 0f;

    public DownloadingPanel(String fileName){
        super("DOWNLOADING");
        JPanel p = new JPanel(new GridLayout(3,1));
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        String lblTxt = String.format("Downloading file %s",fileName);
        row1.add(new JLabel(lblTxt));
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        row2.add(progressBar);
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        progressLbl = new JLabel((String.format("Progress: %.2f %%",progress)));
        row3.add(progressLbl);
        p.add(row1);
        p.add(row2);
        p.add(row3);
        this.add(p);
        setSize(350,100);
        setVisible(false);
    }

    public void setProgress(float progress){
        progressLbl.setText(String.format("Progress: %.2f %%",progress));
        progressBar.setValue((int) progress);
    }

}
