import { Suspense, useState } from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';
import Header from './components/Header/index';
import NotFound from './components/NotFound/index';
import Contact from './components/Contact';
import Footer from './components/Footer/index';
import ScreenAuthSignIn from './screens/Auth/SignIn';
import ScreenAuthForgetPassword from './screens/Auth/ForgetPassword';
import ScreenMainPage from './screens/MainPage/MainPage';
import AddArticle from './screens/MainPage/Articles/Form/AddArticle';
import ScreenAuthSignUp from './screens/Auth/SignUp';
import ScreenFormProfile from './screens/Profile/Form/Form';
import ChangePassword from './screens/Auth/ChangePassword';
import ScreenReportArticleList from './screens/Reports/Article/List';
import ScreenReportCommentList from './screens/Reports/Comment/List';
import AdminPage from './screens/Admin/AdminPage';
import ScreenHomePage from './screens/Home/HomePage';
import { Nav, NavItem, NavLink } from 'reactstrap';
import ScreenArticleSearchResult from './screens/ArticleSearchResult/List';
import ScreenNotificationList from './screens/Notification/List';


function App() {

  const [reloadToggle, setReloadToggle] = useState(false);

  const [searchStrValue, setSearchStrValue] = useState("");
  const [searchFiltersValue, setSearchFiltersValue] = useState({category: undefined, from: undefined, to: undefined});

  const receiveReloadToggle = () => {
    setReloadToggle(!reloadToggle);
  };

  const receiveSearchStr = (searchStr) => {
    console.log("Received Search String: ", searchStr);

    setSearchStrValue(searchStr);
  }
  const receiveFilters = (searchFilters) => {
    console.log("Received Search Filters: ", searchFilters);
    setSearchFiltersValue(searchFilters);
  }

  // console.log("Location: ", window.location.pathname);

  return (
    <div className="my-app">
      {/* <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header> */}
      <Suspense fallback={<div>Loading...</div>}>
        <BrowserRouter>
          <Header onHandleChangeSearchStr={receiveSearchStr} onHandleChangeSearchFilters={receiveFilters} />
          
          <Routes>
            <Route path="/home/*" element={<ScreenHomePage />} />
            <Route path="/articles/*" element={<ScreenMainPage />} />
            <Route path="/" element={<Navigate replace to="/home" />} />
            <Route path="/create-article" element={<AddArticle />} />

            <Route path="/search" element={<ScreenArticleSearchResult searchString={searchStrValue} searchFilters={searchFiltersValue} />} />

            <Route path="/article-reports" element={<ScreenReportArticleList />} />
            <Route path="/comment-reports" element={<ScreenReportCommentList />} />

            <Route path="/sign-in" element={<ScreenAuthSignIn onHandleChange={receiveReloadToggle} />} />
            <Route path="/sign-up" element={<ScreenAuthSignUp />} />
            <Route path="/forget-password" element={<ScreenAuthForgetPassword />} />
            <Route path="/change-password" element={<ChangePassword />} />

            <Route path="/profile" element={<ScreenFormProfile />} />
            <Route path="/notification" element={<ScreenNotificationList />} />

            <Route path="/admin/*" element={<AdminPage />} />

            <Route path="*" element={<NotFound />} />
          </Routes>

          <Contact />
          <Footer />
        </BrowserRouter>
      </Suspense>
    </div>
  );
}

export default App;
