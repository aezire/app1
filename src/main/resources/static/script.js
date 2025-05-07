document.getElementById('signupForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const formData = {
        fullName: document.getElementById('fullName').value,
        email: document.getElementById('email').value,
        username: document.getElementById('username').value,
        password: document.getElementById('password').value,
        termsAccepted: document.getElementById('terms').checked
    };

    try {
        const response = await fetch('/api/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        });

        const message = await response.text();
        const messageElement = document.getElementById('message');
        
        if (response.ok) {
            messageElement.classList.add('success');
            messageElement.textContent = message;
            document.getElementById('signupForm').reset();
        } else {
            messageElement.classList.remove('success');
            messageElement.textContent = message;
        }
    } catch (error) {
        document.getElementById('message').textContent = 'An error occurred. Please try again.';
    }
});