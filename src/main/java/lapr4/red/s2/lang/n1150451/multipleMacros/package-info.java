/**
 * Technical documentation regarding the user story Lang05.2 - Multiple Macros.
 * <p>
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <p>
 * <b>Area Leader: -(yes/no)- no</b>
 *
 * <h2>1. Notes</h2>
 * <p>
 *
 * <h2>2. Requirement</h2>
 * Cleansheets should now support multiple macros. Each macro should have a name
 * and should be associated with an workbook (and also persist with the
 * workbook). The grammar of the macros should also have a mechanism to support
 * the invocation of macros. It only should be possible to invoke macros of the
 * same workbook. Special attention should be devoted to recursion (i.e.,
 * avoiding infinite recursion).
 *
 * <p>
 *
 * <h2>3. Analysis</h2>
 * <p>
 * Having in mind the previous interation, it was fully completed and it is
 * fully funciontaly. With that, it is mandatory to undestand a few points:
 *
 * <h3>3.1. Where will the macro be saved?</h3>
 *
 * It is said that each workbook should have their own list of macros.
 *
 * <p/>
 * <img src="class_diagram_analysis.png" alt="image"/>
 * <p/>
 *
 * <h3>3.2. How is the macro indentified?</h3>
 *
 * A macro is identified by its name, which is introduced by the user. This has
 * two repercussions. The first one is that in the same workbook, since it can
 * have "n" macros, the name needs to be unique. The other repercussios is about
 * the grammar itself. By using the name as the unique attribute, it is going to
 * be also the identifier used in another macro to "invoke" it.
 *
 * <h3>3.3. What is the consequence in the previous grammar?</h3>
 * <p>
 * Since macros can be invoked inside a macro, there has to be a new
 * rule.Although it is not stated in the requirement, after a small discussion
 * with the client it was decided that the macro name should be within "[" and
 * "]" in order to make the Macro easier to right and to be read.
 *
 * <h3>3.4. What should happen if a macro is called within the same macro?</h3>
 * Since there is a possibility for this to happen, in the context of a grammar,
 * the recursivity can't be avoided. Having that in mind, the solution should
 * reside in the Java code. There should be a mechanism to detect if the macro
 * has the same name of the one being analised, <b>before</b> the user runs it,
 * and if so, a warning message should be displayed and the user can't run the
 * macro unless that situation is corrected.
 * <p/>
 *
 * Although it is not stated, it was decided that an option for editing should
 * exist the Macro list window.
 * <h2>4. Design</h2>
 *
 * Unit tests: <br/>
 * testAddMacroTwice: Ensures that it is not possible to add a macro that
 * already exists on the list. <br/>
 * testRemoveMacroWithoutExistence: Ensures that it is not possible to remove a
 * macro that doesn't existe on the list. <br/>
 * testRemoveMacroThatExists: Ensures that the removal on a macro that exists
 * can be made. <br/>
 * testUpdateMacro: Ensures that the macro is updated by testing the removal of
 * the old one.
 * <p>
 * <h3>4.1. Functional Tests</h3>
 * Test plan:
 * Create a macro named 
 * <p>
 * <h3>4.2. UC Realization</h3>
 * Sequence Diagram
 *
 * Since this UC has two main parts, this section will be dividied into two sub
 * sections.
 *
 * <h3>Create/Update/Delete Macro</h3>
 *
 * Since there will be needed more functionalities that the ones provided in the
 * default Java Collection classes, it was decided that a "Macro List" class
 * should be created. Having that in consideration, the following sequence
 * diagrams were made:
 * <p>
 * <img src="diagram_insert_sd.png" alt="image"/>
 * <p>
 * <img src="diagram_remove_sd.png" alt="image"/>
 * <p>
 * <img src="diagram_edit_sd.png" alt="image"/>
 * <p>
 * <h3>Macro Invocation</h3>
 *
 * To support the Invocation of Macros, the previous grammar must be changed.
 * With that, a new rule has to be created to support their "detection". Having
 * decided that the macros needs to be inside "[ ]", the rule should be:
 * <br/><br/>
 * <code>
 * macro_invoked : LPAR_SQUARE (~(LPAR_SQUARE | RPAR_SQUARE))+ RPAR_SQUARE ;
 * </code><br/><br/> This means that the macro should be between a LPAR_SQUARE
 * ("[") and a RPAR_SQUARE ("]"), and, its name, can be anything except the
 * delimiter chars.<br/>
 *
 * Also, a new visitor for the rule should be created. The visitor should be
 * base in the next excerpt of code: <br/> <br/><code>
 * s = new StringBuilder();
 * for (i=0 until childNumber)
 * if (i!=0 and i!=childNumber-1) s.append(child(i));
 * </code> <br/><br/>It is needed to use the cicle, and not just child(1), since
 * the macro name could be a combination of letters and numbers and, in that
 * case, more than one token would be detected.
 *
 * <img src="domain_diagram.png" alt="image"/>
 *
 * <h3>4.3. Classes</h3>
 * <p>
 * Two new classes were made in this iteration. The following is a UML
 * representation of them:
 * <img src="class_diagram.png" alt="image"/>
 *
 * <h3>4.4. Design Patterns and Best Practices</h3>
 * <p>
 * <h2>5. Implementation</h2>
 * <p>
 * Regarding the implementation, a change was made in the previously developed
 * UI. It was added a JComboBox do the dialog in order to choose the macro. The
 * controller was extended in a new class to supported the executeMacro method
 * with the Macro's name as a parameter.
 * <p>
 * Also, since it wasn't possible to extend the class, the MacroList was added
 * as an attribute in the WorkBook class, to reflect the analysis/design made.
 * <p>
 * <h2>6. Integration/Demonstration</h2>
 * <p>
 *
 * <h2>7. Final Remarks</h2>
 * In terms of conclusion, the functionality was made with sucess having the
 * output as required by the Product Owner. Also, it is important to note that
 * two extra features were implemented: the Edition and Deletion of Macros Added
 * to the Workbook. On top of that, the Macro name is also unique.
 * <p>
 *
 * <h2>8. Work Log</h2>
 * <p>
 * <b>Tuesday 06/06/2017</b>
 * <p>
 * Yesterday: our team distributed the funcionalities to be worked on this
 * sprint.
 * <p>
 * Today: I started the analysis process.
 * <p>
 * Blocking:---
 * <p>
 * <b>Wednesday 07/06/2017</b>
 * <p>
 * Yesterday: I ifinshed the analysis for the first part of the UC.
 * <p>
 * Today: Finish the design, tests and if possible the implementation for the
 * first part
 * <p>
 * Blocking:
 * <p>
 * <b>Thursday 1/06/2017 </b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:
 * <p>
 * <b>Friday 2/06/2017</b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:
 * <p>
 * <b>Monday 5/06/2017</b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:
 * <p>
 *
 * <h2>9. Self Assessment</h2>
 *
 * <h3>R3. Rubric Requirements Fulfilment: </h3>
 *
 * <h3>R6. Rubric Requirements Analysis: </h3>
 *
 * <h3>R7. Rubric Design and Implement: </h3>
 *
 *
 * @author Diogo Santos 1150451@isep.ipp.pt
 */
package lapr4.red.s2.lang.n1150451.multipleMacros;
