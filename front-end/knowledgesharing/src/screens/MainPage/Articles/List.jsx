import React, { useEffect } from 'react';
import { useState } from 'react';
import articleApi from '../../../apis/articleApi';
import ArticleCard from './List/Item/ArticleCard';
import PaginationBar from './List/Pagination/PaginationBar';
import { Button, ButtonGroup, Card, CardBody, Collapse, Input } from 'reactstrap';
import articleTagApi from '../../../apis/articleTagApi';
import "./ScreenArticleList.css"
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
    const [listTagsInCategory, setListTagsInCategory] = useState([]);

    const [selectTagsCollapseShow, setSelectTagsCollapseShow] = useState(false);

    const [tagsSelected, setTagsSelected] = useState([]);
    const [tagsSelectedSubmitted, setTagsSelectedSubmitted] = useState([]);

    useEffect(() => {
        
        const fetchListTagsByCategory = async () => {
            try {
                const params = {
                    category: category.id
                };

                const response = await articleTagApi.getAll(params);

                setListTagsInCategory(response);

                console.log("Fetch list tags by category successfully: ", response);
            } catch (error) {
                console.log("Failed to fetch list tags by category: ", error);
            }
        }

        const fetchArticleCrude = async () => {
            try {
                setLoaded(false);

                const params = {
                    page: page,
                    category: category.id,
                    hidden: hidden,
                    tagids: tagsSelectedSubmitted
                //     tagids: [
                //         "204c375e-eb40-49de-a556-b751899723d4",
                //         "4ad1a2e7-7d7d-4343-a6d6-251aa6e4ebd6"
                //     ]
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
        fetchListTagsByCategory();
    }, [category, hidden, page, propCategory, tagsSelectedSubmitted]);

    const receivePage = (indexPage) => {
        // alert("KKKK: " + indexPage);
        setPage(indexPage);
        props.onHandleChange(indexPage);
    }

    const changeSelectedTagsValue = (e) => {
        // setTagsSelected(e.target.selectedOptions);
        const value = Array.from(e.target.selectedOptions, option => option.value);
        setTagsSelected(value);
    }

    const listJsxTagItems = listTagsInCategory.map((item) =>
        <option key={item.id} value={item.id}>
            {item.tagName}
        </option>
    )

    // const {articlesCrude} = props;
    const listItems = loaded ? articlesCrude.articles.map((item) => 
        <ArticleCard key={item.id} article={item} />
    ) : "";

    return (
        // <div style={{marginLeft: "15rem", marginRight: "15rem"}}>
        <div>
            <div className='my-flex-sec'>
                <div className='my-flex-sec-left'>
                    {category.id !== undefined ? <><Button
                        color='primary'
                        outline
                        onClick={() => {
                            setSelectTagsCollapseShow(!selectTagsCollapseShow);
                        }}
                        style={{marginBottom: "1rem"}}
                    >
                        Select tags
                    </Button>
                    <Collapse
                        isOpen={selectTagsCollapseShow}
                    >
                        <Card>
                            <CardBody>
                                <Input
                                    style={{marginBottom: "0.5rem"}}
                                    multiple
                                    type='select'
                                    onChange={e => {
                                        changeSelectedTagsValue(e);
                                    }}
                                >
                                    {loaded ? listJsxTagItems : ""}
                                </Input>
                                <Button
                                    color='primary'
                                    size='sm'
                                    onClick={() => {
                                        // console.log("VVZ: ", tagsSelected);
                                        setTagsSelectedSubmitted(tagsSelected);
                                    }}
                                >
                                    Submit
                                </Button>
                                {" "}
                                <Button
                                    // color='primary'
                                    size='sm'
                                    onClick={() => {
                                        // console.log("VVZ: ", tagsSelected);
                                        setTagsSelectedSubmitted([]);
                                    }}
                                >
                                    Clear
                                </Button>
                            </CardBody>
                        </Card>
                    </Collapse></>
                    : ""}
                </div>
                <div className='my-flex-sec-right'>
                    <ButtonGroup style={{display: "flex", justifyContent: "space-between", marginLeft: "55%"}}>
                        <Button
                            color={hidden ? "secondary" : "primary"}
                            onClick={() => {
                                setHidden(false);
                                props.onHandleChangeHid(false);
                            }}
                        >
                            Shown article
                        </Button>
                        <Button
                            color={hidden ? "primary" : "secondary"}
                            onClick={() => {
                                setHidden(true);
                                props.onHandleChangeHid(true);
                            }}
                        >
                            Hidden article
                        </Button>
                    </ButtonGroup>
                </div>
            </div>
            

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