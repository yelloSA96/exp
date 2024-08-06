# Group Presentation on CrewAI 
## Introduction of the tool
- History
  - Basically started off as an experiment in October 27th of 2023. 
  - Uses langchain under the hood 
  - Became a bit more of an online sensation 
- Purpose
  - Creating different crews for different tasks 

## How does it stack up against other Multi Agentic frameworks
### Perplexity Comparison
Here is a comparison of the CrewAI, Autogen, and Agent Swarm AI agent frameworks across the requested aspects:

| Aspect                             | CrewAI                                                                                                                         | Autogen                                                                                                                                                               | Agent Swarm                                                                                                                                              |
|------------------------------------|--------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Architecture and Design**        | Multi-agent framework built on LangChain. Agents have specific roles, background stories, goals, and memories. Modular design. | Unified multi-agent conversation framework. Agents are conversable, customizable and integrate LLMs, tools, and humans in the loop. Ideally for conversational tasks. | Decentralized swarm of specialized agents that collaborate. Agents focus on particular capabilities and knowledge sources.                               |
| **Scalability and Performance**    | Designed to be scalable and flexible, accommodating new and dynamic agents. No specific benchmarks provided.                   | Simplifies orchestration, automation and optimization of complex LLM workflows. Maximizes LLM performance. Uncertain of scalability.                                  | Swarm architecture allows scaling by adding more specialized agents. No performance data available.                                                      |
| **Flexibility and Extensibility**  | Modular design makes it easy to add new agent types. No info on environment or tool integration.                               | Agents can be customized to integrate LLMs, humans, tools, or a combination. Currently there is a GUI interaction that can be used craft workflows.                   | Agents are specialized for particular skills, so new skills require new agents. No details on environment or tool integration.                           |
| **Learning and Adaptation**        | Online courses out there at the moment and attempt some plays at it.                                                           | The learning is not steep with plentiful of examples on their website to learn about the different approaches.                                                        | Unsure about this.                                                                                                                                       |
| **Ease of Use and Documentation**  | There is a bit of a learning curve with this tool, documentation was created by crewAI, no tutorials.                          | Several notebook examples provided. Documentation available.                                                                                                          | Looks to be well documented and well designed.                                                                                                           |
| **Integration and Deployment**     | The developer & founders are growing the community around crewai and plugging in many tools. I.e. Perplexity and langchain.    | Unsure of this.                                                                                                                                                       | Unsure about this.                                                                                                                                       |
| **Community and Support**          | There is a developer community and growing part with frequency updates.                                                        | Open source Microsoft project. Updates are slow and belief it will be commercialised and support provided. I havn't interacted with this much.                        | Looks to not really open sourced more of an open project for the creator. Where selling their service to leverage the product.                           |  
| **Use Cases and Applications**     | The use cases                                                                                                                  | Wide potential applications like task coordination, human-machine collaboration, code generation.                                                                     | Suitable for tasks requiring diverse skills beyond a single agent's capabilities.                                                                        |
| **Strengths and Weaknesses**       | Strengths: Modular design, agent customization. <br>  Weaknesses: Lack of benchmarks and consistency. It also has **telemetry**.   | Strengths: Flexibility, LLM integration, built-in examples to learn quickly. <br/> Weaknesses: Unclear scalability and deployment.                                    | Strengths: Specialized agents, decentralized collaboration. Production grade. <br> Weaknesses: Adding skills requires new agents, limited documentation. |

In summary, while all three frameworks support multi-agent collaboration, they differ in architecture, specialization, and target use cases. Autogen provides the most detailed information and usage examples, while CrewAI and Agent Swarm lack comprehensive documentation. 

The choice of framework depends on specific requirements:
- For LangChain integration and leverage, consider CrewAI
- For well-documented examples and continual growth with the possibility of being used in commercial environment due to backing of Microsoft, choose Autogen
- For decentralized and specific niching on diverse tasks with productionising explore Agent Swarm

### Personal Experience
I havn't touched CrewAI in a while but revising them, they had significantly added a few features/dedicated sseparation.  A bit of debugging to be had with this if you're not a medium level. 
CrewAI - mostly for dynamic crews where you suggest the roles you wanting to entail. 
Autogen - Is a conversational approach where human interaction can be provided. 
Agent Swarm - Not used yet. 

## CrewAI main components 
- Agents
- Tools 
- Crew/execution 
- [NEW] Processes - Extension of hierarchy 
- [NEW] Training
- [NEW] Collaboration
- CLI - Making it easier to setup boiler plate code for your crews

## Examplers/Use Cases
- [Markdown validator](here)
- [Personal AI Secretary](https://x.com/KaranVaidya6/status/1811836099771105399)
- [Recruiter](https://x.com/joaomdmoura/status/1809614136222818719)
- [Property Analyser - P.O.C.](here)
- [Resume Reviewer]()
- [Food Planner]()
## How to keep up to date on developments
- Discord server Access for help
- [DeepLearning.ai](https://learn.deeplearning.ai/courses/multi-ai-agent-systems-with-crewai/lesson/1/introduction) 
- [Github Repository](https://github.com/crewAIInc/crewAI/tree/main)
- [Future - CrewAI Certified](http://www.crewai.com)
## Q & A


## Findings from the Deep Learning AI - CrewAI Course 

### Overview

Overview of the elements include 
- Role Playing - LLM perform better when they are role playing. Use special key words to illustrate the way it should think. 
- Focus - use multiple agents where better results with 1 agent to 1 tool 
- Tools - providing them the key tools as if you were going to hire them. 
- Cooperation - Layouts of these agents
- Guardrails - stay on track especially open source models. 
- Memory - long term(after tasks completed), shorterm(execution of a task), entity 

Key Elements of agent tools
- Versatility - Capable of handling multiple types of inputs  
- Fault Tolerance - If the tool isn't able to get the right output, it can retry or heal from it 
- Caching - Offers cross-agent caching (If different agent's trying to get same info, able to efficiently save time & resources)

Manager Mental framework
Overview Q
- What is the goal?
- What is the process?
[People] What kind of individual would I need to hire to get the job done?
- Which processes and tasks do I expect the individual on my team to do?
  - breaking up into sub tasks
Each task considerations:
- Clear description
- set a clear and concise expectation of the outcome i.e. You've just hired a junior engineer and you need to create a clear outcome of what they're doing. 
- set context
- set a callback
- override agent tools with specific task tools
- force human input before end of task
- execute asynchronously 
- output as pydantic
- output as json
- output as file
- run in parallel

Multi-frameowkr hierarchies
- sequential
- managerial format 

### Tips
- So by default, the crew is performing sequentially 
- Use '' instead of '''''' multilined
- Think like a manager - Ask Q: What is the Goal?, Q: What is the process?, Q: What kind of people would I need to hire to get this done? -> translates into crew ai frameworks 
What are the keywords that better hone on a personality 
- Langchain tools can be used by crew ai
