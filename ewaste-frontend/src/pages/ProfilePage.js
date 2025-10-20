import React, { useState, useEffect } from "react";
import { getUserProfile, updateUserProfile } from "../api/auth";
import "./ProfilePage.css";

const ProfilePage = () => {
  const [profile, setProfile] = useState({
    fullName: "",
    email: "",
    phoneNumber: "",
    pickupAddress: "",
  });

  const [requestHistory] = useState([
    { id: "136.8331", date: "17.08.24 8:45:32", status: "Done", details: "E-waste Pickup", deals: "$18.13" },
    { id: "136.88132", date: "17.08.24 8:51:34", status: "Ongoing", details: "E-waste Submission", deals: "$10.13" },
  ]);

  const [loading, setLoading] = useState(true);
  const [message, setMessage] = useState("");
  const [activeTab, setActiveTab] = useState("profile");

  // Fetch profile from backend
  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const data = await getUserProfile(); // no username needed, backend returns default user
        setProfile({
          fullName: `${data.firstName || ""} ${data.lastName || ""}`.trim(),
          email: data.username || "",
          phoneNumber: data.contactNumber || "",
          pickupAddress: data.pickupAddress || "", // optional, backend may not have this
        });
      } catch (err) {
        console.error("Failed to fetch profile:", err);
        setMessage("Failed to load profile. Please try again.");
      } finally {
        setLoading(false);
      }
    };
    fetchProfile();
  }, []);

  const handleChange = (e) => setProfile({ ...profile, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await updateUserProfile(profile); // backend expects profile object
      setMessage("Profile updated successfully!");
    } catch (err) {
      console.error("Update failed:", err);
      setMessage("Failed to update profile.");
    }
  };

  if (loading) return <div className="profile-loading">Loading...</div>;

  return (
    <div className="profile-container">
      <div className="profile-header">
        <img
          src="https://cdn-icons-png.flaticon.com/512/3135/3135715.png"
          alt="User avatar"
          className="profile-avatar"
        />
        <h2>Welcome, {profile.fullName.split(" ")[0] || "User"}!</h2>
      </div>

      <div className="profile-content">
        <div className="sidebar">
          <ul>
            <li className={activeTab === "profile" ? "active" : ""} onClick={() => setActiveTab("profile")}>
              Profile Details
            </li>
            <li className={activeTab === "history" ? "active" : ""} onClick={() => setActiveTab("history")}>
              Request History
            </li>
          </ul>
        </div>

        <div className="main-section">
          {activeTab === "profile" && (
            <>
              <h3>Edit Profile</h3>
              <form onSubmit={handleSubmit} className="profile-form">
                {[
                  { name: "fullName", label: "Full Name", type: "text" },
                  { name: "email", label: "Email", type: "email" },
                  { name: "phoneNumber", label: "Phone Number", type: "text" },
                  { name: "pickupAddress", label: "Pickup Address", type: "text" },
                ].map((field) => (
                  <div className="form-group" key={field.name}>
                    <label>{field.label}</label>
                    <input
                      type={field.type}
                      name={field.name}
                      value={profile[field.name]}
                      onChange={handleChange}
                    />
                  </div>
                ))}
                <button type="submit" className="save-btn">Save Changes</button>
                {message && <p className="message">{message}</p>}
              </form>
            </>
          )}

          {activeTab === "history" && (
            <>
              <h3 className="history-title">Request History</h3>
              <table className="history-table">
                <thead>
                  <tr>
                    <th>Request ID</th>
                    <th>Date</th>
                    <th>Status</th>
                    <th>Details</th>
                    <th>Deals</th>
                  </tr>
                </thead>
                <tbody>
                  {requestHistory.map((req, index) => (
                    <tr key={index}>
                      <td>{req.id}</td>
                      <td>{req.date}</td>
                      <td>{req.status}</td>
                      <td>{req.details}</td>
                      <td>{req.deals}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </>
          )}
        </div>
      </div>
    </div>
  );
};

export default ProfilePage;
