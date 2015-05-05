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
public class InputGate extends AbstractGate {

    private final String name;
    
    public InputGate(final String name) {
        super();
        this.name = name;
    }
    
    @Override
    public GateType getGateType() {
        return GateType.INPUT;
    }

    @Override
    public Iterable<AbstractGate> inputGates() {
        final List<AbstractGate> list = new ArrayList<>(0);
       
        return new Iterable<AbstractGate>() {

            @Override
            public Iterator<AbstractGate> iterator() {
                return list.iterator();
            }
        };
    }

    @Override
    public AbstractGate getInputGate1() {
        return null;
    }

    @Override
    public AbstractGate getInputGate2() {
        return null;
    }
    
    @Override
    public void changeInputGate(final AbstractGate gateToChange,
                                final AbstractGate newGate) {
        throw new IllegalStateException("No gate to change.");
    }
    
    @Override
    public void setInputGate1(final AbstractGate gate) {
        throw new UnsupportedOperationException(
                "Can't set an input gate for an input gate.");
    }

    @Override
    public void setInputGate2(final AbstractGate gate) {
        throw new UnsupportedOperationException(
                "Can't set an input gate for an input gate.");
    }
}
