import React from 'react';
import { Link } from 'react-router-dom';
import { Card, CardBody, CardColumns, CardSubtitle, CardTitle } from 'reactstrap';
// import PropTypes from 'prop-types';

// ReadingList.propTypes = {
    
// };

function ReadingList(props) {

    const loadReadingListArr = () => {
        const readingListStr = localStorage.getItem("readingList");

        if(readingListStr !== null) {
            const arrReadingList = JSON.parse(readingListStr);

            return arrReadingList.map((item) =>
                <Card
                    key={item.id}
                    outline
                    color='light'
                // style={{marginBottom: "1rem"}}
                >
                    <CardBody>
                        <CardTitle tag="h5">
                            <Link to={"/articles/" + item.url}>{item.title}</Link>
                        </CardTitle>
                        <CardSubtitle>
                            {item.author}
                        </CardSubtitle>
                    </CardBody>
                </Card>
            );
        }
    }

    return (
        <div style={{marginLeft: "1rem"}}>
            <h5>Reading</h5>
            <CardColumns>
                {/* <Card
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
                </Card> */}
                {loadReadingListArr()}
            </CardColumns>
        </div>
    );
}

export default ReadingList;