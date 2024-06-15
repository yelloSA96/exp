import os
from dotenv import load_dotenv, find_dotenv
from langchain_community.llms import Ollama
# these expect to find a .env file at the directory above the lesson.
def load_env():
    _ = load_dotenv(find_dotenv())

def get_openai_api_key():
    load_env()
    openai_api_key = os.getenv("OPENAI_API_KEY")
    return openai_api_key

def get_serper_api_key():
    load_env()
    serper_api = os.getenv("SERPER_API_KEY")
    return serper_api

def get_llama3():
    return ChatOpenAI(
    model = "llama3",
    base_url = "http://localhost:11434/v1")