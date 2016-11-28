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
public class SimplifyVisitor extends DSEClassVisitor {
    
    @Override
    public void visitSum(Sum expr){
	
    }
    
    @Override
    public void visitVariable(Variable aThis) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visitProduct(Product aThis) {
	throw new UnsupportedOperationException("Not supported yet.");
    }
}
