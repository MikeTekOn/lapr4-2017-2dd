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
 * Even though we are not directly changing and acessing the domain and only using it - this is an extension - a domain analysis is still required and mandatory.
 * <h3>Analysis of Core Technical Problem</h3>
 * We can see a class diagram of the domain model of the application <a href="../../../../overview-summary.html#modelo_de_dominio">here</a>.
 * The domain objects we will be interacting with in a larger scale are the Workbook and Cell objects. The changes we are making, and the values which the macros will work with come from these.
 * This requires a thorough study of the implementations of these objects to see how well they can be integrated in bean shell scripts.
 * <p>
 * We want an extensible extension. It is better to create bean shell script classes in runtime, it adds extensibility - a lot - and allows for costumization and will also lower the coupling in our application.
 * For that to happen we should delegate this responsability to a class, which the only purpose is to build these dynamic classes, not run them.
 * After the dynamic class is created, it should be returned by the class loader to another class which will then execute the script within the newly constructed class.
 * After the script executes, it should output a result. For now, and since our domain only supports mathematical calculations, the result it outputs is pretty straightforward. Even so, the creating of a object which will treat these results should be created, because, should the domain need results other than numeric, it is easier to implement.
 * We will integrate the result that the domain usage outputs into this new class.
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
 *  BeanShellResult
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
 * Unit test 2 - ensureBeanShellScriptExecutes
 *  <p>
 * <p>
 * Unit test 3 - ensureBeanShellScriptFailsIfBadCode
 *  <p>
 *  <p>
 * Unit test 4 - ensureBeanShellClassInstanceIsNotBuiltIfNoCode
 *  <p>
 * <h3>4.2. UC Realization</h3>
 * To realize this functional increment, we will need the classes depicted in the analysis section.
 * In this section there will be two sequence diagrams: one that depicts the generic approach of building any beanshell script, and the other will illustrate the construction and the execution of the default script.
 * In order to reflect the changes on the UI we need to send a reference to the UIController class, which fires events to repaint and update the UI. Since this is a controller, it still respects the MvC pattern, although, the decision to send this object into the deep domain objects (beanshellclassinstance) is due to the fact that beanshell can only return serializable objects.
 * We would have to create a uiController inside the beanshell script (user shouldnt be bothered by this) and extract it off beanshell, then rebuild it on the application side and update our UIController with this one as reference. It is just easier for the user to not get bothered with this.
 * Note that this applies to changes made to the workbook with beanshell scripts. With changes being made with the macros, the acess to the workbook are made inside their implementation. See package-info of lapr4.blue.s1.lang.n1151159.macros.
 * <p>
 * Sequence Diagram 1 - Generic class loading and script executing
 * <p>
 * <p>
 * <img src="design_generic_sd.png" alt="image"> 
 * <p>
 * Sequence Diagram 2 - Default script class loading and executing
 * <p>
 * <img src="design_default_script_sd.png" alt="image"> 
 * <p>
 * 
 * The tricky part is building the script. We will have to read each line of the script file (or textbox in this case) and evaluate each one separatly. That task is realized in the create method of the builder class which is hidden in this diagram (simple text read).
 * As explained, the evaluation of each line of code is executed by the newly  created instance returned by the loader.
 * Note that you can define methods in this scripting language but they have to be defined in the same line of code.
 * About the macros, they are evaluated differently. They are not interpreted by the beanshell interpreter. They are evaluated using the implementation of lapr4.blue.s1.lang.n1151159.macros which is totally independent of this UC.
 * To achieve this, we have to separate the lines of code into two sections: Beanshellcode and Macrocode.
 * <h3>4.3. Classes</h3>
 * 
 * <p>
 * <img src="class_diagram.png" alt="image"> 
 * <p>
 * 
 * <h3>4.4. Design Patterns and Best Practices</h3>
 * 
 * This is an extension, so all extension related patterns are already explained in other documents.
 * In regards to the macros, their specification can be found in lapr4.blue.s1.lang.n1151159.macros.
 * 
 * 
 * <h2>5. Implementation</h2>
 * 
 * Added the planned classes.
 * Added junit testing to guarantee domain rules are enforced.
 * Implemented controller class.
 * Integrated macros with beanshell
 * Integrated beanshell with cleansheets
 * <p>
 * 
 * 
 * <h2>6. Integration/Demonstration</h2>
 * 
 * When integrating, it was verified the changes were not showing appropriatly on the user interface. This led to changes in the implementation which are now reflected in this document.
 * 
 * <h2>7. Final Remarks</h2>
 * Future possible changes and improvements: Total domain interaction as depicted in the CleanSheets API UC, improve reading method to allow method implementations on different lines.
 * <p>
 * As an extra this use case already allows for many scripts to run, and allows unprotected acess (for now) to domain objects.
 * 
 * 
 * <h2>8. Work Log</h2> 
 * 
 * <b>Monday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. -nothing-
 * <p>
 * Today
 * <p>
 * 1. Analysis of the use case and cleansheets application.
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on: 
 * <p>
 * 1. Analysis of the use case and cleansheets application.
 * <p>
 * Today
 * <p>
 * Finishing the analysis and starting the design.
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
* <b>Wednesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Finishing the analysis and starting the design.
 * <p>
 * Today
 * <p>
 * 1.  Finishing the design and started the domain junit testing.
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Thursday</b>
 * <p>
 * Yesterday I worked on: 
 * <p>
 * 1.Finishing the design and started the domain junit testing.
 * <p>
 *  Finished the junit tests and started the implementation.
 * <p>
 * 1. ...
 * <p>
 * Blocking:
* <p>
 * 1. -nothing-
 * <p>
* <b>Friday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Finished the junit tests and started the implementation.
 * <p>
 * Today
 * <p>
 * 1. Continued the implementation.
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Saturday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1.  Continued the implementation.
 * <p>
 * Today
 * <p>
 * 1. Finished the implementation.
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <h2>9. Self Assessment</h2> 
 * 
 * 
 * 
 */
package lapr4.blue.s1.lang.n1140822.beanshellwindow;

