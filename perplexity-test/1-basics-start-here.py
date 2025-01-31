import os

from openai import OpenAI

PERPLEXITY_API_KEY = os.getenv("PERPLEXITY_API_KEY")

messages = [
    {
        "role": "system",
        "content": (
            "You are an expert knowledge researcher who cross-correlates "
            "references to other sourcecs to investigate ideas."
        ),
    },
    {
        "role": "user",
        "content": (
            "Context: I'm looking into digital marketing analytics and came across this website. site:https://www.prescient-ai.io. "
            "User: Could you breakdown how their strategy of using AI in their system and what AI analytics do they measure?"
        ),
    },
]

client = OpenAI(api_key=PERPLEXITY_API_KEY, base_url="https://api.perplexity.ai")

def chat_completion_non_streaming():
    # chat completion (non-streaming)
    response = client.chat.completions.create(
        model="mistral-7b-instruct",
        messages=messages,
    )
    return response

def chat_completion_streaming():
    # chat completion ( streaming)
    response_stream = client.chat.completions.create(
        model="llama-3-sonar-large-32k-online",
        messages=messages,
        stream=True,
    )
    return response_stream
# If called directly from the command line take the first argument as the filename
if __name__ == "__main__":
    print(chat_completion_non_streaming())
    response_stream = chat_completion_streaming()
    for response in response_stream:
        print(response)
