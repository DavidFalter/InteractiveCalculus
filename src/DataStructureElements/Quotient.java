/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructureElements;

import DataStructureElements.Visitor.DSEVisitor;

/**
 *
 * @author rthec
 */
public class Quotient extends Expression{
    Expression numerator;
    Expression denominator;

    public Quotient(Expression numerator, Expression denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Expression getNumerator() {
        return numerator;
    }

    public Expression getDenominator() {
        return denominator;
    }
    
    @Override
    public Expression getExpression() {
        return this;
    }

    @Override
    public void accept(DSEVisitor v) {
	v.visitQuotient(this);
    }
    
    public int getPEMDASLevel() {
	return 2;
    }

    public Expression getUsub() {
        return new Variable();
    }
    
}
