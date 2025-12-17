// client/app/layout.js
import { Geist, Geist_Mono } from "next/font/google";
import "./globals.css";
import Header from "../components/Header"; // Import Header

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export const metadata = {
  title: "Web Bán Quần Áo Nam",
  description: "Dự án website bán hàng thời trang",
};

export default function RootLayout({ children }) {
  return (
    <html lang="vi">
      {/* Gộp class font chữ và class màu nền vào đây */}
      <body
        className={`${geistSans.variable} ${geistMono.variable} antialiased bg-gray-50 text-black`}
      >
        <Header /> {/* Header nằm trên cùng */}
        
        <main className="min-h-screen">
          {children}
        </main>

        {/* Footer nằm dưới cùng */}
        <footer className="bg-black text-white py-6 text-center mt-10">
          <p>© 2024 MEN STORE - All rights reserved.</p>
        </footer>
      </body>
    </html>
  );
}