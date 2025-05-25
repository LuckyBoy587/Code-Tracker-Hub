import "../POD.css"
import {useEffect, useState} from "react";
import axios from "axios";
import {BootstrapTooltip} from "../Utilities.jsx";

const LeetCodePOD = () => {
  const [problemName, setProblemName] = useState('')
  const [relatedTopics, setRelatedTopics] = useState([])
  const [difficulty, setDifficulty] = useState("")
  const [acceptanceRate, setAcceptanceRate] = useState(0)
  const [questionLink, setQuestionLink] = useState("")
  const [questionText, setQuestionText] = useState("")
  useEffect(() => {
    axios.get("http://localhost:8080/leetcode/pod")
      .then(res => {
        setQuestionLink(() => res.data["link"])
        const question = res.data["question"];
        setProblemName(question.title);
        let topicsList = []
        for (const topicObj of question["topicTags"]) {
          topicsList.push(topicObj["name"])
        }
        topicsList.sort((a, b) => a.length - b.length)
        setRelatedTopics(topicsList);
        setDifficulty(question["difficulty"]);
        setAcceptanceRate(question["acRate"]);
        setQuestionText(question["content"]);
      })
  }, []);

  const onSolveClick = () => {
    const url = "https://leetcode.com" + questionLink;
    window.open(url, "_blank");
  }

  return (
    <div className={"leetCodePOD"}>
      <div className={"pod-container"}>
        <div className={"problem-half"}>
          <div className={"problem-description"}>
            <p className="problem-header">Problem of the day</p>
            <p className="problem-name">{problemName}</p>
            <div className="related-topics-list">
              {relatedTopics.map((topic, index) => (
                <span key={index} className="related-topic-item">{topic}</span>
              ))}
            </div>
          </div>
        </div>
        <div className={"right-half"}>
          <button onClick={onSolveClick}>Solve Problem</button>
          <div className={"problem-info"}>
            <BootstrapTooltip title={"Difficulty"}>
              <span className={"difficulty"}>{difficulty}</span>
            </BootstrapTooltip>
            <span>{" | "}</span>
            <BootstrapTooltip title={"Acceptance Rate"}>
              <span className={"acceptance"}>{`${acceptanceRate.toFixed(2)}%`}</span>
            </BootstrapTooltip>
          </div>
        </div>
      </div>
      <div className={"description-container"} dangerouslySetInnerHTML={{__html: questionText}}></div>
    </div>
  )
}

export default LeetCodePOD