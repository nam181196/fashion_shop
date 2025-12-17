// server/models/Product.js
const mongoose = require('mongoose');

const productSchema = new mongoose.Schema({
  name: { type: String, required: true }, // Tên áo/quần
  price: { type: Number, required: true }, // Giá bán
  originalPrice: { type: Number }, // Giá gốc (để gạch ngang nếu giảm giá)
  image: { type: String }, // Link ảnh sản phẩm
  category: { type: String }, // Ví dụ: 'ao-thun', 'quan-au'
  description: { type: String },
  
  // QUAN TRỌNG: Quản lý size và màu
  // Ví dụ: Áo này có màu Trắng size M còn 10 cái
  variants: [
    {
      color: { type: String }, // Màu
      size: { type: String },  // Size
      quantity: { type: Number, default: 0 } // Số lượng tồn kho
    }
  ]
}, { timestamps: true }); // Tự động lưu ngày tạo

module.exports = mongoose.model('Product', productSchema);