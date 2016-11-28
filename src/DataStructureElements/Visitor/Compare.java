/*
 *  University of Central Florida
 *  COP 3330
 *  Author: Jonathan Gillis
 */
package DataStructureElements.Visitor;


import DataStructureElements.*;
import java.util.Comparator;

/**
 *
 * @author gillis
 */
public class Compare extends DSEClassVisitor implements Comparator<Expression>{
    Expression o;
    int result;

    public static int cmp(Expression l, Expression r){
	Compare c = new Compare();
	c.compare(l, r);
	return c.result;
    }
    
    @Override
    public void visitVariable(Variable aThis) {
	if(!(o instanceof Variable)){
	    result = -1;
	} else {
	    result = 0;
	}
    }

    @Override
    public void visitProduct(Product a) {
	if(!(o instanceof Product)){
	    result = -1;
	    return;
	}
	Product po = (Product) o;
	result = a.equals(po) ? 0 : -1;
    }

    @Override
    public void visitSum(Sum a) {
	if(!(o instanceof Sum)){
	    result = -1;
	    return;
	}
	Sum so = (Sum) o;
	result =  a.equals(so) ? 0 : -1;
	return;
    }

    
    @Override
    public void visitPower(Power aThis) {
	if(!(o instanceof Power)){
	    result = -1;
	    return;
	}
	Power po = (Power) o;
	result =  aThis.equals(po) ? 0 : -1;
	return;
	
    }
    
    public void visitUnaryExpression(UnaryExpression expr){
	if(!(o instanceof UnaryExpression)){
	    result = -1;
	    return;
	} else {
	    result = expr.equals((UnaryExpression)o) ? 0 : -1;
	    return;
	}
    }

    @Override
    public void visitConstant(Constant aThis) {
	if(!(o instanceof Constant)){
	    result = -1;
	    return;
	} else {
	    result = ((Constant)o).equals((Constant)o) ? 0 : -1;
	    return;
	}	
    }

    @Override
    public void visitExponential(Exponential aThis) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int compare(Expression o1, Expression o2) {
	this.o = o2;
	this.visit(o1);
	return this.result;
    }

    @Override
    public void visitACot(Arccot aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitSec(Sec aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitCSC(Csc aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitASec(Arcsec aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitACSC(Arccsc aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitCot(Cot aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitQuotient(Quotient aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }     
}
