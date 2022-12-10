import React, { useState, useEffect } from "react";
import { Button } from "reactstrap";
import "./PaginationBar.css"

function PaginationBar(props) {

    const {numberOfPages, currentPage} = props;
    // const {numberOfPages} = props;

    const [page, setPage] = useState(1);

    useEffect(() => {
        setPage(currentPage + 1);
    }, [currentPage]);

    return (
        <div className="my-pagination-bar">
            <Button
                color={
                    (page > 1) ? "primary" : "secondary"
                }
                disabled={(page < 2)}
                onClick={() => {
                    if(page > 1) {
                        setPage(page - 1);
                        props.onHandleChange(page - 1 - 1);
                    }
                }}
            >
                Prev
            </Button>

            <span>{page}</span>

            <Button
                color={
                    (page < numberOfPages) ? "primary" : "secondary"
                }
                disabled={(page >= numberOfPages)}
                onClick={() => {
                    if(page < numberOfPages) {
                        setPage(page + 1);
                        props.onHandleChange(page + 1 - 1);
                    }
                }}
            >
                Next
            </Button>
        </div>
    );
}

export default PaginationBar;