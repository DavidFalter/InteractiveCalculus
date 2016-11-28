/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructureElements;

import DataStructureElements.Visitor.DSEVisitor;

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
