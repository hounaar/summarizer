import PyPDF2
from docx import Document
import gensim.summarization

def extract_text_from_pdf(pdf_file):
    text = ""
    with open(pdf_file, "rb") as pdf_file:
        pdf_reader = PyPDF2.PdfFileReader(pdf_file)
        for page_num in range(pdf_reader.getNumPages()):
            page = pdf_reader.getPage(page_num)
            text += page.extractText()
    return text

def extract_text_from_docx(docx_file):
    doc = Document(docx_file)
    text = ""
    for paragraph in doc.paragraphs:
        text += paragraph.text + "\n"
    return text

def extract_text_from_txt(txt_file):
    with open(txt_file, "r") as txt_file:
        text = txt_file.read()
    return text

def summarize_text(text):
    summary = gensim.summarization.summarize(text)
    return summary

if __name__ == "__main":
    input_file = input("Enter the path of the file (PDF, DOCX, or TXT): ")

    file_extension = input_file.split(".")[-1].lower()

    if file_extension == "pdf":
        text = extract_text_from_pdf(input_file)
    elif file_extension == "docx":
        text = extract_text_from_docx(input_file)
    elif file_extension == "txt":
        text = extract_text_from_txt(input_file)
    else:
        print("Unsupported file format")
        text = ""

    if text:
        summary = summarize_text(text)

        # Print the summary
        print("Summary:")
        print(summary)
