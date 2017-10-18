package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDefinedCommand extends Command {

	private Map<String, Command> parameters;
	private List<Command> commandList;
	
	public UserDefinedCommand(Map<String, Command> parameters, List<Command> commands) {
		this.parameters = parameters;
		this.commandList = commands;
	}
	
	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, Map<String, Double> variables, Map<String, Double> localVariables) {		
		for(String var : parameters.keySet())
			localVariables.put(var, parameters.get(var).execute(t, commands, variables, localVariables));
		
		double answer = 0;
		for(Command command : commandList) {
			answer = command.execute(t, commands, variables, localVariables);
		}
		return answer;
	}

}
