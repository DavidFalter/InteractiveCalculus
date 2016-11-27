/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructureElements;

import DataStructureElements.Visitor.Compare;

/**
 *
 * @author rthec
 */
public abstract class UnaryExpression extends Expression
{
    public abstract String getName();
    @Override
    public int getPEMDASLevel(){
	return 100;
    }
    
    public boolean equals(UnaryExpression ue){
	if(this.getName().equals(ue.getName()))
	{
	    return Compare.cmp(this.getExpression(), ue.getExpression()) ==0;
	}
	return false;
    }
}
