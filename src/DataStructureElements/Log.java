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
public class Log extends UnaryExpression{
    Expression e;

    public Log(Expression e) {
        this.e = e;
    }
    
    @Override
    public Expression getExpression() {
        return e;
    }       

    @Override
    public String getName() {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void accept(DSEVisitor v) {
	v.visitLog(this);
    }

    public Expression getUsub() {
        return new Log(new Variable());
    }
    
}
