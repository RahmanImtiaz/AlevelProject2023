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
import javafx.scene.layout.HBox;
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

    boolean PlayerDied;

    int upcount = 1, downcount = 1, rightcount = 1, leftcount = 1;

    Rectangle HealthBarBckg;
    Rectangle HealthBarHP;
    Text HpText;

    Rectangle ManaBarBckg;
    Rectangle ManaBarMP;
    Text MpText;

    Text KillCount, FloorCount, ScoreCount;

    int Score;

    AnimationTimer gametimer;

    Room[][] rooms = new Room[10][10];
    int CurrentRoomx, CurrentRoomy, floornum;

    Stage Stage;
    Stage popupStage;

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
    private int KillCounter = 0;

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

    double Wx = Screen.getPrimary().getBounds().getMaxX();
    double Hy = Screen.getPrimary().getBounds().getMaxY();

    Button BtnPlay, Btnscore, BtnSet, BtnExit, BtnGuide;
    Button BtnSaveScore, BtnResume, Restartbtn, ButtonExitGame;
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

        //p1 = new Player(30, 30, Down1); //creates the player object
        //GenerateMaze();
        /////////////////////
        Random rand = new Random();
        CurrentRoomx = rand.nextInt(10);// 9 or 10 idk
        CurrentRoomy = rand.nextInt(10);

        //show start room on screen before maze gen
        ///rooms[CurrentRoomy][CurrentRoomx] = new Room(WIDTH,HEIGHT,p1);
        ///rooms[CurrentRoomy][CurrentRoomx].drawRoom(GameRoot,rooms[CurrentRoomy][CurrentRoomx]);
        GenerateMaze(CurrentRoomx, CurrentRoomy);
        DrawMaze(rooms[CurrentRoomy][CurrentRoomx]);
        //Maze = rooms[CurrentRoomy][CurrentRoomx];
        //add player
        p1 = new Player(30, 30, Down1); //creates the player object
        ///System.out.println("Start maze at x="+CurrentRoomx+" and y="+CurrentRoomy);

        //GameRoot.getChildren().add(p1.rect);
        GameRoot.getChildren().add(p1.Sprite);
        //GameRoot.getChildren().add(p1.radiuscircleP);

        Scene sceneGame = new Scene(GameRoot, WIDTH, HEIGHT);
        sceneGame.setCursor(Cursor.CROSSHAIR); //changes how the mouse will look
        controls(sceneGame, primaryStage); //calls the subroutine resposible for the key listeners
        loop(primaryStage); //calls the subroutine with the animation timer/gameloop

        spawnRangedEnemies();//calls the subroutine responsible for enemy spawns

        HBox BarBox = new HBox();
        BorderPane scoreBP = new BorderPane();
        scoreBP.setRight(Scores());
        BarBox.getChildren().addAll(HealthBar(), MananBar(), scoreBP);//health bar and mana bar
        BarBox.setSpacing(10);
        GameRoot.getChildren().add(BarBox);

        primaryStage.setScene(sceneGame);
        primaryStage.setFullScreen(true);

    }

    private void DrawMaze() {
        GameRoot.getChildren().clear();
        Image bgimg = new Image("Blackbackround.png");
        ImageView Backg = new ImageView(bgimg);
        Backg.setFitWidth(WIDTH);
        Backg.setFitHeight(HEIGHT);
        GameRoot.getChildren().add(Backg);
        Maze = new Room(WIDTH, HEIGHT, p1);
        for (Cell row[] : Maze.cells) {
            for (Cell r : row) {
                GameRoot.getChildren().add(r.Cell);
            }
        }

        ///////////////////////
    }

    private void DrawMaze(Room Maze) {
        GameRoot.getChildren().clear();
        Image bgimg = new Image("Blackbackround.png");
        ImageView Backg = new ImageView(bgimg);
        Backg.setFitWidth(WIDTH);
        Backg.setFitHeight(HEIGHT);
        GameRoot.getChildren().add(Backg);

        for (Cell row[] : Maze.cells) {
            for (Cell r : row) {
                GameRoot.getChildren().add(r.Cell);
            }
        }

        ///////////////////////
    }

    private void GenerateMaze(int x, int y) {
        rooms[y][x] = new Room(WIDTH, HEIGHT, p1);
        rooms[y][x].isAddedToMaze(); // make visited true
        Maze = rooms[y][x];
        //System.out.println("Room: ");
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
        
        
        /*String MazeDirection = Maze.EnterDoor(p1);
        if (MazeDirection != null) {
            if (MazeDirection == "Down") {
                y = y+1;
            }
            if (MazeDirection == "Up") {
                y = y-1;
            }
            if (MazeDirection == "Left") {
                x = x-1;
            }
            if (MazeDirection == "Right") {
                x = x+1;
            }
            GenerateMaze(x, y);
            Maze = rooms[y][x];
            DrawMaze(rooms[y][x]);
        }
        */
}

private boolean isValidRoom(int x, int y) {
        if (x > -1 && x < 10 && y > -1 && y < 10 && rooms[y][x]!=null) {
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
                    if (!Shooting && p1.getMp()>0 && p1.isAlive()) {
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
                        p1.setMp(p1.getMp()-5);//Take away mana
                    }
                    break;
                case ESCAPE:
                    Escape = true;
                    gametimer.stop();
                    InGamePauseMenu(primaryStage);
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
                p1.setXspeed(dx);
                p1.setYspeed(dy);
                p1.move(dy, dx, Wx, Hy); // player movement method

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

                //Collisions
                Maze.playerwallcollision(p1); //Player and wall collisions
                //healthhitcount++;
                if (healthhitcount % 100 == 0) {
                    playerwithenemyprojcoll(p1); //Player and enemy projectile        
                }
                projectiles.forEach(projectiles -> Maze.Spritewallcollision(projectiles, GameRoot, 1)); //Player projectile and wall collision
                Enemyprojectiles.forEach(Enemyprojectiles -> Maze.Spritewallcollision(Enemyprojectiles, GameRoot, 1)); //Enemy projectile and wall collision
                RangedEnemies.forEach(RangedEnemies -> Maze.Spritewallcollision(RangedEnemies, GameRoot, 2)); //Enemy and wall collision
                RangedEnemies.forEach(RangedEnemies -> enemyplayerprojcoll(RangedEnemies)); //Enemy and player proj collision
                spriteremove(primaryStage); // remove sprite that are not alive

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
               
               
               
            }

        };
        gametimer.start();

       
    }

    private void InGamePauseMenu(Stage primaryStage) {
        //BtnSaveScore, BtnResume, ButtonExitGame
        PauseRoot = new Group();
        double width = WIDTH / 2;
        double height = HEIGHT / 2;

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

        Restartbtn = new Button();
        Restartbtn.setText("> Restart");
        Restartbtn.setBackground(null);
        Restartbtn.setTextFill(Color.WHITE);
        Restartbtn.setFont(new Font("Papyrus", 30));
       

        Restartbtn.setOnAction(e -> {
            gametimer.stop();
            CreateGame(primaryStage);
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
        vbox.getChildren().addAll(Title, BtnSaveScore, BtnResume, Restartbtn, ButtonExitGame);
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
        for (int i = 0; i < projectiles.size(); ++i) {
            projectiles.get(i).moveprojectile();
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
            RangedEnemycounter++;// counts the number of enemies
        }
    }

    private void spawnMaleeEnemies() {

    }

    private void EnemyProjectile(double px, double py) {
        for (int i = 0; i < RangedEnemies.size(); i++) {
            if (RangedEnemies.get(i).Sprite != null && RangedEnemies.get(i).isAlive()) {

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



    private StackPane HealthBar() {
        HealthBarBckg = new Rectangle(300, 30);
        HealthBarBckg.setFill(Color.GREY);
        HealthBarHP = new Rectangle(295, 25);
        HealthBarHP.setFill(Color.RED);

        HpText = new Text("HP:      " + p1.getHealth() + "/100");
        HpText.setFont(new Font("Papyrus", 20));
        HpText.setFill(Color.WHITE);

        //HBox hbox = new HBox();
        StackPane sp = new StackPane();
        sp.getChildren().addAll(HealthBarBckg, HealthBarHP, HpText);
        //hbox.getChildren().add(sp);

        return sp;
    }

    private StackPane MananBar() {
        ManaBarBckg = new Rectangle(300, 30);
        ManaBarBckg.setFill(Color.GREY);
        ManaBarMP = new Rectangle(295, 25);
        ManaBarMP.setFill(Color.AQUA);

        MpText = new Text("MP:      " + p1.getMp() + "/100");
        MpText.setFont(new Font("Papyrus", 20));
        MpText.setFill(Color.WHITE);

        //HBox Mbox = new HBox();
        StackPane sp = new StackPane();
        sp.getChildren().addAll(ManaBarBckg, ManaBarMP, MpText);
        //Mbox.getChildren().add(sp);

        return sp;
    }
   
    private HBox Scores(){
        HBox Scorebox = new HBox();
        KillCount = new Text("Kills: " + KillCounter);
        KillCount.setFont(new Font("Papyrus", 40));
        KillCount.setFill(Color.WHITE);
       
        FloorCount = new Text("Floor: " + floornum);
        FloorCount.setFont(new Font("Papyrus", 40));
        FloorCount.setFill(Color.WHITE);
       
        ScoreCount = new Text("Score: " + Score);
        ScoreCount.setFont(new Font("Papyrus", 40));
        ScoreCount.setFill(Color.WHITE);
       
        Scorebox.getChildren().addAll(KillCount, ScoreCount, FloorCount);
        Scorebox.setSpacing(35);
        Scorebox.setAlignment(Pos.TOP_RIGHT);
       
        return Scorebox;
   
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

    //}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
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

        if (p1.isAlive() == false) {//Remove player if dead
            GameRoot.getChildren().remove(p1.Sprite);//remove player
            GameRoot.getChildren().remove(projectiles);//remove players projectiles
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
                //System.out.println(KillCounter);//used for testing
            }
        }
    }
}
