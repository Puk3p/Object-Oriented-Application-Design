package Object;

import Main.GamePanel;
import Entity.Entity;

public class Key extends Entity {
    public Key(GamePanel gamePanel) {
        super(gamePanel);
        name = "Key";
        down1 = setup("/Objects/key");
        description = "[" + name + "]\nA key to unlock doors";
        collision = true;
    }
}
