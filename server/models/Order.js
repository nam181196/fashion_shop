const mongoose = require('mongoose');

const OrderSchema = new mongoose.Schema({
  customerName: { type: String, required: true },
  phone: { type: String, required: true },
  address: { type: String, required: true },
  items: [
    {
      name: String,
      quantity: Number,
      price: Number,
      color: String,
      size: String,
      image: String
    }
  ],
  totalAmount: { type: Number, required: true },
  status: { type: String, default: 'Chờ xác nhận' }, // Chờ xác nhận, Đang giao, Đã xong
}, { timestamps: true });

module.exports = mongoose.model('Order', OrderSchema);