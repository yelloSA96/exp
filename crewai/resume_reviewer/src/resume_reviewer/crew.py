from crewai import Agent, Crew, Process, Task
from crewai.project import CrewBase, agent, crew, task
from crewai_tools.tools.file_read_tool.file_read_tool import FileReadTool
from crewai_tools.tools.scrape_website_tool.scrape_website_tool import ScrapeWebsiteTool

# Uncomment the following line to use an example of a custom tool
# from resume_reviewer.tools.custom_tool import MyCustomTool
from crewai_tools.tools.serper_dev_tool.serper_dev_tool import SerperDevTool


# Check our tools documentations for more information on how to use them
# from crewai_tools import SerperDevTool

@CrewBase
class ResumeReviewerCrew():
	"""ResumeReviewer crew"""
	agents_config = 'config/agents.yaml'
	tasks_config = 'config/tasks.yaml'

	@agent
	def tech_researcher(self) -> Agent:
		return Agent(
			config=self.agents_config['tech_researcher'],
			tools=[ScrapeWebsiteTool(), SerperDevTool()],
			verbose=True
		)

	@agent
	def personal_profiler(self) -> Agent:
		return Agent(
			config=self.agents_config['personal_profiler'],
			tools=[ScrapeWebsiteTool(), SerperDevTool()],
			verbose=True
		)

	@agent
	def resume_strategist(self) -> Agent:
		return Agent(
			config=self.agents_config['resume_strategist'],
			tools=[ScrapeWebsiteTool(), SerperDevTool(), FileReadTool(file_path='./data/resume.md')],
			verbose=True
		)

	@agent
	def interview_preparer(self) -> Agent:
		return Agent(
			config=self.agents_config['interview_preparer'],
			tools=[ScrapeWebsiteTool(), SerperDevTool(), FileReadTool(file_path='./data/resume.md')],
			verbose=True
		)

	@task
	def research_task(self) -> Task:
		return Task(
			config=self.tasks_config['research_task'],
			async_execution=True,
			agent=self.tech_researcher()
		)

	@task
	def profile_task(self) -> Task:
		return Task(
			config=self.tasks_config['profile_task'],
			async_execution=True,
			agent=self.personal_profiler()
		)

	@task
	def resume_strategy_task(self) -> Task:
		return Task(
			config=self.tasks_config['resume_strategy_task'],
			agent=self.resume_strategist(),
			output_file='tailored_resume.md',
			context=[self.research_task(), self.profile_task()]
		)

	@task
	def interview_preparation_task(self) -> Task:
		return Task(
			config=self.tasks_config['interview_preparation_task'],
			agent=self.interview_preparer(),
			output_file='interview_materials.md',
			context=[self.research_task(), self.profile_task(), self.resume_strategy_task()]
		)

	@crew
	def crew(self) -> Crew:
		"""Creates the ResumeReviewer crew"""
		return Crew(
			agents=self.agents, # Automatically created by the @agent decorator
			tasks=self.tasks, # Automatically created by the @task decorator
			process=Process.sequential,
			verbose=2,
			# process=Process.hierarchical, # In case you wanna use that instead https://docs.crewai.com/how-to/Hierarchical/
		)