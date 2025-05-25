import {createContext, useContext, useEffect, useState} from "react";
import Login from "./login.jsx";

const AuthContext = createContext(null);

export const AuthProvider = ({children}) => {
  const [userDetails, setUserDetails] = useState(null);
  const updateUserDetails = (details) => {
    setUserDetails(() => details);
    localStorage.setItem("USER_DETAILS", JSON.stringify(details));
  }
  const logout = () => {
    localStorage.removeItem("USER_DETAILS");
    setUserDetails(() => null);
  }
  const hasLeetcodeProfile = () => {
    return userDetails && !(userDetails["leetcode_username"] == null || userDetails["leetcode_username"].length === 0)
  }

  const hasGfgProfile = () => {
    return userDetails && !(userDetails["gfg_username"] == null || userDetails["gfg_username"].length === 0)
  }
  useEffect(() => {
    const storedDetailsString = localStorage.getItem("USER_DETAILS");
    if (storedDetailsString) {
      setUserDetails(() => JSON.parse(storedDetailsString));
    }
  }, [])
  return (
    <AuthContext.Provider value={{userDetails, updateUserDetails, logout, hasLeetcodeProfile, hasGfgProfile}}>
      {userDetails === null ? <Login/> : children}
    </AuthContext.Provider>
  )
}
export const useAuth = () => useContext(AuthContext);

export const ProtectedElement = ({child}) => {
  const {userDetails} = useAuth()
  return userDetails ? child : <Login/>
}

