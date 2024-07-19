package model;

import enums.Level;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import view.GameAtomicBomber;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game {
    private  Plane plane ;
    private final double WIDTH = 900.0;
    private final double HEIGHT = 600.0;
    private String username;
    private int score = 0;
    private GameAtomicBomber gameLauncher;
    private final ArrayList<Transition> animations = new ArrayList<>();
    private final Group trucks = new Group();
    private final Group tanks =new Group();
    private final Group buildings =new Group();
    private  Rectangle LAND;
    private final Group trees=new Group();
    private final Group bunkers=new Group();
    private final Group nukeBombSymbol=new Group();
    private final Group clusterBombSymbol=new Group();
    private final Group bombs=new Group();
    private final Group bullets=new Group();
    private final Group zsu57s=new Group();
    private final Group migs=new Group();
    private Label labelOfIronBomb;
    private Label labelOfNukeBomb;
    private Label labelOfPlanes;
    private Label labelOfKills;
    private Label labelOfWave;
    private Label labelOfCluster;
    private Pane pane;
    private ScoreIcon scoreIcon;
    private BombIcon bombIcon;
    private NukeIcon nukeIcon;
    private int shoot;
    private int successfulShoot;
    private Group warnings=new Group();
    private Group accuracies=new Group();
    public double getWIDTH() {
        return WIDTH;
    }

    public double getHEIGHT() {
        return HEIGHT;
    }

    public Group getTrucks() {
        return trucks;
    }
    public Group getTanks(){
        return tanks;
    }

    public Group getBunkers() {
        return bunkers;
    }

    public Group getBuildings() {
        return buildings;
    }

    public Group getTrees() {
        return trees;
    }

    public Group getClusterBombSymbol() {
        return clusterBombSymbol;
    }


    public Group getNukeBombSymbol() {
        return nukeBombSymbol;
    }

    public ArrayList<Transition> getAnimations() {
        return animations;
    }

    public Group getBullets() {
        return bullets;
    }

    public Group getWarnings() {
        return warnings;
    }

    public Game(String username, GameAtomicBomber gameAtomicBomber, Pane pane) {
        this.pane=pane;
        shoot=0;
        successfulShoot=0;
    }

    public Rectangle getLand() {
        return LAND;
    }


    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Rectangle getLAND() {
        return LAND;
    }

    public void setLAND(Rectangle LAND) {
        this.LAND = LAND;
    }

    public Group getZsu57s() {
        return zsu57s;
    }

    public Group getBombs() {
        return bombs;
    }

    public Pane getPane() {
        return pane;
    }


    public Group getMigs() {
        return migs;
    }

    public ScoreIcon getScoreIcon() {
        return scoreIcon;
    }

    public void setScoreIcon(ScoreIcon scoreIcon) {
        this.scoreIcon = scoreIcon;
    }

    public Label getLabelOfIronBomb() {
        return labelOfIronBomb;
    }

    public Label getLabelOfNukeBomb() {
        return labelOfNukeBomb;
    }

    public void setLabelOfIronBomb(Label labelOfIronBomb) {
        this.labelOfIronBomb = labelOfIronBomb;
    }

    public void setLabelOfNukeBomb(Label labelOfNukeBomb) {
        this.labelOfNukeBomb = labelOfNukeBomb;
    }
    public void updateLabelOfIronBomb(){
        labelOfIronBomb.setText(User.getLoggedInUser().getNumberOfIronBomb()+"");
    }
    public void updateLabelOfNukeBomb(){
        labelOfNukeBomb.setText(User.getLoggedInUser().getNumberOfNukeBomb()+"");
    }

    public Label getLabelOfPlanes() {
        return labelOfPlanes;
    }

    public void setLabelOfPlanes(Label labelOfPlanes) {
        this.labelOfPlanes = labelOfPlanes;
    }

    public void updateLabelOfPlanes(){
        labelOfPlanes.setText(User.getLoggedInUser().getNumberOfPlanes()+"");
    }

    public BombIcon getBombIcon() {
        return bombIcon;
    }

    public void setBombIcon(BombIcon bombIcon) {
        this.bombIcon = bombIcon;
    }

    public NukeIcon getNukeIcon() {
        return nukeIcon;
    }

    public void setNukeIcon(NukeIcon nukeIcon) {
        this.nukeIcon = nukeIcon;
    }

    public Label getLabelOfKills() {
        return labelOfKills;
    }

    public void setLabelOfKills(Label labelOfKills) {
        this.labelOfKills = labelOfKills;
    }
    public void updateLabelOfKills (){
        labelOfKills.setText(User.getLoggedInUser().getScore()+"");
    }

    public Label getLabelOfWave() {
        return labelOfWave;
    }

    public void setLabelOfWave(Label labelOfWave) {
        this.labelOfWave = labelOfWave;
    }
    public void updateLabelOfWave(){
        labelOfWave.setText("Wave:"+ GameAtomicBomber.getCurrentGameAtomicBomber().getWave());
    }

    public int getShoot() {
        return shoot;
    }

    public int getSuccessfulShoot() {
        return successfulShoot;
    }

    public void setSuccessfulShoot(int successfulShoot) {
        this.successfulShoot = successfulShoot;
    }

    public void setShoot(int shoot) {
        this.shoot = shoot;
    }

    public Group getAccuracies() {
        return accuracies;
    }

    public Label getLabelOfCluster() {
        return labelOfCluster;
    }

    public void setLabelOfCluster(Label labelOfCluster) {
        this.labelOfCluster = labelOfCluster;
    }

    public void updateLabelOfCluster(){
        labelOfCluster.setText("Cluster:"+User.getLoggedInUser().getNumberOfClusterBomb());
    }
}
