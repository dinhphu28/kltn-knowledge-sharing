import React from 'react';
import NavBar from './navbar';
// import PropTypes from 'prop-types';

// index.propTypes = {
    
// };

function Header(props) {

    const receiveSearchStr = (searchStr) => {
        // console.log("Received Search String: ", searchStr);
        props.onHandleChangeSearchStr(searchStr);
    }

    const receiveFilters = (searchFilters) => {
        // console.log("Received Search Filters NB: ", searchFilters);
        props.onHandleChangeSearchFilters(searchFilters);
    }

    return (
        <div>
            <header>
                <NavBar onHandleChangeSearchStr={receiveSearchStr} onHandleChangeSearchFilters={receiveFilters} />
            </header>
        </div>
    );
}

export default Header;