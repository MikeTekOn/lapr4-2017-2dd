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
 */
package csheets.core;

import eapli.util.Strings;
import java.io.Serializable;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * An address in a spreadsheet that denotes to the location of a cell.
 *
 * @author Einar Pehrson
 */
public class Address implements Comparable<Address>, Serializable {

    /**
     * The unique version identifier used for serialization
     */
    private static final long serialVersionUID = -1096697824364544991L;

    /**
     * The lowest character to be used in a column name
     */
    public static final char LOWEST_CHAR = 'A';

    /**
     * The highest character to be used in a column name
     */
    public static final char HIGHEST_CHAR = 'Z';
    /**
     * The column of the spreadsheet that the address refers to
     */
    private int column;

    /**
     * The row of the spreadsheet that the address refers to
     */
    private int row;

    /**
     * A string representation of the address
     */
    private transient String string;

    /**
     * Creates a new address from the given column and row indices.
     *
     * @param column the column of the address
     * @param row the row of the address
     */
    public Address(int column, int row) {
        this.column = column;
        this.row = row;
    }

    /**
     * Creates a new address from the given column and row indices.
     *
     * @param column The string representation of the column index of the
     * address.
     * @param row The string representation of the row index of the address.
     */
    public Address(String column, String row) {
        this.column = translateColumn(column);
        this.row = translateRow(row);
    }

    /**
     * It translates the string representation shown in the UI to the matching
     * column index.
     *
     * @param column The column String representation.
     * @return It returns the index or throws an IllegalArgumentException if the
     * data is not valid.
     */
    private int translateColumn(String column) {
        if (Strings.isNullOrWhiteSpace(column)) {
            throw new IllegalArgumentException("Missing data.");
        }
        int columnIndex = 0;
        if (!column.matches("^[A-Z]+$")) {
            throw new IllegalArgumentException("The column must contain only uppercase letters.");
        }
        final char[] columnArray = column.toCharArray();
        int arrayIndex = 0;
        while (arrayIndex < columnArray.length) {
            columnIndex += (columnArray[arrayIndex] - LOWEST_CHAR) + (arrayIndex * (HIGHEST_CHAR - LOWEST_CHAR));
            arrayIndex++;
        }
        return columnIndex;
    }

    /**
     * It translates the string representation shown in the UI to the matching
     * row index.
     *
     * @param row The row String representation.
     * @return It returns the index or throws an IllegalArgumentException if the
     * data is not valid.
     */
    private int translateRow(String row) {
        if (Strings.isNullOrWhiteSpace(row)) {
            throw new IllegalArgumentException("Missing data.");
        }
        try {
            return Integer.parseInt(row) - 1;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("The row must be an integer number.");
        }
    }

    /**
     * Returns the column of the address.
     *
     * @return the column of the address
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns the row of the address.
     *
     * @return the row of the address
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns whether the other object is an address with the same column and
     * row coordinates as this address.
     *
     * @param other the object to check for equality
     * @return true if the objects are equal
     */
    public boolean equals(Object other) {
        if (!(other instanceof Address) || other == null) {
            return false;
        }
        Address otherAddress = (Address) other;
        return row == otherAddress.getRow()
                && column == otherAddress.getColumn();
    }

    /**
     * Returns the hash code of the address.
     *
     * @return an int with the column in the 16 high bits, row in the low 16.
     */
    public int hashCode() {
        return (column << 16) + row;
    }

    /**
     * Compares this address with the given address for order. Ordering is first
     * done on the addresses' columns, then on their rows.
     *
     * @param address the address to compared to
     * @return a negative integer, zero, or a positive integer as this object is
     * less than, equal to, or greater than the specified object.
     */
    public int compareTo(Address address) {
        int columnDiff = column - address.getColumn();
        if (columnDiff != 0) {
            return columnDiff;
        } else {
            return row - address.getRow();
        }
    }

    /**
     * Returns a string representation of the address on the form "B22",
     * composed of the letter of the column and number of the row that intersect
     * to form the address.
     *
     * @return a string representation of the address
     */
    public String toString() {
        if (string == null) {
            String columnStr;
            int tempColumn = column;
            for (columnStr = ""; tempColumn >= 0; tempColumn = tempColumn
                    / (HIGHEST_CHAR - LOWEST_CHAR + 1) - 1) {
                columnStr = (char) ((char) (tempColumn % (HIGHEST_CHAR
                        - LOWEST_CHAR + 1)) + LOWEST_CHAR) + columnStr;
            }
            string = columnStr + (row + 1);
        }
        return new String(string);
    }

    public String translateColumnByIndex() {
        char[] charArray = IntStream.rangeClosed('A', 'Z')
                .mapToObj(c -> "" + (char) c).collect(Collectors.joining()).toCharArray();
        String[] finalChar = new String[26 * 26 + 26];

        int cont = 0;
        for (int i = 0; i < charArray.length; i++) {
            finalChar[cont] = String.valueOf(charArray[i]);
            cont++;
        }

        for (int l = 0; l < charArray.length; l++) {

            for (int j = 0; j < charArray.length; j++) {
                finalChar[cont] = charArray[l] + "" + charArray[j];
                cont++;
            }
        }

        return finalChar[getColumn()];
    }


    public int translateRowByIndex() {
        return row+1;
    }

}
