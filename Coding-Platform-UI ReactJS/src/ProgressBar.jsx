import {useEffect, useState} from "react";

const ProgressBar = ({ title, total, solved, color }) => {
  const [animateKey, setAnimateKey] = useState(0);

  useEffect(() => {
    setAnimateKey(prevKey => prevKey + 1);
  }, [total, solved]);

  const completedPercent = (solved / total) * 100;

  return (
    <div className="progress-field">
      <div className={`progress-bar light-${color}`}>
        <div className={`progress-line`} style={{ width: `${completedPercent}%` }}>
          <div
            key={animateKey} // Use this key to reset the animation
            className={`progress-line-animate ${color}`}
          />
        </div>
      </div>
      <div className="progress-info-container">
        <span className="difficulty-title" style={{ color: color }}>
          {title}
        </span>
        <span className="solved-count">{`${solved} / ${total}`}</span>
      </div>
    </div>
  );
};

export default ProgressBar;