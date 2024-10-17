/**
 * 
 */

const signupForm = document.getElementById("signupForm");

signupForm.addEventListener("submit", function(e) {
	e.preventDefault();
	
//	const formData = new FormData(signupForm);  // formData
	
	const user = {
		username: document.getElementById("username").value,
		userpwd: document.getElementById("userpwd").value,
	};
	
	fetch("/user/signup/api", {
		method: "POST",
		headers: {
			"Accept": "application/json",
			"Content-Type": "application/json",
		},
	 	body: JSON.stringify(user), //json형식이 아니라 FormData 형식으로.
//		body: formData, // formData
	})
	.then(response => {
		console.log('Response status:', response.status); // 상태 코드 출력
		if (response.ok) {
			alert("회원 가입 성공");
			signupForm.reset();
			return response.text();
		} else {
			console.log(user);
			alert("회원 가입 실패");
		}
	})
	.then(message => {
        alert(message); // "환영합니다." 메시지 표시
        setTimeout(() => {
            window.location.href = '/'; // 일정 시간 후 리다이렉트
        }, 2000); // 2초 후 이동
    })
	.catch(error => {
		console.error("Error: ", error);
		alert("오류가 발생했습니다.");
	})
});