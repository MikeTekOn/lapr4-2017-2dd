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
 * It is said that each workbook should have their own list of macros. Analysing
 * that, In a first analysis a workbook should have its own list of macros.
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
 * Since macros can be invoked inside a macro, there has to be a new rule:<br/>
 * ref_macro :  
 * LPARRET FUNCTION RPARRET; <br/>
 *
 * Being LPARRET and RPARRET the token corresponding to the char '[' and ']',
 * respectively. Although it is not stated in the requirement, after a small
 * discussion with the client it was decided that the macro name should be
 * within Brackets in order to make the Macro easier to right and to be read.
 *
 * <h3>3.4. What should happen if a macro is called withing the same macro?</h3>
 * Since there is a possibility for this to happen, in the context of a grammar,
 * the recursivity can't be avoided. Having that in mind, the solution should
 * reside in the Java code. There should be a mechanism to detect if the macro
 * has the same name of the one being analised, <b>before</b> the user runs it,
 * and if so, a warning message should be displayed and the user can't run the
 * macro unless that situation is corrected.
 * <h2>4. Design</h2>
 * <p>
 * <h3>4.1. Functional Tests</h3>
 * <p>
 * <h3>4.2. UC Realization</h3>
 * Sequence Diagram
 * <p>
 *
 * <h3>4.3. Classes</h3>
 * <p>
 * <h3>4.4. Design Patterns and Best Practices</h3>
 * <p>
 * <h2>5. Implementation</h2>
 * <p>
 * <p>
 * <h2>6. Integration/Demonstration</h2>
 * <p>
 *
 * <h2>7. Final Remarks</h2>
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
 * <b>Wednesday 31/05/2017</b>
 * <p>
 * Yesterday:
 * <p>
 * Today: 
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
