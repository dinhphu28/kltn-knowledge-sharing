import React from 'react';
import "./navbarStyles.css";
import "./NotificationBtn.css";
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBell } from '@fortawesome/free-solid-svg-icons';
// import PropTypes from 'prop-types';

// NotificationBtn.propTypes = {
    
// };

function NotificationBtn(props) {
    return (
        <div>
            <Link className="my-svg-btn" to="/notification">
                <span className="notification">
                    <FontAwesomeIcon icon={faBell} />
                    {/* <span className="badge">3</span> */}
                </span>
            </Link>
        </div>
    );
}

export default NotificationBtn;