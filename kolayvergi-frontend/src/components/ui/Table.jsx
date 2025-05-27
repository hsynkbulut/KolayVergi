const Table = ({ children, className = "" }) => {
  return (
    <div className="overflow-x-auto rounded-2xl shadow">
      <table className={`min-w-full bg-white divide-y divide-gray-200 ${className}`}>
        {children}
      </table>
    </div>
  );
};

export default Table; 