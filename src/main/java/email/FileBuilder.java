package email;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.*;

@Dependent
public class FileBuilder implements Serializable {
    @Inject
    MailContents mailContents;

    public void createFile() throws FileNotFoundException, UnsupportedEncodingException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("OrderDetails.pdf"));
        document.open();
        document.add(new Paragraph(mailContents.buildMessage()));
        document.close();;

    }
}
