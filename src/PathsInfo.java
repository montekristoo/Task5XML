public enum PathsInfo {
    ROOT_PATH(System.getProperty("user.home") + "//Desktop//Data"),
    ROOT_PAGE("https://makeup.md/sitemap/sitemap.xml"),
    ROOT_TAG("sitemap"),
    BREAKPOINT("url"),
    ALL_DATA_FOLDER("//AllData"),
    FILE_DATA("//data.txt");

    private String value;

    PathsInfo(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}