// App.jsx
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext'; // named export
import PrivateRoute from './components/PrivateRoute'; // default export
import Navbar from './components/Navbar'; // default export
import HomePage from './pages/HomePage'; // default export
import LoginPage from './pages/LoginPage'; // default export
import RegisterPage from './pages/RegisterPage'; // default export
import ProfilePage from './pages/ProfilePage'; // default export
import EwasteRequestForm from './components/EwasteRequestForm'; // newly added
import RequestsList from './components/RequestsList'; // newly added
import './App.css';

function App() {
  return (
    <Router>
      <AuthProvider>
        <Navbar />
        <div className="main-content">
          <Routes>
            {/* Public Routes */}
            <Route path="/" element={<HomePage />} />
            <Route path="/home" element={<HomePage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />

            {/* Protected Routes */}
            <Route
              path="/profile"
              element={
                <PrivateRoute>
                  <ProfilePage />
                </PrivateRoute>
              }
            />

            <Route
              path="/submit-request"
              element={
                <PrivateRoute>
                  <EwasteRequestForm />
                </PrivateRoute>
              }
            />

            <Route
              path="/my-requests"
              element={
                <PrivateRoute>
                  <RequestsList />
                </PrivateRoute>
              }
            />
          </Routes>
        </div>
      </AuthProvider>
    </Router>
  );
}

export default App;
