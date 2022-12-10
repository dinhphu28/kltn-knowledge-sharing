import { faPen } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import { Component } from "react";
import { Link } from "react-router-dom";
import "./navbarStyles.css"

class AddArticleBtn extends Component {
    // constructor(props) {
    //     super(props);
    // }

    render() {
        return (
            <div>
                <Link className="my-svg-btn" to="/create-article">
                    <FontAwesomeIcon icon={faPen} />
                </Link>
            </div>
        )
    }
}

export default AddArticleBtn;