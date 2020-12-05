import React, { Component } from 'react';
import { Button, Container, Col } from 'reactstrap';
import { Link } from 'react-router-dom';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import "bootstrap/dist/css/bootstrap.min.css";
import 'bootstrap/dist/css/bootstrap.css';

class viewTrainees extends Component {

  constructor(props) {
    super(props);
    this.state = { consultants: [], isLoading: true };
  }

  componentDidMount() {
    this.setState({ isLoading: true });
    fetch('/api/consultant')
      .then(response => response.json())
      .then(data => this.setState({ consultants: data, isLoading: false }));

  }

  render() {
    const { isLoading } = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    function buttonFormatter(cell) {
      console.log(cell);
      return <Button size="lg" block color="warning" tag={Link} to={"/schedule/" + cell} ><b>View</b></Button>;
    }

    return (
      <div>

        <Container fluid="xl" style={{ width: '40%' }}>
          <Col style={{ backgroundColor: 'lightblue', padding: '3%' }}>
            <h1 style={{ textDecoration: 'underline', textAlign:'center' }}><b>Consultants</b></h1>
            <BootstrapTable data={this.state.consultants} striped hover search tableStyle={{ background: 'SeaShell' }} headerStyle={{ background: 'LightSlateGrey' }}>
              <TableHeaderColumn headerAlign='center' dataAlign='center' dataField="name" dataSort={true}>Name</TableHeaderColumn>
              <TableHeaderColumn dataAlign='center' dataField="employeeID" isKey dataFormat={buttonFormatter}></TableHeaderColumn>
            </BootstrapTable>
          </Col>
        </Container>
      </div>
    );
  }
}
export default viewTrainees;