import axios from "axios";
import React from "react";


function createInputField(labelText, fieldName, inputType, onChangeCallback) {
    return (
        <div className="form-group">
            <label>{labelText}</label>
            <input className="form-control" name={fieldName} type={inputType} onChange={onChangeCallback}></input>
        </div>
    );
}

class UserRegisterPage extends React.Component {

    state = {
        username: null,
        displayName: null,
        mail: null,
        password: null,
        repeatPassword: null,
        pendingApiCall: null
    }

    onFieldChange = (event) => {
        const {name, value} = event.target;
        this.setState({
            [name]: value
        });
    };

    onRegister = event => {
        event.preventDefault();

        const { username, displayName, mail, password, repeatPassword } = this.state;

        if(repeatPassword !== password) {
            return;
        }

        const reqBody = {
            username,
            displayName,
            mail,
            password
        };

        this.setState({ pendingApiCall: true });
        
        axios
            .post("/api/1.0/users/register", reqBody)
            .then(reponse => {
                this.setState({ pendingApiCall: false });
            })
            .catch(error => {
                this.setState({ pendingApiCall: false });
            });

    };


    render() {
        const { pendingApiCall } = this.state;
        return ( 
        <div className="container">
            <h1 className="text-center">Register</h1>
            <form>
            {createInputField("Username", "username", "text", this.onFieldChange)}
            {createInputField("Display Name", "displayName", "text", this.onFieldChange)}
            {createInputField("E-Mail", "mail", "mail", this.onFieldChange)}
            {createInputField("Password", "password", "password", this.onFieldChange)}
            {createInputField("Repeat Password", "repeatPassword", "password", this.onFieldChange)}
            <div className="text-center">
                <button disabled={pendingApiCall} className="btn btn-primary" onClick={this.onRegister}>
                    Register
                </button>
            </div>
            </form>
        </div>
        );
    }

}

export default UserRegisterPage;