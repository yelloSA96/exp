from crewai import Agent, Crew, Process, Task
from crewai.project import CrewBase, agent, crew, task
from .tools.scrape_website import scrapeWebsiteTool
from .tools.Perplexity import Perplexity


@CrewBase
class RealestateCrew():
	"""Realestate crew"""
	agents_config = 'config/agents.yaml'
	tasks_config = 'config/tasks.yaml'

	@agent
	def researcher(self) -> Agent:
		return Agent(
			config=self.agents_config['researcher'],
			tools=[scrapeWebsiteTool(website_url='https://www.domain.com.au/auction-results/melbourne'),Perplexity()],
			verbose=True
		)

	@agent
	def real_estate_agent(self) -> Agent:
		return Agent(
			config=self.agents_config['real_estate_agent'],
			tools=[Perplexity()],
			verbose=True
		)

	@task
	def research_task(self) -> Task:
		return Task(
			config=self.tasks_config['research_task'],
			agent=self.researcher(),
		)

	@task
	def build_report(self) -> Task:
		return Task(
			config=self.tasks_config['reporting_task'],
			agent=self.real_estate_agent(),
			output_file='2-property-purchasing-report.md'
		)

	@crew
	def crew(self) -> Crew:
		"""Creates the Realestate crew"""
		return Crew(
			agents=self.agents, # Automatically created by the @agent decorator
			tasks=self.tasks, # Automatically created by the @task decorator
			process=Process.sequential,
			verbose=2,
		)