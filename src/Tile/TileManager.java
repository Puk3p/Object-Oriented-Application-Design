package Tile;

import Main.GamePanel;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tiles;
    public int mapTileNum[][];
    HashMap<String, BufferedImage> imageCache = new HashMap<>();

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[500];
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        loadTileImages();
        loadMap("/Maps/map_01.txt");
    }

    public void setup(int index, String imagePath, boolean collision) {
        try {
            if (!imageCache.containsKey(imagePath)) {
                BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/Tiles/" + imagePath + ".png"));
                imageCache.put(imagePath, image);
            }
            tiles[index] = new Tile();
            tiles[index].image = imageCache.get(imagePath);
            tiles[index].collision = collision;
        } catch (IOException e) {
            System.err.println("Error loading image for tile: " + imagePath);
            e.printStackTrace();
        }
    }

    private void loadTileImages() {
        System.out.println("Loading tile images...");

        //casa
        setup(400, "blank", false);
        setup(404, "casainterior4", false);
        setup(405, "casainterior5", false);
        setup(406, "casainterior6", false);
        setup(411, "casainterior11", false);
        setup(412, "casainterior12", false);
        setup(413, "casainterior13", false);
        setup(407, "casainterior7", false);
        setup(429, "casainterior29", true);
        setup(430, "casainterior30", true);
        setup(431, "casainterior31", true);
        setup(436, "casainterior36", true);
        setup(438, "casainterior38", true);
        setup(443, "casainterior43", true);
        setup(444, "casainterior44", true);
        setup(445, "casainterior45", true);



        //rasarui
        setup(351, "pamantRasaduri1", false);
        setup(352, "pamantRasaduri2", false);
        setup(353, "pamantRasaduri3", false);
        setup(354, "pamantRasaduri4", false);
        setup(355, "pamantRasaduri5", false);
        setup(356, "pamantRasaduri6", false);
        setup(357, "pamantRasaduri7", false);
        setup(358, "pamantRasaduri8", false);
        setup(359, "pamantRasaduri9", false);
        setup(360, "pamantRasaduri10", false);
        setup(361, "pamantRasaduri11", false);
        setup(362, "pamantRasaduri12", false);
        setup(363, "pamantRasaduri13", false);
        setup(364, "pamantRasaduri14", false);
        setup(365, "pamantRasaduri15", false);
        setup(366, "pamantRasaduri16", false);
        setup(367, "pamantRasaduri17", false);
        setup(368, "pamantRasaduri18", false);
        setup(369, "pamantRasaduri19", false);
        setup(370, "pamantRasaduri20", false);
        setup(371, "pamantRasaduri21", false);
        setup(372, "pamantRasaduri22", false);
        setup(373, "pamantRasaduri23", false);
        setup(374, "pamantRasaduri24", false);
        setup(375, "pamantRasaduri25", false);
        setup(376, "pamantRasaduri26", false);
        setup(377, "pamantRasaduri27", false);
        setup(378, "pamantRasaduri28", false);
        setup(379, "pamantRasaduri29", false);
        setup(380, "pamantRasaduri30", false);
        setup(381, "pamantRasaduri31", false);
        setup(382, "pamantRasaduri32", false);
        setup(383, "pamantRasaduri33", false);
        setup(384, "pamantRasaduri34", false);
        setup(385, "pamantRasaduri35", false);
        setup(386, "pamantRasaduri36", false);
        setup(387, "pamantRasaduri37", false);
        setup(388, "pamantRasaduri38", false);
        setup(389, "pamantRasaduri39", false);
        setup(390, "pamantRasaduri40", false);
        setup(391, "pamantRasaduri41", false);
        setup(392, "pamantRasaduri42", false);
        setup(393, "pamantRasaduri43", false);
        setup(394, "pamantRasaduri44", false);
        setup(395, "pamantRasaduri45", false);
        setup(396, "pamantRasaduri46", false);
        setup(397, "pamantRasaduri47", false);
        setup(398, "pamantRasaduri48", false);



        //tile-uri univ
        setup(249, "colt_pt_dreapta", false);
        setup(248,"colt_pt_stanga", false);
        setup(250,"colt_pt_stanga_next", false);

        setup(310, "cararae_normala", false);
        setup(311, "carare_finala1", false);
        setup(312, "carare_finala2", false);
        setup(313, "carare_finala3", false);
        setup(314, "carare_finala4", false);
        setup(316, "carare_finala6", false);
        setup(317, "carare_finala7", false);
        setup(318, "carare_finala8", false);
        setup(319, "carare_finala9", false);

        setup(321, "carareinversa1", false);
        setup(322, "carareinversa2", false);
        setup(323, "carareinversa3", false);
        setup(324, "carareinversa4", false);
        setup(326, "carareinversa6", false);
        setup(327, "carareinversa7", false);
        setup(328, "carareinversa8", false);
        setup(329, "carareinversa9", false);

        setup(0, "apa", false);
        setup(1,"apa", true);
        setup(7, "Sprite-0007", true);
        setup(8, "Sprite-0008", true);
        setup(9, "Sprite-0009", true);
        setup(21, "Sprite-0021", true);
        setup(22, "Sprite-0022", true);
        setup(23, "Sprite-0023", true);
        setup(24, "Sprite-0024", false);  // Changed to false
        setup(25, "Sprite-0025", true);
        setup(26, "Sprite-0026", true);
        setup(27, "Sprite-0027", true);
        setup(28, "Sprite-0028", true);
        setup(31, "Sprite-0031", true);
        setup(32, "Sprite-0032", true);
        setup(33, "Sprite-0033", true);
        setup(36, "Sprite-0036", true);
        setup(37, "Sprite-0037", false);  // Changed to false
        setup(38, "Sprite-0038", false);  // Changed to false
        setup(39, "Sprite-0039", false);  // Changed to false
        setup(40, "Sprite-0040", false);  // Changed to false
        setup(41, "Sprite-0041", false);  // Changed to false
        setup(42, "Sprite-0042", false);  // Changed to false
        setup(43, "Sprite-0043", false);  // Changed to false
        setup(44, "Sprite-0044", true);
        setup(45, "Sprite-0045", true);
        setup(46, "Sprite-0046", true);
        setup(47, "Sprite-0047", false);  // Changed to false
        setup(48, "Sprite-0048", false);  // Changed to false
        setup(49, "Sprite-0049", true);
        setup(52, "Sprite-0052", true);
        setup(53, "Sprite-0053", false);
        setup(54, "Sprite-0054", true);
        setup(55, "Sprite-0055", true);
        setup(56, "Sprite-0056", false);
        setup(57, "Sprite-0057", false);  // Changed to false
        setup(58, "Sprite-0058", false);  // Changed to false
        setup(59, "Sprite-0059", false);  // Changed to false
        setup(60, "Sprite-0060", false);  // Changed to false
        setup(61, "Sprite-0061", false);  // Changed to false
        setup(62, "Sprite-0062", false);  // Changed to false
        setup(63, "Sprite-0063", false);  // Changed to false
        setup(64, "Sprite-0064", false);  // Changed to false
        setup(65, "Sprite-0065", true);
        setup(68, "Sprite-0068", true);
        setup(69, "Sprite-0069", false);
        setup(70, "Sprite-0070", true);
        setup(71, "Sprite-0071", true);
        setup(72, "Sprite-0072", true);
        setup(73, "Sprite-0073", true);
        setup(74, "Sprite-0074", true);
        setup(75, "Sprite-0075", true);
        setup(76, "Sprite-0076", false);  // Changed to false
        setup(77, "Sprite-0077", false);  // Changed to false
        setup(78, "Sprite-0078", false);  // Changed to false
        setup(79, "Sprite-0079", true);
        setup(80, "Sprite-0080", true);
        setup(81, "Sprite-0081", true);
        setup(84, "Sprite-0084", true);
        setup(85, "Sprite-0085", true);
        setup(86, "Sprite-0086", true);
        setup(87, "Sprite-0087", true);
        setup(88, "Sprite-0088", true);
        setup(89, "Sprite-0089", true);
        setup(90, "Sprite-0090", true);
        setup(91, "Sprite-0091", true);
        setup(92, "Sprite-0092", true);
        setup(93, "Sprite-0093", true);
        setup(94, "Sprite-0094", true);
        setup(95, "Sprite-0095", true);
        setup(96, "Sprite-0096", true);
        setup(97, "Sprite-0097", true);
        setup(100, "Sprite-0100", true);
        setup(101, "Sprite-0101", true);
        setup(102, "Sprite-0102", true);
        setup(106, "Sprite-0106", true);
        setup(107, "Sprite-0107", true);
        setup(108, "Sprite-0108", true);
        setup(109, "Sprite-0109", true);
        setup(110, "Sprite-0110", true);
        setup(111, "Sprite-0111", true);
        setup(112, "Sprite-0112", true);
        setup(113, "Sprite-0113", true);
        setup(125, "Sprite-0125", true);
        setup(126, "Sprite-0126", true);


        //barca
        setup(200,"SpriteBarca1", true);
        setup(201,"SpriteBarca2", true);
        setup(202,"SpriteBarca3", true);
        setup(203,"SpriteBarca4", true);
        setup(204,"SpriteBarca5", true);
        setup(205,"SpriteBarca6", true);
        setup(206,"SpriteBarca7", true);
        setup(207,"SpriteBarca8", true);
        setup(208,"SpriteBarca9", true);
        setup(209,"SpriteBarca10", true);
        setup(210,"SpriteBarca11", true);
        setup(211,"SpriteBarca12", true);
        setup(212,"SpriteBarca13", true);
        setup(213,"SpriteBarca14", true);
        setup(214,"SpriteBarca15", true);
        setup(215,"SpriteBarca16", true);
        setup(216,"SpriteBarca17", true);
        setup(217,"SpriteBarca18", true);
        setup(218,"SpriteBarca19", true);
        setup(219,"SpriteBarca20", true);
        setup(220,"SpriteBarca21", true);
        setup(221,"SpriteBarca22", true);
        setup(222,"SpriteBarca23", true);
        setup(223,"SpriteBarca24", true);
        setup(224,"SpriteBarca25", true);
        setup(225,"SpriteBarca26", true);
        setup(226,"SpriteBarca27", true);
        setup(227,"SpriteBarca28", true);
        setup(228,"SpriteBarca29", true);
        setup(229,"SpriteBarca30", true);
        setup(230,"SpriteBarca31", true);
        setup(231,"SpriteBarca32", true);
        setup(232,"SpriteBarca33", true);
        setup(233,"SpriteBarca34", true);
        setup(234,"SpriteBarca35", true);
        setup(235,"SpriteBarca36", true);
        setup(236,"SpriteBarca37", true);
        setup(237,"SpriteBarca38", true);
        setup(238,"SpriteBarca39", true);
        setup(239,"SpriteBarca40", true);
        setup(240,"SpriteBarca41", true);
        setup(241,"SpriteBarca42", true);
        setup(242,"SpriteBarca43", true);
        setup(243,"SpriteBarca44", true);
        setup(244,"SpriteBarca45", true);
        setup(245,"SpriteBarca46", true);
        setup(246,"SpriteBarca47", true);
        setup(247,"SpriteBarca48", true);

        //casa
        setup(251, "Casa1", false);
        setup(252, "Casa2", false);
        setup(253, "Casa3", false);
        setup(254, "Casa4", false);
        setup(255, "Casa5", false);
        setup(256, "Casa6", false);
        setup(257, "Casa7", false);
        setup(258, "Casa8", false);
        setup(259, "Casa9", false);
        setup(260, "Casa10", false);
        setup(261, "Casa11", false);
        setup(262, "Casa12", false);
        setup(263, "Casa13", false);
        setup(264, "Casa14", false);
        setup(265, "Casa15", false);
        setup(266, "Casa16", false);
        setup(267, "Casa17", false);
        setup(268, "Casa18", true);
        setup(269, "Casa19", true);
        setup(270, "Casa20", true);
        setup(271, "Casa21", true);
        setup(272, "Casa22", true);
        setup(273, "Casa23", true);
        setup(274, "Casa24", false);
        setup(275, "Casa25", false);
        setup(276, "Casa26", true);
        setup(277, "Casa27", true);
        setup(278, "Casa28", true);
        setup(279, "Casa29", true);
        setup(280, "Casa30", true);
        setup(281, "Casa31", true);
        setup(282, "Casa32", false);
        setup(283, "Casa33", false);
        setup(284, "Casa34", true);
        setup(285, "Casa35", true);
        setup(286, "Casa36", true);
        setup(287, "Casa37", true);
        setup(288, "Casa38", true);
        setup(289, "Casa39", true);
        setup(290, "Casa40", false);
        setup(291, "Casa41", false);
        setup(292, "Casa42", true);
        setup(293, "Casa43", true);
        setup(294, "Casa44", true);
        setup(295, "Casa45", true);
        setup(296, "Casa46", true);
        setup(297, "Casa47", true);
        setup(298, "Casa48", false);
        setup(299, "Casa49", false);
        setup(300, "Casa50", false);
        setup(301, "Casa51", false);
        setup(302, "Casa52", false);
        setup(303, "Casa53", false);
        setup(304, "Casa54", false);
        setup(305, "Casa55", false);
        setup(306, "Casa56", false);
    }

    public void loadMap(String path) {
        System.out.println("Loading map from: " + path);
        try (InputStream is = getClass().getResourceAsStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < gamePanel.maxWorldRow) {
                String[] tokens = line.split("\\s+");
                for (int col = 0; col < tokens.length && col < gamePanel.maxWorldCol; col++) {
                    mapTileNum[col][row] = Integer.parseInt(tokens[col]);
                }
                row++;
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Failed to load the map.");
            e.printStackTrace();
        }
    }

    public void unloadTileImages() {
        System.out.println("Unloading tile images...");
        imageCache.clear();
    }

    public void draw(Graphics2D graphics2D) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldRow < gamePanel.maxWorldRow && worldCol < gamePanel.maxWorldCol) {
            int tileNum = mapTileNum[worldCol][worldRow];

            if (tileNum >= 0 && tileNum < tiles.length) {
                if (tiles[tileNum] == null) {
                    System.err.println("Tile " + tileNum + " is not initialized!");
                } else {
                    int worldX = worldCol * gamePanel.tileSize;
                    int worldY = worldRow * gamePanel.tileSize;
                    int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
                    int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

                    if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                            worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                            worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                            worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
                        graphics2D.drawImage(tiles[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                    }
                }
            } else {
                System.err.println("Invalid tile index: " + tileNum);
            }
            ++worldCol;

            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                ++worldRow;
            }
        }
    }

    public void loadMapLevel2() {
        // Load the map for level 2
        loadMap("/Maps/map_02.txt");
    }

    public void loadMapLevel3() {
        // Load the map for level 3
        loadMap("/Maps/map_03.txt");
    }
}
