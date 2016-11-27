/*
 *  University of Central Florida
 *  COP 3330
 *  Author: Jonathan Gillis
 */
package Utilities;

import DataStructureElements.Arccos;
import DataStructureElements.Arccot;
import DataStructureElements.Arccsc;
import DataStructureElements.Arcsec;
import DataStructureElements.Arcsin;
import DataStructureElements.Arctan;
import DataStructureElements.Constant;
import DataStructureElements.Cos;
import DataStructureElements.Cot;
import DataStructureElements.Csc;
import DataStructureElements.Exponential;
import DataStructureElements.Power;
import DataStructureElements.Product;
import DataStructureElements.Sec;
import DataStructureElements.Sin;
import DataStructureElements.Sum;
import DataStructureElements.Tan;
import DataStructureElements.*;
import DataStructureElements.Visitor.Compare;
import DataStructureElements.Visitor.DSEVisitor;
import java.util.*;
import java.lang.*;

/**
 *
 * @author gillis
 */
public class Integrate extends DSEVisitor {

    Expression result;
    static List<String> steps = new ArrayList<>();
    
    public static List<String> getSteps(){
        return steps;
    }
    
    public static void clearSteps(){
    	steps.clear();
    }
    
    public static  Expression integrate(Expression e){
	Integrate i = new Integrate();
	i.visit(e);
	return i.result;
    }
    
    @Override
    public void visitConstant(Constant aThis) {
	ArrayList<Expression> holder = new ArrayList<>();
        holder.add(aThis);
        holder.add(new Variable());
        result = new Product(holder);
    }

    @Override
    public void visitVariable(Variable aThis) {
	ArrayList<Expression> holder = new ArrayList<>();
	holder.add(new Constant(1.0/2.0d));
	holder.add(new Power(2, aThis));
	
	result = new Product(holder);
    }

    @Override
    public void visitProduct(Product aThis) {
	ArrayList<Expression> holder = new ArrayList<>();
        ArrayList<Expression> holderTemp = new ArrayList<>();
        ArrayList<Expression> holderU = new ArrayList<>();
        ArrayList<Expression> holderDU = new ArrayList<>();
        Product pTemp;
        Expression u;
        Expression du;
        Constant cu;
        Constant cdu;
        Product p;
        Constant stepcu = new Constant(1);
        
	holder = aThis.getList();
        Constant c;
        if (holder.get(0) instanceof Constant){
            c = (Constant) holder.get(0);
            holder.remove(0);
        }
        else {
            c = new Constant(1);
        }
        // Integral of a constant
        if (holder.size() == 0){
            steps.add("Take the integral of " + Stringifier.stringify(c));
            result = integrate(c);
            return;
        }
        if (holder.size() == 1){
            if (holder.get(0).getExpression() instanceof Variable ||
                    holder.get(0) instanceof Constant){
                steps.add("Take of integral of " + Stringifier.stringify(holder.get(0)));
                holder.add(integrate(holder.get(0)));
                holder.remove(0);
                holder.add(0, c);
                p = new Product(holder);
                p = (Product) ShrinkTree.shrink(p);
                p = Simplify.simplifyProduct(p);
                result = p;
                return;
            }
            else {
                holder.add(0, new Constant(1));
            }
        }
        if (holder.size() <= 2){
            for (int i = 0; i < holder.size(); i++){
                holderTemp.addAll(holder);
                holderTemp.remove(i);                
                u = holderTemp.get(0).getExpression().getDerivative();
                u = ShrinkTree.shrink(u);
                if (u instanceof Sum){
                    u = Simplify.simplifySum((Sum) u);
                    if (((Sum)u).getList().size() == 1){
                        u = ((Sum)u).getList().get(0);
                    }
                }
                else if (u instanceof Product){
                    u = Simplify.simplifyProduct((Product) u);
                    if (((Product)u).getList().size() == 1){
                        u = ((Product)u).getList().get(0);
                    }
                }
                du = holder.get(i);
                cu = new Constant(1);
                cdu = new Constant(1);
                if (u instanceof Product){
                    holderU = ((Product)u).getList();
                    if (holderU.get(0) instanceof Constant){
                        stepcu = new Constant(((Constant) holderU.get(0)).getValue());
                        cu = new Constant(1 / ((Constant) holderU.get(0)).getValue());
                        holderU.remove(0);
                    }
                    if (holderU.size() == 1){
                        u = holderU.get(0);
                    }
                }
                
                if (du instanceof Product){
                    holderU = ((Product)du).getList();
                    if (holderDU.get(0) instanceof Constant){
                        cdu = (Constant) holderDU.get(0);
                        holderDU.remove(0);
                    }
                    du = new Product(holderDU);
                }
                if (Compare.cmp(u, du) == 0){
                    steps.add("Let u = " + Stringifier.stringify(holderTemp.get(0).getExpression()));
                    steps.add("So du = " + Stringifier.stringify(stepcu) + " * " + Stringifier.stringify(du));
                    steps.add(Stringifier.stringify(du) + " = " + Stringifier.stringify(cu) + " * du");
                    steps.add("Using u-substitution we replace " + Stringifier.stringify(du) + " with " + Stringifier.stringify(cu) + " du");
                    steps.add("and we replace " + Stringifier.stringify(holderTemp.get(0).getExpression()) + " with u.");
                    holder.clear();
                    holder.add(0, c);
                    holder.add(0, cu);
                    holder.add(0, cdu);
                    holder.add(integrate(holderTemp.get(0)));
                    Expression dummy = holderTemp.get(0);
                    if (holderTemp.get(0) instanceof Sin){
                        dummy = ((Sin)dummy).getUsub();
                    }
                    else if (holderTemp.get(0) instanceof Cos){
                        dummy = ((Cos)dummy).getUsub();
                    }
                    else if (holderTemp.get(0) instanceof Exponential){
                        dummy = ((Exponential)dummy).getUsub();
                    }
                    else if (holderTemp.get(0) instanceof Power){
                        dummy = ((Power)dummy).getUsub();
                    }
                    else if (holderTemp.get(0) instanceof Sum){
                        dummy = ((Sum)dummy).getUsub();
                    }
                    else if (holderTemp.get(0) instanceof Product){
                        dummy = ((Product)dummy).getUsub();
                    }
                    steps.add("Now we take the integral of " + Stringifier.stringifyu(dummy) + " which is " + Stringifier.stringifyu(Integrate.integrate(dummy)));
                    steps.add("Replace u with " + Stringifier.stringify(holderTemp.get(0).getExpression()) + " to get our final answer:");
                    p = new Product(holder);
                    p = (Product) ShrinkTree.shrink(p);
                    p = Simplify.simplifyProduct(p);
                    steps.add(Stringifier.stringify(p));
                    result = p;
                    return;
                }
            }            
        }
        result = new Constant(0);
    }

    @Override
    public void visitSum(Sum aThis) {
	ArrayList<Expression> terms = aThis.getList(),
		nsum = new ArrayList<>();
        steps.add("Integrate each term");
	for(Expression e : terms){
	    nsum.add(integrate(e));
	}
	result = Simplify.simplifySum(new Sum(nsum));
    }

    @Override
    public void visitExponential(Exponential aThis) {
	ArrayList<Expression> holder = new ArrayList<>();
        Constant c = new Constant(1 / Math.log(aThis.getBase()));
        Product p;
        holder.add(c);
        holder.add(aThis);
        p = new Product(holder);
        p = (Product) ShrinkTree.shrink(p);
        p = Simplify.simplifyProduct(p);
        result = p;
    }

    @Override
    public void visitPower(Power aThis) {
	ArrayList<Expression> holder = new ArrayList<>();
        Constant c;
        Product p;
	
	double pow = aThis.getPower();
        c = new Constant(1/(pow+1));
        holder.add(c);
        holder.add(new Power(pow+1, aThis.getExpression()));
        p = new Product(holder);
        p = (Product) ShrinkTree.shrink(p);
        p = Simplify.simplifyProduct(p);
        result = p;	
    }

    @Override
    public void visitTan(Tan aThis) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visitSin(Sin aThis) {
        ArrayList<Expression> holder = new ArrayList<>();
	Product p;
        holder.add(new Constant(-1));
        holder.add(new Cos(aThis.getExpression()));
        p = new Product(holder);
        p = (Product) ShrinkTree.shrink(p);
        p = Simplify.simplifyProduct(p);
        result = p;
    }

    @Override
    public void visitCos(Cos aThis) {
	result = new Sin(aThis.getExpression());
    }

    @Override
    public void visitATan(Arctan aThis) {
    	result = new Constant(0);
    }

    @Override
    public void visitACos(Arccos aThis) {
    	result = new Constant(0);
    }

    @Override
    public void visitASin(Arcsin aThis) {
    	result = new Constant(0);
    }

    @Override
    public void visitACot(Arccot aThis) {
    	result = new Constant(0);
    }

    @Override
    public void visitSec(Sec aThis) {
	result = new Constant(0);
    }

    @Override
    public void visitCSC(Csc aThis) {
    	result = new Constant(0);
    }

    @Override
    public void visitASec(Arcsec aThis) {
    	result = new Constant(0);
    }

    @Override
    public void visitACSC(Arccsc aThis) {
    	result = new Constant(0);
    }

    @Override
    public void visitCot(Cot aThis) {
    	result = new Constant(0);
    }

    @Override
    public void visitQuotient(Quotient aThis) {
    	result = new Constant(0);
    }

    @Override
    public void visitLn(Ln aThis) {
    	result = new Constant(0);
    }

    @Override
    public void visitLog(Log aThis) {
    	result = new Constant(0);
    }
    
    
}


class USub extends Integrate { 
    public static Expression sub(Expression e){
	USub usub = new USub();
	usub.visit(e);
	return usub.result;
    }
    
    
    @Override
    public void visitProduct(Product p){
	ArrayList<Expression> all = (ArrayList<Expression>) p.getList().clone();
	all = removeConstants(all);
	
	if(all.size() == 2){
	    
	}
	
	for(int split =1; split<= all.size(); split++ ){
	    List<Expression>
		    ll = all.subList(0, split),
		    lr =all.subList(split, all.size());
	    
	    ArrayList<Expression> left = new ArrayList<>(),
		    right = new ArrayList<>();
	    left.addAll(ll);
	    right.addAll(lr);
	    
	    Product pl = new Product(left);
	    pl = Simplify.simplifyProduct(pl);
	    Product pr = new Product(right);
	    pr = Simplify.simplifyProduct(pr);
	    Expression eleft = ShrinkTree.shrink(pl), 
		    eright = ShrinkTree.shrink(pr);
	    Expression dleft = ShrinkTree.shrink(eleft.getDerivative());
	    Expression dright = ShrinkTree.shrink(eright.getDerivative());
	    if(Compare.cmp(pl, dleft) == 0){
		result =  Integrate.integrate(pl);
		return;
	    } else if(Compare.cmp(pr, dright) == 0){
		result =  Integrate.integrate(pr);
		return;
	    }
	    
	}
	Expression dleft = ShrinkTree.shrink(all.get(0).getDerivative());
//	dleft = removeConstants(dleft);
	Expression dright = all.get(1).getDerivative();
//	dright = removeConstants(dright);
	
	dright = ShrinkTree.shrink(dright);
	if(Compare.cmp(all.get(0), dleft) == 0){
	    result = Integrate.integrate(all.get(0));
	    return;
	} else if (Compare.cmp(all.get(1), dright) == 0){
	    result = Integrate.integrate(all.get(1));
	    return;
	}
	throw new UnsupportedOperationException("too hard of a usub");
    }
    
    static ArrayList<Expression> removeConstants(ArrayList<Expression> l){
	ArrayList<Expression> n = new ArrayList<>();
	for(Expression e : l){
	    if(!(e instanceof Constant))
		n.add(e);
	}
	return n;
    }
    
}
