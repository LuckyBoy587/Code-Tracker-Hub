import "../POD.css"
import {useEffect, useState} from "react";
import axios from "axios";
import {BootstrapTooltip} from "../Utilities.jsx";

const GeeksForGeeksPOD = () => {
  const [problemName, setProblemName] = useState('')
  const [relatedTopics, setRelatedTopics] = useState([])
  const [difficulty, setDifficulty] = useState("")
  const [accuracy, setAccuracy] = useState(0)
  const [questionLink, setQuestionLink] = useState("")
  const [questionLines, setQuestionLines] = useState([])
  useEffect(() => {
    axios.get("http://localhost:8080/gfg/pod")
      .then(res => {
        setQuestionLink(() => res.data["problem_url"])
        const question = res.data;
        setProblemName(() => question["problem_name"]);
        let topicsList = []
        for (const topicObj of question["tags"]["topic_tags"]) {
          topicsList.push(topicObj)
        }
        topicsList.sort((a, b) => a.length - b.length)
        setRelatedTopics(topicsList);
        setDifficulty(question["difficulty"]);
        setAccuracy(question["accuracy"]);
        setQuestionLines(() => question["description"]);
      })
  }, []);

  const onSolveClick = () => {
    window.open(questionLink, "_blank");
  }

  return (<div className={"gfgPOD"}>
    <div className={"pod-container"}>
      <div className={"problem-half"}>
        <div className={"problem-description"}>
          <p className="problem-header">Problem of the day</p>
          <p className="problem-name">{problemName}</p>
          <div className="related-topics-list">
            {relatedTopics.map((topic, index) => (<span key={index} className="related-topic-item">{topic}</span>))}
          </div>
        </div>
      </div>
      <div className={"right-half"}>
        <button onClick={onSolveClick}>Solve Problem</button>
        <div className={"problem-info"}>
          <BootstrapTooltip title={"Difficulty"}>
            <span>{difficulty}</span>
          </BootstrapTooltip>
          <span>{"|"}</span>
          <BootstrapTooltip title={"Accuracy"}>
            <span>{`${accuracy}%`}</span>
          </BootstrapTooltip>
        </div>
      </div>
    </div>
    <div className={"description-container"}>
      {questionLines.map((line, index) =>
        <p key={index} dangerouslySetInnerHTML={{__html: line}}></p>)}
    </div>
  </div>)
}

export default GeeksForGeeksPOD