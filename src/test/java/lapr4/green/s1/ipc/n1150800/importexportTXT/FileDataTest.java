/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.CellDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pedro
 */
public class FileDataTest {
    
    private CleanSheets cs;
    private Workbook workbook;
    private File workbookFile;
    private Spreadsheet activeSpreadsheet;
    
    private Cell firstCell;
    private Cell lastCell;
    private CellRange cellRange;
    private char separatorCharacter;
    
    private File validFile;
    private FileOutputStream outputStream;
    private BufferedWriter bw;
    
    private File fileToWrite;
    private FileInputStream inputStream;
    private BufferedReader br;
    
    private File invalidFile;
    
    public FileDataTest() {
    }
    
    @Before
    public void setUp() throws FileNotFoundException, IOException {
        cs = new CleanSheets();
        workbook = new Workbook(1);
        workbookFile = new File("workbookFile.cls");
        cs.saveAs(workbook, workbookFile);
        activeSpreadsheet = workbook.getSpreadsheet(0);
        
        firstCell = activeSpreadsheet.getCell(new Address(0, 0));
        lastCell = activeSpreadsheet.getCell(new Address(1, 0));
        cellRange = new CellRange(firstCell, lastCell);
        separatorCharacter = ';';
        
        validFile = new File("file.txt");
        outputStream = new FileOutputStream(validFile);
        bw = new BufferedWriter(new OutputStreamWriter(outputStream));
        String line = "hi;world";
        bw.write(line);
        
        bw.flush();
        bw.close();
        
        fileToWrite = new File("filetowrite.txt");
        
        invalidFile = new File("file.docx");
    }

    /**
     * Test of validateFileExtension method, of class FileData.
     */
    @Test
    public void testValidateFileExtension() {
        System.out.println("validateFileExtension");
        
        /* invalidFile IS NOT OF .txt EXTENSION, THIS SHOULD FAIL */
        boolean expResult = false;
        boolean result = FileData.validateFileExtension(invalidFile);
        assertEquals(expResult, result);
        
        /* validFILE IS OF .txt EXTENSION, THIS SHOULD PASS */
        expResult = true;
        result = FileData.validateFileExtension(validFile);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFileData method, of class FileData.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetFileData() throws Exception {
        System.out.println("getFileData");
        
        FileData instance = new FileData(validFile, separatorCharacter, cellRange, false);
        CellDTO[][] expResult = {{new CellDTO(firstCell.getAddress(), "hi"), new CellDTO(lastCell.getAddress(), "world")}};
        CellDTO[][] result = instance.getFileData(activeSpreadsheet);
        System.out.println(result[0][0].getContent());
        System.out.println(result[0][1].getContent());
        
        assertEquals(expResult[0][0].getAddress(), result[0][0].getAddress());
        assertEquals(expResult[0][0].getContent(), result[0][0].getContent());
        
        assertEquals(expResult[0][1].getAddress(), result[0][1].getAddress());
        assertEquals(expResult[0][1].getContent(), result[0][1].getContent());
    }

    /**
     * Test of fillCells method, of class FileData.
     * @throws java.lang.Exception
     */
    @Test
    public void testFillCells() throws Exception {
        System.out.println("fillCells");
        
        FileData instance = new FileData(validFile, separatorCharacter, cellRange, false);
        CellDTO[][] cellList = instance.getFileData(activeSpreadsheet);
        
        instance.fillCells(cellList);
        
        assertEquals(activeSpreadsheet.getCell(firstCell.getAddress()).getContent(), "hi");
        assertEquals(activeSpreadsheet.getCell(lastCell.getAddress()).getContent(), "world");
    }

    /**
     * Test of getCellsFromRange method, of class FileData.
     * @throws csheets.core.formula.compiler.FormulaCompilationException
     */
    @Test
    public void testGetCellsFromRange() throws FormulaCompilationException {
        System.out.println("getCellsFromRange");
        FileData instance = new FileData(validFile, separatorCharacter, cellRange, false);
        activeSpreadsheet.getCell(firstCell.getAddress()).setContent("hi");
        activeSpreadsheet.getCell(lastCell.getAddress()).setContent("world");
        
        CellDTO[][] expResult = {{new CellDTO(firstCell.getAddress(), "hi"), new CellDTO(lastCell.getAddress(), "world")}};
        CellDTO[][] result = instance.getCellsFromRange(activeSpreadsheet);
        
        assertEquals(expResult[0][0].getAddress(), result[0][0].getAddress());
        assertEquals(expResult[0][0].getContent(), result[0][0].getContent());
        
        assertEquals(expResult[0][1].getAddress(), result[0][1].getAddress());
        assertEquals(expResult[0][1].getContent(), result[0][1].getContent());
    }

    /**
     * Test of setToFile method, of class FileData.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetToFile() throws Exception {
        System.out.println("setToFile");
        CellDTO[][] cellList = {{new CellDTO(firstCell.getAddress(), "hi"), new CellDTO(lastCell.getAddress(), "world")}};
        FileData instance = new FileData(fileToWrite, separatorCharacter, cellRange, false);
        instance.setToFile(cellList);
        
        inputStream = new FileInputStream(fileToWrite);
        br = new BufferedReader(new InputStreamReader(inputStream));
        String line = br.readLine();
        br.close();
        String[] rowData = line.split(String.valueOf(separatorCharacter));
        
        assertEquals(rowData[0], cellList[0][0].getContent());
        assertEquals(rowData[1], cellList[0][1].getContent());
    }
    
    @After
    public void cleanUp() {
        workbookFile.delete();
        validFile.delete();
        fileToWrite.delete();
        invalidFile.delete();
    }
    
}
