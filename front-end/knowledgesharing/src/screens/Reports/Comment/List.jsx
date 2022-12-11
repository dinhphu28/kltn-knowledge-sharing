import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { Button, ButtonGroup, Card, CardBody, CardLink, CardSubtitle, CardText, CardTitle } from 'reactstrap';
import commentReportApi from '../../../apis/commentReportApi';
import ListCommentReportPopup from './ListCommentReportPopup';
// import PropTypes from 'prop-types';

// ScreenReportCommentList.propTypes = {
    
// };

function ScreenReportCommentList(props) {
    const [popupOpen, setPopupOpen] = useState(false);
    const [solved, setSolved] = useState(false);
    const [listReport, setListReport] = useState([]);
    const [loaded, setLoaded] = useState(false);
    const [popupData, setPopupData] = useState({});

    useEffect(() => {
        const fetchListReports = async () => {
            try {
                const params = {
                    solved: solved
                };

                const response = await commentReportApi.getAll(params);

                console.log("Fetch comment reports list successfully: ", response);

                setListReport(response);
                
                setLoaded(true);

            } catch(error) {
                console.log("Failed to fetch list comment reports: ", error);
            }
        }

        fetchListReports();
    }, [solved]);

    const loadListReportCards = () => {
        if(loaded) {
            const listItems = listReport.map((item) =>
            <div key={item.id} className="my-report-card">
                <Card
                >
                    <CardBody>
                    <CardTitle tag="h5">
                        Article's title: {item.articleTitle}
                    </CardTitle>
                    <CardSubtitle
                        className="mb-2 text-muted"
                        tag="h6"
                    >
                        Report by: {item.author}
                    </CardSubtitle>
                    <CardText>
                        <small className='text-muted'>
                            Date: {item.date} Time: {item.time}
                        </small>
                    </CardText>
                    <CardSubtitle
                        className="mb-2 text-muted"
                        tag="h6"
                    >
                        Comment's author: {item.commentAuthor}
                    </CardSubtitle>
                    <CardText>
                        <small className='text-muted'>
                            <b>Comment's content:</b>{" "}
                            <em>
                                {item.commentContent}
                            </em>
                        </small>
                    </CardText>
                    <hr />
                    <CardSubtitle
                        className="mb-2 text-muted"
                        tag="h6"
                    >
                        Report's content:
                    </CardSubtitle>
                    <CardText>
                        {item.content}
                    </CardText>
                    {item.solved ?
                        <CardText style={{color: "#198754"}}>
                            Solved
                        </CardText> :
                        <CardText style={{color: "#ffc107"}}>
                            Unsolved
                        </CardText>
                    }
                    <CardLink
                        href='#'
                        onClick={() => {
                            const pData = {
                                commentId: item.commentId,
                                articleTitle: item.articleTitle,
                                commentContent: item.commentContent,
                                commentAuthor: item.commentAuthor
                            };
                            setPopupData(pData);
                            setPopupOpen(true);
                        }}
                    >
                        Show all reports of this comment
                    </CardLink>
                    <CardLink href={"/articles/" + item.articleUrl}>
                        Go to article
                    </CardLink>
                    {item.solved ?
                        <Button
                            color="primary"
                            className="float-end"
                            onClick={() => {
                                // fetchSolvedOrUnsolved(item.id, false);
                            }}
                        >
                            Mark as unsolved
                        </Button> :
                        <Button
                            color="primary"
                            className="float-end"
                            onClick={() => {
                                // fetchSolvedOrUnsolved(item.id, true);
                            }}
                        >
                            Mark as solved
                        </Button>
                    }
                    </CardBody>
                </Card>
            </div>
            );

            return listItems;
        }
    }

    const receiveCancel = () => {
        setPopupOpen(false);
    }

    return (
        <div>
            <Link to="/reports"
                style={{marginLeft: "2rem"}}
            >
                Article reports
            </Link>
            <div className='btn-sw-report-separate'>
                <ButtonGroup className='float-end'>
                    <Button
                        color={solved ? "secondary" : "primary"}
                        onClick={() => {
                            setSolved(false);
                        }}
                    >
                        Unsolved
                    </Button>
                    <Button
                        color={solved ? "primary" : "secondary"}
                        onClick={() => {
                            setSolved(true);
                        }}
                    >
                        Solved
                    </Button>
                </ButtonGroup>
            </div>
            <br />
            <h4
                style={{textAlign: "center"}}
            >
                Comment reports
            </h4>
            <div className="my-reports-list">
                {loadListReportCards()}

                {popupOpen ? <ListCommentReportPopup
                    onHandleChange={receiveCancel}
                    commentId={popupData.commentId}
                    articleTitle={popupData.articleTitle}
                    commentContent={popupData.commentContent}
                    commentAuthor={popupData.commentAuthor}
                />: ""}
            </div>
        </div>
    );
}

export default ScreenReportCommentList;