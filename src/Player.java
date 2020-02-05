import processing.core.PVector;

import java.util.HashMap;


public class Player {

    public PVector computeMove(Tile[][] board) {
        int x = 0, y = 0, count = 0;
        int numBlackTiles = 0, numWhiteTiles, i, j;

        PVector out = new PVector(0, 0);

        boolean[][] simplifiedBoard  = new boolean[board.length][board[0].length],
                originalBoard = new boolean[board.length][board[0].length];

        HashMap<Integer, boolean[][]> tree = new HashMap<>();

        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board[i].length; j++) {
                simplifiedBoard[i][j] = board[i][j].isOn();
                originalBoard[i][j] = board[i][j].isOn();
            }
        }

        tree.put(0, simplifiedBoard);
        for (int k = 0; k < 5; k++) {

            for (i = 0; i < board.length; i++) {
                for (j = 0; j < board[i].length; j++) {
                    tree.put(k, hypotheticalClick(i, j, simplifiedBoard));
                }
            }

        }

        return out;
    }

    private boolean[][] hypotheticalClick(int i, int j, boolean[][] simplifiedBoard) {
        boolean[][] out = new boolean[simplifiedBoard.length][simplifiedBoard[0].length];


        for (int k = 0; k < simplifiedBoard.length; k++) {
            for (int l = 0; l < simplifiedBoard[i].length; l++) {
                out[k][l] = simplifiedBoard[k][l];
            }
        }

        if(i-1>0)out[i-1][j-1]=!out[i-1][j-1];
        if(i+1<out.length)out[i+1][j-1]=!out[i-1][j-1];
        if(j-1>0)out[i][j-1]=!out[i][j-1];
        if(j+1<out[0].length)out[i][j-1]=!out[i][j-1];

        out[i][j] = !out[i][j];


        return out;
    }

    private int countBlackTiles(Tile[][] board) {
        int numBlackTiles = 0, i;
        for (Tile[] tiles : board) {
            for (i = 0; i < tiles.length; i++) {
                if (tiles[i].isOn()) {
                    numBlackTiles++;
                }
            }
        }

        return numBlackTiles;
    }

}
