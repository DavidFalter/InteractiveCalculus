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
public class Constant extends TerminalExpression implements Comparable<Constant>{
    double value;
    
    public Constant(double v) {
        this.value = v;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public Expression getExpression() {
        return this; //To change body of generated methods, choose Tools | Templates.
    }

    
    
    @Override
    public void accept(DSEVisitor v) {
	v.visitConstant(this);
    }

    @Override
    public int compareTo(Constant o) {
	return (int) (this.getValue() - o.value);
    }

    public Expression getUsub() {
        return new Constant(this.value);
    }
    
}
