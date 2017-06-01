/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.exportation;

import csheets.core.Cell;
import java.util.List;

/**
 *
 * @author Eric & Diogo 1150451
 */
public interface ExportStrategy {
    public boolean export(List<Cell> list);
}
