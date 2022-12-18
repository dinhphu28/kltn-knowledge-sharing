import React from 'react';
import { Table } from 'reactstrap';
// import PropTypes from 'prop-types';

// NewlyUpdatedArticleList.propTypes = {
    
// };

function NewlyUpdatedArticleList(props) {
    return (
        <div>
            <h5>
                New Articles
            </h5>
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
                            Description
                        </th>
                        <th>
                            Author
                        </th>
                        <th>
                            Category
                        </th>
                        <th>
                            Time
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">
                            <a href="#">ReactJS là gì?</a>
                        </th>
                        <td>
                            Lorem ipsum dolor sit amet consectetur, adipisicing elit. Harum, beatae.
                        </td>
                        <td>
                            anthony
                        </td>
                        <td>
                            Front-End
                        </td>
                        <td>
                            01/12/2022 15:30
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">
                            <a href="#">Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellat, ullam!</a>
                        </th>
                        <td>
                            Lorem, ipsum dolor sit amet consectetur adipisicing elit. Qui necessitatibus id sunt porro voluptas, facilis ratione amet accusantium animi vel, dolor velit quis placeat reiciendis, ipsam possimus maiores nisi fugit.
                        </td>
                        <td>
                            jack
                        </td>
                        <td>
                            iOS
                        </td>
                        <td>
                            01/12/2022 15:30
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">
                        <a href="#">Lorem ipsum dolor sit amet consectetur adipisicing.</a>
                        </th>
                        <td>
                            Larry
                        </td>
                        <td>
                            catmeow
                        </td>
                        <td>
                            Android
                        </td>
                        <td>
                            01/12/2022 15:30
                        </td>
                    </tr>
                </tbody>
            </Table>
        </div>
    );
}

export default NewlyUpdatedArticleList;