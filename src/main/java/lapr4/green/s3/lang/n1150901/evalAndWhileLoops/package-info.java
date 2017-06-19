/**
 * Technical documentation regarding the user story <b>Lang01.3 - Eval and While
 * Loops</b>.
 *
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b></p>
 *
 * <p>
 * <b>Area Leader: -(yes/no)- no</b></p>
 *
 * <h2>1. Requirement</h2>
 * <p><b>Statement:</b></p>
 *
 * <p>
 * Add the 'Eval' function. This function has a single parameter that is a
 * string. When executed, this function will 'compile' the formula contained in
 * the only parameter and execute the resulting expression. The result of 'Eval'
 * is the result of the execution of the compiled expression. For example, if we
 * write the following formula '=“ 2 + 3 “' we get the string “2 + 3“ in the
 * cell. However, if we write the formula '= eval (“ 2 + 3 “)' the value
 * obtained in the cell is '5'. Add the following two loop functions: 'DoWhile'
 * and 'WhileDo'. The 'DoWhile' executes the first expression in loop while the
 * second expression evaluates to true. In each iteration of the loop the the
 * first expression is the first to be evaluated. The 'WhileDo' executes the
 * second expression in loop while the first evaluates to true. In each
 * iteration of the loop the the first expression is the first to be evaluated.
 * Example:</p>
 * <pre>
 * {@code
 *  ={@Counter:=1; WhileDo(Eval( “A“&@Counter)> 0; {C1:=C1+Eval(“B“&@Counter); @Counter:=@Counter+1})}
 * }
 * </pre>
 * <p>
 * In this example, the cell C1 will get the sum of all the values of column B
 * in that the corresponding values in column A are greater than zero.</p>
 *
 * 
 * <h2>2. Analysis</h2>
 * <p>This user story can be "divided" in 2 sub-tasks that will be the
 * following:</p>
 *
 * <h3>Implement 'Eval' function:</h3>
 * <p>
 * - The 'eval' function allows to get the result of the execution of a compiled
 * expression.</p>
 *
 * <h3>Implement 'While' loops:</h3>
 * <p>
 * - There should be implemented the loops 'WhileDo' and 'DoWhile' that allow to
 * execute an expression while the other is true.</p>
 * <p>
 *
 * <h2>3. Open Questions</h2>
 * <ol>
 * <li>Do the new functions (WhileDo and DoWhile) need to be inside '{}' to
 * work, or should they be allowed to be used out of '{}'?</li>
 * <li>Should the application allow for the user to introduce this functions
 * with lower and higher case or only with the form specified?</li>
 * </ol>
 * <p>
 *
 * <h2>4. Answers</h2>
 * <ul>
 * <li>1 - No, the loops should be allowed to be used outside of '{}'.</li>
 * <li>2 - The user can write the loop's function's name with lower or upper
 * case.</li>
 * </ul>
 *
 * <h2>5. Notes</h2>
 * <p>The function 'eval' could not be implemented by formula's grammar as the
 * other functions (DoWhile and WhileDo) were, due to conflicts with the
 * previous versions of the grammar that are used in other user storys. By the
 * fact that this function needs strings to be evaluated this would conflict
 * with the way that the strings were implemented on the grammar and could not
 * be fixed without changing work already done in the previous sprints.For this
 * simple fact it was chosen not to change anything already done in the
 * formula's grammar to avoid the risk of "messing up" other user storys. Even
 * though the grammar isn't being used for this function it was still left an
 * implementation of what it would like, this implementation however it's not
 * used as already mentioned before.</p>
 * <p>
 * For this user story to not be completely incomplete it was implemented other
 * form for the function 'eval' that relies that the user can enter any math
 * operation inside quotation marks. If this is performed correctly the 'eval'
 * function will do its job and present the correct results.</p>
 * <p>
 *
 * <h2>6. Functional Tests</h2>
 * <p>These are functional tests, performed to test the performance of the
 * following functions.</p>
 *
 * <p>
 * <b>Test1:</b> ensureEvalStatementExecution</p>
 * <p>
 * There should be a way to verify that the 'eval' function will work
 * correctly.</p>
 * <p>
 * For example:</p>
 * <p>
 * If we enter in a cell:</p>  {@code
 *  =eval("2+3")
 * }
 * <p>
 * The result in the cell where the function was invoked should be 5.</p>
 * <p>
 *
 * <p>
 * <b>Test2:</b> ensureDoWhileLoopExecution</p>
 * <p>
 * There should be a way to verify that the 'DoWhile' loop will work
 * correctly.</p>
 * <p>
 * For example:</p>
 * <p>
 * If we enter in a cell: (Cell A1 = 1)</p>  {@code
 *  =dowhile((A1 := A1+1); A1 < 10)
 * }
 * <p>
 * The result should be that the cell A1 will have the result 10 and the cell
 * where the function was invoked will also have the result 10.</p>
 * <p>
 *
 * <p>
 * <b>Test3:</b> ensureWhileDoLoopExecution</p>
 * <p>
 * There should be a way to verify that the 'WhileDo' loop will work
 * correctly.</p>
 * <p>
 * For example:</p>
 * <p>
 * If we enter in a cell:</p>  {@code
 *  =whiledo(A1 < 10; (A1 := A1+1))
 * }
 * <p>
 * The result should be that the cell A1 will have the result 10 and the cell
 * where the function was invoked will also have the result 10.</p>
 * <p>
 *
 * 
 * <h2>7. Design</h2>
 * <p>From the 2 sub-tasks we get 2 diagrams that are the following:</p>
 *
 * <p>
 * <b>Eval Function</b></p>
 * <p>
 * <img src="us53_design_1.png" alt="design_1"></p>
 *
 * <p>
 * <b>DoWhile Loop</b></p>
 * <p>
 * <img src="us53_design_2.png" alt="design_2"></p>
 *
 * <p>
 * <b>WhileDo Loop</b></p>
 * <p>
 * <img src="us53_design_3.png" alt="design_3"></p>
 *
 * @author Miguel Silva (1150901)
 */
package lapr4.green.s3.lang.n1150901.evalAndWhileLoops;
