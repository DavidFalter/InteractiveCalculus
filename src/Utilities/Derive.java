/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import DataStructureElements.*;
import DataStructureElements.Visitor.*;
import java.util.*;

public class Derive extends DSEVisitor{
    Expression result;
    static List<String> steps = new ArrayList<>();
    public static void clearSteps(){
    	steps.clear();
    }
    public static List<String> getSteps(){
        return steps;
    }
    
    public static  Expression derive(Expression e){
	Derive i = new Derive();
	i.visit(e);
        i.result = ShrinkTree.shrink(i.result);
        i.result = Simplify.simplify(i.result);
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
                holderD.add(Derive.derive(tempHolder.get(i)));
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
            
            for (int i = 0; i < countExpressions; i++){
                String dummy = "";
                ArrayList<Expression>  productHolder = new ArrayList<>();
                for (int j = 0; j < countExpressions; j++){
                    if (j != i)
                        dummy = dummy + "f" + (i+1) + "*f" + (j+1) + " = ";
                }
                for (int j = 0; j < countExpressions; j++){                   
                    if (j != 0)
                        dummy += " * ";
                    if (i == j){
                        dummy += Stringifier.stringify(holderD.get(i));
                        productHolder.add(holderD.get(i));
                    }
                    else{
                        dummy += Stringifier.stringify(tempHolder.get(j));
                        productHolder.add(tempHolder.get(j));
                    }                    
                }
                steps.add(dummy);
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
                if (sumHolder.isEmpty()){
                    result = Derive.derive(c);
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
           steps.add("For " + Stringifier.stringify(holder.get(i)) + ":");
           holderD.add(Derive.derive(holder.get(i)));
           steps.add("The derivative is " + Stringifier.stringify(holderD.get(i)));
       }
       
       s = new Sum(holderD);
       s = ShrinkTree.shrink(s);
       s = Simplify.simplify(s);
       result = s;
    }

    @Override
    public void visitTan(Tan aThis) {
        Expression e;
        e = aThis.getExpression();
        Power p = new Power(2, new Sec(e));              
        
        if (e instanceof Variable){
            result = p;
        }
        else{            
            result = chainRule(p, e, aThis);
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
            result = chainRule(c, e, aThis);
        }
    }

    @Override
    public void visitATan(Arctan aThis) {
        Expression e, num, denom;
        Sum s;
        Quotient q;
        ArrayList<Expression> sum = new ArrayList<>();
        
        e = aThis.getExpression();
        num = new Constant(1);
        
        sum.add(new Constant(1));
        sum.add(new Power(2, e));
        s = new Sum(sum);
        
        denom = s;
        
        q = new Quotient(num, denom);
               
        if (e instanceof Variable){
            result = q;
        }
        else {
            result = chainRule(q, e, aThis);
        }  
    }

    @Override
    public void visitPower(Power aThis) {
        Product p;
        Expression base = aThis.getExpression();
        ArrayList<Expression> product = new ArrayList<>();
        double power = aThis.getPower();
        
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
        
        if (!(base instanceof Variable)){
            result = chainRule(p, base, aThis);
        }      
        else {
            result = p;
        }
    }

    @Override
    public void visitACos(Arccos aThis) {
        Expression e, num, denom;
        Sum s;
        Product p;
        Quotient q;
        ArrayList<Expression> sum = new ArrayList<>();
        ArrayList<Expression> product = new ArrayList<>();
        
        e = aThis.getExpression();
        num = new Constant(1);
        
        product.add(new Constant(-1));
        product.add(new Power(2, e));
        p = new Product(product);
        
        sum.add(new Constant(1));
        sum.add(p);
        s = new Sum(sum);
        
        denom = new Power(.5, s);
        
        q = new Quotient(num, denom);
        
        product.clear();
        product.add(new Constant(-1));
        product.add(q);
        p = new Product(product);        
        
        if (e instanceof Variable){
            result = p;
        }
        else {
            result = chainRule(p, e, aThis);
        }  
    }

    @Override
    public void visitConstant(Constant aThis) {
        result = new Constant(0);
    }

    @Override
    public void visitCos(Cos aThis) {
        Expression e, pr;
        e = aThis.getExpression();
        Sin s = new Sin(e);
        ArrayList<Expression> product = new ArrayList<>();
        product.add(new Constant(-1));
        product.add(s);
        
        pr = new Product(product);
        if (e instanceof Variable){
            result = pr;
        }
        else {            
            result = chainRule(pr, e, aThis);
        }
    }

    @Override
    public void visitASin(Arcsin aThis) {
        Expression e, num, denom;
        Sum s;
        Product p;
        Quotient q;
        ArrayList<Expression> sum = new ArrayList<>();
        ArrayList<Expression> product = new ArrayList<>();
        
        e = aThis.getExpression();
        num = new Constant(1);
        
        product.add(new Constant(-1));
        product.add(new Power(2, e));
        p = new Product(product);
        
        sum.add(new Constant(1));
        sum.add(p);
        s = new Sum(sum);
        
        denom = new Power(.5, s);
        
        q = new Quotient(num, denom);
               
        if (e instanceof Variable){
            result = q;
        }
        else {
            result = chainRule(q, e, aThis);
        }        
    }

    @Override
    public void visitACot(Arccot aThis) {
        Expression e, num, denom;
        Sum s;
        Product p;
        Quotient q;
        ArrayList<Expression> sum = new ArrayList<>();
        ArrayList<Expression> product = new ArrayList<>();
        
        e = aThis.getExpression();
        num = new Constant(1);
        
        sum.add(new Constant(1));
        sum.add(new Power(2, e));
        s = new Sum(sum);
        
        denom = s;
        
        q = new Quotient(num, denom);
        product.add(new Constant(-1));
        product.add(q);
        p = new Product(product); 
        
        if (e instanceof Variable){
            result = p;
        }
        else {
            result = chainRule(p, e, aThis);
        }
    }

    @Override
    public void visitSec(Sec aThis) {
        Expression e, pr;
        e = aThis.getExpression();
        ArrayList<Expression> product = new ArrayList<>();
        product.add(new Sec(e));
        product.add(new Tan(e));
        pr = new Product(product);
        if (e instanceof Variable){
            result = pr;
        }
        else {            
            result = chainRule(pr, e, aThis);
        }
    }

    @Override
    public void visitCSC(Csc aThis) {
        Expression e, pr;
        e = aThis.getExpression();
        ArrayList<Expression> product = new ArrayList<>();
        product.add(new Constant(-1));
        product.add(new Csc(e));
        product.add(new Cot(e));
        pr = new Product(product);
        if (e instanceof Variable){
            result = pr;
        }
        else {            
            result = chainRule(pr, e, aThis);
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
        Expression e, pr;
        Power p;
        ArrayList<Expression> product = new ArrayList<>();
        
        e = aThis.getExpression();
        p = new Power(2, new Csc(e));
        product.add(new Constant(-1));
        product.add(p);
        pr = new Product(product);
        
        if (e instanceof Variable){            
            result = pr;
        }
        else{
            result = chainRule(pr, e, aThis);
        }
    }

    @Override
    public void visitQuotient(Quotient aThis) {
        Quotient q;
        Power denom;
        Expression p1, p2, temp, numerator, denominator, num;
        

        numerator = aThis.getNumerator();
        denominator = aThis.getDenominator();
        
        steps.add("Remember the quotient rule: d/dx(f / g) = (g*f' - f*g')/g^2");
        
        steps.add("f = " + Stringifier.stringify(numerator));
        steps.add("g = " + Stringifier.stringify(denominator));
        steps.add("Now find f' and g'");
        
        ArrayList<Expression> sum = new ArrayList<>();
        ArrayList<Expression> product1 = new ArrayList<>();
        ArrayList<Expression> product2 = new ArrayList<>();
        
        steps.add("To find f':");
        product1.add(denominator);
        temp = Derive.derive(numerator);
        temp = ShrinkTree.shrink(temp);
        temp = Simplify.simplify(temp);
        
        steps.add("f' = " + Stringifier.stringify(Derive.derive(numerator)));

        product1.add(temp);
        p1 = new Product(product1);
        p1 = (Product) ShrinkTree.shrink(p1);
        p1 = Simplify.simplify(p1);
        sum.add(p1);
        
        steps.add("To find g':");
        product2.add(numerator);
        product2.add(Derive.derive(denominator));
        steps.add("g' = " + Stringifier.stringify(Derive.derive(denominator)));
        p2 = new Product(product2);
        p2 = ShrinkTree.shrink(p2);
        p2 = Simplify.simplify(p2);
                
        sum.add(p2);
        num = new Sum(sum);
        num = ShrinkTree.shrink(num);
        num = Simplify.simplify(num);
        
        denom = new Power(2, denominator);
        
        steps.add("Now substitute everything in to the quotient rule to get " + 
                Stringifier.stringify(new Quotient(num, denom)));
        
        result = new Quotient(num, denom);
    }

    @Override
    public void visitExponential(Exponential aThis) {
        Expression e, p;
        e = aThis.getExpression();

        ArrayList<Expression> product = new ArrayList<>();
        product.add(new Constant(Math.log(aThis.getBase())));
        product.add(aThis);
        p = new Product(product);
        if (e instanceof Variable){            
            result = p;
        }
        else{            
            result = chainRule(p, e, aThis);          
        }
    }

    @Override
    public void visitLn(Ln aThis) {
        Expression e = aThis.getExpression();
        if (!(e instanceof Variable)){
            steps.add("For this term we use the chain rule take the derivative of the inside, let u(x) = " + Stringifier.stringify(e));
            steps.add("so u'(x) = " + Stringifier.stringify(Derive.derive(e)));
            Expression temp = Derive.derive(e);
            temp = ShrinkTree.shrink(temp);
            temp = Simplify.simplify(temp);
            steps.add("Remember that with the chain rule d/dx(f(u(x)) = u'(x) * f'(u(x)) ");        
            steps.add("Now take the derivative of the outside with respect to u, f(u) = " + Stringifier.stringifyu(aThis.getUsub()));
            steps.add("f'(u) = " + Stringifier.stringifyu(Derive.derive(aThis.getUsub())));
            steps.add("Replacing u with " + Stringifier.stringify(e) + " we get " + Stringifier.stringify(new Quotient(Derive.derive(e),e)));
            result = new Quotient(temp,e);
        }
        else        
            result = new Quotient(Derive.derive(e),e);
    }

    @Override
    public void visitLog(Log aThis) {
        Expression e, p, temp;
        ArrayList<Expression> product = new ArrayList<>();
        e = aThis.getExpression();
        product.add(new Constant(1/Math.log(10)));
        
        temp = new Product(product);
        
        if (!(e instanceof Variable)){
            steps.add("For this term we use the chain rule take the derivative of the inside, let u(x) = " + Stringifier.stringify(e));
            steps.add("so u'(x) = " + Stringifier.stringify(Derive.derive(e)));
            product.add(Derive.derive(e));
            temp = new Product(product);
            temp = ShrinkTree.shrink(temp);
            temp = Simplify.simplify(temp);
            steps.add("Remember that with the chain rule d/dx(f(u(x)) = u'(x) * f'(u(x)) ");        
            steps.add("Now take the derivative of the outside with respect to u, f(u) = " + Stringifier.stringifyu(aThis.getUsub()));
            steps.add("f'(u) = " + Stringifier.stringifyu(Derive.derive(aThis.getUsub())));
            steps.add("Replacing u with " + Stringifier.stringify(e) + " we get " + Stringifier.stringify(new Quotient(Derive.derive(e),e)));
            result = new Quotient(temp,e);
        }
        else        
            result = new Quotient(temp,e);
    }
    
    private Expression chainRule(Expression fprime, Expression u, Expression orig){
        ArrayList<Expression> product = new ArrayList<>();
        Expression p, uprime;
        
                
        steps.add("Remember that with the chain rule d/dx(f(u(x)) = u'(x) * f'(u(x)) ");
        steps.add("Let u(x) = " + Stringifier.stringify(u));
        steps.add("Let f(u) = " + Stringifier.stringifyu(orig.getUsub()));
        uprime = Derive.derive(u);
        uprime = ShrinkTree.shrink(uprime);
        uprime = Simplify.simplify(uprime);
        
        product.add(uprime);
        steps.add("so u'(x) = " + Stringifier.stringify(uprime));
        steps.add("and f'(u) = " + Stringifier.stringifyu(Derive.derive(orig.getUsub())));
        
        product.add(fprime);
        
        p = new Product(product);
        p = ShrinkTree.shrink(p);
        p = Simplify.simplify(p);
        steps.add("Replacing u with " + Stringifier.stringify(u) + " we get " + Stringifier.stringify(p));
        return p;
    }   
}
