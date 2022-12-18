import React, { useEffect, useState } from 'react';
import { Alert, Button, Input, Label, Modal, ModalBody, ModalFooter, ModalHeader, Table } from 'reactstrap';
// import PropTypes from 'prop-types';
import articleTagApi from '../../../apis/articleTagApi';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import categoryApi from '../../../apis/categoryApi';

// ArticleTagMan.propTypes = {
    
// };

function ArticleTagMan(props) {

    const [listTags, setListTags] = useState([]);
    const [modalIsOpen, setModalIsOpen] = useState(false);
    const [listCategory, setListCategory] = useState([]);
    const [selectedCategoryInModal, setSelectedCategoryInModal] = useState(null);
    const [inputTagName, setInputTagName] = useState("");
    const [showAlertSuccess, setShowAlertSuccess] = useState(false);
    const [showAlertFailure, setShowAlertFailure] = useState(false);

    useEffect(() => {
        const fetchListTags = async () => {
            try {
                const response = await articleTagApi.getAll();

                setListTags(response);

                console.log("Fetch list tags successfully: ", response);
            } catch (error) {
                console.log("Failed to fetch list tags: ", error);
            }
        }

        fetchListTags();

        const fetchListCategory = async () => {
            try {
                const response = await categoryApi.getAll();

                setListCategory(response);

                setSelectedCategoryInModal(response[0].id);

                console.log("Fetch list category successfully: ", response);
            } catch (error) {
                console.log("Failed to fetch list category: ", error);
            }
        }

        fetchListCategory();
    }, []);

    const toggleModal = () => {
        setModalIsOpen(!modalIsOpen);
    }

    const changeInputValueCategory = (e) => {
        setSelectedCategoryInModal(e.target.value);
    }

    const changeInputValueTagName = (e) => {
        setInputTagName(e.target.value);
    }

    const fetchCreateNewTag = async (categoryId, tagName) => {
        try {
            const data = {
                category: categoryId,
                tagName: tagName
            }

            const response = await articleTagApi.post(data);

            setShowAlertSuccess(true);
            setTimeout(() => {
                setShowAlertSuccess(false);
            }, 3000);

            console.log("Fetch create new article tag successfully: ", response);

        } catch (error) {
            console.log("Failed to fetch create new article tag: ", error);
        }
    }

    const listOptionJsxCategoryItems = listCategory.map((item) =>
        <option
            key={item.id}
            value={item.id}
        >
            {item.name}
        </option>
    )

    const listJsxTagItems = listTags.map((item) =>
        <tr key={item.id}>
            <th scope="row">
                {item.tagName}
            </th>
            <td>
                0
            </td>
            <td>
                {item.categoryName}
            </td>
        </tr>
    )

    return (
        <div style={{marginLeft: "10rem", marginRight: "10rem", marginTop: "3rem"}}>
            
            {(localStorage.getItem("role") === "admin") ?
                <>
                    <Button
                        style={{marginRight: "1rem", marginBottom: "0.5rem"}}
                        type='button'
                        color='primary'
                        onClick={() => {
                            toggleModal();
                        }}
                    >
                        <FontAwesomeIcon icon={faPlus} /> Add
                    </Button>
                </> : ""
            }

            {showAlertSuccess ?
                <Alert>
                    New category has been create successfully!
                </Alert>
                : ""}

            {showAlertFailure ? <Alert color='danger'>
                Failed to create new category
            </Alert>
                : ""}

            <Table
                hover
                responsive
            >
                <thead>
                    <tr>
                        <th>
                            Title
                        </th>
                        <th>
                            Num Of Articles
                        </th>
                        <th>
                            Category
                        </th>
                    </tr>
                </thead>
                <tbody>
                    {/* <tr>
                        <th scope="row">
                            bash
                        </th>
                        <td>
                            14
                        </td>
                        <td>
                            Linux
                        </td>
                        <td>
                            01/12/2022 15:30
                        </td>
                    </tr> */}
                    {listJsxTagItems}
                </tbody>
            </Table>

            <Modal
                isOpen={modalIsOpen}
                toggle={toggleModal}
            >
                <ModalHeader toggle={toggleModal}>Create new tag</ModalHeader>
                <ModalBody>
                    <Input
                        type="select"
                        name="category"
                        onChange={e => changeInputValueCategory(e)}
                    >
                        {listOptionJsxCategoryItems}
                    </Input>
                    <Label>
                        Tag name:
                    </Label>
                    <Input
                        type='text'
                        name="tag-name"
                        onChange={e => changeInputValueTagName(e)}
                    />
                </ModalBody>
                <ModalFooter>
                    <Button
                        color="primary"
                        onClick={() => {
                            fetchCreateNewTag(selectedCategoryInModal, inputTagName);

                            toggleModal();
                        }}
                    >
                        Submit
                    </Button>{' '}
                    <Button color="secondary" onClick={toggleModal}>
                        Cancel
                    </Button>
                </ModalFooter>
            </Modal>
        </div>
    );
}

export default ArticleTagMan;