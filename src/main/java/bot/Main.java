package bot;

import bot.aod.StateManagerAOD;
import bot.service.FinancialBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new FinancialBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

//        var st = new StateManager("hoh", "666");
//        StateManagerAOD.save(st);
//        st.getTakenAccount().getCategoryManagers().get(0).addCategory("hsdhf");
//        StateManagerAOD.update(st);
//        st.getTakenAccount().getCategoryManagers().get(0).getCategories().get(0).put(6655);
//        st.setTakenCategoryManager(st.getTakenAccount().getCategoryManagers().get(0));
//        StateManagerAOD.update(st);
//
//        var sameSt = StateManagerAOD.findById(666L);
//        System.out.println("###");


    }
}
