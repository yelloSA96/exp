# Group Presentation on CrewAI 
## Introduction of the tool
- History
- Purpose

## How does it stack up against other Multi Agentic frameworks
### Perplexity Comparison
Here is a comparison of the CrewAI, Autogen, and Agent Swarm AI agent frameworks across the requested aspects:

| Aspect                                 | CrewAI                                                                                                                           | Autogen                                                                                                                   | Agent Swarm                                                                                                                       |
|----------------------------------------|----------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------|
| **Architecture and Design**            | Multi-agent framework built on LangChain. Agents have specific roles, background stories, goals, and memories. Modular design.   | Unified multi-agent conversation framework. Agents are conversable, customizable and integrate LLMs, tools, and humans.   | Decentralized swarm of specialized agents that collaborate. Agents focus on particular capabilities and knowledge sources.        |
| **Scalability and Performance**        | Designed to be scalable and flexible, accommodating new and dynamic agents. No specific benchmarks provided.                     | Simplifies orchestration, automation and optimization of complex LLM workflows. Maximizes LLM performance. No benchmarks. | Swarm architecture allows scaling by adding more specialized agents. No performance data available.                               |
| **Flexibility and Extensibility**      | Modular design makes it easy to add new agent types. No info on environment or tool integration.                                 | Agents can be customized to integrate LLMs, humans, tools, or a combination. Supports code execution.                     | Agents are specialized for particular skills, so new skills require new agents. No details on environment or tool integration.    |
| **Learning and Adaptation**            | No information provided on learning algorithms or adaptation capabilities.                                                       | Enables automated continual learning from new data to promote agent adaptability.                                         | No specifics on learning or adaptation techniques supported.                                                                      |
| **Ease of Use and Documentation**      | No information on learning curve, documentation, tutorials or community resources.                                               | Several notebook examples provided. Documentation available but comprehensiveness unknown.                                | No details on ease of use, documentation, or community resources.                                                                 |
| **Integration and Deployment**         | No information on integration or deployment options.                                                                             | No specific info on integration. Likely runs locally based on examples.                                                   | No details on integration or deployment provided.                                                                                 |
| **Community and Support**              | No information on developer community, commercial support, or update frequency.                                                  | Open source Microsoft project. Update frequency and commercial support unknown.                                           | No data on community activity, support options, or update cadence.                                                                |  
| **Use Cases and Applications**         | No notable projects or target industries mentioned.                                                                              | Wide potential applications like task coordination, human-machine collaboration, code generation, data viz, etc.          | Suitable for tasks requiring diverse skills beyond a single agent's capabilities. No specific projects listed.                    |
| **Strengths and Weaknesses**           | Strengths: Modular design, agent customization. Weaknesses: Lack of benchmarks and limited info on capabilities.                 | Strengths: Flexibility, LLM integration, built-in examples. Weaknesses: Unclear scalability and deployment.               | Strengths: Specialized agents, decentralized collaboration. Weaknesses: Adding skills requires new agents, limited documentation. |
| **Comparison**                         | Unique in being built on LangChain. Insufficient information to compare features, performance and ease of use.                   | Most comprehensive documentation and examples. Unique in supporting continual learning.                                   | Only one focused on decentralized swarm collaboration. Lacks comparisons on other aspects due to limited info.                    |

In summary, while all three frameworks support multi-agent collaboration, they differ in architecture, specialization, and target use cases. Autogen provides the most detailed information and usage examples, while CrewAI and Agent Swarm lack comprehensive documentation. 

The choice of framework depends on specific requirements:
- For LangChain integration, consider CrewAI
- For well-documented examples and continual learning, choose Autogen
- For decentralized swarm coordination on diverse tasks, explore Agent Swarm

Citations:
[1] https://www.crewai.com

[2] https://e2b.dev/ai-agents/crewai

[3] https://www.linkedin.com/pulse/comparative-analysis-multi-agentic-frameworks-crewai-vs-sai-panyam-vbmrc

[4] https://microsoft.github.io/autogen/docs/Use-Cases/agent_chat/

[5] https://blog.mlq.ai/building-ai-agents-autogen/

[6] https://microsoft.github.io/autogen/blog/2024/06/21/AgentEval/

[7] https://community.openai.com/t/agent-swarm-what-actually-is-the-point/578347

[8] https://ai.plainenglish.io/orchestrating-a-swarm-of-ai-agents-to-accomplish-complex-goals-a-theorical-approach-57241b614b46?gi=cf4b99a1e5c7

[9] https://www.sciencedirect.com/science/article/pii/S1877050913003682

### Personal Experience
I havn't touched CrewAI in a while but revising them, they had significantly added a few features/dedicated seperation. 
CrewAI - mostly for dynamic crews where you suggest the roles you wanting to entail. 
Autogen - Is a conversational approach where human interaction can be provided. 

## CrewAI main components 
- Agents
- Tools 
- Crew/execution 
- [NEW] Processes - Extension of hierarchy 
- [NEW] Training
- [NEW] Collaboration

## Examplers/Use Cases
- [Markdown validator]()
- [Personal AI Secretary](https://x.com/KaranVaidya6/status/1811836099771105399)
- [Recruiter](https://x.com/joaomdmoura/status/1809614136222818719)
- []
## How to keep up to date on developments
- Discord 
- [DeepLearning.ai](https://learn.deeplearning.ai/courses/multi-ai-agent-systems-with-crewai/lesson/1/introduction) 
- [Github Repository](https://github.com/crewAIInc/crewAI/tree/main)

## Q & A