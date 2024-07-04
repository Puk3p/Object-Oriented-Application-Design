package Main;

import DataBase.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Object.Table;
import Entity.Entity;
import Object.Carrot;
import Entity.Player;
import Tile.TileManager;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 4;

    private int completedTables = 0;
    private boolean levelCompletedMessageShown = false;

    public final int tileSize = originalTileSize * scale; // 48*48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    public final int maxWorldCol = 80;
    public final int maxWorldRow = 80;
    public final int worldWidth = tileSize * maxWorldCol; // 800 pixels
    public final int worldHeight = tileSize * maxWorldRow; // 800 pixels

    int FPS = 60;

    TileManager tileManager = new TileManager(this);
    public KeyHandler keyHandler = new KeyHandler(this);
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyHandler);
    public Entity objects[] = new Entity[50];
    public Entity NPC[] = new Entity[10];
    ArrayList<Entity> entityList = new ArrayList<>();
    public EventHandler eventHandler = new EventHandler(this);
    Player database = new Player(this, keyHandler);

    public AssetSetter assetSetter = new AssetSetter(this);
    Sound sound = new Sound();
    Sound music = new Sound();
    public UI ui = new UI(this);

    public int gameState;
    public final int tileState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;

    public final int maxMap = 3;
    public int currentMap = 0;

    public final int level1 = 1;
    public final int level2 = 2;
    public final int level3 = 3;
    public int currentLevel = level1;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObjects();
        assetSetter.setNPC();
        gameState = tileState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                ++drawCount;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            player.update();

            // Update NPCs
            for (int i = 0; i < NPC.length; ++i) {
                if (NPC[i] != null) {
                    NPC[i].update();
                }
            }

            // Update objects, including special handling for Carrot and Table
            for (int i = 0; i < objects.length; ++i) {
                if (objects[i] != null) {
                    if (objects[i] instanceof Carrot) {
                        ((Carrot) objects[i]).update();  // Call update on Carrot to possibly advance its stage
                    }
                    if (objects[i] instanceof Table) {
                        ((Table) objects[i]).getHasCarrot(); // Verifică dacă sunt 20 de morcovi
                    }
                }
            }

            if (currentLevel == level1 && checkLevel1Completion()) {
                loadLevel2();
            } else if (currentLevel == level2 && checkLevel2Completion()) {
                loadLevel3();
            }
        }

        if (gameState == pauseState) {
            // Handling for pause state if necessary
        }

        // Ensure player updates are called only in their respective state
        if (gameState == playState) {
            player.update();
        }
    }

    private boolean checkLevel1Completion() {
        // Define your conditions for level 1 completion
        return player.hasCollectedAllKeys(); // Example condition
    }

    private boolean checkLevel2Completion() {
        return completedTables >= 8; // Check if 8 tables are completed
    }


    private void loadLevel2() {
        currentLevel = level2;
        player.resetPosition(); // Reset player's position for the new level
        assetSetter.setObjectsLevel2(); // Load objects for level 2
        assetSetter.setNPCLevel2(); // Load NPCs for level 2
        tileManager.loadMapLevel2(); // Load tile map for level 2
    }

    private void loadLevel3() {
        currentLevel = level3;
        player.resetPosition(); // Reset player's position for the new level
        assetSetter.setObjectsLevel3(); // Load objects for level 3
        assetSetter.setNPCLevel3(); // Load NPCs for level 3
        tileManager.loadMapLevel3(); // Load tile map for level 3
        ui.showMessage("Level 3 loaded!");
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        if (gameState == tileState) {
            ui.draw(graphics2D);
        } else if (gameState == playState && ui.gameFinished) {
            ui.drawLevel2();
        } else {
            tileManager.draw(graphics2D);

            entityList.clear();

            entityList.add(player);
            for (Entity npc : NPC) {
                if (npc != null) {
                    entityList.add(npc);
                }
            }
            for (Entity obj : objects) {
                if (obj != null) {
                    entityList.add(obj);
                }
            }

            Collections.sort(entityList, Comparator.comparingInt(e -> e.worldY));

            for (Entity entity : entityList) {
                entity.draw(graphics2D);
            }

            ui.draw(graphics2D);
        }

        graphics2D.dispose();
    }

    public void playMusic(int index) {
        music.setFile(index);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int index) {
        sound.setFile(index);
        sound.play();
    }

    public void insertDataToDatabase() {
        Connection conn = DataBase.getConnection();
        if (conn == null) {
            System.err.println("Failed to establish a database connection.");
            return;
        }
        String sql = "INSERT INTO new_table (idnew_table, `keys`, carrot) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, database.getHasCarrot());
            stmt.setInt(2, database.getHasKey());
            stmt.setInt(3, database.getHasCarrot());
            stmt.executeUpdate();
            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBase.closeConnection(conn);
        }
    }

    public void setVariablesFromDatabase() {
        Connection connection = DataBase.getConnection();
        try {
            String query = "SELECT `keys`, carrot FROM new_table";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                database.setHasKey(resultSet.getInt("keys"));
                database.setHasCarrot(resultSet.getInt("carrot"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBase.closeConnection(connection);
        }
    }

    public void updateTableCompletion(Table table) {
        if (table.down1 == table.down2) {
            completedTables++;
            if (completedTables >= 8 && !levelCompletedMessageShown) {
                showLevelCompletedMessage();
                levelCompletedMessageShown = true;
                loadLevel3(); // Load level 3 when 8 tables are completed
            }
        }
    }

    public void showLevelCompletedMessage() {
        ui.showMessage("Nivel completat");
    }
}
