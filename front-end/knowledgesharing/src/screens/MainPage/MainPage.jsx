import React, { useEffect, useState } from 'react';
import { Route, Routes, useLocation } from 'react-router-dom';
import articleApi from '../../apis/articleApi';
import CategoryNavMenu from '../../components/Category/NavMenu';
import ScreenArticleFormContent from './Articles/Form/Content';
// import PropTypes from 'prop-types';
import ScreenArticleList from './Articles/List';

// ScreenMainPage.propTypes = {
    
// };

function ScreenMainPage(props) {

    const [page, setPage] = useState(0);
    const [category, setCategory] = useState({id: undefined, name: "Newest"})
    const [hidden, setHidden] = useState(false);
    const [loaded, setLoaded] = useState(false);
    const [articlesCrude, setArticlesCrude] = useState([]);

    const [redundantArticleByUrl, setRedundantArticleByUrl] = useState(null);
    const [loaded2, setLoaded2] = useState(false);

    const location = useLocation();
    console.log("Curr Location: ", location.pathname.substring(10));

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

        const fetchArticleByUrl = async () => {
            try {
                const params = {
                    hidden: ((localStorage.getItem("role") === "mod") || localStorage.getItem("role") === "admin") ? false : true
                }
                const response = await articleApi.getByUrl(location.pathname.substring(10), params);

                console.log("Fetch article by URL successfully: ", response);

                setRedundantArticleByUrl(response);

                setLoaded2(true);

                // setArticleDetails(response);
                // setTmpVoteScore(response.voteScore);

                console.log("My URL: ", location.pathname.substring(10));
            } catch (error) {
                console.log("Failed to fetch article by URL: ", error);
            }
        }

        if(location.pathname !== "/articles") {
            fetchArticleByUrl();
        }

        const fetchArticleCrude = async () => {
            try {
                const params = {
                    page: page,
                    category: category.id,
                    hidden: hidden
                };

                const response = await articleApi.getAll(params);

                console.log("Main Page: Fetch article crude successfully", response);

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
    }, [category, hidden, page]);

    const receiveCategory = (category) => {
        setPage(0);
        setCategory(category);
        setLoaded(false);

        // console.log("YY: ", category);
    };

    const receiveHidden = (hidden) => {
        setPage(0);
        setHidden(hidden);
        // setLoaded(!loaded);
        // setLoaded(!loaded);
    };

    const receivePage = (indexPage) => {
        setPage(indexPage);
    };

    const loadListRoute = () => {
        if(loaded) {
            const listItems = articlesCrude.articles.map((item) =>
                <Route key={item.id} path={item.url} element={<ScreenArticleFormContent article={item} />} />
                // <Route key={item.id} path={item.url} element={<ArticleContent
                //     title="Tieu de cung nhe"
                //     author={item.author}
                //     content={item.content}
                //     dateCreated={item.dateCreated}
                // />} />
            );
            // console.log("Default: ", "beffff")

            return listItems;
        }
    };

    const redundantArticleByUrlRoute = () => {

        if(loaded2) {
            // console.log("Redunt: ", "affff")

            return (
                <Route key={"red-" + redundantArticleByUrl.id} path={redundantArticleByUrl.url} element={<ScreenArticleFormContent article={redundantArticleByUrl} />} />
            );
        }
    }

    return (
        <div style={{marginLeft: "2rem", marginRight: "2rem"}}>
            <CategoryNavMenu onHandleChangeCat={receiveCategory} onHandleChangeHid={receiveHidden} />

            {/* <div className='col-4' style={{display: "inline-block", margin: "1rem", marginLeft: "2rem"}}>
                <InputGroup>
                    <Input />
                    <Button>
                        Search
                    </Button>
                </InputGroup>
            </div> */}

            <Routes>
                {/* <Route path="/meo" element={<SignUpPage />} /> */}
                {/* {listRoutes} */}
                {/* <Route path="*" element={<NotFound />} /> */}

                <Route
                    path="/"
                    element={
                        loaded ? <ScreenArticleList
                            // articlesCrude={articlesCrude}
                            category={category}
                            // // page={page}
                            onHandleChange={receivePage}
                            onHandleChangeHid={receiveHidden}
                        /> : <p>Loading...</p>}
                />

                {loadListRoute()}

                {/* {((localStorage.getItem("role") === "mod") || localStorage.getItem("role") === "admin") ? "" : ""} */}

                {loaded2 && location.pathname !== "/articles" ? redundantArticleByUrlRoute() : ""}
                {/* {redundantArticleByUrlRoute()} */}

            </Routes>
        </div>
    );
}

export default ScreenMainPage;