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
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
    Scene GuideScene;

    public Player p1;
    public int Spritecount = 0;
    public int Spritenum = 0;
    Projectiles playerprojectile;

    boolean running, goNorth, goSouth, goEast, goWest;
    boolean Shooting, Shootleft, Shootright, Shootup, Shootdown;
    public String Up1, Up2, Down1, Down2, Left1, Left2, Right1, Right2;
    
    private ArrayList<Rectangle> projectiles = new ArrayList();
    private ArrayList<Rectangle> enemies = new ArrayList();

    //final int WIDTH = 1920;
    //final int HEIGHT = 1080;
    final int WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
    final int HEIGHT = (int) Screen.getPrimary().getBounds().getHeight();

    Button BtnPlay, Btnscore, BtnSet, BtnExit, BtnGuide;

    @Override
    public void start(Stage primaryStage) {

        Stage = primaryStage;

        MenuScene = CreateMainMenu1();
        ScoreScene = CreateScore2();
        GuideScene = CreateGuide();
        SettingsScene = CreateSetting3();
        CreateGame(primaryStage);

        Stage.setTitle("Mortal Destruction");
        Stage.setScene(MenuScene);
        Stage.setFullScreen(true);
        Stage.show();

    }

    private Scene CreateMainMenu1() { //Function used to create the main menu scene

        BtnPlay = new Button();
        BtnPlay.setText("> Play Game");
        BtnPlay.setBackground(null);
        BtnPlay.setTextFill(Color.WHITE);
        BtnPlay.setFont(new Font("Papyrus", 30));
        BtnPlay.setOnAction(event -> CreateGame(Stage));

        // BtnPlay.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
        //    BtnPlay.setStyle("-fx-font-weight: bold");
        //    BtnPlay.setScaleX(3.2);
        //    BtnPlay.setScaleY(2.2);
        // });
        Btnscore = new Button();
        Btnscore.setText("> High Score");
        Btnscore.setBackground(null);
        Btnscore.setTextFill(Color.WHITE);
        Btnscore.setFont(new Font("Papyrus", 30));
        Btnscore.setOnAction(event -> switchscene(ScoreScene));

        BtnGuide = new Button();
        BtnGuide.setText("> Instructions");
        BtnGuide.setBackground(null);
        BtnGuide.setTextFill(Color.WHITE);
        BtnGuide.setFont(new Font("Papyrus", 30));
        BtnGuide.setOnAction(event -> switchscene(GuideScene));

        BtnSet = new Button();
        BtnSet.setText("> Settings");
        BtnSet.setBackground(null);
        BtnSet.setTextFill(Color.WHITE);
        BtnSet.setFont(new Font("Papyrus", 30));
        BtnSet.setOnAction(event -> switchscene(SettingsScene));

        BtnExit = new Button();
        BtnExit.setText("> Exit");
        BtnExit.setBackground(null);
        BtnExit.setTextFill(Color.WHITE);
        BtnExit.setFont(new Font("Papyrus", 30));
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
        vboxM.getChildren().addAll(BtnPlay, Btnscore, BtnGuide, BtnSet, BtnExit);

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

        Label Title = new Label("Score Board");
        Title.setLayoutX(WIDTH / 2 - 250);
        Title.setLayoutY(150);
        Title.setFont(new Font("Papyrus", 80));
        Title.setTextFill(Color.WHITE);
        ScoreRoot.getChildren().add(Title);

        BorderPane borderPane2 = new BorderPane();

        Button BtnBack = new Button();
        BtnBack.setText("< Back");
        BtnBack.setBackground(null);
        BtnBack.setTextFill(Color.WHITE);
        BtnBack.setFont(new Font("Papyrus", 20));
        BtnBack.setOnAction(event -> switchscene(MenuScene));

        borderPane2.setLeft(BtnBack);
        borderPane2.setTranslateY(7 * (HEIGHT / 8));

        ScoreRoot.getChildren().add(borderPane2);
        Scene sceneScore = new Scene(ScoreRoot, WIDTH, HEIGHT);

        return sceneScore;
    }

    private Scene CreateGuide() {
        Group ScoreGuide = new Group();
        Image imgBack = new Image("Backgroundimg.jpeg");
        ImageView Backg = new ImageView(imgBack);
        Backg.setFitWidth(WIDTH);
        Backg.setFitHeight(HEIGHT);
        ScoreGuide.getChildren().add(Backg);

        Rectangle rectangle = new Rectangle(200, 200, 960, 540);
        Image SetBox = new Image("Settingsbox.JPG");
        rectangle.setFill(new ImagePattern(SetBox));
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(WIDTH, HEIGHT);
        borderPane.setCenter(rectangle);

        ScoreGuide.getChildren().addAll(borderPane);

        Label Title = new Label("User Intsructions");
        Title.setLayoutX(WIDTH / 2 - 300);
        Title.setLayoutY(150);
        Title.setFont(new Font("Papyrus", 80));
        Title.setTextFill(Color.WHITE);
        ScoreGuide.getChildren().add(Title);

        BorderPane borderPane2 = new BorderPane();

        Button BtnBack = new Button();
        BtnBack.setText("< Back");
        BtnBack.setBackground(null);
        BtnBack.setTextFill(Color.WHITE);
        BtnBack.setFont(new Font("Papyrus", 20));
        BtnBack.setOnAction(event -> switchscene(MenuScene));

        borderPane2.setLeft(BtnBack);
        borderPane2.setTranslateY(7 * (HEIGHT / 8));

        ScoreGuide.getChildren().add(borderPane2);
        Scene sceneGuide = new Scene(ScoreGuide, WIDTH, HEIGHT);

        return sceneGuide;
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
        borderPane2.setTranslateY(7 * (HEIGHT / 8));

        SetRoot.getChildren().add(borderPane2);
        Scene sceneSet = new Scene(SetRoot, WIDTH, HEIGHT);

        return sceneSet;

    }

    private void CreateGame(Stage primaryStage) { //Function used to create the Game scene
        Down1 = "Down1.png";
        Down2 = "Down2.png";
        Up1 = "Up1.png";
        Up2 = "Up2.png";
        Left1 = "Left1.png";
        Left2 = "Left2.png";
        Right1 = "Right1.png";
        Right2 = "Right2.png";
        
        playerprojectile = new Projectiles();
        Group GameRoot = new Group();
        Image imgBack = new Image("GameBackground.jpeg");
        ImageView Backg = new ImageView(imgBack);
        Backg.setFitWidth(WIDTH);
        Backg.setFitHeight(HEIGHT);
        GameRoot.getChildren().add(Backg);

        p1 = new Player(30, 30, 4, Down1);
       // Terrain Maze = new Terrain();
       // for (Rectangle row[] : Maze.rectangles) {
       //     for (Rectangle r : row) {
       //         GameRoot.getChildren().add(r);
       //     }

       // }

        //GameRoot.getChildren().add(p1.rect);
        GameRoot.getChildren().add(p1.Sprite);

        Scene sceneGame = new Scene(GameRoot, WIDTH, HEIGHT);

        sceneGame.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case W:
                    goNorth = true;
                    Shootup = true;
                    break;
                case S:
                    goSouth = true;
                    Shootdown = true;
                    break;
                case A:
                    goWest = true;
                    Shootleft = true;
                    break;
                case D:
                    goEast = true;
                    Shootright = true;
                    break;
                case SHIFT:
                    running = true;
                case SPACE:
                    Shooting = true;
                    playerprojectile.shoot(p1.getXSprite(),p1.getYSprite());
                    GameRoot.getChildren().add(playerprojectile.getImageView());
                    
                    break;
            }
        });

        sceneGame.setOnKeyReleased((KeyEvent event) -> {
            switch (event.getCode()) {
                case W:
                    goNorth = false;
                    break;
                case S:
                    goSouth = false;
                    break;
                case A:
                    goWest = false;
                    break;
                case D:
                    goEast = false;
                    break;
                case SHIFT:
                    running = false;
                    
                    break;
            }
        });

        Image Down1 = new Image("Down1.png");
        Image Down2 = new Image("Down2.png");
        Image Up1 = new Image("Up1.png");
        Image Up2 = new Image("Up2.png");
        Image Left1 = new Image("Left1.png");
        Image Left2 = new Image("Left2.png");
        Image Right1 = new Image("Right1.png");
        Image Right2 = new Image("Right2.png");

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;

                if (goNorth) {
                    p1.Sprite.setImage(Up1);
                    dy += 2;
                }
                if (goSouth) {
                    p1.Sprite.setImage(Down1);
                    dy -= 2;
                }
                if (goEast) {
                    p1.Sprite.setImage(Right1);
                    dx -= 2;
                }
                if (goWest) {
                    p1.Sprite.setImage(Left2);
                    dx += 2;
                }
                if (running) {
                    dx *= 3;
                    dy *= 3;
                }
                p1.move(dy, dx);
                if (playerprojectile.stillShooting) {
                    playerprojectile.moveprojec();
                    
                }

            }

        }.start();

        primaryStage.setScene(sceneGame);
        primaryStage.setFullScreen(true);

    }

    private void switchscene(Scene newScene) { //Method called on when button is clicked. This method takes in a new scene in and switchs the scene to the new scene.
        Stage.setScene(newScene);
        Stage.setFullScreen(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
