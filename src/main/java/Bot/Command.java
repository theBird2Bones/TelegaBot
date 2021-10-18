package Bot;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

abstract class Command {
    protected StateManager stateManager;
    public Command(StateManager stateManager){
        this.stateManager = stateManager;
    }
    abstract public String execute();
}

class AboutCommand extends Command {
    public AboutCommand(StateManager stateManager){
        super(stateManager);
    }
    @Override
    public String execute() {
        return "Creators: Konstantin & Mariia";
    }
}

class HelpCommand extends Command{
    public HelpCommand(StateManager stateManager){
        super(stateManager);
    }

    @Override
    public String execute() {
        return "That I can do:\n/about - Show my creators\n/help - Show the list of possible commands";
    }
}

class ShowContentCommand extends Command{
    public ShowContentCommand(StateManager stateManager){
        super(stateManager);
    }

    @Override
    public String execute() {
        return switch (stateManager.getCurrentState()){
            case tookAccount ->
                Formatter.formatAccountInnerCategoryManager(stateManager.getTakenAccount());
            case tookCategoryManager ->
                    Formatter.formatCategoryManagerContent(stateManager.getTakenCategoryManager());
        };
    }
}

class GetTotalCommand extends Command{
    public GetTotalCommand(StateManager stateManager){
        super(stateManager);
    }

    @Override
    public String execute() {
        return switch (stateManager.getCurrentState()){
            case tookAccount ->
                    Formatter.formatAccountInnerCategoryManagerTotal(stateManager.getTakenAccount());
            case tookCategoryManager ->
                   Formatter.formatCategoryManagerTotal(stateManager.getTakenCategoryManager());
        };
    }
}

class MoveToCommand extends Command{
    private String command;
    public MoveToCommand(StateManager stateManager, String command){
        super(stateManager);
        this.command = command;
    }

    @Override
    public String execute() {
        return switch (stateManager.getCurrentState()){
            case tookAccount -> {
                stateManager.setCurrentState(CurrentState.tookCategoryManager);
                stateManager.setTakenCategoryManager(
                        stateManager .getTakenAccount() .getCategoryManagers()
                                .get(Integer.parseInt(command.split(" ")[2])-1));
                yield  String.format("You are moved to %s",
                        stateManager .getTakenAccount() .getCategoryManagers()
                                .get(Integer.parseInt(command.split(" ")[2])-1));
            }
            case tookCategoryManager -> "Please, move up to choose 'Income' or 'Outcome' category";
        };
    }
}

class IncreaseCommand extends Command {
    private String command;

    public IncreaseCommand(StateManager stateManager, String command) {
        super(stateManager);
        this.command = command;
    }

    @Override
    public String execute() {
        return switch (stateManager.getCurrentState()) {
            case tookAccount -> "Please choose 'Income' or 'Outcome' category to change balance";
            case tookCategoryManager -> {
                var splittedMessageText = command.split(" ");
                if (Pattern.matches("^-?\\d+?$", splittedMessageText[2])) {
                    stateManager.getTakenCategoryManager().getCategories()
                            .get(Integer.parseInt(splittedMessageText[1]) - 1)
                            .put(Long.parseLong(splittedMessageText[2]));
                    yield Formatter.formatCategoryManagerTotal(stateManager.getTakenCategoryManager());
                }
                yield "Sorry but you have to write digits only";
            }
        };
    }
}

class CreateCommand extends Command{
    private String command;
    public CreateCommand(StateManager stateManager, String command){
        super(stateManager);
        this.command = command;
    }

    @Override
    public String execute() {
        return switch (stateManager.getCurrentState()){
            case tookAccount -> "Sorry, but you can create categories in 'Income' or 'Outcome' only";
            case tookCategoryManager ->{
                var categoryName = Arrays.stream(command.split(" "))
                        .skip(1).collect(Collectors.joining(" ")).toString();
                stateManager.getTakenCategoryManager().addCategory(categoryName);
                yield Formatter.formatCategoryManagerContent(stateManager.getTakenCategoryManager());
            }
        };
    }
}

class MoveUpCommand extends Command{
    public MoveUpCommand(StateManager stateManager){
        super(stateManager);
    }

    @Override
    public String execute() {
        stateManager.releaseCategoryManager();
        stateManager.setCurrentState(CurrentState.tookAccount);
        return String.format("You moved up to %s", stateManager.getTakenAccount());
    }
}

class DeleteCommand extends Command{
    private String command;
    public DeleteCommand(StateManager stateManager, String command){
        super(stateManager);
        this.command = command;
    }

    @Override
    public String execute() {
        var splitterCommand = command.split(" ");
        return switch (stateManager.getCurrentState()){
            case tookAccount -> "Sorry, but you can delete only categories in 'Income' and 'Outcome'";
            case tookCategoryManager -> {
                var deletedName = stateManager.getTakenCategoryManager().getCategories()
                        .get(Integer.parseInt(splitterCommand[1])-1).getName();
                stateManager.getTakenCategoryManager().removeCategoryWithIndex(Integer.parseInt(splitterCommand[1])-1);
                yield String.format("you've deleted %s",deletedName);
            }
        };
    }
}

class RenameCommand extends Command{
    private String command;
    public RenameCommand(StateManager stateManager, String command){
        super(stateManager);
        this.command = command;
    }

    @Override
    public String execute() {
        var splitterCommand = command.split(" ");
        return switch (stateManager.getCurrentState()){
            case tookAccount -> "Sorry, but you can rename only categories in 'Income' and 'Outcome'";
            case tookCategoryManager -> {
                var deletedName = stateManager.getTakenCategoryManager().getCategories()
                        .get(Integer.parseInt(splitterCommand[1])-1).getName();
                stateManager.getTakenCategoryManager().getCategories()
                        .get(Integer.parseInt(splitterCommand[1])-1).setName(splitterCommand[2]);
                var newName = stateManager.getTakenCategoryManager().getCategories()
                        .get(Integer.parseInt(splitterCommand[1])-1).getName();
                yield String.format("you've renamed %s into %s",deletedName, newName);
            }
        };
    }
}
