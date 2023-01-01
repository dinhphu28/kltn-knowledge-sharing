import React, { useEffect, useState } from 'react';
// import PropTypes from 'prop-types';
import articleApi from '../../apis/articleApi';
import ArticleCard from '../MainPage/Articles/List/Item/ArticleCard';
import PaginationBar from '../MainPage/Articles/List/Pagination/PaginationBar';

// ScreenArticleSearchResult.propTypes = {
    
// };

function ScreenArticleSearchResult(props) {

    const searchStrVal = props.searchString;
    const searchFiltersVal = props.searchFilters;
    console.log("SR Search filters: ", searchFiltersVal);

    const [articlesCrude, setArticlesCrude] = useState({});
    const [loaded, setLoaded] = useState(false);
    const [page, setPage] = useState(0);

    useEffect(() => {
        const fetchListArticlesSearchResult = async () => {
            try {
                const params = {
                    q: searchStrVal,
                    category: searchFiltersVal.category,
                    from: searchFiltersVal.from,
                    to: searchFiltersVal.to,
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
    }, [page, searchFiltersVal.category, searchFiltersVal.from, searchFiltersVal.to, searchStrVal]);

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
                <h6 style={{marginLeft: "2rem", marginTop: "1rem"}}>{articlesCrude.numberOfPages > 0 ? "Showing results for: " : "No result for:"} {searchStrVal}</h6>
            </div>
            
            <div style={{marginLeft: "2rem", marginRight: "2rem"}}>
                {listItems}
            </div>

            {articlesCrude.numberOfPages > 0 ? <PaginationBar
                numberOfPages={articlesCrude.numberOfPages}
                currentPage={articlesCrude.currentPage}
                onHandleChange={receivePage}
            /> : ""}
        </div>
    );
}

export default ScreenArticleSearchResult;