/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructureElements;

import static DataStructureElements.Visitor.Compare.cmp;
import DataStructureElements.Visitor.DSEVisitor;
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
	
	for(int i = 0; i < oterms.size(); i++){
	    Expression oexpr = oterms.get(i);
            for (int j = 0; j < aterms.size(); j++){
                Expression aexpr = aterms.get(j);
                if(cmp(oexpr,aexpr) == 0){
                    aterms.remove(j);
                    break;
                }
            }    	               
	}	
	return aterms.isEmpty();
    }
    
    public Expression getUsub() {
        ArrayList<Expression> temp = new ArrayList<>();
        temp.add(new Variable());
        return new Sum(temp);
    }    
}
