/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150901.search.workbook;

import csheets.core.Workbook;
import eapli.framework.dto.DTO;
import java.io.Serializable;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class ResponseWorkbookDTO implements Serializable, DTO {

    private final String workbookName;

    public ResponseWorkbookDTO(String wbName) {
        workbookName = wbName;
    }

    public String getWorkbookName() {
        return workbookName;
    }
}
