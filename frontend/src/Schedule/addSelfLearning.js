import React, { Component } from 'react';

import { Button, Container, Form, Label, Input, FormGroup, Col, FormText } from 'reactstrap';

import AsyncCreatableSelect from 'react-select/async-creatable';
import '../App.css';
import moment from 'moment'

class addSelfLearning extends Component {


	constructor(props) {
		super(props);
		this.state = {
			dbSkills: [], isLoading: true, week: '', description: '', skill: null, date: '', errors: {
				week: '',
				description: '',
				skill: '',
				date: ''
			}, touched: { week: false, description: false, skill: false, date: false }, errorsFound: false
		};
		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
		this.handleSelect = this.handleSelect.bind(this);
		this.handleBlur = this.handleBlur.bind(this);

	}

	async componentDidMount() {
		this.setState({ isLoading: true });
		await fetch(`/api/skill`)
			.then(response => response.json())
			.then(data => this.setState({ dbSkills: data, isLoading: false }));
	}

	handleBlur = (field) => (evt) => {
		console.log("HIIII");
		this.setState({
			touched: { ...this.state.touched, [field]: true }
		});
	}

	handleSubmit(event) {
		if (this.state.week.length < 1 || this.state.description.length < 1 || this.state.skill === null || this.state.date.length < 1) {
			this.setState({
				touched: { week: true, description: true, skill: true, date: true }
			});
			event.preventDefault();
		} else {


			event.preventDefault();
			alert(JSON.stringify({

				"content": this.state.week,

				"description": this.state.description,

				"skill": this.state.skill,

				"dateToBeDone": this.state.date

			}));

			 fetch("/api/schedule/selflearning", {
			 	method: 'post',
			 	headers: { 'Content-Type': 'application/json' },
			 	body: JSON.stringify({
			 		"content": this.state.week,
					"description": this.state.description,
					"skill": this.state.skill,
					"consultantId": 1,
					"dateToBeDone": this.state.date
				})
			});
		}
	}

	handleChange(event) {
		this.setState({
			[event.target.name]: event.target.value
		});
	}

	handleSelect(event) {
		console.log(event.value);
		this.setState({
			skill: event.value
		});
	}

	validate() {

		if (this.state.touched.week && this.state.week.length < 1)
			this.state.errors.week = "Please describe what you are doing this week!";
		else this.state.errors.week = "";
		if (this.state.touched.description && this.state.description.length < 1)
			this.state.errors.description = "Please enter a description!";
		else this.state.errors.description = "";
		if (this.state.touched.skill && this.state.skill === null)
			this.state.errors.skill = "Please select or create a skill!";
		else this.state.errors.skill = "";
		if (this.state.touched.date && this.state.date.length < 1)
			this.state.errors.date = "Please Select a date!";
		else this.state.errors.date = "";
		return this.state.errors;
	}


	render() {
		const errors = this.validate(this.state.week, this.state.description, this.state.skill, this.state.date)
		const { dbSkills, isLoading } = this.state;

		if (isLoading) {
			return <p>Loading...</p>;
		}

		const skills = dbSkills.map(group => {
			return { value: group.name, label: group.name };
		});

		const filterColors = (inputValue) => {
			return skills.filter(i =>
				i.label.toLowerCase().includes(inputValue.toLowerCase())
			);
		};

		const promiseOptions = inputValue =>
			new Promise(resolve => {
				setTimeout(() => {
					resolve(filterColors(inputValue));
				}, 1000);
			});

		const today = new Date();

		return (
			<div>

				<link rel="stylesheet" href="https://npmcdn.com/react-bootstrap-table/dist/react-bootstrap-table-all.min.css"></link>
				<Container fluid className="App" style={{ width: '50%' }}>

					<Form onSubmit={this.handleSubmit} className="form" style={{ backgroundColor: 'BurlyWood', padding: '3%' }}>
						<h1 style={{ textDecoration: 'underline' }}><b>Pond Form!</b></h1>
						<br />
						<Col>
							<FormGroup className="selfLearningGroups">
								<Label for="week" className="labelSelfLearning">What will you be doing?</Label>
								<Input
									type="textarea"
									name="week"
									id="week"
									value={this.state.week}
									onChange={this.handleChange}
									valid={errors.week === '' && this.state.week.length > 0}
									invalid={errors.week !== ''}
									onBlur={this.handleBlur('week')}
									placeholder="What's your plan for this week?"
								/>
								<p style={{ color: 'white', backgroundColor: 'red' }}>{errors.week}</p>
							</FormGroup>
						</Col>
						<Col>
							<FormGroup className="selfLearningGroups">
								<Label for="skill" className="labelSelfLearning">What Skill will you be learning/improving upon?</Label>
								<FormText color="white">Tip: Add Skill by typing!</FormText>
								<AsyncCreatableSelect
									cacheOptions
									defaultOptions
									name="skill"
									onChange={this.handleSelect}
									loadOptions={promiseOptions}
									onBlur={this.handleBlur('skill')}
									placeholder="Please select or add skill"
								/>
								<p style={{ color: 'white', backgroundColor: 'red' }}>{errors.skill}</p>
							</FormGroup>
						</Col>
						<Col>
							<FormGroup className="selfLearningGroups">
								<Label for="description" className="labelSelfLearning">How do you plan on learning this skill?</Label>
								<Input
									type="textarea"
									name="description"
									id="description"
									value={this.state.description}
									onChange={this.handleChange}
									valid={errors.description === '' && this.state.description.length > 0}
									invalid={errors.description !== ''}
									placeholder="How will you learn this skill?"
									onBlur={this.handleBlur('description')}

								/>
								<p style={{ color: 'white', backgroundColor: 'red' }}>{errors.description}</p>
							</FormGroup>
						</Col>
						<Col>

							<FormGroup className="selfLearningGroups">
								<Label for="week" className="labelSelfLearning">When do you plan on finishing this task?</Label>
								<Input
									type="date"
									name="date"
									id="date"
									value={this.state.date}
									onChange={this.handleChange}
									min={moment({ today }).format('YYYY-MM-DD')}
									valid={errors.date === '' && this.state.date.length > 0}
									invalid={errors.date !== ''}
									onBlur={this.handleBlur('date')}
								/>
								<p style={{ color: 'white', backgroundColor: 'red' }}>{errors.date}</p>
							</FormGroup>
						</Col>
						<Button outline color="success" size="lg" block >Submit</Button>{' '}
					</Form>
				</Container>
			</div>
		);


	}

}
export default addSelfLearning;


