import React, { useEffect, useState } from 'react';
import SensorTable from './components/SensorTable';
import MaxTemperatureChart from './components/MaxTemperatureChart';

function App() {
  const [sensorData, setSensorData] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/sensor-stats') // Replace with your API
      .then((res) => res.json())
      .then((data) => setSensorData(data))
      .catch((err) => console.error(err));
  }, []);

  return (
    <div className="App">
      <h1>Sensor Data</h1>
      <SensorTable />
      <h2>Max Temperature Chart</h2>
      <MaxTemperatureChart data={sensorData} />
    </div>
  );
}

export default App;
