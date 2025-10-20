import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import './Navbar.css';

const Navbar = () => {
  const { isAuthenticated, user, logout } = useAuth();
  const location = useLocation();

  return (
    <nav className="navbar">
      <Link to="/" className="navbar-brand">EcoCycle</Link>

      <div className="navbar-links">
        {isAuthenticated ? (
          <>
            <span className="welcome-message">
              Welcome, {user?.username || 'User'}!
            </span>

            {/* 🏠 Home */}
            <Link
              to="/home"
              className={`navbar-item ${location.pathname === '/home' ? 'active' : ''}`}
            >
              Home
            </Link>

            {/* ♻️ Submit Request */}
            <Link
              to="/submit-request"
              className={`navbar-item ${location.pathname === '/submit-request' ? 'active' : ''}`}
            >
              Submit Request
            </Link>

            {/* 📋 My Requests */}
            <Link
              to="/my-requests"
              className={`navbar-item ${location.pathname === '/my-requests' ? 'active' : ''}`}
            >
              My Requests
            </Link>

            {/* 👤 Profile */}
            <Link
              to="/profile"
              className={`navbar-item ${location.pathname === '/profile' ? 'active' : ''}`}
            >
              Profile
            </Link>

            {/* 🚪 Logout */}
            <button onClick={logout} className="navbar-item logout-btn">
              Logout
            </button>
          </>
        ) : (
          <>
            <Link to="/home" className="navbar-item">Home</Link>
            <Link to="/login" className="navbar-item">Login</Link>
            <Link to="/register" className="navbar-item">Register</Link>
          </>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
