import React from 'react';
import { Card, CardBody, CardColumns, CardSubtitle, CardTitle } from 'reactstrap';
// import PropTypes from 'prop-types';

// ReadingList.propTypes = {
    
// };

function ReadingList(props) {
    return (
        <div style={{marginLeft: "1rem"}}>
            <h5>Reading</h5>
            <CardColumns>
                <Card
                    outline
                    color='light'
                    // style={{marginBottom: "1rem"}}
                >
                    <CardBody>
                        <CardTitle tag="h5">
                            Lorem ipsum dolor sit amet consectetur
                        </CardTitle>
                        <CardSubtitle>
                            Card Subtitle
                        </CardSubtitle>
                    </CardBody>
                </Card>
        
                <Card
                    outline
                    color='light'
                    // style={{marginBottom: "1rem"}}
                >
                    <CardBody>
                        <CardTitle tag="h5">
                            Card title Lorem ipsum dolor sit amet.
                        </CardTitle>
                        <CardSubtitle>
                            Card Subtitle
                        </CardSubtitle>
                    </CardBody>
                </Card>

                <Card
                    outline
                    color='light'
                    // style={{marginBottom: "1rem"}}
                >
                    <CardBody>
                        <CardTitle tag="h5">
                            Card title Lorem ipsum dolor sit.
                        </CardTitle>
                        <CardSubtitle>
                            Card Subtitle
                        </CardSubtitle>
                    </CardBody>
                </Card>

                <Card
                    outline
                    color='light'
                    // style={{marginBottom: "1rem"}}
                >
                    <CardBody>
                        <CardTitle tag="h5">
                            Card title Lorem ipsum dolor sit.
                        </CardTitle>
                        <CardSubtitle>
                            Card Subtitle
                        </CardSubtitle>
                    </CardBody>
                </Card>

                <Card
                    outline
                    color='light'
                    // style={{marginBottom: "1rem"}}
                >
                    <CardBody>
                        <CardTitle tag="h5">
                            Card title Lorem ipsum dolor sit.
                        </CardTitle>
                        <CardSubtitle>
                            Card Subtitle
                        </CardSubtitle>
                    </CardBody>
                </Card>
            </CardColumns>
        </div>
    );
}

export default ReadingList;