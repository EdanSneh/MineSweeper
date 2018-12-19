import javax.swing.*;
import java.awt.*;

public class Tile {
	private int type;
	private boolean masked;
	private boolean flagged;
	int x, y, width, height;
	ImageType image;

	public Tile() {
		type = 0;
		masked = true;
		image = ImageType.TILE;
	}

	public Image getImage() {
	    java.net.URL imgURL = getClass().getClassLoader().getResource(image.getUrl());
        ImageIcon icon = null;
        if (imgURL != null) {
            icon = new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + image.getUrl());
        }
        return icon.getImage();
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
	}
}
