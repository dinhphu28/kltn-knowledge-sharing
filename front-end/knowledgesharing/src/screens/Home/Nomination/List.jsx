import React, { Fragment, useState } from 'react';
import { Card, CardBody, CardGroup, CardImg, CardSubtitle, CardText, CardTitle } from 'reactstrap';
import "./NominatedArticleList.css";
import ReadingList from '../../../components/ReadingList/ReadingList';
// import PropTypes from 'prop-types';
import { useEffect } from 'react';
import nominatedArticleApi from '../../../apis/nominatedArticleApi';
import { BASE_URL_API_BE } from '../../../constants/global';

// NominatedArticleList.propTypes = {
    
// };

function NominatedArticleList(props) {

    const [listNominatedArticles, setListNominatedArticles] = useState([]);

    useEffect(() => {
        const fetchListNominatedArticles = async () => {
            try {
                const response = await nominatedArticleApi.getAll();

                setListNominatedArticles(response);

                console.log("Fetch list nominated articles successfully: ", response);
            } catch (error) {
                console.log("Failed to fetch nominated articles: ", error);
            }
        }

        fetchListNominatedArticles();
    }, []);

    const listJsxNominatedArticles = () => {

        const outerListJsx = [];
        let innerListJsx = [];

        for(let ii = 0; ii < 9; ii++) {

            if(ii < listNominatedArticles.length) {
                innerListJsx.push(
                    <Card key={"card-gr" + ii}>
                        <CardImg
                            alt="Card image cap"
                            src={BASE_URL_API_BE + "/files/downloadFile/" + listNominatedArticles[ii].thumbnailUrl}
                            top
                            width="100%"
                            style={{maxHeight: "11rem", objectFit: "cover"}}
                            // style={{maxWidth: "300px"}}
                        />
                        <CardBody>
                            <CardTitle tag="h5">
                                {listNominatedArticles[ii].title}
                            </CardTitle>
                            <CardSubtitle
                                className="mb-2 text-muted"
                                tag="h6"
                            >
                                {listNominatedArticles[ii].author}
                            </CardSubtitle>
                            <CardText>
                                {listNominatedArticles[ii].description.length > 15 ? listNominatedArticles[ii].description.substring(0, 75) + "..." : listNominatedArticles[ii].description}
                            </CardText>
                        </CardBody>
                    </Card>
                );
            } else {
                innerListJsx.push(
                    <Card key={"card" + ii}>
                        <CardImg
                            alt="Card image cap"
                            src="https://reactjs.org/logo-og.png"
                            top
                            width="100%"
                            style={{maxHeight: "11rem", objectFit: "cover"}}
                            // style={{maxWidth: "300px"}}
                        />
                        <CardBody>
                            <CardTitle tag="h5">
                                Blank - Position {ii}
                            </CardTitle>
                            <CardSubtitle
                                className="mb-2 text-muted"
                                tag="h6"
                            >
                                Author
                            </CardSubtitle>
                            <CardText>
                                {/* This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer. */}
                                Blank content
                            </CardText>
                        </CardBody>
                    </Card>
                );
            }

            if(innerListJsx.length === 3 && ii > 0) {
                outerListJsx.push(
                    <Fragment key={"fragment" + ii}>
                        <CardGroup key={ii}>
                            {innerListJsx}
                        </CardGroup>
                        <br key={"br" + ii}/>
                    </Fragment>
                );
            }

            if((ii + 1) % 3 === 0) {
                innerListJsx = [];
            }
        }

        return outerListJsx;
    }

    // console.log("yyy: ", listJsxNominatedArticles());

    return (
        <div className='nomination-home-sec'>
            {/* <div style={{marginRight: "25rem"}}> */}
            <div className='nomination-home-row-left'>
                <h5>
                    Nomination
                </h5>
                {/* <CardGroup>
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
                                Author
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
                                Author
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
                                Author
                            </CardSubtitle>
                            <CardText>
                                This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.
                            </CardText>
                        </CardBody>
                    </Card>
                </CardGroup>
                <br/>
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
                <br/>
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
                </CardGroup> */}

                {listJsxNominatedArticles()}
            </div>

            <div className='nomination-home-row-right'>
                <ReadingList />
            </div>
        </div>
    );
}

export default NominatedArticleList;