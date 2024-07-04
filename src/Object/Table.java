package Object;

import Entity.Entity;
import Main.GamePanel;

public class Table extends Entity {
    GamePanel gamePanel;
    public boolean isCompleted = false;
    public static int tableFinished = 0;
    public Table(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        name = "Table";
        down1 = setup("/Objects/masa");
        down2 = setup("/Objects/masamorcov");
        description = "[" + name + "]\nA table";
        collision = true;
    }

    public void interact() {
        if (gamePanel.player.getHasCarrot() >= 20 && !isCompleted) {
            down1 = down2; // Schimbă imaginea mesei la cea cu plăcinta de morcovi
            description = "[" + name + "]\nA table with a carrot pie";
            gamePanel.player.setCarrotCount(gamePanel.player.getCarrotCount() - 20); // Scade 20 de morcovi din inventar
            gamePanel.updateTableCompletion(this); // Actualizează starea meselor
            isCompleted = true;
            ++tableFinished;

            if (tableFinished >= 8) {
                gamePanel.showLevelCompletedMessage(); // Afișează mesajul când 8 mese sunt completate
            }
        }
    }



}
