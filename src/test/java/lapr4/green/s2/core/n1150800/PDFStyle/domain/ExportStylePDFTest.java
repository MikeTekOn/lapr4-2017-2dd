/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150800.PDFStyle.domain;

import com.itextpdf.text.BaseColor;
import csheets.core.Cell;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class ExportStylePDFTest {
    
    @Before
    public void setUp() {
    }

    /**
     * Test of manageBorders method, of class ExportStylePDF.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureBorderLineIsChosen() {
        System.out.println("manageBorders");
        boolean solidBorder = false;
        boolean dottedBorder = false;
        boolean noBorder = false;
        BaseColor borderColor = new BaseColor(BaseColor.RED.getRGB());
        ExportStylePDF instance = new ExportStylePDF();
        instance.manageBorders(solidBorder, dottedBorder, noBorder, borderColor);
    }
    
    @After
    public void cleanUp() {
    }
}
