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
        password: null,
        repeatPassword: null,
        mail: null,
        pendingApiCall: null
    }

    OnFieldChange = (event) => {
        const {name, value} = event.target;
        this.setState({
            [name]: value
        });
    };


    render() {
        return ( 
        <div className="container">
            <h1 className="text-center">Register</h1>
            <form>
            {createInputField("Username", "username", "text", this.OnFieldChange)}
            {createInputField("E-Mail", "mail", "mail", this.OnFieldChange)}
            {createInputField("Password", "password", "password", this.OnFieldChange)}
            {createInputField("Repeat Password", "repeatPassword", "password", this.OnFieldChange)}
            <div className="text-center">
                <button className="btn btn-primary">
                    Register
                </button>
            </div>
            </form>
        </div>
        )
        ;
    }

}

export default UserRegisterPage;