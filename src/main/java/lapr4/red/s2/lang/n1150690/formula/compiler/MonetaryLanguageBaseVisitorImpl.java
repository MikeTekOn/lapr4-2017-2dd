/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150690.formula.compiler;

import csheets.core.Cell;
import csheets.core.formula.BinaryOperation;
import csheets.core.formula.BinaryOperator;
import csheets.core.formula.Expression;
import csheets.core.formula.lang.Language;
import csheets.core.formula.lang.UnknownElementException;
import eapli.util.Files;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.rmi.PortableRemoteObject;
import lapr4.red.s2.lang.n1150690.formula.MonetaryConvertion;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class MonetaryLanguageBaseVisitorImpl extends MonetaryLanguageBaseVisitor<Expression> {

    private static final String PROPERTIES_FILE = "project.properties";
    private Cell cell;
    private String currency;
    private String coin;
    private int numberOfErrors;
    private final StringBuilder errorBuffer;

    public MonetaryLanguageBaseVisitorImpl(Cell cell) {
        this.cell = cell;
        currency = "";
        coin = "";
        numberOfErrors = 0;
        errorBuffer = new StringBuilder();
    }

    public int getNumberOfErrors() {
        return numberOfErrors;
    }

    public String getErrorsMessage() {
        return errorBuffer.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Expression visitFormula(MonetaryLanguageParser.FormulaContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Expression visitCurrency(MonetaryLanguageParser.CurrencyContext ctx) {
        currency = ctx.getText();
        String firstLetter = currency.substring(0, 1);
        currency = firstLetter + currency.substring(1).toLowerCase();
        if (currency.equals("euro")) {
            coin = "€";
        }
        if (currency.equals("dollar")) {
            coin = "$";
        }
        if (currency.equals("pound")) {
            coin = "£";
        }
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Expression visitExpression(MonetaryLanguageParser.ExpressionContext ctx) {
        try {
            // Convert binary operation
            BinaryOperator operator = Language.getInstance().getBinaryOperator(ctx.op.getText());
            /*if (ctx.left != null) {
                String left = ctx.left.getText();
                int size = left.length();
                String currentCoin = left.substring(size - 2).trim();
                if (!currentCoin.equals(coin)) {
                    String factor = factorToConvert(currentCoin);
                    MonetaryConvertion conversion = new MonetaryConvertion();
                    conversion.convertTo(left.substring(0, size-1), new BigDecimal(factor));
                }
            }
            if (ctx.right != null) {
                String right = ctx.right.getText();
                int size = right.length();
                String currentCoin = right.substring(size - 2).trim();
                if (!currentCoin.equals(coin)) {
                    String factor = factorToConvert(currentCoin);
                    MonetaryConvertion conversion = new MonetaryConvertion();
                    conversion.convertTo(right.substring(0, size-1), new BigDecimal(factor));
                }
            }*/

            return new BinaryOperation(
                    visit(ctx.getChild(0)),
                    operator,
                    visit(ctx.getChild(2))
            );
        } catch (UnknownElementException ex) {
            addVisitError(ex.getMessage());
        }

        return visitChildren(ctx);
    }

    /**
     * Adds a given message to the visit error.
     *
     * @param msg message to be appended to the error
     */
    private void addVisitError(String msg) {
        errorBuffer.append(msg).append("\n");
        numberOfErrors++;
    }

    /**
     *
     * @param currentCoin
     * @return
     */
    private String factorToConvert(String currentCoin) {
        String exchange = "";
        if (currentCoin.equals("€")) {
            exchange = currency + "ToEuro";
        }
        if (currentCoin.equals("$")) {
            exchange = currency + "ToDollar";
        }
        if (currentCoin.equals("£")) {
            exchange = currency + "ToPound";
        }
        InputStream in = this.getClass().getResourceAsStream(PROPERTIES_FILE);
        return "";/*Files.getPropertyValue(in, exchange);*/
    }
}
