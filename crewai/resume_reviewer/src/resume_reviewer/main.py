#!/usr/bin/env python
import sys

from dotenv import load_dotenv

from resume_reviewer.crew import ResumeReviewerCrew

# This main file is intended to be a way for your to run your
# crew locally, so refrain from adding necessary logic into this file.
# Replace with inputs you want to test with, it will automatically
# interpolate any tasks and agents information

def run():
    """
    Run the crew.
    """
    job_application_inputs = {
        'job_posting_url': 'https://www.linkedin.com/jobs/view/3982875041/',
        'github_url': 'https://github.com/yelloSA96',
        'personal_writeup': """A devoted traditional engineer turned software. 
        Currently, my aspirations is to creating tools and create automated workflow that are refined for my stakeholders (devs & support).
        I also have a deep passion for sharing knowledge and learning challenging skills ensuring me and my team are constantly growing."""
    }
    load_dotenv()
    ResumeReviewerCrew().crew().kickoff(inputs=job_application_inputs)


def train():
    """
    Train the crew for a given number of iterations.
    """
    inputs = {
        "topic": "AI LLMs"
    }
    try:
        ResumeReviewerCrew().crew().train(n_iterations=int(sys.argv[1]), inputs=inputs)

    except Exception as e:
        raise Exception(f"An error occurred while training the crew: {e}")

def replay():
    """
    Replay the crew execution from a specific task.
    """
    try:
        ResumeReviewerCrew().crew().replay(task_id=sys.argv[1])

    except Exception as e:
        raise Exception(f"An error occurred while replaying the crew: {e}")
