import unittest
from word2markdown import Document2Markdown

class TestDocument2Markdown(unittest.TestCase):
    def setUp(self):
        self.doc2md = Document2Markdown('test.docx')

    def test_getMarkdown(self):
        self.doc2md.getMarkdown()
        with open('output.md', 'r') as f:
            content = f.read()
        self.assertNotEqual(content, '')

    def test_fail_getMarkdown(self):
        with self.assertRaises(Exception):
            bad_doc2md = Document2Markdown('nonexistent.docx')
            bad_doc2md.getMarkdown()

if __name__ == '__main__':
    unittest.main()