import React, { useMemo } from 'react';
import { Bar } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  BarElement,
  CategoryScale,
  LinearScale,
  Tooltip,
  Legend,
} from 'chart.js';

// Register chart components
ChartJS.register(BarElement, CategoryScale, LinearScale, Tooltip, Legend);

function MaxTemperatureChart({ data }) {
  // Memoize data to avoid unnecessary chart rerenders
  const chartData = useMemo(() => ({
    labels: data.map((sensor) => sensor.sensorId),
    datasets: [
      {
        label: 'Max Temperature (°C)',
        data: data.map((sensor) => sensor.maxTemperature),
        backgroundColor: 'rgba(255, 99, 132, 0.6)',
        borderWidth: 1,
      },
    ],
  }), [data]);

  const options = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: { position: 'top' },
    },
    scales: {
      y: {
        beginAtZero: false,
        title: {
          display: true,
          text: 'Max Temperature (°C)',
        },
      },
      x: {
        title: {
          display: true,
          text: 'Sensor ID',
        },
        ticks: {
          maxRotation: 90,
          minRotation: 45,
        },
      },
    },
  };

  return (
    <div
      style={{
        width: '80%',
        height: '400px',
        margin: '0 auto',
        resize: 'both',
        overflow: 'auto',
        border: '1px solid #ccc',
      }}
    >
      <Bar data={chartData} options={options} />
    </div>
  );
}

export default MaxTemperatureChart;
