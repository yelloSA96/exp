import React, { useState, useEffect } from 'react';
import { Redirect } from 'react-router-dom';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import moment from 'moment';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

import { parseSkills } from './OpportunityHelper';

function InterviewForm(props) {
  const [redirect, setRedirect] = useState(false);
  const [consultants, setConsultants] = useState([]);
  const [consultant, setConsultant] = useState(null);
  const [type, setType] = useState(null);
  const [location, setLocation] = useState(null);
  const [date, setDate] = useState(null);
  const [time, setTime] = useState(null);
  const interviewType = ['Face-to-face', 'Group', 'Telephone', 'Video Call'];

  // same as componentDidMount
  useEffect(() => {
    fetch('/api/consultant')
      .then((res) => res.json())
      .then((consultants) => setConsultants(consultants));
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();

    // validate consultant ID
    if (!consultant.includes(':') ||
      !Number.isInteger(parseInt(consultant.split(':')[0]))
    ) {
      alert('invalid consultant format');
      return;
    }

    // send data to backend
    fetch('/api/schedule/interview', {
      method: 'post',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        'opportunityId': props.data.id,
        'consultantId': consultant.split(':')[0],
        'type': type,
        'location': location,
        'date': moment(date).format('YYYY-MM-DD'),
        'time': moment(time).format('hh:mm a'),
      }),
    }).then(res => {
      if (res.ok) {
        setRedirect(true);
      }
    });
  };

  const renderGreyField = (fieldName, value) => {
    return (
      <>
        <label htmlFor={fieldName}>{fieldName}:</label>
        <input type="text" name={fieldName} value={value} disabled />
      </>
    );
  };

  if (redirect) {
    return <Redirect to="/" />;
  }
  return (
    <Modal isOpen={props.show}>
      <ModalHeader toggle={props.toggle}>Assign Interview</ModalHeader>
      <ModalBody>
        <form id="interview-form" onSubmit={e => handleSubmit(e)}>
          {renderGreyField('Client', 'client name')}
          {renderGreyField('Position', props.data.position)}
          {renderGreyField('Skill', parseSkills(props.data.skills))}

          <label>Consultant:</label>
          <span className="w-100">
            <input
              className="w-100"
              type="text"
              list="consultants"
              onChange={e => setConsultant(e.target.value)}
              autoFocus
              required
            />
            <datalist id="consultants">
              {consultants.map(({ employeeID, name }) => (
                <option key={employeeID} value={`${employeeID}: ${name}`} />
              ))}
            </datalist>
          </span>

          <label>Type:</label>
          <span className="w-100">
            <input
              className="w-100"
              type="text"
              list="interview-type"
              onChange={e => setType(e.target.value)}
              required
            />
            <datalist id="interview-type">
              {interviewType.map(type => (
                <option key={type} value={type} />
              ))}
            </datalist>
          </span>

          <label>Location:</label>
          <input type="text" onChange={e => setLocation(e.target.value)} required />

          <label>Date:</label>
          <span>
            <DatePicker
              selected={date}
              onChange={date => setDate(date)}
              minDate={new Date()}
              required
            />
          </span>

          <label>Time:</label>
          <span>
            <DatePicker
              selected={time}
              onChange={time => setTime(time)}
              showTimeSelect
              showTimeSelectOnly
              timeIntervals={15}
              timeCaption="Time"
              dateFormat="hh:mm aa"
              required
            />
          </span>
        </form>
      </ModalBody>
      <ModalFooter>
        <Button color="primary" type="submit" form="interview-form">Submit</Button>
        <Button color="secondary" onClick={props.toggle}>Cancel</Button>
      </ModalFooter>
    </Modal>
  );
}

export default InterviewForm;
