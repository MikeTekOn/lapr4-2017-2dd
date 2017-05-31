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
 * Update the "style" extension so that it can be used for the conditional formatting of cells based on the result of the execution of formulas. 
 * For the style of a cell to be conditional it must have an associated formula and two formatting styles. 
 * One of the styles is applied when the formula evaluates to true and the other when it evaluates to false. 
 * The editing of these settings should be done in a sidebar window.
 * 
 * <p>
 * <b>Use Case "Conditional Formatting of Cells":</b> 
 * The user selects a cell and chooses the Conditional Formatting option.
 * The system displays a sidebar window with a formula input and style options for both condition outputs (true or false)
 * The user chooses the desired options.
 * The system saves the conditional formatting of the cell.
 *  
 * <h2>3. Analysis</h2>
 * For the conditional formatting of cells use case to work, an update to the style extension is needed, so first we need to study how extensions are integrated and how they work.
 * The first sequence diagram in the section <a href="../../../../overview-summary.html#arranque_da_aplicacao">Application Startup</a> tells us that extensions must be subclasses of the Extension abstract class and need to be registered in special files.
 * The class StyleExtension is important for the context of this use case because it is one of the classes that extends the Extension class and it provides a method to make cells stylable.
 * It also implements the method getUIExtension which returns an instance of a StyleUIExtension (extends UIExtension) that will provide the method to get the sidebar for the conditional formatting of cells use case.
 * 
 * <h3>First "analysis" sequence diagram</h3>
 * The following diagram depicts a proposal for the realization of the previously described use case. 
 * We call this diagram an "analysis" use case realization because it functions like a draft that we can do during analysis or early design in order to get a previous approach to the design. 
 * For that reason we mark the elements of the diagram with the stereotype "analysis" that states that the element is not a design element and, therefore, does not exists as such in the code of the application (at least at the moment that this diagram was created).
 * <p>
 * <img src="lang03_1_analysis_basic_sd" alt="image"> 
 * <p>
 * 
 * From the previous diagram, although it is very basic, we can already see that an update to the StylableCell should be made in order for it to support the definition of the new styles associated with the conditional formatting of cells use case.
 * The StyleUIExtension should also be updated with the getSidebar method, which will call the sidebar with the components allowing the user to insert the formula and define the condition styles.
 * 
 * <h2>8. Work Log</h2> 

 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Project's manual analysis.
 * 2. Studying the requirements.
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
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
package lapr4.blue.s1.lang.n1151031.formulastools;
