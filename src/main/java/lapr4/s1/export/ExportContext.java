/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.s1.export;

/**
 *
 * @author Diogo Santos and Eric Amaral (1141570)
 */
public class ExportContext {

    ExportStrategy s;

    public ExportContext(ExportStrategy s) {
        this.s = s;
    }

    public boolean executeStrategy() {
        return s.export();
    }
}
