package Project;

import java.io.IOException;

public class KisiBulunamadiException extends IOException {
    public KisiBulunamadiException(String errmsg, Throwable err) {
        super(errmsg, err);
    }

    public KisiBulunamadiException(String errmsg) {
        super(errmsg);
    }
}
