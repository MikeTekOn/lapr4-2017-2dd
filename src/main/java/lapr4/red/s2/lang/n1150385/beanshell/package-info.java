/**
 * Technical documentation regarding the user story Core02.1: Comments on Cells.
 * 
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 * 
 * <p>
 * <b>Area Leader: -(yes/no)- yes</b>
 * 
 * <h2>1. Notes</h2>
 * 
 * <p>
 *
 * <h2>2. Requirement</h2>
 * It should now be possible to invoke Beanshell scripts from macros and formulas using a new function.
 * The new function should be able to execute Beanshell scripts synchronously or asynchronously. If the
 * execution mode is synchronous, then the function should wait for the script to end its execution. In this
 * case the return value of the function should be the value of the last expression of the Beanshell script.
 * If the execution mode is asynchronous then the function should return immediately after launching the
 * execution of the script: the script and the formula/macro will execute in parallel. Existing variables in
 * the macro or formula that executes the script should be accessible inside the Beanshell script.
 * 
 * <p>
 *
 * <h2>3. Analysis</h2>
 *   To add the function described above, we need to understand how functions are parsed and executed. Looking at my
 * colleagues analysis and designs, I find that the function grammars are stored in 'main.antlr4.*'. The grammar is
 * important because is what defines the structure of the script, be it a macro, formula or beanShell, but, since it
 * does not require much work, only knowledge, I will edit the file that has already been created instead of copying
 * the file to a package of mine and then editing the small things that need to be done.
 * <p>
 *     The Visitors are in the packages 'main.java.lapr4.blue.s1.lang.n1151159.macros.compiler' and
 * 'main.java.lapr4.blue.s1.lang.n1151452.formula.compiler' for macros and formulas respectively. The Visitors will
 * then traverse the parsed tree and execute whatever code we place there. The grammar and the Visitors will therefore
 * be of critical importance to the resolution of this use-case.
 * <p>
 *   So now we know where to modify everything grammar related, however we still need to know how to interpret
 * BeanShellScript. Looking through the documentation I find that everything needed is in
 * 'main.java.lapr4.blue.s1.lang.n1140822.beanshellwindow'. Using the BeanShellLoader, we can load the code into a
 * BeanShellInstance. The BeanShellInstance can then be executed and will return a BeanShellResult (with every result
 * for every expression inside the script, even though we only need the last one) from which we can extract the result
 * by calling the lastResult() function;
 * <p>
 *   Since all else is made and functional, this is all the analysis needed.
 * <p>
 *
 * <h2>4. Design</h2>
 *   In order for the shell code not be confused by any other element in the text to be parsed, a special code syntax
 * will be needed. I took inspiration from XML's <i>&lt![CDATA[ ... ]]&gt</i> approach and created two very similar
 * tags, one for synchronous BeanShell code, and another for asynchronous. The synchronous tag is <i>&lt[SHELL[</i> and
 * the asynchronous tag is <i>&lt![SHELL[</i>, and this is the syntax used in both the macros and the formulas
 * grammar.
 * <p>
 *   Notice that the only difference between the two tags is the existence of an exclamation mark before the first
 * right parentheses. This makes it easy to memorize.
 * <p>
 *   Now the visitors have to be updated. Here is what the EvalVisitor for the BeanShell code looks like:
 * <p>
 *     <img src="lang07_2_design.png" alt="image">
 * <p>
 *   The BeanShellInstance created cannot be executed at this stage because we are only still parsing the text. For it
 * to be run at the correct time, it needs to be able to be evaluated in the correct sequence, just like every other
 * expression in the parsing tree. Because of this, there is a need for this class to now implement the 'Expression'
 * interface.
 * <p>
 *   The behaviour of the evaluate method will be like so, for both Macros and Formulas:
 * <p>
 *     <img src="lang07_2_design2.png" alt="image">
 * <p>
 *
 * <h2>5. Work Log</h2>
 * 
 * <p>
 * <b>Monday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. nothing
 * <p>
 * Today
 * <p>
 * 1. Group presentation of core functionalities
 * <p>
 * Blocking:
 * <p>
 * 1. nothing
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on: 
 * <p>
 * 1. Group presentation of core functionalities
 * <p>
 * Today
 * <p>
 * 1. Analysis of the use-case
 * <p>
 * Blocking:
 * <p>
 * 1. nothing
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Analysis of the use-case
 * <p>
 * Today
 * <p>
 * 1. Making some code experimentations for better understanding of the domain model
 * <p>
 * Blocking:
 * <p>
 * 1. nothing
 * <p>
 * <b>Thursday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Making some code experimentations for better understanding of the domain model
 * <p>
 * Today
 * <p>
 * 1. Started and finished making design, implementation and tests
 * <p>
 * Blocking:
 * <p>
 * 1. nothing
 * <p>
 *
 *
 * <h2>9. Self Assessment</h2> 
 * 
 * -Insert here your self-assessment of the work during this sprint regarding Rubrics R3, R6 and R7.-
 * 
 * <h3>R3. Rubric Requirements Fulfilment: 3</h3>
 * 
 * 3- some defects. The student did fulfil all the requirements and also did justify the eventual options related to the interpretation/analysis of the problem.
 * 
 * <h3>R6. Rubric Requirements Analysis: 4</h3>
 * 
 * 4- correct. There is a robust and very complete analysis of the problem with well chosen technical artifacts (diagrams, grammars, etc.) for its documentation and without errors.
 * 
 * <h3>R7. Rubric Design and Implement: 2</h3>
 * 
 * 2- many defects. The code follows good practices although some design patterns should have been applied. The technical documentation covers the majority of the solution although it may have some errors. However the appropriate type of technical artifacts for documenting design are present and the ideia behind the solution is understandable. Code does not "goes against" the design options of the original code of the project. Unit tests seem to cover a significant amount of functionalities (e.g., more than 50%) but there was not test first approach.
 * 
 * @author alexandrebraganca
 */
package lapr4.red.s2.lang.n1150385.beanshell;

