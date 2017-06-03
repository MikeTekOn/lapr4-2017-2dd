/**
 * Technical documentation regarding the user story Lang04.1: Insert Function Basic Wizard.
 * <p>
 * JIRA issue: LAPR4E17DD-60
 * 
 * <p>
 * <b>Scrum Master: no</b>
 * 
 * <p>
 * <b>Area Leader: no</b>
 * 
 * <h2>1. Notes</h2>
 * 
 * <h3>Already implemented the button next to the formula bar, which was only requested in sprint 2</h3>
 * 
 * <h2>2. Requirement</h2>
 * <p>
 * Cleansheets should have a menu option to launch a wizard to aid the user in calling functions in formulas.
 * This new window should display a list of possible functions.
 * The construction of this list should be made dynamically based on the properties file and self-description of the functions.
 * When a function is selected in the list its syntax should be displayed in a edit box.
 * The "syntax" should include the name of the functions and its parameters.
 * 
 * 
 * <p>
 * For example, for the factorial function, that only has one parameter, the following text should be displayed in the edit box "= FACT(Number)".
 * The window should also contain an area to display a text describing the selected function (i.e., help text).
 * The window should have an "Apply" and a "Cancel" button.
 * If the user selects the "Apply" button the text of the syntax of the function should be written in the formula bar.
 * <p>
 * 
 * <b>Use Case "Insert Function Basic Wizard":</b>
 * 
 * <p>
 * <img src="ssd_insert_function_basic_wizard.png" alt="image">
 * 
 * <ol>
 * <li>  The user selects "Function Wizard" option.</li> 
 * <li>  The system displays a new window that contains a list of functions.</li> 
 * <li>  The user select a function.</li> 
 * <li>  The system display the syntax and description of selected function.</li> 
 * <li>  The user choose apply option.</li> 
 * <li>  The system close the window and write the syntax function in the formula bar.</li> 
 * </ol>

 * 
 * <h2>3. Analysis</h2>
 * 
 * <p>
 * The user interface of the application it was implemented with an Action.
 * To build this functionality we had to respect this pattern.
 * <p>
 * It was necessary to create a menu option to launch the functionality.
 * <p>
 * The situation with the client was clarified and it was decided that could already be done the allocation of the button next to the formula bar
 * <p>
 * Cliking the button will generate an action, that has the responsability of create the interface with the user.
 * 
 * <p>
 * The diagram below demonstrate what was done:
 * 
 * <p>
 * <img src="cd1_insert_function_basic_wizard.png" alt="image">
 * 
 * <p>
 * After analyzing the code already implemented, it was verified that the responsibility to indicate the list of functions is "Language" class.
 * This class implements an interface - "Function" - several attributes of each function can be accessed, one of which is the description.
 * 
 * <h2>4. Design</h2>
 * 
 * <h3>4.1. Functional Tests</h3>
 * <ol>
 * <li>  Selects Function Wizard option ("fx" button).</li> 
 * <li>  A new window will appear. Choose the function: FACT.</li> 
 * <li>  On the label it will be possible see the description of the function and on edit box the syntax: =FACT(Number)</li>
 * <li>  Change parameter Number to 3.
 * <li>  Then click "Apply" button.</li> 
 * <li>  The system close the window and write the function in the formula bar and show the result: 6.</li> 
 * </ol>
 * 
 * <h3>4.2. UC Realization</h3>
 * 
 * <img src="sd_insert_function_basic_wizard.png" alt="image">
 * 
 * <h3>4.3. Classes</h3>
 * 
 * <p>
 * Class Diagram:
 * <p>
 * <img src="cd2_insert_function_basic_wizard.png" alt="image">
 * 
 * 
 * <h2>5. Implementation</h2>
 * 
 * <p>
 * It was necessary concatenate the syntax of each Function.
 * The code below shows the method of construction:
 * 
 * <pre>
 * {@code 
 *  public class FunctionWizardController {
 *      ...
 *  public String getSyntax(String identifier) throws UnknownElementException{
 *      String aux = EQUAL + identifier + LEFT_PAR;
 *      int count = 0;
 *      for (FunctionParameter fs : Language.getInstance().getFunction(identifier).getParameters()){
 *          if(count == 0){
 *              aux += fs.getName();
 *          }else{
 *              aux += SEMICOLON + fs.getName();
 *          } 
 *          count++;
 *      }
 *      aux += RIGHT_PAR;
 *      return aux;
 *  }
 * }
 * }
 * </pre>
 * <p>
 * It was assumed that it would only be possible to submit a valid formula by clicking on the apply button.
 * Therefore, JoptionPane was implemented to alert the user
 * <pre>
 * {@code 
 *  public class FunctionWizardUI {
 *      ...
 * 
 *  //execute when Apply Button is cliked
 *   private void apply() throws FormulaCompilationException{
 *      try {
 *          String aux = txtSyntax.getText();
 *          if (!aux.isEmpty()){
 *              controller.insertSyntaxFormulaBar(aux);
 *              dispose();
 *          }else{
 *              JOptionPane.showMessageDialog(
 *                  null,
 *                  "Edit box is empty!",
 *                  "Function Wizard",
 *                  JOptionPane.ERROR_MESSAGE);
 *          }  
 *      } catch (FormulaCompilationException | java.lang.IllegalArgumentException i){
 *          JOptionPane.showMessageDialog(
 *                  null,
 *                  "Invalid Formula!\n"
 *                  + "Check if all parameters has valid values!\n\n"
 *                  + "e.g.  = IF( A1 > 2; \"abc\"; \"xpto\") ",
 *                  "Function Wizard",
 *                  JOptionPane.ERROR_MESSAGE);
 *      }
 *   }
 * }
 * }
 * </pre>
 * 
 * <h2>6. Work Log</h2> 
 * 
 * <b>Tuesday</b>
 * <p>
 * I worked on Analysis of the use story
 * 
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Update analysis and started design + functional test
 * 
 * <p>
 * <b>Thursday</b>
 * <p>
 * Implemented almost all code and update design
 * 
 * <p>
 * <b>Friday</b>
 * <p>
 * Finish implementation 
 * 
 * @author Pedro Fernandes 1060503 - 2DD - 2016/17
 */
package lapr4.blue.s1.lang.n1060503.functionWizard;
