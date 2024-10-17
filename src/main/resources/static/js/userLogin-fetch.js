/**
 * 
 */

const loginForm = document.getElementById("loginForm");

loginForm.addEventListener("submit", function(e) {
	e.preventDefault();
	
//	const formData = new FormData(signupForm);  // formData
	
	const user = {
		username: document.getElementById("username").value,
		userpwd: document.getElementById("userpwd").value,
	};
	
	fetch("/user/login/api", {
		method: "POST",
		headers: {
			"Accept": "application/json",
			"Content-Type": "application/json",
		},
	 	body: JSON.stringify(user),
	})
	.then(response => {
		return response.json().then(data => ({
			status: response.status,
			data: data
		}))
	})
    .then(({status, data}) => {
        if (status === 200) {
            alert('로그인에 성공했습니다.');
            //document.getElementById('messageForm').reset();
			window.location.href = '/';
        } else {
            //alert(data.message);
			throw new Error(data.message);
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert(error.message || '로그인에 실패했습니다.')
    });
});