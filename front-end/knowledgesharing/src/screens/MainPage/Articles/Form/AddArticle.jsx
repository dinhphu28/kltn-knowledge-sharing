import React, { useEffect, useState } from 'react';
import { Button, Input, Label } from 'reactstrap';
import articleApi from '../../../../apis/articleApi';
import categoryApi from '../../../../apis/categoryApi';
import UploadFiles from '../../../../components/FileUpload/FileUpload';
// import PropTypes from 'prop-types';

// AddArticle.propTypes = {
    
// };

function AddArticle(props) {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [content, setContent] = useState("");
    const [thumbnailUrl, setThumbnailUrl] = useState("");
    const [audioFileName, setAudioFileName] = useState("");
    const [category, setCategory] = useState("Abc");
    const [categoryList, setCategoryList] = useState([]);
    const [loaded, setLoaded] = useState(false);

    useEffect(() => {
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
                        key={item.name}
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
            console.log("Value in event: ", e.target.value);
            console.log("Value in state: ", category);
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

        if(audioFileName.length < 10) {
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
            createArticleToBE();

            const data = {
                // author: localStorage.getItem("username"),
                // title: title,
                // description: description,
                // content: content,
                // audioContent: audioFileName,
                // thumbnailUrl: thumbnailUrl,
                // category: category,

                createdBy: localStorage.getItem("username"),
                createdByName: "AA",
                title: title,
                description: description,
                content: content,
                audioContent: audioFileName,
                author: localStorage.getItem("username"),
                category: category,
                thumbnailUrl: thumbnailUrl,
                tags: []
            };

            console.log("VZVZ: ", data);
        }
    }

    const createArticleToBE = async () => {
        if(audioFileName !== "") {
            try {
                const data = {
                    // author: localStorage.getItem("username"),
                    // title: title,
                    // description: description,
                    // content: content,
                    // audioContent: audioFileName,
                    // thumbnailUrl: thumbnailUrl,
                    // category: category,

                    createdBy: localStorage.getItem("username"),
                    createdByName: "AA",
                    title: title,
                    description: description,
                    content: content,
                    audioContent: audioFileName,
                    author: localStorage.getItem("username"),
                    category: category,
                    thumbnailUrl: thumbnailUrl,
                    tags: []
                };
    
                const response = await articleApi.post(data);
    
                console.log("Post article successfully: ", response);
    
            } catch(error) {
                console.log("Failed to post article to BE: ", error.request);
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

    const receiveAudioUrl = (auFileName) => {
        setAudioFileName(auFileName);
    }

    const receiveImageUrl = (imgFileName) => {
        setThumbnailUrl(imgFileName);
    }

    return (
        <div>
            <form
                className="container"
                style={{paddingTop: "2%"}}
                onSubmit={e => {
                    submitForm(e);
                }}
            >
                <div className="title-area my-glob">
                    <Label>
                        Title:
                    </Label>
                    <Input
                        type="textarea"
                        name="title"
                        onChange={e => changeInputValueTitle(e)}
                    />
                </div>
                <div className="description-area my-glob">
                    <Label>
                        Description:
                    </Label>
                    <Input
                        type="textarea"
                        name="description"
                        onChange={e => changeInputValueDescription(e)}
                    />
                </div>
                <div className="content-area my-glob">
                    <Label>
                        Content:
                    </Label>
                    <Input
                        type="textarea"
                        name="content"
                        onChange={e => changeInputValueContent(e)}
                    />
                </div>
                <div className="thumbnail-area my-glob">
                    {/* <Label>
                        Thumbnail URL:
                    </Label>
                    <Input
                        type="url"
                        name="thumbnail"
                        onChange={e => changeInputValueThumbnailUrl(e)}
                    /> */}
                    <Label>
                        Upload thumbnail image:
                    </Label>
                    <UploadFiles onHandleChange={receiveImageUrl} />
                    <hr />
                </div>
                <div className="audio-upload-area my-glob">
                    <Label>
                        Upload audio file:
                    </Label>
                    <UploadFiles onHandleChange={receiveAudioUrl} />
                    <hr />
                </div>
                <div className="category-area my-glob">
                    <Label>
                        Category:
                    </Label>
                    <Input
                        type="select"
                        name="category"
                        onChange={e => changeInputValueCategory(e)}
                    >
                        {/* <option>
                            {ARTICLE_CATEGORIES.front_end.label}
                        </option>
                        <option>
                            {ARTICLE_CATEGORIES.back_end.label}
                        </option>
                        <option>
                            {ARTICLE_CATEGORIES.ios.label}
                        </option>
                        <option>
                            {ARTICLE_CATEGORIES.android.label}
                        </option>
                        <option>
                            {ARTICLE_CATEGORIES.tips_tricks.label}
                        </option> */}
                        {loaded ? loadListCategory() : undefined}
                    </Input>
                </div>
                <div className="confirm-btn">
                    <Button id="btn-create"
                        block
                        color="primary"
                        type="submit"
                    >
                        Create article
                    </Button>
                </div>
            </form>
        </div>
    );
}

export default AddArticle;