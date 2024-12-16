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

// Variabel Global untuk Menyimpan Chart
let statChart = null;

// Fungsi untuk Memperbarui Data Statistik
function updateStatistic() {
    const filter = document.getElementById("statFilter").value;
    const data = statisticsData[filter];

    // Perbarui Card Statistik
    document.getElementById("statTitle").textContent = data.title;
    document.getElementById("statData").textContent = `Total Tasks: ${data.total}`;
    document.getElementById("statAdditional").textContent = `Completed Tasks: ${data.completed} | On Progress: ${data.onProgress} | Not Started: ${data.notStarted}`;

     // Perbarui Pie Chart
     updatePieChart(data);
}

// Fungsi untuk Membuat atau Memperbarui Pie Chart
function updatePieChart(data) {
    const ctx = document.getElementById("statChart").getContext("2d");

    // Hapus Chart Sebelumnya Jika Ada
    if (statChart) {
        statChart.destroy();
    }

    // Buat Pie Chart Baru
    statChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ["Completed", "On Progress", "Not Started"],
            datasets: [{
                data: [data.completed, data.onProgress, data.notStarted],
                backgroundColor: ["#2ecc71", "#f1c40f", "#e74c3c"] // Hijau, Kuning, Merah
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

// Inisialisasi Statistik Pertama Kali
window.onload = () => {
    updateStatistic();
};