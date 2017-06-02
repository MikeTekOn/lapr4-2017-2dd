/*
 * Copyright (c) 2005 Einar Pehrson <einar@pehrson.nu>.
 *
 * This file is part of
 * CleanSheets - a spreadsheet application for the Java platform.
 *
 * CleanSheets is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * CleanSheets is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CleanSheets; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * ATB (April, 2014): Updated to use antlr3 generated parser and lexer
 * JRT (May, 2017): Uptated to use antlr4
 */
package lapr4.blue.s1.lang.n1151452.formula;

import csheets.core.*;
import csheets.core.formula.Expression;
import lapr4.blue.s1.lang.n1151452.formula.compiler.BlueFormulaLexer;
import lapr4.blue.s1.lang.n1151452.formula.compiler.BlueFormulaParser;
import lapr4.blue.s1.lang.n1151452.formula.compiler.FormulaEvalVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;

/**
 * A test-class for processing formulas from an input stream.
 *
 * @author Einar Pehrson
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 02/06/17. (updated)
 */
public class ConsoleV2 {

    /**
     * Creates a new console for the given input stream.
     *
     * @param in  the input stream from which formulas are read
     * @param out the output stream to which messages are written
     */
    private ConsoleV2(InputStream in, OutputStream out) {
        // Wraps the output stream
        PrintStream printer;
        if (out instanceof PrintStream) {
            printer = (PrintStream) out;
        } else {
            printer = new PrintStream(out);
        }

        // Header
        printer.println("|******************************************|");
        printer.println("|       Test the apps formulas here:       |");
        printer.println("|******************************************|\n\n");
        printer.println("Enter formula here:");

        // Fetches a cell
        Workbook workbook = new Workbook(1);
        Spreadsheet sheet = workbook.getSpreadsheet(0);
        Cell cell = sheet.getCell(new Address(0, 0));

        // Reads and compiles input
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                ANTLRInputStream input = new ANTLRInputStream(line);

                // create the buffer of tokens between the lexer and parser
                BlueFormulaLexer lexer = new BlueFormulaLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);

                BlueFormulaParser parser = new BlueFormulaParser(tokens);
                try {
                    ParseTree tree = parser.expression();

                    FormulaEvalVisitor eval = new FormulaEvalVisitor(cell);
                    Expression expression = eval.visit(tree);
                    printer.println("Formula: " + expression + " = " + expression.evaluate());
                } catch (RecognitionException e) {
                    printer.println(e.getLocalizedMessage());
                } catch (IllegalValueTypeException e) {
                    System.err.println(e);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Creates a new console for the command-line.
     *
     * @param args the command-line arguments (ignored)
     */
    public static void main(String[] args) {
        new ConsoleV2(System.in, System.out);
    }
}
