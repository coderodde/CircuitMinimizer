package net.coderodde.circuitminimizer.code.support;

import java.util.List;
import net.coderodde.circuitminimizer.code.AbstractCircuitMinimizer;
import net.coderodde.circuitminimizer.code.CircuitExpander;
import net.coderodde.circuitminimizer.domain.AbstractGate;
import net.coderodde.circuitminimizer.domain.GateType;
import net.coderodde.circuitminimizer.domain.support.Constant0Gate;
import net.coderodde.circuitminimizer.domain.support.Constant1Gate;

/**
 * This class implements a default circuit minimizer.
 * 
 * @author Rodion Efremov
 * @version 1.6
 */
public class DefaultCircuitMinimizer extends AbstractCircuitMinimizer {

    /**
     * Given a gate, this algorithm computes all logical gates reachable from it
     * and minimize it.
     * 
     * @param  gate the gate of a circuit to minimize.
     * @return a minimized circuit.
     */
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
            
            if (tryEliminateConstantGate(gate)) {
                circuit.remove(gate);
                return true;
            }
            
            if (tryEliminateConstantInputForNotGate(gate)) {
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
    
    protected boolean tryEliminateConstantGate(final AbstractGate gate) {
        if (gate.getGateType() == GateType.CONSTANT0
                || gate.getGateType() == GateType.CONSTANT1) {
            final AbstractGate input = gate.getInputGate1();
            
            if (input.getGateType() == GateType.CONSTANT0
                    ||  input.getGateType() == GateType.CONSTANT1) {
                input.addOutputGates(gate.getOutputGates());
                
                for (final AbstractGate output : gate.getOutputGates()) {
                    output.changeInputGate(gate, input);
                }
                
                return true;
            }
        }
        
        return false;
    }
    
    protected boolean 
    tryEliminateConstantInputForNotGate(final AbstractGate gate) {
        if (gate.getGateType() != GateType.NOT) {
            return false;
        }
        
        final AbstractGate input = gate.getInputGate1();
        if (input.getGateType() == GateType.CONSTANT0) {
            final AbstractGate inputOfInput = input.getInputGate1();
            final Constant1Gate newGate = new Constant1Gate();
             
            inputOfInput.addOutputGate(newGate);
            newGate.setInputGate1(inputOfInput);
            newGate.addOutputGates(gate.getOutputGates());
            
            for (final AbstractGate output : gate.getOutputGates()) {
                output.changeInputGate(input, newGate);
            }
            
            return true;
        }

        if (input.getGateType() == GateType.CONSTANT1) {
            final AbstractGate inputOfInput = input.getInputGate1();
            final Constant0Gate newGate = new Constant0Gate();
            
            inputOfInput.addOutputGate(newGate);
            newGate.setInputGate1(inputOfInput);
            newGate.addOutputGates(gate.getOutputGates());
            
            for (final AbstractGate output : gate.getOutputGates()) {
                output.changeInputGate(input, newGate);
            }
            
            return true;
        }

        return false;
    }
}
