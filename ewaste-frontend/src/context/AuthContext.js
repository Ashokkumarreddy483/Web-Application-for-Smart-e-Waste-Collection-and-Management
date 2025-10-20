import React, { createContext, useState, useEffect, useContext } from 'react';
import { loginUser as apiLoginUser, registerUser as apiRegisterUser } from '../api/auth';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  // Initialize auth state from localStorage
  useEffect(() => {
    const userData = localStorage.getItem('user');
    if (userData) {
      setIsAuthenticated(true);
      setUser(JSON.parse(userData));
    }
    setLoading(false);
  }, []);

  // Login
  const login = async (credentials) => {
    try {
      const data = await apiLoginUser(credentials); // data is the user object now

      if (!data || !data.username) {
        console.error('Login failed: Invalid response from server', data);
        return { success: false, message: 'Invalid login response' };
      }

      localStorage.setItem('user', JSON.stringify(data));
      setIsAuthenticated(true);
      setUser(data);

      return { success: true };
    } catch (error) {
      console.error('Login failed:', error);

      const message =
        error.response?.data || 'Login failed. Check if backend is running.';
      setIsAuthenticated(false);
      setUser(null);
      return { success: false, message };
    }
  };

  // Register
  const register = async (userData) => {
    try {
      const data = await apiRegisterUser(userData);

      if (!data) {
        console.error('Registration failed: Invalid response from server', data);
        return { success: false, message: 'Invalid registration response' };
      }

      return { success: true };
    } catch (error) {
      console.error('Registration failed:', error);

      const message =
        error.response?.data || 'Registration failed. Check if backend is running.';
      return { success: false, message };
    }
  };

  // Logout
  const logout = () => {
    localStorage.removeItem('user');
    setIsAuthenticated(false);
    setUser(null);
  };

  return (
    <AuthContext.Provider
      value={{ isAuthenticated, user, loading, login, register, logout, setUser }}
    >
      {!loading && children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
