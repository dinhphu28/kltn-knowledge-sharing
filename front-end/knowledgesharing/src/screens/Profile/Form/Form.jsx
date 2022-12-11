import React, { useEffect, useState } from 'react';
import { Button, Form, FormGroup, Input, Label } from 'reactstrap';
import profileApi from '../../../apis/profileApi';
import "./Form.css";
// import PropTypes from 'prop-types';
import emailVerificationApi from '../../../apis/emailVerificationApi';
import { BASE_URL_API_BE } from '../../../constants/global';
import UploadFiles from '../../../components/FileUpload/FileUpload';

// ScreenFormProfile.propTypes = {
    
// };

function ScreenFormProfile(props) {
    const [avatar, setAvatar] = useState("");
    const [email, setEmail] = useState("");
    const [emailVerified, setEmailVerified] = useState(false);
    const [oldEmail, setOldEmail] = useState("");
    
    // const [profileInfo, setProfileInfo] = useState({});

    // const changeAvatarInputValue = (e) => {
    //     setAvatar(e.target.value);
    // }
    const changeEmailInputValue = (e) => {
        setEmail(e.target.value);
    }

    const validationForm = () => {
        let returnData = {
            error: false,
            msg: ""
        };

        const re = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
        
        if(email !== null) {
            if(email.length > 0) {
                if(!re.test(email)) {
                    returnData = {
                        error: true,
                        msg: "Not match email format"
                    }
                }
            }
        }

        if(avatar !== null) {
            if(avatar.length < 10) {
                returnData = {
                    error: true,
                    msg: "Length of url is invalid"
                }
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
            alert("Submit form success");

            fetchUpdateProfile();

            // props.onHandleChange();
        }
    }

    useEffect(() => {
        const fetchProfile = async () => {
            try {
                const username = localStorage.getItem("username");

                const response = await profileApi.get(username);

                // setProfileInfo(response);

                setAvatar(response.avatar);
                setEmail(response.email);
                setOldEmail(response.email);
                setEmailVerified(response.verified);

                console.log("Fetch profile successfully: ", response);
                
            } catch (error) {
                console.log("Failed to fetch profile info: ", error);
            }
        }

        fetchProfile();
    }, []);

    // call api update here
    const fetchUpdateProfile = async () => {
        try {
            const data = {
                avatar: avatar,
                email: email,
                firstName: "",
                lastName: "",
            };

            // console.log("Data profile: ", data);

            await profileApi.put(localStorage.getItem("username"), data);

            // console.log("Update profile successfully: ", response);

        } catch (error) {
            console.log("Failed to fetch update profile: ", error);
        }
    }

    const receiveImageUrl = (imgFileName) => {
        setAvatar(imgFileName);
    }

    const fetchRequestEmailVerification = async () => {
        try {
            const data = {
                username: localStorage.getItem("username")
            };

            alert("Please check your mail inbox.");

            const response = await emailVerificationApi.post(data);

            console.log("Fetch REV successfully: ", response);

        } catch(error) {
            console.log("Failed to fetch REV: ", error);
            alert("Please save your email and click verify again!");
        }
    }

    return (
        <div className="my-profile">
            <div className="username-section">
                {/* <img src={avatar} alt="Avatar" /> */}
                <img src={avatar ? (BASE_URL_API_BE + "/files/downloadFile/" + avatar) : "http://www.vov.edu.vn/frontend/home/images/no-avatar.png"} alt="Avatar" />
                <h5>
                    Username: <span style={{color: "#20cc93"}}>{localStorage.getItem("username")}</span>
                </h5>
            </div>
            <Form className="my-form"
                onSubmit={e => {
                    submitForm(e);
                }}
            >
                <FormGroup>
                    {/* <Label>
                        Avatar URL:
                    </Label>
                    <Input
                        type="url"
                        name="avatarUrl"
                        placeholder="Your avatar image's url"
                        onChange={e => changeAvatarInputValue(e)}
                        value={avatar}
                    /> */}
                    <Label>
                        Upload avatar:
                    </Label>
                    <UploadFiles onHandleChange={receiveImageUrl} />
                </FormGroup>
                <FormGroup>
                    <Label>
                        Email: {oldEmail}
                        {emailVerified === 1 ?
                            <h4 id="verified-label">
                                Verified
                            </h4> :
                            <h4 id="unverified-label">
                                Unverified
                            </h4>
                        }
                    </Label>
                    {!emailVerified ?
                        <Button
                            type="button"
                            color="primary"
                            className="btn-verify-email"
                            onClick={() => {
                                fetchRequestEmailVerification();
                            }}
                        >
                            Verify email
                        </Button>
                    : ""}
                    <Input 
                        type="email"
                        name="email"
                        placeholder="example@example.com"
                        onChange={e => changeEmailInputValue(e)}
                        value={email}
                    />
                </FormGroup>
                <Button
                    color="primary"
                    type="submit"
                    block={true}
                >
                    Save
                </Button>
            </Form>
        </div>
    );
}

export default ScreenFormProfile;