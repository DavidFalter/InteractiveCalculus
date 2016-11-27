/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructureElements;

import DataStructureElements.Visitor.Compare;
import DataStructureElements.Visitor.DSEVisitor;
import Utilities.Stringifier;
import java.util.*;
import java.lang.Math;
public class Exponential extends Expression {
    Double base;
    Expression e;

    public Exponential(Double base, Expression e) {
        this.base = base;
        this.e = e;
    }

    public Double getBase() {
        return base;
    }
    
    @Override
    public Expression getExpression() {
        return e;
    }

    @Override
    public Expression getDerivative() {
        ArrayList<Expression> product = new ArrayList<>();
        product.add(new Constant(Math.log(this.base)));
        product.add(this);
        if (e instanceof Variable){
            return new Product(product);
        }
        else{
            super.addStep("For this term we use the chain rule take the derivative of the inside, let u(x) = " + Stringifier.stringify(e));
            super.addStep("so u'(x) = " + Stringifier.stringify(e.getDerivative()));
                        
            product.add(e.getDerivative());
            super.addStep("Remember that with the chain rule d/dx(f(u(x)) = u'(x) * f'(u(x)) ");        
            super.addStep("Now take the derivative of the outside with respect to u, f(u) = " + Stringifier.stringifyu(this.getUsub()));
            super.addStep("f'(u) = " + Stringifier.stringifyu(this.getUsub().getDerivative()));
            super.addStep("Replacing u with " + Stringifier.stringify(e) + " we get " + Stringifier.stringify(new Product(product)));
            return new Product(product);            
        }
    }

    @Override
    public Expression getIntegral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void accept(DSEVisitor v) {
	v.visitExponential(this);
    }

    @Override
    public int getPEMDASLevel() {
	return 3;
    }
    
    public boolean equals(Exponential o) {
	if(getBase() == o.getBase() && Compare.cmp(e, o.e) ==0)
	    return true;
	else
	    return false;
    }

    public Expression getUsub() {
        return new Exponential(this.base, new Variable());
    }

    
} 
