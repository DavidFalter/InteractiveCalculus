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
public class Arccot extends UnaryExpression{
    Expression e;

    public Arccot(Expression e) {
        this.e = e;
    }

    public Expression getExpression() {
        return e;
    }

    @Override
    public Expression getDerivative() {
        Arctan atan = new Arctan(e);
	Expression datan = atan.getDerivative();
	ArrayList<Expression> ddx = new ArrayList<>();
	ddx.add(new Constant(-1));
	ddx.add(datan);
	return new Product(ddx);
    }

    @Override
    public Expression getIntegral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
	return "acot";
    }

    @Override
    public void accept(DSEVisitor v) {
	v.visitACot(this);
    }

    public Expression getUsub() {
        return new Arccot(new Variable());
    }
    
}
