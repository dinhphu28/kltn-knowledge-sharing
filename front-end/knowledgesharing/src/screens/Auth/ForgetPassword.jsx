import React, { useState } from 'react';
import { Button, Form, FormGroup, Input, Label, Modal, ModalHeader, ModalBody } from 'reactstrap';
// import PropTypes from 'prop-types';
import { useNavigate } from 'react-router';
import authApi from '../../apis/authApi';

// ScreenAuthForgetPassword.propTypes = {
    
// };

function ScreenAuthForgetPassword(props) {
    const [username, setUsername] = useState("");
    const [modalResetPasswordOpen, setModalResetPasswordOpen] = useState(false);
    const [token, setToken] = useState("");
    const [password, setPassword] = useState("");


    let navigate = useNavigate();

    const changeUsernameInputValue = (e) => {
        setUsername(e.target.value);
    }

    const changeTokenInputValue = (e) => {
        setToken(e.target.value);
    }

    const changePasswordInputValue = (e) => {
        setPassword(e.target.value);
    }

    // Validation Form username
    const validationFormUsername = () => {
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

        return returnData;
    }

    const submitFormUsername = (e) => {
        e.preventDefault();

        const validation = validationFormUsername();

        if(validation.error) {
            alert(validation.msg);
        } else {
            alert("Check your email inbox");
            setModalResetPasswordOpen(true);
            fetchGetTokenResetPasswd();
        }
    }

    // Validation Form username
    const validationFormReset = () => {
        let returnData = {
            error: false,
            msg: ""
        }

        // Kiểm tra password dài ít nhất 8 kí tự
        if(password.length < 8) {
            returnData = {
                error: true,
                msg: "Length of password must be greater than 8 character"
            }
        }

        // Kiểm tra token ít nhất 90 kí tự
        if(token.length < 90) {
            returnData = {
                error: true,
                msg: "Wrong token"
            }
        }

        return returnData;
    }

    const submitFormReset= (e) => {
        e.preventDefault();

        const validation = validationFormReset();

        if(validation.error) {
            alert(validation.msg);
        } else {
            setModalResetPasswordOpen(false);
            fetchResetPasswd();
            navigate("/sign-in");
        }
    }

    const fetchGetTokenResetPasswd = async () => {
        try {
            await authApi.getTokenResetPasswd(username);

            // console.log("Fetch get token reset passwd successfully: ", response);

        } catch (error) {
            console.log("Failed to fetch get token reset passwd: ", error);
        }
    }

    const fetchResetPasswd = async () => {
        try {
            const data = {
                token: token,
                password: password
            };

            await authApi.resetPasswd(username, data);

            // console.log("Fetch reset password successfully: ", response);

        } catch (error) {
            console.log("Failed to fetch reset password: ", error);
        }
    }

    return (
        <div className="container" style={{paddingTop: "5%"}}>
            <h1 style={{textAlign: "center"}}>
                Forgot password
            </h1>
            <Form
                onSubmit={e => {
                    submitFormUsername(e);
                }}
            >
                <FormGroup>
                    <Label>
                        Username:
                    </Label>
                    <Input
                        type="text"
                        name="username"
                        placeholder='Enter username'
                        onChange={e => changeUsernameInputValue(e)}
                    />
                </FormGroup>
                <Button
                    color='primary'
                    type='submit'
                    block={true}
                >
                    Submit
                </Button>
            </Form>

            <Modal
                isOpen={modalResetPasswordOpen}
            >
                <ModalHeader>
                    Add new category
                </ModalHeader>
                <ModalBody>
                    <Form
                        onSubmit={e => {
                            submitFormReset(e);
                        }}
                    >
                        <FormGroup>
                            <Label>
                                Token: <em>Get your token in email inbox</em>
                            </Label>
                            <Input
                                type="text"
                                name="token"
                                placeholder="Enter your token"
                                onChange={e => changeTokenInputValue(e)}
                            />
                        </FormGroup>
                        <FormGroup>
                            <Label>
                                New password:
                            </Label>
                            <Input
                                type="password"
                                name='password'
                                placeholder='Enter your password'
                                onChange={e => changePasswordInputValue(e)}
                            />
                        </FormGroup>
                        <Button
                            color="primary"
                            type="submit"
                        >
                            Submit
                        </Button>
                        <Button
                            className="float-end"
                            type="button"
                            onClick={() => {
                                setModalResetPasswordOpen(false);
                            }}
                        >
                            Cancel
                        </Button>
                    </Form>
                </ModalBody>
            </Modal>
        </div>
    );
}

export default ScreenAuthForgetPassword;