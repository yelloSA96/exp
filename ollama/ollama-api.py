from typing import List, Dict, Any

import requests

def data(prompt: str) -> dict[str, str ]:
    return ''{
        'model': 'llama3',
        'prompt': prompt
    }''

def fetch_data_from_api(url):
    try:
        response = requests.post(url,data("Why is the sky blue?"))
        response.raise_for_status()  # Raise an error for bad status codes
        data = response.json()  # Parse JSON response
        return data
    except requests.exceptions.HTTPError as http_err:
        print(f"HTTP error occurred: {http_err}")
    except Exception as err:
        print(f"Other error occurred: {err}")




if __name__ == "__main__":
    api_url = "http://localhost:11434/api/generate"
    result = fetch_data_from_api(api_url)
    if result:
        print(result)