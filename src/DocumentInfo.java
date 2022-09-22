import lombok.Getter;
import lombok.Setter;

import java.io.BufferedWriter;

@Getter
@Setter
public class DocumentInfo {
    private String address;
    private String tag;
    private BufferedWriter writer;

    public DocumentInfo(String address, String tag, BufferedWriter writer) {
        this.address = address;
        this.tag = tag;
        this.writer = writer;
    }

}
