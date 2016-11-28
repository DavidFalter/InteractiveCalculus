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
public class Variable extends TerminalExpression implements Comparable<Variable>{

    public Variable() {
    }

    @Override
    public Expression getExpression() {
        return this;
    }

    @Override
    public void accept(DSEVisitor v) {
	v.visitVariable(this);
    }

    @Override
    public int compareTo(Variable o) {
	return 0;
    }

    public Expression getUsub() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
