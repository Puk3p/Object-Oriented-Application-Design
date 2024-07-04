package Object;

import javax.imageio.ImageIO;
import java.io.IOException;
import Entity.Entity;
import Main.GamePanel;

public class Usa2 extends Entity {
    GamePanel gamePanel;
    public Usa2(GamePanel gamePanel) {
        super(gamePanel);
        name = "Usa2";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/usa2.png"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        collision = true;
    }

    public void interact() {
        if (gamePanel.player.worldX == this.worldX && gamePanel.player.worldY == this.worldY) {
            gamePanel.eventHandler.teleport(gamePanel.dialogueState, 75, 4);  // Coordonează către care vrei să teleportezi jucătorul
        }
    }
}
