/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructureElements;

import DataStructureElements.Visitor.Compare;
import DataStructureElements.Visitor.DSEVisitor;
import Utilities.ShrinkTree;
import Utilities.Simplify;
import Utilities.Stringifier;
import java.util.*;

public class Power extends Expression{
    private double power;
    private Expression base;

    public Power(double power, Expression e) {
        this.power = power;
        this.base = e;
    }
    
    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public Expression getExpression() {
        return base;
    }
    
    @Override
    public Expression getDerivative() {
        Product p;
        ArrayList<Expression> product = new ArrayList<>();
        
        if (!(base instanceof Variable)){
            super.addStep("For this term we use the chain rule take the derivative of the inside, let u(x) = " + Stringifier.stringify(base));
            product.add(base.getDerivative());
            super.addStep("so u'(x) = " + Stringifier.stringify(base.getDerivative()));
            super.addStep("Remember that with the chain rule d/dx(f(u(x)) = u'(x) * f'(u(x)) ");        
            super.addStep("Now take the derivative of the outside with respect to u, f(u) = " + Stringifier.stringifyu(this.getUsub()));
            super.addStep("f'(u) = " + Stringifier.stringifyu(this.getUsub().getDerivative()));
            p = new Product(product);
            p = (Product) ShrinkTree.shrink(p);
            p = Simplify.simplifyProduct(p);
            super.addStep("Replacing u with " + Stringifier.stringify(base) + " we get " + Stringifier.stringify(p));
            return p;
        }
        
        if (power > 2){
            product.add(new Constant(power));
            product.add(new Power(power-1,base));
        }
        else {
            product.add(new Constant(power));
            product.add(base);
        }
        
        p = new Product(product);
        p = (Product) ShrinkTree.shrink(p);
        p = Simplify.simplifyProduct(p);
        return p;
    }

    @Override
    public Expression getIntegral() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void accept(DSEVisitor v) {
	v.visitPower(this);
    }

    @Override
    public int getPEMDASLevel() {
	return 3;
    }

    public boolean equals(Power o) {
	if(getPower() == o.getPower() && Compare.cmp(base, o.base) ==0)
	    return true;
	else
	    return false;
    }

    public Expression getUsub() {
        return new Power(this.power, new Variable());
    }
}
