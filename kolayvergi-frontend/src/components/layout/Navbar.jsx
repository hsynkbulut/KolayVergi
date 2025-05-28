import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';
import Button from '../ui/Button';
import Icon from '../ui/Icon';

const Navbar = ({ currentPath, onSidebarToggle, isSidebarOpen }) => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  const navLinks = [
    { to: '/', label: 'Ana Sayfa' },
    ...(user?.roles?.includes('ROLE_ADMIN') ? [{ to: '/admin', label: 'Kullanıcılar' }] : []),
  ];

  return (
    <nav className="w-full bg-white shadow sticky top-0 z-50 font-sans">
      <div className="w-full flex items-center h-16 px-4 md:px-8 justify-between">
        {/* Sol: Hamburger + Logo + Menü */}
        <div className="flex items-center gap-4 md:gap-6">
          <button
            className="flex items-center justify-center p-2 rounded-md border border-gray-200 bg-white shadow hover:bg-blue-50 transition-colors focus:outline-none"
            onClick={onSidebarToggle}
            aria-label="Menüyü Aç/Kapat"
          >
            <Icon name={isSidebarOpen ? 'FiX' : 'FiMenu'} className="w-6 h-6 text-blue-700" />
          </button>
          <Link to="/" className="flex items-center gap-2 text-2xl md:text-3xl font-extrabold text-blue-700 tracking-tight select-none">
            <Icon name="FiPieChart" className="w-7 h-7 text-blue-500" />
            KolayVergi
          </Link>
          <div className="hidden md:flex items-center gap-6 ml-2">
            {navLinks.map(link => (
              <Link
                key={link.to}
                to={link.to}
                className={`text-base md:text-lg px-2 py-1 rounded-lg font-medium transition-colors whitespace-nowrap
                  ${currentPath === link.to ? 'bg-blue-100 text-blue-700 font-bold shadow-sm' : 'text-gray-700 hover:text-blue-700 hover:bg-blue-50'}`}
              >
                {link.label}
              </Link>
            ))}
          </div>
        </div>
        {/* Sağ: Kullanıcı ve Çıkış */}
        <div className="flex items-center gap-4 flex-shrink-0">
          <div className="flex items-center gap-2 bg-blue-50 px-3 py-1 rounded-full">
            <Icon name="FiUser" className="w-5 h-5 text-blue-600" />
            <span className="text-gray-700 text-sm md:text-base font-medium select-none truncate max-w-[160px]">{user?.email}</span>
          </div>
          <button
            onClick={handleLogout}
            title="Çıkış"
            className="flex items-center justify-center px-5 py-2 rounded-xl bg-gray-100 shadow-md hover:bg-red-50 focus:ring-2 focus:ring-red-200 transition-all duration-150 group"
          >
            <Icon name="FiLogOut" className="w-6 h-6 text-red-500 group-hover:text-red-600" />
            <span className="sr-only">Çıkış</span>
          </button>
        </div>
      </div>
    </nav>
  );
};

export default Navbar; 