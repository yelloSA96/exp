import os
from typing import Any, Type
from crewai_tools import BaseTool
import requests
from pydantic import BaseModel, Field


class PerplexityToolSchema(BaseModel):
	"""Input for PerplexityTool."""
	search_query: str = Field(..., description="Mandatory search query you want to use to search the internet")

class Perplexity(BaseTool):
    name: str = "perplexity"
    description: str = (
        "A tool that can search the internet with a search_query and answer a question in a paragraph."
    )
    args_schema: Type[BaseModel] = PerplexityToolSchema
    messages: list = [
        {
            "role": "system",
            "content": (
                "You are an expert knowledge researcher who cross-correlates references to other sources to craft an answer to a question."
            ),
        }
    ]

    def __init__(self,  **data: Any):
        super().__init__(**data)

    def User(self, prompt: str):
        return {
            "role":"user",
            "content": prompt
        }

    def System(self, prompt: str):
        return {
            "role":"system",
            "content": prompt
        }

    def chat_completion_non_streaming(self):
        """'chat completion (non-streaming)"""
        url = "https://api.perplexity.ai/chat/completions"
        payload = {
            "model": "llama-3.1-sonar-large-128k-online",
            "messages": self.messages
        }
        headers = {
            "accept": "application/json",
            "content-type": "application/json",
            "authorization": f"Bearer {os.getenv('PERPLEXITY_API_KEY')}"
        }
        try:
            response = requests.post(url, json=payload, headers=headers)
            response.raise_for_status()  # Raise an error for bad status codes
            if response.content:
                return response.json()
            else:
                print("Empty response content")
                return None
        except requests.exceptions.RequestException as e:
            print(f"An error occurred: {e}")
            return None
        except ValueError as e:
            print(f"JSON decoding failed: {e}")
            print(f"Response content: {response.text}")
            return None

    def _run(self, **kwargs: Any) -> str:
        search_query = kwargs.get('search_query') or kwargs.get('query')
        if search_query is not None:
            self.messages.append(self.User(search_query))
        else:
            return None
        api_response = self.chat_completion_non_streaming()
        if api_response and 'choices' in api_response:
            return api_response['choices'][0]['message']['content']
        else:
            raise ValueError(f"API response does not contain 'choices': {api_response}")

if __name__ == "__main__":
    tool = Perplexity()
    print(tool.run(search_query="Glen Waverley property market trends August 2024"))