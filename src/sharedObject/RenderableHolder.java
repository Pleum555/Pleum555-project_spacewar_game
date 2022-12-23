package sharedObject;

import java.util.ArrayList;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class RenderableHolder {
	private static RenderableHolder instance = new RenderableHolder();
	private static List<IRenderable> entities;
	public static Image plane;
	public static Image meteorite;
	public static Image shield;
	public static Image shield_item;
	public static Image point_item ;
	public static Image background ;
	public static Image home ;
	public static Image laser ;
	public static AudioClip  explosionSound;
	public static AudioClip  bgSound;

	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static void loadResource() {
		plane = new Image(ClassLoader.getSystemResource("plane.png").toString());
		meteorite = new Image(ClassLoader.getSystemResource("pngegg.png").toString());
		shield = new Image(ClassLoader.getSystemResource("spr_shield.png").toString());
		shield_item = new Image(ClassLoader.getSystemResource("shield-icon.png").toString());
		point_item = new Image(ClassLoader.getSystemResource("point.png").toString());
		background = new Image(ClassLoader.getSystemResource("Background.jpg").toString());
		laser = new Image(ClassLoader.getSystemResource("laser.png").toString());
		home = new Image(ClassLoader.getSystemResource("Home.png").toString());
		explosionSound = new AudioClip(ClassLoader.getSystemResource("Explosion.wav").toString());
		bgSound = new AudioClip(ClassLoader.getSystemResource("bg sound.mp3").toString());
	}

	public void add(IRenderable entity) {
		entities.add(entity);
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}
	
	public List<IRenderable> getEntities() {
		return entities;
	}
}
