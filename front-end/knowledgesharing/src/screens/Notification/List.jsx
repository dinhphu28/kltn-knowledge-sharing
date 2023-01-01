import React, { useState } from 'react';
// import PropTypes from 'prop-types';
import { useEffect } from 'react';
import userNotificationApi from '../../apis/userNotificationApi';
import { Card, CardBody, CardLink, CardSubtitle, CardText, CardTitle } from 'reactstrap';

// ScreenNotificationList.propTypes = {
    
// };

function ScreenNotificationList(props) {

    const [listUserNotification, setListUserNotification] = useState([]);

    useEffect(() => {
        const fetchListNotification = async () => {
            try {
                const params = {
                    username: localStorage.getItem("username")
                }
    
                const response = await userNotificationApi.getAll(params);

                console.log("Fetch list notification successfully: ", response);

                setListUserNotification(response);
                
            } catch (error) {
                console.log("Failed to fetch list notification: ", error);
            }
        }

        fetchListNotification();
    }, []);

    const listJsxUserNotificationItems = listUserNotification.map((item) =>
        <div key={item.id} className="my-report-card">
            <Card
            >
                <CardBody>
                    <CardTitle tag="h5">
                        {item.title}
                    </CardTitle>
                    {/* <CardSubtitle
                        className="mb-2 text-muted"
                        tag="h6"
                    >
                        Report by: {item.author}
                    </CardSubtitle> */}
                    <CardText>
                        <small className='text-muted'>
                            Date: {item.dateCreated}
                        </small>
                    </CardText>
                    <CardText>
                        {item.content}
                    </CardText>
                    {/* {item.solved ?
                        <CardText style={{ color: "#198754" }}>
                            Solved
                        </CardText> :
                        <CardText style={{ color: "#ffc107" }}>
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
                    } */}
                </CardBody>
            </Card>
        </div>
    );

    return (
        <div>
            <h4
                style={{textAlign: "center"}}
            >
                Notification
            </h4>

            <div className="my-reports-list">
                {listJsxUserNotificationItems}
            </div>

        </div>
    );
}

export default ScreenNotificationList;