/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import DataStructureElements.*;
import java.util.*;

public class ShrinkTree {
    public static Expression shrink(Expression e){
        Sum tempSum;
        Product tempProduct;
        ArrayList<Expression> holder;
        
        if (!(e instanceof Container)){
            if (e instanceof Variable || e instanceof Constant)
                return e;
            else if (e instanceof Quotient){
                shrink(((Quotient) e).getNumerator());
                shrink(((Quotient) e).getDenominator());
            }
            else
                shrink(e.getExpression());
        }
        else {            
            if (e instanceof Container){                
                if (((Container)e).getList().size() == 1){
                    e = ((Container)e).getList().get(0);
                    return e;
                }
                if (e instanceof Sum){
                    holder = ((Sum)e).getList();
                    for (int i = 0; i < holder.size(); i++){
                        shrink(holder.get(i));
                        if (holder.get(i) instanceof Sum){
                            tempSum = (Sum) holder.get(i);
                            holder.addAll(tempSum.getList());
                            holder.remove(i);
                        }
                    }
                    e = new Sum(holder);
                }
                else if (e instanceof Product){
                    holder = ((Product)e).getList();
                    for (int i = 0; i < holder.size(); i++){
                        shrink(holder.get(i));
                        if (holder.get(i) instanceof Sum){
                            tempSum = (Sum) holder.get(i);
                            if (tempSum.getList().size() == 1 && 
                                    tempSum.getList().get(0) instanceof Product){
                                tempProduct = (Product) tempSum.getList().get(0);
                                holder.addAll(tempProduct.getList());
                                holder.remove(i);
                            }                                
                        }
                        if (holder.get(i) instanceof Product){
                            tempProduct = (Product) holder.get(i);
                            holder.addAll(tempProduct.getList());
                            holder.remove(i);
                        }
                    }
                    e = new Product(holder);
                }
            }
        }
        return e;
    }
}
