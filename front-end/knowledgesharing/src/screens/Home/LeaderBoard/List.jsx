import React from 'react';
import { Card, CardBody, CardGroup, CardImg, CardSubtitle, CardText, CardTitle } from 'reactstrap';
// import PropTypes from 'prop-types';

// LeaderBoardList.propTypes = {
    
// };

function LeaderBoardList(props) {
    return (
        <div>
            <h5>Leader board</h5>
            <CardGroup>
                <Card>
                    <CardImg
                        alt="Card image cap"
                        src="https://reactjs.org/logo-og.png"
                        top
                        width="100%"
                        // style={{maxWidth: "300px"}}
                    />
                    <CardBody>
                        <CardTitle tag="h5">
                            Card title
                        </CardTitle>
                        <CardSubtitle
                            className="mb-2 text-muted"
                            tag="h6"
                        >
                            Card subtitle
                        </CardSubtitle>
                        <CardText>
                            This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.
                        </CardText>
                    </CardBody>
                </Card>

                <Card>
                    <CardImg
                        alt="Card image cap"
                        src="https://reactjs.org/logo-og.png"
                        top
                        width="100%"
                        // style={{maxWidth: "300px"}}
                    />
                    <CardBody>
                        <CardTitle tag="h5">
                            Card title
                        </CardTitle>
                        <CardSubtitle
                            className="mb-2 text-muted"
                            tag="h6"
                        >
                            Card subtitle
                        </CardSubtitle>
                        <CardText>
                            This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.
                        </CardText>
                    </CardBody>
                </Card>

                <Card>
                    <CardImg
                        alt="Card image cap"
                        src="https://reactjs.org/logo-og.png"
                        top
                        width="100%"
                        // style={{maxWidth: "300px"}}
                    />
                    <CardBody>
                        <CardTitle tag="h5">
                            Card title
                        </CardTitle>
                        <CardSubtitle
                            className="mb-2 text-muted"
                            tag="h6"
                        >
                            Card subtitle
                        </CardSubtitle>
                        <CardText>
                            This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.
                        </CardText>
                    </CardBody>
                </Card>
            </CardGroup>
        </div>
    );
}

export default LeaderBoardList;