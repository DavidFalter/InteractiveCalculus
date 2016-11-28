package Utilities;

import DataStructureElements.*;
import DataStructureElements.Visitor.Compare;
import java.util.*;

public class Simplify {

    static boolean isSimplifying = false;
    public static Expression simplify(Expression e){
        if (e instanceof Sum){
            return simplifySum((Sum) e);
        }
        else if (e instanceof Product){
            return simplifyProduct((Product) e);
        }
        else {
            return e;
        }
    }
    
    public static Sum simplifySum(Sum s){
        ArrayList<Expression> holder;
        ArrayList<Expression> pHolder = new ArrayList<>();
        ArrayList<Expression> newHolder = new ArrayList<>();
        ArrayList<Expression> temp;
        Constant c;
        Product p, p1, p2;
        boolean hit;
        
        holder = s.getList();
        
        // Removes unnecessary 1's from coefficients
        // Sets term = 0 if 0 is a factor
        for (int i = 0; i < holder.size(); i++){
            if (holder.get(i) instanceof Product){
                p = (Product) holder.get(i);
                if (p.getList().get(0) instanceof Constant){
                    temp = p.getList();
                    c = (Constant) p.getList().get(0);
                    if (c.getValue() == 1){                        
                        temp.remove(0);
                        holder.remove(i);
                        if (temp.isEmpty()){
                            temp.add(new Constant(1));
                        }
                        holder.add(i, new Product(temp));
                    }
                    else if (c.getValue() == 0){
                        holder.remove(i);
                        holder.add(i, new Constant(0));
                    }
                }
            }
        }
        
        if (holder.size() > 1){           
            
            newHolder.add(holder.get(0));
            pHolder.clear();

            for (int i = 1; i < holder.size(); i++){
                Constant c1;
                Constant c2;
                hit = false;
                for (int j = 0; j < newHolder.size(); j++){
                    if (holder.get(i) instanceof Product && newHolder.get(j) instanceof Product){
                        p1 = (Product) holder.get(i);
                        p2 = (Product) newHolder.get(j);

                        if (!p1.getList().isEmpty() && p1.getList().get(0) instanceof Constant){
                            pHolder = p1.getList(); 
                            c1 = (Constant) pHolder.get(0);
                            pHolder.remove(0);
                            p1 = new Product(pHolder);                        
                        }
                        else {
                            pHolder = p1.getList();
                            c1 = new Constant(1);
                        }
                        if (!p2.getList().isEmpty() && p2.getList().get(0) instanceof Constant){
                            pHolder = p2.getList(); 
                            c2 = (Constant) pHolder.get(0);
                            pHolder.remove(0);
                            p2 = new Product(pHolder);
                        }
                        else {
                            pHolder = p2.getList(); 
                            c2 = new Constant(1);
                        }
                        if (Compare.cmp(p1, p2) == 0){
                            c = new Constant(c1.getValue()+c2.getValue());
                            pHolder.add(0, c);
                            p = new Product(pHolder);
                            newHolder.remove(j);
                            newHolder.add(j, p);
                            hit = true;
                            break;
                        }
                    }
                }
                if (!hit){
                    newHolder.add(holder.get(i));
                }
            }
        }
        else {
            return s;
        }
        
        for (int i = 0; i < newHolder.size(); i++){
            if (newHolder.get(i) instanceof Constant){
                c = (Constant) newHolder.get(i);
                if (c.getValue() == 0){
                    newHolder.remove(i);
                }
            }
        }
        s = new Sum(newHolder);
        return s;
    }
           
    public static Product simplifyProduct(Product p){
	ArrayList<Expression> withoutConstants = new ArrayList<>();
        ArrayList<Expression> holder;
        ArrayList<Constant> constants;
        ArrayList<Expression> newHolder = new ArrayList<>();
        Product simpleProduct;
        boolean hit;
        
        holder = p.getList();    
        constants = removeConstants(holder);       
        
        //Multiplies all constants in a product together
        for (int i = 1; i < constants.size(); i++){
            double temp = constants.get(0).getValue();
            constants.get(0).setValue(temp * constants.get(i).getValue());
        }
        
        if (holder.size() <= 1){
            holder.add(0, constants.get(0));
            return new Product(holder);
        }
        
        //Multiplies all terms that can be multiplied together
        newHolder.add(holder.get(0));
        for (int i = 1; i < holder.size(); i++){
            hit = false;
            for (int j = 0; j < newHolder.size(); j++){
                Expression temp1;
                Expression temp2;
                Power ptemp;
                temp1 = ShrinkTree.shrink(holder.get(i));
                temp2 = ShrinkTree.shrink(newHolder.get(j));
                if (temp1 instanceof Power && temp2 instanceof Power){
                    if (Compare.cmp(temp1.getExpression(), temp2.getExpression()) == 0){
                        ptemp = new Power(((Power)temp1).getPower() + ((Power)temp2).getPower(), temp1.getExpression());
                        newHolder.remove(j);
                        newHolder.add(j, ptemp);
                        hit = true;
                        break;
                    }
                }
                else if (temp1 instanceof Power && !(temp2 instanceof Power)){                                       
                    if (Stringifier.stringify(temp1.getExpression()).compareTo(Stringifier.stringify(temp2)) == 0){
                        ((Power)temp1).setPower(((Power)temp1).getPower()+1); 
                        ptemp = (Power) temp1;
                        newHolder.remove(j);
                        newHolder.add(j, ptemp);
                        hit = true;
                        break;
                    }
                }
                 else if (temp2 instanceof Power && !(temp1 instanceof Power)){
                    if (Stringifier.stringify(temp1).compareTo(Stringifier.stringify(temp2.getExpression())) == 0){
                        ((Power)temp2).setPower(((Power)temp2).getPower()+1);
                        ptemp = (Power) temp2;
                        newHolder.remove(j);
                        newHolder.add(j, ptemp);
                        hit = true;
                        break;
                    }                    
                }
                 else {
                    if (Stringifier.stringify(temp1).compareTo(Stringifier.stringify(temp2)) == 0){
                        ptemp = new Power(2, temp1);
                        newHolder.remove(j);
                        newHolder.add(j, ptemp);
                        hit = true;
                        break;
                    }                     
                }                
            }
            if (!hit){
                newHolder.add(holder.get(i));
            }
        }
        
        if (constants.isEmpty())
            constants.add(new Constant(1));
        
        if (constants.get(0).getValue() != 1){
            newHolder.add(0, constants.get(0));
        }
        simpleProduct = new Product(newHolder);
        return simpleProduct;
    }
    
    //Removes constants from holder and returns a list of the removed constants
    public static ArrayList<Constant> removeConstants(ArrayList<Expression> product){
        ArrayList<Constant> constants = new ArrayList<>();
        
        for (int i = 0; i < product.size(); i++){
            if(product.get(i) instanceof Constant){
                constants.add((Constant) product.get(i));
                product.remove(i);
                i--;
            }
        }
        
        return constants;
    }
    
}
