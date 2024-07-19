package controller;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.*;
import model.ZSU57;
import view.GameAtomicBomber;
import view.animations.*;

public class GameController {
    public static void moveRight(Plane plane){

        if((plane.getRotate()%360>0 && plane.getRotate()%360<=180) || (plane.getRotate()%360<-180 && plane.getRotate()%360>-360))
            plane.setRotate(plane.getRotate()-5);
        else if((plane.getRotate()%360<0 && plane.getRotate()%360>=-180) || (plane.getRotate()%360>=180 && plane.getRotate()%360<=360))
            plane.setRotate(plane.getRotate()+5);
    }
    public static void moveLeft(Plane plane){
        if((plane.getRotate()%360>=0 && plane.getRotate()%360<180) || (plane.getRotate()%360<-180 && plane.getRotate()%360>-360))
            plane.setRotate(plane.getRotate()+5);
        else if((plane.getRotate()%360<0 && plane.getRotate()%360>-180) || (plane.getRotate()%360>180 && plane.getRotate()%360<360))
            plane.setRotate(plane.getRotate()-5);
    }
    public static void moveDown(Plane plane){
        if(plane.getRotate()%360<90 && plane.getRotate()%360>=-90) plane.setRotate(plane.getRotate()+5);
        else if((plane.getRotate()%360>90 && plane.getRotate()%360<=270) || (plane.getRotate()%360<=-90 && plane.getRotate()%360>-270))
            plane.setRotate(plane.getRotate()-5);
    }
    public static void moveUp(Plane plane){
        if((plane.getRotate()%360<90 && plane.getRotate()%360>=-90) || (plane.getRotate()%360>=270 && plane.getRotate()%360<450))
            plane.setRotate(plane.getRotate()-5);
        else if((plane.getRotate()%360>90 && plane.getRotate()%360<=270) || (plane.getRotate()%360<-90 && plane.getRotate()%360>=-270))
            plane.setRotate(plane.getRotate()+5);
    }
    public static Plane shootPlane(Pane pane,Game game){
        Plane plane=new Plane(game);
        pane.getChildren().add(plane);
        PlaneShooting planeShooting=new PlaneShooting(game,pane,plane);
        plane.setPlaneShooting(planeShooting);
        game.getAnimations().add(planeShooting);
        planeShooting.play();
        return plane;
    }
    public static Truck shootTruck(Pane pane,Game game){
        Truck truck=new Truck(game);
        pane.getChildren().add(truck);
        TruckShooting truckShooting = new TruckShooting(game,pane,truck);
        game.getAnimations().add(truckShooting);
        game.getTrucks().getChildren().add(truck);
        truckShooting.play();
        truck.setTruckShooting(truckShooting);
        return truck;
    }
    public static Tank shootTank(Pane pane,Game game,double x){
        Tank tank=new Tank(game,x);
        pane.getChildren().add(tank);
        TankShooting tankShooting = new TankShooting(game,pane,tank);
        game.getAnimations().add(tankShooting);
        game.getTanks().getChildren().add(tank);
        tankShooting.play();
        tank.setTankShooting(tankShooting);
        return tank;
    }
    public static Building createBuilding(Pane pane,Game game){
        Building building=new Building(game);
        game.getBuildings().getChildren().add(building);
        return building;
    }
    public static void createIcones(Pane pane, Game game){
        ScoreIcon scoreIcon=new ScoreIcon(game);
        BombIcon bombIcon=new BombIcon(game);
        NukeIcon nukeIcon=new NukeIcon(game);
        Label labelOfIronBomb=new Label(User.getLoggedInUser().getNumberOfIronBomb()+"");
        game.setLabelOfIronBomb(labelOfIronBomb);
        Label labelOfWave=new Label("Wave:"+ GameAtomicBomber.getCurrentGameAtomicBomber().getWave());
        game.setLabelOfWave(labelOfWave);
        Label labelOfCluster=new Label("Cluster:"+User.getLoggedInUser().getNumberOfClusterBomb());
        game.setLabelOfCluster(labelOfCluster);
        Label labelOfNukeBomb=new Label(User.getLoggedInUser().getNumberOfNukeBomb()+"");
        game.setLabelOfNukeBomb(labelOfNukeBomb);
        Label labelOfPlanes=new Label(User.getLoggedInUser().getNumberOfPlanes()+"");
        game.setLabelOfPlanes(labelOfPlanes);
        Label labelOfKills=new Label(User.getLoggedInUser().getScore()+"");
        game.setLabelOfKills(labelOfKills);
        labelOfNukeBomb.setLayoutX(97);
        labelOfNukeBomb.setLayoutY(17);
        labelOfPlanes.setLayoutY(25);
        labelOfPlanes.setLayoutX(145);
        labelOfCluster.setLayoutX(165);
        labelOfCluster.setLayoutY(20);
        labelOfCluster.setStyle("-fx-font-weight: bold;");
        labelOfKills.setLayoutY(10);
        labelOfKills.setLayoutX(145);
        labelOfIronBomb.setLayoutX(43);
        labelOfIronBomb.setLayoutY(17);
        labelOfWave.setLayoutX(game.getWIDTH()/2);
        labelOfWave.setLayoutY(10);
        labelOfPlanes.setTextFill(Color.BLACK);
        labelOfIronBomb.setTextFill(Color.BLACK);
        labelOfNukeBomb.setTextFill(Color.BLACK);
        labelOfKills.setTextFill(Color.BLACK);
        labelOfWave.setTextFill(Color.WHITE);
    }
    public static void shootIronBomb(Pane pane,Plane plane,Game game){
        IronBomb ironBomb=new IronBomb(game,plane);
        int planeIndex=pane.getChildren().indexOf(plane);
        pane.getChildren().add(planeIndex,ironBomb);
        IronBombShooting ironBombShooting =new IronBombShooting(game,pane,ironBomb,plane);
        game.getAnimations().add(ironBombShooting);
        ironBombShooting.play();
        ironBomb.setIronBombShooting(ironBombShooting);
        User.getLoggedInUser().setNumberOfIronBomb(User.getLoggedInUser().getNumberOfIronBomb()-1);
    }
    public static void shootNukeBomb(Pane pane,Plane plane,Game game){
        NukeBomb nukeBomb=new NukeBomb(game,plane);
        int planeIndex=pane.getChildren().indexOf(plane);
        pane.getChildren().add(planeIndex,nukeBomb);
        NukeBombShooting nukeBombShooting =new NukeBombShooting(game,pane,nukeBomb,plane);
        game.getAnimations().add(nukeBombShooting);
        nukeBombShooting.play();
        nukeBomb.setNukeBombShooting(nukeBombShooting);
        User.getLoggedInUser().setNumberOfNukeBomb(User.getLoggedInUser().getNumberOfNukeBomb()-1);
        game.updateLabelOfNukeBomb();
    }
    public static Tree createTree(Pane pane,Game game){
        Tree tree=new Tree(game);
        pane.getChildren().add(tree);
        game.getTrees().getChildren().add(tree);
        return tree;
    }
    public static Bunker createBunker(Pane pane,Game game){
        Bunker bunker=new Bunker(game);
        pane.getChildren().add(bunker);
        game.getBunkers().getChildren().add(bunker);
        return bunker;
    }
    public static NukeBombSymbol createNukeBombSymbol(Pane pane,Game game,Building building){
        NukeBombSymbol nukeBombSymbol=new NukeBombSymbol(game,building);
        pane.getChildren().add(nukeBombSymbol);
        NukeBombSymbolShooting nukeBombSymbolShooting=new NukeBombSymbolShooting(game,pane,nukeBombSymbol);
        game.getAnimations().add(nukeBombSymbolShooting);
        nukeBombSymbolShooting.play();
        nukeBombSymbol.setNukeBombSymbolShooting(nukeBombSymbolShooting);
        return nukeBombSymbol;
    }

    public static ClusterBombSymbol createClusterBombSymbol(Pane pane, Game game, Bunker bunker) {
        ClusterBombSymbol clusterBombSymbol =new ClusterBombSymbol(game,bunker);
        game.getClusterBombSymbol().getChildren().add(clusterBombSymbol);
        pane.getChildren().add(clusterBombSymbol);
        ClusterBombSymbolShooting clusterBombSymbolShooting=new ClusterBombSymbolShooting(game,pane,clusterBombSymbol);
        game.getAnimations().add(clusterBombSymbolShooting);
        clusterBombSymbolShooting.play();
        clusterBombSymbol.setClusterBombSymbolShooting(clusterBombSymbolShooting);
        return clusterBombSymbol;
    }
    public static ZSU57 shootZSU57(Pane pane, Game game){
        ZSU57 zsu57=new ZSU57(game);
        pane.getChildren().add(zsu57);
        ZSU57Shooting zsu57Shooting=new ZSU57Shooting(game,pane,zsu57);
        game.getAnimations().add(zsu57Shooting);
        game.getZsu57s().getChildren().add(zsu57);
        zsu57Shooting.play();
        zsu57.setZsu57Shooting(zsu57Shooting);
        return zsu57;
    }
    public static Mig shootMig(Pane pane,Game game){
        Mig mig=new Mig(game);
        pane.getChildren().add(mig);
        MigShooting migShooting=new MigShooting(game,pane,mig);
        game.getAnimations().add(migShooting);
        game.getMigs().getChildren().add(mig);
        migShooting.play();
        mig.setMigShooting(migShooting);
        return mig;
    }
    public static void shootBulletOfZSU57(Pane pane,Game game,ZSU57 zsu57){
        ZSU57Bullet bullet=new ZSU57Bullet(game,zsu57);
        pane.getChildren().add(bullet);
        ZSU57BulletShooting zsu57BulletShooting=new ZSU57BulletShooting(game,pane,bullet,game.getPlane(),zsu57);
        game.getAnimations().add(zsu57BulletShooting);
        game.getBullets().getChildren().add(bullet);
        zsu57BulletShooting.play();
        bullet.setBulletShooting(zsu57BulletShooting);
    }
    public static void shootBulletOfMig(Pane pane,Game game,Mig mig){
        MigBullet bullet=new MigBullet(game,mig);
        pane.getChildren().add(bullet);
        MigBulletShooting migBulletShooting=new MigBulletShooting(game,pane,bullet,game.getPlane(),mig);
        game.getAnimations().add(migBulletShooting);
        game.getBullets().getChildren().add(bullet);
        migBulletShooting.play();
        bullet.setBulletShooting(migBulletShooting);
    }
    public static void updateNumberOfKills(Label label,int number){
        label.setText("Kills: "+number);
    }
}
