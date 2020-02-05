import processing.core.PApplet;
import processing.core.PVector;

import javax.swing.*;

public class MainAI extends PApplet {

    public static int NUM_TILES_ROW = Integer.parseInt(JOptionPane.showInputDialog("Enter num in a row"));
    public static int NUM_TILES_COL = Integer.parseInt(JOptionPane.showInputDialog("Enter num in a column"));
    private static final double INIT_ON_PROBABILITY = 0 / 6;

    private Tile[][] tiles = new Tile[NUM_TILES_ROW][NUM_TILES_COL];

    public static final int WIDTH = 1920, HEIGHT = NUM_TILES_COL * Tile.SIDE;

    private boolean creationPhase = true;

    Player p = new Player();

    public void setup() {
        size(WIDTH, HEIGHT);
        int j;
        for (int i = 0; i < tiles.length; i++) {
            for (j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new Tile(i * Tile.SIDE, j * Tile.SIDE);
            }
        }

        Tile[] neighbors;


        for (int i = 0; i < tiles.length; i++) {
            for (j = 0; j < tiles[i].length; j++) {
                neighbors = tiles[i][j].getNeighbors();

                if (j != tiles[i].length - 1) neighbors[Tile.DOWN] = tiles[i][j + 1];
                if (j != 0) neighbors[Tile.UP] = tiles[i][j - 1];
                if (i != 0) neighbors[Tile.LEFT] = tiles[i - 1][j];
                if (i != tiles.length - 1) neighbors[Tile.RIGHT] = tiles[i + 1][j];

                if (j != tiles[i].length - 1) tiles[i][j + 1] = neighbors[Tile.DOWN];
                if (j != 0) tiles[i][j - 1] = neighbors[Tile.UP];
                if (i != 0) tiles[i - 1][j] = neighbors[Tile.LEFT];
                if (i != tiles.length - 1) tiles[i + 1][j] = neighbors[Tile.RIGHT];

            }
        }


        for (Tile[] row : tiles) {
            for (Tile t : row) {
                if (Math.random() < INIT_ON_PROBABILITY) {
                    t.changePolarity();
                }
            }
        }


    }


    public void draw() {
        background(255);

        drawTiles();
        PVector vector = p.computeMove(tiles);
        System.out.println(vector);
        mousePressed((int) vector.x, (int) vector.y);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void drawTiles() {
        fill(255);

        for (Tile[] a : tiles) {
            for (Tile t : a) {
                t.draw(this);
            }
        }


    }

    public void mousePressed(int x, int y) {
        if (x < tiles.length && y < tiles[0].length) {
            tiles[x][y].changePolarity();
            tiles[x][y].changeNeighborsPolarity();
        }


    }

    public void keyPressed() {
        if (key == 'e') {
            creationPhase = !creationPhase;
        }
    }

    public static void main(String[] args) {
        PApplet.main("MainAI");
    }
}
