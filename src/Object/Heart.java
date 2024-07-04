package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import Entity.Entity;

public class Heart extends Entity {

    GamePanel gamePanel;
    public Heart(GamePanel gamePanel) {
        super(gamePanel);
        name = "Heart";
        image = setup("/Objects/zero");
        image2 = setup("/Objects/half");
        image3 = setup("/Objects/full");
    }
}
