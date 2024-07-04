package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import Entity.Entity;
import Object.Heart;

public class UI {

    GamePanel gamePanel;
    Graphics2D graphics2D;
    Font arial_40, arial_80B, bellMTFont;
    BufferedImage heart_full, heart_half, heart_empty;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    BufferedImage dialogueBoxImage;
    public String currentDialogue = "";
    UtilityTool uTool = new UtilityTool();
    public int commandNum = 0;
    public int slotCol = 0;
    public int slotRow = 0;
    BufferedImage level2Image, level3Image;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        loadDialogueBoxImage();
        loadLevel2Image();
        loadLevel3Image();
        Entity heart = new Heart(gamePanel);
        heart_full = heart.image3;
        heart_half = heart.image2;
        heart_empty = heart.image;
    }

    private void loadDialogueBoxImage() {
        try {
            dialogueBoxImage = ImageIO.read(getClass().getResourceAsStream("/UIs/GameUI.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String message) {
        if (!message.equals(this.message)) { // Only update if the message is different
            this.message = message;
            messageOn = true;
            messageCounter = 0;  // Reset counter when a new message is shown
        }
    }


    private void drawMessage() {
        if (messageOn) {
            // Setări pentru chenar
            int boxWidth = 400;
            int boxHeight = 100;
            int x = (gamePanel.screenWidth - boxWidth) / 2;  // Centrat orizontal
            int y = gamePanel.screenHeight - boxHeight - 50;  // Poziționat la partea de jos a ecranului

            // Desenează chenarul negru curbat
            graphics2D.setColor(Color.BLACK);
            graphics2D.fillRoundRect(x, y, boxWidth, boxHeight, 25, 25);

            // Setează fontul și culoarea textului
            graphics2D.setFont(new Font("Arial", Font.BOLD, 20));
            graphics2D.setColor(Color.WHITE);

            // Calculează centrarea textului pe orizontală în chenar
            int textWidth = graphics2D.getFontMetrics().stringWidth(message);
            int textX = x + (boxWidth - textWidth) / 2;
            int textY = y + (boxHeight - graphics2D.getFontMetrics().getHeight()) / 2 + graphics2D.getFontMetrics().getAscent();

            // Desenează mesajul
            graphics2D.drawString(message, textX, textY);

            // Incrementăm counterul pentru a controla durata de afișare a mesajului
            messageCounter++;
            if (messageCounter > 180) { // Mesajul va fi afișat pentru 3 secunde (FPS 60 * 3)
                messageOn = false;
            }
        }
    }

    public void drawMainMenu() {
        // Load the main menu image
        BufferedImage menuImage = loadImage("/Titles/LoadScreen.png");
        graphics2D.drawImage(menuImage, 0, 0, gamePanel.screenWidth, gamePanel.screenHeight, null);

        // Set font
        graphics2D.setFont(new Font("Bell MT", Font.BOLD, 21));

        // Menu options
        String[] options = {"New Game", "Load Game", "Exit"};
        int ey = 600;  // y position for all options

        // Draw each menu option
        for (int i = 0; i < options.length; i++) {
            // Calculate x position for centered text of each option
            int ex = getXforCenteredText(options[i]) - 182;
            if (i > 0) {
                ex += 180 * i;  // Increase the offset for each subsequent option
            }

            // Draw actual text
            graphics2D.setColor(Color.WHITE); // Text color
            graphics2D.drawString(options[i], ex, ey);

            // Check if this is the selected command
            if (commandNum == i) {
                // Set the stroke and color for the rectangle
                graphics2D.setColor(Color.YELLOW);
                graphics2D.setStroke(new BasicStroke(3));
                FontMetrics fm = graphics2D.getFontMetrics();
                int textWidth = fm.stringWidth(options[i]);
                int textHeight = fm.getHeight();

                // Draw rounded rectangle around the text
                graphics2D.drawRoundRect(ex - 5, ey - fm.getAscent() - 5, textWidth + 10, textHeight + 10, 15, 15);
            }
        }
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void draw(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
        graphics2D.setFont(arial_40);
        graphics2D.setColor(Color.WHITE);

        if (gamePanel.gameState == gamePanel.tileState) {
            if (gameFinished) {
                drawLevel2();
            } else {
                drawMainMenu();
            }
        } else if (gamePanel.gameState == gamePanel.playState) {
            drawPlayerLife();
            drawMessage(); // Adăugăm desenarea mesajului în starea de joc activ
        } else if (gamePanel.gameState == gamePanel.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        } else if (gamePanel.gameState == gamePanel.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        } else if (gamePanel.gameState == gamePanel.characterState) {
            drawCharacterScreen();
            drawInventory();
        } else if (gameFinished && gamePanel.currentLevel == gamePanel.level3) {
            drawLevel3();
        }
    }

    public void drawInventory() {
        int frameX = gamePanel.tileSize * 9;
        int frameY = gamePanel.tileSize;
        int frameWidth = gamePanel.tileSize * 6;
        int frameHeight = gamePanel.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;

        for (int i = 0; i < gamePanel.player.inventory.size(); ++i) {
            graphics2D.drawImage(gamePanel.player.inventory.get(i).down1, slotX, slotY, null);
            slotX += gamePanel.tileSize;
            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += gamePanel.tileSize;
            }
        }

        int cursorX = slotXstart + (gamePanel.tileSize * slotCol);
        int cursorY = slotYstart + (gamePanel.tileSize * slotRow);
        int cursorWidth = gamePanel.tileSize;
        int cursorHeight = gamePanel.tileSize;

        graphics2D.setColor(Color.BLACK);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        int descriptionFrameX = frameX;
        int descriptionFrameY = frameY + frameHeight;
        int descriptionFrameWidth = frameWidth;
        int descriptionFrameHeight = gamePanel.tileSize * 3;
        drawSubWindow(descriptionFrameX, descriptionFrameY, descriptionFrameWidth, descriptionFrameHeight);

        int textX = descriptionFrameX + 20;
        int textY = descriptionFrameY + gamePanel.tileSize;

        // Schimbă fontul și culoarea textului aici
        graphics2D.setColor(Color.BLACK); // Setează culoarea textului la negru
        graphics2D.setFont(new Font("Arial", Font.BOLD, 28)); // Setează fontul la Arial Bold de mărimea 28

        int itemIndex = getItemIndexOnSlot();

        if (itemIndex < gamePanel.player.inventory.size()) {
            String[] lines = gamePanel.player.inventory.get(itemIndex).description.split("\n");
            for (String line : lines) {
                graphics2D.drawString(line, textX, textY);
                textY += 32; // Ajustează acest număr conform nevoilor de spațiere
            }
        }
    }

    public int getItemIndexOnSlot() {
        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
    }

    private void drawCharacterScreen() {
        final int frameX = gamePanel.tileSize * 2;
        final int frameY = gamePanel.tileSize;
        final int frameWidth = gamePanel.tileSize * 5;
        final int frameHeight = gamePanel.tileSize * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        graphics2D.setColor(Color.BLACK);
        graphics2D.setFont(bellMTFont);

        int textX = frameX + 20;
        int textY = frameY + gamePanel.tileSize;
        final int lineHeight = 32;

        graphics2D.drawString("Level", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Life", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Carrots", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Keys", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Coins", textX, textY);
        textY += lineHeight;

        int tailX = (frameX + frameWidth) - 30;
        textY = frameY + gamePanel.tileSize;
        String value;
        value = String.valueOf(gamePanel.player.life);
        textX = getXforAlignToRightText(value, tailX);
        graphics2D.drawString(value, textX, textY);
    }

    public int getXforAlignToRightText(String text, int tailX) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        int x = tailX - length;
        return x;
    }

    public void drawPlayerLife() {
        int x = gamePanel.tileSize / 2;
        int y = gamePanel.tileSize / 2;
        int i = 0;

        while (i < gamePanel.player.maxLife / 2) {
            graphics2D.drawImage(heart_empty, x, y, null);
            ++i;
            x += gamePanel.tileSize;
        }

        x = gamePanel.tileSize / 2;
        y = gamePanel.tileSize / 2;
        i = 0;

        while (i < gamePanel.player.maxLife) {
            graphics2D.drawImage(heart_half, x, y, null);
            ++i;
            if (i < gamePanel.player.life) {
                graphics2D.drawImage(heart_full, x, y, null);
            }
            ++i;
            x += gamePanel.tileSize;
        }
    }

    public void drawPauseScreen() {
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 80));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gamePanel.screenHeight / 2;
        graphics2D.drawString(text, x, y);
    }

    public int getXforCenteredText(String text) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        int x = gamePanel.screenWidth / 2 - length / 2;
        return x;
    }

    public void drawLevel2() {
        graphics2D.drawImage(level2Image, 0, 0, gamePanel.screenWidth, gamePanel.screenHeight, null);
    }

    public void drawLevel3() {
        graphics2D.drawImage(level3Image, 0, 0, gamePanel.screenWidth, gamePanel.screenHeight, null);
    }

    public void drawDialogueScreen() {
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize / 2;
        int width = (gamePanel.screenWidth - gamePanel.tileSize * 4);
        int height = (gamePanel.tileSize * 4);
        graphics2D.drawImage(dialogueBoxImage, x, y, width, height, null);

        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 32F));
        x += gamePanel.tileSize;
        y += gamePanel.tileSize;

        for (String line : currentDialogue.split("\n")) {
            graphics2D.drawString(line, x, y);
            y += 40;
        }
        graphics2D.drawString(currentDialogue, x, y);
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        // Setare umbre
        Color shadowColor = new Color(0, 0, 0, 150); // Umbra neagră semi-transparentă
        int shadowOffset = 5;
        graphics2D.setColor(shadowColor);
        graphics2D.fillRoundRect(x + shadowOffset, y + shadowOffset, width, height, 35, 35);

        // Setare gradient pentru fundal galben spălăcit și transparent
        Color gradientStartColor = new Color(241, 241, 123, 150); // Galben deschis semi-transparent
        Color gradientEndColor = new Color(255, 255, 204, 150); // Galben și mai deschis semi-transparent
        GradientPaint gradientPaint = new GradientPaint(x, y, gradientStartColor, x + width, y + height, gradientEndColor);
        graphics2D.setPaint(gradientPaint);
        graphics2D.fillRoundRect(x, y, width, height, 35, 35);

        // Bordura mov
        Color borderColor = new Color(255, 0, 255); // Mov pentru bordură
        graphics2D.setColor(borderColor);
        graphics2D.setStroke(new BasicStroke(3)); // Grosimea bordurii
        graphics2D.drawRoundRect(x, y, width, height, 35, 35);

        // Opțional: un al doilea strat de bordură pentru efect extra
        Color innerBorderColor = new Color(0, 255, 8); // Indigo pentru bordura interioară
        graphics2D.setColor(innerBorderColor);
        graphics2D.setStroke(new BasicStroke(1)); // Bordură mai subțire
        graphics2D.drawRoundRect(x + 2, y + 2, width - 4, height - 4, 33, 33);
    }

    public void loadLevel2Image() {
        try {
            level2Image = ImageIO.read(getClass().getResourceAsStream("/Titles/LoadScreen.png"));
            if (level2Image != null) {
                System.out.println("Level 2 image loaded successfully.");
            } else {
                System.out.println("Failed to load Level 2 image.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadLevel3Image() {
        try {
            level3Image = ImageIO.read(getClass().getResourceAsStream("/Titles/LoadScreen.png"));
            if (level3Image != null) {
                System.out.println("Level 3 image loaded successfully.");
            } else {
                System.out.println("Failed to load Level 3 image.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
