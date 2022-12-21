import React from 'react';
import "./NotFound.css";
// import PropTypes from 'prop-types';

NotFound.propTypes = {};

function NotFound(props) {
    return (
        <div>
            {/* Oops ... Not found */}
            <img className='center' style={{textAlign: "center"}}
                src="https://www.pngitem.com/pimgs/m/561-5616833_image-not-found-png-not-found-404-png.png"
                alt="not-found"
            />
        </div>
        // <div id="wrapper">
        //     <img src="https://i.imgur.com/qIufhof.png" />
        //     <div id="info">
        //         <h3>This page could not be found</h3>
        //     </div>
        // </div >
    );
}

export default NotFound;