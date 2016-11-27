/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructureElements;

import DataStructureElements.Visitor.DSEVisitor;
import java.util.ArrayList;

/**
 *
 * @author rthec
 */
public class Arcsin extends UnaryExpression{
    Expression e;

    public Arcsin(Expression e) {
        this.e = e;
    }

    public Expression getExpression() {
        return e;
    }

    @Override
    public Expression getDerivative() {
	ArrayList<Expression> a = new ArrayList<>();
	a.add(new Constant(1));
	ArrayList<Expression> p = new ArrayList<>();
	p.add(new Constant(-1));
	p.add(new Power(2, e));
	a.add(new Product(p));
	
	Power denom = new Power(1.0/2.0,new Sum(a));
        return new Quotient(e.getDerivative(), denom);
    }

    @Override
    public Expression getIntegral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
	return "asin";
    }

    @Override
    public void accept(DSEVisitor v) {
	v.visitASin(this);
    }

    public Expression getUsub() {
        return new Arcsin(new Variable());
    }
    
}
