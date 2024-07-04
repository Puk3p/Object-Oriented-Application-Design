package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    boolean dataInserted = false;
    GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyPressed(KeyEvent event) {

        int code = event.getKeyCode();

        if (gamePanel.gameState == gamePanel.tileState) {
            titleState(code);
        }
        if (gamePanel.gameState == gamePanel.playState) {
            playState(code);
        } else if (gamePanel.gameState == gamePanel.pauseState) {
            pauseState(code);
        } else if (gamePanel.gameState == gamePanel.dialogueState) {
            dialogueState(code);
        } else if (gamePanel.gameState == gamePanel.characterState) {
            characterState(code);
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
    }

    public void titleState(int code) {

        if (code == KeyEvent.VK_D) {
            ++gamePanel.ui.commandNum;

            if (gamePanel.ui.commandNum > 2) {
                gamePanel.ui.commandNum = 2;
            }
        }
        if (code == KeyEvent.VK_A) {
            --gamePanel.ui.commandNum;

            if (gamePanel.ui.commandNum < 0) {
                gamePanel.ui.commandNum = 2;
            }
        }

        if (code == KeyEvent.VK_ENTER) {

            if (gamePanel.ui.commandNum == 0) {
                gamePanel.gameState = gamePanel.playState;
                gamePanel.playMusic(0);
            }

            if (gamePanel.ui.commandNum == 1) {
                gamePanel.setVariablesFromDatabase();
                gamePanel.gameState = gamePanel.playState;
            }

            if (gamePanel.ui.commandNum == 2) {
                System.exit(0);
            }
        }
    }

    public void playState(int code) {

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }

        if (code == KeyEvent.VK_P) {
            gamePanel.gameState = gamePanel.pauseState;
        }

        if (code == KeyEvent.VK_I) {
            gamePanel.gameState = gamePanel.characterState;
        }

        if (code == KeyEvent.VK_L) {
            if (!dataInserted) { // Verifică dacă funcția nu a fost deja executată
                gamePanel.insertDataToDatabase();
                dataInserted = true; // Marchează funcția ca fiind executată
            } else {
                System.out.println("Datele au fost deja introduse. Nu se poate reintroduce.");
            }
        }
    }

    public void pauseState(int code) {

        if (code == KeyEvent.VK_P) {
            gamePanel.gameState = gamePanel.playState;
        }
    }

    public void dialogueState(int code) {

        if (code == KeyEvent.VK_ENTER) {
            gamePanel.gameState = gamePanel.playState;
        }
    }

    public void characterState(int code) {

        if (code == KeyEvent.VK_I) {
            gamePanel.gameState = gamePanel.playState;
        }

        if (code == KeyEvent.VK_W) {
            if (gamePanel.ui.slotRow != 0) {
                --gamePanel.ui.slotRow;
                gamePanel.playSE(3);
            }
        }

        if (code == KeyEvent.VK_S) {
            if (gamePanel.ui.slotRow != 3) {
                ++gamePanel.ui.slotRow;
                gamePanel.playSE(3);
            }
        }

        if (code == KeyEvent.VK_A) {
            if (gamePanel.ui.slotCol != 0) {
                --gamePanel.ui.slotCol;
                gamePanel.playSE(3);
            }
        }

        if (code == KeyEvent.VK_D) {
            if (gamePanel.ui.slotCol != 4) {
                ++gamePanel.ui.slotCol;
                gamePanel.playSE(3);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent event) {
        //TODO
    }

    @Override
    public void keyReleased(KeyEvent event) {

        int code = event.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
