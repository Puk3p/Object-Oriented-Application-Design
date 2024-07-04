package Main;

import Object.Usa1;
import java.awt.*;
import Object.Stairs;

public class EventHandler {

    GamePanel gamePanel;
    EventRect eventRectangle[][];

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        eventRectangle = new EventRect[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        int col = 0;
        int row = 0;
        while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {

            eventRectangle[col][row] = new EventRect();
            eventRectangle[col][row].x = 23;
            eventRectangle[col][row].y = 23;
            eventRectangle[col][row].width = 2;
            eventRectangle[col][row].height = 2;
            eventRectangle[col][row].eventRectangleDefaultX = eventRectangle[col][row].x;
            eventRectangle[col][row].eventRectangleDefaultY = eventRectangle[col][row].y;

            ++col;
            if (col == gamePanel.maxWorldCol) {
                col = 0;
                ++row;
            }
        }
    }

    public void checkEvent() {
        for (int i = 0; i < gamePanel.objects.length; i++) {
            if (gamePanel.objects[i] != null && gamePanel.objects[i] instanceof Usa1) {
                Usa1 usa = (Usa1) gamePanel.objects[i];
                if (hit(usa.worldX / gamePanel.tileSize, usa.worldY / gamePanel.tileSize, "any")) {
                    usa.interact();
                }
            }
            if (gamePanel.objects[i] != null && gamePanel.objects[i] instanceof Stairs) {
                Stairs stairs = (Stairs) gamePanel.objects[i];
                if (hit(stairs.worldX / gamePanel.tileSize, stairs.worldY / gamePanel.tileSize, "any")) {
                    stairs.interact();
                }
            }
        }
        if (gamePanel.currentLevel == gamePanel.level2 || gamePanel.currentLevel == gamePanel.level3) {
            checkTeleport();
            checkSpecialTeleport();
        }
    }
    public void checkSpecialTeleport() {
        int targetX = 79 * gamePanel.tileSize;
        int targetY = 9 * gamePanel.tileSize;

        if (gamePanel.player.worldX == targetX && gamePanel.player.worldY == targetY) {
            gamePanel.ui.showMessage("You have been teleported to a special location!");
            gamePanel.playSE(2);
            teleport(gamePanel.playState, 11, 15);  // Coordonatele de destinație
        }
    }

    public void checkTeleport() {
        int targetX = 10 * gamePanel.tileSize;
        int targetY = 15 * gamePanel.tileSize;

        if (gamePanel.player.worldX == targetX && gamePanel.player.worldY == targetY) {
            gamePanel.ui.showMessage("You have been teleported!");
            gamePanel.playSE(2);
            teleport(gamePanel.playState, 11, 15);  // Coordonatele de destinație
        }
    }


    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;

        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;
        eventRectangle[eventCol][eventRow].x = eventCol * gamePanel.tileSize + eventRectangle[eventCol][eventRow].x;
        eventRectangle[eventCol][eventRow].y = eventRow * gamePanel.tileSize + eventRectangle[eventCol][eventRow].y;

        if (gamePanel.player.solidArea.intersects(eventRectangle[eventCol][eventRow])) {
            if (gamePanel.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }

        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
        eventRectangle[eventCol][eventRow].x = eventRectangle[eventCol][eventRow].eventRectangleDefaultX;
        eventRectangle[eventCol][eventRow].y = eventRectangle[eventCol][eventRow].eventRectangleDefaultY;

        return hit;
    }

    public void teleport(int gameState, int destX, int destY) {
        gamePanel.gameState = gameState;
        gamePanel.ui.currentDialogue = "Teleport";
        gamePanel.player.worldX = destX * gamePanel.tileSize;
        gamePanel.player.worldY = destY * gamePanel.tileSize;
    }
}
