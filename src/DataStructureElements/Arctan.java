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
public class Arctan extends UnaryExpression{
    Expression e;

    public Arctan(Expression e) {
        this.e = e;
    }

    public Expression getExpression() {
        return e;
    }

    @Override
    public Expression getDerivative() {
        ArrayList<Expression> denom = new ArrayList<>();
	denom.add(new Constant(1));
	denom.add(new Power(2,e));
	Sum d = new Sum(denom);
	
	if(e instanceof Variable){
	    return new Quotient(new Constant(1), d);
	} else {
	    return new Quotient(e.getDerivative(), d);
	}
    }

    @Override
    public Expression getIntegral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
	return "atan";
    }

    @Override
    public void accept(DSEVisitor v) {
	v.visitATan(this);
    }

    public Expression getUsub() {
        return new Arctan(new Variable());
    }
    
}
