import React, { Component } from 'react';
import { Button, Container, Form } from 'reactstrap';
import "bootstrap/dist/css/bootstrap.min.css";
import 'bootstrap/dist/css/bootstrap.css';
import AsyncSelect from 'react-select/async';

class removeSkills extends Component {

    constructor(props) {
        super(props);
        this.state = { skills: [], isLoading: true, values: [] };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
    }

    async componentDidMount() {
        this.setState({ isLoading: true });
        await fetch("/api/skill")
            .then(response => response.json())
            .then(data => this.setState({ skills: data, isLoading: false }));
    }

    handleSubmit(event) {
        event.preventDefault();
        if (this.state.values !== null) {
            this.state.values.forEach(element => {
                console.log(element.value);
                fetch("/api/skill?name="+ element.value, {
                    method: 'delete'    
               });
            });
        }

    }

    handleInputChange(values) {
        this.setState({ ...this.state, values });
    }

    render() {
        const { skills, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const skillOptions = skills.map(group => {
            return { value: group.name, label: group.name };
        });

        const filterSkills = (inputValue) => {
            return skillOptions.filter(i =>
                i.label.toLowerCase().includes(inputValue.toLowerCase())
            );
        };

        const promiseOptions = inputValue =>
            new Promise(resolve => {
                setTimeout(() => {
                    resolve(filterSkills(inputValue));
                }, 1000);
            });

        return (
            <div>
                <Container>
                    <Form onSubmit={this.handleSubmit} className="form" style={{ backgroundColor: 'BurlyWood', padding: '3%' }}>
                        <AsyncSelect
                            isMulti
                            cacheOptions
                            defaultOptions
                            loadOptions={promiseOptions}
                            onChange={this.handleInputChange}
                            value={this.state.values}
                        />
                        <Button outline color="success" size="lg" block >Submit</Button>{' '}
                    </Form>
                </Container>

            </div>
        );
    }
}
export default removeSkills;