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
public abstract class DSEVisitor {
    
    public void visit(Expression e){
	this.before();
	e.accept(this);
	this.after();	
    }
        
    void before(){
	
    }
    void after(){
	
    }

    public abstract void visitVariable(Variable aThis);

    public abstract void visitProduct(Product aThis);

    public abstract void visitSum(Sum aThis);

    public abstract void visitTan(Tan aThis);

    public abstract void visitSin(Sin aThis);

    public abstract void visitATan(Arctan aThis);

    public abstract void visitPower(Power aThis);

    public abstract void visitACos(Arccos aThis);

    public abstract void visitConstant(Constant aThis);

    public abstract void visitCos(Cos aThis);

    public abstract void visitASin(Arcsin aThis);

    public abstract void visitACot(Arccot aThis);

    public abstract void visitSec(Sec aThis);

    public abstract void visitCSC(Csc aThis);
    
    public abstract void visitASec(Arcsec aThis);

    public abstract void visitACSC(Arccsc aThis);

    public abstract void visitCot(Cot aThis);
    
    public abstract void visitQuotient(Quotient aThis);

    public abstract void visitExponential(Exponential aThis);
    
    public abstract void visitLn(Ln aThis);
    
    public abstract void visitLog(Log aThis);
}
