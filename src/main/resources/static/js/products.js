const token = localStorage.getItem("token");
const username = localStorage.getItem("username");
document.getElementById("userName").innerText = username || "Guest";

const API_BASE = "http://localhost:8080/api/products";

async function fetchProducts() {
  const keyword = document.getElementById("search").value.trim();
  const url = keyword ? `${API_BASE}/search?keyword=${keyword}` : API_BASE;

  const response = await fetch(url, {
    headers: {
      Authorization: `Bearer ${token}`
    }
  });

  const container = document.getElementById("productsContainer");
  container.innerHTML = "";

  if (response.ok) {
    const products = await response.json();

    if (products.length === 0) {
      container.innerHTML = "<p>No products found.</p>";
      return;
    }

    products.forEach(product => {
      const card = document.createElement("div");
      card.className = "product-card";
      card.innerHTML = `
        <img src="${product.image}" alt="${product.name}" />
        <h3>${product.name}</h3>
        <p>${product.category} • ${product.brand}</p>
        <p>${product.description.substring(0, 60)}...</p>
        <p class="price">₹${product.discountPrice || product.price}</p>
      `;
      container.appendChild(card);
    });

  } else {
    container.innerHTML = "<p>Error loading products. Please login.</p>";
  }
}

function logout() {
  localStorage.clear();
  window.location.href = "login.html";
}

window.onload = fetchProducts;
