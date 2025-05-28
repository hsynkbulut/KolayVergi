import Navbar from './Navbar';
import Sidebar from './Sidebar';
import Footer from './Footer';
import { useState } from 'react';

const Layout = ({ children, currentPath }) => {
  const [sidebarOpen, setSidebarOpen] = useState(false);

  return (
    <div className="min-h-screen w-screen flex flex-col bg-gradient-to-br from-blue-50 via-pink-50 to-purple-100">
      <header className="w-screen">
        <Navbar
          currentPath={currentPath}
          onSidebarToggle={() => setSidebarOpen((open) => !open)}
          isSidebarOpen={sidebarOpen}
        />
      </header>
      <div className="flex flex-1 w-screen relative">
        {/* Sidebar */}
        <Sidebar open={sidebarOpen} onClose={() => setSidebarOpen(false)} />
        {/* Overlay (sidebar açıkken) */}
        {sidebarOpen && (
          <div
            className="fixed inset-0 bg-black bg-opacity-20 z-30"
            onClick={() => setSidebarOpen(false)}
          ></div>
        )}
        {/* Main içerik */}
        <main className={`flex-1 min-w-0 flex items-center justify-center transition-all duration-200 ${sidebarOpen ? 'md:ml-64' : ''}`}> 
          <div className="w-full max-w-5xl bg-white rounded-2xl shadow-lg p-8 my-10">
          {children}
          </div>
        </main>
      </div>
      <footer className="w-screen">
        <Footer />
      </footer>
    </div>
  );
};

export default Layout; 