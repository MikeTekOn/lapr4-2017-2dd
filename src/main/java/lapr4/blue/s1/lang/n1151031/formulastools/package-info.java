/**
 * Technical documentation regarding the user story Lang03.1: Conditional Formatting of Cells.
 *
 * <b>Scrum Master: - no</b>
 * <p>
 *
 * <b>Area Leader: - no</b>
 *
 * <h2>1. Notes</h2>
 * <p>
 *
 * 2017/05/30 - started the FI analysis.
 *
 * <h2>2. Requirement</h2>
 * Update the "style" extension so that it can be used for the conditional
 * formatting of cells based on the result of the execution of formulas. For the
 * style of a cell to be conditional it must have an associated formula and two
 * formatting styles. One of the styles is applied when the formula evaluates to
 * true and the other when it evaluates to false. The editing of these settings
 * should be done in a sidebar window.
 *
 * <p>
 * <b>Use Case "Conditional Formatting of Cells":</b>
 * The user selects a cell. The system displays a sidebar window with a
 * condition input and style options for both condition outputs (true or false)
 * The user writes the condition and chooses the desired condition styles. The
 * cell will change the style according to the condition output and styles
 * defined.
 * <p>
 * <img src="lang03_1_ssd.png" alt="image">
 *
 * <h2>3. Analysis</h2>
 * For the conditional formatting of cells use case to work, an update to the
 * style extension is needed, so first we need to study how extensions are
 * integrated and how they work. The first sequence diagram in the section
 * <a href="../../../../overview-summary.html#arranque_da_aplicacao">Application
 * Startup</a> tells us that extensions must be subclasses of the Extension
 * abstract class and need to be registered in special files. The class
 * StyleExtension is important for the context of this use case because it is
 * one of the classes that extends the Extension class and it provides a method
 * to make cells stylable. The StylableCell class has the methods setFont(),
 * setForegroundColor(), setBackgroundColor() and setBorder() which could be
 * used to change the cell style. In that case we need to study those methods
 * learn about and how they are used. The described methods are used mainly
 * associated to Actions. The button to change Font, for instance, is associated
 * with the FontAction, which uses the method setFont from the StylableCell
 * class. However, the way the Action is designed implies that everytime we want
 * to make a change to a style of a cell, we have to select the option and
 * choose the font. There is no way to save multiple styling options and apply
 * them all based on a condition associated with the cell. Because of that, a
 * possible solution is to create a new class (ConditionStyle) with the
 * responsabilty of knowing the different characteristics of a user defined
 * style.
 *
 * <h3>ConditionStylableExtension</h3>
 * As stated before, the style extension does not solve the main problems of the
 * condition formatting use case. After studying the project, we think the
 * correct approach is to develop a solution similar to the comments use case.
 * Like with the comments, in which a cell is made "commentable" as it is
 * associated with a comment, for this FI, the idea is similar. A cell should be
 * associated with a condition. The result of the evaluation of that condition
 * is what differs from the mentioned FI. Besides having a condition associated,
 * the style of the cell will change accordingly to the result of the condition
 * evaluation. To solve that problem, we should create a new extension:
 * ConditionStylableExtension. By creating a new extension and a new class with
 * the responsability of "knowing" the user input styles, we should be able to
 * use some of the code in the StylableCell class (the set methods). That way we
 * can add an entire new functionality using part of the code already
 * implemented and overcoming some of the problems mentioned before.
 * <p>
 * <img src="lang03_1_analysis_classes.png" alt="image">
 *
 * <h3>Condition associated with a cell</h3>
 * To make a cell condition stylable, we have to evaluate a user defined
 * condition to true or false. That condition must be associated with a specific
 * cell. After the user writes the condition, it must be read as a string. That
 * string has to be converted to a expression so we can check the result of the
 * condition. However, it is important to notice that we cannot use the
 * FormulaCopiler already developed because in order to compile a formula, we
 * need to send the cell linked to that formula. If we insert a condition over
 * the cell and we evaluate that condition, we will receive an error (#CIRCLE)
 * because it will evaluate the condition over itself. To solve that problem, we
 * have to develop the ConditionStyleCompiler. That way we can compile an
 * expression instead of a formula. Then, with the method evaluate() and
 * toBoolean(), the result will be TRUE or FALSE and we can apply the style to
 * the cell based on that.
 *
 * <h3>First "analysis" sequence diagram</h3>
 * The following diagram depicts a proposal for the realization of the
 * previously described use case. We call this diagram an "analysis" use case
 * realization because it functions like a draft that we can do during analysis
 * or early design in order to get a previous approach to the design. For that
 * reason we mark the elements of the diagram with the stereotype "analysis"
 * that states that the element is not a design element and, therefore, does not
 * exists as such in the code of the application (at least at the moment that
 * this diagram was created).
 * <p>
 * <img src="lang03_1_analysis_basic_sd.png" alt="image">
 *
 * <h2>4. Design</h2>
 * There are two important methods we have to develop, so we will study them in more detail.
 * 
 * <h3>fireConditionChanged() method</h3>
 * <img src="lang03_1_design_fireConditionChanged.png" alt="image">
 * <h3>headerValueChanged() method</h3>
 * <img src="lang03_1_design_valueChanged.png" alt="image">
 * <h2>5. Tests</h2>
 * <ol>
 * <li>ensureConditionIsValidExpression
 * <li>ensureConditionStartsWithEqualSign
 * <li>ensureCellCannotBeEmptyWhenConditionOverItself
 * <li>ensureCellHasCondition
 * <li>ensureConditionCannotBeText
 * <li>ensureConditionCannotBeNumber
 * </ol>
 *
 * <h3>5.1. Functional Tests</h3>
 * <ol>
 * <li>Insert in cell A1 the value "10"
 * <li>Insert in the condition associated with cell A1: "=A1&gt;0"
 * <li>Cells' background becomes green
 * <li>Change cell value to 0
 * <li>Cells' background becomes red
 * </ol>
 *
 * <h2>8. Work Log</h2>
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Project's manual analysis. 2. Studying the requirements.
 * <p>
 * Today
 * <p>
 * 1. Functional increment analysis.
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. FI and general project analysis.
 * <p>
 * Today
 * <p>
 * 1. FI analysis (how to convert a condition from a string to a
 * expression/formula and be able to evaluate it). 2. Start FI design.
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 * <p>
 * <b>Thursday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. FI analysis (how to convert a condition from a string to a
 * expression/formula and be able to evaluate it). 2. Started the FI design.
 * <p>
 * Today
 * <p>
 * 1. Finish FI analysis (how to apply of styles to a cell). 2. Continue/finish
 * FI design. 3. Write Tests.
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 * <p>
 * <b>Friday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Finish FI analysis (how to apply of styles to a cell). 2. Continue/finish
 * FI design. 3. Write Tests.
 * <p>
 * Today
 * <p>
 * 1. Finish design. 2. Start Implementation.
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 * <p>
 * <b>Saturday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Finished design. 2. Started Implementation.
 * <p>
 * Today
 * <p>
 * 1. FI Implementation.
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 * <p>
 *  * <b>Sunday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. FI Implementation.
 * <p>
 * Today
 * <p>
 * 1. FI Implementation finish.
 * 2. Correcting documentation
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 * <p>
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
package lapr4.blue.s1.lang.n1151031.formulastools;
