const Modal = ({ open, onClose, children }) => {
  if (!open) return null;
  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50 transition-all duration-200">
      <div className="bg-white rounded-2xl shadow-2xl p-8 relative animate-fadeIn">
        <button onClick={onClose} className="absolute top-3 right-3 text-gray-400 hover:text-red-500 text-2xl font-bold transition-colors">&times;</button>
        {children}
      </div>
    </div>
  );
};

export default Modal; 