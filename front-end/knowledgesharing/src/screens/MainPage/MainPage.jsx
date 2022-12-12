import React, { useEffect, useState } from 'react';
import { Route, Routes } from 'react-router-dom';
import { Button, Input, InputGroup } from 'reactstrap';
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

            return listItems;
        }
    };

    return (
        <div>
            <CategoryNavMenu onHandleChangeCat={receiveCategory} onHandleChangeHid={receiveHidden} />

            <div className='col-4' style={{display: "inline-block", margin: "1rem"}}>
                <InputGroup>
                    <Input />
                    <Button>
                        Search
                    </Button>
                </InputGroup>
            </div>

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
                        /> : <p>Loading...</p>}
                />

                {loadListRoute()}

            </Routes>
        </div>
    );
}

export default ScreenMainPage;