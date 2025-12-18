// server/index.js
const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
const dotenv = require('dotenv');
const Product = require('./models/Product'); 
const Order = require('./models/Order');

dotenv.config();

const app = express();
const PORT = process.env.PORT || 5005;

// 1. Cấu hình CORS chi tiết hơn để tránh lỗi 403
app.use(cors({
  origin: 'http://localhost:3000', // Cho phép web ở cổng 3000 truy cập
  methods: ['GET', 'POST', 'PUT', 'DELETE'],
  credentials: true
}));

app.use(express.json());

// 2. Thêm một route mặc định để kiểm tra server sống hay chết
app.get('/', (req, res) => {
  res.send("API Server is running...");
});

// Kết nối MongoDB (Ưu tiên dùng 127.0.0.1 trong file .env nếu localhost lỗi)
mongoose.connect(process.env.MONGO_URL)
  .then(() => console.log('✅ Đã kết nối MongoDB thành công'))
  .catch((err) => console.error('❌ Lỗi kết nối MongoDB:', err));

// --- API ---

// Lấy danh sách sản phẩm
app.get('/api/products', async (req, res) => {
  try {
    const products = await Product.find().sort({ createdAt: -1 });
    res.status(200).json(products);
  } catch (err) {
    res.status(500).json({ message: "Lỗi lấy dữ liệu", error: err.message });
  }
});

// Lấy chi tiết 1 sản phẩm theo ID (Dùng cho trang [id]/page.js)
app.get('/api/products/:id', async (req, res) => {
    try {
      const product = await Product.findById(req.params.id);
      if (!product) return res.status(404).json("Không tìm thấy sản phẩm");
      res.status(200).json(product);
    } catch (err) {
      res.status(500).json(err);
    }
  });

// Thêm sản phẩm mới
app.post('/api/products', async (req, res) => {
  try {
    const newProduct = new Product(req.body);
    const savedProduct = await newProduct.save();
    res.status(201).json(savedProduct);
  } catch (err) {
    res.status(500).json({ message: "Lỗi lưu dữ liệu", error: err.message });
  }
});

app.post('/api/orders', async (req, res) => {
  try {
    const newOrder = new Order(req.body);
    const savedOrder = await newOrder.save();
    res.status(201).json(savedOrder);
  } catch (err) {
    res.status(500).json({ message: "Lỗi lưu đơn hàng", error: err.message });
  }
});

// API: Lấy danh sách đơn hàng (Cho trang Admin xem)
app.get('/api/orders', async (req, res) => {
  try {
    const orders = await Order.find().sort({ createdAt: -1 });
    res.status(200).json(orders);
  } catch (err) {
    res.status(500).json(err);
  }
});

app.listen(PORT, () => {
  console.log(`🚀 Server đang chạy tại http://localhost:${PORT}`);
});