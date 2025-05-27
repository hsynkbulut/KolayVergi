import { Link, useLocation } from 'react-router-dom';
import Icon from '../ui/Icon';

const menu = [
  { label: 'Borçlarım', to: '/borclarim', icon: 'FiList' },
  { label: 'Alışverişlerim', to: '/alisverislerim', icon: 'FiShoppingCart' },
  { label: 'Taksit Ödeme', to: '/taksit-odeme', icon: 'FiCreditCard' },
];

const Sidebar = ({ open, onClose }) => {
  const location = useLocation();
  return (
    <aside
      className={`fixed top-0 left-0 h-full w-64 bg-gray-50 border-r border-gray-200 z-50 transition-transform duration-200
        ${open ? 'translate-x-0' : '-translate-x-full'} flex flex-col`}
      style={{ boxShadow: open ? '0 0 24px 0 rgba(0,0,0,0.08)' : 'none' }}
    >
      {/* Kapatma butonu sol üstte */}
      {open && (
        <button
          className="absolute top-4 left-4 p-2 rounded-full bg-white border border-gray-200 shadow hover:bg-blue-100 transition-colors z-50"
          onClick={onClose}
          aria-label="Kapat"
        >
          <Icon name="FiX" className="w-6 h-6 text-blue-700" />
        </button>
      )}
      <div className="flex-1 flex flex-col gap-2 pt-16 px-4">
        <nav className="flex flex-col gap-1">
          {menu.map((item) => (
            <Link
              key={item.to}
              to={item.to}
              className={`flex items-center gap-3 px-4 py-2 rounded-lg font-semibold transition-all text-base group
                ${location.pathname.startsWith(item.to)
                  ? 'bg-blue-100 text-blue-700 shadow-sm'
                  : 'text-gray-700 hover:bg-blue-50 hover:text-blue-700'}`}
              onClick={onClose}
            >
              <Icon name={item.icon} className="w-5 h-5 group-hover:text-blue-700" />
              {item.label}
            </Link>
          ))}
        </nav>
      </div>
    </aside>
  );
};

export default Sidebar; 