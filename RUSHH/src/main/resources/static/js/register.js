document.getElementById("registerForm").addEventListener("submit", async function (e) {
  e.preventDefault();

  const name = document.getElementById("name").value.trim();
  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value.trim();
  const role = document.getElementById("role").value;

  const response = await fetch("http://localhost:8080/api/auth/register", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ name, email, password, role })
  });

  const errorMsg = document.getElementById("registerError");

  if (response.ok) {
    alert("Registration successful! Please login.");
    window.location.href = "login.html";
  } else {
    const errorData = await response.json();
    errorMsg.innerText = errorData.message || "Registration failed. Try again.";
  }
});
