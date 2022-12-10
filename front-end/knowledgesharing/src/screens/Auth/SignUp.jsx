import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import authApi from '../../apis/authApi';
// import PropTypes from 'prop-types';

// ScreenAuthSignUp.propTypes = {
    
// };

function ScreenAuthSignUp(props) {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [retypePassword, setRetypePassword] = useState("");

    let navigate = useNavigate();

    const changeUsernameInputValue = (e) => {
        setUsername(e.target.value);
    }
    const changePasswordInputValue = (e) => {
        setPassword(e.target.value);
    }
    const changeRetypePasswordInputValue = (e) => {
        setRetypePassword(e.target.value);
    }

    const validationForm = () => {
        let returnData = {
            error: false,
            msg: ""
        }

        // const {username, password, retypePassword} = this.state;

        const re = /\S/;
        if(!re.test(username)) {
            returnData = {
                error: true,
                msg: 'Not match username format'
            }
        }

        // Kiểm tra password dài ít nhất 8 ký tự và Retype password khớp với password
        if(password.length < 8) {
            returnData = {
                error: true,
                msg: "Length of password must be greater than 8 character"
            }
        } else if(password !== retypePassword) {
            returnData = {
                error: true,
                msg: "Password not match"
            }
        }

        return returnData;
    }

    const submitForm = (e) => {
        e.preventDefault();

        const validation = validationForm();

        if(validation.error) {
            alert(validation.msg);
        } else {
            alert("Submit form sign up success")

            fetchSignUp();
        }
    }

    const fetchSignUp = async () => {
        try {
            const signUpData = {
                username: username,
                password: password
            };

            const response = await authApi.post(signUpData);

            console.log("Fetch successfully: ", response);

            navigate('/sign-in');

        } catch(error) {
            console.log("Failed to fetch sign up: ", error);
            alert("Username was existed or invalid!");
        }
    }

    return (
        <div className="container" style={{paddingTop: "5%"}}>
            <h1 style={{textAlign: "center"}}>
                Sign up
            </h1>
            <form
                onSubmit={e => {
                    submitForm(e);
                }}
                >
                <div className="form-group form-label">
                    <label htmlFor="text">Username:</label>
                    <input
                        type="text"
                        className="form-control"    
                        name="username"
                        placeholder="Enter username"
                        onChange={e => changeUsernameInputValue(e)}
                    />
                </div>
                <div className="form-group form-label">
                    <label htmlFor="pwd">Password:</label>
                    <input
                        type="password"
                        className="form-control"
                        name="password"
                        placeholder="Enter password"
                        onChange={e => changePasswordInputValue(e)}
                    />
                </div>
                <div className="form-group form-label">
                    <label htmlFor="pwd">Retype password:</label>
                    <input
                        type="password"
                        className="form-control"
                        name="retypePassword"
                        placeholder="Retype password"
                        onChange={e => changeRetypePasswordInputValue(e)}
                    />
                </div>
                <button className="btn btn-primary" type="submit">
                    Sign Up
                </button>
            </form>
        </div>
    )
}

export default ScreenAuthSignUp;