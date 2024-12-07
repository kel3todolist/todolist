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
