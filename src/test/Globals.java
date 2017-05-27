package test;

import gameEngine.Sprite;

/**
 * Created by roscale on 4/20/17.
 */
abstract class Globals
{
	public static String spritesPath = "sprites/";

	public static Sprite zombieSprite;
	public static Sprite zombieAttackSprite;
	public static Sprite peashooterSprite;

	public static void loadSprites()
	{
		zombieSprite = new Sprite(spritesPath + "ZombieRegular/walk/", "png");
		zombieAttackSprite = new Sprite(spritesPath + "ZombieRegular/attack/", "png");
		peashooterSprite = new Sprite(spritesPath + "PeashooterRegular/rest/", "png");
	}
}
