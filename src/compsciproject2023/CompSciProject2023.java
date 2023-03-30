/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compsciproject2023;

import java.applet.AudioClip;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import static javafx.application.Application.launch;
import static javafx.application.Platform.exit;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author 10848
 */
public class CompSciProject2023 extends Application {

    double randommovedx = 0;
    double randommovedy = 0;

    MediaPlayer mediaPlayer;
    Slider volume;

    boolean PlayerDied, GameStart = false;
    Text Details;

    int upcount = 1, downcount = 1, rightcount = 1, leftcount = 1;

    Rectangle HealthBarBckg;
    Rectangle HealthBarHP;
    Text HpText;

    Rectangle ManaBarBckg;
    Rectangle ManaBarMP;
    Text MpText;

    Text KillCount, FloorCount, ScoreCount;

    int Score, HighestScore = 0, CurrentScore = 0;

    AnimationTimer gametimer;

    Room[][] rooms = new Room[3][3];
    int CurrentRoomx, CurrentRoomy, floornum, indexX, indexY, floorcount = 0, prevfloorcount = 0;

    Stage Stage;
    Stage popupStage, SaveScoreStage;

    Scene MenuScene;
    Scene ScoreScene;
    Scene SettingsScene;
    Scene GuideScene;

    public Player p1;
    public int Spritecount = 0;
    public int Spritenum = 0;
    public int healthhitcount = 0;
    //Projectiles playerprojectile;
    double projectileSpeed = 10;

    Room Maze;

    boolean running, goNorth, goSouth, goEast, goWest, Escape, ClickChest;
    boolean Shooting, Shootleft, Shootright, Shootup, Shootdown;
    public String Up1, Up2, Down1, Down2, Left1, Left2, Right1, Right2;

    private ArrayList<Projectiles> projectiles = new ArrayList();
    private Projectiles projectile;
    private int projectileLifespan = 6;

    private ArrayList<Chests> HpChests = new ArrayList();
    private ArrayList<Chests> MpChests = new ArrayList();
    private ArrayList<Traps> Traps = new ArrayList();

    private ArrayList<Enemies> RangedEnemies = new ArrayList();
    private ArrayList<Projectiles> Enemyprojectiles = new ArrayList();
    private int RangedEnemycounter = 0, RangedEnemyspawnTime = 180, SpawnEnemycounter, Timer;
    private double RangedEnemySpeed = 4;
    private int KillCounter = 0, prevkillcount = 0;
    //private Enemies RangedEnemy;

    private ArrayList<Rectangle> Arrows = new ArrayList();
    private int Arrowcounter = 0, ArrowspawnTime = 180, ArrowSpeed = 4;
    private Rectangle Arrow;

    //final int WIDTH = 1920;
    //final int HEIGHT = 1080;
    final double WIDTH = Screen.getPrimary().getBounds().getWidth();
    final double HEIGHT = Screen.getPrimary().getBounds().getHeight();

    double Wx = Screen.getPrimary().getBounds().getMaxX();
    double Hy = Screen.getPrimary().getBounds().getMaxY();

    Button BtnPlay, Btnscore, BtnSet, BtnExit, BtnGuide;
    Button BtnSaveScore, BtnResume, Restartbtn, ButtonExitGame;
    private Group Menuroot, ScoreRoot, Guideroot, SetRoot, GameRoot, PauseRoot, SaveRoot;

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
        GameStart = false;
        music();

        BtnPlay = new Button();
        BtnPlay.setText("> Play Game");
        BtnPlay.setBackground(null);
        BtnPlay.setTextFill(Color.WHITE);
        BtnPlay.setFont(new Font("Papyrus", 30));
        BtnPlay.setOnAction(event -> CreateGame(Stage));

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
        GameStart = false;
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

        UpdateScoreBoard();//updates score board

        ScoreRoot.getChildren().addAll(Title);//Adds the title

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

    private void UpdateScoreBoard() {
        BorderPane scoreBP = new BorderPane();
        VBox Scores = LoadDetails();//calls the score vbox - returns null if textfile not found
        if (Scores != null) {//If there is a text file
            Scores.setLayoutX(WIDTH / 2 - 250);
            Scores.setLayoutY(250);
            Scores.setAlignment(Pos.CENTER);
            scoreBP.setPrefSize(WIDTH, HEIGHT);
            scoreBP.setCenter(Scores);//Sets the score the the center
            scoreBP.setTranslateY(-150);//moves scores up by 200
            ScoreRoot.getChildren().addAll(scoreBP); //adds score to scoreboard 
        }

    }

    private Scene CreateGuide() {
        GameStart = false;
        Guideroot = new Group();
        Image imgBack = new Image("Backgroundimg.jpeg");
        ImageView Backg = new ImageView(imgBack);
        Backg.setFitWidth(WIDTH);
        Backg.setFitHeight(HEIGHT);
        Guideroot.getChildren().add(Backg);

        BorderPane Text = new BorderPane();
        Text.setPrefSize(WIDTH, HEIGHT);
        Text inst = new Text("W - Move Up\nS - Move Down\nA - Move Left\nD - Move Right\nC - Open Chests\nSPACEBAR - Shoot *(Face the direction you want to shoot at)");
        inst.setFont(new Font("Papyrus", 30));
        inst.setFill(Color.WHITE);
        Text.setCenter(inst);

        Rectangle rectangle = new Rectangle(200, 200, 960, 540);
        Image SetBox = new Image("Settingsbox.JPG");
        rectangle.setFill(new ImagePattern(SetBox));
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(WIDTH, HEIGHT);
        borderPane.setCenter(rectangle);

        Guideroot.getChildren().addAll(borderPane, Text);

        Label Title = new Label("User Instructions");
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
        GameStart = false;
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
        volume = new Slider();
        volume.setValue(mediaPlayer.getVolume() * 100);//Initially sets slider to audios volume
        volume.setLayoutX(WIDTH / 2 - 400);
        volume.setLayoutY(400);
        volume.setPrefWidth(700);

        Label volPerc = new Label();
        volPerc.setLayoutX(WIDTH / 2);
        volPerc.setLayoutY(400);

        volPerc.textProperty().bind(
                Bindings.format(
                        "%.0f",
                        volume.valueProperty()
                )
        );

        //Volume control
        volume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(volume.getValue() / 100);//Max slider is 100, max audio val is 1. So divide by 100
            }
        });

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
        GameStart = true;
        Down1 = "Down1.png";
        Down2 = "Down2.png";
        Up1 = "Up1.png";
        Up2 = "Up2.png";
        Left1 = "Left1.png";
        Left2 = "Left2.png";
        Right1 = "Right1.png";
        Right2 = "Right2.png";

        GameRoot = new Group();

        Random rand = new Random();
        CurrentRoomx = rand.nextInt(3);// 9 or 10 idk
        CurrentRoomy = rand.nextInt(3);

        //Initialise the rooms in the array
        int RoomNum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                RoomNum++;
                rooms[i][j] = new Room(WIDTH, HEIGHT, p1, RoomNum);
            }
        }

        GenerateMaze(CurrentRoomx, CurrentRoomy);
        DrawMaze(rooms[CurrentRoomy][CurrentRoomx], 100, 100, 100, 100);//Passes random starting room, and 100 HP, 100 MP, Player x and y

        Maze = rooms[CurrentRoomy][CurrentRoomx];

        Scene sceneGame = new Scene(GameRoot, WIDTH, HEIGHT);
        sceneGame.setCursor(Cursor.CROSSHAIR); //changes how the mouse will look
        controls(sceneGame, primaryStage); //calls the subroutine resposible for the key listeners
        loop(primaryStage); //calls the subroutine with the animation timer/gameloop

        primaryStage.setScene(sceneGame);
        primaryStage.setFullScreen(true);

    }

    private void DrawMaze(Room maze, int PHealth, int PMana, double pX, double pY) {//Passed in new room, and players current health and mana
        if (maze.isDiscovered()) {
            floorcount++;//increments floor count
            maze.setDiscovered(true);
        }
        GameRoot.getChildren().clear();//Remove everything from root
        RangedEnemies.clear();//Clear Enemy array lists
        HpChests.clear();//Clear Chest array lists        
        MpChests.clear();//Clear Chest array lists  
        Traps.clear();//Clear Chest array lists    
        Image bgimg = new Image("Blackbackround.png");
        ImageView Backg = new ImageView(bgimg);
        Backg.setFitWidth(WIDTH);
        Backg.setFitHeight(HEIGHT);
        GameRoot.getChildren().add(Backg);//add black background
        for (Cell row[] : maze.cells) {
            for (Cell r : row) {
                GameRoot.getChildren().add(r.Cell);//add all the cells of new maze
            }
        }
        DrawGame(PHealth, PMana, pX, pY, maze);//Draws the rest of the game
    }

    private void DrawGame(int PlayerHealth, int PlayerMana, double Playerx, double Playery, Room maze) {
        p1 = new Player(30, 30, Down1, PlayerHealth, PlayerMana); //creates the player object
        p1.setXSprite(Playerx);
        p1.setYSprite(Playery);
        spawnTraps();//Spawns Traps
        GameRoot.getChildren().add(p1.Sprite);

        spawnRangedEnemies();//calls the subroutine responsible for enemy spawns
        spawnHpChests();//Spawns in chests giving Hp
        spawnMpChests();//Spawns in chests giving Mp

        HBox BarBox = new HBox();//Used for Health bar and mana bar
        BorderPane scoreBP = new BorderPane();
        scoreBP.setRight(Scores(maze));//Borderpane stores and sets the HBOX containing score to the right
        BarBox.getChildren().addAll(HealthBar(), ManaBar(), scoreBP);//health bar, mana bar and scores, kill count etc.
        BarBox.setSpacing(10);
        GameRoot.getChildren().add(BarBox); //Hbox added to the root
    }

    private void GenerateMaze(int x, int y) {//Recursive backtracking
        int RoomNum = (x + 1) * (y + 1); // find room num
        rooms[y][x].isAddedToMaze(); // make visited true
        System.out.println("Room " + rooms[y][x].roomnum + ", Visited: " + rooms[y][x].IsInTheMaze());//used for testing

        indexX = x;
        indexY = y;
        Direction[] directions = Direction.values();//get all directions

        //Randomise direction
        shuffle(directions);

        //check if there is a valid room in that direction
        //make door in wall
        //make matching neighbor door
        //recursive call on neighbour
        for (Direction d : directions) {
            if (d == Direction.DOWN && isValidRoom(x, y + 1)) {//check if direction is down and the next room down is valid
                rooms[y][x].makeDoor(d); //make an exit to existing room
                rooms[y + 1][x].makeDoor(d.getNeighbourDoor(d)); //make an entrance to the new room
                GenerateMaze(x, y + 1); //Recursive call for the new room
            } else if (d == Direction.UP && isValidRoom(x, y - 1)) {//check if direction is up and the next room down is valid
                rooms[y][x].makeDoor(d); //make an exit to existing room
                rooms[y - 1][x].makeDoor(d.getNeighbourDoor(d)); //make an entrance to the new room
                GenerateMaze(x, y - 1); //Recursive call for the new room
            } else if (d == Direction.LEFT && isValidRoom(x - 1, y)) {//check if direction is left and the next room down is valid
                rooms[y][x].makeDoor(d); //make an exit to existing room
                rooms[y][x - 1].makeDoor(d.getNeighbourDoor(d)); //make an entrance to the new room
                GenerateMaze(x - 1, y); //Recursive call for the new room
            } else if (d == Direction.RIGHT && isValidRoom(x + 1, y)) {//check if direction is right and the next room down is valid
                rooms[y][x].makeDoor(d); //make an exit to existing room
                rooms[y][x + 1].makeDoor(d.getNeighbourDoor(d)); //make an entrance to the new room
                GenerateMaze(x + 1, y); //Recursive call for the new room
            }
        }
    }

    private boolean isValidRoom(int x, int y) {
        if (x > -1 && x < 3 && y > -1 && y < 3 && rooms[y][x].IsInTheMaze() == false) {
            return true;
        }
        return false;
    }

    private boolean isValidIndex(int x, int y) {
        if (x > -1 && x < 3 && y > -1 && y < 3) {
            return true;
        }
        return false;
    }

    private void shuffle(Direction[] directions) {
        Random Rindex = new Random();
        for (int i = 0; i < directions.length; i++) {
            int index = Rindex.nextInt(5); // random value between 0-4
            if (index != i) {
                Direction temp = directions[i];
                directions[i] = directions[index];
                directions[index] = temp;
            } else {
                i--;//i get incremented in a for loop -- this line decrements in so that i does not change-- cause it to loop again.
            }
        }
    }

    private void controls(Scene sceneGame, Stage primaryStage) {
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
                    if (!Shooting && p1.getMp() > 0 && p1.isAlive()) {
                        String playerfireball = "PurpleFireBall.png";
                        if (Shootup) {
                            projectiles.add(projectile = new Projectiles(-projectileSpeed, 0, playerfireball, p1.getXSprite() + 50, p1.getYSprite() + 50));
                            GameRoot.getChildren().add(projectile.Sprite);
                        }
                        if (Shootdown) {
                            projectiles.add(projectile = new Projectiles(projectileSpeed, 0, playerfireball, p1.getXSprite() + 50, p1.getYSprite() + 50));
                            GameRoot.getChildren().add(projectile.Sprite);
                        }
                        if (Shootleft) {
                            projectiles.add(projectile = new Projectiles(0, -projectileSpeed, playerfireball, p1.getXSprite() + 50, p1.getYSprite() + 50));
                            GameRoot.getChildren().add(projectile.Sprite);
                        }
                        if (Shootright) {
                            projectiles.add(projectile = new Projectiles(0, projectileSpeed, playerfireball, p1.getXSprite() + 50, p1.getYSprite() + 50));
                            GameRoot.getChildren().add(projectile.Sprite);
                        }
                        Shooting = true;
                        if (p1.getMp() > 0) {//Only is Mp is above 0
                            p1.setMp(p1.getMp() - 5);//Take away mana
                        }

                    }
                    break;
                case ESCAPE:
                    Escape = true;
                    gametimer.stop();
                    InGamePauseMenu(primaryStage);
                    break;
                case C:
                    ClickChest = true;
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
                case C:
                    ClickChest = false;
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
                }
                if (goSouth) {

                    if (downcount % 2 == 0) {
                        p1.Sprite.setImage(Down2);
                        downcount++;
                    } else {
                        p1.Sprite.setImage(Down1);
                    }
                    dy -= 4;
                }
                if (goEast) {

                    if (rightcount % 2 == 0) {
                        p1.Sprite.setImage(Right2);
                        rightcount++;
                    } else {
                        p1.Sprite.setImage(Right1);
                    }
                    dx -= 4;
                }
                if (goWest) {

                    if (leftcount % 2 == 0) {
                        p1.Sprite.setImage(Left1);
                        leftcount++;
                    } else {
                        p1.Sprite.setImage(Left2);
                    }
                    dx += 4;
                }
                if (running) {
                    dx *= 3;
                    dy *= 3;
                }
                p1.setXspeed(dx);
                p1.setYspeed(dy);
                p1.move(dy, dx, Wx, Hy); // player movement method

                shootPlayerprojectile(); //calls for the method responsible for the player shooting
                Arrowcounter++;//increments each time
                RangedEnemycounter++;//dont need it for now
                spawnArrows();//spawns arrows
                moveArrows();//moves the arrows

                Timer++;
                if (SpawnEnemycounter % 150 == 0) {
                    EnemyProjectile(p1.getXSprite() + 50, p1.getYSprite() + 50); //responsible for creating enemy projectiles
                }
                Enemyprojectiles.forEach(Enemyprojectiles -> Enemyprojectiles.moveprojectile()); //calls movement method for each projectile
                SpawnEnemycounter++;//increments each time--> counts the number of ticks in loop
                if (SpawnEnemycounter % 30 == 0) { //works for every 200 ticks
                    for (int i = 0; i < RangedEnemies.size(); i++) {
                        int Edy = 0;
                        int Edx = 0;
                        Random change = new Random();
                        int numy = change.nextInt(75);//Finds random number for the y direction
                        int numx = change.nextInt(75);//Finds random number for the x direction
                        if (RangedEnemies.get(i) != null) {//If there is an enemy
                            if (numy < 25) {//The player moves down
                                Edy += 10;
                            }
                            if (numy >= 25 && numy < 50) {//The player moves up
                                Edy -= 10;
                            }
                            if (numy >= 50 && numy < 75) {//The player does not move
                                Edy += 0;
                            }
                            if (numx < 25) {//The player moves left
                                Edx -= 10;
                            }
                            if (numx >= 25 && numx < 50) {//The player moves right
                                Edx += 10;
                            }
                            if (numx >= 50 && numx < 75) {//The Player does not move
                                Edx += 0;
                            }
                            for (int j = 0; j < change.nextInt(10); j++) {//Moves a direction a random number of times
                                RangedEnemies.get(i).move(Edx, Edy, Wx, Hy);
                            }
                        }
                    }

                }
                if (p1.getHealth() <= 0) {//Player Death control
                    p1.setAlive(false);
                }

                //Collisions
                Maze.playerwallcollision(p1); //Player and wall collisions
                if (healthhitcount % 100 == 0 && GameStart) {
                    playerwithenemyprojcoll(p1); //Player and enemy projectile        
                }
                projectiles.forEach(projectiles -> Maze.Spritewallcollision(projectiles, GameRoot, 1)); //Player projectile and wall collision
                Enemyprojectiles.forEach(Enemyprojectiles -> Maze.Spritewallcollision(Enemyprojectiles, GameRoot, 1)); //Enemy projectile and wall collision
                RangedEnemies.forEach(RangedEnemies -> Maze.Spritewallcollision(RangedEnemies, GameRoot, 2)); //Enemy and wall collision
                RangedEnemies.forEach(RangedEnemies -> enemyplayerprojcoll(RangedEnemies)); //Enemy and player proj collision
                spriteremove(primaryStage); // remove sprite that are not alive

                //Check to see if player enters door
                String MazeDirection = Maze.EnterDoor(p1);
                double px = 0;
                double py = 0;
                if (MazeDirection != null) {
                    if ("Down".equals(MazeDirection) && isValidIndex(indexX, indexY + 1)) {
                        indexY = indexY + 1;
                    }
                    if ("Up".equals(MazeDirection) && isValidIndex(indexX, indexY - 1)) {
                        indexY = indexY - 1;
                    }
                    if ("Left".equals(MazeDirection) && isValidIndex(indexX - 1, indexY)) {
                        indexX = indexX - 1;
                    }
                    if ("Right".equals(MazeDirection) && isValidIndex(indexX + 1, indexY)) {
                        indexX = indexX + 1;
                    }
                    px = Maze.lastx;
                    py = Maze.lasty;
                    DrawMaze(rooms[indexY][indexX], p1.getHealth(), p1.getMp(), px, py);//Passes new room, and players current health and mana
                    Maze = rooms[indexY][indexX];
                }

                //Adjusts health bar based on player health
                double healthdec;
                double phealth = p1.getHealth();
                healthdec = 295 * (phealth / 100);//Find the distance needed to ensure the health bar stays left
                double change = (295 - healthdec) / 2;
                if (HealthBarHP.getWidth() != healthdec) {
                    HealthBarHP.setWidth(healthdec);
                    HealthBarHP.setTranslateX(-change);//Moves the health bar so it stays in the left
                }
                HpText.setText("HP:      " + p1.getHealth() + "/100");
                if (p1.getHealth() < 0) {
                    HpText.setText("Player Has Died!");//When player has died
                    PlayerDied = true;
                }

                //Adjusts mana bar based on player Mp
                double Mpdec;
                double pMana = p1.getMp();
                Mpdec = 295 * (pMana / 100);//Find the distance needed to ensure the mana bar stays left
                double Manachange = (295 - Mpdec) / 2;
                if (ManaBarMP.getWidth() != Mpdec) {
                    ManaBarMP.setWidth(Mpdec);
                    ManaBarMP.setTranslateX(-Manachange);//Moves the mana bar so it stays in the left
                }
                MpText.setText("MP:      " + p1.getMp() + "/100");

                //update kill counter bar
                KillCount.setText("Kills: " + KillCounter);

                //Score Update
                if (Timer % 50 == 0 && p1.isAlive()) {
                    Score++;//Score increases every 10 ticks
                }
                if (floorcount > prevfloorcount) {//if new floor found
                    Score = Score + 100;//increase score by 100 each time
                    prevfloorcount++;
                }
                if (KillCounter > prevkillcount) {//if enemy found
                    Score = Score + 20;//increase score by 20 each time
                    prevkillcount++;
                }

                ScoreCount.setText("Score: " + Score);//OUTPUTS SCORE

                int RoomNum = Maze.roomnum;
                FloorCount.setText("Floor: " + RoomNum);//outputs floor number

                //Hp Chest
                HpChests.forEach(HpChests -> OpenChest(HpChests));//Opens chest
                //Mp Chest
                MpChests.forEach(MpChests -> OpenChest(MpChests));//Opens chest
                //Traps
                Traps.forEach(Traps -> TrapDmg(Traps));

            }
        };
        gametimer.start();
    }

    private void InGamePauseMenu(Stage primaryStage) {
        GameStart = false;
        PauseRoot = new Group();
        double width = WIDTH / 2;
        double height = HEIGHT / 2;

        Image imgBack = new Image("Settingsbox.JPG");
        ImageView Backg = new ImageView(imgBack);
        Backg.setFitWidth(width);
        Backg.setFitHeight(height);
        PauseRoot.getChildren().add(Backg);

        Label Title = new Label("Pause Menu");
        Title.setFont(new Font("Papyrus", 50));
        Title.setTextFill(Color.WHITE);

        BtnSaveScore = new Button();
        BtnSaveScore.setText("> Save Game");
        BtnSaveScore.setBackground(null);
        BtnSaveScore.setTextFill(Color.WHITE);
        BtnSaveScore.setFont(new Font("Papyrus", 30));
        BtnSaveScore.setOnAction(event -> {
            SaveScoreScene();
            popupStage.close();

        });

        BtnResume = new Button();
        BtnResume.setText("> Resume");
        BtnResume.setBackground(null);
        BtnResume.setTextFill(Color.WHITE);
        BtnResume.setFont(new Font("Papyrus", 30));

        BtnResume.setOnAction(e -> {
            gametimer.start();
            popupStage.close();
            GameStart = true;
        });

        Restartbtn = new Button();
        Restartbtn.setText("> Restart");
        Restartbtn.setBackground(null);
        Restartbtn.setTextFill(Color.WHITE);
        Restartbtn.setFont(new Font("Papyrus", 30));

        Restartbtn.setOnAction(e -> {
            gametimer.stop();
            Score = 0;//Resets Score to 0
            CreateGame(primaryStage);
            gametimer.start();
            popupStage.close();
            GameStart = true;
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
        vbox.getChildren().addAll(Title, BtnSaveScore, BtnResume, Restartbtn, ButtonExitGame);
        borderPane.setCenter(vbox);
        PauseRoot.getChildren().addAll(borderPane);
        popupStage = new Stage();
        popupStage.setScene(new Scene(PauseRoot));
        popupStage.initStyle(StageStyle.UNDECORATED);
        popupStage.centerOnScreen();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setHeight(height);
        popupStage.setWidth(width);
        popupStage.show();

    }

    private void shootPlayerprojectile() {
        for (int i = 0; i < projectiles.size(); ++i) {
            projectiles.get(i).moveprojectile();
        }
    }

    private void spawnRangedEnemies() {

        String imgpath = "Enemyleft.png";
        Random r = new Random();

        int height = (int) HEIGHT;
        int width = (int) WIDTH;
        for (int i = 0; i < r.nextInt(2) + 2; i++) {
            double x = r.nextInt(width - 250);
            double y = r.nextInt(height - 250);
            RangedEnemies.add(new Enemies(5, 5, imgpath, 10, 100, x, y, 30));
            RangedEnemies.get(i).Sprite.setFitHeight(130);
            RangedEnemies.get(i).Sprite.setFitWidth(100);
            if (RangedEnemies.get(i).Sprite != null) {
                GameRoot.getChildren().add(RangedEnemies.get(i).Sprite);
                RangedEnemycounter++;// counts the number of enemies  
            }

        }
    }

    private void EnemyProjectile(double px, double py) {
        for (int i = 0; i < RangedEnemies.size(); i++) {
            if (RangedEnemies.get(i).Sprite != null && RangedEnemies.get(i).isAlive()) {//Only alive enemies can attack
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

    }

    private StackPane HealthBar() {//Makes a health bar.
        HealthBarBckg = new Rectangle(300, 30); //Background bar
        HealthBarBckg.setFill(Color.GREY);
        HealthBarHP = new Rectangle(295, 25); //Health bar
        HealthBarHP.setFill(Color.RED);

        HpText = new Text("HP:      " + p1.getHealth() + "/100");//Outputs this over the red bar
        HpText.setFont(new Font("Papyrus", 20));
        HpText.setFill(Color.WHITE);

        StackPane sp = new StackPane();
        sp.getChildren().addAll(HealthBarBckg, HealthBarHP, HpText);//Add grey bar, then red bar, then text
        return sp;//Returns the stackpane
    }

    private StackPane ManaBar() {//Makes a mana bar.
        ManaBarBckg = new Rectangle(300, 30); //Background bar
        ManaBarBckg.setFill(Color.GREY);
        ManaBarMP = new Rectangle(295, 25);
        ManaBarMP.setFill(Color.DARKTURQUOISE); //Mana bar

        MpText = new Text("MP:      " + p1.getMp() + "/100");//Outputs this over the blue bar
        MpText.setFont(new Font("Papyrus", 20));
        MpText.setFill(Color.WHITE);

        StackPane sp = new StackPane();
        sp.getChildren().addAll(ManaBarBckg, ManaBarMP, MpText);//Add grey bar, then blue bar, then text

        return sp;//Returns the stackpane
    }

    private HBox Scores(Room maze) {
        HBox Scorebox = new HBox();
        KillCount = new Text("Kills: " + KillCounter);//For the kill counts
        KillCount.setFont(new Font("Papyrus", 40));
        KillCount.setFill(Color.WHITE);

        int RoomNum = maze.roomnum;
        FloorCount = new Text("Floor: " + RoomNum);//For the floor number
        FloorCount.setFont(new Font("Papyrus", 40));
        FloorCount.setFill(Color.WHITE);

        ScoreCount = new Text("Score: " + Score);//For the Score count
        ScoreCount.setFont(new Font("Papyrus", 40));
        ScoreCount.setFill(Color.WHITE);

        Scorebox.getChildren().addAll(KillCount, ScoreCount, FloorCount);//Add all scores to vbox
        Scorebox.setSpacing(35);
        Scorebox.setAlignment(Pos.TOP_RIGHT);

        return Scorebox;

    }

    private void spawnHpChests() {
        String HpChestOpen = "HpChestOpen.png";
        String HpChestClosed = "HpChestClosed.png";
        Random r = new Random();

        int height = (int) HEIGHT;
        int width = (int) WIDTH;
        for (int i = 0; i < r.nextInt(2) + 2; i++) {
            double x = r.nextInt(width - 250);
            double y = r.nextInt(height - 250);
            int HpBoost = r.nextInt(5) + 5;
            HpChests.add(new Chests(5, 5, HpChestClosed, "HpChest", x, y, HpBoost));
            HpChests.get(i).Sprite.setFitHeight(50);
            HpChests.get(i).Sprite.setFitWidth(50);
            if (HpChests.get(i).Sprite != null) {
                GameRoot.getChildren().add(HpChests.get(i).Sprite);
            }
        }
    }

    private void spawnMpChests() {
        String MpChestOpen = "MpChestOpen.png";
        String MpChestClosed = "MpChestClosed.png";
        Random r = new Random();

        int height = (int) HEIGHT;
        int width = (int) WIDTH;
        for (int i = 0; i < r.nextInt(2) + 2; i++) {
            double x = r.nextInt(width - 250);
            double y = r.nextInt(height - 250);
            int MpBoost = r.nextInt(5) + 5;
            MpChests.add(new Chests(5, 5, MpChestClosed, "MpChest", x, y, MpBoost));
            MpChests.get(i).Sprite.setFitHeight(50);
            MpChests.get(i).Sprite.setFitWidth(50);
            if (MpChests.get(i).Sprite != null) {
                GameRoot.getChildren().add(MpChests.get(i).Sprite);
            }
        }
    }

    private void OpenChest(Chests Chest) {
        Image HpChestOpen = new Image("HpChestOpen.png");
        Image MpChestOpen = new Image("MpChestOpen.png");
        if (p1.Sprite.getBoundsInParent().intersects(Chest.Sprite.getBoundsInParent()) && ClickChest == true) {
            if (Chest.Boost != 0) {//Makes sure score increase once per chest opened
                Score = Score + 15;//increases score by 15 with each chest open
            }
            if (Chest.Type == "HpChest" && p1.getHealth() <= 100 && p1.getHealth() >= 0) {
                Chest.Sprite.setImage(HpChestOpen);
                p1.setHealth(p1.getHealth() + Chest.Boost);
                //HpText.setText("  + " + Chest.Boost + " Hp");
                int counter = 0;
                Text notify = null;
                while (counter < 100) {
                    HpText.setText("  + " + Chest.Boost + " Hp");
                    if (Chest.Boost > 0) {
                        notify = new Text("  + " + Chest.Boost + " Hp");
                    }
                    notify.setFont(new Font("Papyrus", 20));
                    notify.setFill(Color.WHITE);
                    notify.setX(Chest.getXSprite());
                    notify.setY(Chest.getYSprite());
                    GameRoot.getChildren().add(notify);
                    counter++;
                }
                GameRoot.getChildren().remove(notify);
                Chest.Boost = 0;//Makes sure the chest cannot be used again

            }
            if (Chest.Type == "MpChest" && p1.getHealth() <= 100 && p1.getHealth() >= 0) {
                Chest.Sprite.setImage(MpChestOpen);
                p1.setMp(p1.getMp() + Chest.Boost);
                int counter = 0;
                Text notify = null;
                while (counter < 100) {
                    HpText.setText("  + " + Chest.Boost + " Mp");
                    if (Chest.Boost > 0) {
                        notify = new Text("  + " + Chest.Boost + " Mp");
                    }
                    notify.setFont(new Font("Papyrus", 20));
                    notify.setFill(Color.WHITE);
                    notify.setX(Chest.getXSprite());
                    notify.setY(Chest.getYSprite());
                    GameRoot.getChildren().add(notify);
                    counter++;
                }
                GameRoot.getChildren().remove(notify);
                Chest.Boost = 0;//Makes sure the chest cannot be used again
            }
            if (p1.getMp() > 100) {//If Mp goes above 100, this caps it to 100
                p1.setMp(100);
            }
            if (p1.getHealth() > 100) {//If Hp goes above 100, this caps it to 100
                p1.setHealth(100);
            }
        }
    }

    private void spawnTraps() {
        String Trap = "Trap.jpeg";
        Random r = new Random();

        int height = (int) HEIGHT;
        int width = (int) WIDTH;
        for (int i = 0; i < r.nextInt(5) + 2; i++) {
            double x = r.nextInt(width - 250);
            double y = r.nextInt(height - 250);
            int Damage = r.nextInt(2) + 1;
            Traps.add(new Traps(5, 5, Trap, x, y, Damage));
            Traps.get(i).Sprite.setFitHeight(50);
            Traps.get(i).Sprite.setFitWidth(50);
            if (Traps.get(i).Sprite != null) {
                GameRoot.getChildren().add(Traps.get(i).Sprite);
            }
        }
    }

    private void TrapDmg(Traps Trap) {
        if (p1.Sprite.getBoundsInParent().intersects(Trap.Sprite.getBoundsInParent())) {
            if (Timer % 150 == 0) {//Every 150 ticks
                p1.hit(Trap.TrapDamage);
            } else {
                p1.hit(0);
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

    private void enemyplayerprojcoll(Enemies Enemy) {
        Iterator<Projectiles> it = projectiles.iterator();
        while (it.hasNext()) {
            Projectiles proj = it.next();
            if (proj.Sprite.getBoundsInParent().intersects(Enemy.Sprite.getBoundsInParent())) {
                proj.setAlive(false);
                Enemy.setAlive(false);
            }
        }
    }

    private void playerwithenemyprojcoll(Player p1) { //player and enemy proj coll
        Iterator<Projectiles> it = Enemyprojectiles.iterator();
        while (it.hasNext()) {
            Projectiles proj = it.next();
            if (proj.Sprite.getBoundsInParent().intersects(p1.Sprite.getBoundsInParent()) && proj.isAlive()) {
                proj.setAlive(false);
                p1.setCollision(true);
                p1.hit(5);
                System.out.println(p1.getHealth());
            }
        }
    }

    private void spriteremove(Stage primaryStage) { //Remove the sprites from the game root, if they are not alive

        if (p1.isAlive() == false && p1.Removed == false) {//Remove player if dead and has not been removed yet
            GameRoot.getChildren().remove(p1.Sprite);//remove player
            GameRoot.getChildren().remove(projectiles);//remove players projectiles, so player can shoot after death
            p1.Removed = true;//Ensures that this is only called once when the player is killed
            //InGamePauseMenu(primaryStage);//Calls in game menu when player dies
            SaveScoreScene();
        }
        for (int i = 0; i < Enemyprojectiles.size(); i++) {//Remove enemy projectile if dead
            if (Enemyprojectiles.get(i).isAlive() == false) {
                GameRoot.getChildren().remove(Enemyprojectiles.get(i).Sprite);
            }
        }
        for (int j = 0; j < projectiles.size(); j++) {//Remove Player projectile if dead
            if (projectiles.get(j).isAlive() == false) {
                GameRoot.getChildren().remove(projectiles.get(j).Sprite);
            }
        }
        for (int k = 0; k < RangedEnemies.size(); k++) {//Remove enemy if dead
            if (RangedEnemies.get(k).isAlive() == false) {
                GameRoot.getChildren().remove(RangedEnemies.get(k).Sprite);//removes enemy
                RangedEnemies.remove(k);
                KillCounter++;//counts the kills
                Score = Score + 20;//20 add to score at every kill
            }
        }
    }

    private void SaveScoreScene() {
        int playerScore = Score;
        SaveRoot = new Group();
        double width = WIDTH / 2;
        double height = HEIGHT / 2;

        Image imgBack = new Image("Settingsbox.JPG");
        ImageView Backg = new ImageView(imgBack);
        Backg.setFitWidth(width);
        Backg.setFitHeight(height);
        SaveRoot.getChildren().add(Backg);

        Label Title = new Label("Save Score");
        Title.setFont(new Font("Papyrus", 50));
        Title.setTextFill(Color.WHITE);

        Label User = new Label("Enter Username:");
        User.setFont(new Font("Papyrus", 30));
        User.setTextFill(Color.WHITE);
        TextField UserNameField = new TextField();

        UserNameField.setMaxWidth(width - 150);
        UserNameField.setFont(new Font("Papyrus", 20));
        Button Submitbtn = new Button("Submit");
        Submitbtn.setOnAction(event -> {
            String username = UserNameField.getText();
            SaveUserName(username, playerScore);
            Stage.setScene(MenuScene);//Goes to main menu
            SaveScoreStage.close();//Save screen closes
        });

        //Regex - Input Validation
        Alert Error = new Alert(AlertType.NONE);//Alert
        UserNameField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if (!UserNameField.getText().matches("^[A-Z0-9]{4,8}$")) {//When not matching A-Z (Caps) or 1-9 between 4-8 char
                    //set the textField empty
                    UserNameField.setText("");
                    Error.setAlertType(AlertType.ERROR);//Error Alert
                    Error.setTitle("Incorrect Format");
                    Error.setContentText("User Name cannot contain any symbols, has to be between 4 - 8 characters long, and has to be in Upper Case!");
                    Error.show();
                } else {
                    Error.close();
                }
            }

        });

        Label UserScore = new Label("User's Current Score: " + playerScore);
        UserScore.setFont(new Font("Papyrus", 25));
        UserScore.setTextFill(Color.WHITE);

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
        vbox.getChildren().addAll(Title, User, UserNameField, UserScore, Submitbtn, ButtonExitGame);
        borderPane.setCenter(vbox);
        SaveRoot.getChildren().addAll(borderPane);
        Scene SaveScene = new Scene(SaveRoot);
        SaveScoreStage = new Stage();
        SaveScoreStage.setScene(SaveScene);
        SaveScoreStage.initStyle(StageStyle.UNDECORATED);//Removes the header bar on the new screen
        SaveScoreStage.centerOnScreen();
        SaveScoreStage.initModality(Modality.APPLICATION_MODAL);//Makes it so game cannot be touched until this closes
        SaveScoreStage.setHeight(height);
        SaveScoreStage.setWidth(width);
        SaveScoreStage.show();
    }

    public void SaveUserName(String UserName, int Score) {
        Alert NoFileFound = new Alert(AlertType.NONE);//Alert
        try {
            FileWriter writer = new FileWriter("SaveGameDetails.txt", true);
            BufferedWriter bufferedwriter = new BufferedWriter(writer);
            bufferedwriter.write(UserName + "                              ");
            bufferedwriter.write("||                              " + Score + "\n");
            bufferedwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            NoFileFound.setAlertType(AlertType.ERROR);//Error Alert
            NoFileFound.setTitle("No File Found");
            NoFileFound.setContentText("Error! File Not Found!");
            NoFileFound.show();//shows that there is no file found. alert  notifys player
        }
    }

    public VBox LoadDetails() {
        VBox ScoresList = new VBox();
        Alert NoFileFound = new Alert(AlertType.NONE);//Alert
        try {
            FileReader Reader = new FileReader("SaveGameDetails.txt");
            BufferedReader bufferedreader = new BufferedReader(Reader);
            String line = bufferedreader.readLine();
            while (line != null) {//Loop through all lines
                Label OneScore = new Label(line);//Adds line to a label
                OneScore.setFont(new Font("Papyrus", 25));
                OneScore.setTextFill(Color.WHITE);
                ScoresList.getChildren().add(OneScore);//Adds the label to the vbox
                line = bufferedreader.readLine();//next line
            }
            bufferedreader.close();
            return ScoresList;
        } catch (IOException e) {
            e.printStackTrace();
            NoFileFound.setAlertType(AlertType.ERROR);//Error Alert
            NoFileFound.setTitle("No File Found");
            NoFileFound.setContentText("Error! File Not Found!");
            NoFileFound.show();//shows that there is no file found. alert  notifys player
            return null;//If no file is found --> Null is returned
        }

    }

    public void music() {
        String BackMusic = "BackGmusic.mp3";
        Media sound = new Media(new File(BackMusic).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(new Runnable() {//Makes sure to loop the music
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.play();
    }

    //}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
