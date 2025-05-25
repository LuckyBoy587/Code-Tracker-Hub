import LeetCodePOD from "./LeetCodePOD.jsx";
import LeetCodeProblemSolved from "./LeetCodeProblemSolved.jsx";
import "./leetcode.css"
import {useAuth} from "../Authentication/auth-provider.jsx";

const LeetCode = () => {
  const {hasLeetcodeProfile} = useAuth();

  return (
    <div className={"main-pod-container"}>
      <LeetCodePOD/>
      {hasLeetcodeProfile() ? (
        <LeetCodeProblemSolved/>
      ) : (
        <div className={"no-profile-message"}>
          Please provide your LeetCode profile to view more data.
        </div>
      )}
    </div>
  )
}

export default LeetCode;