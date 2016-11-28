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
    public abstract Expression getExpression();
    public abstract Expression getUsub();
    public abstract void accept(DSEVisitor v);
    public abstract int getPEMDASLevel();
    
    //public abstract Expression getUsub();
}
