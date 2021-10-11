import Bot.Account;
import Bot.Category;
import Bot.CategoryManager;
import Bot.Formatter;
import org.apache.commons.lang.NotImplementedException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;


public class FormatterTest {
    @Test
    public void CategoryShowInnerDataTest(){
        var cat = new Category("Soup",null);
        cat.subtract(500);

        var formatter = new Formatter();// класс должен быть статическим, здесь нельзя сделать иначе
        assertEquals(String.format("Category: %s\nOutcome: %d",cat.getName(), cat.getTotal()),
                formatter.formatCategory(cat));
    }

    @Test
    public void CategoryManagerShowOneInnerCategoryTest(){

        var catMan = new CategoryManager("income",null);
        var formatter = new Formatter();

        catMan.addCategory("Soup");
        catMan.getCategories().get(0).subtract(600);
        assertEquals(
            String.format("Category manager: %s\nInner categories:\n%s",
            catMan.getName(),
            catMan.getCategories().get(0).getTotal()),
            formatter.formatCategoryManagerContent(catMan));
    }

    @Test
    public void CategoryManagerShowThreeInnerCategoriesTest(){
        var catMan = new CategoryManager("income",null);
        var formatter = new Formatter();

        catMan.addCategory("Soup");
        catMan.getCategories().get(0).subtract(600);
        catMan.addCategory("beef");
        catMan.getCategories().get(1).subtract(700);
        catMan.addCategory("cheese");
        catMan.getCategories().get(2).subtract(200);
        assertEquals(String.format("Category manager: %s\nInner categories:\n%s\n%s\n%s",
            catMan.getName(),
            catMan.getCategories().get(0).getTotal(),
            catMan.getCategories().get(1).getTotal(),
            catMan.getCategories().get(2).getTotal()),
            formatter.formatCategoryManagerContent(catMan));
    }

    @Test
    public void CategoryManagerShowTotalTest(){
        var catMan = new CategoryManager("income",null);
        var formatter = new Formatter();

        catMan.addCategory("Soup");
        catMan.getCategories().get(0).subtract(600);
        catMan.addCategory("beef");
        catMan.getCategories().get(1).add(700);

        assertEquals(String.format("Category manager: %s\nTotal: %d\nwith categories:\n%s: %d\n%s: %d",
                catMan.getName(),
                catMan.getTotal(),
                catMan.getCategories().get(0).getName(),
                catMan.getCategories().get(0).getTotal(),
                catMan.getCategories().get(1).getName(),
                catMan.getCategories().get(1).getTotal()),
                formatter.formatCategoryManagerTotal(catMan));
    }

    @Test
    public void AccountShowInnerCategoryManagerTest(){
        var acc = new Account("smth");
        var formatter = new Formatter();
        acc.addCategoryManager("smth1cm");
        var innerManager = acc.getCategoryManagers().get(0);
        innerManager.addCategory("G");
        innerManager.addCategory("T");

        assertEquals(String.format("Account: %s\nInner category managers:\n%s",
                acc.getName(),
                acc.getCategoryManagers().get(0).getName()),
                formatter.formatAccountInnerCategoryManager(acc));
    }

    @Test
    public void AccountShowInnerCategoryManagerTotalTest(){
        var acc = new Account("smth");
        var formatter = new Formatter();
        acc.addCategoryManager("smth1cm");
        var innerManager = acc.getCategoryManagers().get(0);
        innerManager.addCategory("G");
        innerManager.getCategories().get(0).subtract(500);
        innerManager.addCategory("T");
        innerManager.getCategories().get(1).subtract(300);

        acc.addCategoryManager("smth2cm");
        innerManager = acc.getCategoryManagers().get(1);
        innerManager.addCategory("G`");
        innerManager.getCategories().get(0).subtract(500);
        innerManager.addCategory("T`");
        innerManager.getCategories().get(1).subtract(300);


        assertEquals(String.format("Account: %s\nTotal:\n%s\nwith inner category managers:\n%s\n%s",
                acc.getName(),
                acc.getTotal(),
                acc.getCategoryManagers().get(0).getName(),
                acc.getCategoryManagers().get(1).getName()),
                formatter.formatAccountInnerCategoryManagerTotal(acc));
    }
}

