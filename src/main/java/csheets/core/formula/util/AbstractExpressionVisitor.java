/*
 * Copyright (c) 2005 Einar Pehrson <einar@pehrson.nu>.
 *
 * This file is part of
 * CleanSheets - a spreadsheet application for the Java platform.
 *
 * CleanSheets is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * CleanSheets is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CleanSheets; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package csheets.core.formula.util;

import csheets.core.formula.*;
import java.util.HashSet;
import java.util.Set;
import lapr4.blue.s1.lang.n1151088.temporaryVariables.TemporaryVariable;
import lapr4.blue.s1.lang.n1151159.macros.Macro;
import lapr4.gray.s1.lang.n3456789.formula.NaryOperation;

/**
 * A default implementation of an expression visitor, that simply visits all
 * the nodes in the tree. All methods return the expression that was visited.
 * @author Einar Pehrson
 * @author Diana Silva (update due temporaryVariables operation)
 */
public abstract class AbstractExpressionVisitor implements ExpressionVisitor {
      /**
	 * Creates a new expression visitor.
	 */
	public AbstractExpressionVisitor() {}

	public Object visitLiteral(Literal literal) {
		return literal;
	}

	public Object visitUnaryOperation(UnaryOperation operation) {
		operation.getOperand().accept(this);
		return operation;
	}

        @Override
	public Object visitBinaryOperation(BinaryOperation operation) {
		operation.getLeftOperand().accept(this);
		operation.getRightOperand().accept(this);
		return operation;
	}

        @Override
	public Object visitReference(Reference reference) {
		return reference;
	}

        @Override
	public Object visitFunctionCall(FunctionCall call) {
		for (Expression argument : call.getArguments())
			argument.accept(this);
		return call;
	}
        
        /**
         * Update due to temporary variables
         * @author Diana Silva
         * @param operation
         * @return 
         */
        @Override
        public Object visitNaryOperation(NaryOperation operation) {
            Expression[] operands=operation.getOperands();
        
            Set<TemporaryVariable> tempVars = new HashSet<>();
            
            for (Expression expr: operands) {
                
                if (expr instanceof TemporaryVariable) {
                    
                
                if(tempVars.contains(expr)) {
                    tempVars.remove(expr);
                    tempVars.add((TemporaryVariable)expr);
                } else {
                    tempVars.add((TemporaryVariable) expr);
                }
                }
                
                expr.accept(this);
            }
        
            return operation;
        }

	@Override
	public Object visitMacro(Macro macro) {
		return macro.accept(this);
	}
        
        @Override
        public Object visitTemporaryVariable(TemporaryVariable tempVar) {
            return tempVar.accept(this);
        }
}