/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructureElements;

import java.util.ArrayList;
import DataStructureElements.Visitor.DSEVisitor;
import Utilities.ShrinkTree;
import Utilities.Simplify;
import Utilities.Stringifier;

/**
 *
 * @author rthec
 */
public class Cot extends UnaryExpression{
    Expression e;

    public Cot(Expression e) {
        this.e = e;
    }

    public Expression getExpression() {
        return e;
    }
    
    @Override
    public Expression getDerivative() {
        Power p = new Power(2, new Csc(e));
        Product pr;
        ArrayList<Expression> product = new ArrayList<>();
        product.add(new Constant(-1));
        product.add(p);
        if (e instanceof Variable){
            pr = new Product(product);
            return pr;
        }
        else{
            super.addStep("For this term we use the chain rule take the derivative of the inside, let u(x) = " + Stringifier.stringify(e));
            super.addStep("so u'(x) = " + Stringifier.stringify(e.getDerivative()));
            product.add(e.getDerivative());
            pr = new Product(product);
            pr = (Product) ShrinkTree.shrink(pr);
            pr = Simplify.simplifyProduct(pr);
            super.addStep("Remember that with the chain rule d/dx(f(u(x)) = u'(x) * f'(u(x)) ");        
            super.addStep("Now take the derivative of the outside with respect to u, f(u) = " + Stringifier.stringifyu(this.getUsub()));
            super.addStep("f'(u) = " + Stringifier.stringifyu(this.getUsub().getDerivative()));
            super.addStep("Replacing u with " + Stringifier.stringify(e) + " we get " + Stringifier.stringify(pr));
            return pr;
        }
    }

    @Override
    public Expression getIntegral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
	return "cot";
    }

    @Override
    public void accept(DSEVisitor v) {
	v.visitCot(this);
    }

    public Expression getUsub() {
        return new Cot(new Variable());
    }
    
}
