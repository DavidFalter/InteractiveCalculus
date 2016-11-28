/*
 *  University of Central Florida
 *  COP 3330
 *  Author: Jonathan Gillis
 */
package DataStructureElements.Visitor;

import DataStructureElements.*;

/**
 *
 * @author gillis
 */
public class QuietDSEVisitor extends DSEVisitor {

    @Override
    public void visitVariable(Variable aThis) {
	this.visitDefault(aThis);
    }

    @Override
    public void visitProduct(Product aThis) {
	this.visitDefault(aThis);
    }

    @Override
    public void visitSum(Sum aThis) {
	this.visitDefault(aThis);
    }

    @Override
    public void visitTan(Tan aThis) {
	this.visitDefault(aThis);
    }

    @Override
    public void visitSin(Sin aThis) {
	this.visitDefault(aThis);
    }

    @Override
    public void visitATan(Arctan aThis) {
	this.visitDefault(aThis);
    }

    @Override
    public void visitPower(Power aThis) {
	this.visitDefault(aThis);
    }

    @Override
    public void visitACos(Arccos aThis) {
	this.visitDefault(aThis);
    }
    
    @Override
    public void visitConstant(Constant aThis) {
	this.visitDefault(aThis);
    }

    @Override
    public void visitCos(Cos aThis) {
	this.visitDefault(aThis);
    }

    @Override
    public void visitASin(Arcsin aThis) {
	this.visitDefault(aThis);
    }

    @Override
    public void visitACot(Arccot aThis) {
	this.visitDefault(aThis);
    }

    @Override
    public void visitSec(Sec aThis) {
	this.visitDefault(aThis);
    }

    @Override
    public void visitCSC(Csc aThis) {
	this.visitDefault(aThis);
    }

    @Override
    public void visitASec(Arcsec aThis) {
	this.visitDefault(aThis);
    }

    @Override
    public void visitACSC(Arccsc aThis) {
	this.visitDefault(aThis);
    }

    @Override
    public void visitCot(Cot aThis) {
	this.visitDefault(aThis);	
    }

    @Override
    public void visitExponential(Exponential aThis) {
	this.visitDefault(aThis);
    }
    
    public void visitDefault(Expression e){
    }

    @Override
    public void visitQuotient(Quotient aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitLn(Ln aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitLog(Log aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
