package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import Object.Carrot;
import Object.Key;

import Object.Table;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity {
    KeyHandler keyHandler;

    private boolean nearDoor = false;
    private int doorIndex = -1;
    public final int screenX;
    public final int screenY;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);

        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
        setItems();
    }


    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 20;
        worldY = gamePanel.tileSize * 21;
        speed = 2;
        direction = "right";

        maxLife = 6;
        life = maxLife;
        level = 1;
        keys = 0;
        carrot = 0;
        carrotCount = 0;
    }

    public void setItems() {
        inventory.add(new Key(gamePanel));
        inventory.add(new Carrot(gamePanel));
    }

    public void getPlayerImage() {
        try {
            right1 = ImageIO.read(getClass().getResourceAsStream("/Player/dreapta1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Player/dreapta2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/Player/dreapta3.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/Player/stanga1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Player/stanga2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/Player/stanga3.png"));

            up1 = ImageIO.read(getClass().getResourceAsStream("/Player/sus1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Player/sus2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/Player/sus3.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/Player/jos1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Player/jos2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/Player/jos3.png"));

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void update() {
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if (keyHandler.upPressed) {
                direction = "up";
            }
            if (keyHandler.downPressed) {
                direction = "down";
            }
            if (keyHandler.leftPressed) {
                direction = "left";
            }
            if (keyHandler.rightPressed) {
                direction = "right";
            }

            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);

            int objectIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            nearDoor = false;
            doorIndex = -1;

            int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.NPC);
            interactNPC(npcIndex);

            //telep
            gamePanel.eventHandler.checkEvent();
            gamePanel.keyHandler.enterPressed = false;

            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
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

        if (keyHandler.enterPressed && nearDoor && doorIndex != -1) {
            interactWithDoor(doorIndex);
        }
    }
    public void interactWithDoor(int index) {
        if (gamePanel.objects[index].name.equals("Usa1")) {
            if (gamePanel.currentLevel == gamePanel.level2) {
                gamePanel.objects[index] = null;
                gamePanel.ui.showMessage("You opened the door!");
                gamePanel.eventHandler.teleport(gamePanel.playState, 75, 4); // Coordonează către care vrei să teleportezi jucătorul
            } else if (hasKey >= 0) {
                gamePanel.playSE(2);
                gamePanel.objects[index] = null;
                --hasKey;
                gamePanel.ui.showMessage("You opened the door!");
                gamePanel.ui.gameFinished = true;
                gamePanel.stopMusic();
                gamePanel.ui.loadLevel2Image(); // Încărcăm imaginea nivelului 2
                gamePanel.gameState = gamePanel.tileState; // Setăm starea jocului pentru tranziție
            }
        } else if (gamePanel.objects[index].name.equals("Usa2") && hasKey == 1) {
            gamePanel.objects[index] = null;
            --hasKey;
            gamePanel.ui.showMessage("You opened the door!");
        }
    }


    public void interactNPC(int index) {
        if (index != 999) {
            gamePanel.gameState = gamePanel.dialogueState;
            gamePanel.NPC[index].speak();
        }
    }

    public void pickUpObject(int index) {
        if (index != 999) {
            String objectName = gamePanel.objects[index].name;

            switch (objectName) {
                case "Key":
                    ++hasKey;
                    System.out.println("Chei: " + hasKey);
                    gamePanel.objects[index] = null;
                    gamePanel.ui.showMessage("You found a key!");
                    break;
                case "Usa1":
                    if (hasKey > 1) {
                        nearDoor = true;
                        doorIndex = index;
                        gamePanel.ui.showMessage("Press Enter to open the door.");
                    } else {
                        gamePanel.ui.showMessage("The door is locked, you need a key!");
                    }
                    break;
                case "Usa2":
                    if (hasKey == 1) {
                        nearDoor = true;
                        doorIndex = index;
                        gamePanel.ui.showMessage("Press Enter to open the door.");
                    }
                    break;
                case "Carrot":
                    ++hasCarrot;
                    System.out.println("Morcovi: " + hasCarrot);
                    Carrot carrot = (Carrot) gamePanel.objects[index];
                    if (carrot.isReadyForHarvest()) {
                        carrot.harvest();
                        gamePanel.ui.showMessage("You harvested a carrot!");
                        gamePanel.objects[index] = null;
                    } else {
                        gamePanel.ui.showMessage("The carrot is not ready to be harvested yet.");
                    }
                    break;
                case "Table":
                    Table table = (Table) gamePanel.objects[index];
                    table.interact();
                    break;
            }
        }
    }


    public int getCarrotCount() {
        return carrotCount;
    }

    public void setCarrotCount(int carrotCount) {
        this.carrotCount = carrotCount;
    }

    public boolean hasCollectedAllKeys() {
        return hasKey >= 2; // Sau orice număr de chei este necesar pentru a termina nivelul 1
    }

    public void resetPosition() {
        worldX = gamePanel.tileSize * 20;
        worldY = gamePanel.tileSize * 21;
        direction = "right";
    }
}
