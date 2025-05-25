import React from 'react';
import './Navbar.css';
import { FaCode } from 'react-icons/fa';
import {useNavigate} from "react-router-dom";
import {useAuth} from "../Authentication/auth-provider.jsx";
import {Avatar} from "@mui/material";
import {BootstrapTooltip} from "../Utilities.jsx";

const Navbar = () => {
  const navigateTo = useNavigate();
  const {userDetails} = useAuth();
  return (
    <nav className="navbar">
      <div className="navbar-brand" onClick={() => navigateTo("/")}>
        <FaCode className="navbar-icon" />
        <h1 className="navbar-title">Code Dashboard</h1>
      </div>
      <div className="navbar-links">
        <span onClick={() => navigateTo("/leetcode")}>
          LeetCode
        </span>
        <span onClick={() => navigateTo("/gfg")}>
          GFG
        </span>
      </div>
      <div className="profile-icon" onClick={() => navigateTo("/profile")}>
        <BootstrapTooltip title={"Profile"} >
          <Avatar alt={userDetails["name"]} src={userDetails["profile_picture_url"]} />
        </BootstrapTooltip>
      </div>
    </nav>
  );
};

export default Navbar;