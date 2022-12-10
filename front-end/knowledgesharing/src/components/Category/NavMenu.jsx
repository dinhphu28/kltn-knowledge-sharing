import React, { useEffect, useState } from 'react';
import { DropdownItem, Nav, NavItem, NavLink } from 'reactstrap';
import categoryApi from '../../apis/categoryApi';
import './NavMenu.css';
// import PropTypes from 'prop-types';

// CategoryNavMenu.propTypes = {
    
// };

function CategoryNavMenu(props) {

    const [categoryList, setCategoryList] = useState([]);
    const [loaded, setLoaded] = useState(false);
    const [category, setCategory] = useState({id: undefined, name: "Newest"});

    useEffect(() => {
        const fetchCategory = async () => {
            try {
                const response = await categoryApi.getAll();

                // console.log("Fetch category successfully: ", response);

                setCategoryList(response);

                setLoaded(true);
            } catch (error) {
                console.log("Failed to fetch category: ", error);
            }
        }

        fetchCategory();
    }, []);

    const renderJsxCategoryItems = () => {
        if(loaded) {
            const listItems = categoryList.map((item) => {
                return <NavItem
                    key={item.id}
                    // onClick={() => {
                    //     if(category.id !== item.id) {
                    //         setCategory(item);
                    //         props.onHandleChangeCat(item);
                    //     }
                    // }}
                >
                    <NavLink
                        active={category.id === item.id}
                        onClick={() => {
                            if(category.id !== item.id) {
                                setCategory(item);
                                props.onHandleChangeCat(item);
                            }
                        }}
                    >
                        {item.name}
                    </NavLink>
                </NavItem>
            });

            return listItems;
        }
    }

    return (
        <div className="nav-menu">
            <Nav
                pills
            >
                <NavItem>
                    <NavLink
                        active={category.id === undefined}
                        onClick={() => {
                            if(category.id !== undefined) {
                                setCategory({id: undefined, name: "Newest"});
                                props.onHandleChangeCat({id: undefined, name: "Newest"});
                                // props.onHandleChange({
                                //     categoryName: category,
                                //     hidden: hidden
                                // });
                            }
                        }}
                        href="#"
                    >
                        Newest
                    </NavLink>
                </NavItem>
                {renderJsxCategoryItems()}
            </Nav>
        </div>
    );
}

export default CategoryNavMenu;