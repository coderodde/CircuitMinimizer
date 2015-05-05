package net.coderodde.circuitminimizer.domain.support;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.coderodde.circuitminimizer.domain.AbstractGate;
import net.coderodde.circuitminimizer.domain.GateType;

/**
 * This class implements an AND gate.
 * 
 * @author Rodion Efremov
 * @version 1.6 
 */
public class Constant1Gate extends AbstractGate {

    private AbstractGate inputGate;
    
    @Override
    public GateType getGateType() {
        return GateType.CONSTANT0;
    }

    @Override
    public Iterable<AbstractGate> inputGates() {
        final List<AbstractGate> list = new ArrayList<>();
       
        if (inputGate != null) {
            list.add(inputGate);
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
        return inputGate;
    }

    @Override
    public AbstractGate getInputGate2() {
        return inputGate;
    }
    
    @Override
    public void changeInputGate(final AbstractGate gateToChange,
                                final AbstractGate newGate) {
        if (inputGate == gateToChange) {
            inputGate = newGate;
            return;
        }
        
        throw new IllegalStateException("No gate to change.");
    }
    
    @Override
    public void setInputGate1(final AbstractGate gate) {
        inputGate = gate;
    }

    @Override
    public void setInputGate2(final AbstractGate gate) {
        inputGate = gate;
    }
}
