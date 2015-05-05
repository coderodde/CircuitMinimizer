package net.coderodde.circuitminimizer.domain.support;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.coderodde.circuitminimizer.domain.AbstractGate;
import net.coderodde.circuitminimizer.domain.GateType;

/**
 * This class implements an OR gate.
 * 
 * @author Rodion Efremov
 * @version 1.6 
 */
public class ORGate extends AbstractGate {

    private AbstractGate inputGate1;
    private AbstractGate inputGate2;
    
    @Override
    public GateType getGateType() {
        return GateType.OR;
    }

    @Override
    public Iterable<AbstractGate> inputGates() {
        final List<AbstractGate> list = new ArrayList<>(2);
        
        if (inputGate1 != null) {
            list.add(inputGate1);
        }
        
        if (inputGate2 != null) {
            list.add(inputGate2);
        }
        
        return new Iterable<AbstractGate>() {

            @Override
            public Iterator<AbstractGate> iterator() {
                return list.iterator();
            }
        };
    }

    @Override
    public AbstractGate getInputGate1() {
        return inputGate1;
    }

    @Override
    public AbstractGate getInputGate2() {
        return inputGate2;
    }
    
    @Override
    public void changeInputGate(final AbstractGate gateToChange,
                                final AbstractGate newGate) {
        boolean changed = false;
        
        if (inputGate1 == gateToChange) {
            inputGate1 = newGate;
            changed = true;
        }
        
        if (inputGate2 == gateToChange) {
            inputGate2 = newGate;
            changed = true;
        }
        
        if (!changed) {
            throw new IllegalStateException("No gate to change.");
        }
    }
    
    @Override
    public void setInputGate1(final AbstractGate gate) {
        inputGate1 = gate;
    }

    @Override
    public void setInputGate2(final AbstractGate gate) {
        inputGate2 = gate;
    }
}
