package Bot;

import java.util.function.Function;

interface Command {
    String execute(StateManager stateManager);
}

//class InnerContentProcessor implements Command{
//
//    @Override
//    public String execute(StateManager stateManager, String message) {
//        return switch (stateManager.getCurrentState()){
//            case CurrentState.tookAccount -> {
//                var indexOfCategoryManager = message.split(" ")[2];
//                stateManager.setTakenCategoryManager();
//            }
//        }
//    }
//}
//
//class CategoryManagerProcessor implements Command{
//    private Function<CategoryManager, String> processor;
//
//    public CategoryManagerProcessor(Function<CategoryManager, String> processor){
//        this.processor = processor;
//    }
//
//    @Override
//    public String execute(StateManager stateManager) {
//        if(stateManager.getCurrentState() == CurrentState.tookCategoryManager)
//            return processor.apply(stateManager.getTakenCategoryManager());
//        return "You should choose one of the category managers first!";
//    }
//}
//
//class CategoryProcessor implements Command{
//    private Category category;
//    private Function<Category, String> processor;
//    public CategoryProcessor(Category category, Function<Category, String> processor){
//        this.category= category;
//        this.processor = processor;
//    }
//
//    @Override
//    public String execute(StateManager stateManager) {
//        return processor.apply(category);
//    }
//}
//
class TextProcessor implements Command{
    private String message;

    public TextProcessor(String message){
        this.message = message;
    }
    @Override
    public String execute(StateManager stateManager) {
        return message;
    }
}