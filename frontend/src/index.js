import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import './bootstrap-override.scss';
// import App from './App';
import UserRegisterPage from './pages/registerUser';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <UserRegisterPage />
  </React.StrictMode>
);
