import React from 'react';
// import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTasks } from '@fortawesome/free-solid-svg-icons';

// ReportManBtn.propTypes = {};

function ReportManBtn(props) {
    return (
        <div>
            <Link className="my-svg-btn" to="/reports">
                <FontAwesomeIcon icon={faTasks} />
            </Link>
        </div>
    );
}

export default ReportManBtn;