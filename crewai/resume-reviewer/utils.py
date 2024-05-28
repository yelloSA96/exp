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
    openai_api_key = os.getenv("SERPER_API_KEY")
    return openai_api_key

def get_llama3():
    llm = Ollama(model="llama3")
    return llm

def get_embedding():
    return dict(
        llm=get_llama3(),
        embedder=dict(
            provider="ollama",  # or openai, ollama, ...
            config=dict(
                model="models/embedding-001",
                task_type="retrieval_document",
                # Optional title for the embeddings can be added here.
                # title="Embeddings",
            ),
        ),
    )