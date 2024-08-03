import requests

url = "https://api.perplexity.ai/chat/completions"

payload = {
    "model": "llama-3-sonar-small-32k-online",
    "messages": [
        {
            "role": "system",
            "content": "Be extensive in your explanation and not afraid to quote word by word from your references. At the bottom, reference the links you found your answers from. "
        },
        {
            "role": "user",
            "content": "Create a step by step guide on how to customise my sub linux module on windows computer?"
        }
    ]
}
headers = {
    "accept": "application/json",
    "content-type": "application/json",
    "authorization": "Bearer <API_HERE>"
}

response = requests.post(url, json=payload, headers=headers)
response_text = response.text
# print_this = response_text['choices'][0]['message']['content']
print(response_text)