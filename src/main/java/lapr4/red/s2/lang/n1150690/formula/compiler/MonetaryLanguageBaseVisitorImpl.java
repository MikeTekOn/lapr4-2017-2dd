/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150690.formula.compiler;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.BinaryOperation;
import csheets.core.formula.BinaryOperator;
import csheets.core.formula.Expression;
import csheets.core.formula.Literal;
import csheets.core.formula.lang.Language;
import csheets.core.formula.lang.UnknownElementException;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.red.s2.lang.n1150385.beanshell.utils.Pair;
import lapr4.red.s2.lang.n1150690.formula.MonetaryConvertion;
import lapr4.red.s2.lang.n1150690.formula.MonetaryValue;
import lapr4.red.s2.lang.n1150690.formula.configurations.ConfigureExchangeRatesController;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class MonetaryLanguageBaseVisitorImpl extends MonetaryLanguageBaseVisitor<Expression> {

    private Cell cell;
    private String currency;
    private String coin;
    private int numberOfErrors;
    private final StringBuilder errorBuffer;
    private ConfigureExchangeRatesController controller;
    private List<Pair<String, String>> exchangeRates;

    public MonetaryLanguageBaseVisitorImpl(Cell cell) {
        this.cell = cell;
        currency = "";
        coin = "";
        numberOfErrors = 0;
        errorBuffer = new StringBuilder();
        controller = new ConfigureExchangeRatesController();
        exchangeRates = controller.getExchangeRates();
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
    //@Override
    public Expression visitFormula(MonetaryLanguageParser.FormulaContext ctx) {
      
        currency = ctx.getChild(1).getText().toLowerCase();
        
        if (currency.equals("euro")) {
            coin = "\\u20AC";
        }
        if (currency.equals("dollar")) {
            coin = "$";
        }
        if (currency.equals("pound")) {
            coin = "£";
        }
        Expression a = visitExpression(ctx.expression());
        return a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Expression visitExpression(MonetaryLanguageParser.ExpressionContext ctx) {
        BinaryOperator operator = null;

        if (ctx.getChildCount() == 1) {
            return visitChildren(ctx);
        }

        if (ctx.op != null) {
            try {
                operator = Language.getInstance().getBinaryOperator(ctx.op.getText());
            } catch (UnknownElementException ex) {
                addVisitError(ex.getMessage());
            }
        }

        if (ctx.NUMBER() != null) {
            return new BinaryOperation(visit(ctx.getChild(1)), operator, new MonetaryValue(new BigDecimal(ctx.NUMBER().getText())));
        }

        if (ctx.LPAR() != null && ctx.RPAR() != null) {
            return new BinaryOperation(visit(ctx.getChild(1)), operator, visit(ctx.getChild(4)));
            //Literal result = new Literal(o.evaluate());
            //return (Expression) result;
        } else {
            return new BinaryOperation(visit(ctx.getChild(0).getChild(0)), operator, visit(ctx.getChild(2)));
        }
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public Expression visitValue(MonetaryLanguageParser.ValueContext ctx) {
        Token t = (Token) ctx.getChild(0).getPayload();

        if (t.getType() == MonetaryLanguageParser.NUMBER) {
            return new MonetaryValue(new BigDecimal(ctx.getText()));
        } else {
            String number = ctx.getText();
            String treatedNumber = treatNumber(number.substring(0, number.length() - 1), number.substring(number.length() - 1));
            return new MonetaryValue(new BigDecimal(treatedNumber));
        }
    }

    /**
     *
     * @param n
     * @return
     */
    private String treatNumber(String n, String currentCoin) {
        int size = n.length();
        if (!currentCoin.equals(coin)) {
            String factor = factorToConvert(currentCoin);
            MonetaryConvertion conversion = new MonetaryConvertion();
            return conversion.convertTo(n.substring(0, size - 1), new BigDecimal(factor)).toString();
        }
        return new BigDecimal(n.substring(0, size - 1)).toString();
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
        currency = currency.substring(0, 1).toUpperCase() + currency.substring(1).toLowerCase();
        currentCoin = currentCoin.replaceAll("\\u20AC", "€");
        currentCoin = currentCoin.replaceAll("\\u0024", "$");
        currentCoin = currentCoin.replaceAll("\\u00A3", "£");

        if (currentCoin.equals("€")) {
            exchange = currency + "ToEuro";
        }
        if (currentCoin.equals("$")) {
            exchange = currency + "ToDollar";
        }
        if (currentCoin.equals("£")) {
            exchange = currency + "ToPound";
        }
        for (Pair<String, String> p : exchangeRates) {
            if (p.getKey() == exchange) {
                return p.getValue();
            }
        }
        return null;
    }
}
