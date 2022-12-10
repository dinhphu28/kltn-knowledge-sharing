import { faCog } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import { Component } from "react";
import { Link } from "react-router-dom";
import "./navbarStyles.css"

class AdminManBtn extends Component {
    // constructor(props) {
    //     super(props);
    // }

    render() {
        return (
            <div>
                <Link className="my-svg-btn" to="/user-management">
                    <FontAwesomeIcon icon={faCog} />
                </Link>
            </div>
        )
    }
}

export default AdminManBtn;