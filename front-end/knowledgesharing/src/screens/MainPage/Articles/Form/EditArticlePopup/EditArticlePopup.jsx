import React, { useEffect, useState } from 'react';
import { Button, Input, Label } from 'reactstrap';
import { BASE_URL_API_BE } from '../../../../../constants/global';
import "./EditArticlePopup.css";
// import PropTypes from 'prop-types';
import UploadFiles from '../../../../../components/FileUpload/FileUpload';
import articleApi from '../../../../../apis/articleApi';
import categoryApi from '../../../../../apis/categoryApi';

// EditArticlePopup.propTypes = {
    
// };

function EditArticlePopup(props) {
    const {article} = props;

    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [content, setContent] = useState("");
    const [thumbnailUrl, setThumbnailUrl] = useState("");
    const [audioFileName, setAudioFileName] = useState("");
    const [category, setCategory] = useState(article.category);
    const [newArticle, setNewArticle] = useState({});
    const [categoryList, setCategoryList] = useState([]);
    const [loaded, setLoaded] = useState(false);

    useEffect(() => {
        setNewArticle(article);

        setTitle(article.title);
        setDescription(article.description);
        setContent(article.content);
        setThumbnailUrl(article.thumbnailUrl);
        setAudioFileName(article.audioContent);
        // setCategory(article.category);

        const fetchCategory = async () => {
            try {
                const response = await categoryApi.getAll();

                console.log("Fetch category successfully: ", response);

                setCategoryList(response);

                setLoaded(true);
            } catch (error) {
                console.log("Failed to fetch category: ", error);
            }
        }

        fetchCategory();

    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    const isInDefaultCategory = (catNameParam) => {
        if(catNameParam === "front-end" ||
            catNameParam === "back-end" ||
            catNameParam === "ios" ||
            catNameParam === "android" ||
            catNameParam === "tips-tricks" ||
            catNameParam === undefined
        ) {
            return true;
        } else {
            return false;
        }
    };

    const isInDefaultCategoryLabel = (catLabelParam) => {
        // if(catLabelParam === ARTICLE_CATEGORIES.front_end.label ||
        //     catLabelParam === ARTICLE_CATEGORIES.back_end.label ||
        //     catLabelParam === ARTICLE_CATEGORIES.ios.label ||
        //     catLabelParam === ARTICLE_CATEGORIES.android.label ||
        //     catLabelParam === ARTICLE_CATEGORIES.tips_tricks.label
        // ) {
        //     return true;
        // } else {
        //     return false;
        // }

        return false;
    }

    const loadListCategory = () => {
        if(loaded) {
            const listItems = categoryList.map((item) => {
                if(!isInDefaultCategory(item.name)) {
                    return <option
                        key={item.id}
                        value={item.id}
                    >
                        {item.name}
                    </option>
                }
                
                return "";
            });

            return listItems;
        }
    };

    const changeInputValueTitle = (e) => {
        setTitle(e.target.value.trim());
    };
    const changeInputValueDescription = (e) => {
        setDescription(e.target.value);
    };
    const changeInputValueContent = (e) => {
        setContent(e.target.value);
    };
    // const changeInputValueThumbnailUrl = (e) => {
    //     setThumbnailUrl(e.target.value);
    // };
    const changeInputValueCategory = (e) => {
        if(!isInDefaultCategoryLabel(e.target.value)) {
            setCategory(e.target.value);
        } else {
            // setCategory(getQueryValueFromLabel(e.target.value));
        }

        // console.log("Changed category: ", category);
    };

    const validationForm = () => {
        let returnData = {
            error: false,
            msg: ""
        }

        if(title.length < 1) {
            returnData = {
                error: true,
                msg: "Title can't be empty"
            }
        }

        if(description.length < 1) {
            returnData = {
                error: true,
                msg: "Description can't be empty"
            }
        }

        if(content.length < 1) {
            returnData = {
                error: true,
                msg: "Content can't be empty"
            }
        }

        if(thumbnailUrl.length < 10) {
            returnData = {
                error: true,
                msg: "Wrong url length"
            }
        }

        return returnData;
    };

    const submitForm = (e) => {
        e.preventDefault();

        const validation = validationForm();

        if(validation.error) {
            alert(validation.msg);
        } else {
            alert("Submit success")
            // handle submit ok here
            updateArticleToBE();

            props.onHandleChange();
        }
    }

    const updateArticleToBE = async () => {
        if(audioFileName !== "") {
            try {
                const data = {
                    modifiedBy: localStorage.getItem("username"),
                    modifiedByName: localStorage.getItem("username"),
                    title: title,
                    description: description,
                    content: content,
                    audioContent: audioFileName,
                    category: category,
                    thumbnailUrl: thumbnailUrl,
                    tags: []
                };

                console.log("Data to update: ", data);
    
                const response = await articleApi.put(article.id, data);
    
                console.log("Update article successfully: ", response);
    
            } catch(error) {
                console.log("Failed to post article to BE: ", error);
            }
        }
    }

    // const getQueryValueFromLabel = (label) => {
    //     if(label === ARTICLE_CATEGORIES.front_end.label) {

    //         return ARTICLE_CATEGORIES.front_end.queryValue

    //     } else if(label === ARTICLE_CATEGORIES.back_end.label) {

    //         return ARTICLE_CATEGORIES.back_end.queryValue

    //     } else if(label === ARTICLE_CATEGORIES.ios.label) {

    //         return ARTICLE_CATEGORIES.ios.queryValue

    //     } else if(label === ARTICLE_CATEGORIES.android.label) {

    //         return ARTICLE_CATEGORIES.android.queryValue

    //     } else if(label === ARTICLE_CATEGORIES.tips_tricks.label) {

    //         return ARTICLE_CATEGORIES.tips_tricks.queryValue
    //     }
    // };

    // const getLabelFromQueryValue = (label) => {
    //     if(label === ARTICLE_CATEGORIES.front_end.queryValue) {

    //         return ARTICLE_CATEGORIES.front_end.label

    //     } else if(label === ARTICLE_CATEGORIES.back_end.queryValue) {

    //         return ARTICLE_CATEGORIES.back_end.label

    //     } else if(label === ARTICLE_CATEGORIES.ios.queryValue) {

    //         return ARTICLE_CATEGORIES.ios.label

    //     } else if(label === ARTICLE_CATEGORIES.android.queryValue) {

    //         return ARTICLE_CATEGORIES.android.label

    //     } else if(label === ARTICLE_CATEGORIES.tips_tricks.queryValue) {

    //         return ARTICLE_CATEGORIES.tips_tricks.label
    //     }
    // };

    const receiveAudioUrl = (auFileName) => {
        setAudioFileName(auFileName);
    }

    const receiveImageUrl = (imgFileName) => {
        setThumbnailUrl(imgFileName);
    }

    return (
        <div>
            <div className="layer"></div>
            <div className="my-popup2">
                <form
                    className="container"
                    // style={{paddingTop: "2%"}}
                    onSubmit={e => {
                        submitForm(e);
                    }}
                >
                    <div className="title-area2 my-glob2">
                        <Label>
                            Title:
                        </Label>
                        <Input
                            type="textarea"
                            name="title"
                            onChange={e => changeInputValueTitle(e)}
                            defaultValue={newArticle.title}
                        />
                    </div>
                    <div className="description-area2 my-glob2">
                        <Label>
                            Description:
                        </Label>
                        <Input
                            type="textarea"
                            name="description"
                            onChange={e => changeInputValueDescription(e)}
                            defaultValue={newArticle.description}
                        />
                    </div>
                    <div className="content-area2 my-glob2">
                        <Label>
                            Content:
                        </Label>
                        <Input
                            type="textarea"
                            name="content"
                            onChange={e => changeInputValueContent(e)}
                            defaultValue={newArticle.content}
                        />
                    </div>
                    <div className="thumbnail-area2 my-glob2">
                        {/* <Label>
                            Thumbnail URL:
                        </Label>
                        <Input
                            type="url"
                            name="thumbnail"
                            onChange={e => changeInputValueThumbnailUrl(e)}
                            defaultValue={newArticle.thumbnailUrl}
                        /> */}
                        <Label>
                            Upload thumbnail image:
                        </Label>
                        <UploadFiles onHandleChange={receiveImageUrl} />
                        <hr />
                    </div>
                    <div className="audio-upload-area my-glob">
                        <Label>
                            Old audio file: <a href={BASE_URL_API_BE + "/files/downloadFile/" + article.audioContent}>Download old audio</a>
                        </Label>
                        <hr />

                        <Label>
                            Upload audio file:
                        </Label>
                        <UploadFiles onHandleChange={receiveAudioUrl} />
                        <hr />
                    </div>
                    <div className="category-area2 my-glob2">
                        <Label>
                            Category:
                        </Label>
                        {loaded ? <Input
                            type="select"
                            name="category"
                            onChange={e => changeInputValueCategory(e)}
                            // defaultValue={getLabelFromQueryValue(article.category)}
                            defaultValue={{value: categoryList[2].id}}
                            // value={{value: "6481d87b-9e30-48b1-8e0f-8bc24af2b3f7"}}
                        >
                            {loaded ? loadListCategory() : undefined}
                        </Input> : ""}
                    </div>
                    <div className="confirm-btn2">
                        <Button id="btn-update2"
                            block
                            color="primary"
                            type="submit"
                        >
                            Update article
                        </Button>
                        <Button id="btn-cancel2"
                            block
                            color="secondary"
                            type="button"
                            onClick={() => {
                                props.onHandleChange();
                            }}
                        >
                            Cancel
                        </Button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default EditArticlePopup;