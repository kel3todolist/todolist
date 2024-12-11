// Contoh Data Statistik
const statisticsData = {
    daily: {
        title: "Daily Statistic",
        total: 10,
        completed: 6,
        onProgress: 3,
        notStarted: 1
    },
    weekly: {
        title: "Weekly Statistic",
        total: 50,
        completed: 30,
        onProgress: 15,
        notStarted: 5
    },
    monthly: {
        title: "Monthly Statistic",
        total: 200,
        completed: 150,
        onProgress: 40,
        notStarted: 10
    }
};

// Fungsi untuk Memperbarui Data Statistik
function updateStatistic() {
    const filter = document.getElementById("statFilter").value;
    const data = statisticsData[filter];

    // Perbarui Card Statistik
    document.getElementById("statTitle").textContent = data.title;
    document.getElementById("statData").textContent = `Total Tasks: ${data.total}`;
    document.getElementById("statAdditional").textContent = `Completed Tasks: ${data.completed} | On Progress: ${data.onProgress} | Not Started: ${data.notStarted}`;
}
