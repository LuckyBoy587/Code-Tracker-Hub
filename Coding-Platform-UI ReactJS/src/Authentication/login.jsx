import React from 'react';
import './login.css';
import {FcGoogle} from 'react-icons/fc';
import {GoogleLogin} from "@react-oauth/google";
import {jwtDecode} from 'jwt-decode';
import {useAuth} from "./auth-provider.jsx";
import axios from "axios";
const Login = () => {
  const {updateUserDetails} = useAuth();
  const handleGoogleLogin = (response) => {
    const details = jwtDecode(response.credential);
    const email = details["email"];
    const name = details["name"];
    const picture = details["picture"];
    axios.post(`http://localhost:8080/user/login`, {
      email,
      name,
      picture,
    })
      .then(res => {
        updateUserDetails(res.data);
      })
  };

  return (
    <div className="login-container">
      <div className="login-card">
        <h1 className="login-title">Welcome</h1>
        <p className="login-subtitle">Sign in to continue</p>
        <GoogleLogin onSuccess={handleGoogleLogin}>
          <div className={"google-login-button"}>
            <FcGoogle className="google-icon"/>
            Sign in with Google
          </div>
        </GoogleLogin>
      </div>
    </div>
  );
};

export default Login;