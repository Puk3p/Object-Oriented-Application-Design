package Entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Entity {
    GamePanel gamePanel;
    public int worldX, worldY;
    public int speed;

    public BufferedImage right1, right2, right3, left1, left2, left3, up1, up2, up3, down1, down2, down3;
    public String direction = "down";

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    String dialogues[] = new String[20];
    int dialogueIndex = 0;

    public BufferedImage image, image2, image3, image4, image5;
    public String name;
    public boolean collision = false;


    public ArrayList<Entity> inventory = new ArrayList<>();
    public ArrayList<BufferedImage> imageInventory = new ArrayList<>();
    public final int maxInventorySize = 20;

    public int solidAreaDefaultX, solidAreaDefaultY;

    public int maxLife;
    public int life;

    int hasKey = 0;
    int hasCarrot = 0;
    int level = 1;
    int coin = 0;
    int carrot = 0;
    int keys = 0;
    int carrotCount = 0;
    int tableFinished = 0;

    public String description = "";

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public int getHasCarrot() {
        return hasCarrot;
    }

    public void setHasCarrot(int hasCarrot) {
        this.hasCarrot = hasCarrot;
    }

    public int getHasKey() {
        return hasKey;
    }

    public void setHasKey(int hasKey) {
        this.hasKey = hasKey;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public void setAction() {

    }

    public void update() {
        setAction();
        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);
        gamePanel.collisionChecker.checkObject(this, false);
        gamePanel.collisionChecker.checkPlayer(this);

        if (!collisionOn) {
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }

        ++spriteCounter;
        if (spriteCounter > 20) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public BufferedImage setup(String imageName) {

        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
            image = utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return image;
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage image = null;
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if (worldX + gamePanel.tileSize * 2 > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize * 2 < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize * 2 > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize * 2 < gamePanel.player.worldY + gamePanel.player.screenY) {

            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    if (spriteNum == 3) {
                        image = up3;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    if (spriteNum == 3) {
                        image = down3;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    if (spriteNum == 3) {
                        image = left3;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    if (spriteNum == 3) {
                        image = right3;
                    }
                    break;
            }

            graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }

        gamePanel.ui.currentDialogue = dialogues[dialogueIndex];
        ++dialogueIndex;

        switch (gamePanel.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

}
