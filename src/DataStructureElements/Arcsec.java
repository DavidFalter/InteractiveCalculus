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
public class Arcsec extends UnaryExpression{
    Expression e;

    public Arcsec(Expression e) {
        this.e = e;
    }

    public Expression getExpression() {
        return e;
    }

    @Override
    public Expression getDerivative() {
	ArrayList<Expression> a = new ArrayList<>();
	a.add(new Power(2, e));
	a.add(new Constant(-1));
	ArrayList<Expression> d = new ArrayList<>();
	d.add(e);
	d.add(new Power(1.0/2.0, new Sum(a)));
	
        return new Quotient(e.getDerivative(), new Product(d));
    }

    @Override
    public Expression getIntegral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
	return "asec";
    }

    @Override
    public void accept(DSEVisitor v) {
	v.visitASec(this);
    }

    public Expression getUsub() {
        return new Arcsec(new Variable());
    }
    
}
