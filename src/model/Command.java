package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a single line of SLogo code that can be executed. 
 *
 */
public abstract class Command {
	/**
	 * @param t
	 *            The turtle that this command may modify or get data from
	 * @param commands
	 *            The list of available commands, to which this may add
	 * @param variables
	 *            The list of variables, which this may alter or add to
	 * @return The result of the command this represents
	 */
	public double execute(Turtle t, Map<String, CommandDef> commands, Map<String, Double> variables) {
		return execute(t, commands, variables, new HashMap<String, Double>());
	}
	
	public abstract double execute(Turtle t, Map<String, CommandDef> commands, Map<String, Double> variables, Map<String, Double> local);
}
