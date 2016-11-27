/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructureElements;

import java.util.ArrayList;
import DataStructureElements.Visitor.DSEVisitor;
import Utilities.Stringifier;

/**
 *
 * @author rthec
 */
public class Sec extends UnaryExpression{
    Expression e;

    public Sec(Expression e) {
        this.e = e;
    }

    public Expression getExpression() {
        return e;
    }
    
    @Override
    public Expression getDerivative() {
        ArrayList<Expression> product = new ArrayList<>();
        product.add(new Sec(e));
        product.add(new Tan(e));
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
            return new Product(product);
        }
    }

    @Override
    public Expression getIntegral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
	return "sec";
    }

    @Override
    public void accept(DSEVisitor v) {
	v.visitSec(this);
    }

    public Expression getUsub() {
        return  new Sec(new Variable());
    }
    
}
