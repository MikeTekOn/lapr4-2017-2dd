/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Export;

import csheets.core.Cell;
import eapli.util.Strings;
import lapr4.blue.s1.lang.n1151159.macros.Macro;
import lapr4.red.s2.lang.n1150451.multipleMacros.domain.MacroList;
import lapr4.red.s2.lang.n1150623.globalVariables.VarContentor;

/**
 *
 * @author Sofia
 */
public class ActiveCell {
    
    private Cell cell;
    
    public ActiveCell(Cell cell){
        this.cell = cell;
    }
    
    public boolean hasContent(){
        return !Strings.isNullOrEmpty(cell.getContent()) && !Strings.isNullOrWhiteSpace(cell.getContent());
    }
    
    public boolean hasGlobalVariables(VarContentor list){
        return !list.variables().isEmpty();
    }
    
    public boolean hasMacros(MacroList list){
        return !list.getMacroList().isEmpty();
    }
}
