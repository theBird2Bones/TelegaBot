package general;

import bot.Account;
import bot.Formatter;
import bot.categories.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FormatterTest {
    @Test
    public void CategoryShowInnerDataTest(){
        var cat = new WithdrawalCategory("Soup");
        cat.put(500);

        Assertions.assertEquals(String.format("Category: %s\nOutcome: %d",cat.getName(), cat.getTotal()),
                Formatter.formatCategory(cat));
    }

    @Test
    public void CategoryManagerShowOneInnerCategoryTest(){
        var catMan = new DepositCategoryManager("income");

        catMan.addCategory("Soup");
        catMan.getCategories().get(0).put(600);
        assertEquals(
            String.format("Category manager: %s\nInner categories:\n1) %s",
            catMan.getName(),
            catMan.getCategories().get(0).getName()),
            Formatter.formatCategoryManagerContent(catMan));
    }

    @Test
    public void CategoryManagerShowThreeInnerCategoriesTest(){
        var catMan = new DepositCategoryManager("somth");

        catMan.addCategory("Soup");
        catMan.getCategories().get(0).put(600);
        catMan.addCategory("beef");
        catMan.getCategories().get(1).put(700);
        catMan.addCategory("cheese");
        catMan.getCategories().get(2).put(200);
        assertEquals(String.format("Category manager: %s\nInner categories:\n1) %s\n2) %s\n3) %s",
            catMan.getName(),
            catMan.getCategories().get(0).getName(),
            catMan.getCategories().get(1).getName(),
            catMan.getCategories().get(2).getName()),
            Formatter.formatCategoryManagerContent(catMan));
    }

    @Test
    public void CategoryManagerShowTotalTest(){
        var catMan = new DepositCategoryManager("income");

        catMan.addCategory("Soup");
        catMan.getCategories().get(0).put(600);
        catMan.addCategory("beef");
        catMan.getCategories().get(1).put(700);

        assertEquals(String.format("Category manager: %s\nTotal: %d\nwith categories:\n1) %s: %d\n2) %s: %d",
                catMan.getName(),
                catMan.getTotal(),
                catMan.getCategories().get(0).getName(),
                catMan.getCategories().get(0).getTotal(),
                catMan.getCategories().get(1).getName(),
                catMan.getCategories().get(1).getTotal()),
                Formatter.formatCategoryManagerTotal(catMan));
    }

    @Test
    public void AccountShowInnerCategoryManagerTest(){
        var acc = new Account("smth");
        var innerManager = acc.getCategoryManagers().get(0);
        innerManager.addCategory("G");
        innerManager.addCategory("T");

        assertEquals(String.format("Account: %s\nInner category managers:\n1) %s\n2) %s\n3) %s",
                acc.getName(),
                acc.getCategoryManagers().get(0).getName(),
                acc.getCategoryManagers().get(1).getName(),
                acc.getCategoryManagers().get(2).getName()),
                Formatter.formatAccountInnerCategoryManager(acc));
    }

    @Test
    public void AccountShowInnerCategoryManagerTotalTest(){
        var acc = new Account("smth");
        var innerManager = acc.getCategoryManagers().get(0);
        innerManager.addCategory("G");
        innerManager.getCategories().get(0).put(500);
        innerManager.addCategory("T");
        innerManager.getCategories().get(1).put(300);
        innerManager = acc.getCategoryManagers().get(1);
        innerManager.addCategory("G`");
        innerManager.getCategories().get(0).put(500);
        innerManager.addCategory("T`");
        innerManager.getCategories().get(1).put(300);
        assertEquals(String.format("Account: %s\nTotal: %s\nwith inner category managers:\n1) %s\n2) %s\n3) %s",
                acc.getName(),
                acc.getTotal(),
                acc.getCategoryManagers().get(0).getName(),
                acc.getCategoryManagers().get(1).getName(),
                acc.getCategoryManagers().get(2).getName()),
                Formatter.formatAccountInnerCategoryManagerTotal(acc));
    }
}

