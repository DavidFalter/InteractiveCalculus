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

public class Arccos extends UnaryExpression{
    Expression e;

    public Arccos(Expression e) {
        this.e = e;
    }

    public Expression getExpression() {
        return e;
    }

    @Override
    public Expression getDerivative() {
        Power p = new Power(2, e);
	ArrayList<Expression> pr = new ArrayList<>();
	pr.add(new Constant(-1));
	pr.add(p);
	ArrayList<Expression> s = new ArrayList<>();
	s.add(new Constant(1));
	s.add(new Product(pr));
	
	return new Quotient(new Constant(-1), new Power(1.0/2.0, new Sum(s)));
    }

    @Override
    public Expression getIntegral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
	return "acos";
    }

    @Override
    public void accept(DSEVisitor v) {
	v.visitACos(this);
    }

    public Expression getUsub() {
        return new Arccot(new Variable());
    }
    
}
