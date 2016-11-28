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

public class Arccos extends UnaryExpression{
    Expression e;

    public Arccos(Expression e) {
        this.e = e;
    }

    @Override
    public Expression getExpression() {
        return e;
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
