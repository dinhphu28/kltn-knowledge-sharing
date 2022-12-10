import React, { useState } from 'react';
// import PropTypes from 'prop-types';
import { useNavigate } from 'react-router-dom';
import { Button, Form, FormGroup, Input, Label } from 'reactstrap';
// import PropTypes from 'prop-types';
import authApi from '../../apis/authApi';

// ChangePassword.propTypes = {
    
// };

function ChangePassword(props) {
    const [password, setPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const [retypePassword, setRetypePassword] = useState("");

    let navigate = useNavigate();

    const changePasswordInputValue = (e) => {
        setPassword(e.target.value);
    }
    const changeNewPasswordInputValue = (e) => {
        setNewPassword(e.target.value);
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

        // const re = /\S/;
        // if(!re.test(username)) {
        //     returnData = {
        //         error: true,
        //         msg: 'Not match username format'
        //     }
        // }

        // Kiểm tra password dài ít nhất 8 ký tự và Retype password khớp với password
        if(newPassword.length < 8) {
            returnData = {
                error: true,
                msg: "Length of password must be greater than 8 character"
            }
        } else if(newPassword !== retypePassword) {
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
            alert("Submit form change password success")

            fetchChangePassword();
        }
    }

    const fetchChangePassword = async () => {
        try {
            const data = {
                oldValue: password,
                newValue: newPassword
            };

            await authApi.patch(localStorage.getItem("username"), data);

            // console.log("Fetch change password successfully: ", response);

            alert("Password changed");

            navigate("/articles");

        } catch(error) {
            console.log("Failed to fetch change password: ", error);
            alert("Current password is invalid");
        }
    }

    return (
        <div className="container" style={{paddingTop: "5%"}}>
            <Form className="my-form"
                onSubmit={e => {
                    submitForm(e);
                }}
            >
                <FormGroup>
                    <Label>
                        Password:
                    </Label>
                    <Input
                        type="password"
                        name="password"
                        placeholder="Your password"
                        onChange={e => changePasswordInputValue(e)}
                        // value={avatar}
                    />
                </FormGroup>
                <FormGroup>
                    <Label>
                        New password:
                    </Label>
                    <Input 
                        type="password"
                        name="newPassword"
                        placeholder="Your new password"
                        onChange={e => changeNewPasswordInputValue(e)}
                        // value={email}
                    />
                </FormGroup>
                <FormGroup>
                    <Label>
                        Retype password:
                    </Label>
                    <Input
                        type="password"
                        name="retypePassword"
                        placeholder="Retype your new password"
                        onChange={e => changeRetypePasswordInputValue(e)}
                    />
                </FormGroup>
                <Button
                    color="primary"
                    type="submit"
                    block={true}
                >
                    Change password
                </Button>
            </Form>
        </div>
    );
}

export default ChangePassword;