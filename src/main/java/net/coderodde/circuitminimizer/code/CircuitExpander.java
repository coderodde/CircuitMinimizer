package net.coderodde.circuitminimizer.code;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import net.coderodde.circuitminimizer.domain.AbstractGate;

/**
 * This class implements a search algorithm returning all the circuitry that is
 * reachable from an input gate.
 * 
 * @author Rodion Efremov
 * @version 1.6
 */
public class CircuitExpander {
    
    public static List<AbstractGate> expandCircuit(final AbstractGate start) {
        final Queue<AbstractGate> queue = new ArrayDeque<>();
        final Set<AbstractGate> closed = new HashSet<>();
        
        queue.add(start);
        
        while (!queue.isEmpty()) {
            final AbstractGate currentGate = queue.poll();
            
            for (final AbstractGate outputGate : currentGate) {
                if (!closed.contains(outputGate)) {
                    closed.add(outputGate);
                    queue.add(outputGate);
                }
            }
            
            for (final AbstractGate inputGate : currentGate.inputGates()) {
                if (!closed.contains(inputGate)) {
                    closed.add(inputGate);
                    queue.add(inputGate);
                }
            }
        }
        
        return new ArrayList<>(closed);
    }
}
