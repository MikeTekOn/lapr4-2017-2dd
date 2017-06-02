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
package lapr4.blue.s1.lang.n1151159.macros.compiler;

/**
 * An exception that is thrown during a macro compilation.
 *
 * @author Ivo Ferro
 */
public class MacroCompilationException extends Exception {

    /**
     * The serialVersionUID of the MacroCompilationException.java
     */
    private static final long serialVersionUID = -6141961615954639105L;

    /**
     * Creates a new macro compilation exception.
     */
    public MacroCompilationException() {
    }

    /**
     * Creates a new macro compilation exception.
     *
     * @param cause the throwable that caused the macro exception to be raised
     */
    public MacroCompilationException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new macro compilation exception.
     *
     * @param message a message that describes what happened
     */
    public MacroCompilationException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of the exception.
     *
     * @return a string representation of the exception
     */
    public String toString() {
        return getMessage();
    }
}