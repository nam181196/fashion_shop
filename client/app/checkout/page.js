"use client";
import { useCart } from '../../context/CartContext';
import { useState } from 'react';
import axios from 'axios';
import Link from 'next/link';

export default function CheckoutPage() {
  const { cart, clearCart } = useCart(); // Giả sử bạn có hàm clearCart trong Context
  const [info, setInfo] = useState({ name: '', phone: '', address: '' });
  const [isOrdering, setIsOrdering] = useState(false);

  const totalPrice = cart.reduce((total, item) => total + (item.price * item.quantity), 0);

  const handleOrder = async (e) => {
    e.preventDefault();
    setIsOrdering(true);

    const orderData = {
      customerName: info.name,
      phone: info.phone,
      address: info.address,
      items: cart,
      totalAmount: totalPrice
    };

    try {
      // Gửi đơn hàng lên Backend cổng 5005
      await axios.post('http://127.0.0.1:5005/api/orders', orderData);
      
      alert("ĐẶT HÀNG THÀNH CÔNG! Chúng tôi sẽ liên hệ bạn sớm.");
      
      // Xóa giỏ hàng sau khi đặt thành công
      if (typeof window !== 'undefined') {
        localStorage.removeItem('cart');
        window.location.href = "/";
      }
    } catch (err) {
      alert("Lỗi khi đặt hàng: " + err.message);
    } finally {
      setIsOrdering(false);
    }
  };

  if (cart.length === 0) return <div className="py-20 text-center font-black">GIỎ HÀNG TRỐNG</div>;

  return (
    <div className="container mx-auto px-4 py-10 max-w-5xl">
      <h1 className="text-3xl font-black uppercase italic mb-10 border-b-4 border-black pb-2">Thanh toán</h1>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-12">
        <form onSubmit={handleOrder} className="space-y-6">
          <div className="p-8 border-2 border-black bg-white shadow-[6px_6px_0px_0px_rgba(0,0,0,1)]">
            <h2 className="text-xl font-black uppercase mb-6 italic border-b-2 border-black pb-2 inline-block">Thông tin nhận hàng</h2>
            <div className="space-y-4">
              <input className="w-full border-2 border-black p-3 outline-none focus:bg-yellow-50 font-bold" placeholder="HỌ VÀ TÊN" required onChange={e => setInfo({...info, name: e.target.value})} />
              <input className="w-full border-2 border-black p-3 outline-none focus:bg-yellow-50 font-bold" placeholder="SỐ ĐIỆN THOẠI" type="tel" required onChange={e => setInfo({...info, phone: e.target.value})} />
              <textarea className="w-full border-2 border-black p-3 outline-none focus:bg-yellow-50 font-bold" placeholder="ĐỊA CHỈ" rows="3" required onChange={e => setInfo({...info, address: e.target.value})}></textarea>
            </div>
          </div>
          <button 
            disabled={isOrdering}
            className={`w-full bg-black text-white py-5 font-black uppercase text-xl transition shadow-[6px_6px_0px_0px_rgba(0,0,0,0.2)] ${isOrdering ? 'bg-gray-400' : 'hover:bg-red-600'}`}
          >
            {isOrdering ? 'ĐANG XỬ LÝ...' : 'Xác nhận đặt hàng ngay'}
          </button>
        </form>

        <div className="bg-gray-50 p-8 border-2 border-black h-fit">
          <h2 className="text-xl font-black uppercase mb-6 italic">Đơn hàng</h2>
          {cart.map((item, idx) => (
            <div key={idx} className="flex justify-between text-sm mb-2 border-b pb-2">
              <span>{item.name} (x{item.quantity})</span>
              <span className="font-bold">{(item.price * item.quantity).toLocaleString()}đ</span>
            </div>
          ))}
          <div className="flex justify-between text-2xl font-black text-red-600 mt-4">
            <span>TỔNG:</span>
            <span>{totalPrice.toLocaleString()}đ</span>
          </div>
        </div>
      </div>
    </div>
  );
}