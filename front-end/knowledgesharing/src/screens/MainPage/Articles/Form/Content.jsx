import React, { useEffect, useState } from 'react';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCaretUp, faEye, faEyeSlash, faFile, faTrash } from '@fortawesome/free-solid-svg-icons';
import { faCaretDown } from '@fortawesome/free-solid-svg-icons';
import { faPen } from '@fortawesome/free-solid-svg-icons';
import { useLocation, useNavigate } from 'react-router-dom';
import { Button, Col, Label, Modal, ModalBody, ModalFooter, ModalHeader, Row } from 'reactstrap';
// import PropTypes from 'prop-types';
import userVoteStateApi from '../../../../apis/userVoteStateApi';
import articleApi from '../../../../apis/articleApi';
import "./Content.css"
import DOMPurify from 'dompurify';
import ArticleCommentList from './Comment/List';
import EditArticlePopup from './EditArticlePopup/EditArticlePopup';
import ArticleReportPopup from './ReportPopup/ReportPopup';
import nominatedArticleApi from '../../../../apis/nominatedArticleApi';
import userNotificationApi from '../../../../apis/userNotificationApi';

// ScreenArticleFormContent.propTypes = {
    
// };

function ScreenArticleFormContent(props) {
    const {article} = props;

    // let tmpVoteScore = 0;
    // tmpVoteScore = article.voteScore;

    // const {title, author, content, dateCreated} = this.props;

    const [voteState, setVoteState] = useState(0);
    const [tmpVoteScore, setTmpVoteScore] = useState(article.voteScore);
    const [editPopupOpen, setEditPopupOpen] = useState(false);
    const [reportPopupOpen, setReportPopupOpen] = useState(false);
    const [hideState, setHideState] = useState(false);
    const [articleDetails, setArticleDetails] = useState({});
    const [loaded, setLoaded] = useState(false);

    const [modalNominateIsOpen, setModalNominateIsOpen] = useState(false);

    // const [refresh, setRefresh] = useState(false);

    let navigate = useNavigate();

    // const location = useLocation();

    const currentReadingArticle = {
        id: article.id,
        title: article.title,
        author: article.author,
        url: article.url
    }

    // console.log("VYVYVYV: ", currentReadingArticle);

    const appendToReadingList = (item) => {
        const readingListStr = localStorage.getItem("readingList");

        if(readingListStr === null) {
            const arrReadingList = new Array(item);

            localStorage.setItem("readingList", JSON.stringify(arrReadingList));
        } else {
            const arrReadingList = JSON.parse(readingListStr);

            const newArrReadingList = [item].concat(arrReadingList.filter(itemTmp => itemTmp.id !== item.id));

            if(newArrReadingList.length > 5) {
                localStorage.setItem("readingList", JSON.stringify(newArrReadingList.slice(0, 5)));
            } else {
                localStorage.setItem("readingList", JSON.stringify(newArrReadingList));
            }
        }
    }

    appendToReadingList(currentReadingArticle);

    useEffect(() => {

        // const fetchArticleByUrl = async () => {
        //     try {
        //         // const response = await articleApi.getByUrl(location.pathname.substring(10));

        //         // console.log("Fetch article by URL successfully: ", response);

        //         // setArticleDetails(response);
        //         // setTmpVoteScore(response.voteScore);

        //         console.log("My URL: ", location.pathname.substring(10));
        //     } catch (error) {
        //         console.log("Failed to fetch article by URL: ", error);
        //     }
        // }

        const fetchArticleById = async () => {
            try {
                // console.log("VZZ: ", article.id);

                const response = await articleApi.getById(article.id);

                console.log("Fetch article by id successfully: ", response);

                setArticleDetails(response);

            } catch (error) {
                console.log("Failed to fetch article by id: ", error);
            }
        }

        // console.log("VZZ: ", article.id);

        // if(article) {
        //     fetchArticleById();

        //     setHideState(article.hidden);
        // } else {
        //     fetchArticleByUrl();

        //     setHideState(articleDetails.hidden)
        // }

        fetchArticleById();
        // fetchArticleByUrl();

        // setHideState(article.hidden);

        if(localStorage.getItem("username") !== null) {
            fetchGetUVS();
        }

        setHideState(article.hidden);

    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [article.hidden]);
    // }, []);

    const toggleModalNominate = () => {
        setModalNominateIsOpen(!modalNominateIsOpen);
    }

    const fetchGetUVS = async () => {
        try {  
            const params = {
                username: localStorage.getItem("username")
            };

            const response = await userVoteStateApi.get(article.id, params);

            // console.log("Fetch get UVS successfully: ", response);

            setVoteState(response.voteState);

        } catch (error) {
            console.log("Failed to fetch get UVS", error);
        }
    };

    const fetchUpdateUVS = async (vState) => {
        if(localStorage.getItem("username") !== null) {
            try {
                const params = {
                    username: localStorage.getItem("username")
                };
    
                const data = {
                    voteState: vState
                };
    
                await userVoteStateApi.put(article.id, params, data);
    
                // console.log("Fetch update UVS successfully: ", response);
                
    
            } catch (error) {
                console.log("Failed to fetch update UVS: ", error);
            }
        }
    };

    const fetchDeleteArticle = async (articleId) => {
        if((localStorage.getItem("role") === "norm" && article.author === localStorage.getItem("username")) ||
            (localStorage.getItem("role") === "mod") ||
            localStorage.getItem("role") === "admin") {
            
            try {
                // await articleApi.delete(articleId);
                const data = {
                    hidden: 1
                };

                await articleApi.hideShow(articleId, data);

                alert("Article is deleted");

                navigate("/articles");

            } catch(error) {
                console.log("Failed to fetch delete article: ", error);
            }
        }
    }

    const fetchHideShowArticle = async (hiddenState) => {
        if((localStorage.getItem("role") === "mod") || localStorage.getItem("role") === "admin") {
            try {
                const data = {
                    hidden: hiddenState
                };

                await articleApi.hideShow(article.id, data);

                // console.log("Fetch hide-show article successfully: ", response);

            } catch(error) {
                console.log("Failed to fetch hide-show article: ", error);
            }
        }
    }

    const fetchUpdateNominateArticleWithPosition = async (positionIndex) => {
        if(localStorage.getItem("role") === "mod" || localStorage.getItem("role") === "admin") {
            try {
                const data = {
                    articleId: articleDetails.id
                }

                const response = await nominatedArticleApi.put("nomination-" + positionIndex, data);

                console.log("Fetch put nominated article successfully: ", response);

                alert("Success");

                setModalNominateIsOpen(false);

            } catch (error) {
                console.log("Failed to fetch put nominated articles: ", error);

                alert("Failed");

                setModalNominateIsOpen(false);
            }
        }
    }

    const fetchCreateNotificationToAuthorWhenHide = async () => {
        try {
            const data = {
                createdBy: localStorage.getItem("username"),
                createdByName: localStorage.getItem("username"),
                forUser: articleDetails.author,
                title: "Your article has been blocked",
                content: "Article title: " + articleDetails.title
            }

            const response = await userNotificationApi.post(data);

            console.log("Fetch create notification hide article successfully: ", response);

        } catch (error) {
            console.log("Failed to fetch create notification hide article: ", error);
        }
    }

    const receiveCancel = () => {
        setEditPopupOpen(false);
    }

    const receiveRPCancel = () => {
        setReportPopupOpen(false);
    }

    return (
        <div className="article">
            <div className="hd-and-btn">
                <h1 id="article-title">{article.title}</h1>
                <div id="btn-man">
                    {((localStorage.getItem("role") === "norm" && article.author === localStorage.getItem("username")) ||
                            (localStorage.getItem("role") === "mod" && article.author === localStorage.getItem("username")) ||
                            localStorage.getItem("role") === "admin") ?
                        <>
                            <Button id="btn-edit"
                                type="button"
                                color="primary"
                                onClick={() => {
                                    setEditPopupOpen(true);
                                }}
                            >
                                <FontAwesomeIcon icon={faPen} /> Edit
                            </Button>
                        </> : ""}

                    {((article.author === localStorage.getItem("username")) &&
                        (localStorage.getItem("role") !== "mod" && localStorage.getItem("role") !== "admin")
                    ) ?
                        // (localStorage.getItem("role") === "mod") ||
                        // localStorage.getItem("role") === "admin") ?
                        <>
                            <Button id="btn-del"
                                type="button"
                                color="danger"
                                onClick={() => {
                                    fetchDeleteArticle(article.id);
                                }}
                            >
                                <FontAwesomeIcon icon={faTrash} /> Delete
                            </Button>
                        </> : ""}
                    {((localStorage.getItem("role") === "mod") || localStorage.getItem("role") === "admin") ?
                    <>
                        {hideState ?
                            <Button id="btn-hide"
                                type="button"
                                color="dark"
                                onClick={() => {
                                    // fetchDeleteArticle(article.id);
                                    fetchHideShowArticle(false); // show
                                    setHideState(false);
                                }}
                            >
                                <FontAwesomeIcon icon={faEyeSlash} /> Show
                            </Button> :
                            <Button id="btn-hide"
                                type="button"
                                color="dark"
                                onClick={() => {
                                    // fetchDeleteArticle(article.id);
                                    fetchHideShowArticle(true); // hide
                                    setHideState(true);

                                    fetchCreateNotificationToAuthorWhenHide();
                                }}
                            >
                                <FontAwesomeIcon icon={faEye} /> Hide
                            </Button>
                        }

                        <Button id="btn-nominate"
                            type='button'
                            color='success'
                            onClick={() => {
                                toggleModalNominate();
                            }}
                        >
                            Nominate
                        </Button>
                    </> : ""}
                </div>
            </div>
            <div className="article-info">
                <span>Written by: {article.author}</span>
                <span>Published: {article.dateCreated}</span>
                <span className="vote">
                    <FontAwesomeIcon
                        icon={faCaretUp}
                        color={(voteState === 1) ? "#04d28f" : "#888888"}
                        onClick={() => {
                            if(localStorage.getItem("username") !== null) {
                                if(voteState === 1) {
                                    setVoteState(0);
                                    fetchUpdateUVS(0);
                                    setTmpVoteScore(tmpVoteScore - 1);
                                    // setRefresh(!refresh);
                                } else if(voteState === 0) {
                                    setVoteState(1);
                                    fetchUpdateUVS(1);
                                    setTmpVoteScore(tmpVoteScore + 1);
                                    // setRefresh(!refresh);
                                }
                            }
                        }}
                    />
                    <span>{tmpVoteScore}</span>
                    <FontAwesomeIcon
                        icon={faCaretDown}
                        color={(voteState === -1) ? "#04d28f" : "#888888"}
                        onClick={() => {
                            if(localStorage.getItem("username") !== null) {
                                if(voteState === -1) {
                                    setVoteState(0);
                                    fetchUpdateUVS(0);
                                    setTmpVoteScore(tmpVoteScore + 1);
                                    // setRefresh(!refresh);
                                } else if(voteState === 0) {
                                    setVoteState(-1);
                                    fetchUpdateUVS(-1);
                                    setTmpVoteScore(tmpVoteScore - 1);
                                    // setRefresh(!refresh);
                                }
                            }
                        }}
                    />
                </span>
            </div>
            <hr />
            <div className="article-content">
                {/* Audio player here */}
                {/* <ReactAudioPlayer
                    // src="http://localhost:8080/api/v1/files/downloadFile/cac2a911-6a39-4237-bb8d-49c27eb3dbc2.mp3"
                    // src="http://localhost:8080/downloadFile/06c05f65-4a0d-4e35-95ff-8ea07b326a37.m4a"
                    src={process.env.REACT_APP_BE_API_V1_URL + "/files/downloadFile/" + article.audioContent}
                    autoPlay
                    controls
                /> */}

                {/* <ReactMarkdown children={article.content} remarkPlugins={[remarkGfm]} /> */}
                {/* <h1>{articleDetails.content}</h1>
                <div
                    dangerouslySetInnerHTML={{__html: DOMPurify.sanitize("XXX<a href='https://google.com'>Google</a><h2 style='color:red'>This is red h2</h2>")}}
                /> */}
                <div
                    dangerouslySetInnerHTML={{__html: DOMPurify.sanitize(articleDetails.content)}}
                />
            </div>
            <hr />
            {(localStorage.getItem("role") === "norm") ? <Button
                color="info"
                onClick={() => {
                    setReportPopupOpen(true);
                }}
            >
                <FontAwesomeIcon icon={faFile} /> Report
            </Button> : ""}
            <div className="comment">
                <ArticleCommentList articleId={article.id}/>
            </div>

            {editPopupOpen ? <EditArticlePopup article={articleDetails} onHandleChange={receiveCancel} /> : ""}

            {reportPopupOpen ? <ArticleReportPopup articleId={article.id} onHandleChange={receiveRPCancel} /> : ""}

            <Modal
                isOpen={modalNominateIsOpen}
                toggle={toggleModalNominate}
            >
                <ModalHeader toggle={toggleModalNominate} >Show article in nominated articles</ModalHeader>
                <ModalBody>
                    <Label>
                        Select position (click):
                    </Label>
                    <Row xs="3">
                        <Col className='bg-light border'
                            onClick={() => {
                                fetchUpdateNominateArticleWithPosition(1);
                            }}
                        >
                            Position 1
                        </Col>
                        <Col className='bg-light border'
                            onClick={() => {
                                fetchUpdateNominateArticleWithPosition(2);
                            }}
                        >
                            Position 2
                        </Col>
                        <Col className='bg-light border'
                            onClick={() => {
                                fetchUpdateNominateArticleWithPosition(3);
                            }}
                        >
                            Position 3
                        </Col>
                    </Row>
                    <Row xs="3">
                        <Col className='bg-light border'
                            onClick={() => {
                                fetchUpdateNominateArticleWithPosition(4);
                            }}
                        >
                            Position 4
                        </Col>
                        <Col className='bg-light border'
                            onClick={() => {
                                fetchUpdateNominateArticleWithPosition(5);
                            }}
                        >
                            Position 5
                        </Col>
                        <Col className='bg-light border'
                            onClick={() => {
                                fetchUpdateNominateArticleWithPosition(6);
                            }}
                        >
                            Position 6
                        </Col>
                    </Row>
                    <Row xs="3">
                        <Col className='bg-light border'
                            onClick={() => {
                                fetchUpdateNominateArticleWithPosition(7);
                            }}
                        >
                            Position 7
                        </Col>
                        <Col className='bg-light border'
                            onClick={() => {
                                fetchUpdateNominateArticleWithPosition(8);
                            }}
                        >
                            Position 8
                        </Col>
                        <Col className='bg-light border'
                            onClick={() => {
                                fetchUpdateNominateArticleWithPosition(9);
                            }}
                        >
                            Position 9
                        </Col>
                    </Row>
                </ModalBody>
                <ModalFooter>
                {/* <Button
                    color="primary"
                    onClick={() => {
                        // fetchCreateCategory();

                        toggleModalNominate();
                    }}
                >
                    Submit
                </Button>{' '} */}
                <Button color="secondary" onClick={toggleModalNominate}>
                    Cancel
                </Button>
                </ModalFooter>
            </Modal>
        </div>
    )
}

export default ScreenArticleFormContent;