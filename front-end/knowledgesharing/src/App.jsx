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


function App() {

  const [reloadToggle, setReloadToggle] = useState(false);

  const receiveReloadToggle = () => {
    setReloadToggle(!reloadToggle);
  };

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
          <Header />
          
          <Routes>
            <Route path="/articles/*" element={<ScreenMainPage />} />
            <Route path="/" element={<Navigate replace to="/articles" />} />
            <Route path="/create-article" element={<AddArticle />} />

            <Route path="/sign-in" element={<ScreenAuthSignIn onHandleChange={receiveReloadToggle} />} />
            {/* <Route path="/sign-up" element={<SignUpPage />} /> */}
            <Route path="/forget-password" element={<ScreenAuthForgetPassword />} />
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
