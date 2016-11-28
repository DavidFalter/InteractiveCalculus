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
public class Arcsin extends UnaryExpression{
    Expression e;

    public Arcsin(Expression e) {
        this.e = e;
    }

    public Expression getExpression() {
        return e;
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
