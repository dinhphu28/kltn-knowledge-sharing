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

    return (
        <div>
            <header>
                <NavBar onHandleChangeSearchStr={receiveSearchStr} />
            </header>
        </div>
    );
}

export default Header;