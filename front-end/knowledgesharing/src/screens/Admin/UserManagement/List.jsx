import React, { useEffect, useState } from 'react';
import { Alert, Button, Col, Input, Label, Modal, ModalBody, ModalFooter, ModalHeader, Nav, NavItem, NavLink, Row, TabContent, TabPane, Table } from 'reactstrap';
// import PropTypes from 'prop-types';
import usersApi from '../../../apis/usersApi';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheck, faLock, faPlus, faUnlock, faX } from '@fortawesome/free-solid-svg-icons';

// UserMan.propTypes = {
    
// };

function UserMan(props) {

    const [usersList, setUsersList] = useState([]);
    const [activeStatus, setActiveStatus] = useState(true);
    const [showAlertSuccess, setShowAlertSuccess] = useState(false);
    const [showAlertFailure, setShowAlertFailure] = useState(false);

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const [newPassword, setNewPassword] = useState("");
    const [selectedUser, setSelectedUser] = useState(null);

    const [modalIsOpen, setModalIsOpen] = useState(false);
    const [modalChangePasswordIsOpen, setModalChangePasswordIsOpen] = useState(false);

    useEffect(() => {
        const fetchListUsers = async () => {
            try {

                const params = {
                    role: "mod",
                    active: activeStatus
                }

                const response = await usersApi.getAll(params);

                console.log("Fetch list users successfully: ", response);

                setUsersList(response);
            } catch (error) {
                console.log("Fail to fetch list users: ", error);
            }
        }

        fetchListUsers();
    }, [activeStatus]);

    const toggleModal = () => {
        setModalIsOpen(!modalIsOpen);
    }

    const toggleChangePasswordModal = () => {
        setModalChangePasswordIsOpen(!modalChangePasswordIsOpen);
    }

    const changeUsernameInputValue = (e) => {
        setUsername(e.target.value);
    }
    const changePasswordInputValue = (e) => {
        setPassword(e.target.value);
    }
    
    const changeNewPasswordInputValue = (e) => {
        setNewPassword(e.target.value);
    }

    const fetchCreateModUser = async () => {
        try {
            const data = {
                username: username,
                password: password
            }

            const response = await usersApi.post(data);

            setShowAlertSuccess(true);
            setTimeout(() => {
                setShowAlertSuccess(false);
            }, 3000);

            console.log("Fetch create mod user successfully: ", response);
        } catch (error) {
            console.log("Fetch create mod user failed: ", error);

            setShowAlertFailure(true);
            setTimeout(() => {
                setShowAlertFailure(false);
            }, 3000);
        }
    }

    const fetchUpdateActiveStateOfUser = async (inpUsername, inpActiveState) => {
        try {
            const data = {
                username: inpUsername,
                active: inpActiveState
            }

            const response = await usersApi.putActiveState(data);

            console.log("Fetch change active status successfully: ", response);
        } catch (error) {
            console.log("Fail to fetch update active state: ", error);
        }
    }

    const fetchChangePasswordUser = async (sel_username, newPassword) => {
        try {
            const data = {
                username: sel_username,
                newPassword: newPassword
            }

            const response = await usersApi.putPassword(data);

            console.log("Fetch change password successfully: ", response);

        } catch (error) {
            console.log("Failed to fetch change password: ", error);
        }
    }

    const listJsxUserItems = usersList.map((item) =>
        <tr key={item.username}>
            <th scope="row">
                <img src="https://cly.1cdn.vn/2021/01/03/iu.jpg" alt="avatar" style={{ maxWidth: "100px" }} />
            </th>
            <td>
                {item.username}
            </td>
            <td>
                <FontAwesomeIcon style={{fontSize: "1.5rem"}} icon={item.active ? faCheck : faX} color={item.active ? 'green' : 'red'}/>
            </td>
            <td>
                <Button
                    color={item.active ? 'danger' : 'success'}
                    onClick={(e) => {
                        fetchUpdateActiveStateOfUser(item.username, !item.active);

                        setUsersList(usersList.filter((itemUsr) => itemUsr.username !== item.username));
                    }}
                >
                    <FontAwesomeIcon icon={faUnlock} />
                </Button>
            </td>
            <td>
                <Button
                    color='link'
                    onClick={() => {
                        setSelectedUser(item.username);

                        toggleChangePasswordModal();
                    }}
                >
                    Change password
                </Button>
            </td>
        </tr>
    );

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
            
            <Nav tabs>
                <NavItem>
                    <NavLink
                        className={activeStatus ? "active" : ""}
                        onClick={() => {
                            setActiveStatus(true);
                        }}
                    >
                        Active
                    </NavLink>
                </NavItem>
                <NavItem>
                    <NavLink
                        className={activeStatus ? "" : "active"}
                        onClick={() => {
                            setActiveStatus(false);
                        }}
                    >
                        Inactive
                    </NavLink>
                </NavItem>
            </Nav>
            <TabContent activeTab={activeStatus ? "1" : "2"}>
                <TabPane tabId="1">
                    <Table
                        hover
                        responsive
                    >
                        <thead>
                            <tr>
                                <th>
                                    Avatar
                                </th>
                                <th>
                                    Username
                                </th>
                                <th>
                                    Active
                                </th>
                                <th>
                                    Action
                                </th>
                                <th>
                                    {/* Change password */}
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            {listJsxUserItems}
                        </tbody>
                    </Table>
                </TabPane>
                <TabPane tabId="2">
                    <Table
                        hover
                        responsive
                    >
                        <thead>
                            <tr>
                                <th>
                                    Avatar
                                </th>
                                <th>
                                    Username
                                </th>
                                <th>
                                    Active
                                </th>
                                <th>
                                    Action
                                </th>
                                <th>
                                    {/* Change password */}
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            {listJsxUserItems}
                        </tbody>
                    </Table>
                </TabPane>
            </TabContent>

            <Modal
                isOpen={modalIsOpen}
                toggle={toggleModal}
            >
                <ModalHeader toggle={toggleModal} >Create new category</ModalHeader>
                <ModalBody>
                    <Label>
                        Username:
                    </Label>
                    <Input
                        type="text"d
                        name="username"
                        onChange={e => {changeUsernameInputValue(e)}}
                    />
                    <Label>
                        Password:
                    </Label>
                    <Input
                        type="password"
                        name="password"
                        onChange={e => {changePasswordInputValue(e)}}
                    />
                </ModalBody>
                <ModalFooter>
                <Button
                    color="primary"
                    onClick={() => {
                        fetchCreateModUser();

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
            
            <Modal
                isOpen={modalChangePasswordIsOpen}
                toggle={toggleChangePasswordModal}
            >
                <ModalHeader toggle={toggleChangePasswordModal}>Change password of user: {selectedUser}</ModalHeader>
                <ModalBody>
                    <Label>
                        New Password:
                    </Label>
                    <Input
                        type='password'
                        name="new-password"
                        onChange={e => changeNewPasswordInputValue(e)}
                    />
                </ModalBody>
                <ModalFooter>
                    <Button
                        color="primary"
                        onClick={() => {
                            fetchChangePasswordUser(selectedUser, newPassword);

                            toggleChangePasswordModal();
                        }}
                    >
                        Submit
                    </Button>{' '}
                    <Button color="secondary" onClick={toggleChangePasswordModal}>
                        Cancel
                    </Button>
                </ModalFooter>
            </Modal>
        </div>
    );
}

export default UserMan;