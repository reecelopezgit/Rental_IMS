import BASE_URL from "./config.js";

document.addEventListener('DOMContentLoaded', () => {
    // Fetch Properties for Dropdown
    const loadProperties = () => {
        fetch('http://rental-ims.onrender.com/api/properties')
            .then(response => response.json())
            .then(properties => {
                const propertySelect = document.getElementById('item-property');
                if (propertySelect) {
                    propertySelect.innerHTML = '<option value="">Choose a Property</option>';
                    properties.forEach(property => {
                        const option = document.createElement('option');
                        option.value = property.id;
                        option.textContent = property.property_Name;
                        propertySelect.appendChild(option);
                    });
                }
                const servicePropertySelect = document.getElementById('select-property');
                if (servicePropertySelect) {
                    servicePropertySelect.innerHTML = '<option value="">Choose a Property</option>';
                    properties.forEach(property => {
                        const option = document.createElement('option');
                        option.value = property.id;
                        option.textContent = property.property_Name;
                        servicePropertySelect.appendChild(option);
                    });
                }
            })
            .catch(error => console.error('Error loading properties:', error));
    };

    // Fetch Items for Dropdown
    const loadItemsService = () => {
        fetch('http://rental-ims.onrender.com/api/items')
            .then(response => response.json())
            .then(items => {
                const itemSelect = document.getElementById('select-item');
                if (itemSelect) {
                    itemSelect.innerHTML = '<option value="">Choose an item</option>';
                    items.forEach(item => {
                        const option = document.createElement('option');
                        option.value = item.id;
                        option.textContent = item.name;
                        itemSelect.appendChild(option);
                    });
                }
            })
            .catch(error => console.error('Error loading items:', error));
    };

    // Initialize Dropdowns
    loadProperties();
    loadItemsService();

    // Handle Property Form Submission
    const propertyForm = document.getElementById('property-form');
    if (propertyForm) {
        propertyForm.addEventListener('submit', event => {
            event.preventDefault();
            const propertyData = {
                property_name: document.getElementById('property-name').value,
                address: document.getElementById('property-address').value,
                type: document.getElementById('property-type').value
            };
            fetch('http://rental-ims.onrender.com/api/properties/new', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(propertyData)
            })
                .then(response => {
                    if (response.ok) {
                        alert('Property added successfully!');
                        propertyForm.reset();
                        loadProperties();
                    } else {
                        alert('Failed to add property');
                    }
                })
                .catch(error => console.error('Error adding property:', error));
        });
    }

    // Handle Item Form Submission
    const itemForm = document.getElementById('item-form');
    if (itemForm) {
        itemForm.addEventListener('submit', event => {
            event.preventDefault();
            const itemData = {
                name: document.getElementById('item-name').value,
                item_description: document.getElementById('item-description').value,
                price: parseFloat(document.getElementById('item-price').value),
                quantity: parseInt(document.getElementById('item-quantity').value),
                category: document.getElementById('item-category').value,
                propertyId: parseInt(document.getElementById('item-property').value)
            };
            fetch('http://rental-ims.onrender.com/api/items/new', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(itemData)
            })
                .then(response => {
                    if (response.ok) {
                        alert('Item added successfully!');
                        itemForm.reset();
                        loadItemsService();
                    } else {
                        alert('Failed to add item');
                    }
                })
                .catch(error => console.error('Error adding item:', error));
        });
    }

    // Handle Service Request Form Submission
    const serviceForm = document.getElementById('service-request-form');
    if (serviceForm) {
        serviceForm.addEventListener('submit', event => {
            event.preventDefault();
            const serviceData = {
                name: document.getElementById('service-name').value,
                serviceDescription: document.getElementById('service-description').value,
                cost: parseFloat(document.getElementById('service-cost').value),
                status: document.getElementById('service-status').value,
                serviceCategory: document.getElementById('service-category').value,
                propertyId: parseInt(document.getElementById('select-property').value),
                inventoryItemId: parseInt(document.getElementById('select-item').value),
                completionDate: document.getElementById('completion-date').value || null,
                requestDate: document.getElementById('request-date').value || null,
                scheduledDate: document.getElementById('scheduled-date').value || null
            };
            fetch('http://rental-ims.onrender.com/api/services/new', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(serviceData)
            })
                .then(response => {
                    if (response.ok) {
                        alert('Service request added successfully!');
                        serviceForm.reset();
                    } else {
                        response.text().then(text => {
                            console.error('Failed to add service request:', text);
                            alert('Failed to add service request: ' + text);
                        });
                    }
                })
                .catch(error => console.error('Error adding service request:', error));
        });
    }
});
