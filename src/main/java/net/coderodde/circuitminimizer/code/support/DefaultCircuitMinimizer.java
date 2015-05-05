package net.coderodde.circuitminimizer.code.support;

import java.util.List;
import net.coderodde.circuitminimizer.code.AbstractCircuitMinimizer;
import net.coderodde.circuitminimizer.code.CircuitExpander;
import net.coderodde.circuitminimizer.domain.AbstractGate;
import net.coderodde.circuitminimizer.domain.GateType;

/**
 * This class implements a default circuit minimizer.
 * 
 * @author Rodion Efremov
 * @version 1.6
 */
public class DefaultCircuitMinimizer extends AbstractCircuitMinimizer {

    @Override
    public List<AbstractGate> minimize(final AbstractGate gate) {
        final List<AbstractGate> circuit = CircuitExpander.expandCircuit(gate);
        
        while (step(circuit)) {
            
        }
        
        return circuit;
    }
    
    protected boolean step(final List<AbstractGate> circuit) {
        for (final AbstractGate gate : circuit) {
            if (tryEliminateDuplicateInput(gate)) {
                circuit.remove(gate);
                return true;
            }
        }
        
        return false;
    } 
    
    protected boolean tryEliminateDuplicateInput(final AbstractGate gate) {
        if (gate.getGateType() == GateType.AND
                || gate.getGateType() == GateType.OR) {
            if (gate.getInputGate1() == null) {
                return false;
            }
            
            if (gate.getInputGate2() == null) {
                return false;
            }
            
            if (gate.getInputGate1() == gate.getInputGate2()) {
                final AbstractGate input = gate.getInputGate1();
                
                input.removeOutputGate(gate);
                input.addOutputGates(gate.getOutputGates());
                
                for (final AbstractGate outputGate : gate.getOutputGates()) {
                    outputGate.changeInputGate(gate, input);
                }
                
                return true;
            } 
        }
            
        return false;
    } 
}
