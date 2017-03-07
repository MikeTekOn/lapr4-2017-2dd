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
package lapr4.gray.s1.lang.n3456789.formula.lang;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import csheets.CleanSheets;
import csheets.core.formula.BinaryOperator;
import csheets.core.formula.Function;
import csheets.core.formula.UnaryOperator;
import csheets.core.formula.lang.NumericFunction;
import csheets.core.formula.lang.UnknownElementException;

import lapr4.gray.s1.lang.n3456789.formula.NaryOperator;

/**
 * A factory for creating certain types of language elements.
 * @author Einar Pehrson
 */
public class Language {

    
        private csheets.core.formula.lang.Language baseInstance=null;
        
    	private static final Language instance = new Language();

	/** The singleton instance */
	//private static final Language instance = new Language();

	/** The name of the file in which language properties are stored */
	//private static final String PROPERTIES_FILENAME = "res/language.props";

	/** The unary operators that are supported by the language */
	//private List<UnaryOperator> unaryOperators = new ArrayList<UnaryOperator>();

	/** The binary operators that are supported by the language */
	private List<BinaryOperator> binaryOperators = new ArrayList<>();

	/** The functions that are supported by the language */
	//private List<Function> functions = new ArrayList<Function>();

       	/** The n-ary operators that are supported by the language */
	private List<NaryOperator> naryOperators = new ArrayList<>();        
 
	/**
	 * Creates a new language.
	 */
	private Language() {
            
                baseInstance=csheets.core.formula.lang.Language.getInstance();
                

                /*
		// Loads properties
		Properties language = new Properties();
		InputStream stream = CleanSheets.class.getResourceAsStream(PROPERTIES_FILENAME);
		if (stream != null) {
			try {
				language.load(stream);
			} catch (IOException e) {
				System.err.println("An I/O error occurred when loading language"
					+ " properties file (" + PROPERTIES_FILENAME + ").");
				return;
			} finally {
				try {
					if (stream != null)
						stream.close();
				} catch (IOException e) {}
			}

			// Loads elements
			for (Object className : language.keySet()) {
				// Loads class and instantiates element
				Class elementClass;
				Object element;
				try {
					elementClass = Class.forName(getClass().getPackage()
						.getName() + "." + (String)className);
					element = elementClass.newInstance();
				} catch (Exception e) {
					// Skip this element, regardless of what went wrong
					continue;
				}

				// Stores element
				if (Function.class.isAssignableFrom(elementClass))
					functions.add(Function.class.cast(element));
				if (BinaryOperator.class.isAssignableFrom(elementClass))
					binaryOperators.add(BinaryOperator.class.cast(element));
				if (UnaryOperator.class.isAssignableFrom(elementClass))
					unaryOperators.add(UnaryOperator.class.cast(element));
                                // Add support for n-ary operators
				if (NaryOperator.class.isAssignableFrom(elementClass))
					naryOperators.add(NaryOperator.class.cast(element));
                                
			}
		} else
			System.err.println("Could not find language properties file ("
				+ PROPERTIES_FILENAME + ").");

		// Loads static methods from java.lang.Math that use double precision
		for (Method method : Math.class.getMethods())
			if (Modifier.isStatic(method.getModifiers()) &&
						method.getReturnType() == Double.TYPE)
				functions.add(new NumericFunction(method));
                */
                
            // IMP: Need to add new binary operators    
            binaryOperators.add(new AssignmentOperator());
            
            // IMP: Need to add new nary operators!!!!
            naryOperators.add(new SequenceOperator());
	}

	/**
	 * Returns the singleton instance.
	 * @return the singleton instance
	 */
	public static Language getInstance() {
		return instance;
	}

	/**
	 * Returns the unary operator with the given identifier.
         * @param identifier identifier
	 * @return the unary operator with the given identifier
         * @throws csheets.core.formula.lang.UnknownElementException throws
	 */
	public UnaryOperator getUnaryOperator(String identifier) throws UnknownElementException {
                return baseInstance.getUnaryOperator(identifier);
                /*
		for (UnaryOperator operator : unaryOperators)
			if (identifier.equalsIgnoreCase(operator.getIdentifier()))
				return operator; // .clone()
		throw new UnknownElementException(identifier);
                */
	}

	/**
	 * Returns the binary operator with the given identifier.
         * @param identifier identifier
	 * @return the binary operator with the given identifier
         * @throws csheets.core.formula.lang.UnknownElementException throws
	 */
	public BinaryOperator getBinaryOperator(String identifier) throws UnknownElementException {
                try {
                    return baseInstance.getBinaryOperator(identifier);
                }
                catch (UnknownElementException e) {
                    for (BinaryOperator operator : binaryOperators)
			if (identifier.equalsIgnoreCase(operator.getIdentifier()))
				return operator; // .clone()
                    throw new UnknownElementException(identifier);
                }
	}

        /**
	 * Returns the n-ary operator with the given identifier.
         * @param identifier identifier
	 * @return the binary operator with the given identifier
         * @throws csheets.core.formula.lang.UnknownElementException throws
	 */
	public NaryOperator getNaryOperator(String identifier) throws UnknownElementException {
		for (NaryOperator operator : naryOperators)
			if (identifier.equalsIgnoreCase(operator.getIdentifier()))
				return operator; // .clone()
		throw new UnknownElementException(identifier);
	}
        
	/**
	 * Returns the function with the given identifier.
         * @param identifier identifier
	 * @return the function with the given identifier
         * @throws csheets.core.formula.lang.UnknownElementException throws
	 */
	public Function getFunction(String identifier) throws UnknownElementException {
                return baseInstance.getFunction(identifier);
            
                /*
		for (Function function : functions)
			if (identifier.equalsIgnoreCase(function.getIdentifier()))
				return function; // .clone()
		throw new UnknownElementException(identifier);
                */
	}

	/**
	 * Returns whether there is a function with the given identifier.
         * @param identifier identifier
	 * @return whether there is a function with the given identifier
	 */
	public boolean hasFunction(String identifier) {
               return baseInstance.hasFunction(identifier);
            
               /*
		try {
			return getFunction(identifier) != null;
		} catch (UnknownElementException e) {
			return false;
		}
               */
	}

	/**
	 * Returns the functions that are supported by the syntax.
	 * @return the functions that are supported by the syntax
	 */
	public Function[] getFunctions() {
                return baseInstance.getFunctions();
                /*
		return functions.toArray(new Function[functions.size()]);
                */
	}
}