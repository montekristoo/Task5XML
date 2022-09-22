public enum PathsInfo {
        ROOT_PATH(System.getProperty("user.home") + "//Desktop//Data"),
        MANAGER_FILE("//manager.txt"),
        MANAGER_FOLDER("//Manager"),
        ROOT_PAGE("https://makeup.md/sitemap/sitemap.xml"),
        ROOT_TAG("sitemap"),
        BREAKPOINT("url"),
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

