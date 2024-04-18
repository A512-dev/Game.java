package controller;

import model.charactersModel.PanelModel;

public class Database {
    public static PanelModel panelModelData;

    public static PanelModel getPanelModelData() {
        return panelModelData;
    }

    public static void setPanelModelData(PanelModel panelModelData) {
        Database.panelModelData = panelModelData;
    }
}
