import React, { Component } from 'react';
import { withRouter, Link } from 'react-router-dom';
import { Container, Row, Col, Button } from 'reactstrap';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import moment from 'moment'
class viewSchedule extends Component {


	constructor(props) {
		super(props);
		this.state = { schedule: [], isLoading: true, interviews: [], selfLearning: [] };
	}

	async componentDidMount() {
		this.setState({ isLoading: true });

		const id = this.props.match.params.id;
		await fetch("/api/schedule?consultant=" + id + "&type=interview")
			.then(response => response.json())
			.then(data => this.setState({ interviews: data }));
		await fetch("/api/schedule?consultant=" + id + "&type=selflearning")
			.then(response => response.json())
			.then(data => this.setState({ selfLearning: data, isLoading: false }));
	}

	render() {

		var options = {
			sortName: "date",  //default sort column name
			sortOrder: "asc",  //default sort order
		};

		const { isLoading } = this.state;

		if (isLoading) {
			return <p>Loading...</p>;
		}

		function dateFormatter(cell, row) {
			return moment(cell).format("DD-MM-YYYY");
		}

		function skillFormatter(cell, row) {
			return cell.name;
		}

		function skill(cell, row) {
			return cell.skills[0].name;
		}

		function position(cell, row) {
			return cell.position;
		}

		function skillSort(a, b, order) {
			console.log("Hi");
			if (order === "desc") {
				if (a.skill.name < b.skill.name) {
					return -1;
				}
				if (a.skill.name > b.skill.name) {
					return 1;
				}
			} else {
				if (a.skill.name < b.skill.name) {
					return 1;
				}
				if (a.skill.name > b.skill.name) {
					return -1;
				}
			}
			return 0;

		}

		return (
			<div>


				<Container fluid style={{ width: '90%' }}>
					<Row>

						<Col style={{ backgroundColor: 'lightgray', padding: '3%' }} >
							<Button color="outline-info" tag={Link} to={"/"}>Back</Button>
							<br /><br />
							<h2 class="sub-header" style={{ textDecoration: 'underline' }}><b>Self-Learning</b></h2>
							<div>
								<BootstrapTable data={this.state.selfLearning} options={options} striped hover search tableStyle={{ background: 'SeaShell' }} headerStyle={{ background: 'SlateGrey' }}>
									<TableHeaderColumn dataField="id" isKey dataSort={true} hidden>ID</TableHeaderColumn>
									<TableHeaderColumn dataField="date" dataFormat={dateFormatter} dataSort={true}>Date</TableHeaderColumn>
									<TableHeaderColumn dataField="content" dataSort={true}>Content</TableHeaderColumn>
									<TableHeaderColumn dataField="description" dataSort={true}>Description</TableHeaderColumn>
									<TableHeaderColumn dataField="skill" dataFormat={skillFormatter} dataSort={true} sortFunc={skillSort} filterFormatted >Skill</TableHeaderColumn>
								</BootstrapTable>
							</div>
						</Col>

						<Col style={{ backgroundColor: 'lightblue', padding: '3%' }}>
							<br /><br />
							<h2 class="sub-header" style={{ textDecoration: 'underline' }}><b>Interviews</b></h2>
							<div>
								<BootstrapTable data={this.state.interviews} striped hover search tableStyle={{ background: 'SeaShell' }} headerStyle={{ background: 'SteelBlue' }}>
									<TableHeaderColumn dataField="id" isKey dataSort={true} hidden>ID</TableHeaderColumn>
									<TableHeaderColumn dataField="date" dataFormat={dateFormatter} dataSort={true}>Date</TableHeaderColumn>
									<TableHeaderColumn dataField="content" dataSort={true}>Content</TableHeaderColumn>
									<TableHeaderColumn dataField="opportunity" dataFormat={position} dataSort={true}>Position</TableHeaderColumn>
									<TableHeaderColumn dataField="opportunity" dataFormat={skill} dataSort={true}>Skills</TableHeaderColumn>
								</BootstrapTable>
							</div>
						</Col>
					</Row>

				</Container>
			</div>
		);


	}

}
export default withRouter(viewSchedule);