package net.coderodde.circuitminimizer.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * This abstract class defines the API for logical gates.
 * 
 * @author Rodion Efremov
 * @version 1.6
 */
public abstract class AbstractGate implements Iterable<AbstractGate> {
    
    /**
     * This set contains all the gates whose input comes immediately from
     * this gate's output.
     */
    private final Set<AbstractGate> outputGateSet;
    
    /**
     * Constructs an abstract gate.
     */
    public AbstractGate() {
        this.outputGateSet = new HashSet<>();
    }
    
    /**
     * Returns the type of this gate.
     * 
     * @return the type of this gate.
     */
    public abstract GateType getGateType();
    
    /**
     * Returns the first input gate.
     * 
     * @return the input gate.
     */
    public abstract AbstractGate getInputGate1();
    
    /**
     * Returns the second input gate.
     * 
     * @return the input gate.
     */
    public abstract AbstractGate getInputGate2();
    
    /**
     * Sets the first input gate.
     * 
     * @param gate the gate to set as an input gate.
     */
    public abstract void setInputGate1(final AbstractGate gate);
    
    /**
     * Sets the second input gate.
     * 
     * @param gate the gate to set as an input gate.
     */
    public abstract void setInputGate2(final AbstractGate gate);
    
    /**
     * Attempts to add the input gate to the set of output gates.
     * 
     * @param gate the output gate to set.
     */
    public void addOutputGate(final AbstractGate gate) {
        outputGateSet.add(gate);
    }
    
    /**
     * Attempts to add all gates in <code>gateList</code> to the set of output
     * gates of this gate.
     * 
     * @param gateList the list of gates to add.
     */
    public void addOutputGates(final Collection<AbstractGate> gateList) {
        outputGateSet.addAll(gateList);
    }
    
    /**
     * Ensures that <code>gate</code> is not in the set of output gates.
     * 
     * @param gate the gate to remove from output gate set.
     */
    public void removeOutputGate(final AbstractGate gate) {
        outputGateSet.remove(gate);
    }
    
    /**
     * Returns an unmodifiable view of all the output gates of this gate.
     * 
     * @return a view of output gates.
     */
    public List<AbstractGate> getOutputGates() {
        return Collections.unmodifiableList(new ArrayList<>(outputGateSet));
    }
    
    /**
     * Returns the iterator over this gates output gates.
     * 
     * @return the iterator.
     */
    @Override
    public Iterator<AbstractGate> iterator() {
        return Collections.unmodifiableSet(outputGateSet).iterator();
    }
    
    /**
     * Returns an {@link java.lang.Iterable} over this gate's input gates.
     * 
     * @return an iterable.
     */
    public abstract Iterable<AbstractGate> inputGates();
    
    /**
     * Changes <code>gateToChange</code> to <code>newGate</code>.
     * 
     * @param gateToChange the target gate.
     * @param newGate      the new gate.
     */
    public abstract void changeInputGate(final AbstractGate gateToChange,
                                         final AbstractGate newGate);
    
    /**
     * Clears the set of output gates.
     */
    public void clearOutputGates() {
        outputGateSet.clear();
    }
}
