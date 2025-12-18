"use client";
import Link from 'next/link';
import { useCart } from '../context/CartContext'; // Đảm bảo đường dẫn này đúng với file CartContext của bạn
import { ShoppingCart, User, Search } from 'lucide-react';

export default function Header() {
  const { cart } = useCart();

  // Tính tổng số lượng sản phẩm trong giỏ
  const totalItems = cart.reduce((total, item) => total + item.quantity, 0);

  return (
    <header className="sticky top-0 z-50 bg-white border-b-4 border-black py-4">
      <div className="container mx-auto px-4 flex justify-between items-center">
        
        {/* LOGO */}
        <Link href="/" className="text-2xl font-black uppercase tracking-tighter">
          MEN<span className="text-red-600">STORE</span>
        </Link>

        {/* MENU CHÍNH (Nếu có) */}
        <nav className="hidden md:flex gap-8 font-black uppercase text-sm italic">
          <Link href="/" className="hover:text-red-600 transition">Trang chủ</Link>
          <Link href="/products" className="hover:text-red-600 transition">Sản phẩm</Link>
          <Link href="/admin" className="hover:text-red-600 transition font-bold text-gray-400">Admin</Link>
        </nav>

        {/* ICON CHỨC NĂNG */}
        <div className="flex items-center gap-6">
          <button className="hover:text-red-600 transition">
            <Search size={24} strokeWidth={3} />
          </button>
          
          <Link href="/login" className="hover:text-red-600 transition">
            <User size={24} strokeWidth={3} />
          </Link>

          {/* GIỎ HÀNG - Đã sửa lỗi Link và hiển thị số lượng */}
          <Link href="/cart" className="relative group">
            <ShoppingCart size={28} strokeWidth={3} className="group-hover:text-red-600 transition" />
            
            {/* Vòng tròn đỏ báo số lượng */}
            {totalItems > 0 && (
              <span className="absolute -top-2 -right-2 bg-red-600 text-white text-[10px] font-black w-5 h-5 flex items-center justify-center rounded-full border-2 border-white animate-bounce">
                {totalItems}
              </span>
            )}
          </Link>
        </div>
      </div>
    </header>
  );
}