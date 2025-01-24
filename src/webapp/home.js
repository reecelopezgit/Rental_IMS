import BASE_URL from "./config.js";




document.addEventListener('DOMContentLoaded', function() {
    const navToggle = document.querySelector('.nav-toggle');
    const navPanel = document.querySelector('.navigation-panel');
    const mainContent = document.querySelector('.home-container');
    
    navToggle.addEventListener('click', function() {
        navPanel.classList.toggle('collapsed');
        mainContent.classList.toggle('collapsed');
        
        // Store the state in localStorage
        const isCollapsed = navPanel.classList.contains('collapsed');
        localStorage.setItem('navCollapsed', isCollapsed);
    });
    
    // Restore the state on page load
    const isCollapsed = localStorage.getItem('navCollapsed') === 'true';
    if (isCollapsed) {
        navPanel.classList.add('collapsed');
        mainContent.classList.add('collapsed');
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


// Function to validate the token
async function validateToken() {
  const token = localStorage.getItem("authToken"); // Get token from localStorage or cookies

  if (!token) {
    alert("No token found. Redirecting to login...");
    window.location.href = "index.html"; // Redirect to login if no token
    return;
  }

  try {
    // Call the API
    const response = await fetch('http://rental-ims.onrender.com/api/users/validate-token', {
      method: "GET",
      headers: {
        "Authorization": token, // Send the token in the Authorization header
      },
    });

    if (response.status === 200) {
      console.log("Token is valid. Proceeding...");
      // Proceed with loading the page
    } else {
      const errorMessage = await response.text();
      alert(`Error: ${errorMessage}`);
      window.location.href = "index.html"; // Redirect to login if token is invalid
    }
  } catch (error) {
    console.error("An error occurred:", error);
    alert("Unable to validate token. Please try again later.");
    window.location.href = "index.html"; // Redirect on API call failure
  }
}

// Call the function on page load
document.addEventListener("DOMContentLoaded", validateToken);


document.addEventListener("DOMContentLoaded", () => {
    const totalPropertiesSpan = document.getElementById('total-properties');
    const totalItemsSpan = document.getElementById('total-items');
    const pendingRequestsSpan = document.getElementById('pending-requests');

    // Fetch the summary from the backend API
    fetch('http://rental-ims.onrender.com/home/summary') // Adjust URL to match your backend endpoint
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch summary data');
            }
            return response.json(); // Parse JSON response
        })
        .then(data => {
            // Update the DOM with the fetched totals
            totalPropertiesSpan.textContent = data.totalProperties;
            totalItemsSpan.textContent = data.totalItems;
            pendingRequestsSpan.textContent = data.pendingServiceRequests;
        })
        .catch(error => {
            console.error('Error fetching summary data:', error);
            // Handle errors gracefully in the UI
            totalPropertiesSpan.textContent = 'Error';
            totalItemsSpan.textContent = 'Error';
            pendingRequestsSpan.textContent = 'Error';
        });
});


