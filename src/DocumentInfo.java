import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class DocumentInfo {
    private String address;
    private String tag;
    private int limit;

    public DocumentInfo(String address, String tag, int limit) {
        this.address = address;
        this.tag = tag;
        if (limit <= 0) {
            throw new RuntimeException("The limit must be greater than 0");
        }
        this.limit = limit;
    }

}


