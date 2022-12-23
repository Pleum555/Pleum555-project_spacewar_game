package logic;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import Entity.Laser;
import Entity.Meteorite;
import Entity.Player;
import Entity.Point;
import Entity.Shield;
import EntityBase.Entity;
import input.InputUtility;
import sharedObject.RenderableHolder;

public class GameLogic {
	private Player player;
	private static ArrayList<Entity> entityformove;
	private static ArrayList<Entity> entityforadd;
	private static Laser laser;
	private static boolean lose;
	private static boolean shield ;
	private static int score;
	private static int count;
	
	
	public GameLogic(){
		player = new Player(320,240);
		entityformove = new ArrayList<Entity>() ;
		entityforadd = new ArrayList<Entity>() ;
		count = 0 ;
		
		Shield shield_item = new Shield();
		Meteorite meteorite = new Meteorite();
		laser = new Laser(player);
		
		addNewObject(laser);
		addNewObject(player);
		addNewObject(meteorite);
		addNewObject(shield_item);
		score = 0 ;
		lose = false ;
		shield = false ;
	}
	
	private void addNewObject(Entity entity){
		RenderableHolder.getInstance().add(entity);
		entityforadd.add(entity) ;
		count += 1 ;
	}
	
	public void WaveUpdate() {
		Thread updateMeteorite = new Thread(new Runnable() {

			@Override
			public void run() {

				// TODO Auto-generated method stub
				try {
					Thread.sleep(2000) ;
					while (!isLose()) {
						Meteorite m = new Meteorite();
						addNewObject(m) ;
						if(count*30 <= 1500) {
							Thread.sleep(2000-count*30);
						} else {
							count = 30 ;
							Thread.sleep(500);
						}
					}
				} catch (InterruptedException e) {
				}
			}
		});
		
		Thread updateShield = new Thread(new Runnable() {

			@Override
			public void run() {

				// TODO Auto-generated method stub
				try {
					Thread.sleep(30000);
					while (!isLose()) {
						Shield s = new Shield();
						addNewObject(s) ;
						Thread.sleep(30000);
					}
				} catch (InterruptedException e) {
				}
			}
		});
		Thread updatePoint = new Thread(new Runnable() {

			@Override
			public void run() {

				// TODO Auto-generated method stub
				try {
					Thread.sleep(20000);
					while (!isLose()) {
						Point p = new Point();
						addNewObject(p) ;
						Thread.sleep(20000);
					}
				} catch (InterruptedException e) {
				}
			}
		});
		Thread updateMove = new Thread(new Runnable() {

			@Override
			public void run() {

				// TODO Auto-generated method stub
				try {
					while (!isLose()) {
						entityformove = entityforadd ;
						Thread.sleep(5);
						try {
							for (Entity e : entityformove) {
								if(e instanceof Meteorite) {
									if(count*30 > 1000)((Meteorite) e).movement(1.5) ;
									else ((Meteorite) e).movement(1) ;
								}
								else if(e instanceof Point) ((Point) e).movement() ;
								else if(e instanceof Shield) ((Shield) e).movement() ;
							}
						} catch(ConcurrentModificationException e) {}
						if(laser.isVisible()) {
							laser.movement() ;
						}
					}
				} catch (InterruptedException e) {
				}
			}
		});
		updateShield.start();
		updateMeteorite.start();
		updatePoint.start();
		updateMove.start();
	}
	
	public void scoreUpdate() {
		Thread scorecount = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					while (!isLose()) {
						score++;
						Thread.sleep(200);
					}
				} catch (InterruptedException e) {
				}
			}
		});
		scorecount.start();
	}
	


	public void logicUpdate(){
		player.update();
		laser.update(player);
		ArrayList<Entity> remove = new ArrayList<Entity>() ;
		boolean add = false ;
		try {
			for (Entity e : entityformove) {
				if(e.getX() <-10 ) {
					remove.add(e) ;
					e.setDestroyed(true);
				}
				if(!laser.isDestroyed() && !e.isDestroyed() && laser.collideWith(e) && e instanceof Meteorite) laser.meteor_hit((Meteorite) e); 
				if(!e.isDestroyed() && player.collideWith(e)){
					if(e instanceof Meteorite) {
						if(isShield()) {
							((Meteorite) e).damage(player);
							setSheid(false);
						}
						else {
							((Meteorite) e).damage(player);
							setLose(true);
							InputUtility.resetInput();
						}
					} else if(e instanceof Shield) {
						if(isShield() && laser.isVisible()) {
							add = true ;
						}
						((Shield) e).powerup(player);
					} else if(e instanceof Point) {
						((Point) e).powerup(player);
					}
				}
			}
		} catch(ConcurrentModificationException e) {}
		entityforadd.removeAll(remove) ;
		if(add) {
		laser = new Laser(player) ;
		addNewObject(laser);
		}
	}

	public boolean isLose() {
		return lose;
	}

	public static void setLose(boolean lose) {
		GameLogic.lose = lose;
	}

	public static int getScore() {
		return score;
	}

	public static void addScore(int s) {
		GameLogic.score += s;
	}

	public static boolean isShield() {
		return shield;
	}

	public static void setSheid(boolean shield) {
		GameLogic.shield = shield;
	}
	
	
}
