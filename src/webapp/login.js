

document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("login-form");

    loginForm.addEventListener("submit", (event) => {
        event.preventDefault();

        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        fetch('http://localhost:8080/api/users/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Login failed');
            }
            return response.json();
        })
        .then(data => {
            localStorage.setItem("authToken", data.token); // Save token
            window.location.href = "home.html"; // Redirect to home page
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Invalid login credentials");
        });
    });
});



document.addEventListener("DOMContentLoaded", () => {
    const token = localStorage.getItem('authToken'); // Check token from storage
    if (token) {
        window.location.href = 'home.html'; // Redirect to home if logged in
    }
});



document.addEventListener('DOMContentLoaded', () => {
    const currentPath = window.location.pathname.split('/').pop(); // Get current page name
    const menuLinks = document.querySelectorAll('.main-menu a');

    menuLinks.forEach(link => {
        if (link.getAttribute('href') === currentPath) {
            link.classList.add('active'); // Highlight current menu item
        } else {
            link.classList.remove('active');
        }
    });

    // Optional: Highlight logo when Home is active
    const logo = document.querySelector('.logo');
    const homeLink = document.querySelector('a[href="home.html"]');
    if (homeLink.classList.contains('active')) {
        logo.classList.add('active');
    } else {
        logo.classList.remove('active');
    }
});


