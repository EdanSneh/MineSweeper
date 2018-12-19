public enum ImageType {
    ONE("one"),
    TWO("two"),
    THREE("three"),
    FOUR("four"),
    FIVE("five"),
    SIX("six"),
    SEVEN("seven"),
    EIGHT("eight"),
    BOMB("bomb"),
    TILE("tile"),
    FLAG("flag");

    private String url;

    private ImageType(String url) {
        this.url = "images/" + url + ".png";
    }

    public String getUrl() {
        return url;
    }
}
