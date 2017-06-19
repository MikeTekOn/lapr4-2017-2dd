package lapr4.red.s3.ipc.n1150623.MultipleSharing;

import csheets.core.Address;
import csheets.core.Spreadsheet;

/**
 * Created by Guilherme Ferreira 1150623 on 18/06/2017.
 */
public class ShareInfo {

    private String shareName;
    private Address inicial_address_range;
    private Address last_address_range;
    private Spreadsheet spreadsheet;
    private String otherParty;

    protected ShareInfo(String name, Address ini, Address end, Spreadsheet spread, String otherParty){
        this.shareName = name;
        this.inicial_address_range = ini;
        this.last_address_range = end;
        this.spreadsheet = spread;
        this.otherParty = otherParty;
    }

    public static ShareInfo createShareInfo(String name, Address ini, Address end, Spreadsheet spread, String otherParty){
        return new ShareInfo(name, ini, end, spread, otherParty);
    }

    public Spreadsheet spreadsheet(){
        return spreadsheet;
    }

    public String shareName(){
        return shareName;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(shareName).append(": ")
                .append(spreadsheet.getTitle())
                .append(" [")
                .append(inicial_address_range.toString())
                .append(" ,")
                .append(last_address_range.toString())
                .append("];\n")
                .append(" -> ")
                .append(otherParty);

        return s.toString();
    }

}
