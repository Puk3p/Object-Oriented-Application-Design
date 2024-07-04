package Entity;

import Main.GamePanel;

public class NPC_Trader extends Entity {

    public NPC_Trader(GamePanel gamePanel) {
        super(gamePanel);

        direction = "down";
        speed = 0;

        getPlayerImage();
        setDialogue();

    }

    @Override
    public void speak() {
        // Verifică dacă jucătorul are suficienți morcovi pentru tranzacție
        if (gamePanel.player.getHasCarrot() >= 10) {
            gamePanel.player.setHasCarrot(gamePanel.player.getHasCarrot() - 10); // Scade 20 morcovi
            gamePanel.player.setHasKey(gamePanel.player.getHasKey() + 1); // Adaugă o cheie
            gamePanel.ui.showMessage("Trade successful! Here's your key.");
            super.speak();
        } else {
            gamePanel.ui.showMessage("You need at least 20 carrots to trade for a key.");
        }
        super.speak();
    }

    public void getPlayerImage() {
        up1 = setup("/Npcs/npc1");
        up2 = setup("/Npcs/npc2");
        up3 = setup("/Npcs/npc1");
        down1 = setup("/Npcs/npc2");
        down2 = setup("/Npcs/npc1");
        down3 = setup("/Npcs/npc2");
        left1 = setup("/Npcs/npc1");
        left2 = setup("/Npcs/npc2");
        left3 = setup("/Npcs/npc1");
        right1 = setup("/Npcs/npc2");
        right2 = setup("/Npcs/npc1");
        right3 = setup("/Npcs/npc2");
    }

    public void setDialogue() {
        dialogues[0] = "Hello!";
        dialogues[1] = "Hello! Do you want to trade 20 carrots for a key?";
        dialogues[2] = "Goodbye!";
    }


}
