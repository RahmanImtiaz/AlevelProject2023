/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compsciproject2023;

import java.applet.AudioClip;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import javafx.application.Application;
import static javafx.application.Application.launch;
import static javafx.application.Platform.exit;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author 10848
 */
public class CompSciProject2023 extends Application {

    Stage Stage;
    Scene MenuScene;
    Scene ScoreScene;
    Scene SettingsScene;
    Scene GameScene;

    //final int WIDTH = 1920;
    //final int HEIGHT = 1080;
    final int WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
    final int HEIGHT = (int) Screen.getPrimary().getBounds().getHeight();

    Button BtnPlay;
    
    KeyHandler KeyH = new KeyHandler();

    @Override
    public void start(Stage primaryStage) {

        Stage = primaryStage;

        MenuScene = CreateMainMenu1();
        ScoreScene = CreateScore2();
        SettingsScene = CreateSetting3();
        GameScene = CreateGame();

       
        

        Stage.setTitle("Mortal Destruction");
        Stage.setScene(MenuScene);

        Stage.show();
    }

    private Scene CreateMainMenu1() { //Function used to create the main menu scene

        BtnPlay = new Button();
        BtnPlay.setText("> Play Game");
        BtnPlay.setBackground(null);
        BtnPlay.setTextFill(Color.WHITE);
        BtnPlay.setFont(new Font("Papyrus", 30));
        //BtnPlay.setScaleX(3);
        //BtnPlay.setScaleY(2);
        BtnPlay.setOnAction(event -> switchscene(GameScene));

        // BtnPlay.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
        //    BtnPlay.setStyle("-fx-font-weight: bold");
        //    BtnPlay.setScaleX(3.2);
        //    BtnPlay.setScaleY(2.2);
        // });
        Button Btnscore = new Button();
        Btnscore.setText("> High Score");
        Btnscore.setBackground(null);
        Btnscore.setTextFill(Color.WHITE);
        Btnscore.setFont(new Font("Papyrus", 30));
        //Btnscore.setScaleX(3);
        //Btnscore.setScaleY(2);

        Btnscore.setOnAction(event -> switchscene(ScoreScene));

        Button BtnSet = new Button();
        BtnSet.setText("> Settings");
        BtnSet.setBackground(null);
        BtnSet.setTextFill(Color.WHITE);
        BtnSet.setFont(new Font("Papyrus", 30));
        //BtnSet.setScaleX(3);
        //BtnSet.setScaleY(2);

        BtnSet.setOnAction(event -> switchscene(SettingsScene));

        Button BtnExit = new Button();
        BtnExit.setText("> Exit");
        BtnExit.setBackground(null);
        BtnExit.setTextFill(Color.WHITE);
        BtnExit.setFont(new Font("Papyrus", 30));
        //BtnExit.setScaleX(3);
        //BtnExit.setScaleY(2);

        BtnExit.setOnAction(event -> exit());

        Group root = new Group();
        Image imgBack = new Image("BackgroundMenuimg.jpeg");
        ImageView Backg = new ImageView(imgBack);
        Backg.setFitWidth(WIDTH);
        Backg.setFitHeight(HEIGHT);

        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(WIDTH, HEIGHT);

        VBox vboxM = new VBox();

        vboxM.setAlignment(Pos.CENTER);

        root.getChildren().add(Backg);
        vboxM.getChildren().addAll(BtnPlay, Btnscore, BtnSet, BtnExit);

        // vboxM.setSpacing(50);
        borderPane.setTranslateY(200);
        borderPane.setCenter(vboxM);
        root.getChildren().add(borderPane);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        return scene;

    }

    private Scene CreateScore2() { //Function used to create the High Score scene
        Group ScoreRoot = new Group();
        Image imgBack = new Image("Backgroundimg.jpeg");
        ImageView Backg = new ImageView(imgBack);
        Backg.setFitWidth(WIDTH);
        Backg.setFitHeight(HEIGHT);
        ScoreRoot.getChildren().add(Backg);

        Rectangle rectangle = new Rectangle(200, 200, 960, 540);

        Image SetBox = new Image("Settingsbox.JPG");
        rectangle.setFill(new ImagePattern(SetBox));

        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(WIDTH, HEIGHT);
        borderPane.setCenter(rectangle);

        ScoreRoot.getChildren().add(borderPane);

        BorderPane borderPane2 = new BorderPane();
        Button BtnBack = new Button();
        BtnBack.setText("< Back");
        BtnBack.setBackground(null);
        BtnBack.setTextFill(Color.WHITE);
        BtnBack.setFont(new Font("Papyrus", 20));

        BtnBack.setOnAction(event -> switchscene(MenuScene));
        borderPane2.setLeft(BtnBack);
        borderPane2.setTranslateY(750);

        ScoreRoot.getChildren().add(borderPane2);
        Scene sceneScore = new Scene(ScoreRoot, WIDTH, HEIGHT);

        return sceneScore;
    }

    private Scene CreateSetting3() { //Function used to create the settings scene
        Group SetRoot = new Group();
        Image imgBack = new Image("Backgroundimg.jpeg");
        ImageView Backg = new ImageView(imgBack);
        Backg.setFitWidth(WIDTH);
        Backg.setFitHeight(HEIGHT);
        SetRoot.getChildren().add(Backg);

        Rectangle rectangle = new Rectangle(200, 200, 960, 540);

        Image SetBox = new Image("Settingsbox.JPG");
        rectangle.setFill(new ImagePattern(SetBox));

        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(WIDTH, HEIGHT);
        borderPane.setCenter(rectangle);

        SetRoot.getChildren().add(borderPane);

        Label Title = new Label("Settings");
        Title.setLayoutX(WIDTH / 2 - 150);
        Title.setLayoutY(150);
        Title.setFont(new Font("Papyrus", 80));
        Title.setTextFill(Color.WHITE);

        Label vol = new Label("Volume");
        Slider volume = new Slider();
        volume.setLayoutX(WIDTH / 2 - 400);
        volume.setLayoutY(400);
        volume.setPrefWidth(700);
        //double value = volume.getValue();
        Label volPerc = new Label();
        volPerc.setLayoutX(WIDTH / 2);
        volPerc.setLayoutY(400);

        volPerc.textProperty().bind(
                Bindings.format(
                        "%.0f",
                        volume.valueProperty()
                )
        );

        volPerc.setLayoutX(WIDTH / 2 + 350);
        volPerc.setLayoutY(335);
        volPerc.setFont(new Font("Papyrus", 50));
        volPerc.setTextFill(Color.WHITE);

        vol.setLayoutX(WIDTH / 2 - 400);
        vol.setLayoutY(300);
        vol.setFont(new Font("Papyrus", 50));
        vol.setTextFill(Color.WHITE);
        SetRoot.getChildren().addAll(vol, volume, Title, volPerc);

        BorderPane borderPane2 = new BorderPane();
        Button BtnBack = new Button();
        BtnBack.setText("< Back");
        BtnBack.setBackground(null);
        BtnBack.setTextFill(Color.WHITE);
        BtnBack.setFont(new Font("Papyrus", 20));

        BtnBack.setOnAction(event -> switchscene(MenuScene));
        borderPane2.setLeft(BtnBack);
        borderPane2.setTranslateY(750);

        SetRoot.getChildren().add(borderPane2);
        Scene sceneSet = new Scene(SetRoot, WIDTH, HEIGHT);

        return sceneSet;

    }

    private Scene CreateGame() { //Function used to create the Game scene

        Group GameRoot = new Group();
        Image imgBack = new Image("GameBackground.jpeg");
        ImageView Backg = new ImageView(imgBack);
        Backg.setFitWidth(WIDTH);
        Backg.setFitHeight(HEIGHT);
        GameRoot.getChildren().add(Backg);
        

        Scene sceneGame = new Scene(GameRoot, WIDTH, HEIGHT);

        return sceneGame;
    }

    private void switchscene(Scene newScene) { //Method called on when button is clicked. This method takes in a new scene in and switchs the scene to the new scene.
        Stage.setScene(newScene);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
