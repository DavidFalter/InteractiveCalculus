/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructureElements;

import DataStructureElements.Visitor.DSEVisitor;
import Utilities.Stringifier;
import java.util.*;

/**
 *
 * @author rthec
 */
public class Cos extends UnaryExpression{
    Expression e;

    public Cos(Expression e) {
        this.e = e;
    }

    @Override
    public Expression getExpression() {
        return e;
    }
    
        
    @Override
    public Expression getDerivative() {
        Sin s = new Sin(e);
        ArrayList<Expression> product = new ArrayList<>();
        product.add(new Constant(-1));
        product.add(s);
        if (e instanceof Variable){
            return new Product(product);
        }
        else {
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
    public String getName() {
	return "cos";
    }

    @Override
    public void accept(DSEVisitor v) {
	v.visitCos(this);
    }

    public Expression getUsub() {
        return new Cos(new Variable());
    }
    
}
