package net.coderodde.circuitminimizer.code;

import java.util.List;
import net.coderodde.circuitminimizer.domain.AbstractGate;

/**
 * This abstract class defines the API for circuit minimizer.
 * 
 * @author Rodion Efremov
 * @version 1.6
 */
public abstract class AbstractCircuitMinimizer {

    public abstract List<AbstractGate> minimize(final AbstractGate gate);
}
