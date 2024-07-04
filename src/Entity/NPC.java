package Entity;

import Main.GamePanel;
import java.util.Random;

public class NPC extends Entity {

    public NPC(GamePanel gamePanel) {
        super(gamePanel);

        direction = "down";
        speed = 1;

        getPlayerImage();
        setDialogue();

    }

    public void getPlayerImage() {

        down1 = setup("/Npcs/jos1");
        down2 = setup("/Npcs/jos2");
        down3 = setup("/Npcs/jos3");
        left1 = setup("/Npcs/stanga1");
        left2 = setup("/Npcs/stanga2");
        left3 = setup("/Npcs/stanga3");
        right1 = setup("/Npcs/dreapta1");
        right2 = setup("/Npcs/dreapta2");
        right3 = setup("/Npcs/dreapta3");
        up1 = setup("/Npcs/sus1");
        up2 = setup("/Npcs/sus2");
        up3 = setup("/Npcs/sus3");
    }

    public void setAction() {
        ++actionLockCounter;

        if (actionLockCounter == 110) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "left";
            } else if (i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

    public void setDialogue() {
        dialogues[0] = "Hello!";
        dialogues[1] = "How are you? \nAnimal Crossing is a great game!";
        dialogues[2] = "I'm fine, thank you! \nI'm just walking around.";
        dialogues[3] = "Goodbye!";
    }

    public void speak() {
        super.speak();
    }
}
