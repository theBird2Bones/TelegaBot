package utils;

import bot.StateManager;

public class StateManagerComparator {
    public static boolean isEqual(StateManager st1, StateManager st2){
        return st1.getDialogState() == st2.getDialogState() &&
                st1.getCurrentState() == st2.getCurrentState() &&
                st1.getChatID().equals(st2.getChatID())  &&
                (
                        st1.getBufferedCommandName() != null &&
                        st1.getBufferedCommandName().equals(st2.getBufferedCommandName()) ||
                        st1.getBufferedCommandName() == null && st2.getBufferedCommandName() == null
                ) &&
                CategoryManagerComparator.isEqual(
                        st1.getTakenAccount().getCategoryManagers().get(0),
                        st2.getTakenAccount().getCategoryManagers().get(0)) &&
                CategoryManagerComparator.isEqual(
                        st1.getTakenAccount().getCategoryManagers().get(1),
                        st2.getTakenAccount().getCategoryManagers().get(1));
    }
}
