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
import lapr4.red.s2.lang.n1150690.formula.configurations.ConfigurateExchangeRatesController;

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
    private ConfigurateExchangeRatesController controller;
    private List<Pair<String, String>> exchangeRates;

    public MonetaryLanguageBaseVisitorImpl(Cell cell) {
        this.cell = cell;
        currency = "";
        coin = "";
        numberOfErrors = 0;
        errorBuffer = new StringBuilder();
        controller = new ConfigurateExchangeRatesController();
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
        currency = firstLetter.toUpperCase() + currency.substring(1).toLowerCase();
        if (currency.equals("Euro")) {
            coin = "€";
        }
        if (currency.equals("Dollar")) {
            coin = "$";
        }
        if (currency.equals("Pound")) {
            coin = "£";
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Expression visitExpression(MonetaryLanguageParser.ExpressionContext ctx) {
        // if (ctx.getChildCount() == 3) {
        try {
            return withThreeChilds(ctx);
        } catch (IllegalValueTypeException ex) {
            Logger.getLogger(MonetaryLanguageBaseVisitorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        //}
        /* if (ctx.getChildCount() == 1) {
            return withoneChild(ctx);
        }*/
        return null;
    }

    /**
     *
     * @param ctx
     * @return
     */
    private Expression withThreeChilds(MonetaryLanguageParser.ExpressionContext ctx) throws IllegalValueTypeException {
        if (ctx.LPAR() != null && ctx.RPAR() != null) {
            return visit(ctx.getChild(1));
        }

        // Convert binary operation
        BinaryOperator operator = null;
        //BigDecimal leftOperand = null;
        BigDecimal rightOperand = null;
        if (ctx.op != null) {
            try {
                operator = Language.getInstance().getBinaryOperator(ctx.op.getText());
            } catch (UnknownElementException ex) {
                addVisitError(ex.getMessage());
            }
        }

        //if (ctx.right != null && ctx.left != null) {
        //return visit(ctx.getChild(0));
        //leftOperand = treatNumber(ctx.left.getText());
        if (ctx.NUMBER() != null) {
            rightOperand = new BigDecimal(ctx.NUMBER().getText());
        }
        if (ctx.NUMBER_FOR_COIN() != null) {
            String number = ctx.NUMBER_FOR_COIN().getText();
            rightOperand = treatNumber(number);
        }
        //Literal l = new Literal(new Value((Number) leftOperand));
        Literal r = new Literal(new Value((Number) rightOperand));
        BinaryOperation o = new BinaryOperation(visit(ctx.getChild(0)), operator, r);
        Literal result = new Literal(o.evaluate());
        return (Expression) result;
        /*}

        if (ctx.NUMBER() != null) {
            rightOperand = new BigDecimal(ctx.NUMBER().getText());
            Literal r = new Literal(new Value((Number) rightOperand));
            BinaryOperation o = new BinaryOperation(visit(ctx.getChild(0)), operator, r);
            return (Expression) o.evaluate();
        }
        if (ctx.NUMBER_FOR_COIN() != null) {
            String number = ctx.NUMBER_FOR_COIN().getText();
            rightOperand = treatNumber(number);
            Literal r = new Literal(new Value((Number) rightOperand));
            BinaryOperation o = new BinaryOperation(visit(ctx.getChild(0)), operator, r);
            return (Expression) o.evaluate();
        }
        return new BinaryOperation(visit(ctx.getChild(0)), operator, );*/
    }

    /**
     *
     * @param ctx
     * @return
     */
    /*private Expression withoneChild(MonetaryLanguageParser.ExpressionContext ctx) {
        BigDecimal operand = null;
        Literal o = null;
        if (ctx.NUMBER() != null) {
            operand = new BigDecimal(ctx.NUMBER().getText());
        }
        if (ctx.NUMBER_FOR_COIN() != null) {
            String number = ctx.NUMBER_FOR_COIN().getText();
            operand = treatNumber(number);
            o = new Literal(new Value((Number) operand));
        }
        return (Expression) operand;
    }*/
    /**
     *
     * @param n
     * @return
     */
    private BigDecimal treatNumber(String n) {
        int size = n.length();
        String currentCoin = n.substring(size - 1).trim();
        if (currentCoin.equals("€") || currentCoin.equals("$") || currentCoin.equals("£")) {
            if (!currentCoin.equals(coin)) {
                String factor = factorToConvert(currentCoin);
                MonetaryConvertion conversion = new MonetaryConvertion();
                return conversion.convertTo(n.substring(0, size - 1), new BigDecimal(factor));
            }
            return new BigDecimal(n.substring(0, size - 1));
        }
        return new BigDecimal(n);
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
        for (Pair<String, String> p : exchangeRates) {
            if (p.getKey() == exchange) {
                return p.getValue();
            }
        }
        return null;
    }
}
