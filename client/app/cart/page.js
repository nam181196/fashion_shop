"use client";
import { useCart } from '../../context/CartContext';
import Link from 'next/link';
import { Trash2, Plus, Minus } from 'lucide-react';

export default function CartPage() {
  const { cart, removeFromCart, updateQuantity } = useCart();

  // Tính tổng tiền
  const totalPrice = cart.reduce((total, item) => total + (item.price * item.quantity), 0);

  if (cart.length === 0) {
    return (
      <div className="container mx-auto px-4 py-20 text-center">
        <h2 className="text-2xl font-black uppercase mb-4">Giỏ hàng của bạn đang trống</h2>
        <Link href="/" className="inline-block bg-black text-white px-8 py-3 font-bold uppercase hover:bg-red-600 transition">
          Tiếp tục mua sắm
        </Link>
      </div>
    );
  }

  return (
    <div className="container mx-auto px-4 py-10">
      <h1 className="text-3xl font-black uppercase italic mb-10 border-b-4 border-black pb-4">Giỏ hàng</h1>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-10">
        {/* DANH SÁCH SẢN PHẨM */}
        <div className="lg:col-span-2 space-y-6">
          {cart.map((item, index) => (
            <div key={index} className="flex flex-col md:flex-row gap-6 p-4 border-2 border-black bg-white shadow-[4px_4px_0px_0px_rgba(0,0,0,1)]">
              <div className="w-full md:w-32 h-40 bg-gray-100 flex-shrink-0">
                <img src={item.image} alt={item.name} className="w-full h-full object-cover" />
              </div>
              
              <div className="flex-grow flex flex-col justify-between">
                <div>
                  <div className="flex justify-between items-start">
                    <h3 className="font-black uppercase text-lg">{item.name}</h3>
                    <button onClick={() => removeFromCart(index)} className="text-gray-400 hover:text-red-600">
                      <Trash2 size={20} />
                    </button>
                  </div>
                  <p className="text-sm text-gray-500 uppercase font-bold mt-1">
                    Màu: {item.color} / Size: {item.size}
                  </p>
                </div>

                <div className="flex justify-between items-center mt-4">
                  <div className="flex items-center border-2 border-black">
                    <button onClick={() => updateQuantity(index, item.quantity - 1)} className="p-2 hover:bg-gray-100"><Minus size={16}/></button>
                    <span className="px-4 font-bold">{item.quantity}</span>
                    <button onClick={() => updateQuantity(index, item.quantity + 1)} className="p-2 hover:bg-gray-100"><Plus size={16}/></button>
                  </div>
                  <p className="font-black text-lg">{(item.price * item.quantity).toLocaleString()}đ</p>
                </div>
              </div>
            </div>
          ))}
        </div>

        {/* TỔNG KẾT & NÚT THANH TOÁN */}
        <div className="lg:col-span-1">
          <div className="bg-white p-6 border-2 border-black shadow-[8px_8px_0px_0px_rgba(0,0,0,1)] sticky top-24">
            <h3 className="text-xl font-black uppercase mb-6 italic">Cộng giỏ hàng</h3>
            <div className="flex justify-between mb-4 font-bold text-gray-600">
              <span>Tạm tính:</span>
              <span>{totalPrice.toLocaleString()}đ</span>
            </div>
            <div className="flex justify-between mb-6 text-xl font-black text-red-600 border-t-2 border-black pt-4">
              <span>TỔNG CỘNG:</span>
              <span>{totalPrice.toLocaleString()}đ</span>
            </div>
            
            {/* ĐÂY LÀ NÚT BẠN CẦN */}
            <Link 
              href="/checkout" 
              className="block w-full bg-black text-white py-4 font-black uppercase hover:bg-red-600 transition text-center text-lg"
            >
              Tiến hành thanh toán
            </Link>
            
            <p className="text-[10px] text-gray-400 mt-4 uppercase text-center font-bold">
              Miễn phí đổi trả trong vòng 7 ngày
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}