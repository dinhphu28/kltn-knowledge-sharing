import React from 'react';
import styled from "styled-components";
import { BASE_URL_API_BE } from '../../../../../constants/global';
import { Link } from "react-router-dom";
import { faCaretDown, faCaretUp } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
// import PropTypes from 'prop-types';

// ArticleCard.propTypes = {
    
// };

function ArticleCard(props) {

    const { article } = props;

    return (
        <div>
            <Card>
                <img src={BASE_URL_API_BE + "/files/downloadFile/" + article.thumbnailUrl} alt="thumbnail pic" />
                <Content>
                    <h2>
                        <Link to={"/articles/" + article.url}>{article.title}</Link>
                    </h2>
                    <p>
                        {article.description}
                    </p>
                </Content>
                <Author>Author: {article.author}</Author>
                <Date>Date: {article.dateCreated}</Date>
                <Vote>
                    <FontAwesomeIcon icon={faCaretUp} />{' '}
                    <span>{article.voteScore}</span>
                    <FontAwesomeIcon icon={faCaretDown} />
                </Vote>
            </Card>
        </div>
    );
}

const Card = styled.div`
    padding: 1.2rem 2rem;
    margin-top: 2rem;
    display: flex;
    position: relative;
    box-shadow: 1px 1px 2px rgba(30, 30, 30, 0.3);
    background: #fff;
    img {
        /* width: 250px; */
        /* height: 220px; */
        width: 15rem;
        height: 14rem;
        object-fit: cover;
    }
`;

const Vote = styled.span`
    display: flex;
    align-items: center;
    position: absolute;
    bottom: 1.2rem;
    right: 2rem;
    svg {
        font-size: 2rem;
        color: #888;
        cursor: pointer;
        &:not(:first-child) {
            margin-left: 0.35rem;
        }
    }
    span {
        margin-left: 0.35rem;
    }
`;

const Author = styled.div`
    position: absolute;
    bottom: 1.2rem;
    left: 40rem;
`;

const Date = styled.span`
    position: absolute;
    bottom: 1.2rem;
    left: 19.5rem;
`;

const Content = styled.div`
    margin-left: 1rem;
    h2 {
        a {
            color: #04d28f;
        }
    }
    p {
        margin-top: 1.5rem;
        display: inline-block;
    }
`;

export default ArticleCard;