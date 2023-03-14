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
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
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
import javafx.scene.Cursor;
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
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author 10848
 */
public class CompSciProject2023 extends Application {

    double randommovedx = 0;
    double randommovedy = 0;

    int upcount = 1, downcount = 1, rightcount = 1, leftcount = 1;

    AnimationTimer gametimer;

    Stage Stage;
    Stage popupStage;

    Scene MenuScene;
    Scene ScoreScene;
    Scene SettingsScene;
    Scene GuideScene;

    public Player p1;
    public int Spritecount = 0;
    public int Spritenum = 0;
    //Projectiles playerprojectile;
    double projectileSpeed = 10;
    
    Room Maze;

    boolean running, goNorth, goSouth, goEast, goWest, Escape;
    boolean Shooting, Shootleft, Shootright, Shootup, Shootdown;
    public String Up1, Up2, Down1, Down2, Left1, Left2, Right1, Right2;

    private ArrayList<Projectiles> projectiles = new ArrayList();
    private Projectiles projectile;
    private int projectileLifespan = 6;

    private ArrayList<Enemies> MaleeEnemies = new ArrayList();
    private int MaleeEnemycounter = 0, MaleeEnemyspawnTime = 180;
    private double MaleeEnemySpeed = 2;
    private Enemies MaleeEnemy;

    private ArrayList<Enemies> RangedEnemies = new ArrayList();
    private ArrayList<Projectiles> Enemyprojectiles = new ArrayList();
    private int RangedEnemycounter = 0, RangedEnemyspawnTime = 180, SpawnEnemycounter, EnemyProjcounter;
    private double RangedEnemySpeed = 4;
    //private Enemies RangedEnemy;

    private ArrayList<Rectangle> Arrows = new ArrayList();
    private int Arrowcounter = 0, ArrowspawnTime = 180, ArrowSpeed = 4;
    private Rectangle Arrow;

    //final int WIDTH = 1920;
    //final int HEIGHT = 1080;
    final double WIDTH = Screen.getPrimary().getBounds().getWidth();
    final double HEIGHT = Screen.getPrimary().getBounds().getHeight();

    Button BtnPlay, Btnscore, BtnSet, BtnExit, BtnGuide;
    Button BtnSaveScore, BtnResume, ButtonExitGame;
    private Group Menuroot, ScoreRoot, Guideroot, SetRoot, GameRoot, PauseRoot;

    @Override
    public void start(Stage primaryStage) {

        Stage = primaryStage;

//Function is called and stored the Scene returned from a subroutine is stored in global variable
        MenuScene = CreateMainMenu1();
        ScoreScene = CreateScore2();
        GuideScene = CreateGuide();
        SettingsScene = CreateSetting3();
        CreateGame(primaryStage);  // This subroutine is different, we instead pass the primary stage, and set the scene to the stage in this procedure.

        Stage.setTitle("Mortal Destruction");
        Stage.setScene(MenuScene); //set menu scene
        Stage.setFullScreen(true); //opens menu in full screen
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

        Menuroot = new Group();
        Image imgBack = new Image("BackgroundMenuimg.jpeg");
        ImageView Backg = new ImageView(imgBack);
        Backg.setFitWidth(WIDTH);
        Backg.setFitHeight(HEIGHT);
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(WIDTH, HEIGHT);

        VBox vboxM = new VBox();
        vboxM.setAlignment(Pos.CENTER);

        Menuroot.getChildren().add(Backg);
        vboxM.getChildren().addAll(BtnPlay, Btnscore, BtnGuide, BtnSet, BtnExit);

        // vboxM.setSpacing(50);
        borderPane.setCenter(vboxM); //places the vbox to the center
        borderPane.setTranslateY(200); //moves vbox down a bit
        Menuroot.getChildren().add(borderPane);
        Scene Menuscene = new Scene(Menuroot, WIDTH, HEIGHT);

        return Menuscene;

    }

    private Scene CreateScore2() { //Function used to create the High Score scene
        ScoreRoot = new Group();
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
        borderPane.setCenter(rectangle); //sets the rectange to the center

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
        Guideroot = new Group();
        Image imgBack = new Image("Backgroundimg.jpeg");
        ImageView Backg = new ImageView(imgBack);
        Backg.setFitWidth(WIDTH);
        Backg.setFitHeight(HEIGHT);
        Guideroot.getChildren().add(Backg);

        Rectangle rectangle = new Rectangle(200, 200, 960, 540);
        Image SetBox = new Image("Settingsbox.JPG");
        rectangle.setFill(new ImagePattern(SetBox));
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(WIDTH, HEIGHT);
        borderPane.setCenter(rectangle);

        Guideroot.getChildren().addAll(borderPane);

        Label Title = new Label("User Intsructions");
        Title.setLayoutX(WIDTH / 2 - 300);
        Title.setLayoutY(150);
        Title.setFont(new Font("Papyrus", 80));
        Title.setTextFill(Color.WHITE);
        Guideroot.getChildren().add(Title);

        BorderPane borderPane2 = new BorderPane();

        Button BtnBack = new Button();
        BtnBack.setText("< Back");
        BtnBack.setBackground(null);
        BtnBack.setTextFill(Color.WHITE);
        BtnBack.setFont(new Font("Papyrus", 20));
        BtnBack.setOnAction(event -> switchscene(MenuScene));

        borderPane2.setLeft(BtnBack);
        borderPane2.setTranslateY(7 * (HEIGHT / 8));

        Guideroot.getChildren().add(borderPane2);
        Scene sceneGuide = new Scene(Guideroot, WIDTH, HEIGHT);

        return sceneGuide;
    }

    private Scene CreateSetting3() { //Function used to create the settings scene
        SetRoot = new Group();
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

        //playerprojectile = new Projectiles();
        GameRoot = new Group();
        //Image imgBack = new Image("GameBackground.jpeg");
        Image bgimg = new Image("Blackbackround.png");
        ImageView Backg = new ImageView(bgimg);
        Backg.setFitWidth(WIDTH);
        Backg.setFitHeight(HEIGHT);
        GameRoot.getChildren().add(Backg);

        p1 = new Player(30, 30, Down1); //creates the player object

        GenerateMaze();

        //GameRoot.getChildren().add(p1.rect);
        GameRoot.getChildren().add(p1.Sprite);
        //GameRoot.getChildren().add(p1.radiuscircleP);

        Scene sceneGame = new Scene(GameRoot, WIDTH, HEIGHT);
        sceneGame.setCursor(Cursor.CROSSHAIR); //changes how the mouse will look
        controls(sceneGame); //calls the subroutine resposible for the key listeners
        loop(primaryStage); //calls the subroutine with the animation timer/gameloop

        spawnRangedEnemies();//calls the subroutine responsible for enemy spawns

        //sceneGame.setOnKeyPressed(
        //        new EventHandler<KeyEvent>() {
        //    public void handle(KeyEvent e) {
        //        String code = e.getCode().toString();
        //        if (code == "ESCAPE") {
        //            InGamePauseMenu();
        //        }
        //    }
        //});

        primaryStage.setScene(sceneGame);
        primaryStage.setFullScreen(true);

    }

    private void controls(Scene sceneGame) {
        sceneGame.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case W:
                    goNorth = true;
                    Shootup = true;
                    Shootdown = false;
                    Shootleft = false;
                    Shootright = false;

                    break;
                case S:
                    goSouth = true;
                    Shootup = false;
                    Shootdown = true;
                    Shootleft = false;
                    Shootright = false;
                    break;
                case A:
                    goWest = true;
                    Shootup = false;
                    Shootdown = false;
                    Shootleft = true;
                    Shootright = false;
                    break;
                case D:
                    goEast = true;
                    Shootup = false;
                    Shootdown = false;
                    Shootleft = false;
                    Shootright = true;
                    break;
                case SHIFT:
                    running = true;
                    break;
                case SPACE:
                    if (!Shooting) {
                        String playerfireball = "PurpleFireBall.png";
                        if (Shootup) {
                            projectiles.add(projectile = new Projectiles(-projectileSpeed, 0, playerfireball, p1.getXSprite(), p1.getYSprite()));
                            GameRoot.getChildren().add(projectile.Sprite);
                        }
                        if (Shootdown) {
                            projectiles.add(projectile = new Projectiles(projectileSpeed, 0, playerfireball, p1.getXSprite(), p1.getYSprite()));
                            GameRoot.getChildren().add(projectile.Sprite);
                        }
                        if (Shootleft) {
                            projectiles.add(projectile = new Projectiles(0, -projectileSpeed, playerfireball, p1.getXSprite(), p1.getYSprite()));
                            GameRoot.getChildren().add(projectile.Sprite);
                        }
                        if (Shootright) {
                            projectiles.add(projectile = new Projectiles(0, projectileSpeed, playerfireball, p1.getXSprite(), p1.getYSprite()));
                            GameRoot.getChildren().add(projectile.Sprite);
                        }
                        Shooting = true;
                    }
                    break;
                case ESCAPE:
                    Escape = true;
                    gametimer.stop();
                    InGamePauseMenu();
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
                case SPACE:
                    Shooting = false;
                    break;
                case ESCAPE:
                    Escape = false;
                    break;

            }
        });
    }

    private void loop(Stage primaryStage) {

        Image Down1 = new Image("Down1.png");
        Image Down2 = new Image("Down2.png");
        Image Up1 = new Image("Up1.png");
        Image Up2 = new Image("Up2.png");
        Image Left1 = new Image("Left1.png");
        Image Left2 = new Image("Left2.png");
        Image Right1 = new Image("Right1.png");
        Image Right2 = new Image("Right2.png");

        gametimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double dx = 0, dy = 0;

                if (goNorth) {
                    if (upcount % 2 == 0) {
                        p1.Sprite.setImage(Up2);
                        upcount++;
                    } else {
                        p1.Sprite.setImage(Up1);
                    }
                    dy += 4;
                    //p1.yspeed =4;
                }
                if (goSouth) {

                    if (downcount % 2 == 0) {
                        p1.Sprite.setImage(Down2);
                        downcount++;
                    } else {
                        p1.Sprite.setImage(Down1);
                    }
                    dy -= 4;
                    //p1.yspeed = -4;
                }
                if (goEast) {

                    if (rightcount % 2 == 0) {
                        p1.Sprite.setImage(Right2);
                        rightcount++;
                    } else {
                        p1.Sprite.setImage(Right1);
                    }
                    dx -= 4;
                    //p1.xspeed =-4;
                }
                if (goWest) {

                    if (leftcount % 2 == 0) {
                        p1.Sprite.setImage(Left1);
                        leftcount++;
                    } else {
                        p1.Sprite.setImage(Left2);
                    }
                    dx += 4;
                    //p1.xspeed +=4;
                }
                if (running) {
                    dx *= 3;
                    dy *= 3;
                    //p1.xspeed *= 3;
                    //p1.yspeed *= 3;
                }
               
                p1.move(dy, dx); // player movement method

                shootPlayerprojectile(); //calls for the method responsible for the player shooting
                Arrowcounter++;//increments each time
                RangedEnemycounter++;//dont need it for now
                spawnArrows();//spawns arrows
                moveArrows();//moves the arrows

                //Timer myTimer = new Timer();
                //TimerTask myTimerTask = new TimerTask() {
                //@Override
                //public void run() {
                //EnemyProjectile(p1.getXSprite(), p1.getYSprite());
                //}
                //};
                //myTimer.scheduleAtFixedRate(myTimerTask, 0, 10);
                EnemyProjcounter++;
                if (SpawnEnemycounter % 100 == 0) {
                    EnemyProjectile(p1.getXSprite() + 50, p1.getYSprite() + 50); //responsible for creating enemy projectiles
                }
                Enemyprojectiles.forEach(Enemyprojectiles -> Enemyprojectiles.moveprojectile()); //calls movement method for each projectile
                SpawnEnemycounter++;//increments each time--> counts the number of ticks in loop
                if (SpawnEnemycounter % 30 == 0) { //works for every 200 ticks

                    //Random change = new Random();
                    //int random = change.nextInt(9);
                    //if (random == 0 || random == 1) {
                    ///   randommovedy = -1;
                    //}
                    //if (random == 2) {
                    //    randommovedy = 1;
                    //}
                    //if (random == 3) {
                    //   randommovedx = -1;
                    //}
                    //if (random == 4) {
                    ///    randommovedx = 1;
                    ///}
                    //if (random == 5) {
                    ///    randommovedx = 0;
                    ///}
                    ///if (random == 6) {
                    ///   randommovedy = 0;
                    //}
                    ///if (random == 6 || random == 7) {
                    //   randommovedx = 0;
                    //    randommovedy = 0;
                    //}
                    for (int i = 0; i < RangedEnemies.size(); i++) {
                        int Edy = 0;
                        int Edx = 0;
                        Random change = new Random();
                        int num = change.nextInt(150);
                        if (RangedEnemies.get(i) != null) {
                            if (num<25) {
                                Edy += 10;
                            }
                            if (num>=25 && num<50) {
                                Edy -= 10;
                            }
                            if (num>=50 && num<75) {
                                Edx -= 10;
                            }
                            if (num>=75 && num<100) {
                                Edx += 10;
                            }
                            if (num>=100 && num<125) {
                                Edx += 0;
                            }
                            if (num>=125 && num<150) {
                                Edy += 0;
                            }
                            
                            if (p1.getXSprite()<RangedEnemies.get(i).getXSprite()) {
                                Edx -= 10;
                            }
                            for (int j = 0; j < change.nextInt(10); j++) {
                                RangedEnemies.get(i).move(Edx, Edy, WIDTH, HEIGHT);
                            }
                            //RangedEnemies.get(i).move(Edx, Edy, WIDTH, HEIGHT);
                        }
                    }

                }
                Maze.playerwallcollision(p1);
                //checkcollision
                Enemyprojectiles.forEach(Enemyprojectiles -> checkcollision(Enemyprojectiles, p1, 1)); //passes both sprite and 1--1 because player-enemyproj interact

                //RangedEnemies.forEach(RangedEnemies -> RangedEnemies.move(randommovedx, randommovedy, WIDTH, HEIGHT));
            }

        };
        gametimer.start();

        // if (Escape) {
        //             gametimer.stop();
        //             InGamePauseMenu(primaryStage);
        //         }
    }

    private void InGamePauseMenu() {
        //BtnSaveScore, BtnResume, ButtonExitGame
        PauseRoot = new Group();
        double width = WIDTH/2.5;
        double height = HEIGHT/2.5;

        Image imgBack = new Image("Settingsbox.JPG");
        ImageView Backg = new ImageView(imgBack);
        Backg.setFitWidth(width);
        Backg.setFitHeight(height);
        PauseRoot.getChildren().add(Backg);
        
        Label Title = new Label("Pause Menu");
        //Title.setLayoutX(width / 2  - 100);
        //Title.setLayoutY(height / 2 - 200);
        Title.setFont(new Font("Papyrus", 50));
        Title.setTextFill(Color.WHITE);
        
        BtnSaveScore = new Button();
        BtnSaveScore.setText("> Save Game");
        BtnSaveScore.setBackground(null);
        BtnSaveScore.setTextFill(Color.WHITE);
        BtnSaveScore.setFont(new Font("Papyrus", 30));
        //BtnSaveScore.setOnAction(event -> ));
        
        BtnResume = new Button();
        BtnResume.setText("> Resume");
        BtnResume.setBackground(null);
        BtnResume.setTextFill(Color.WHITE);
        BtnResume.setFont(new Font("Papyrus", 30));

        BtnResume.setOnAction(e -> {
            gametimer.start();
            popupStage.close();
            
        });
        
        ButtonExitGame = new Button();
        ButtonExitGame.setText("> Exit Game");
        ButtonExitGame.setBackground(null);
        ButtonExitGame.setTextFill(Color.WHITE);
        ButtonExitGame.setFont(new Font("Papyrus", 30));
        ButtonExitGame.setOnAction(event -> exit());
        

        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(width, height);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(Title,BtnSaveScore,BtnResume,ButtonExitGame);
        borderPane.setCenter(vbox);
        PauseRoot.getChildren().addAll(borderPane);
        popupStage = new Stage();
        popupStage.initStyle(StageStyle.UNDECORATED);
        //popupStage.initOwner(primaryStage);
        //popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(PauseRoot));
        popupStage.centerOnScreen();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setHeight(height);
        popupStage.setWidth(width);
        popupStage.show();

       

    }

    private void shootPlayerprojectile() {
        //projectileLifespan--;
        //for (int i = 0; i < projectiles.size(); ++i) {
        //if (projectiles.get(i).getLayoutY() > (GameRoot.getBoundsInParent().getMinY() - projectile.getHeight())) {
        //projectiles.get(i).relocate(projectiles.get(i).getLayoutX(), (projectiles.get(i).getLayoutY() - projectileSpeed));
        //} else {
        //projectiles.remove(i);
        //GameRoot.getChildren().remove(i);
        //}
        //}

        for (int i = 0; i < projectiles.size(); ++i) {
            projectiles.get(i).moveprojectile();
            //if (projectiles.get(i).getYSprite() > (GameRoot.getBoundsInParent().getMinY())) {
            //projectiles.get(i).moveprojectile();
            //} else {
            //projectiles.remove(i);
            //GameRoot.getChildren().remove(i);
            //}

            //if (projectiles.get(i).Sprite.getBoundsInParent().intersects(RangedEnemies.get(i).Sprite.getBoundsInParent())) {
            //    GameRoot.getChildren().remove(RangedEnemies.get(i).Sprite);
            //}
        }
    }

    private void spawnRangedEnemies() {

        String imgpath = "Enemyleft.png";
        Random r = new Random();

        int height = (int) HEIGHT;
        int width = (int) WIDTH;

        for (int i = 0; i < r.nextInt(5) + 2; i++) {
            double x = r.nextInt(width);
            double y = r.nextInt(height);
            RangedEnemies.add(new Enemies(5, 5, imgpath, 10, 100, x, y, 30));
            RangedEnemies.get(i).Sprite.setFitHeight(130);
            RangedEnemies.get(i).Sprite.setFitWidth(100);
            GameRoot.getChildren().add(RangedEnemies.get(i).Sprite);

        }
    }

    private void spawnMaleeEnemies() {

    }

    private void EnemyProjectile(double px, double py) {
        for (int i = 0; i < RangedEnemies.size() / 2 + 1; i++) {
            double playerdistx;
            double playerdisty;
            playerdistx = RangedEnemies.get(i).getXSprite() - px;
            playerdisty = RangedEnemies.get(i).getYSprite() - py;

            double hypot = Math.hypot(px - RangedEnemies.get(i).getXSprite(), py - RangedEnemies.get(i).getYSprite());

            if (p1.radiuscircleP.getBoundsInParent().intersects(RangedEnemies.get(i).radiuscircleE.getBoundsInParent())) {
                String playerfireball = "RedFireBall.png";
                double val = playerdisty / playerdistx;
                double angle = Math.atan(val);
                double dy = projectileSpeed * (Math.sin(angle));
                double dx = projectileSpeed * (Math.cos(angle));
                if (px < RangedEnemies.get(i).getXSprite() && py < RangedEnemies.get(i).getXSprite()) { // this is where the projectile shoots to up-left corner
                    if (dy > 0) {
                        dy = -dy;
                    }
                    if (dx > 0) {
                        dx = -dx;
                    }
                }
                if (px < RangedEnemies.get(i).getXSprite() && py > RangedEnemies.get(i).getYSprite()) { // this is where the projectile shoots to down-left corner
                    if (dy < 0) {
                        dy = -dy;
                    }
                    if (dx > 0) {
                        dx = -dx;
                    }
                }
                Enemyprojectiles.add(projectile = new Projectiles(dy, dx, playerfireball, RangedEnemies.get(i).getXSprite(), RangedEnemies.get(i).getYSprite() + 10));

                GameRoot.getChildren().add(projectile.Sprite);
            }
        }

    }

    private void GenerateMaze() {
        Maze = new Room(WIDTH, HEIGHT,p1);
        
        for (Cell row[] : Maze.cells) {
            for (Cell r : row) {
                GameRoot.getChildren().add(r.Cell);
            }
        }
    }

    private void spawnArrows() {

        double spawnPosition = Math.random();

        Image arrow = new Image("Arrow.png");

        int eWidth = 20;
        int eHeight = 40;
        double ex = (int) ((WIDTH - eWidth) * spawnPosition);
        int ey = (int) (GameRoot.getLayoutY());

        if (Arrowcounter % ArrowspawnTime == 0) {

            Arrow = new Rectangle(eWidth, eHeight);
            Arrow.setFill(new ImagePattern(arrow));
            Arrow.relocate(ex, ey);
            Arrows.add(Arrow);
            GameRoot.getChildren().add(Arrow);
        }
    }

    public void moveArrows() {
        for (int i = 0; i < Arrows.size(); ++i) {
            if (Arrows.get(i).getLayoutY() < (GameRoot.getBoundsInParent().getMaxY() + Arrow.getHeight())) {
                Arrows.get(i).relocate(Arrows.get(i).getLayoutX(), (Arrows.get(i).getLayoutY() + ArrowSpeed));
            } else {
                Arrows.remove(i);
            }
        }
    }

    private void switchscene(Scene newScene) { //Method called on when button is clicked. This method takes in a new scene in and switchs the scene to the new scene.
        Stage.setScene(newScene);
        Stage.setFullScreen(true);
    }

    private void checkcollision(Sprite sprite1, Sprite sprite2, int code) {
        if (code == 1) {//checks if player and enemy projectiles -- code 1
            if (sprite1.Sprite.getBoundsInParent().intersects(sprite2.Sprite.getBoundsInParent())) {
                //p1.playerhit = true;//player hit is true
                sprite1.setCollision(true);
                sprite2.setCollision(true);//player hit is true
                
                //projectileremove();
                
                p1.hit();//player hit method called - changed player health
                
                sprite2.setCollision(false);
                
                System.out.println(p1.getHealth());//output player health - testing
                //if (p1.getAlive()==false) {
                //    GameRoot.getChildren().remove(p1);
                //    GameRoot.getChildren().removeAll(projectiles);
                //}
            }
            sprite2.setCollision(false);
        }

        if (code == 2) {

        }

    }
    
    //private void checkwallcollision(Sprite sprite, Room room){
        //Collection<Rectangle2D> wallbounds = room.
    //}
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void projectileremove() {
        for (int i = 0; i < Enemyprojectiles.size(); i++) {
            if (Enemyprojectiles.get(i).getCollision() == true) {
                GameRoot.getChildren().remove(Enemyprojectiles.get(i).Sprite);
            }
        }
    }
}
