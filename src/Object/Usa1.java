package Object;
import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Usa1 extends Entity {
    GamePanel gamePanel;
    public Usa1(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        name = "Usa1";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/usa1.png"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        collision = false;
    }

    public void interact() {
        if (gamePanel.currentLevel == gamePanel.level2) {
            gamePanel.eventHandler.teleport(gamePanel.playState, 75, 4);  // Coordonează către care vrei să teleportezi jucătorul
        } else {
            gamePanel.ui.showMessage("Nothing happens.");
        }
    }
}
