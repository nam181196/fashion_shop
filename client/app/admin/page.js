"use client";
import { useEffect, useState } from 'react';
import axios from 'axios';

export default function AdminOrders() {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchOrders();
  }, []);

  const fetchOrders = async () => {
    try {
      const res = await axios.get('http://127.0.0.1:5005/api/orders');
      setOrders(res.data);
      setLoading(false);
    } catch (err) {
      console.error("Lỗi lấy đơn hàng:", err);
      setLoading(false);
    }
  };

  if (loading) return <div className="p-20 text-center font-black">ĐANG TẢI DỮ LIỆU...</div>;

  return (
    <div className="container mx-auto px-4 py-10">
      <h1 className="text-4xl font-black uppercase italic mb-10 border-b-8 border-black pb-2 inline-block bg-yellow-400 px-4">
        Quản lý đơn hàng
      </h1>

      <div className="grid gap-6">
        {orders.length === 0 ? (
          <p className="text-xl font-bold">Chưa có đơn hàng nào.</p>
        ) : (
          orders.map((order) => (
            <div key={order._id} className="border-4 border-black p-6 bg-white shadow-[8px_8px_0px_0px_rgba(0,0,0,1)] hover:translate-x-1 hover:translate-y-1 hover:shadow-none transition-all">
              <div className="flex flex-col md:flex-row justify-between mb-4 border-b-2 border-dashed border-black pb-4">
                <div>
                  <p className="text-sm text-gray-500 font-bold uppercase">Mã đơn: {order._id.slice(-8)}</p>
                  <h2 className="text-xl font-black uppercase text-red-600">{order.customerName}</h2>
                  <p className="font-bold">📞 {order.phone}</p>
                  <p className="font-medium">📍 {order.address}</p>
                </div>
                <div className="text-right mt-4 md:mt-0">
                  <p className="text-sm font-bold">{new Date(order.createdAt).toLocaleString('vi-VN')}</p>
                  <p className="text-2xl font-black mt-2 text-blue-700">{order.totalAmount.toLocaleString()}đ</p>
                  <span className="inline-block bg-black text-white px-3 py-1 text-xs font-black uppercase mt-2">
                    {order.status}
                  </span>
                </div>
              </div>

              <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
                {order.items.map((item, idx) => (
                  <div key={idx} className="flex items-center gap-3 bg-gray-100 p-2 border-2 border-black">
                    <img src={item.image} alt={item.name} className="w-12 h-12 object-cover border border-black" />
                    <div>
                      <p className="text-xs font-black uppercase leading-tight">{item.name}</p>
                      <p className="text-[10px] font-bold text-gray-600">
                        Size: {item.size} | Màu: {item.color} | SL: {item.quantity}
                      </p>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
}