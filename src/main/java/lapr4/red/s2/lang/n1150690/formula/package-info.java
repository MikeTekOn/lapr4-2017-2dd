/**
 * Technical documentation regarding the user story Lang01.2: Monetary Language.
 *
 * <p/>
 * <b>Scrum Master: no</b>
 * <p/>
 * <b>Area Leader: no</b>
 *
 * <h2>1. Notes</h2>
 * <p/>
 * <p/>
 *
 * <h2>2. Requirement</h2>
 * <ul>
 * <li>Add a new formulas language (currently Cleansheets only has the Excel
 * formulas that begin with the character "="). The new language should do only
 * calculations related to currencies. </li>
 * <li>The character that begins the formula should be "#".</li>
 * <li>The formula should only accept the addition, subtraction, multiplication
 * and division operators.
 * <li>Operands are monetary values in which it is necessary to provide the
 * currency (Ex: 10.21€, 1.32£ or 0.20$). </li>
 * <li>All expressions are required to be contained within brackets with the
 * currency prefix in which we want the result. Ex: "#euro{10.32$ + 12.89£}" or
 * "#dollar{ 10.32$ + 12.89£}" or "#pound{10.32$ + 12.89£}".
 * <li>For the user to use this language instead of the "regular" Excel language
 * it should start the formula by the character "#" instead of the "="
 * character. </li>
 * <li>Cleansheets should also provide a way for setting exchange rates (by
 * means of a configuration).</li>
 * <li>The implementation should avoid the use of numbers in floating point
 * representation (e.g., float, double) in order to avoid precision
 * problems.</li>
 * </ul>
 * <p/>
 *
 * <h2>3. Analysis</h2>
 * <p/>
 * <b>What types of currency can the user enter?</b> After the discussion with
 * the client, this one said that he can only enter amounts in Euro, Dollar or
 * Pounds.
 * <p/>
 * <b>Exchange Rates</b> - The <u>exchange rates</u> are previous defined in the
 * system, although the user can configure them to a new value. This exchange
 * rates will be persisted in properties file. Whenever a new instance of the
 * application is started, these rates will be loaded into memory for use in
 * conversions. Thus the properties file will have six lines corresponding to
 * the exchange rates:  
 * <code>
 * <p/>
 * EuroToDollar=1.12749
 * <p/>
 * EuroToPound=0.87423
 * <p/>
 * DollarToEuro=0.88693
 * <p/>
 * DollarToPound=0.77538
 * <p/>
 * PoundToEuro=1.14380
 * <p/>
 * PoundToDollar=1.28977
 * </code>
 * <p/>
 * The values were taken from a website of exchange rates:
 * <a href="http://www.xe.com/">XE - The World's Trusted Currency Authority</a>
 * <p/>
 * <p/>
 * Regarding the problem of using numbers in <b>floating point</b>, in the
 * implementation i will use numbers defined as BigDecimal. A BigDecimal
 * consists of an arbitrary precision integer unscaled value and a 32-bit
 * integer scale. The BigDecimal class provides operations for arithmetic, scale
 * manipulation, rounding, comparison, hashing, and format conversion. This
 * class gives its user complete control over rounding behavior. For more
 * informations, i do recommend reading the
 * <a href="https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html">javadoc</a>
 * of BigDecimal.
 * <p/>
 * <p/>
 * The <b>previous use case</b> has several information about how the parser and
 * executer work together, how to convert a parser tree to Expressions, how to
 * load the language properties and several referencies about important methods
 * use in this use case. Please go checkout the information of the package
 * <code>lapr4.blue.s1.lang.n1151452.formula</code>
 *
 * <h3>3.2. The Language</h3>
 * <p/>
 * The formula should start with the character "#" and be followed by the
 * currency that the user wants the result. The expression must be within
 * brackets and must contain at least one number with the currency. The
 * expression may contain operations of addition, subtraction, multiplication,
 * wherein the multiplication and division of the operands must be a number, not
 * a monetary amount. The expression may still contain operations in parentheses
 * to indicate precedence.
 * <p/>
 *
 * <h3>3.3. Domain Model</h3>
 * <p/>
 * <img src="domain_model.png" alt="image">
 * <p/>
 * <h2>4. Tests</h3>
 * <h3>4.1. Unit Tests</h3>
 * <ul>
 * <li>test convertions between coins</li>
 * <li>ensureExpressionIsNotMalformed</li>
 * </ul>
 * <p/>
 * <h3>4.2. Functional Tests</h3>
 * <p/>
 * <ul>
 * <li>Test for the calculation with the same currency
 * <ol>
 * <li>Insert in the cell A1 the following expression: "#euro{12.36€ -
 * 9.06€}</li>
 * <li>The cell A1 should have the value: 3.30</li>
 * </ol>
 * </li>
 * <p/>
 * <li>Test for the calculation with the diferent currencies
 * <ol>
 * <li>Insert in the cell A1 the following expression: "#euro{12.36$ -
 * 5.06£}</li>
 * <li>The system must convert the 12.36$ value to euros, and the value must be:
 * 10.96€</li>
 * <li>The system must convert the 5.06£ value to euros, and the value must be:
 * 5.79€€</li>
 * <li>The cell A1 should have the value: 5.17</li>
 * </ol>
 * </li>
 * </ul>
 *
 * <h2>5. Design</h2>
 * <h3>5.1. UC Realization</h3>
 * <p/>
 * <b>How Operations are made</b>
 * <img src="assignment_sd" alt="image">
 * <p/>
 * <b>Load Property (Exchange Rate) and Convert Monetary value</b>
 * <img src="lang01_2_convert.png" alt="image">
 * <p/>
 * <b>Configuration of Exchange Rates</b>
 * <img src="lang01_2_configurateExchangeRates.png" alt="image">
 *
 * <h3>5.2. Classes</h3>
 * <p/>
 * <b>Configurations diagram</b>
 * <img src="lang01_2_configurations_cd.png" alt="image">
 * <p/>
 * <img src="lang01_2_cd.png" alt="image">
 * 
 * <h2>5. Implementation</h2>
 * </p/> I needed to modify the applyTo methods of the Adder, Divider, Multiplier
 * and Subtracter classes to do the operations with values in BigDecimal.
 *
 *
 * <h2>6. Integration/Demonstration</h2>
 * <p/>
 *
 * <h2>7. Final Remarks</h2>
 * <p/>
 *
 * <h2>8. Work Log</h2>
 * <p/>
 * <b>Tuesday: 06/06/2017</b>
 * <p/>
 * Yesterday: our team distributed the funcionalities to be worked on this
 * sprint.
 * <p/>
 * Today: I will start the analysis process.
 * <p/>
 * Blocking:---
 * <p/>
 * <b>Wednesday 07/06/2017</b>
 * <p/>
 * Yesterday:I finished the analysis process.
 * <p/>
 * Today:I will make a plan to the funcional tests and start the design.
 * <p/>
 * Blocking:---
 * <p/>
 * <b>Thursday 08/06/2017 </b>
 * <p/>
 * Yesterday:
 * <p/>
 * Today:
 * <p/>
 * Blocking:
 * <p/>
 * <b>Friday 09/06/2017</b>
 * <p/>
 * Yesterday:
 * <p/>
 * Today:
 * <p/>
 * Blocking:--
 * <p/>
 * <b>Monday 12/06/2017</b>
 * <p/>
 * Yesterday:
 * <p/>
 * Today:
 * <p/>
 * Blocking:
 * <p/>
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
 * @author Sofia Silva 1150690@isep.ipp.pt
 */
package lapr4.red.s2.lang.n1150690.formula;
