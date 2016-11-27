/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructureElements;

import static DataStructureElements.Visitor.Compare.cmp;
import DataStructureElements.Visitor.DSEVisitor;
import Utilities.ShrinkTree;
import Utilities.Simplify;
import Utilities.Stringifier;
import java.util.*;

public class Product extends Container{
    private final ArrayList<Expression> holder;

    public Product(ArrayList<Expression> holder) {
        this.holder = (ArrayList<Expression>) holder.clone();
    }
    
    @Override
    public ArrayList<Expression> getList() {
        return (ArrayList<Expression>) holder.clone();
    }   
    
    @Override
    public Expression getDerivative() {
        Expression s;
        Expression p;
        Constant c = null;
        ArrayList<Expression> sumHolder = new ArrayList<>();
        ArrayList<Expression> holderD = new ArrayList<>();
        ArrayList<Expression> tempHolder = new ArrayList<>();
        ArrayList<Expression> pHolder = new ArrayList<>();
        
        int countExpressions = 0;
        
        
        for (int i = 0; i < holder.size(); i++){
            tempHolder.add(holder.get(i));
        }
        
        if (tempHolder.get(0) instanceof Constant){
        	c = (Constant) tempHolder.get(0);
            tempHolder.remove(0);
        }
        
        if (tempHolder.size() > 1){
            super.addStep("Now we must use the product rule to take the derivative of f = f1 * f2");
            super.addStep("Remember d/dx(f1 * f2) = f1'*f2 + f1'*f2");
            super.addStep("Start by finding the derivative of each function: ");
        }
        
        for (int i = 0; i < tempHolder.size(); i++){
            if (!(tempHolder.get(i) instanceof Constant)){
                countExpressions++;                
                holderD.add(tempHolder.get(i).getDerivative());
                if (tempHolder.size() > 1){
                    super.addStep("f" + (i+1) + "' = " + Stringifier.stringify(holderD.get(i)));
                }
            }            
        }
        
        if (countExpressions == 1){
            if (c != null){
                pHolder.add(c);
            }
            pHolder.add(holderD.get(0));
            p = new Product(pHolder);
            p = ShrinkTree.shrink(p);
            p = Simplify.simplifyProduct((Product) p);
            return p;
        }        
        
        if (countExpressions > 2)
            super.addStep("Since we have more than 2 functions: d/dx(f1 * f2 *...* fn) = f1'*f2*f3...*fn + f1*f2'...*fn + f1*f2*...*fn'");
        
        for (int i = 0; i < countExpressions; i++){
            super.addStep("f" + (i+1) + " = " + Stringifier.stringify(tempHolder.get(i)));
        }
        
        super.addStep("f' = ");
        for (int i = 0; i < countExpressions; i++){
            ArrayList<Expression>  productHolder = new ArrayList<>();
            for (int j = 0; j < countExpressions; j++){
                if (i == j){
                    super.addStep("f" + (i+1) + "' = " + Stringifier.stringify(holderD.get(i)));
                    productHolder.add(holderD.get(i));
                }
                else{
                    super.addStep("f" + (j+1) + " = " + Stringifier.stringify(tempHolder.get(j)));
                    productHolder.add(tempHolder.get(j));
                }
                super.addStep("+");
            }
            p = new Product(productHolder);
            p = ShrinkTree.shrink(p);
            p = Simplify.simplifyProduct((Product) p);
            sumHolder.add(p);
        }
                
        s = new Sum(sumHolder);
        s = ShrinkTree.shrink(s);
        s = Simplify.simplifySum((Sum) s);
        super.addStep("So f' = " + Stringifier.stringify(s));
        if (c != null){
            if (sumHolder.size() == 0){
                return c.getDerivative();
            }
            pHolder.add(c);
            pHolder.add(s);
            p = new Product(pHolder);
            return p;
        }
        else {
            return s;
        }
    }

    @Override
    public Expression getIntegral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Expression getExpression() {
        return this; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void accept(DSEVisitor v) {
	v.visitProduct(this);
    }

    @Override
    public int getPEMDASLevel() {
	return 2;
    }

    public boolean equals(Product po) {

	List<Expression> aterms = this.getList(), 
		oterms = po.getList();
	if(oterms.size() != aterms.size()){
	    return false;
	}
	
	for(int i =0; i< oterms.size(); i++){
	    Expression
		    oexpr = oterms.get(i),
		    aexpr = aterms.get(i);
	    
	    if(cmp(oexpr,aexpr) != 0){
		return false;
	    }
	}	
	return true;
    }
    
    public Expression getUsub() {
        ArrayList<Expression> temp = new ArrayList<>();
        temp.add(new Variable());
        return new Sum(temp);
    }    
}
