import javax.swing.*;
import java.awt.*;

public enum ImageType {
    ZERO("zero"),
    ONE("one"),
    TWO("two"),
    THREE("three"),
    FOUR("four"),
    FIVE("five"),
    SIX("six"),
    SEVEN("seven"),
    EIGHT("eight"),
    TILE("tile"),
    BOMB("bomb"),
    FLAG("flag");

    private String url;

    private ImageType(String url) {
        this.url = "images/" + url + ".png";
    }

    public String getUrl() {
        return url;
    }

    public Image getImage() {
        java.net.URL imgURL = getClass().getClassLoader().getResource(getUrl());
        ImageIcon icon = null;
        if (imgURL != null) {
            icon = new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + getUrl());
        }
        return icon.getImage();
    }
}
