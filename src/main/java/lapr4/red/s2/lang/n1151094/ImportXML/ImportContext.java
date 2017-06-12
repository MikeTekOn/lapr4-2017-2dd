/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1151094.ImportXML;

/**
 *
 * @author Eduangelo Ferreira - 1151094
 */
public class ImportContext {

    ImportStrategy s;

    public ImportContext(ImportStrategy s) {
        this.s = s;
    }

    public boolean executeStrategy() {
        return s.importData();
    }
}
