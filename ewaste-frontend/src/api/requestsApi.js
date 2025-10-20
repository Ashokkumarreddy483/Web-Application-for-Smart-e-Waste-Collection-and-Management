// src/api/requestsApi.js
import axios from "axios";

const API_BASE = "http://localhost:8080/api";

const api = axios.create({
  baseURL: API_BASE,
  timeout: 20000,
});

// Return auth header object (or empty object)
function authHeader() {
  // Ensure your login stores the access token in localStorage under "token"
  // e.g. localStorage.setItem("token", loginResponse.accessToken);
  const token = localStorage.getItem("token");
  return token ? { Authorization: `Bearer ${token}` } : {};
}

/**
 * Submit a new e-waste request.
 * Expects FormData containing:
 *  - "data": JSON blob of EwasteRequestCreateDto
 *  - "images": one or more File entries
 */
export const submitEwasteRequest = async (formData) => {
  const res = await api.post("/requests", formData, {
    headers: {
      ...authHeader(),
      "Content-Type": "multipart/form-data",
    },
  });
  return res.data;
};

export const fetchUserRequests = async () => {
  const res = await api.get("/requests", { headers: authHeader() });
  return res.data;
};

export const fetchRequestById = async (id) => {
  const res = await api.get(`/requests/${id}`, { headers: authHeader() });
  return res.data;
};
