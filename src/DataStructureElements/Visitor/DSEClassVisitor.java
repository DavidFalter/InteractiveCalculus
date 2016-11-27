/*
 *  University of Central Florida
 *  COP 3330
 *  Author: Jonathan Gillis
 */
package DataStructureElements.Visitor;

import DataStructureElements.Container;
import DataStructureElements.Expression;
import DataStructureElements.TerminalExpression;
import DataStructureElements.UnaryExpression;

/**
 *
 * @author gillis
 */
public class DSEClassVisitor extends QuietDSEVisitor {
    
    @Override
    public void visit(Expression expr){
	if(expr instanceof UnaryExpression) {
	    this.visitUnaryExpression((UnaryExpression) expr);
	}else{
	    expr.accept(this);
	}
    }
    
    void visitUnaryExpression(UnaryExpression expr){
	this.visitDefault(expr);
    }
}
