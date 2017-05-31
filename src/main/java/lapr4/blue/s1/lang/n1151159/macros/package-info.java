/**
 * Technical documentation regarding the user story Lang05.1: Macro Window
 *
 * <p>
 * <b>Scrum Master: no</b>
 *
 * <p>
 * <b>Area Leader: no</b>
 *
 *
 *
 * <h2>1. Notes</h2>
 *
 * <p>
 * The functional increment <b>Lang07 - Java Scripting</b> is to be integrated on this one. The user interface is common.
 *
 *
 *
 * <h2>2. Requirement</h2>
 *
 * <p>
 * The user should be able to create and run macros. The macro should be associated with with the current active
 * workbook. For the first iteration is not required to persist the macro.
 *
 * <p>
 * A macro is sequence of instructions that are executed sequential.
 * The formulas are the same that are used in the cells. It can also contain comments.
 *
 * <p>
 * Although the formulas starts with an '=' in the cells,  for the macros it can be omitted.
 *
 * <p>
 * A comment starts with ';', and this line should be ignored for processing.
 *
 * <p>
 * <b>Use Case "Macro Window":</b> The user selects the menu Extensions > Macros > Edit/Execute Macros. The system
 * displays the macros window. The user enters the code for the macro and selects the native CleanSheet language. The
 * system displays the result of the macro (last written instruction). The user presses the run button and the macro is
 * executed.
 *
 *
 *
 * <h2>3. Analysis</h2>
 *
 * <p>
 * A new language should be designed to support the macros.
 * This language should define a recursive grammar that recognize all Cleansheet's formulas and comments.
 *
 *
 *
 * <h2>8. Work Log</h2>
 *
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Project analysis.
 * 3. Studying the requirements.
 * 2. Brainstorming about the work mode.
 * <p>
 * Today
 * <p>
 * 1. Functional increment analysis.
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 * <p>
 *
 *
 * @author Ivo Ferro
 */
package lapr4.blue.s1.lang.n1151159.macros;