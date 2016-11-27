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
public class Arccsc extends UnaryExpression{
    Expression e;

    public Arccsc(Expression e) {
        this.e = e;
    }

    public Expression getExpression() {
        return e;
    }

    @Override
    public Expression getDerivative() {
        Arcsec asec = new Arcsec(e);
	ArrayList<Expression> p = new ArrayList<Expression>();
	p.add(new Constant(-1));
	p.add(asec.getDerivative());
	return new Product(p);
    }

    @Override
    public Expression getIntegral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
	return "acsc";
    }

    @Override
    public void accept(DSEVisitor v) {
	v.visitACSC(this);
    }

    public Expression getUsub() {
        return new Arccsc(new Variable());
    }
    
}
