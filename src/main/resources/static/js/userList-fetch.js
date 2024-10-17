/**
 * 
 */


function userListFetch() {
	
	const loading = document.getElementById("loading_id");
	const error = document.getElementById("error_id");
	const userList = document.getElementById("userList_id");
	
	loading.style.display = "block";
	error.style.display = "none";
	userList.innerHTML = "";
	
	fetch("/user/list/api", {
		method: "GET",
		headers: {
			"Content-Type": "application/json",
		}
	})
	.then(response => {
		if (!response.ok) {
			throw new Error("네트워크 문제 발생")
		}
		return response.json();
	})
	.then(users => {
		loading.style.display = "none";
		users.forEach(user => {
			li = document.createElement("li");
			li.innerHTML = `<a href="/user/${user.id}">${user.id} - ${user.username}</a>`;
			
			userList.appendChild(li);
		})
	})
	.catch(error => {
		console.error("Error: ", error);
	})
	
}


window.addEventListener("DOMContentLoaded", userListFetch);