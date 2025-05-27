import Icon from './Icon';

const Button = ({ children, type = 'primary', icon, className = '', ...props }) => {
  let base = 'inline-flex items-center justify-center gap-2 px-5 py-2.5 rounded-lg font-semibold shadow-md transition-all duration-150 focus:outline-none focus:ring-2 focus:ring-blue-400';
  let color = '';
  switch (type) {
    case 'secondary':
      color = 'bg-white text-blue-600 border border-blue-600 hover:bg-blue-50';
      break;
    case 'danger':
      color = 'bg-red-600 text-white hover:bg-red-700';
      break;
    case 'disabled':
      color = 'bg-gray-300 text-gray-500 cursor-not-allowed';
      break;
    default:
      color = 'bg-blue-600 text-white hover:bg-blue-700';
  }
  return (
    <button {...props} className={`${base} ${color} ${className}`} disabled={type === 'disabled' || props.disabled}>
      {icon && <Icon name={icon} className="w-5 h-5" />}
      {children}
    </button>
  );
};

export default Button; 