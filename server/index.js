// server/index.js
const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
const dotenv = require('dotenv');
// Import cái Model vừa tạo ở bước 1
const Product = require('./models/Product'); 

dotenv.config();

const app = express();
const PORT = process.env.PORT || 5000;

// Middleware
app.use(cors());
app.use(express.json()); // Để đọc được JSON gửi lên

// Kết nối MongoDB
mongoose.connect(process.env.MONGO_URL)
  .then(() => console.log('✅ Đã kết nối MongoDB thành công'))
  .catch((err) => console.error('❌ Lỗi kết nối MongoDB:', err));

// --- CÁC API ---

// 1. API Lấy danh sách sản phẩm (Frontend sẽ gọi cái này)
app.get('/api/products', async (req, res) => {
  try {
    const products = await Product.find().sort({ createdAt: -1 }); // Lấy tất cả, mới nhất lên đầu
    res.status(200).json(products);
  } catch (err) {
    res.status(500).json(err);
  }
});

// 2. API Thêm sản phẩm mới (Dùng để nhập hàng)
app.post('/api/products', async (req, res) => {
  try {
    const newProduct = new Product(req.body);
    const savedProduct = await newProduct.save(); // Lưu vào DB
    res.status(200).json(savedProduct);
  } catch (err) {
    res.status(500).json(err);
  }
});

// Chạy server
app.listen(PORT, () => {
  console.log(`🚀 Server đang chạy tại http://localhost:${PORT}`);
});