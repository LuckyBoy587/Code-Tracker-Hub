import './App.css'
import Navbar from "./navbar/Navbar.jsx";
import {BrowserRouter, Outlet, Route, Routes} from "react-router-dom";
import {AuthProvider} from "./Authentication/auth-provider.jsx";
import Profile from "./profile/profile.jsx";
import LeetCode from "./leetcode/LeetCode.jsx";
import GeeksForGeeks from "./gfg/GeeksForGeeks.jsx";

function App() {
  return (
    <div>
      <AuthProvider>
        <BrowserRouter>
          <div className={"app-container"}>
            <Navbar/>
            <div className={"bottom-container"}>
              <Routes>
                <Route path="/" element={<Outlet/>}/>
                <Route path="/leetcode" element={<LeetCode/>}/>
                <Route path="/gfg" element={<GeeksForGeeks/>}/>
                <Route path="/profile" element={<Profile/>}/>
              </Routes>
            </div>
          </div>
        </BrowserRouter>
      </AuthProvider>
    </div>
  )
}

export default App
