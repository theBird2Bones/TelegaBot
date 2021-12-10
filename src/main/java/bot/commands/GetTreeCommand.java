package bot.commands;

import bot.StateManager;
import bot.dao.operations.NoOperation;
import bot.keyboard.Keyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class GetTreeCommand extends Command {
    public GetTreeCommand(StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public SendMessage execute() {
        return getInfo();
    }

    @Override
    public SendMessage getInfo() {
        var account = stateManager.getTakenAccount();
        var tree = String.format("Current account:\n%s\n", account.getName());

        var outterBorderT = "┣";
        var outterBorderStraight = "┃";
        var outterBorderCorner = "┗";
        var outterBorderHorizon = "━";

        var managerCounter = 0;
        var outterBorder = outterBorderT;
        var outterPadding = "┃   ";

        for(var manager: account.getCategoryManagers()){
            managerCounter++;
            if(managerCounter == account.getCategoryManagers().size()){
                outterBorder = outterBorderCorner;
                outterPadding = "    ";
            }
            tree += String.format("%s━ %s: %d\n", outterBorder, manager.getName(), manager.getTotal());

            var innerBorder = outterBorderT;
            var categoryCount = 0;

            for(var category: manager.getCategories()){
                categoryCount++;
                if(categoryCount == manager.getCategories().size()){
                    innerBorder = outterBorderCorner;
                }
                tree += outterPadding;
                tree += String.format("%s━ %s: %d\n",innerBorder, category.getName(), category.getTotal());
            }
        }
        stateManager.setBdOperation(new NoOperation());
        tree += "Account total: " + account.getTotal();
        return SendMessage
                .builder()
                .chatId(stateManager.getChatID())
                .text(tree)
                .replyMarkup(Keyboard.createKeyboardMarkUp(stateManager.getAvailableButtonNames()))
                .build();
    }
}