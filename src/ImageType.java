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
}
