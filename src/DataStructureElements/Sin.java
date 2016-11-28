/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructureElements;

import DataStructureElements.Visitor.DSEVisitor;

public class Sin extends UnaryExpression{
    Expression e;

    public Sin(Expression e) {
        this.e = e;
    }

    public Expression getExpression() {
        return e;
    }

    @Override
    public String getName() {
	return "sin";
    }

    @Override
    public void accept(DSEVisitor v) {
	v.visitSin(this);
    }

    public Expression getUsub() {
        return  new Sin(new Variable());
    }
    
}
