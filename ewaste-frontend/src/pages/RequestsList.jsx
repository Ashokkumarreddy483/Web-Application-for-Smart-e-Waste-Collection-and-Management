// RequestsList.jsx
import React, { useEffect, useState } from "react";
import { fetchUserRequests } from "./requestsApi";

export default function RequestsList() {
  const [list, setList] = useState([]);
  useEffect(() => {
    fetchUserRequests().then(setList).catch(console.error);
  }, []);

  return (
    <div>
      <h2>My E-waste Requests</h2>
      <table>
        <thead>
          <tr><th>ID</th><th>Device</th><th>Qty</th><th>Status</th><th>Created</th></tr>
        </thead>
        <tbody>
          {list.map(r => (
            <tr key={r.requestId}>
              <td>{r.requestId}</td>
              <td>{r.deviceType} - {r.brand} {r.model}</td>
              <td>{r.quantity}</td>
              <td>{r.status}</td>
              <td>{new Date(r.createdAt).toLocaleString()}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
