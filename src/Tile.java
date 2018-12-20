import java.awt.*;

public class Tile {
	private int type;
	private boolean masked;
	private boolean flagged;
	ImageType image;

	public Tile() {
		type = 0;
		masked = true;
		image = ImageType.TILE;
	}

	public Image getImage() {
	    return image.getImage();
    }

	public int getType() {
		return type;
	}

	public boolean isFlagged() {
		return flagged;
	}

	public boolean isMasked() {
		return masked;
	}

	public void setType(int type) {
            this.type = type;
	}

	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}

	public void setMasked(boolean masked) {
		this.masked = masked;
		if (!masked) {
            image = ImageType.values()[type];
        }
	}
}
