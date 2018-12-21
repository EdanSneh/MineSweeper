import java.awt.*;

public class Board {

    public static final int bombInt = 10;
    private Tile[][] tiles;
    private int numMasked;
    private boolean isLose;
    private int size;
    private boolean firstClick;
    private int length;
    private int width;
    private int bombs;


    public Board(int length, int width, int bombs) {
        this(length, width, bombs, 40);
    }

    public Board(int length, int width, int bombs, int size) {
        tiles = new Tile[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                tiles[i][j] = new Tile();
            }
        }
        this.size = size;
        this.bombs = bombs;
        this.length = length;
        this.width = width;
    }

    public void startGame(int x, int y) {
        firstClick = true;
        placeBombs(bombs, x, y);
        numMasked = length * width - bombs;
        checkSquare(x, y);
    }

    private void placeBombs(int bombs, int x, int y) {
        for (int i = 0; i < bombs; i++) {
            int randX = (int) (Math.random() * length);
            int randY = (int) (Math.random() * width);
            Tile thisTile = tiles[randX][randY];
            if (thisTile.getType() != bombInt && (Math.abs(x - randX) > 1 || Math.abs(y - randY) > 1)) {
                thisTile.setType(bombInt);
                addUp(randX + 1, randY);
                addUp(randX + 1, randY - 1);
                addUp(randX + 1, randY + 1);
                addUp(randX - 1, randY - 1);
                addUp(randX - 1, randY + 1);
                addUp(randX - 1, randY);
                addUp(randX, randY - 1);
                addUp(randX, randY + 1);
            } else {
                i--;
            }
        }
    }

    private void addUp(int x, int y) {
        Tile tile;
        try {
            tile = tiles[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
        if (tile.getType() != bombInt) {
            tile.setType(tile.getType() + 1);
        }
    }

    public boolean checkSquare(int x, int y) {
        return checkSquare(x, y, true);
    }

    private boolean checkSquare(int x, int y, boolean UserClick) {
        Tile tile;
        try {
            tile = tiles[x][y];
        } catch (Exception e) {
            return false;
        }
        if (tile.isMasked() && !tile.isFlagged()) {
            tile.setMasked(false);
            if (tile.getType() == bombInt) {
                isLose = true;
                unMask();
            } else {
                if (tile.getType() == 0) {
                    checkSquare(x + 1, y, false);
                    checkSquare(x - 1, y, false);
                    checkSquare(x, y + 1, false);
                    checkSquare(x, y - 1, false);
                    checkSquare(x + 1, y - 1, false);
                    checkSquare(x - 1, y + 1, false);
                    checkSquare(x + 1, y + 1, false);
                    checkSquare(x - 1, y - 1, false);
                }
                numMasked--;
            }
            return true;
        } else if (!tile.isMasked() && UserClick && tile.getType() <= flagsNear(x, y)) {
                checkSquare(x + 1, y, false);
                checkSquare(x - 1, y, false);
                checkSquare(x, y + 1, false);
                checkSquare(x, y - 1, false);
                checkSquare(x + 1, y - 1, false);
                checkSquare(x - 1, y + 1,false);
                checkSquare(x + 1, y + 1, false);
                checkSquare(x - 1, y - 1, false);
                return true;
        }
        return false;
    }

    private int flagsNear(int x, int y) {
        int flags = 0;
        flags += checkFlag(x + 1, y);
        flags += checkFlag(x - 1, y);
        flags += checkFlag(x, y + 1);
        flags += checkFlag(x, y - 1);
        flags += checkFlag(x + 1, y - 1);
        flags += checkFlag(x - 1, y + 1);
        flags += checkFlag(x + 1, y + 1);
        flags += checkFlag(x - 1, y - 1);
        return flags;
    }

    private int checkFlag(int x, int y) {
        Tile tile;
        try {
            tile = tiles[x][y];
        } catch (Exception e) {
            return 0;
        }
        if (tile.isFlagged()) {
            return 1;
        } else {
            return 0;
        }
    }

    private void unMask() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                tiles[i][j].setMasked(false);
            }
        }
    }

    public boolean flag(int x, int y) {
        Tile tile = tiles[x][y];
        if (tile.isMasked()) {
            tile.setFlagged(!tile.isFlagged());
            return true;
        }
        return false;
    }

    public boolean isWin() {
        if (numMasked == 0) {
            unMask();
        }
        return numMasked == 0;
    }

    public boolean isLose() {
        return isLose;
    }

    public void draw(Graphics g) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                Image img = tiles[i][j].getImage();
                g.drawImage(img, i * size, j * size, size, size, null);
            }
        }
    }

    public int length() {
        return length;
    }

    public int width() {
        return width;
    }

    public boolean firstClick() {
        return firstClick;
    }
}
