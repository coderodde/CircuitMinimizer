package net.coderodde.circuitminimizer.code.support;

import java.util.List;
import net.coderodde.circuitminimizer.domain.AbstractGate;
import net.coderodde.circuitminimizer.domain.support.ANDGate;
import net.coderodde.circuitminimizer.domain.support.InputGate;
import net.coderodde.circuitminimizer.domain.support.ORGate;
import org.junit.Test;
import static org.junit.Assert.*;

public class DefaultCircuitMinimizerTest {
    
    @Test
    public void testMinimize() {
        final ANDGate and = new ANDGate();
        final ORGate or = new ORGate();
        final InputGate inputGate1 = new InputGate("A");
        
        // Build
        and.setInputGate1(inputGate1);
        and.setInputGate2(inputGate1);
        
        inputGate1.addOutputGate(and);
        
        or.setInputGate1(and);
        or.setInputGate2(and);
        
        and.addOutputGate(or);
        // End: build
        
        List<AbstractGate> result = 
                new DefaultCircuitMinimizer().minimize(or);
        
        assertEquals(1, result.size());
        assertEquals(inputGate1, result.get(0));
        
        // Build
        and.setInputGate1(inputGate1);
        and.setInputGate2(inputGate1);
        
        inputGate1.addOutputGate(and);
        
        or.setInputGate1(and);
        or.setInputGate2(and);
        
        and.addOutputGate(or);
        // End: build
        
        result = new DefaultCircuitMinimizer().minimize(and);
        
        assertEquals(1, result.size());
        assertEquals(inputGate1, result.get(0));
        
        // Build
        and.setInputGate1(inputGate1);
        and.setInputGate2(inputGate1);
        
        inputGate1.addOutputGate(and);
        
        or.setInputGate1(and);
        or.setInputGate2(and);
        
        and.addOutputGate(or);
        // End: build
        
        result = new DefaultCircuitMinimizer().minimize(inputGate1);
        
        assertEquals(1, result.size());
        assertEquals(inputGate1, result.get(0));

        //// Another configuration
        and.clearOutputGates();
        or.clearOutputGates();
        inputGate1.clearOutputGates();
        
        // Build
        or.setInputGate1(inputGate1);
        or.setInputGate2(inputGate1);
        
        inputGate1.addOutputGate(or);
        
        and.setInputGate1(or);
        and.setInputGate2(or);
        
        or.addOutputGate(and);
        // End: build
        
        result = new DefaultCircuitMinimizer().minimize(or);
        
        assertEquals(1, result.size());
        assertEquals(inputGate1, result.get(0));
        
        // Build
        or.setInputGate1(inputGate1);
        or.setInputGate2(inputGate1);
        
        inputGate1.addOutputGate(or);
        
        and.setInputGate1(or);
        and.setInputGate2(or);
        
        or.addOutputGate(and);
        // End: build
        
        result = new DefaultCircuitMinimizer().minimize(and);
        
        assertEquals(1, result.size());
        assertEquals(inputGate1, result.get(0));
        
        // Build
        or.setInputGate1(inputGate1);
        or.setInputGate2(inputGate1);
        
        inputGate1.addOutputGate(or);
        
        and.setInputGate1(or);
        and.setInputGate2(or);
        
        or.addOutputGate(and);
        // End: build
        
        result = new DefaultCircuitMinimizer().minimize(inputGate1);
        
        assertEquals(1, result.size());
        assertEquals(inputGate1, result.get(0));
        
    }

    @Test
    public void testStep() {
    }

    @Test
    public void testTryEliminateDuplicateInput() {
    }
    
}
