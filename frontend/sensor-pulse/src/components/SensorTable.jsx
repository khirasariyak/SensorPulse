import React, { useEffect, useState } from 'react';

function SensorTable() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchData = () => {
    fetch('http://localhost:8080/sensor-stats') // Replace with your real API URL
      .then((res) => {
        if (!res.ok) {
          throw new Error('Network response was not ok');
        }
        return res.json();
      })
      .then((json) => {
        setData(json);
        setLoading(false);
        setError(null);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchData(); // fetch immediately on mount

    const interval = setInterval(() => {
      fetchData(); // fetch every 1 second
    }, 1000);

    return () => clearInterval(interval); // clean up on unmount
  }, []);

  if (loading) return <p>Loading data...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <table border="1" cellPadding="8" cellSpacing="0">
      <thead>
        <tr>
          <th>Sensor ID</th>
          <th>Min Temperature</th>
          <th>Max Temperature</th>
          <th>Avg Temperature</th>
          <th>Min Humidity</th>
          <th>Max Humidity</th>
          <th>Avg Humidity</th>
          <th>Count</th>
        </tr>
      </thead>
      <tbody>
        {data.map((sensor) => (
          <tr key={sensor.sensorId}>
            <td>{sensor.sensorId}</td>
            <td>{sensor.minTemperature.toFixed(2)}</td>
            <td>{sensor.maxTemperature.toFixed(2)}</td>
            <td>{sensor.avgTemperature.toFixed(2)}</td>
            <td>{sensor.minHumidity.toFixed(2)}</td>
            <td>{sensor.maxHumidity.toFixed(2)}</td>
            <td>{sensor.avgHumidity.toFixed(2)}</td>
            <td>{sensor.count}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default SensorTable;
