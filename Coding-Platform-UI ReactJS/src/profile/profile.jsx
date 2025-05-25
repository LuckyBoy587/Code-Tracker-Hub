import { useState } from "react";
import { useAuth } from "../Authentication/auth-provider.jsx";
import "./profile.css";
import axios from "axios";
import {useNavigate} from "react-router-dom";

const Profile = () => {
  const { userDetails, updateUserDetails, logout } = useAuth();
  const navigateTo = useNavigate();
  const [isEditing, setIsEditing] = useState(false);
  const [leetcodeUsername, setLeetcodeUsername] = useState(userDetails["leetcode_username"] || "");
  const [gfgUsername, setGfgUsername] = useState(userDetails["gfg_username"] || "");

  const handleEditToggle = () => {
    setIsEditing(!isEditing);
  };

  const handleSave = () => {
    const updatedUserDetails = {...userDetails, "leetcode_username": leetcodeUsername, "gfg_username": gfgUsername};
    axios.post("http://localhost:8080/user/update", updatedUserDetails)
    .then(res => {
      updateUserDetails(res.data);
    })
    setIsEditing(false);
  };

  const handleLogout = () => {
    logout();
    navigateTo("/");
  }

  return (
    <div className="profile-container">
      <div className="profile-card">
        <img
          src={userDetails["profile_picture_url"]}
          alt="Profile Picture"
          className="profile-picture"
        />
        <h1 className="profile-name">{userDetails["name"]}</h1>
        <p className="profile-email">{userDetails["email"]}</p>
        <div className="profile-field">
          <span className="profile-label">LeetCode Username:</span>
          {isEditing ? (
            <input
              type="text"
              value={leetcodeUsername}
              onChange={(e) => setLeetcodeUsername(e.target.value)}
              className="profile-input"
            />
          ) : (
            <span className="profile-value">{leetcodeUsername || "Not set"}</span>
          )}
        </div>
        <div className="profile-field">
          <span className="profile-label">GFG Username:</span>
          {isEditing ? (
            <input
              type="text"
              value={gfgUsername}
              onChange={(e) => setGfgUsername(e.target.value)}
              className="profile-input"
            />
          ) : (
            <span className="profile-value">{gfgUsername || "Not set"}</span>
          )}
        </div>
        <div className={"button-container"}>
          <button className="profile-button" onClick={isEditing ? handleSave : handleEditToggle}>
            {isEditing ? "Save" : "Edit"}
          </button>

          <button className="profile-button" onClick={handleLogout}>
            Logout
          </button>
        </div>
      </div>
    </div>
  );
};

export default Profile;