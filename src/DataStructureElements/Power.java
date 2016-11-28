/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructureElements;

import DataStructureElements.Visitor.Compare;
import DataStructureElements.Visitor.DSEVisitor;

public class Power extends Expression{
    private double power;
    private final Expression base;

    public Power(double power, Expression e) {
        this.power = power;
        this.base = e;
    }
    
    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public Expression getExpression() {
        return base;
    }

    @Override
    public void accept(DSEVisitor v) {
	v.visitPower(this);
    }

    @Override
    public int getPEMDASLevel() {
	return 3;
    }

    public boolean equals(Power o) {
	if(getPower() == o.getPower() && Compare.cmp(base, o.base) ==0)
	    return true;
	else
	    return false;
    }

    public Expression getUsub() {
        return new Power(this.power, new Variable());
    }
}
