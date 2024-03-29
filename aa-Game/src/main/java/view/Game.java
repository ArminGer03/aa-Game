package view;

import contoller.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Ball;
import model.MainCircle;
import model.Shooter;
import utility.DataClass;
import utility.RandomGenerator;
import view.Animations.RotateAnimation;
import view.Animations.ShootAnimation;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Objects;

import static java.lang.Math.PI;


public class Game extends Application {

    public static GameController gameController;
    public static int BALLS_COUNT = DataClass.getCurrentUser().getBalls();
    public static int shotBalls = 0;
    public static LinkedList<Double> initialRandomAngles;
    private static final int COUNTDOWN_TIME = 90; // in seconds
    private static int remainingTime = COUNTDOWN_TIME;
    private static RotateAnimation rotateAnimation;
    private static Timeline timeline = null;

    private static Pane gamePane;
    private static Label phaseLabel;
    private static Label WindLabel;
    private static Label scoreLabel;
    private static MediaPlayer backGroundTrack;
    private static ProgressBar iceProgressBar;
    public static boolean isFinished = false;


    @Override
    public void start(Stage stage) throws Exception {
        //make scene
        URL url = LoginMenu.class.getResource("/fxml/gamePane.fxml");
        assert url != null;
        Pane gamePane = FXMLLoader.load(url);

        //B&W theme
        if (!DataClass.getCurrentUser().isNormalTheme()){
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1.0);
            gamePane.setEffect(colorAdjust);
        }


        Scene scene = new Scene(gamePane,500,800);

        //main circle initializing
        Color mainCircleColor = DataClass.getCurrentUser().getColor();
        MainCircle mainCircle = new MainCircle(250 , 350, mainCircleColor);

        //gameController
        gameController = new GameController(mainCircle.getCircleBorder());
        gameController.setMainCircle(mainCircle);

        //create objects
        Ball[] balls = createBalls(BALLS_COUNT);
        Shooter shooter = createShooter(gamePane,balls);
        Bounds bounds = shooter.getBoundsInParent();
        setBallsPositionInShooter(bounds,balls);

        //add objects to gamePane
        gamePane.getChildren().add(shooter);
        gamePane.getChildren().addAll(balls);
        gamePane.getChildren().add(mainCircle);
        randomInitialBalls(gamePane);
        addTimer(gamePane);
        createPhaseTracker(gamePane);
        createSoundTrack();
        createIceProgressBar(gamePane);
        createWindTracker(gamePane);
        createProfilePickViewer(gamePane);
        createScoreBoard(gamePane);

        //set difficulty
        setGameDifficulty();

        setGamePane(gamePane);

        gamePane.getChildren().get(0).requestFocus();

        stage.setScene(scene);
        stage.show();
    }


    public static void setGameDifficulty() {
        String difficulty = DataClass.getCurrentUser().getGameMode();
        switch (difficulty) {
            case "Easy" -> {
                ShootAnimation.setSpeed(10);
                RotateAnimation.setRotationSpeed(0.005);
                GameController.setIceModeDifficulty(7);
            }
            case "Medium" -> {
                ShootAnimation.setSpeed(15);
                RotateAnimation.setRotationSpeed(0.010);
                GameController.setIceModeDifficulty(5);
            }
            case "Hard" -> {
                ShootAnimation.setSpeed(20);
                RotateAnimation.setRotationSpeed(0.015);
                GameController.setIceModeDifficulty(3);
            }
        }
    }

    private Shooter createShooter(Pane gamePane,Ball[] balls) {
        Shooter shooter = new Shooter(245,590,10,185);
        shooter.setOnKeyPressed(keyEvent -> {
            String keyName = keyEvent.getCode().getName();

            if (keyName.equals(DataClass.getCurrentUser().getLeftKey()) &&
                    GameController.getPhase() == 4 && !isFinished){
                Game.gameController.moveLeft(shooter);
            }
            else if (keyName.equals(DataClass.getCurrentUser().getRightKey()) &&
                    GameController.getPhase() == 4 && !isFinished){
                Game.gameController.moveRight(shooter);
            }
            else if (keyName.equals(DataClass.getCurrentUser().getShootKey()) && !isFinished){
                try {
                    Game.gameController.ballShooting(gamePane , balls);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (keyName.equals("Tab") && GameController.getIceProgress() == 1 && !isFinished){
                Game.gameController.iceMode();
            }
            else if (keyName.equals("Esc") && !isFinished){
                try {
                    pauseGame();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (keyName.equals("Space") && isFinished){
                try {
                    new ScoreBoardMenu().start(DataClass.getStage());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            //todo add esc for pause

            Bounds bounds = shooter.getBoundsInParent();
            setBallsPositionInShooter(bounds,balls);

        });

        return shooter;
    }

    private void addTimer(Pane gamePane){
        Text timerText = new Text(formatTime(remainingTime));
        timerText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        timerText.setFill(Color.BLACK);
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            remainingTime--;
            if (remainingTime >= 0) {
                timerText.setText(formatTime(remainingTime));
            }
            else {
                stopTimer();
                try {
                    lose();
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timerText.setLayoutX(20);
        timerText.setLayoutY(20);
        gamePane.getChildren().add(timerText);
        timeline.play();
    }

    private void stopTimer() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    private void createWindTracker(Pane gamePane){
        WindLabel = new Label("Wind: " + GameController.getWindDegree());
        WindLabel.setLayoutX(420);
        WindLabel.setLayoutY(35);
        WindLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        gamePane.getChildren().add(WindLabel);
    }


    public static void updateWindTracker(int wind){
        String windText = "Wind: " + wind;
        WindLabel.setText(windText);
        ShootAnimation.setWindAngle(wind * PI / 180);
    }

    private static void createPhaseTracker(Pane gamePane){
        phaseLabel = new Label("Phase " + GameController.getPhase());
        updatePhaseLabel(GameController.getPhase());
        phaseLabel.setLayoutX(20);
        phaseLabel.setLayoutY(30);
        gamePane.getChildren().add(phaseLabel);
    }

    public static void updatePhaseLabel(int phase) {
        String phaseText = "Phase " + phase;
        phaseLabel.setText(phaseText);
        switch (phase) {
            case 2 -> {
                gameController.activatePhase2();
                phaseLabel.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-opacity: 0.8; " +
                        "-fx-background-color: orange;");
            }
            case 3 -> {
                gameController.activatePhase3();
                phaseLabel.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-opacity: 0.8; " +
                        "-fx-background-color: yellow;");
            }
            case 4 -> {
                gameController.activatePhase4();
                phaseLabel.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-opacity: 0.8; " +
                        "-fx-background-color: green;");
            }
            default -> phaseLabel.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-opacity: 0.8; " +
                    "-fx-background-color: red;");
        }
    }

    private static void createIceProgressBar(Pane gamePane){
        Label nameLabel = new Label("Ice Progress:");
        iceProgressBar = new ProgressBar(GameController.getIceProgress());
        iceProgressBar.setStyle("-fx-accent: #b2FFFF");
        iceProgressBar.setLayoutX(20);
        iceProgressBar.setLayoutY(750);
        nameLabel.setLayoutX(20);
        nameLabel.setLayoutY(750);
        gamePane.getChildren().addAll(iceProgressBar,nameLabel);
    }

    public static void updateIceProgressBar(double progress) {
        iceProgressBar.setProgress(progress);
    }
    public void createSoundTrack() throws URISyntaxException {
        if(DataClass.getCurrentUser().isUnMute()){
            String path = "/soundtracks/" + DataClass.getCurrentUser().getSoundTrackPath() + ".mp3";
            URI uri = Objects.requireNonNull(LoginMenu.class.getResource(path)).toURI();
            Media soundtrack = new Media(uri.toString());

            backGroundTrack = new MediaPlayer(soundtrack);

            backGroundTrack.setVolume(0.1);

            backGroundTrack.play();
        }
    }


    private Ball[] createBalls(int n){
        Ball[] balls = new Ball[n];

        for (int i = 0; i<n ; i++){
            balls[i] = new Ball(100,100,10,i);
        }

        return balls;
    }

    private void setBallsPositionInShooter( Bounds bounds, Ball[] balls){

        for (int i = shotBalls; i < BALLS_COUNT ; i++){
            balls[i].getCircle().setCenterX(bounds.getMinX() + bounds.getWidth() / 2);
            if (shotBalls < 5){
                if (i > 5 + shotBalls)
                    balls[i].getCircle().setCenterY(bounds.getMaxY());
                else
                    balls[i].getCircle().setCenterY(bounds.getMinY() + (i - shotBalls) * bounds.getHeight() / 5);
            }
            else{
                balls[i].getCircle().setCenterY(bounds.getMinY() + (i - shotBalls) * bounds.getHeight() / 5);
            }
        }
    }

    public static void randomInitialBalls(Pane gamePane){

        RotateAnimation rotateAnimation = new RotateAnimation(GameController.getRotatingBalls(),
                GameController.getBorderCircle(), 1,0.01,true);
        setRotateAnimation(rotateAnimation);

        if (initialRandomAngles == null){
            initialRandomAngles = new LinkedList<>();
            double angle = 0;
            for (int i = 0; i < 5; i++){
                boolean check = true;
                while(check){
                    angle = RandomGenerator.randomAngle();
                    check = false;
                    for (double existAngle: initialRandomAngles) {
                        if(existAngle == angle){
                            check = true;
                            break;
                        }
                    }
                }

                Ball ball = new Ball(0,0,10,0);
                ball.setAngleWithCenter(angle);
                ball.getNumberText().setVisible(false);
                gamePane.getChildren().add(ball);
                GameController.getRotatingBalls().add(ball);
                initialRandomAngles.add(angle);
                rotateAnimation.play();
            }
        }

    }

    private void createProfilePickViewer(Pane gamePane) throws URISyntaxException {
        URI uri = Objects.requireNonNull(LoginMenu.class.getResource(DataClass.getCurrentUser().getImagePath())).toURI();
        Image profileImage = new Image(uri.toString(),40,40, true,true);
        ImageView profilePicViewer = new ImageView();
        profilePicViewer.setImage(profileImage);
        profilePicViewer.setLayoutX(450);
        profilePicViewer.setLayoutY(750);
        gamePane.getChildren().add(profilePicViewer);
    }

    private void createScoreBoard(Pane gamePane) {
        scoreLabel = new Label("score: " + GameController.getScore());
        scoreLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        scoreLabel.setLayoutX(420);
        scoreLabel.setLayoutY(20);
        gamePane.getChildren().add(scoreLabel);
    }

    public static void updateScoreLabel() {
        String scoreText = "score: " + GameController.getScore();
        scoreLabel.setText(scoreText);
    }

    public static RotateAnimation getRotateAnimation() {
        return rotateAnimation;
    }

    public static void setRotateAnimation(RotateAnimation rotateAnimation) {
        Game.rotateAnimation = rotateAnimation;
    }

    public static Pane getGamePane() {
        return gamePane;
    }

    public void setGamePane(Pane gamePane) {
        Game.gamePane = gamePane;
    }

    public static int getRemainingTime() {
        return remainingTime;
    }

    public static void lose() throws URISyntaxException {
        finish();
        BackgroundFill backgroundFill = new BackgroundFill(Color.RED, null, null);
        Background background = new Background(backgroundFill);
        gamePane.setBackground(background);

        if(DataClass.getCurrentUser().isUnMute()){
            URI uri;
            uri = Objects.requireNonNull(LoginMenu.class.getResource("/soundtracks/GameOver.mp3")).toURI();
            Media shootSoundMedia = new Media(uri.toString());
            MediaPlayer shootSound = new MediaPlayer(shootSoundMedia);
            shootSound.setVolume(0.5);
            shootSound.play();
        }
    }


    public static void win() throws URISyntaxException {
        finish();
        GameController.setHighScore();
        BackgroundFill backgroundFill = new BackgroundFill(Color.GREEN, null, null);
        Background background = new Background(backgroundFill);
        gamePane.setBackground(background);
        if(DataClass.getCurrentUser().isUnMute()){
            URI uri;
            uri = Objects.requireNonNull(LoginMenu.class.getResource("/soundtracks/veryNice.mp3")).toURI();
            Media shootSoundMedia = new Media(uri.toString());
            MediaPlayer shootSound = new MediaPlayer(shootSoundMedia);
            shootSound.setVolume(0.5);
            shootSound.play();
        }
    }

    public static void finish(){
        isFinished = true;
        if(DataClass.getCurrentUser().isUnMute()){
            backGroundTrack.stop();
        }
        rotateAnimation.stop();
        timeline.stop();
        if (GameController.iceModeActivated){
            GameController.timerIce.cancel();
        }
        if (GameController.phase2Activated){
            GameController.phase2TimerSize.cancel();
            GameController.phase2TimerDirection.cancel();
        }
        if (GameController.phase3Activated){
            GameController.phase3Timer.cancel();
        }
        if (GameController.phase4Activated){
            GameController.phase4Timer.cancel();
        }
    }

    private void pauseGame() throws IOException {

        if(DataClass.getCurrentUser().isUnMute()){
            backGroundTrack.pause();
        }
        rotateAnimation.pause();
        timeline.pause();

        Stage pauseStage = new Stage();
        URL url = LoginMenu.class.getResource("/fxml/PauseMenu.fxml");
        assert url != null;
        AnchorPane anchorPane = FXMLLoader.load(url);
        Scene scene = new Scene(anchorPane,600,400);
        pauseStage.setScene(scene);
        DataClass.setPauseStage(pauseStage);
        pauseStage.show();
    }

    public static void playGame() {
        if(DataClass.getCurrentUser().isUnMute()){
            backGroundTrack.play();
        }
        rotateAnimation.play();
        timeline.play();
    }

    public static void changeMusic() throws URISyntaxException {
        if(DataClass.getCurrentUser().isUnMute()){
            String path = "/soundtracks/" + DataClass.getCurrentUser().getSoundTrackPath() + ".mp3";
            URI uri = Objects.requireNonNull(LoginMenu.class.getResource(path)).toURI();
            Media soundtrack = new Media(uri.toString());

            if (backGroundTrack.getStatus() == MediaPlayer.Status.PLAYING ||
                    backGroundTrack.getStatus() == MediaPlayer.Status.PAUSED) {
                backGroundTrack.stop();
            }

            backGroundTrack = new MediaPlayer(soundtrack);

            backGroundTrack.setVolume(0.1);

            backGroundTrack.play();
        }
    }
}
