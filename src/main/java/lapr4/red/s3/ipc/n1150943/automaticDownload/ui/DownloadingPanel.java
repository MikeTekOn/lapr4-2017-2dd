package lapr4.red.s3.ipc.n1150943.automaticDownload.ui;

import oracle.jrockit.jfr.JFR;
import ui.Notification;

import javax.swing.*;
import java.awt.*;


/**
 * Created by João Cardoso - 1150943 on 13-06-2017.
 */
public class DownloadingPanel extends JFrame {

    public static final long BYTES_PER_SECOND = 5150000;

    JLabel progressLbl;
    JProgressBar progressBar;
    float progress = 0f;

    private DownloadingPanel(String fileName){
        super("DOWNLOADING");
        JPanel p = new JPanel(new GridLayout(3,1));
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        String lblTxt = String.format("Downloading file %s",fileName);
        row1.add(new JLabel(lblTxt));
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        progressBar = new JProgressBar(0,100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        row2.add(progressBar);
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        progressLbl = new JLabel((String.format("Progress: %.2f %%",progress)));
        row3.add(progressLbl);
        p.add(row1);
        p.add(row2);
        p.add(row3);
        this.add(p);
        setLocationRelativeTo(null);
        setSize(350,100);
        setVisible(false);
    }

    private void setProgress(float progress){
        progressLbl.setText(String.format("Progress: %.2f %%",progress));
        progressBar.setValue((int) progress);
    }

    /**
     * Based on average transfer speed (several tests with different file sizes)
     * @param filesize
     */
    private void startDownload(long filesize){
        long totalTime = filesize / BYTES_PER_SECOND;
        int iterationTime = (int) ((totalTime * 1000)/ 100) ; // multiply by 1000 to convert from seconds to miliseconds and divide by 100%
        int currentProgress = 0;
        while(progressBar.getValue()<100){
            try {
                Thread.sleep(iterationTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentProgress ++;
            if(currentProgress>=100){
                setProgress(100);
            }else{
                setProgress(currentProgress);
            }
        }
    }

    public static void startDownloadingPanel(String fileName, long fileSize, boolean visible){
        DownloadingPanel dp = new DownloadingPanel(fileName);

        new SwingWorker<Void, Void>() {
            protected Void doInBackground() throws Exception {
                dp.startDownload(fileSize);
                return null;
            };

        }.execute();

        if(visible){
            dp.setVisible(true);
        }else{
            Notification.notifyHost(dp,"Updating file "+fileName);
        }
    }

}

