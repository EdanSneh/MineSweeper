import java.awt.*;

public class Board {

    public static final int bombInt = 10;
    private Tile[][] tiles;
    private int numMasked;
    private boolean isLose;


    public Board(int length, int width, int bombs) {
        tiles = new Tile[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                tiles[i][j] = new Tile();
            }
        }
        placeBombs(length, width, bombs);
        numMasked = length * width - bombs;

    }

    private void placeBombs(int length, int width, int bombs) {
        for (int i = 0; i < bombs; i++) {
            int randLength = (int) (Math.random() * length);;
            int randWidth = (int) (Math.random() * width);
            Tile thisTile = tiles[randLength][randWidth];
            if (thisTile.getType() != bombInt) {
                thisTile.setType(bombInt);
                if (randLength != 0) {
                    Tile tile = tiles[randLength - 1][randWidth];
                    tile.setType(tile.getType() + 1);
                } else if (randLength == length) {
                    Tile tile = tiles[randLength + 1][randWidth];
                    tile.setType(tile.getType() + 1);
                }

                if (randWidth != 0) {
                    Tile tile = tiles[randLength][randWidth - 1];
                    tile.setType(tile.getType() + 1);
                } else if (randWidth == width) {
                    Tile tile = tiles[randLength][randWidth + 1];
                    tile.setType(tile.getType() + 1);
                }

                if (randLength != 0 && randWidth != 0) {
                    Tile tile = tiles[randLength - 1][randWidth - 1];
                    tile.setType(tile.getType() + 1);
                } else if (randLength != length && randWidth != width) {
                    Tile tile = tiles[randLength + 1][randWidth + 1];
                    tile.setType(tile.getType() + 1);
                }

                if (randLength != 0 && randWidth != width) {
                    Tile tile = tiles[randLength - 1][randWidth + 1];
                    tile.setType(tile.getType() + 1);
                } else if (randLength != length && randWidth != 0) {
                    Tile tile = tiles[randLength + 1][randWidth - 1];
                    tile.setType(tile.getType() + 1);
                }
            } else {
                i--;
            }
        }
    }

    public boolean checkSquare(int length, int width) {
        Tile tile = tiles[length][width];
        if (tile.isMasked() && !tile.isFlagged()) {
            if (tile.getType() == bombInt) {
                isLose = true;
            } else {
                tile.setMasked(false);
                numMasked--;
            }
            return true;
        }
        return false;
    }

    public boolean flag(int length, int width) {
        Tile tile = tiles[length][width];
        if (tile.isMasked()) {
            tile.setFlagged(!tile.isFlagged());
            return true;
        }
        return false;
    }

    public boolean isWin() {
        return numMasked == 0;
    }

    public boolean isLose() {
        return isLose;
    }

    public void draw(Graphics g) {
        int width = tiles[0].length;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < width; j++) {
                Image img = tiles[i][j].getImage();
                g.drawImage(img, i, j, 200, 200, null);
            }
        }
    }
}
