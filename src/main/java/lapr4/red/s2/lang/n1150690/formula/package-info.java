/**
 * Technical documentation regarding the user story Lang01.2: Monetary Language.
 *
 * <p>
 * <b>Scrum Master: no</b>
 * <p>
 * <b>Area Leader: no</b>
 *
 * <h2>1. Notes</h2>
 * <p>
 * <p>
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
 * <p>
 *
 * <h2>3. Analysis</h2>
 * <p>
 * <b>What types of currency can the user enter?</b> After the discussion with
 * the client, this one said that he can only enter amounts in Euro, Dollar or
 * Pounds.
 * <p>
 * The <u>exchange rates</u> are previous defined in the system, although the
 * user can configure them to a new value. This exchange rates will be persisted
 * in properties file. Whenever a new instance of the application is started,
 * these rates will be loaded into memory for use in conversions. Thus the
 * properties file will have six lines corresponding to the exchange rates:  
 * <code>
 * <p>
 * EuroToDollar=1.12749
 * <p>
 * EuroToPound=0.87423
 * <p>
 * DollarToEuro=0.88693
 * <p>
 * DollarToPound=0.77538
 * <p>
 * PoundToEuro=1.14380
 * <p>
 * PoundToDollar=1.28977
 * </code>
 * <p>
 * The values were taken from a website of exchange rates:
 * <a href="http://www.xe.com/">XE - The World's Trusted Currency Authority</a>
 * <p>
 * Regarding the problem of using numbers in floating point, in the
 * implementation i will use numbers defined as BigDecimal. A BigDecimal
 * consists of an arbitrary precision integer unscaled value and a 32-bit
 * integer scale. The BigDecimal class provides operations for arithmetic, scale
 * manipulation, rounding, comparison, hashing, and format conversion. This
 * class gives its user complete control over rounding behavior. For more
 * informations, i do recommend reading the
 * <a href="https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html">javadoc</a>
 * of BigDecimal.
 * <p>
 * <p>
 * The previous use case has several information about how the parser and
 * executer work together, how to convert a parser tree to Expressions, how to
 * load the language properties and several referencies about important methods
 * use in this use case. Please go checkout the information of the package
 * <code>lapr4.blue.s1.lang.n1151452.formula</code>
 *
 * <h3>3.2. The Language</h3>
 * <p>
 * The formula should start with the character "#" and be followed by the
 * currency that the user wants the result. The expression must be within
 * brackets and must contain at least two numbers and one operand. It is
 * necessary to define a grammar for this formula and i start to write a draft
 * of it:
 * <p>
 * <code>
 * <p>
 * formula: START_CHAR currency L_CURLY_BRACKET expression R_CURLY_BRACKET;
 * <p>
 * currency: DOLLAR | EURO | POUND;
 * <p>
 * expression: NUMBER COIN ( MULTI | DIV ) expression | NUMBER COIN ( PLUS |
 * MINUS) expression | NUMBER COIN | ;
 *
 * <p>
 * START_CHAR: '#' ;
 * <p>
 * DOLLAR: 'dollar' ;
 * <p>
 * EURO: 'euro' ;
 * <p>
 * POUND: 'pound' ;
 * <p>
 * NUMBER: [0-9]+\.[0-9]{2} ;
 * <p>
 * COIN: '€' | '$' | '£' ;
 * <p>
 * PLUS: '+' ;
 * <p>
 * MINUS: '-' ;
 * <p>
 * MULTI: '*' ;
 * <p>
 * DIV: '/' ;
 * <p>
 * L_CURLY_BRACKET: '{' ;
 * <p>
 * R_CURLY_BRACKET: '}' ;
 * </code>
 *
 * <h3>3.3. Domain Model</h3>
 * <p>
 * <img src="domain_model.png" alt="image">
 * <i>Not a definitive model</i>
 * <p>
 * <h2>4. Design</h2>
 * <p>
 *
 * <h3>4.1. Functional Tests</h3>
 * <p>
 *
 * <h3>4.2. UC Realization</h3>
 * <p>
 *
 * <h3>4.3. Classes</h3>
 * <p>
 * <h2>5. Implementation</h2>
 * <p>
 *
 *
 * <h2>6. Integration/Demonstration</h2>
 * <p>
 *
 * <h2>7. Final Remarks</h2>
 * <p>
 *
 * <h2>8. Work Log</h2>
 * <p>
 * <b>Tuesday: 06/06/2017</b>
 * <p>
 * Yesterday: our team distributed the funcionalities to be worked on this
 * sprint.
 * <p>
 * Today: I will start the analysis process.
 * <p>
 * Blocking:---
 * <p>
 * <b>Wednesday 07/06/2017</b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:---
 * <p>
 * <b>Thursday 08/06/2017 </b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:
 * <p>
 * <b>Friday 09/06/2017</b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:--
 * <p>
 * <b>Monday 12/06/2017</b>
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
 * @author Sofia Silva 1150690@isep.ipp.pt
 */
package lapr4.red.s2.lang.n1150690.formula;
