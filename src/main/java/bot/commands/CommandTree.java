package bot.commands;

import bot.StateManager;
import java.util.*;
import java.util.function.Function;

public class CommandTree {
    private HashMap<String, CommandTree> nexts = new HashMap<>();
    private Function<String, Function<StateManager, Command>> command = null;

    public Function<String, Function<StateManager, Command>> getCommand(String textCommand) {
        return getCommand(new LinkedList<>(Arrays.asList(textCommand.split(" "))), this);
    }

    private Function<String, Function<StateManager, Command>> getCommand(LinkedList<String> tokens, CommandTree currentNode){
        if(currentNode.command != null){
            return currentNode.command;
        }
        else if(!currentNode.nexts.containsKey(tokens.getFirst())){
            return text -> (stateManager -> new HelpCommand(stateManager));
        }
        else{
            var newNode = currentNode.nexts.get(tokens.getFirst());
            tokens.pollFirst();
            return getCommand(tokens, newNode);
        }
    }

    private void setTextCommand(
            String textCommand,
            Function<String, Function<StateManager, Command>> command) {
        var commandTokens = new LinkedList<>(Arrays.asList(textCommand.split(" ")));
        setTextCommand(commandTokens, command, this);
    }

    private void setTextCommand(
            LinkedList<String> commandToken,
            Function<String, Function<StateManager, Command>> command,
            CommandTree currentNode) {
        if (commandToken.isEmpty()) {
            currentNode.command = command;
            return;
        }
        if (!currentNode.nexts.containsKey(commandToken.getFirst()))
            currentNode.nexts.put(commandToken.getFirst(), new CommandTree());
        var newCurrentNode = currentNode.nexts.get(commandToken.getFirst());
        commandToken.pollFirst();
        setTextCommand(commandToken, command, newCurrentNode);
    }

    public static class Builder{
        private CommandTree commandTree = new CommandTree();
        public void setCommand(String textCommand, Function<String, Function<StateManager, Command>> command){
            this.commandTree.setTextCommand(textCommand, command);
        }
        public CommandTree build(){
            return this.commandTree;
        }
    }
}