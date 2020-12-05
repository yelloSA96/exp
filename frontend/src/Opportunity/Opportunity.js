import React from 'react';
import { Button } from 'reactstrap';
import { parseSkills } from './OpportunityHelper';
import InterviewForm from './InterviewForm';

class Opportunity extends React.Component {
  state = {
    isLoaded: false,
    showForm: false,
    opportunity: {
      position: null,
      skills: [],
    }
  };

  componentDidMount() {
    fetch('/api/opportunity')
      .then(res => res.json())
      .then(opportunities => this.setState({ isLoaded: true, opportunities }));
  }

  toggleModal = (opportunity) => {
    this.setState({ showForm: !this.state.showForm, opportunity });
  };

  render() {
    const { isLoaded, opportunities, showForm, opportunity } = this.state;

    if (!isLoaded)
      return <div>Loading...</div>;
    else
      return (
        <>
          <table className="table table-striped">
            <thead>
              <tr>
                <th>Client</th>
                <th>Position</th>
                <th>Skillsets</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {opportunities.map(opportunity =>
                <tr key={opportunity.id}>
                  <td>client name</td>
                  <td>{opportunity.position}</td>
                  <td>{parseSkills(opportunity.skills)}</td>
                  <td>
                    <Button color="primary" size="sm"
                      onClick={() => this.toggleModal(opportunity)}>Assign Interview
                    </Button>
                  </td>
                </tr>
              )}
            </tbody>
          </table>
          <InterviewForm show={showForm} toggle={this.toggleModal} data={opportunity} />
        </>
      );
  }
}

export default Opportunity;
