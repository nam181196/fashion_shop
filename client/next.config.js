/** @type {import('next').NextConfig} */
const nextConfig = {
  // Cấu hình ảnh để load được ảnh từ link bên ngoài (quan trọng cho web bán hàng)
  images: {
    remotePatterns: [
      {
        protocol: 'https',
        hostname: '**', // Cho phép load ảnh từ mọi nguồn
      },
    ],
  },
};

export default nextConfig;