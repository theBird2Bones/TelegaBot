package bot;

public interface MainActions {
    void createCategory();
    void removeCategory();
    void renameCategory();
    String getCategoryName();

    long getTotal();
    //стоит, возможно еще добавить вывод с покупками (*покупка нейм* - *затрата*)

    void goUpTheTree();
    void goDownTheTree();
}
