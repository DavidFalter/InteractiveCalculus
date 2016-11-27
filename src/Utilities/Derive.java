/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

public class Derive extends DSEVisitor{
    Expression result;
    static List<String> steps = new ArrayList<>();
    
    public static List<String> getSteps(){
        return steps;
    }
    
    public static void clearSteps(){
    	steps.clear();
    }
    
    public static  Expression derive(Expression e){
	Derive i = new Derive();
	i.visit(e);
	return i.result;
    }
    
    @Override
    public void visitVariable(Variable aThis) {
        result = new Constant(1);
    }       
    
    @Override
    public void visitProduct(Product aThis) {
        Expression s;
        Expression p;
        Constant c = null;
        ArrayList<Expression> holder = aThis.getList();
        ArrayList<Expression> sumHolder = new ArrayList<>();
        ArrayList<Expression> holderD = new ArrayList<>();
        ArrayList<Expression> tempHolder = new ArrayList<>();
        ArrayList<Expression> pHolder = new ArrayList<>();
        
        int countExpressions = 0;
        
        
        tempHolder.addAll(holder);
        
        if (tempHolder.get(0) instanceof Constant){
        	c = (Constant) tempHolder.get(0);
            tempHolder.remove(0);
        }
        
        if (tempHolder.size() > 1){
            steps.add("We must use the product rule to take the derivative of f = f1 * f2");
            steps.add("Remember d/dx(f1 * f2) = f1'*f2 + f1'*f2");
            steps.add("Start by finding the derivative of each function: ");
        }
        
        for (int i = 0; i < tempHolder.size(); i++){
            if (!(tempHolder.get(i) instanceof Constant)){
                countExpressions++;                
                holderD.add(tempHolder.get(i).getDerivative());
                if (tempHolder.size() > 1){
                    steps.add("f" + (i+1) + "' = " + Stringifier.stringify(holderD.get(i)));
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
            steps.add("Take the derivative of this term using basic rules.");
            result = p;
        }        
        else{
            if (countExpressions > 2)
                steps.add("Since we have more than 2 functions: d/dx(f1 * f2 *...* fn) = f1'*f2*f3...*fn + f1*f2'...*fn + f1*f2*...*fn'");

            for (int i = 0; i < countExpressions; i++){
                steps.add("f" + (i+1) + " = " + Stringifier.stringify(tempHolder.get(i)));
            }

            steps.add("f' = ");
            for (int i = 0; i < countExpressions; i++){
                ArrayList<Expression>  productHolder = new ArrayList<>();
                for (int j = 0; j < countExpressions; j++){
                    if (i == j){
                        steps.add("f" + (i+1) + "' = " + Stringifier.stringify(holderD.get(i)));
                        productHolder.add(holderD.get(i));
                    }
                    else{
                        steps.add("f" + (j+1) + " = " + Stringifier.stringify(tempHolder.get(j)));
                        productHolder.add(tempHolder.get(j));
                    }
                    steps.add("+");
                }
                p = new Product(productHolder);
                p = ShrinkTree.shrink(p);
                p = Simplify.simplifyProduct((Product) p);
                sumHolder.add(p);
            }

            s = new Sum(sumHolder);
            s = ShrinkTree.shrink(s);
            s = Simplify.simplifySum((Sum) s);
            steps.add("So f' = " + Stringifier.stringify(s));
            if (c != null){
                if (sumHolder.size() == 0){
                    result = c.getDerivative();
                }
                pHolder.add(c);
                pHolder.add(s);
                p = new Product(pHolder);
                result = p;
            }
            else {
                result = s;
            }
        }
    }

    @Override
    public void visitSum(Sum aThis) {
       Expression s;
       ArrayList<Expression> holder = aThis.getList();
       ArrayList<Expression> holderD = new ArrayList<>();
       steps.add("Find the derivative of each term in " + Stringifier.stringify(aThis));
       for (int i = 0; i < holder.size(); i++){
           holderD.add(holder.get(i).getDerivative());
       }
       
       s = new Sum(holderD);
       s = ShrinkTree.shrink(s);
       s = Simplify.simplifySum((Sum) s);
       result = s;
    }

    @Override
    public void visitTan(Tan aThis) {
        Expression e = aThis.getExpression();
        Power p = new Power(2, new Sec(e));
        Product pr;
        ArrayList<Expression> product = new ArrayList<>();
        
        if (e instanceof Variable){
            result = p;
        }
        else{
            steps.add("For this term we use the chain rule take the derivative of the inside, let u(x) = " + Stringifier.stringify(e));
            steps.add("so u'(x) = " + Stringifier.stringify(e.getDerivative()));
                        
            product.add(p);
            product.add(e.getDerivative());
            pr = new Product(product);
            pr = (Product) ShrinkTree.shrink(pr);
            pr = Simplify.simplifyProduct(pr);
            steps.add("Remember that with the chain rule d/dx(f(u(x)) = u'(x) * f'(u(x)) ");        
            steps.add("Now take the derivative of the outside with respect to u, f(u) = " + Stringifier.stringifyu(aThis.getUsub()));
            steps.add("f'(u) = " + Stringifier.stringifyu(aThis.getUsub().getDerivative()));
            steps.add("Replacing u with " + Stringifier.stringify(e) + " we get " + Stringifier.stringify(pr));
            result = pr;
        }
    }

    @Override
    public void visitSin(Sin aThis) {
        Expression e = aThis.getExpression();
        Cos c = new Cos(e);
        ArrayList<Expression> product = new ArrayList<>();
        
        if (e instanceof Variable){
            result = c;
        }
        else {
            steps.add("For this term we use the chain rule take the derivative of the inside, let u(x) = " + Stringifier.stringify(e));
            steps.add("so u'(x) = " + Stringifier.stringify(e.getDerivative()));            
                       
            product.add(c);
            product.add(e.getDerivative());
            steps.add("Remember that with the chain rule d/dx(f(u(x)) = u'(x) * f'(u(x)) ");        
            steps.add("Now take the derivative of the outside with respect to u, f(u) = " + Stringifier.stringifyu(aThis.getUsub()));
            steps.add("f'(u) = " + Stringifier.stringifyu(aThis.getUsub().getDerivative()));
            steps.add("Replacing u with " + Stringifier.stringify(e) + " we get " + Stringifier.stringify(new Product(product)));
            result = new Product(product);
        }
    }

    @Override
    public void visitATan(Arctan aThis) {
    	result = new Constant(0);
    }

    @Override
    public void visitPower(Power aThis) {
        Product p;
        Expression base = aThis.getExpression();
        ArrayList<Expression> product = new ArrayList<>();
        double power = aThis.getPower();
        
        if (!(base instanceof Variable)){
            steps.add("For this term we use the chain rule take the derivative of the inside, let u(x) = " + Stringifier.stringify(base));
            product.add(base.getDerivative());
            steps.add("so u'(x) = " + Stringifier.stringify(base.getDerivative()));
            steps.add("Remember that with the chain rule d/dx(f(u(x)) = u'(x) * f'(u(x)) ");        
            steps.add("Now take the derivative of the outside with respect to u, f(u) = " + Stringifier.stringifyu(aThis.getUsub()));
            steps.add("f'(u) = " + Stringifier.stringifyu(aThis.getUsub().getDerivative()));
            p = new Product(product);
            p = (Product) ShrinkTree.shrink(p);
            p = Simplify.simplifyProduct(p);
            steps.add("Replacing u with " + Stringifier.stringify(base) + " we get " + Stringifier.stringify(p));
            result = p;
        }
        
        if (power > 2){
            product.add(new Constant(power));
            product.add(new Power(power-1,base));
        }
        else {
            product.add(new Constant(power));
            product.add(base);
        }
        
        p = new Product(product);
        p = (Product) ShrinkTree.shrink(p);
        p = Simplify.simplifyProduct(p);
        result = p;
    }

    @Override
    public void visitACos(Arccos aThis) {
    	result = new Constant(0);
    }

    @Override
    public void visitConstant(Constant aThis) {
        result = new Constant(0);
    }

    @Override
    public void visitCos(Cos aThis) {
        Expression e = aThis.getExpression();
        Sin s = new Sin(e);
        ArrayList<Expression> product = new ArrayList<>();
        product.add(new Constant(-1));
        product.add(s);
        if (e instanceof Variable){
            result = new Product(product);
        }
        else {
            steps.add("For this term we use the chain rule take the derivative of the inside, let u(x) = " + Stringifier.stringify(e));
            steps.add("so u'(x) = " + Stringifier.stringify(e.getDerivative()));
                        
            product.add(e.getDerivative());
            
            steps.add("Remember that with the chain rule d/dx(f(u(x)) = u'(x) * f'(u(x)) ");        
            steps.add("Now take the derivative of the outside with respect to u, f(u) = " + Stringifier.stringifyu(aThis.getUsub()));
            steps.add("f'(u) = " + Stringifier.stringifyu(aThis.getUsub().getDerivative()));
            steps.add("Replacing u with " + Stringifier.stringify(e) + " we get " + Stringifier.stringify(new Product(product)));
            result = new Product(product);
        }
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
        Expression e = aThis.getExpression();
        ArrayList<Expression> product = new ArrayList<>();
        product.add(new Sec(e));
        product.add(new Tan(e));
        if (e instanceof Variable){
            result = new Product(product);
        }
        else {
            steps.add("For this term we use the chain rule take the derivative of the inside, let u(x) = " + Stringifier.stringify(e));
            steps.add("so u'(x) = " + Stringifier.stringify(e.getDerivative()));
                        
            product.add(e.getDerivative());
            
            steps.add("Remember that with the chain rule d/dx(f(u(x)) = u'(x) * f'(u(x)) ");        
            steps.add("Now take the derivative of the outside with respect to u, f(u) = " + Stringifier.stringifyu(aThis.getUsub()));
            steps.add("f'(u) = " + Stringifier.stringifyu(aThis.getUsub().getDerivative()));
            result = new Product(product);
        }
    }

    @Override
    public void visitCSC(Csc aThis) {
        Expression e = aThis.getExpression();
        ArrayList<Expression> product = new ArrayList<>();
        product.add(new Constant(-1));
        product.add(new Csc(e));
        product.add(new Cot(e));
        if (e instanceof Variable){
            result = new Product(product);
        }
        else {
            steps.add("For this term we use the chain rule take the derivative of the inside, let u(x) = " + Stringifier.stringify(e));
            steps.add("so u'(x) = " + Stringifier.stringify(e.getDerivative()));
            
            product.add(e.getDerivative());
            
            steps.add("Remember that with the chain rule d/dx(f(u(x)) = u'(x) * f'(u(x)) ");        
            steps.add("Now take the derivative of the outside with respect to u, f(u) = " + Stringifier.stringifyu(aThis.getUsub()));
            steps.add("f'(u) = " + Stringifier.stringifyu(aThis.getUsub().getDerivative()));
            steps.add("Replacing u with " + Stringifier.stringify(e) + " we get " + Stringifier.stringify(new Product(product)));
            result = new Product(product);
        }
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
        Expression e = aThis.getExpression();
        Power p = new Power(2, new Csc(e));
        Product pr;
        ArrayList<Expression> product = new ArrayList<>();
        product.add(new Constant(-1));
        product.add(p);
        if (e instanceof Variable){
            pr = new Product(product);
            result = pr;
        }
        else{
            steps.add("For this term we use the chain rule take the derivative of the inside, let u(x) = " + Stringifier.stringify(e));
            steps.add("so u'(x) = " + Stringifier.stringify(e.getDerivative()));
            product.add(e.getDerivative());
            pr = new Product(product);
            pr = (Product) ShrinkTree.shrink(pr);
            pr = Simplify.simplifyProduct(pr);
            steps.add("Remember that with the chain rule d/dx(f(u(x)) = u'(x) * f'(u(x)) ");        
            steps.add("Now take the derivative of the outside with respect to u, f(u) = " + Stringifier.stringifyu(aThis.getUsub()));
            steps.add("f'(u) = " + Stringifier.stringifyu(aThis.getUsub().getDerivative()));
            steps.add("Replacing u with " + Stringifier.stringify(e) + " we get " + Stringifier.stringify(pr));
            result = pr;
        }
    }

    @Override
    public void visitQuotient(Quotient aThis) {
        Quotient q;
        Product p1;
        Product p2;
        Power denom;
        Sum num;
        Expression numerator = aThis.getNumerator();
        Expression denominator = aThis.getDenominator();
        
        steps.add("Remember the quotient rule: d/dx(f / g) = (g*f' - f*g')/g^2");
        
        steps.add("f = " + Stringifier.stringify(numerator));
        steps.add("g = " + Stringifier.stringify(denominator));
        steps.add("Now find f' and g'");
        
        ArrayList<Expression> sum = new ArrayList<>();
        ArrayList<Expression> product1 = new ArrayList<>();
        ArrayList<Expression> product2 = new ArrayList<>();
        
        steps.add("To find f':");
        product1.add(denominator);
        product1.add(numerator.getDerivative());
        steps.add("f' = " + Stringifier.stringify(numerator.getDerivative()));
        p1 = new Product(product1);
        p1 = (Product) ShrinkTree.shrink(p1);
        p1 = Simplify.simplifyProduct(p1);
        
        
        steps.add("To find g':");
        product2.add(numerator);
        product2.add(denominator.getDerivative());
        steps.add("g' = " + Stringifier.stringify(numerator.getDerivative()));
        p2 = new Product(product2);
        p2 = (Product) ShrinkTree.shrink(p2);
        p2 = Simplify.simplifyProduct(p2);
        
        sum.add(p1);
        sum.add(p2);
        num = new Sum(sum);
        
        denom = new Power(2, denominator);
        
        steps.add("Now substitute everything in to the quotient rule to get " + 
                Stringifier.stringify(new Quotient(num, denom)));
        
        result = new Quotient(num, denom);
    }

    @Override
    public void visitExponential(Exponential aThis) {
        Expression e = aThis.getExpression();
        ArrayList<Expression> product = new ArrayList<>();
        product.add(new Constant(Math.log(aThis.getBase())));
        product.add(aThis);
        if (e instanceof Variable){
            result = new Product(product);
        }
        else{
            steps.add("For this term we use the chain rule take the derivative of the inside, let u(x) = " + Stringifier.stringify(e));
            steps.add("so u'(x) = " + Stringifier.stringify(e.getDerivative()));
                        
            product.add(e.getDerivative());
            steps.add("Remember that with the chain rule d/dx(f(u(x)) = u'(x) * f'(u(x)) ");        
            steps.add("Now take the derivative of the outside with respect to u, f(u) = " + Stringifier.stringifyu(aThis.getUsub()));
            steps.add("f'(u) = " + Stringifier.stringifyu(aThis.getUsub().getDerivative()));
            steps.add("Replacing u with " + Stringifier.stringify(e) + " we get " + Stringifier.stringify(new Product(product)));
            result = new Product(product);            
        }
    }

    @Override
    public void visitLn(Ln aThis) {
        Expression e = aThis.getExpression();
        if (!(e instanceof Variable)){
            steps.add("For this term we use the chain rule take the derivative of the inside, let u(x) = " + Stringifier.stringify(e));
            steps.add("so u'(x) = " + Stringifier.stringify(e.getDerivative()));
            Expression temp = e.getDerivative();
            steps.add("Remember that with the chain rule d/dx(f(u(x)) = u'(x) * f'(u(x)) ");        
            steps.add("Now take the derivative of the outside with respect to u, f(u) = " + Stringifier.stringifyu(aThis.getUsub()));
            steps.add("f'(u) = " + Stringifier.stringifyu(aThis.getUsub().getDerivative()));
            steps.add("Replacing u with " + Stringifier.stringify(e) + " we get " + Stringifier.stringify(new Quotient(e.getDerivative(),e)));
            result = new Quotient(temp,e);
        }
        else        
            result = new Quotient(e.getDerivative(),e);
    }

    @Override
    public void visitLog(Log aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
