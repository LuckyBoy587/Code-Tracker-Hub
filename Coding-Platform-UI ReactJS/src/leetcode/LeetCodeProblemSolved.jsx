import {useEffect, useState} from "react";
import {useAuth} from "../Authentication/auth-provider.jsx";
import axios from "axios";
import ProgressBar from "../ProgressBar.jsx";

const LeetCodeProblemSolved = () => {
  const {userDetails} = useAuth();
  const [easyTotalState, setEasyTotalState] = useState(0);
  const [easySolvedState, setEasySolvedState] = useState(0);
  const [mediumTotalState, setMediumTotalState] = useState(0);
  const [mediumSolvedState, setMediumSolvedState] = useState(0);
  const [hardTotalState, setHardTotalState] = useState(0);
  const [hardSolvedState, setHardSolvedState] = useState(0);

  useEffect(() => {
    const username = userDetails["leetcode_username"];
    axios.get(`http://localhost:8080/leetcode/problems-solved/${username}`)
      .then(res => {
        const {
          mediumTotal,
          easySolved,
          hardTotal,
          hardSolved,
          mediumSolved,
          easyTotal
        } = res.data;

        setEasyTotalState(easyTotal);
        setEasySolvedState(easySolved);
        setMediumTotalState(mediumTotal);
        setMediumSolvedState(mediumSolved);
        setHardTotalState(hardTotal);
        setHardSolvedState(hardSolved);
      })
  }, [userDetails]);

  return (
    <div className={"problem-solved-container"}>
      <span className={"problem-header"}>Solved Questions</span>
      <ProgressBar title={"Easy"} total={easyTotalState} solved={easySolvedState} color={"green"}/>
      <ProgressBar title={"Medium"} total={mediumTotalState} solved={mediumSolvedState} color={"orange"}/>
      <ProgressBar title={"Hard"} total={hardTotalState} solved={hardSolvedState} color={"red"}/>
    </div>
  )
}


export default LeetCodeProblemSolved;