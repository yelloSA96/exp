from openai import OpenAI
import whisper

client = OpenAI(api_key="")

audio_file = open("30-second-snippet.mp3", "rb")

# transcription - Default
transcript = client.audio.transcriptions.create(
  model="whisper-1",
  file=audio_file
)

# transcription - timestamps
# transcript = client.audio.transcriptions.create(
#   file=audio_file,
#   model="whisper-1",
#   response_format="verbose_json",
#   timestamp_granularities=["word"]
# )

print(transcript.text)

# TODO: Output the text into a file as new lines
# from whisper.utils import get_writer
output_directory = "./"
# # Save as a TXT file without any line breaks
# with open("transcription.txt", "w", encoding="utf-8") as txt:
#     txt.write(transcript["text"])


# # Save as a TXT file with hard line breaks
# txt_writer = get_writer("txt", output_directory)
# txt_writer(transcript, audio)
