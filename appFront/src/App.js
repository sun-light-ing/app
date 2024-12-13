import {BrowserRouter as Router, Route, Routes, Navigate} from "react-router-dom";
import React from "react";
import './App.css';
import logo from './logo.svg';
import Login from './components/login/login'
import Home from './components/home/home'
import ArticleEditor from './components/articleEditor/ArticleEditor'

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Navigate to="/login"/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/home" element={<Home/>}/>
                <Route path="/ae" element={<ArticleEditor/>}/>
            </Routes>
        </Router>
    );
}

export default App;
