package Object;

import Main.GamePanel;
import Entity.Entity;

public class Stairs extends Entity {
    GamePanel gamePanel;
    public Stairs(GamePanel gamePanel) {
        super(gamePanel);
        name = "Stairs";
        down1 = setup("/Objects/scari");
        description = "[" + name + "]\nStairs to the next level";
        collision = true;
    }

    public void interact() {
        if ((gamePanel.currentLevel == gamePanel.level2 || gamePanel.currentLevel == gamePanel.level3) &&
                gamePanel.player.worldX == this.worldX && gamePanel.player.worldY == this.worldY) {
            gamePanel.eventHandler.teleport(gamePanel.playState, 11, 15);  // Coordonatele de destinație
        } else {
            gamePanel.ui.showMessage("Nothing happens.");
        }
    }
}
