let statChart = null;

function loadStatistics(type) {
    fetch(`/api/statistics?type=${type}`)
        .then(response => response.json())
        .then(data => {
            updateStatisticDisplay(data);
            updatePieChart(data);
        })
        .catch(error => {
            console.error('Error loading statistics:', error);
        });
}

function updateStatisticDisplay(data) {
    document.getElementById("statTitle").textContent = data.title;
    document.getElementById("dateRange").textContent = data.dateRange;
    document.getElementById("statTotal").textContent = `Total Tasks: ${data.total}`;
    document.getElementById("statDetails").textContent =
        `Completed Tasks: ${data.completed} | On Progress: ${data.onProgress} | Not Started: ${data.notStarted}`;
    document.getElementById("completionRate").textContent =
        `Completion Rate: ${data.completionRate.toFixed(2)}%`;
}

function updatePieChart(data) {
    const ctx = document.getElementById("statChart").getContext("2d");

    if (statChart) {
        statChart.destroy();
    }

    statChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ['Completed', 'On Progress', 'Not Started'],
            datasets: [{
                data: [data.completed, data.onProgress, data.notStarted],
                backgroundColor: ['#2ecc71', '#f1c40f', '#e74c3c']
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top'
                }
            }
        }
    });
}

// Initialize statistics on page load
document.addEventListener("DOMContentLoaded", () => {
    const initialType = document.getElementById("statFilter").value;
    loadStatistics(initialType);
});