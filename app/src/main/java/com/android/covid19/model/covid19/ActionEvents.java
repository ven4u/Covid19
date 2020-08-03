package com.android.covid19.model.covid19;

public class ActionEvents {

   private String actionName;
   private String actionDiscription;

    public ActionEvents(String actionName, String actionDiscription) {
        this.actionName = actionName;
        this.actionDiscription = actionDiscription;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionDiscription() {
        return actionDiscription;
    }

    public void setActionDiscription(String actionDiscription) {
        this.actionDiscription = actionDiscription;
    }

    @Override
    public String toString() {
        return "ActionEvents{" +
                "actionName='" + actionName + '\'' +
                ", actionDiscription='" + actionDiscription + '\'' +
                '}';
    }
}
