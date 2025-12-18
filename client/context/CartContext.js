// client/context/CartContext.js
"use client";
import { createContext, useState, useContext, useEffect } from 'react';

const CartContext = createContext();

export function CartProvider({ children }) {
  const [cart, setCart] = useState([]);

  // Load giỏ hàng từ LocalStorage khi mới vào web
  useEffect(() => {
    const savedCart = localStorage.getItem('cart');
    if (savedCart) {
      setCart(JSON.parse(savedCart));
    }
  }, []);

  // Hàm lưu giỏ hàng vào LocalStorage
  const saveCart = (newCart) => {
    setCart(newCart);
    localStorage.setItem('cart', JSON.stringify(newCart));
  };

  // Hàm thêm sản phẩm vào giỏ
  const addToCart = (product, color, size, quantity) => {
    const existingItemIndex = cart.findIndex(
      (item) => item._id === product._id && item.color === color && item.size === size
    );

    let newCart = [...cart];
    if (existingItemIndex > -1) {
      // Nếu trùng sản phẩm + màu + size -> Tăng số lượng
      newCart[existingItemIndex].quantity += quantity;
    } else {
      // Nếu chưa có -> Thêm mới
      newCart.push({ ...product, color, size, quantity });
    }
    saveCart(newCart);
    alert("Đã thêm vào giỏ hàng!");
  };

  // Hàm xóa sản phẩm
  const removeFromCart = (index) => {
    const newCart = cart.filter((_, i) => i !== index);
    saveCart(newCart);
  };

  // --- MỚI: Hàm cập nhật số lượng (Dùng cho nút + - trong giỏ hàng) ---
  const updateQuantity = (index, newQuantity) => {
    if (newQuantity < 1) return; // Không cho giảm dưới 1
    
    const newCart = [...cart];
    newCart[index].quantity = newQuantity;
    saveCart(newCart);
  };

  return (
    // Nhớ truyền updateQuantity vào value bên dưới
    <CartContext.Provider value={{ cart, addToCart, removeFromCart, updateQuantity }}>
      {children}
    </CartContext.Provider>
  );
}

// Hook để dùng nhanh ở các file khác
export const useCart = () => useContext(CartContext);