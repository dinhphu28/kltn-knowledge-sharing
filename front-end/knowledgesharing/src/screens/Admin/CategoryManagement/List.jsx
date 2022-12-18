import React, { useEffect, useState } from 'react';
import { Alert, Button, Input, Label, Modal, ModalBody, ModalFooter, ModalHeader, Table } from 'reactstrap';
// import PropTypes from 'prop-types';
import categoryApi from '../../../apis/categoryApi';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus } from '@fortawesome/free-solid-svg-icons';

// CategoryMan.propTypes = {
    
// };

function CategoryMan(props) {

    const [categoriesList, setCategoriesList] = useState([]);
    const [modalIsOpen, setModalIsOpen] = useState(false);
    const [categoryName, setCategoryName] = useState("");
    const [showAlertSuccess, setShowAlertSuccess] = useState(false);
    const [showAlertFailure, setShowAlertFailure] = useState(false);

    useEffect(() => {
        const fetchCategoriesList = async () => {
            try {
                const response = await categoryApi.getAll();

                console.log("Fetch categories successfully: ", response);

                setCategoriesList(response);
            } catch (error) {
                console.log("Failed to fetch categories: ", error);
            }
        }

        fetchCategoriesList();
    }, []);

    const listJsxCategoryItems = categoriesList.map((item) => 
        <tr key={item.id}>
            <th scope="row">
                {item.name}
            </th>
            <td>
                0
            </td>
            <td>
                {item.dateCreated}
            </td>
        </tr>
    );

    const toggleModal = () => {
        setModalIsOpen(!modalIsOpen);
    }

    const changeInputValueCategoryName = e => {
        setCategoryName(e.target.value);
    }

    const fetchCreateCategory = async () => {
        try {
            const data = {
                name: categoryName
            }

            // console.log("vvv: ", data);

            const response = await categoryApi.post(data);

            setShowAlertSuccess(true);
            setTimeout(() => {
                setShowAlertSuccess(false);
            }, 3000);

            console.log("Fetch add category successfully: ", response);
        } catch (error) {
            console.log("Failed to fetch add category: ", error);

            setShowAlertFailure(true);
            setTimeout(() => {
                setShowAlertFailure(false);
            }, 3000);
        }
    }

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
                            Name
                        </th>
                        <th>
                            Num Of Articles
                        </th>
                        <th>
                            Date Created
                        </th>
                    </tr>
                </thead>
                <tbody>
                    {listJsxCategoryItems}
                </tbody>
            </Table>
            
            <Modal
                isOpen={modalIsOpen}
                toggle={toggleModal}
            >
                <ModalHeader toggle={toggleModal} >Create new category</ModalHeader>
                <ModalBody>
                    <Label>
                        Category name:
                    </Label>
                    <Input
                        type="text"
                        name="category-name"
                        onChange={e => {changeInputValueCategoryName(e)}}
                    />
                </ModalBody>
                <ModalFooter>
                <Button
                    color="primary"
                    onClick={() => {
                        fetchCreateCategory();

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

export default CategoryMan;