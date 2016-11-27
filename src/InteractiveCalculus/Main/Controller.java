package InteractiveCalculus.Main;

import java.util.ArrayList;
import java.util.List;

import DataStructureElements.Expression;
import Parser.Parser;
import Utilities.Derive;
import Utilities.Integrate;
import Utilities.Stringifier;

/**
 *
 * @author gillis
 */
public class Controller {
    
    public Integral solveIntegral(String s){
	
	Integrate.clearSteps();
	Expression e = Parser.parseString(s);
	Expression i = Integrate.integrate(e);
	Integral in = new Integral();
	
	in.setOrignal(s);
	in.setIntegral(Stringifier.stringify(i));
	in.setSteps(Integrate.getSteps());
        
	return in;
    }
    
    public Derivative solveDerivative(String s){
	
	Derive.clearSteps();
	Expression e = Parser.parseString(s);
	Expression d = Derive.derive(e);
        Derivative ddx = new Derivative();
        
	ddx.setOrignal(s);
	ddx.setDerivative(Stringifier.stringify(d));
	ddx.setSteps(Derive.getSteps());
        
	return ddx;
    }

		

		public Original getOriginal(String string)
		{
			String equation = string;

			Original original = new Original();
			
			original.setOriginalEquation(equation);
			
			return original;
		}


}
