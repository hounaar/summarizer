import java.io.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class TextFileSummarizer {
    public static String extractTextFromPDF(String pdfFile) throws IOException {
        PDDocument document = PDDocument.load(new File(pdfFile));
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);
        document.close();
        return text;
    }

    public static String extractTextFromDocx(String docxFile) throws IOException {
        XWPFDocument document = new XWPFDocument(new FileInputStream(docxFile));
        StringBuilder text = new StringBuilder();
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            text.append(paragraph.getText());
            text.append("\n");
        }
        return text.toString();
    }

    public static String extractTextFromTxt(String txtFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(txtFile));
        StringBuilder text = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            text.append(line);
            text.append("\n");
        }
        reader.close();
        return text.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the path of the file (PDF, DOCX, or TXT): ");
        String input_file = reader.readLine();
        String file_extension = input_file.substring(input_file.lastIndexOf('.') + 1).toLowerCase();

        String text = null;
        switch (file_extension) {
            case "pdf":
                text = extractTextFromPDF(input_file);
                break;
            case "docx":
                text = extractTextFromDocx(input_file);
                break;
            case "txt":
                text = extractTextFromTxt(input_file);
                break;
            default:
                System.out.println("Unsupported file format");
        }

        if (text != null) {
            // Summarize the text here and print the summary
            String summary = summarizeText(text);
            System.out.println("Summary:");
            System.out.println(summary);
        }
    }

    public static String summarizeText(String text) {
        // Implement text summarization here and return the summary
        // You can use external libraries like Apache OpenNLP or CoreNLP for Java
        // Or use custom summarization techniques
        return text;
    }
}
