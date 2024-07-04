package Object;

import Main.GamePanel;
import Entity.Entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics2D;

public class Carrot extends Entity {
    private BufferedImage[] stages;
    private BufferedImage harvestedImage;
    private int currentStage = 0;
    private long lastUpdateTime;
    private static final long[] COOLDOWN_TIMES = {10000, 15000, 20000}; // Timpii de așteptare pentru fiecare tranziție
    private boolean isHarvested = false;
    GamePanel gamePanel;
    public static final String objectName = "Carrot";


    public Carrot(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.name = objectName;
        description = "[" + name + "]\nAn ordinary carrot";
        try {
            stages = new BufferedImage[5];
            stages[0] = setup("/Objects/faza_morcov0");
            stages[1] = setup("/Objects/faza_morcov1");
            stages[2] = setup("/Objects/faza_morcov3");
            stages[3] = setup("/Objects/faza_morcov5");
            harvestedImage = setup("/Objects/faza_morcov7");
            image = stages[0];  // Set initial image
            stages[4] = setup("/Objects/Morcov"); //dupa recoltare
            image2 = stages[4];
            //System.out.println("Carrot created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("Error initializing Carrot.");
        }
        lastUpdateTime = System.currentTimeMillis();
        collision = false;
    }



    public boolean isReadyForHarvest() {
        return currentStage == 3;  // Verifică dacă morcovul este la ultimul stadiu
    }

    public void update() {
        if (!isHarvested && currentStage < COOLDOWN_TIMES.length && System.currentTimeMillis() - lastUpdateTime > COOLDOWN_TIMES[currentStage]) {
            if (currentStage < stages.length - 1) {
                currentStage++;
                image = stages[currentStage];
                lastUpdateTime = System.currentTimeMillis();  // Resetare timp
            }
        }
    }
    public void draw(Graphics2D g2) {
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }


    public void harvest() {
        if (isReadyForHarvest() && !isHarvested) {
            isHarvested = true;
            image = harvestedImage;
        }
    }
}
