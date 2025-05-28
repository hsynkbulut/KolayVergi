import Modal from './Modal';

const ConfirmModal = ({
  open,
  onClose,
  onConfirm,
  icon,
  iconClass = '',
  title,
  description,
  cancelText = 'Vazgeç',
  confirmText = 'Evet',
  confirmColor = 'bg-red-600 hover:bg-red-700 focus:ring-2 focus:ring-red-300',
  loading = false
}) => {
  return (
    <Modal open={open} onClose={onClose}>
      <div className="flex flex-col items-center text-center px-6 py-8 sm:px-10 sm:py-10 animate-fadeIn">
        <div className={`rounded-full p-5 mb-6 flex items-center justify-center shadow-inner ${iconClass}`}>
          {icon}
        </div>
        <h2 className="text-2xl sm:text-3xl font-bold text-gray-900 mb-3">{title}</h2>
        {description && <p className="text-gray-500 mb-8 text-base sm:text-lg">{description}</p>}
        <div className="flex flex-row-reverse gap-3 w-full justify-center mt-2">
          <button
            onClick={onConfirm}
            className={`min-w-[120px] px-6 py-2.5 rounded-lg ${confirmColor} text-white font-semibold shadow transition focus:outline-none`}
            disabled={loading}
          >
            {loading ? '...' : confirmText}
          </button>
          <button
            onClick={onClose}
            className="min-w-[120px] px-6 py-2.5 rounded-lg bg-gray-100 text-gray-700 hover:bg-gray-200 font-semibold transition focus:outline-none"
            disabled={loading}
          >
            {cancelText}
          </button>
        </div>
      </div>
    </Modal>
  );
};

export default ConfirmModal; 