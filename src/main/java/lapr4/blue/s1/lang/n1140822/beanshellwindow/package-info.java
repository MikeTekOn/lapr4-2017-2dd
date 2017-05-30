/**
 * Technical documentation regarding the user story Lang07.1: Bean Shell Window.
 * 
 *
 * <b>Scrum Master: -(yes/no)- no</b>
 * 
 * <p>
 * <b>Area Leader: -(yes/no)- no</b>
 * 
 * <h2>1. Notes</h2>
 * 
 * 
 * <p>
 * -In this section you should register important notes regarding your work during the sprint.
 * For instance, if you spend significant time helping a colleague or if you work in more than a feature.-
 *
 * <h2>2. Requirement</h2>
 * It is required to add support for bean shell scripts. At this moment, only a default script that follows a certain work flow should be added for execution. The result of the script execution should be the result of the last executed instruction.
 * 
 * <p>
 * <b>Use Case "BeanShell Window":</b>  The user selects the beanshell script execution option. The text window is filled with the default script. The user presses the run button. The beanshell script executes and displays the result on the result window.
 * 
 *  
 * <h2>3. Analysis</h2>
 * This use case uses the same extension as the Lang05.1 one. Because of that, the extension implemention details won't be covered in this document.
 * The domain in this use case is tricky. It depends on the script that is written in the bean shell script language, since bean shell supports java objects, and will interact with the current domain classes.
 * As such, in this document, it will be depicted the default script interactions since total coverage of the domain interaction is impossible - the users are the ones that insert the scripts.
 * It will also have a second sequence diagram that is generic - should be plausible for every script creation flow.
 * 
 * <h3>First "analysis" sequence diagram</h3>
 * The following diagram depicts a proposal for the realization of the previously described use case. This is will serve as a draft for the design phase.
 * <p>
 * <img src="analysis_simple_sd.png" alt="image"> 
 * <p>
 * 
 * As we can see, the simple sequence diagram for this use case looks very simplistic. As it was explained before, this is not always true, because beanshell can interact with the domain objects.
 * Even though we are not directly changing the domain and only using it - this is an extension - a domain analysis is still required and mandatory.
 * <h3>Analysis of Core Technical Problem</h3>
 * We can see a class diagram of the domain model of the application <a href="../../../../overview-summary.html#modelo_de_dominio">here</a>.
 * The domain objects we will be interacting with in a larger scale are the Workbook and Cell objects. The changes we are making, and the values which the macros will work with come from these.
 * This requires a thorough study of the implementations of these objects to see how well they can be integrated in bean shell scripts.
 * <p>
 * We want an extensible extension. It is better to create bean shell classes in runtime, it adds extensibility - a lot - and allows for costumization and will also lower the coupling in our application.
 * For that to happen we should delegate this responsability to a class, which the only purpose is to build these dynamic classes, not run them.
 * After the dynamic class is created, it should be returned by the class loader to another class which will then execute the script within the newly constructed class.
 * After the script executes, it should output a result. For now, and since our domain only does mathematical calculations, the result it outputs is pretty straightforward. Even so, the creating of a object which will treat these results should be created, because, should the domain need results other than numeric, it is easier to implement.
 * This justifies the creation of another class its purpose is to treat the results and output in the required format.
 * Of this analysis result the following classes:
 * <p>
 * <p>
 *  BeanShellClassLoaderController
 * <p>
 * <p>
 *  BeanShellClassLoader
 * <p>
 * <p>
 *  BeanShellResult
 * <p>
 * <p>
 * 
 * <h2>4. Design</h2>
 *
 * <h3>4.1. Functional Tests</h3>
 * Although domain class testing is already guaranteed, we need to make sure the inserted script the class loader is trying to build is valid or capture and treat the exception in case it's not. Note that this only verifies for this functional increment.
 * The next one has to run this task asynchronously, which means it wont evaluate the entire script before running it.
 * A valid BeanShellScript is a scrip that is able to be evaluated by the shell and executed without exceptions. Note that the script may compile but throw exceptions in runtime, which makes it invalid.
 * This makes it imperative to code a two step validation: the class has to load correctly and the script has to execute without throwing any exceptions.
 *  <p>
 * Unit test 1 - ensureBeanShellClassInstanceIsBuiltCorrectly
 *  <p>
 *  <p>
 * Unit test 2 - EnsureBeanShellScriptExecutes
 *  <p>
 * -----------------------------------------------------------BELOW IS STILL UNCHANGED----------------------------------------------------------------------------
 * <h3>4.2. UC Realization</h3>
 * To realize this user story we will need to create a subclass of Extension. We will also need to create a subclass of UIExtension. For the sidebar we need to implement a JPanel. In the code of the extension <code>csheets.ext.style</code> we can find examples that illustrate how to implement these technical requirements.
 * The following diagrams illustrate core aspects of the design of the solution for this use case.
 * <p>
 * <b>Note:</b> It is very important that in the final version of this technical documentation the elements depicted in these design diagrams exist in the code!
 * 
 * <h3>Extension Setup</h3>
 * The following diagram shows the setup of the "comments" extension when cleansheets is run.
 * <p>
 * <img src="core02_01_design.png" alt="image">
 * 
 *
 * <h3>User Selects a Cell</h3>
 * The following diagram illustrates what happens when the user selects a cell. The idea is that when this happens the extension must display in the sidebar the comment of that cell (if it exists).
 * <p>
 * <img src="core02_01_design2.png" alt="image">
 * 
 * <h3>User Updates the Comment of a Cell</h3>
 * The following diagram illustrates what happens when the user updates the text of the comment of the current cell. To be noticed that this diagram does not depict the actual selection of a cell (that is illustrated in the previous diagram).
 * <p>
 * <img src="core02_01_design3.png" alt="image">
 * 
 * <h3>4.3. Classes</h3>
 * 
 * -Document the implementation with class diagrams illustrating the new and the modified classes-
 * 
 * <h3>4.4. Design Patterns and Best Practices</h3>
 * 
 * -Describe new or existing design patterns used in the issue-
 * <p>
 * -You can also add other artifacts to document the design, for instance, database models or updates to the domain model-
 * 
 * <h2>5. Implementation</h2>
 * 
 * -Reference the code elements that where updated or added-
 * <p>
 * -Also refer all other artifacts that are related to the implementation and where used in this issue. As far as possible you should use links to the commits of your work-
 * 
 * <h2>6. Integration/Demonstration</h2>
 * 
 * -In this section document your contribution and efforts to the integration of your work with the work of the other elements of the team and also your work regarding the demonstration (i.e., tests, updating of scripts, etc.)-
 * 
 * <h2>7. Final Remarks</h2>
 * 
 * -In this section present your views regarding alternatives, extra work and future work on the issue.-
 * <p>
 * As an extra this use case also implements a small cell visual decorator if the cell has a comment. This "feature" is not documented in this page.
 * 
 * 
 * <h2>8. Work Log</h2> 
 * 
 * -Insert here a log of you daily work. This is in essence the log of your daily standup meetings.-
 * <p>
 * Example
 * <p>
 * <b>Monday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. -nothing-
 * <p>
 * Today
 * <p>
 * 1. Analysis of the...
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on: 
 * <p>
 * 1. ...
 * <p>
 * Today
 * <p>
 * 1. ...
 * <p>
 * Blocking:
 * <p>
 * 1. ...
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
package lapr4.blue.s1.lang.n1140822.beanshellwindow;

