import React, { useEffect, useState } from 'react';
import { Button, Card, CardBody, CardLink, CardSubtitle, CardText, CardTitle, ButtonGroup } from 'reactstrap';
import { Link } from 'react-router-dom';
import articleReportApi from '../../../apis/articleReportApi';
import ListArticleReportPopup from './ListArticleReportPopup';
// import PropTypes from 'prop-types';

// ScreenReportArticleList.propTypes = {
    
// };

function ScreenReportArticleList(props) {
    const [listReport, setListReport] = useState([]);
    const [loaded, setLoaded] = useState(false);
    const [popupOpen, setPopupOpen] = useState(false);
    const [popupData, setPopupData] = useState({});
    const [solved, setSolved] = useState(false);

    useEffect(() => {
        const fetchListReports = async () => {
            try {
                const params = {
                    solved: solved
                };

                const response = await articleReportApi.getAll(params);

                // console.log("Fetch list reports successfully: ", response);

                setListReport(response);

                setLoaded(true);

            } catch (error) {
                console.log("Failed to fetch list reports: ", error);
            }
        }

        fetchListReports();
    }, [solved]);

    const fetchSolvedOrUnsolved = async (id, newSolvedState) => {
        try {
            const data = {
                isSolved: newSolvedState
            };

            // console.log("Article report mark solved data: " + id + "-- ", data);

            await articleReportApi.put(id, data);

            // console.log("Fetch update solved state successfully: ", response);

        } catch (error) {
            console.log("Failed to fetch solved-unsolved: ", error);
        }
    }

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
                                articleId: item.articleId,
                                articleTitle: item.articleTitle
                            };
                            setPopupData(pData);
                            setPopupOpen(true);
                        }}
                    >
                        Show all reports of this article
                    </CardLink>
                    <CardLink href={"/articles/" + item.articleUrl}>
                        Go to article
                    </CardLink>
                    {item.isSolved === 1 ?
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

    const receiveCancel = () => {
        setPopupOpen(false);
    }

    return (
        <div>
            <Link to="/comment-reports"
                style={{marginLeft: "2rem"}}
            >
                Comment reports
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
                Article reports
            </h4>
            <div className="my-reports-list">
                {loadListReportCards()}

                {popupOpen ? <ListArticleReportPopup
                    onHandleChange={receiveCancel}
                    articleId={popupData.articleId}
                    articleTitle={popupData.articleTitle}
                />: ""}
            </div>
        </div>
    );
}

export default ScreenReportArticleList;