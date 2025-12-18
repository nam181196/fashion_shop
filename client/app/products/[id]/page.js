"use client";
import { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'next/navigation';
import { useCart } from '../../../context/CartContext';
import { ShoppingCart, ArrowLeft } from 'lucide-react'; // Thư viện bạn vừa cài
import Link from 'next/link';

export default function ProductDetail() {
  const { id } = useParams();
  const { addToCart } = useCart();
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  
  const [selectedColor, setSelectedColor] = useState('Trắng'); // Mặc định vì dữ liệu mẫu đơn giản
  const [selectedSize, setSelectedSize] = useState('L');
  const [quantity, setQuantity] = useState(1);

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        // Sử dụng 127.0.0.1:5005 để khớp với Backend đang chạy của bạn
        const res = await axios.get(`http://127.0.0.1:5005/api/products/${id}`);
        setProduct(res.data);
        setLoading(false);
      } catch (error) {
        console.error("Lỗi lấy chi tiết sản phẩm:", error);
        setLoading(false);
      }
    };
    if (id) fetchProduct();
  }, [id]);

  if (loading) return <div className="text-center py-20 font-bold uppercase italic">Đang tải sản phẩm...</div>;
  if (!product) return <div className="text-center py-20 font-bold">Không tìm thấy sản phẩm!</div>;

  const handleAddToCart = () => {
    addToCart(product, selectedColor, selectedSize, quantity);
    alert("Đã thêm vào giỏ hàng!");
  };

  return (
    <div className="container mx-auto px-4 py-10">
      <Link href="/" className="inline-flex items-center text-sm font-black uppercase mb-8 hover:text-red-600 transition">
        <ArrowLeft size={18} className="mr-2 border-2 border-black"/> Quay lại
      </Link>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-12">
        {/* HÌNH ẢNH */}
        <div className="border-4 border-black p-2 bg-white shadow-[10px_10px_0px_0px_rgba(0,0,0,1)]">
          <img 
            src={product.image || "https://placehold.co/600x800"} 
            alt={product.name} 
            className="w-full h-auto object-cover"
          />
        </div>

        {/* THÔNG TIN */}
        <div className="flex flex-col space-y-6">
          <div className="border-b-4 border-black pb-4">
            <h1 className="text-5xl font-black uppercase italic leading-none mb-4">{product.name}</h1>
            <p className="text-3xl font-black text-red-600 italic italic tracking-tighter">
                {product.price?.toLocaleString()} VNĐ
            </p>
          </div>

          <div className="space-y-6">
            <p className="font-bold text-gray-600 leading-relaxed italic border-l-4 border-black pl-4">
                "{product.description || "Sản phẩm chất lượng cao thuộc bộ sưu tập mới nhất."}"
            </p>

            <div className="flex flex-wrap gap-4 pt-4">
               <div className="flex items-center border-4 border-black font-black">
                 <button onClick={() => setQuantity(q => q > 1 ? q - 1 : 1)} className="px-4 py-2 hover:bg-black hover:text-white transition">-</button>
                 <span className="px-6 py-2 bg-yellow-400 border-x-4 border-black">{quantity}</span>
                 <button onClick={() => setQuantity(q => q + 1)} className="px-4 py-2 hover:bg-black hover:text-white transition">+</button>
               </div>
            </div>
          </div>

          <button 
            onClick={handleAddToCart}
            className="group flex items-center justify-center gap-4 bg-black text-white py-6 font-black uppercase text-2xl hover:bg-red-600 transition shadow-[10px_10px_0px_0px_rgba(0,0,0,0.2)]"
          >
            <ShoppingCart size={28} className="group-hover:animate-bounce" /> Mua ngay
          </button>

          <div className="grid grid-cols-2 gap-4">
             <div className="border-2 border-black p-4 font-black uppercase text-[10px] text-center italic bg-gray-50">Giao hàng hỏa tốc</div>
             <div className="border-2 border-black p-4 font-black uppercase text-[10px] text-center italic bg-gray-50">Đổi trả 7 ngày</div>
          </div>
        </div>
      </div>
    </div>
  );
}