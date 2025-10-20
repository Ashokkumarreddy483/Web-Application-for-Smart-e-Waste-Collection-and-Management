import axios from "axios";

const API_URL = "http://localhost:8080/api";

export const register = (userData) => axios.post(`${API_URL}/auth/register`, userData);
export const login = (loginData) => axios.post(`${API_URL}/auth/login`, loginData);
export const getProfile = (id) => axios.get(`${API_URL}/users/profile/${id}`);
export const updateProfile = (id, data) => axios.put(`${API_URL}/users/profile/${id}`, data);
