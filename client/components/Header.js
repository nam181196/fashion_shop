// client/components/Header.jsx
"use client"; // Dùng cái này vì có tương tác click (nếu cần sau này)
import Link from 'next/link';
import { FaShoppingCart, FaUser, FaSearch } from 'react-icons/fa'; // Nhớ cài react-icons chưa?

export default function Header() {
  return (
    <header className="bg-white shadow-md sticky top-0 z-50">
      <div className="container mx-auto px-4 h-20 flex justify-between items-center">
        
        {/* 1. LOGO */}
        <Link href="/" className="text-3xl font-extrabold text-black tracking-tighter">
          MEN<span className="text-red-600">STORE</span>
        </Link>

        {/* 2. MENU (Desktop) */}
        <nav className="hidden md:flex space-x-8 font-bold text-sm uppercase text-gray-700">
          <Link href="/" className="hover:text-red-600 transition">Trang chủ</Link>
          <Link href="/products" className="hover:text-red-600 transition">Sản phẩm</Link>
          <Link href="/sale" className="hover:text-red-600 transition text-red-500">Sale Off</Link>
          <Link href="/contact" className="hover:text-red-600 transition">Liên hệ</Link>
        </nav>

        {/* 3. ICONS */}
        <div className="flex items-center space-x-6 text-gray-700">
          <FaSearch className="cursor-pointer hover:text-black text-xl" />
          <FaUser className="cursor-pointer hover:text-black text-xl" />
          
          <div className="relative cursor-pointer">
            <FaShoppingCart className="hover:text-black text-xl" />
            <span className="absolute -top-2 -right-2 bg-red-600 text-white text-[10px] font-bold rounded-full w-4 h-4 flex items-center justify-center">
              0
            </span>
          </div>
        </div>

      </div>
    </header>
  );
}