/**
 * 
 */


function userDetailFetch() {
	const pathParts = window.location.pathname.split('/');
	const userId = pathParts[pathParts.length - 1];
	
	console.log(userId);
	
	fetch(`/user/${userId}/api`, {
		method: "GET",
		headers: {
			"Accept": "application/json",
			"Content-Type": "application/json"
		}
	})
	.then(response => {
		if (!response.ok) {
			throw new Error("Network response was not ok")
		}
		return response.json();
	})
	.then(data => {
		const userNum = document.getElementById("userNum");
		const userName = document.getElementById("userName");
		const userPwd = document.getElementById("userPwd");
		const userCreatedAt = document.getElementById("userCreatedAt");
		const userUpdatedAt = document.getElementById("userUpdatedAt");
		
		userNum.textContent = data.id;
		userName.textContent = data.username;
		userPwd.textContent = data.userpwd;
		userCreatedAt.textContent = data.createdAt;
		userUpdatedAt.textContent = data.updatedAt;
		
		console.log(data);
	})
	.catch(error => {
		console.error("fetch 중에 오류 발생", error);
	})
}

window.addEventListener("DOMContentLoaded", userDetailFetch);