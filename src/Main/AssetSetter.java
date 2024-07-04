package Main;

import Object.Stairs;
import Object.Table;
import Entity.NPC_Trader;
import Entity.NPC;
import Object.Key;
import Object.Usa1;
import Object.Usa2;
import Object.Carrot;
import java.awt.Point;
import java.nio.channels.Pipe;
import java.util.HashMap;
import java.util.Map;
import Entity.Entity;

public class AssetSetter {
    GamePanel gamePanel;

    public enum ObjectType {
        KEY, USA1, USA2, CARROT, TABLE, STAIRS
    }

    private final Map<ObjectType, Class<?>> objectMap = new HashMap<>() {{
        put(ObjectType.KEY, Key.class);
        put(ObjectType.USA1, Usa1.class);
        put(ObjectType.USA2, Usa2.class);
        put(ObjectType.CARROT, Carrot.class);
        put(ObjectType.TABLE, Table.class);
        put(ObjectType.STAIRS, Stairs.class);
    }};

    private final Point[] objectPositions = new Point[] {
            new Point(14, 18),
            new Point(9, 15),
            new Point(10, 15),
            new Point(25, 17),
            new Point(25, 16),
            new Point(25, 15),
            new Point(26, 17),
            new Point(26, 16),
            new Point(26, 15),
            new Point(27, 15),
            new Point(27, 16),
            new Point(24, 17),
            new Point(24, 16),
            new Point(24, 15),
            new Point(28, 15),
            new Point(28, 16),
            new Point(70, 6), // Poziția mesei
            new Point(72, 6), // Poziția mesei
            new Point(74, 6), // Poziția mesei
            new Point(76, 6), // Poziția mesei
            new Point(68, 8),
            new Point(70, 8),
            new Point(72, 8),
            new Point(74, 8),
            new Point(76, 8),
            new Point(78, 8),
            new Point(16, 10),
            new Point(80, 9)
    };

    private final ObjectType[] objectTypes = new ObjectType[] {
            ObjectType.KEY,
            ObjectType.USA1,
            ObjectType.USA2,
            ObjectType.CARROT,
            ObjectType.CARROT,
            ObjectType.CARROT,
            ObjectType.CARROT,
            ObjectType.CARROT,
            ObjectType.CARROT,
            ObjectType.CARROT,
            ObjectType.CARROT,
            ObjectType.CARROT,
            ObjectType.CARROT,
            ObjectType.CARROT,
            ObjectType.CARROT,
            ObjectType.CARROT,
            ObjectType.TABLE,
            ObjectType.TABLE,
            ObjectType.TABLE,
            ObjectType.TABLE,
            ObjectType.TABLE,
            ObjectType.TABLE,
            ObjectType.TABLE,
            ObjectType.TABLE,
            ObjectType.TABLE,
            ObjectType.USA1,
            ObjectType.STAIRS
    };

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObjects() {
        int minSize = Math.min(gamePanel.objects.length, Math.min(objectPositions.length, objectTypes.length));
        for (int i = 0; i < minSize; i++) {
            Entity object = createObject(objectTypes[i]);
            if (object != null) {
                gamePanel.objects[i] = object;
                gamePanel.objects[i].worldX = objectPositions[i].x * gamePanel.tileSize;
                gamePanel.objects[i].worldY = objectPositions[i].y * gamePanel.tileSize;
            } else {
                System.out.println("Object creation failed for type: " + objectTypes[i]);
            }
        }
    }



    private Entity createObject(ObjectType type) {
        try {
            // Assuming each class constructor requires a GamePanel instance
            return (Entity) objectMap.get(type).getDeclaredConstructor(GamePanel.class).newInstance(gamePanel);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to create instance of " + type);
            return null;
        }
    }

    public void setNPC() {
        gamePanel.NPC[0] = new NPC(gamePanel);
        gamePanel.NPC[0].worldX = 22 * gamePanel.tileSize;
        gamePanel.NPC[0].worldY = 22 * gamePanel.tileSize;

        gamePanel.NPC[1] = new NPC_Trader(gamePanel);
        gamePanel.NPC[1].worldX = 7 * gamePanel.tileSize;
        gamePanel.NPC[1].worldY = 16 * gamePanel.tileSize;
    }

    public void setObjectsLevel2() {
        // Define objects for level 2
        gamePanel.objects[0] = new Key(gamePanel);
        gamePanel.objects[0].worldX = 20 * gamePanel.tileSize;
        gamePanel.objects[0].worldY = 20 * gamePanel.tileSize;

        gamePanel.objects[1] = new Table(gamePanel);
        gamePanel.objects[1].worldX = 70 * gamePanel.tileSize;
        gamePanel.objects[1].worldY = 6 * gamePanel.tileSize;
        // Add other objects as needed

        gamePanel.objects[2] = new Table(gamePanel);
        gamePanel.objects[2].worldX = 72 * gamePanel.tileSize;
        gamePanel.objects[2].worldY = 6 * gamePanel.tileSize;

        gamePanel.objects[2] = new Table(gamePanel);
        gamePanel.objects[2].worldX = 74 * gamePanel.tileSize;
        gamePanel.objects[2].worldY = 6 * gamePanel.tileSize;

        gamePanel.objects[2] = new Table(gamePanel);
        gamePanel.objects[2].worldX = 76 * gamePanel.tileSize;
        gamePanel.objects[2].worldY = 6 * gamePanel.tileSize;

        gamePanel.objects[2] = new Table(gamePanel);
        gamePanel.objects[2].worldX = 68 * gamePanel.tileSize;
        gamePanel.objects[2].worldY = 6 * gamePanel.tileSize;

        gamePanel.objects[3] = new Usa1(gamePanel);
        gamePanel.objects[3].worldX = 10 * gamePanel.tileSize;
        gamePanel.objects[3].worldY = 15 * gamePanel.tileSize;

        gamePanel.objects[4] = new Stairs(gamePanel);
        gamePanel.objects[4].worldX = 64 * gamePanel.tileSize;
        gamePanel.objects[4].worldY = 9 * gamePanel.tileSize;
    }

    public void setNPCLevel2() {
        // Define NPCs for level 2
        gamePanel.NPC[0] = new NPC(gamePanel);
        gamePanel.NPC[0].worldX = 10 * gamePanel.tileSize;
        gamePanel.NPC[0].worldY = 10 * gamePanel.tileSize;
        // Add other NPCs as needed
    }

    public void setObjectsLevel3() {
        setObjectsLevel2();

    }

    public void setNPCLevel3() {
        setNPCLevel2();
    }
}
