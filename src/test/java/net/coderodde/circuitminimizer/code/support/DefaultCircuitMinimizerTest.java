package net.coderodde.circuitminimizer.code.support;

import java.util.List;
import net.coderodde.circuitminimizer.domain.AbstractGate;
import net.coderodde.circuitminimizer.domain.support.ANDGate;
import net.coderodde.circuitminimizer.domain.support.Constant0Gate;
import net.coderodde.circuitminimizer.domain.support.Constant1Gate;
import net.coderodde.circuitminimizer.domain.support.InputGate;
import net.coderodde.circuitminimizer.domain.support.NOTGate;
import net.coderodde.circuitminimizer.domain.support.ORGate;
import org.junit.Test;
import static org.junit.Assert.*;

public class DefaultCircuitMinimizerTest {
    
    private final DefaultCircuitMinimizer minimizer;
    
    public DefaultCircuitMinimizerTest() {
        this.minimizer = new DefaultCircuitMinimizer();
    }
    
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
    public void testRemoveConstantChain() {
        final InputGate input = new InputGate("A");
        Constant0Gate gate0a = new Constant0Gate();
        Constant0Gate gate0b = new Constant0Gate();
        Constant1Gate gate1 = new Constant1Gate();
        
        input.addOutputGate(gate0a);
        gate0a.setInputGate1(input);
        
        gate0a.addOutputGate(gate1);
        gate1.setInputGate1(gate0a);
        
        gate1.addOutputGate(gate0b);
        gate0b.setInputGate1(gate1);
        
        final List<AbstractGate> result = minimizer.minimize(gate1);
        
        assertEquals(2, result.size());
        
        assertTrue(result.contains(input));
        
        assertTrue(result.contains(gate0a) 
                || result.contains(gate0b)
                || result.contains(gate1));
        
        assertFalse(result.contains(gate0a) && result.contains(gate0b));
        assertFalse(result.contains(gate0a) && result.contains(gate1));
        assertFalse(result.contains(gate0b) && result.contains(gate1));
    }
    
    @Test
    public void testConstantNot() {
        final InputGate input = new InputGate("A");
        final Constant0Gate gate0a = new Constant0Gate();
        final Constant0Gate gate0b = new Constant0Gate();
        final Constant1Gate gate1 = new Constant1Gate();
        final NOTGate not = new NOTGate();
        
        input.addOutputGate(gate0b);
        gate0b.setInputGate1(input);
        
        gate0b.addOutputGate(gate1);
        gate1.setInputGate1(gate0b);
        
        gate1.addOutputGate(gate0a);
        gate0a.setInputGate1(gate1);
        
        gate0a.addOutputGate(not);
        not.setInputGate1(gate0a);
        
        final List<AbstractGate> result = minimizer.minimize(input);
        
        assertEquals(2, result.size());
        assertTrue(result.contains(input));
        assertFalse(result.contains(not));
        assertTrue(result.contains(gate0a)
                    || result.contains(gate0b)
                    || result.contains(gate1));
        assertFalse(result.contains(gate0a) && result.contains(gate0b));
        assertFalse(result.contains(gate0a) && result.contains(gate1));
        assertFalse(result.contains(gate0b) && result.contains(gate1));
    }
}
