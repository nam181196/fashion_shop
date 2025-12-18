// client/app/products/page.js
"use client";
import { useState, useEffect } from 'react';
import axios from 'axios';
import Link from 'next/link';

export default function AllProducts() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const res = await axios.get('http://localhost:5005/api/products');
        setProducts(res.data);
      } catch (err) {
        console.error("Lỗi lấy dữ liệu:", err);
      }
    };
    fetchProducts();
  }, []);

  return (
    <div className="container mx-auto px-4 py-10">
      <h1 className="text-3xl font-bold uppercase mb-8 border-b pb-4 text-center">Tất cả sản phẩm</h1>
      
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-8">
        {products.map((product) => (
          <Link href={`/products/${product._id}`} key={product._id} className="group">
            <div className="relative aspect-[3/4] overflow-hidden bg-gray-100 rounded-lg mb-4">
              <img
                src={product.image || "https://placehold.co/600x800"}
                alt={product.name}
                className="w-full h-full object-cover transition-transform duration-500 group-hover:scale-110"
              />
            </div>
            <h3 className="font-bold text-gray-800 uppercase text-sm mb-1">{product.name}</h3>
            <p className="text-red-600 font-bold">{product.price.toLocaleString('vi-VN')}đ</p>
          </Link>
        ))}
      </div>
    </div>
  );
}