import { faTimesCircle } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { useEffect, useState } from 'react';
import { Button, Card, CardBody, CardSubtitle, CardText, CardTitle } from 'reactstrap';
import commentReportApi from '../../../apis/commentReportApi';
import "./ListCommentReportPopup.css";
// import PropTypes from 'prop-types';

// ListCommentReportPopup.propTypes = {
    
// };

function ListCommentReportPopup(props) {
    const {commentId, articleTitle, commentContent, commentAuthor} = props;
    const [listReport, setListReport] = useState([]);
    const [loaded, setLoaded] = useState(false);

    useEffect(() => {
        const fetchListReports = async () => {
            try {
                const response = await commentReportApi.getAllOfComment(commentId);

                // console.log("Fetch list reports of comment successfully: ", response);

                setListReport(response);

                setLoaded(true);

            } catch (error) {
                console.log("Failed to fetch reports list of comment: ", error);
            }
        }

        fetchListReports();
    }, [commentId]);

    const fetchSolvedOrUnsolved = async (id, newSolvedState) => {
        try {
            const data = {
                solved: newSolvedState
            };

            const response = await commentReportApi.putSolvedUnsolved(id, data);

            console.log("Fetch update solved state successfully: ", response);

        } catch (error) {
            console.log("Failed to fetch solved-unsolved: ", error);
        }
    }

    const loadListReportCards = () => {
        if(loaded) {
            const listItems = listReport.map((item) =>
            <div key={item.id} className="my-report-card2">
                <Card
                >
                    <CardBody>
                    <CardTitle tag="h5">
                        Article's title: {articleTitle}
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
                        Comment's author: {commentAuthor}
                    </CardSubtitle>
                    <CardText>
                        <small className='text-muted'>
                            <b>Comment's content:</b>{" "}
                            <em>
                                {commentContent}
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
                    {item.solved ?
                        <Button
                            color="primary"
                            className="float-end"
                            onClick={() => {
                                fetchSolvedOrUnsolved(item.id, false);
                            }}
                        >
                            Mark as unsolved
                        </Button> :
                        <Button
                            color="primary"
                            className="float-end"
                            onClick={() => {
                                fetchSolvedOrUnsolved(item.id, true);
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

    return (
        <div>
            <div className="layer"></div>
            <div className="my-rp-popup">
                <div className="my-popup-nav">
                    <FontAwesomeIcon icon={faTimesCircle}
                        onClick={() => {
                            props.onHandleChange();
                        }}
                    />
                </div>
                {loadListReportCards()}
            </div>
        </div>
    );
}

export default ListCommentReportPopup;