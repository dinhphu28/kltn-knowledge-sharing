import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import authApi from '../../apis/authApi';
// import PropTypes from 'prop-types';

// ScreenAuthSignIn.propTypes = {
    
// };

function ScreenAuthSignIn(props) {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    let navigate = useNavigate();

    const changeUsernameInputValue = (e) => {
        setUsername(e.target.value);
    }
    const changePasswordInputValue = (e) => {
        setPassword(e.target.value);
    }

    // Validation Form
    const validationForm = () => {
        let returnData = {
            error: false,
            msg: ""
        }

        // const {username, password} = this.state;

        // Kiểm tra username
        const re = /\S/;
        if(!re.test(username)) {
            returnData = {
                error: true,
                msg: 'Not match username format'
            }
        }

        // Kiểm tra password dài ít nhất 8 kí tự
        if(password.length < 8) {
            returnData = {
                error: true,
                msg: "Length of password must be greater than 8 character"
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
            alert("Submit form sign in success");
            fetchSignIn();
        }
    }

    const fetchSignIn = async () => {
        try{
            const signInData = {
                username: username,
                password: password
            };

            const response = await authApi.put(signInData);

            console.log("Fetch successfully: ", response);

            // console.log("Login success");

            localStorage.setItem("username", username);
            localStorage.setItem("role", response.role);
            localStorage.setItem("token", response.token);

            navigate('/articles');

            props.onHandleChange();

        } catch(error) {
            console.log("Failed to fetch sign in: ", error);
            alert("Invalid username or password!");
        }
    }

    return (
        <div className="container" style={{paddingTop: "5%"}}>
            <h1 style={{textAlign: "center"}}>
                Sign in
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
                <button className="btn btn-primary" type="submit">
                    Sign In
                </button>
                <Link
                    to="/forget-password"
                    className="float-end"
                >
                    Forgot password
                </Link>
            </form>
        </div>
    );
}

export default ScreenAuthSignIn;