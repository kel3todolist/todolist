// Check if dark mode is enabled in localStorage when the page is loaded
window.addEventListener('DOMContentLoaded', () => {
    const body = document.body;

    if (localStorage.getItem('darkMode') === 'enabled') {
        body.classList.add('dark');  // Apply dark mode immediately
    } else {
        body.classList.remove('dark');
    }

    // Setup the toggle button event listener
    const toggleSwitch = document.querySelector('.toggle-switch');
    toggleSwitch.addEventListener('click', () => {
        body.classList.toggle('dark');

        // Save the current state of dark mode in localStorage
        if (body.classList.contains('dark')) {
            localStorage.setItem('darkMode', 'enabled');
        } else {
            localStorage.setItem('darkMode', 'disabled');
        }
    });
});

document.addEventListener("DOMContentLoaded", function () {
    const tasks = document.querySelectorAll(".task-box");

    tasks.forEach(task => {
        const priorityText = task.querySelector("p").textContent.toLowerCase(); // Ambil teks kategori

        if (priorityText.includes("low")) {
            task.classList.add("low");
        } else if (priorityText.includes("medium")) {
            task.classList.add("medium");
        } else if (priorityText.includes("high")) {
            task.classList.add("high");
        }
    });
});
// Mendapatkan elemen input tanggal
const dateInput = document.getElementById('date');

// Fungsi untuk mengatur tanggal minimum ke tanggal saat ini
const setMinDate = () => {
    const today = new Date();
    const year = today.getFullYear();
    const month = ('0' + (today.getMonth() + 1)).slice(-2); // Format bulan menjadi dua digit
    const day = ('0' + today.getDate()).slice(-2); // Format tanggal menjadi dua digit
    dateInput.setAttribute('min', `${year}-${month}-${day}`); // Set atribut min ke tanggal saat ini
};

// Panggil fungsi setMinDate saat halaman dimuat
window.onload = setMinDate;

