import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import FormValidator from './FormValidator';

class Edit extends Component {

  constructor(props) {
    super(props);
 
    this.state = {
      customer: {}	  
    };
  }
  componentDidMount() {
    axios.get('http://localhost:8080/customers/'+this.props.match.params.id)
      .then(res => {
        this.setState({ customer: res.data });
      });
  }

  onChange = (e) => {
    const state = this.state.customer
    state[e.target.name] = e.target.value;
    this.setState({customer:state});
  }

  onSubmit = (e) => {
    e.preventDefault();
		const { emailAddress, lastName, firstName,address } = this.state.customer;
		axios.put('http://localhost:8080/customers/'+this.props.match.params.id, { emailAddress, lastName, firstName,address })
		  .then((result) => {
			this.props.history.push("/list")
		  });
  }

  render() {	
    return (
      <div className="container">
        <div className="panel panel-default">
          <div className="panel-heading">
            <h3 className="panel-title">
              Edit Customer
            </h3>
          </div>
          <div className="panel-body">

            <form onSubmit={this.onSubmit}>
			  <div className="form-group">
                <label>Username:</label>
                <input type="email" className="form-control" name="emailAddress"  disabled='true' value={this.state.customer.emailAddress} />
              </div>
              <div className="form-group">
                <label>Last Name:</label>
                <input type="text" className="form-control" name="lastName" value={this.state.customer.lastName} onChange={this.onChange} placeholder="Last Name" />
              </div>
              <div className="form-group">
                <label>First Name:</label>
                <input type="text" className="form-control" name="firstName" value={this.state.customer.firstName} onChange={this.onChange} placeholder="First Name" />
              </div>
              <div className="form-group">
                <label>Address:</label>
                <input type="text" className="form-control" name="address" value={this.state.customer.address} onChange={this.onChange} placeholder="Address" />
              </div>
              <button type="submit" className="btn btn-success">Update</button>
            </form>
            <h4><Link to='/list/'> Back to Customer List</Link></h4>
          </div>
        </div>
      </div>
    );
  }
}

export default Edit;
