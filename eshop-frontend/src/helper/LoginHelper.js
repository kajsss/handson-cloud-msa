export const loginHelper = {
    login,
    logout,
    isLoginNow,
};

function login(userEmail, userPassword) {
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({'email': userEmail, 'pw': userPassword})
    }

    return fetch(`${process.env.VUE_APP_BASE_URL}/user/login`, requestOptions)
        .then(loginHandleResponse);
}

function loginHandleResponse(response) {
    return response.text().then(text => {
        const data = text && JSON.parse(text);
        if (response.ok) {
            const token = response.headers.get('Authorization');
            localStorage.setItem('token',token);
            return {token}
        } else {
            if (response.status === 401) {
                logout();
                location.reload();
            }
            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
        }
    });

}

function isLoginNow() {
    return !!localStorage.getItem('token');
}

function logout() {
    localStorage.removeItem('token');
}
