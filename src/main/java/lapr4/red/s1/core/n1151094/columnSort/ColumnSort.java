/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1151094.columnSort;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.compiler.FormulaCompilationException;

/**
 *
 * @author Eduangelo Ferreira 1151094
 */
public class ColumnSort {

    
    private Cell[] column;
    private Value[] values;
    private Value[] helper;
    private char op;
    private int contador = 0;
    private int number;

    /**
     *
     * @param column
     * @throws IllegalValueTypeException
     * @throws FormulaCompilationException
     */
    public ColumnSort(Cell[] column) throws IllegalValueTypeException, FormulaCompilationException {
        if (column == null) {
            throw new IllegalArgumentException();
        }
        contaContent(column);
        this.column = new Cell[contador];
    }

    public void sort(Value[] values) {
        this.values = values;
        number = values.length;
        this.helper = new Value[number];
        mergeSort(0, number - 1);
    }

    private void mergeSort(int low, int high) {
        // check if low is smaller than high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
            // Sort the left side of the array
            mergeSort(low, middle);
            // Sort the right side of the array
            mergeSort(middle + 1, high);
            // Combine them both
            merge(low, middle, high);
        }
    }

    private void merge(int low, int middle, int high) {

        // Copy both parts into the helper array
        for (int i = low; i <= high; i++) {

            helper[i] = values[i];

        }

        int i = low;
        int j = middle + 1;
        int k = low;
        if (getSort() == '1') {
            while (i <= middle && j <= high) {
                if (helper[i].compareTo(helper[j]) <= 0) {
                    values[k] = helper[i];
                    i++;
                } else {
                    values[k] = helper[j];
                    j++;
                }
                k++;
            }
        } else {
            while (i <= middle && j <= high) {
                if (helper[i].compareTo(helper[j]) >= 0) {
                    values[k] = helper[i];
                    i++;
                } else {
                    values[k] = helper[j];
                    j++;
                }
                k++;
            }
        }

        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {

            values[k] = helper[i];
            k++;
            i++;

        }

    }

    /**
     * Este metodo permite selecionar o tipo de ordenação escolhida pelo
     * utilizador
     *
     * @param op pode ser ascending or descending
     */
    public void selectSort(char op) {
        this.op = op;
    }

    /**
     * Devolve o tipo de ordenação escolhida neste caso ascending or descending
     *
     * @return op
     */
    public char getSort() {
        return this.op;
    }

    /**
     * Conta o numero de conteudo das celulas pertence a uma coluna
     *
     * @param vec vec
     * @return contador 
     * @throws IllegalValueTypeException
     */
    private int contaContent(Cell[] vec) throws IllegalValueTypeException {

        for (int i = 0; i < vec.length; i++) {
            if (!vec[i].getContent().equals("")) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Tamanho dos vectores das celulas da coluna
     * @return 
     */
    public int size() {
        return this.contador;
    }

    /**
     * Prenche o vector dos value com os value das celulas da coluna
     * @param column column
     * @param value value
     * @throws FormulaCompilationException
     * @throws IllegalValueTypeException 
     */
    public void fillInVector(Cell[] column, Value[] value) throws FormulaCompilationException, IllegalValueTypeException {

        int conta = 0;
        for (int i = 0; i < column.length; i++) {
            if (!column[i].getContent().equals("")) {
                this.column[conta] = column[i];
                value[conta] = column[i].getValue();
                conta++;
            }

        }
    }

    /**
     * Altera os content das celulas depois da ordenção 
     * @param values Value
     * @throws FormulaCompilationException
     * @throws IllegalValueTypeException 
     */
    public void alterCell(Value[] values) throws FormulaCompilationException, IllegalValueTypeException {
        for (int l = 0; l < column.length; l++) {

            if (values[l].getType() == Value.Type.TEXT) {
                column[l].setContent(values[l].toText());
            } else if (values[l].getType() == Value.Type.NUMERIC) {
                column[l].setContent(String.valueOf(values[l].toNumber()));
            }

        }
    }

}
