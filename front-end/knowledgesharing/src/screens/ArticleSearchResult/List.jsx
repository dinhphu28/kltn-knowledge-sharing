import React, { useEffect, useState } from 'react';
// import PropTypes from 'prop-types';
import articleApi from '../../apis/articleApi';
import ArticleCard from '../MainPage/Articles/List/Item/ArticleCard';
import PaginationBar from '../MainPage/Articles/List/Pagination/PaginationBar';

// ScreenArticleSearchResult.propTypes = {
    
// };

function ScreenArticleSearchResult(props) {

    const searchStrVal = props.searchString;

    const [articlesCrude, setArticlesCrude] = useState({});
    const [loaded, setLoaded] = useState(false);
    const [page, setPage] = useState(0);

    useEffect(() => {
        const fetchListArticlesSearchResult = async () => {
            try {
                const params = {
                    q: searchStrVal,
                    page: page
                }

                console.log("DT search: ", params);

                const response = await articleApi.getWithSearch(params);

                console.log("Fetch list articles with search successfully: ", response);

                setArticlesCrude(response);

                setLoaded(true);

            } catch (error) {
                console.log("Failed to fetch list articles with search: ", error);
            }
        }

        fetchListArticlesSearchResult();
    }, [page, searchStrVal]);

    const receivePage = (indexPage) => {
        setPage(indexPage);
        // props.onHandleChange(indexPage);
    }

    const listItems = loaded ? articlesCrude.articles.map((item) => 
        <ArticleCard key={item.id} article={item} />
    ) : "";

    return (
        <div>
            <div>
                <h6 style={{marginLeft: "2rem"}}>Showing results for: {searchStrVal}</h6>
            </div>
            
            <div style={{marginLeft: "2rem", marginRight: "2rem"}}>
                {listItems}
            </div>

            <PaginationBar
                numberOfPages={articlesCrude.numberOfPages}
                currentPage={articlesCrude.currentPage}
                onHandleChange={receivePage}
            />
        </div>
    );
}

export default ScreenArticleSearchResult;