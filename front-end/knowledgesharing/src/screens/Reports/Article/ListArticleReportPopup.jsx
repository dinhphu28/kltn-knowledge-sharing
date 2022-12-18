import React, { useEffect, useState } from 'react';
import { Button, Card, CardBody, CardSubtitle, CardText, CardTitle } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTimesCircle } from '@fortawesome/free-solid-svg-icons';
import articleReportApi from '../../../apis/articleReportApi';
import "./ListArticleReportPopup.css";

// import PropTypes from 'prop-types';

// ListArticleReportPopup.propTypes = {
    
// };

function ListArticleReportPopup(props) {
    const {articleId, articleTitle} = props;

    const [listReport, setListReport] = useState([]);
    const [loaded, setLoaded] = useState(false);

    useEffect(() => {
        const fetchListReports = async () => {
            try {
                const response = await articleReportApi.getAllOfArticle(articleId);

                console.log("Fetch list reports of article successfully: ", response);

                setListReport(response);

                setLoaded(true);

            } catch (error) {
                console.log("Failed to fetch reports list of article: ", error);
            }
        }

        fetchListReports();
    }, [articleId]);

    const fetchSolvedOrUnsolved = async (id, newSolvedState) => {
        try {
            const data = {
                solved: newSolvedState
            };

            await articleReportApi.putSolvedUnsolved(id, data);

            // console.log("Fetch update solved state successfully: ", response);

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
                    <CardText>
                        {item.content}
                    </CardText>
                    {item.isSolved === 1 ?
                        <CardText style={{color: "#198754"}}>
                            Solved
                        </CardText> :
                        <CardText style={{color: "#ffc107"}}>
                            Unsolved
                        </CardText>
                    }
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

export default ListArticleReportPopup;