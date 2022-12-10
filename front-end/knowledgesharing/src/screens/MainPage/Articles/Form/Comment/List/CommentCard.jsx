import React, { useEffect, useState } from 'react';
import profileApi from '../../../../../../apis/profileApi';
// import PropTypes from 'prop-types';
import commentApi from '../../../../../../apis/commentApi';
import commentReportApi from '../../../../../../apis/commentReportApi';
import { Button, Form, Modal, ModalBody, ModalHeader, FormGroup, Label, Input } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEye, faEyeSlash, faFile } from '@fortawesome/free-solid-svg-icons';
import "./CommentCard.css"

// CommentCard.propTypes = {
    
// };

function CommentCard(props) {
    const {comment} = props;
    const [avatar, setAvatar] = useState("");
    const [loaded, setLoaded] = useState(false);
    const [modalCommentReportOpen, setModalCommentReportOpen] = useState(false);
    const [reportContent, setReportContent] = useState("");
    const [hideState, setHideState] = useState(false);

    const changeReportContentInputValue = (e) => {
        setReportContent(e.target.value);
    }

    useEffect(() => {
        const fetchProfile = async () => {
            let ld = false;
            try {
                // setLoaded(false);

                const username = comment.author;

                const response = await profileApi.get(username);

                // setProfileInfo(response);

                setAvatar(response.avatar);

                setLoaded(true);
                ld = true;

                // console.log("Fetch profile successfully: ", response);
                
            } catch (error) {
                if(ld) {
                    console.log("Failed to fetch avatar of comments card info: ", error);
                }
            }
        }

        fetchProfile();

        setHideState(comment.hidden)
    }, [comment.author, comment.hidden]);

    // Validation Form
    const validationForm = () => {
        let returnData = {
            error: false,
            msg: ""
        }

        // Kiểm tra token ít nhất 90 kí tự
        if(reportContent.length < 1) {
            returnData = {
                error: true,
                msg: "Wrong token"
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
            alert("Report sent!");
            setModalCommentReportOpen(false);
            fetchCreateCmtReport();
        }
    }

    const fetchCreateCmtReport = async () => {
        try{
            const data = {
                commentId: comment.id,
                author: localStorage.getItem("username"),
                content: reportContent
            };

            await commentReportApi.post(data);

            // console.log("Fetch create comment report successfully: ", response);

        } catch(error) {
            console.log("Failed to fetch create comment report: ", error);
        }
    }

    const fetchHideShowComment = async (hiddenState) => {
        try {
            const data = {
                hidden: hiddenState
            };

            await commentApi.hideShow(comment.articleId, comment.id, data);

            // console.log("Fetch hide-show comment successfully: ", response);

        } catch(error) {
            console.log("Failed to fetch hide-show comment: ", error);
        }
    }

    return (
        <div className="comment-card">
            <div className="base-content">
                <div className="comment-info">
                    <img src={loaded ? (process.env.REACT_APP_BE_API_V1_URL + "/files/downloadFile/" + avatar) : "http://www.vov.edu.vn/frontend/home/images/no-avatar.png"} alt="Avatar" />
                    {/* https://bloganchoi.com/wp-content/uploads/2021/10/iu-solo.jpg */}
                    <span className="username">{comment.author}</span>
                    <span className="datetime">{comment.date} {comment.time}</span>
                </div>
                <p>
                    {comment.content}
                </p>
            </div>
            <div  className="comment-report-btn">
                {(localStorage.getItem("role") === "norm") ?
                    <>
                    <Button
                        color="info"
                        onClick={() =>{
                            setModalCommentReportOpen(true);
                        }}
                    >
                        <FontAwesomeIcon icon={faFile} /> Report
                    </Button>

                    <Modal
                        isOpen={modalCommentReportOpen}
                    >
                        <ModalHeader>
                            Add new category
                        </ModalHeader>
                        <ModalBody>
                            <Form
                                onSubmit={e => {
                                    submitForm(e);
                                }}
                            >
                                <FormGroup>
                                    <Label>
                                        Report content:
                                    </Label>
                                    <Input
                                        type="textarea"
                                        name="report-content"
                                        placeholder="Type your report"
                                        id='cmt-rp-ctn'
                                        onChange={e => changeReportContentInputValue(e)}
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
                                        setModalCommentReportOpen(false);
                                    }}
                                >
                                    Cancel
                                </Button>
                            </Form>
                        </ModalBody>
                    </Modal>
                    </>
                : ""}
                {((localStorage.getItem("role") === "mod") || localStorage.getItem("role") === "admin") ?
                    <>
                        {hideState ?
                            <Button id="btn-hide-rp"
                                type="button"
                                color="dark"
                                onClick={() => {
                                    fetchHideShowComment(false); // show
                                    setHideState(false);
                                }}
                            >
                                <FontAwesomeIcon icon={faEyeSlash} /> Show
                            </Button> :
                            <Button id="btn-hide-rp"
                                type="button"
                                color="dark"
                                onClick={() => {
                                    fetchHideShowComment(true); // hide
                                    setHideState(true);
                                }}
                            >
                                <FontAwesomeIcon icon={faEye} /> Hide
                            </Button>
                        }
                    </> : ""}
            </div>
        </div>
    );
}

export default CommentCard;