// src/components/RequestsList.jsx
import React, { useEffect, useState } from "react";
import { fetchUserRequests } from "../api/requestsApi";

export default function RequestsList() {
  const [list, setList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    let mounted = true;
    setLoading(true);
    fetchUserRequests()
      .then((data) => {
        if (mounted) {
          setList(data || []);
          setError(null);
        }
      })
      .catch((err) => {
        console.error(err);
        if (mounted) setError(err.response?.data || err.message || "Failed to load");
      })
      .finally(() => mounted && setLoading(false));
    return () => (mounted = false);
  }, []);

  return (
    <div className="requests-list card">
      <h2>My E-waste Requests</h2>

      {loading ? (
        <div>Loading...</div>
      ) : error ? (
        <div className="error">Error: {JSON.stringify(error)}</div>
      ) : list.length === 0 ? (
        <div>No requests found. Submit one from the form.</div>
      ) : (
        <table className="requests-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Device</th>
              <th>Qty</th>
              <th>Status</th>
              <th>Created</th>
            </tr>
          </thead>
          <tbody>
            {list.map((r) => (
              <tr key={r.requestId}>
                <td>{r.requestId}</td>
                <td>{r.deviceType} — {r.brand} {r.model}</td>
                <td>{r.quantity}</td>
                <td>{r.status}</td>
                <td>{r.createdAt ? new Date(r.createdAt).toLocaleString() : "-"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}
