package controllers;

import client.Client;
import client.SocketClient;
import fertdt.RequestMessage;
import fertdt.ResponseMessage;
import helpers.constants.Constants;
import javafx.application.Platform;
import helpers.utils.Resource;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import models.Game;

public class MenuController {

    @FXML
    private Button backButton;
    @FXML
    private Pane contentPane;
    @FXML
    private Button hostGameButton;
    @FXML
    private Button enterButton;
    @FXML
    private static Label errorLabel;
    @FXML
    private TextField addressTextField;

    private static SocketClient client;
    private Game game;

    public void initialize() {
        client = SocketClient.getInstance();
        game = new Game();
    }

    @FXML
    private void hostButtonPressed(ActionEvent actionEvent) {
        hostGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            private final static Integer DEFAULT_ROOM_ID = 0;

            @Override
            public void handle(MouseEvent event) {
                RequestMessage requestMessage = RequestMessage.createStartRequestMessage(DEFAULT_ROOM_ID);
                client.sendMessage(requestMessage);

                GUI.getStage().setScene(Resource.getScenes().get(Constants.WAITING_RESOURCE_NAME));
                GUI.getStage().show();
            }
        });
    }

    @FXML
    private void connectButtonPressed(ActionEvent actionEvent) {
        GUI.getStage().setScene(Resource.getScenes().get(Constants.MENU_CONNECTION_BY_ROOM_RESOURCE_NAME));
        GUI.getStage().setResizable(true);
        GUI.getStage().show();
    }

    @FXML
    private void enterButtonPressed(ActionEvent actionEvent) {
        enterButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String roomIdText = addressTextField.getText();
                Integer roomId;
                try {
                    roomId = Integer.valueOf(roomIdText);
                } catch (NumberFormatException e) {
                    fail("Введите id комнаты!");
                    return;
                }

                RequestMessage requestMessage = RequestMessage.createStartRequestMessage(roomId);
                client.sendMessage(requestMessage);

                GUI.getStage().setScene(Resource.getScenes().get(Constants.WAITING_RESOURCE_NAME));
                GUI.getStage().show();
            }
        });
    }

    @FXML
    private void backButtonPressed(ActionEvent actionEvent) {
        GUI.getStage().setScene(Resource.getScenes().get(Constants.MENU_RESOURCE_NAME));
        GUI.getStage().setResizable(true);
        GUI.getStage().show();
    }

    private static void fail(String errorText) {
        errorLabel.setText(errorText);
        errorLabel.setVisible(true);
    }
}
