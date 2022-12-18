import React, { useEffect } from 'react';
import { useState } from 'react';
import articleApi from '../../../apis/articleApi';
import ArticleCard from './List/Item/ArticleCard';
import PaginationBar from './List/Pagination/PaginationBar';
// import PropTypes from 'prop-types';

// ScreenArticleList.propTypes = {
    
// };

function ScreenArticleList(props) {

    const propCategory = props.category;

    const [page, setPage] = useState(0);
    // const [category, setCategory] = useState({id: undefined, name: "Newest"});
    const [category, setCategory] = useState(propCategory);
    const [hidden, setHidden] = useState(false);
    const [loaded, setLoaded] = useState(false);
    const [articlesCrude, setArticlesCrude] = useState({});

    useEffect(() => {
        // const fetchArticleCrude = async () => {
        //     try {
        //         const params = {page: page, category: category.queryValue};

        //         const response = await articleApi.getAll(params);

        //         // console.log("Fetch article crude successfully", response);

        //         const data = response;

        //         setArticlesCrude(data);

        //         // console.log("These are articles list: ", data);

        //         setLoaded(true);

        //     } catch(error) {
        //         // throw Promise;
        //         console.log("Failed to fetch article crude: ", error);
        //     }
        // }
        // fetchArticleCrude();

        const fetchArticleCrude = async () => {
            try {
                setLoaded(false);

                const params = {
                    page: page,
                    category: category.id,
                    hidden: hidden
                };

                const response = await articleApi.getAll(params);

                console.log("Fetch article crude successfully", response);

                const data = response;

                setArticlesCrude(data);

                // console.log("These are articles list: ", data);

                setLoaded(true);

            } catch(error) {
                // throw Promise;
                console.log("Failed to fetch article crude: ", error);
            }
        }
        fetchArticleCrude();
    }, [category, hidden, page, propCategory]);

    const receivePage = (indexPage) => {
        // alert("KKKK: " + indexPage);
        setPage(indexPage);
        props.onHandleChange(indexPage);
    }

    // const {articlesCrude} = props;
    const listItems = loaded ? articlesCrude.articles.map((item) => 
        <ArticleCard key={item.id} article={item} />
    ) : "";
    
    return (
        // <div style={{marginLeft: "15rem", marginRight: "15rem"}}>
        <div>
            {listItems}

        <PaginationBar
            numberOfPages={articlesCrude.numberOfPages}
            currentPage={articlesCrude.currentPage}
            onHandleChange={receivePage}
        />
        </div>
    );
}

export default ScreenArticleList;