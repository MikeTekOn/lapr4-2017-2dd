package csheets.core;

import java.util.EventListener;

/**
 * Created by PRenato on 15/06/2017.
 */
public interface HeaderCellListener extends EventListener {

    public void headerValueChanged(int colIndex);
}
