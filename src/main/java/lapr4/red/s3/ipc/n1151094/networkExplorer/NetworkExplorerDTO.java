package lapr4.red.s3.ipc.n1151094.networkExplorer;

import csheets.core.Workbook;
import java.io.Serializable;
import java.net.InetAddress;

public class NetworkExplorerDTO implements Serializable {

    public InetAddress address;
    public String[][] listExtensionActivate;
    public String[][] listExtensionDesactivate;
    public Workbook work;

    public NetworkExplorerDTO(InetAddress address, Workbook work, String[][] listExtensionActivate, String[][] listExtensionDesactivate) {
        this.address = address;
        this.work = work;
        this.listExtensionActivate = listExtensionActivate;
        this.listExtensionDesactivate = listExtensionDesactivate;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public InetAddress getAdrress() {

        return this.address;
    }

    public void setListExtensionDesactivate(String[][] listExtensionDesactivate) {
        this.listExtensionDesactivate = listExtensionDesactivate;
    }

    public String[][] getListExtensionDesactivate() {
        return listExtensionDesactivate;
    }

    public String[][] getListExtensionActivate() {
        return listExtensionActivate;
    }

    public void setListExtensionActivate(String[][] listExtensionActivate) {
        this.listExtensionActivate = listExtensionActivate;
    }

    public Workbook getWork() {
        return work;
    }

    public void setWork(Workbook work) {
        this.work = work;
    }
}
