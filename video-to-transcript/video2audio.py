from audio_extract import extract_audio

# Extracting the video to audo script
extract_audio('full-video.mp4', './30-second-snippet.mp3', start_time="00:30", duration=30.0)