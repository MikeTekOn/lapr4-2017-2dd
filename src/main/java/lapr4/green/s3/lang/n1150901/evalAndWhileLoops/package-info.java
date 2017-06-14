/**
 * Technical documentation regarding the user story Lang01.3 - Eval and While Loops.
 *
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 * </p>
 *
 * <p>
 * <b>Area Leader: -(yes/no)- no</b>
 * </p>
 *
 * <p>
 * <h2>1. Notes</h2>
 * No notes to be reported.
 * </p>
 *
 * <p>
 * <h2>2. Requirement</h2>
 * <b>Statement:</b>
 * </p>
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
 * Example: '= {@Counter:=1; WhileDo(Eval( “A“&@Counter)> 0; {C1:=C1+Eval(“B“&@Counter); @Counter:=@Counter+1})}'. In this example, the cell C1 will get the sum of
 * all the values of column B in that the corresponding values in column A are
 * greater than zero.
 * </p>
 *
 * <p>
 * <h2>3. Analysis</h2>
 * This user story can be "divided" in 2 sub-tasks that will be the following:
 * </p>
 * <p>
 * <h3>Implement 'Eval' function:</h3>
 * - The 'eval' function allows to get the result of the execution of a compiled
 * expression.
 * </p>
 * <p>
 * <h3>Implement 'While' loops:</h3>
 * - There should be implemented the loops 'WhileDo' and 'DoWhile' that allow to
 * execute an expression while the other is true.
 * </p>
 *
 * <p>
 * <h2>4. Open Questions</h2>
 * <ol>
 * <li>Do the new functions (WhileDo and DoWhile) need to be inside '{}' to
 * work, or should they be allowed to be used out of '{}'?</li>
 * <li>Should the application allow for the user to introduce this functions
 * with lower and higher case or only with the form specified?</li>
 * </ol>
 * </p>
 *
 * <p>
 * <h2>5. Answers</h2>
 * <ul>
 * <li>1 - No, the loops should be allowed to be used outside of '{}'.</li>
 * <li>2 - The user can write the loop's function's name with lower or upper
 * case.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <h2>6. Assumptions</h2>
 * <ol>
 * <li></li>
 * </ol>
 * </p>
 *
 * <p>
 * <h2>7. Unit Tests</h2>
 * These are unit tests, performed to test the consistency of the user story.
 * </p>
 * <p>
 * <b>Test1:</b> ensureEvalStatementExecution
 * </p>
 * <p>
 * There should be a way to verify that the 'eval' function will work correctly.
 * </p>
 * <p>
 * <b>Test2:</b> ensureDoWhileLoopExecution
 * </p>
 * <p>
 * There should be a way to verify that the 'DoWhile' loop will work correctly.
 * </p>
 * <p>
 * <b>Test3:</b> ensureWhileDoLoopExecution
 * </p>
 * <p>
 * There should be a way to verify that the 'WhileDo' loop will work correctly.
 * </p>
 *
 * <p>
 * <h2>8. Functional Tests</h2>
 * These are functional tests, performed manually in the UI.
 * </p>
 *
 * @author Miguel Silva (1150901)
 */
package lapr4.green.s3.lang.n1150901.evalAndWhileLoops;
