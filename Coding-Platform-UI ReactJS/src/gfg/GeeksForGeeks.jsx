import GeeksForGeeksPOD from "./GeeksForGeeksPOD.jsx";
import "../leetcode/leetcode.css"
import "./gfg.css"
import {useAuth} from "../Authentication/auth-provider.jsx";
import GFGProblemSolved from "./GFGProblemSolved.jsx";

const GeeksForGeeks = () => {
  const {hasGfgProfile} = useAuth();

  return (
    <div className={"main-pod-container"}>
      <GeeksForGeeksPOD/>
      {hasGfgProfile() ? (
        <GFGProblemSolved/>
      ) : (
        <div className={"no-profile-message"}>
          Please provide your GeekForGeeks profile to view more data.
        </div>
      )}
    </div>
  )
}

export default GeeksForGeeks;