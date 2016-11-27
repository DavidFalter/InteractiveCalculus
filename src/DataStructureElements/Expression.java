/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructureElements;

import DataStructureElements.Visitor.DSEVisitor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rthec
 */
public abstract class Expression {
    private static List<String> steps = new ArrayList<>();
    
    public void addStep(String str){
        steps.add(str);
    }
    
    public List<String> getSteps(){
        return steps;
    }
    
    public abstract Expression getExpression();
    public abstract Expression getDerivative();
    public abstract Expression getIntegral();    
    
    public abstract void accept(DSEVisitor v);
    public abstract int getPEMDASLevel();
    
    //public abstract Expression getUsub();
}
