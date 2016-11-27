/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructureElements;

import DataStructureElements.Visitor.DSEVisitor;
import Utilities.ShrinkTree;
import Utilities.Simplify;
import Utilities.Stringifier;
import java.util.ArrayList;

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
    public Expression getDerivative() {
        Quotient q;
        Product p1;
        Product p2;
        Power denom;
        Sum num;
        
        super.addStep("Remember the quotient rule: d/dx(f / g) = (g*f' - f*g')/g^2");
        
        super.addStep("f = " + Stringifier.stringify(numerator));
        super.addStep("g = " + Stringifier.stringify(denominator));
        super.addStep("Now find f' and g'");
        
        ArrayList<Expression> sum = new ArrayList<>();
        ArrayList<Expression> product1 = new ArrayList<>();
        ArrayList<Expression> product2 = new ArrayList<>();
        
        super.addStep("To find f':");
        product1.add(denominator);
        product1.add(numerator.getDerivative());
        super.addStep("f' = " + Stringifier.stringify(numerator.getDerivative()));
        p1 = new Product(product1);
        p1 = (Product) ShrinkTree.shrink(p1);
        p1 = Simplify.simplifyProduct(p1);
        
        
        super.addStep("To find g':");
        product2.add(numerator);
        product2.add(denominator.getDerivative());
        super.addStep("g' = " + Stringifier.stringify(numerator.getDerivative()));
        p2 = new Product(product2);
        p2 = (Product) ShrinkTree.shrink(p2);
        p2 = Simplify.simplifyProduct(p2);
        
        sum.add(p1);
        sum.add(p2);
        num = new Sum(sum);
        
        denom = new Power(2, denominator);
        
        super.addStep("Now substitute everything in to the quotient rule to get " + 
                Stringifier.stringify(new Quotient(num, denom)));
        
        return new Quotient(num, denom);
    }

    @Override
    public Expression getIntegral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
