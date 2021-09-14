package Bot;

public interface MainActions {
    void createCategory();
    void removeCategory();
    void renameCategory();
    String getCategoryName();

    long getTotal();
    long getTotalProDays();
    //стоит, возможно еще добавить вывод с покупками (*покупка нейм* - *затрата*)

    void goDown();
    void goUp();
}
