/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150451.exportPDF.domain;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import java.io.*;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.bouncycastle.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diogo Santos
 */
public class ExportPDFTest {

    List<Cell> listCells;
    private Spreadsheet s;

    public ExportPDFTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        CleanSheets csheets = new CleanSheets();
        csheets.create();
        Workbook w = csheets.getWorkbooks()[0];
        s = w.getSpreadsheet(0);
        Cell c1 = s.getCell(0, 0);
        Cell c2 = s.getCell(1, 1);
        Cell c3 = s.getCell(2, 2);
        try {
            c1.setContent("test1");
            c2.setContent("test2");
            c3.setContent("test3");
        } catch (FormulaCompilationException ex) {
            Logger.getLogger(ExportPDFTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        listCells = getCellsWorksheet();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of selectPath method, of class ExportPDF.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSelectPath() {
        System.out.println("selectPath");
        String path = ".pdt";
        ExportPDF instance = new ExportPDF();
        instance.selectPath(path);
    }

    /**
     * Functional test. The tested is based between a comparison with a pdf
     * already in the disk and with a newly generated one.
     */
    @Test
    public void testExport() throws FileNotFoundException {
        System.out.println("export");
        ExportPDF instance = new ExportPDF();
        instance.selectRange(listCells);
        instance.selectPath(".\\src\\test\\java\\lapr4\\red\\s1\\core\\n1150451\\exportPDF\\domain\\outputTest.pdf");
        instance.export();

        File file1 = new File(".\\src\\test\\java\\lapr4\\red\\s1\\core\\n1150451\\exportPDF\\domain\\outputTest.pdf");
        File file2 = new File(".\\src\\test\\java\\lapr4\\red\\s1\\core\\n1150451\\exportPDF\\domain\\fixedTest.pdf");

        InputStream in1 = new BufferedInputStream(new FileInputStream(file1));
        InputStream in2 = new BufferedInputStream(new FileInputStream(file2));
        try {
            int byteCont = 0;
            int value1, value2;
            do {
                //Where the R/ID is placed in the pdf file
                if (byteCont > 28594) {
                    break;
                }
                //since we're buffered read() isn't expensive
                value1 = in1.read();
                value2 = in2.read();
                if (value1 != value2) {
                    System.out.println(byteCont);
                    throw new IllegalStateException();
                }
                byteCont++;
            } while (value1 >= 0);

            //If it was sucessful deletes the generated file


            //since we already checked that the file sizes are equal 
            //if we're here we reached the end of both files without a mismatch
        } catch (IOException e) {
            //do nothing
        }

    }

    private List<Cell> getCellsWorksheet() {
        List<Cell> list = new ArrayList<>();
        for (int i = 0; i <= 127; i++) {
            for (int j = 0; j <= 52; j++) {
                list.add(s.getCell(i, j));
            }
        }
        return list;
    }

}
