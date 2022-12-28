import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Collapse, DropdownItem, DropdownMenu, DropdownToggle, Input, InputGroup, Label, Modal, ModalBody, ModalFooter, ModalHeader, Nav, Navbar, NavbarBrand, NavbarText, NavbarToggler, NavItem, NavLink, UncontrolledDropdown } from 'reactstrap';
import AddArticleBtn from './addArticleBtn';
import "./navbarStyles.css"
import ReportManBtn from './reportManBtn';
// import PropTypes from 'prop-types';
import AdminManBtn from './adminManBtn';
import { BASE_URL_API_BE } from '../../constants/global';
import profileApi from '../../apis/profileApi';
import categoryApi from '../../apis/categoryApi';

// navbar.propTypes = {
    
// };

function SignInUpNav(props) {
    return (
        <>
            <NavLink style={{color: "#0d6efd", whiteSpace: "nowrap"}} href="/sign-in">Sign In</NavLink>
            <NavLink href="/sign-up" style={{marginLeft: "1rem", color: "#0d6efd", whiteSpace: "nowrap"}}>Sign Up</NavLink>
        </>
    );
}

function UserAvtNav(props) {

    // const [reloadToggle, setReloadToggle] = useState(false);
    const [avatar, setAvatar] = useState("");

    let navigate = useNavigate();

    useEffect(() => {
        const fetchProfile = async () => {
            try {
                const username = localStorage.getItem("username");

                const response = await profileApi.get(username);

                // setProfileInfo(response);

                setAvatar(response.avatar);

                // console.log("Fetch profile successfully: ", response);
                
            } catch (error) {
                console.log("Failed to fetch profile info: ", error);
            }
        }

        fetchProfile();
    }, []);

    const logOutHandler = () => {
        localStorage.removeItem("username");
        localStorage.removeItem("token");
        localStorage.removeItem("role");

        props.onHandleChange();

        navigate("/");
    };

    return (
        <div className="user-avatar-nav">
            {/* <NavLink href="/profile">  
                <img src={avatar} alt="Avatar" />
            </NavLink> */}

            <UncontrolledDropdown
                // inNavbar
                // nav
            >
                <DropdownToggle
                    // caret
                    nav
                >
                    <img src={BASE_URL_API_BE + "/files/downloadFile/" + avatar} alt="Avatar" />
                </DropdownToggle>
                <DropdownMenu end>
                    <DropdownItem>
                        <NavLink href="/profile">
                            Profile
                        </NavLink>
                    </DropdownItem>
                    <DropdownItem>
                        <NavLink href="/change-password">
                            Change password
                        </NavLink>
                    </DropdownItem>
                    <DropdownItem divider />
                    <DropdownItem
                        onClick={() => logOutHandler()}
                        id="btn-sign-out">
                        Sign out
                    </DropdownItem>
                </DropdownMenu>
            </UncontrolledDropdown>

            {/* <Button
                color="primary"
                onClick={() => logOutHandler()}
            >
                Sign out
            </Button> */}
        </div>
    );
}

function NavBar(props) {

    const [reloadToggle, setReloadToggle] = useState(false);
    
    const [searchStrValue, setSearchStrValue] = useState("");
    const [modalIsOpen, setModalIsOpen] = useState(false);

    const [categoryList, setCategoryList] = useState([]);
    const [category, setCategory] = useState(undefined);
    const [from, setFrom] = useState(undefined);
    const [to, setTo] = useState(undefined);

    let navigate = useNavigate();

    const receiveData = () => {
        setReloadToggle(!reloadToggle);
    };

    const changeSearchInputValue = (e) => {
        setSearchStrValue(e.target.value);
    }
    const toggleModal = () => {
        setModalIsOpen(!modalIsOpen);
    }
    const changeInputValueCategory = (e) => {
        setCategory(e.target.value);
    };
    const changeInputFrom = (e) => {
        setFrom(e.target.value);
    }
    const changeInputTo = (e) => {
        setTo(e.target.value);
    }

    const fetchCategory = async () => {
        try {
            const response = await categoryApi.getAll();

            console.log("Fetch category successfully: ", response);

            setCategoryList(response);

            // if(category === null) {
            //     setCategory(response[0].id);
            // }

        } catch (error) {
            console.log("Failed to fetch category: ", error);
        }
    }

    const loadListCategory = () => {
        const listItems = categoryList.map((item) => {
            return (
                <option
                    key={item.id}
                    value={item.id}
                >
                    {item.name}
                </option>
            )
        });

        return listItems;
    };

    return (
        <div className="my-navbar">
            <Navbar
                color="light"
                expand="md"
                // fixed="top"
                light
            >
                <NavbarBrand id="my-brand-logo" href="/">
                    Metal
                </NavbarBrand>
                <NavbarToggler className="me-2" onClick={function noRefCheck(){} } />
                <Collapse navbar>
                <Nav
                    className="me-auto"
                    navbar
                >
                    <NavItem>
                        <NavLink
                            active={window.location.pathname === "/home" || window.location.pathname === "/"}
                            href="/home"
                        >
                            Home
                        </NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink
                            active={window.location.pathname === "/articles"}
                            href="/articles"
                        >
                            Articles
                        </NavLink>
                    </NavItem>

                    <NavItem style={{marginLeft: "1rem"}}>
                        <NavLink href="https://facebook.com/dinhphu.nguyen.355">
                            Facebook
                        </NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink href="https://github.com/dinhphu28">
                            GitHub
                        </NavLink>
                    </NavItem>
                </Nav>

                <InputGroup style={{marginLeft: "10%", marginRight: "3%"}}>
                    <Input
                        placeholder="Type and click search"
                        onChange={e => changeSearchInputValue(e)}
                    />
                    <Button
                        outline
                        onClick={() => {
                            fetchCategory();

                            toggleModal();
                        }}
                    >
                        Show filters
                    </Button>
                    <Button
                        // outline
                        onClick={() => {

                            const searchFilters = {
                                category: category,
                                from: from,
                                to: to
                            }

                            props.onHandleChangeSearchStr(searchStrValue);
                            props.onHandleChangeSearchFilters(searchFilters);

                            navigate("/search");
                        }}
                    >
                        Search
                    </Button>
                </InputGroup>

                {/* <AddArticleBtn /> */}

                {(localStorage.getItem("role") === "norm" || localStorage.getItem("role") === "mod" || localStorage.getItem("role") === "admin") ? <AddArticleBtn /> : ""}
                {(localStorage.getItem("role") === "mod" || localStorage.getItem("role") === "admin") ? <ReportManBtn /> : ""}
                {(localStorage.getItem("role") === "admin") ? <AdminManBtn /> : ""}
                
                {(localStorage.getItem("username") !== null) ? <UserAvtNav onHandleChange={receiveData} /> : <SignInUpNav />}

                {/* <NavLink href="/sign-in">Sign In</NavLink>
                <NavLink href="/sign-up">Sign Up</NavLink> */}
                <NavbarText>
                    {(localStorage.getItem("username") !== null) ? localStorage.getItem("username") : ""}
                </NavbarText>
                </Collapse>
            </Navbar>

            <Modal
                isOpen={modalIsOpen}
                toggle={toggleModal}
            >
                <ModalHeader toggle={toggleModal} >Create new category</ModalHeader>
                <ModalBody>
                    <Label>
                        Category:
                    </Label>
                    <Input
                        type="select"
                        name="category"
                        onChange={e => changeInputValueCategory(e)}
                        defaultValue={category}
                    >
                        <option
                            value={null}
                        >
                            -- Select --
                        </option>
                        {categoryList.length > 0 ? loadListCategory() : undefined}
                    </Input>
                    <Label>
                        From:
                    </Label>
                    <Input
                        type="date"
                        onChange={e => changeInputFrom(e)}
                        defaultValue={from}
                    />
                    <Label>
                        To:
                    </Label>
                    <Input
                        type="date"
                        onChange={e => changeInputTo(e)}
                        defaultValue={to}
                    />
                </ModalBody>
                <ModalFooter>
                <Button
                    color="primary"
                    onClick={() => {
                        console.log("Search Fitler - Category: ", category);
                        console.log("Search Fitler - From: ", from);
                        console.log("Search Fitler - To: ", to);

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

export default NavBar;