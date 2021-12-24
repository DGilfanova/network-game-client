package utils;

import controllers.GUI;
import helpers.constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;

public class Resource {

    private final static String MENU_VIEW_PATH = "/view/menu.fxml";
    private final static String SELECT_CHARACTER_VIEW_PATH = "/view/characterSelection.fxml";
    private final static String CONN_BY_ID_VIEW_PATH = "/view/connById.fxml";
    private final static String MAIN_VIEW_PATH = "/view/main.fxml";

    public static HashMap<String, Scene> scenes = new HashMap<>();

    public static void loadScenes() throws IOException
    {
        scenes.put(Constants.MENU_RESOURCE_NAME, new Scene(FXMLLoader.load(GUI.class.getResource(MENU_VIEW_PATH))));
        scenes.put(Constants.MENU_CONNECTION_BY_ROOM_RESOURCE_NAME, new Scene(FXMLLoader.load(GUI.class.getResource(CONN_BY_ID_VIEW_PATH))));
        scenes.put(Constants.CHARACTER_SELECTION_RESOURCE_NAME, new Scene(FXMLLoader.load(GUI.class.getResource(SELECT_CHARACTER_VIEW_PATH))));
        scenes.put(Constants.MAIN_RESOURCE_NAME,new Scene(FXMLLoader.load(GUI.class.getResource(MAIN_VIEW_PATH))));
    }

    public static HashMap<String, Scene> getScenes() {
        return scenes;
    }
}
