import docx
from markdown import markdown

# This class is intended to convert a document called "resume.doc" to "original-resume.md"

class Document2Markdown:
    def __init__(self, documentName):
        # Load the .docx file
        self.doc = docx.Document(documentName)


    def getMarkdown(self):
        # Extract the text from the document
        text = "\n".join([para.text for para in doc.paragraphs])

        # Convert the text to markdown
        md = markdown(text)

        # Write the markdown to a file
        with open("output.md", "w") as f:
            f.write(md)

