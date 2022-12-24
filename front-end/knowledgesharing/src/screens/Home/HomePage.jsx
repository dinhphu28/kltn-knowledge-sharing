import React from 'react';
import NominatedArticleList from './Nomination/List';
// import PropTypes from 'prop-types';
import NewlyUpdatedArticleList from './NewlyUpdated/List';
import LeaderBoardList from './LeaderBoard/List';
import HighEvaluatedArticleList from './HighEvaluation/List';

// HomePage.propTypes = {
    
// };

function ScreenHomePage(props) {
    return (
        <div style={{marginLeft: "10rem", marginRight: "10rem", marginTop: "2rem"}}>
            {/* <hr/> */}
            <NominatedArticleList />

            <br/>
            <hr/>
            <br/>

            <NewlyUpdatedArticleList />

            <br/>
            <hr/>
            <br/>

            <LeaderBoardList />

            {/* <br/>
            <hr/>
            <br/>

            <HighEvaluatedArticleList /> */}
        </div>
    );
}

export default ScreenHomePage;