import React, { useEffect, useState } from 'react';
import { Card, CardBody, CardGroup, CardImg, CardSubtitle, CardText, CardTitle } from 'reactstrap';
// import PropTypes from 'prop-types';
import leaderBoardArticleApi from '../../../apis/leaderBoardArticleApi';
import { BASE_URL_API_BE } from '../../../constants/global';
import { Link } from 'react-router-dom';

// LeaderBoardList.propTypes = {
    
// };

function LeaderBoardList(props) {

    const [listLeaderBoardArticles, setListLeaderBoardArticles] = useState([]);

    const [articleItem1, setArticleItem1] = useState(undefined);
    const [articleItem2, setArticleItem2] = useState(undefined);
    const [articleItem3, setArticleItem3] = useState(undefined);

    useEffect(() => {
        const fetchListLeaderBoardArticles = async () => {
            try {
                const response = await leaderBoardArticleApi.getAll();

                setListLeaderBoardArticles(response);

                setArticleItem1(response.filter(item => item.id === "leader-board-1")[0]);
                setArticleItem2(response.filter(item => item.id === "leader-board-2")[0]);
                setArticleItem3(response.filter(item => item.id === "leader-board-3")[0]);

                console.log("Fetch list leader board articles successfully: ", response);
                console.log("Fetch list leader board articles successfully --- ITEM: ", response.filter(item => item.id === "leader-board-2")[0]);
            } catch (error) {
                console.log("Failed to fetch leader board articles: ", error);
            }
        }

        fetchListLeaderBoardArticles();
    }, []);

    return (
        <div>
            <h5>Leader board</h5>
            <CardGroup>
                <Card>
                    <CardImg
                        alt="Card image cap"
                        src={articleItem1 !== undefined ? BASE_URL_API_BE + "/files/downloadFile/" + articleItem1.thumbnailUrl : "https://reactjs.org/logo-og.png"}
                        top
                        width="100%"
                        style={{maxHeight: "12rem", objectFit: "cover"}}
                        // style={{maxWidth: "300px"}}
                    />
                    <CardBody>
                        <CardTitle tag="h5">
                            {articleItem1 !== undefined ? <Link to={"/articles/" + articleItem1.url}>{articleItem1.title}</Link> : "Blank"}
                        </CardTitle>
                        <CardSubtitle
                            className="mb-2 text-muted"
                            tag="h6"
                        >
                            {articleItem1 !== undefined ? articleItem1.author : "Author"}
                        </CardSubtitle>
                        <CardText>
                            {articleItem1 !== undefined ? articleItem1.description : "Author"}
                        </CardText>
                    </CardBody>
                </Card>

                <Card>
                    <CardImg
                        alt="Card image cap"
                        src={articleItem2 !== undefined ? BASE_URL_API_BE + "/files/downloadFile/" + articleItem2.thumbnailUrl : "https://reactjs.org/logo-og.png"}
                        top
                        width="100%"
                        style={{maxHeight: "12rem", objectFit: "cover"}}
                        // style={{maxWidth: "300px"}}
                    />
                    <CardBody>
                        <CardTitle tag="h5">
                        {articleItem2 !== undefined ? <Link to={"/articles/" + articleItem2.url}>{articleItem2.title}</Link> : "Blank"}
                        </CardTitle>
                        <CardSubtitle
                            className="mb-2 text-muted"
                            tag="h6"
                        >
                            {articleItem2 !== undefined ? articleItem2.author : "Author"}
                        </CardSubtitle>
                        <CardText>
                            This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.
                        </CardText>
                    </CardBody>
                </Card>

                <Card>
                    <CardImg
                        alt="Card image cap"
                        src={articleItem3 !== undefined ? BASE_URL_API_BE + "/files/downloadFile/" + articleItem3.thumbnailUrl : "https://reactjs.org/logo-og.png"}
                        top
                        width="100%"
                        style={{maxHeight: "12rem", objectFit: "cover"}}
                        // style={{maxWidth: "300px"}}
                    />
                    <CardBody>
                        <CardTitle tag="h5">
                        {articleItem3 !== undefined ? <Link to={"/articles/" + articleItem3.url}>{articleItem3.title}</Link> : "Blank"}
                        </CardTitle>
                        <CardSubtitle
                            className="mb-2 text-muted"
                            tag="h6"
                        >
                            {articleItem3 !== undefined ? articleItem3.author : "Author"}
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