from crewai_tools import ScrapeWebsiteTool

def scrapeWebsiteTool(website_url):
    return ScrapeWebsiteTool(website_url=website_url)
