import React, { useState } from 'react';
import { Navigate, Route, Routes, useLocation, useNavigate } from 'react-router-dom';
import { Nav, NavItem, NavLink } from 'reactstrap';
import CategoryMan from './CategoryManagement/List';
// import PropTypes from 'prop-types';
import UserMan from './UserManagement/List';
import ArticleTagMan from './ArticleTagManagement/List';

// AdminPage.propTypes = {
    
// };

function AdminPage(props) {

    const location = useLocation();
    // console.log("Location: ", location.pathname.substring(7));

    // const [adminMenu, setAdminMenu] = useState({name: "category", path: "category-management"});
    // const [adminMenu, setAdminMenu] = useState({name: "category", path: "category-management"});

    let navigate = useNavigate();

    return (
        <div style={{marginLeft: "2rem", marginRight: "2rem"}}>
            <Nav
                justified
                pills
            >
                <NavItem>
                    <NavLink
                        // active={adminMenu.name === "category"}
                        active={location.pathname.substring(7) === "category-management"}
                        // href="/admin/category-management"
                        onClick={() => {
                            // setAdminMenu({name: "category", path: "category-management"});
                            navigate("/admin/category-management");
                        }}
                    >
                        Category Management
                    </NavLink>
                </NavItem>
                <NavItem>
                    <NavLink
                        // active={adminMenu.name === "user"}
                        active={location.pathname.substring(7) === "user-management"}
                        // href="/admin/user-management"
                        onClick={() => {
                            // setAdminMenu({name: "user", path: "user-management"});
                            navigate("/admin/user-management");
                        }}
                    >
                        User Management
                    </NavLink>
                </NavItem>
                <NavItem>
                    <NavLink
                        // active={adminMenu.name === "tag"}
                        active={location.pathname.substring(7) === "tag-management"}
                        // href="/admin/tag-management"
                        onClick={() => {
                            // setAdminMenu({name: "tag", path: "tag-management"});
                            navigate("/admin/tag-management");
                        }}
                    >
                        Article's Tag Management
                    </NavLink>
                </NavItem>
            </Nav>

            <Routes>
                <Route path="/" element={<Navigate replace to="category-management" />} />
                <Route path="category-management" element={ <CategoryMan />} />
                <Route path="user-management" element={ <UserMan />} />
                <Route path="tag-management" element={ <ArticleTagMan />} />
            </Routes>
        </div>
    );
}

export default AdminPage;