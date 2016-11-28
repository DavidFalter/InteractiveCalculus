/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructureElements;

import DataStructureElements.Visitor.Compare;
import DataStructureElements.Visitor.DSEVisitor;
import java.util.Objects;
public class Exponential extends Expression {
    Double base;
    Expression e;

    public Exponential(Double base, Expression e) {
        this.base = base;
        this.e = e;
    }

    public Double getBase() {
        return base;
    }
    
    @Override
    public Expression getExpression() {
        return e;
    }

    @Override
    public void accept(DSEVisitor v) {
	v.visitExponential(this);
    }

    @Override
    public int getPEMDASLevel() {
	return 3;
    }
    
    public boolean equals(Exponential o) {
	if(Objects.equals(this.getBase(), o.getBase()) && Compare.cmp(e, o.e) ==0)
	    return true;
	else
	    return false;
    }

    public Expression getUsub() {
        return new Exponential(this.base, new Variable());
    }

    
} 
