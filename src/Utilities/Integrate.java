/*
 *  University of Central Florida
 *  COP 3330
 *  Author: Jonathan Gillis
 */
package Utilities;

import DataStructureElements.*;
import DataStructureElements.Visitor.*;
import java.util.*;

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
        i.result = ShrinkTree.shrink(i.result);
        i.result = Simplify.simplify(i.result);
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
	result = uSub(aThis);
        if (result instanceof Constant && ((Constant)result).getValue() == 0){
            result = byParts(aThis);
        }
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
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visitACos(Arccos aThis) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visitASin(Arcsin aThis) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visitACot(Arccot aThis) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visitSec(Sec aThis) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visitCSC(Csc aThis) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visitASec(Arcsec aThis) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visitACSC(Arccsc aThis) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visitCot(Cot aThis) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visitQuotient(Quotient aThis) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visitLn(Ln aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitLog(Log aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Expression uSub(Product aThis){
        ArrayList<Expression> oldHolder = new ArrayList<>();
        ArrayList<Expression> holder = new ArrayList<>();
        ArrayList<Expression> holderTemp = new ArrayList<>();
        ArrayList<Expression> holderU = new ArrayList<>();
        ArrayList<Expression> holderDU = new ArrayList<>();
        Product pTemp, p;
        Expression u, du, compdu;
        Constant cu, cdu, stepcu;
        
        stepcu = new Constant(1);
        
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
        if (holder.isEmpty()){
            steps.add("Take the integral of " + Stringifier.stringify(c));
            return integrate(c);
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
                return p;
            }
            else {
                holder.add(0, new Constant(1));
            }
        }
               
        oldHolder.addAll(holder);
        
        for (int i = 0; i < holder.size(); i++){
            //using holder.get(i) as u
            cu = new Constant(1);
            cdu = new Constant(1);
            holder.clear();
            holder.addAll(oldHolder);
            holderTemp.clear();
            holderTemp.addAll(holder);
            holderTemp.remove(i);
            
            //if holder.get(i)
            if (holder.get(i).getExpression() instanceof Variable){
                holder.add(i, new Power(1, holder.get(i)));
                holder.remove(i+1);
            }
            
            u = holder.get(i);
            compdu = Derive.derive(u.getExpression());
            compdu = ShrinkTree.shrink(compdu);            
            compdu = Simplify.simplify(compdu);
            du = new Product(holderTemp);
            du = ShrinkTree.shrink(du);
            du = Simplify.simplify(du);           
            
            if (compdu instanceof Product){
                holderU = ((Product)compdu).getList();
                if (holderU.get(0) instanceof Constant){
                    stepcu = new Constant(((Constant) holderU.get(0)).getValue());
                    cu = new Constant(1 / stepcu.getValue());
                    holderU.remove(0);
                }
                if (holderU.size() == 1){
                    compdu = holderU.get(0);
                }
                else {
                    compdu = new Product(holderU);
                }
            }
            else if (compdu instanceof Constant){
                stepcu = new Constant(((Constant) compdu).getValue());
                cu = new Constant(1 / stepcu.getValue());
            }
            
            if (du instanceof Product){
                holderDU = ((Product)du).getList();
                if (holderDU.get(0) instanceof Constant){
                    cdu = (Constant) holderDU.get(0);
                    holderDU.remove(0);
                }
                du = new Product(holderDU);
            }
            
            if (Compare.cmp(compdu, du) == 0){
                steps.add("Let u = " + Stringifier.stringify(u.getExpression()));
                steps.add("So du = " + Stringifier.stringify(stepcu) + " * " + Stringifier.stringify(du) + " dx");
                steps.add(Stringifier.stringify(du) + " * dx = " + Stringifier.stringify(cu) + " * du");
                steps.add("Using u-substitution we replace " + Stringifier.stringify(du) + " with " + Stringifier.stringify(cu) + " du");
                steps.add("and we replace " + Stringifier.stringify(u.getExpression()) + " with u.");
                holder.clear();
                holder.add(0, c);
                holder.add(0, cu);
                holder.add(0, cdu);
                holder.add(integrate(u));
                Expression dummy = u;
                if (u instanceof Sin){
                    dummy = ((Sin)dummy).getUsub();
                }
                else if (u instanceof Cos){
                    dummy = ((Cos)dummy).getUsub();
                }
                else if (u instanceof Exponential){
                    dummy = ((Exponential)dummy).getUsub();
                }
                else if (u instanceof Power){
                    dummy = ((Power)dummy).getUsub();
                }
                else if (u instanceof Sum){
                    dummy = ((Sum)dummy).getUsub();
                }
                else if (u instanceof Product){
                    dummy = ((Product)dummy).getUsub();
                }
                steps.add("Now we take the integral of " + Stringifier.stringifyu(dummy) + " which is " + Stringifier.stringifyu(Integrate.integrate(dummy)));
                steps.add("Replace u with " + Stringifier.stringify(u.getExpression()) + " to get our final answer:");
                p = new Product(holder);
                p = (Product) ShrinkTree.shrink(p);
                p = Simplify.simplifyProduct(p);
                steps.add(Stringifier.stringify(p));
                return p;
            }
        }
        return new Constant(0);
    }
    
    
    //For funsies
    private Expression byParts(Product aThis){        
        return new Constant(0);
    }
}

