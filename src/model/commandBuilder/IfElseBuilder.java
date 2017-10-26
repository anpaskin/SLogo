package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.IfElse;

public class IfElseBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new IfElse(dispenser.getNextCommand(), dispenser.getNextCommandList(), dispenser.getNextCommandList());
	}

}
