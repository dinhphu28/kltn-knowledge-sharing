import React, { useEffect, useState } from 'react';
import { Button, Table } from 'reactstrap';
// import PropTypes from 'prop-types';
import articleTagApi from '../../../apis/articleTagApi';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus } from '@fortawesome/free-solid-svg-icons';

// ArticleTagMan.propTypes = {
    
// };

function ArticleTagMan(props) {

    const [listTags, setListTags] = useState([]);

    useEffect(() => {
        const fetchListTags = async () => {
            try {
                const response = await articleTagApi.getAll();

                setListTags(response);

                console.log("Fetch list tags successfully: ", response);
            } catch (error) {
                console.log("Failed to fetch list tags: ", error);
            }
        }

        fetchListTags();
    }, []);

    const listJsxTagItems = listTags.map((item) =>
        <tr key={item.id}>
            <th scope="row">
                {item.tagName}
            </th>
            <td>
                0
            </td>
            <td>
                {item.category}
            </td>
        </tr>
    )

    return (
        <div style={{marginLeft: "10rem", marginRight: "10rem", marginTop: "3rem"}}>
            
            {(localStorage.getItem("role") === "admin") ?
                <>
                    <Button
                        style={{marginRight: "1rem", marginBottom: "0.5rem"}}
                        type='button'
                        color='primary'
                        onClick={() => {
                            // toggleModal();
                        }}
                    >
                        <FontAwesomeIcon icon={faPlus} /> Add
                    </Button>
                </> : ""
            }

            <Table
                hover
                responsive
            >
                <thead>
                    <tr>
                        <th>
                            Title
                        </th>
                        <th>
                            Num Of Articles
                        </th>
                        <th>
                            Category
                        </th>
                    </tr>
                </thead>
                <tbody>
                    {/* <tr>
                        <th scope="row">
                            bash
                        </th>
                        <td>
                            14
                        </td>
                        <td>
                            Linux
                        </td>
                        <td>
                            01/12/2022 15:30
                        </td>
                    </tr> */}
                    {listJsxTagItems}
                </tbody>
            </Table>
        </div>
    );
}

export default ArticleTagMan;