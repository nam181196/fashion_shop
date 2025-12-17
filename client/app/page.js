// client/app/page.js
"use client"; // Bắt buộc phải có dòng này vì ta dùng useState, useEffect

import { useState, useEffect } from 'react';
import axios from 'axios';
import Link from 'next/link';

export default function Home() {
  const [products, setProducts] = useState([]); // Chứa danh sách sản phẩm
  const [loading, setLoading] = useState(true); // Trạng thái đang tải

  // Hàm gọi API lấy sản phẩm
  useEffect(() => {
    const fetchProducts = async () => {
      try {
        // Gọi xuống Backend (cổng 5000)
        const res = await axios.get('http://localhost:5000/api/products');
        setProducts(res.data);
        setLoading(false);
      } catch (error) {
        console.error("Lỗi gọi API:", error);
        setLoading(false);
      }
    };
    fetchProducts();
  }, []);

  return (
    <div className="pb-20">
      
      {/* 1. BANNER QUẢNG CÁO */}
      <div className="w-full h-[400px] md:h-[500px] bg-gray-800 flex items-center justify-center text-white">
        <div className="text-center">
          <h2 className="text-4xl md:text-6xl font-bold uppercase tracking-widest mb-4">New Collection</h2>
          <p className="text-lg text-gray-300 mb-8">Phong cách thời thượng cho phái mạnh</p>
          <button className="bg-white text-black px-8 py-3 font-bold uppercase hover:bg-red-600 hover:text-white transition">
            Mua ngay
          </button>
        </div>
      </div>

      {/* 2. DANH SÁCH SẢN PHẨM */}
      <div className="container mx-auto px-4 mt-16">
        <h2 className="text-3xl font-bold text-center uppercase mb-10">Sản phẩm mới</h2>

        {loading ? (
          <div className="text-center py-20 text-gray-500">Đang tải danh sách sản phẩm...</div>
        ) : (
          <div className="grid grid-cols-2 md:grid-cols-4 gap-6 md:gap-8">
            {products.map((product) => (
              <Link href={`/products/${product._id}`} key={product._id} className="group">
                {/* Ảnh sản phẩm */}
                <div className="relative overflow-hidden bg-gray-100 aspect-[3/4] mb-4">
                  <img 
                    src={product.image || "https://placehold.co/600x800"} 
                    alt={product.name}
                    className="w-full h-full object-cover group-hover:scale-105 transition duration-500"
                  />
                  {/* Nhãn Sale nếu có */}
                  {product.originalPrice > product.price && (
                    <span className="absolute top-2 right-2 bg-red-600 text-white text-xs font-bold px-2 py-1">
                      SALE
                    </span>
                  )}
                </div>

                {/* Thông tin */}
                <div>
                  <h3 className="text-sm md:text-base font-bold text-gray-800 uppercase line-clamp-1 group-hover:text-red-600 transition">
                    {product.name}
                  </h3>
                  <div className="flex gap-2 mt-1">
                    <span className="font-bold text-black">{product.price.toLocaleString('vi-VN')}đ</span>
                    {product.originalPrice && (
                      <span className="text-gray-400 line-through text-sm">{product.originalPrice.toLocaleString('vi-VN')}đ</span>
                    )}
                  </div>
                </div>
              </Link>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}