import React, { useState, useEffect } from "react";
import { apiAuth } from "../api/auth";


const OTPModal = ({ open, onClose, contact }) => {
const [otp, setOtp] = useState("");
useEffect(() => setOtp(""), [open]);
if (!open) return null;


const handleVerify = async () => {
try {
await apiAuth.verifyOtp({ contact, otp });
alert("Verification successful");
onClose();
} catch (e) {
alert(e.message);
}
};


return (
<div className="fixed inset-0 flex items-center justify-center bg-black/50">
<div className="bg-white p-6 rounded-lg w-80">
<h3 className="text-lg font-semibold mb-3">Verify OTP</h3>
<input value={otp} onChange={(e) => setOtp(e.target.value)} placeholder="Enter OTP" className="border px-3 py-2 rounded w-full mb-3" />
<div className="flex justify-end gap-2">
<button onClick={onClose} className="border px-3 py-1 rounded">Cancel</button>
<button onClick={handleVerify} className="bg-blue-600 text-white px-3 py-1 rounded">Verify</button>
</div>
</div>
</div>
);
};
export default OTPModal;