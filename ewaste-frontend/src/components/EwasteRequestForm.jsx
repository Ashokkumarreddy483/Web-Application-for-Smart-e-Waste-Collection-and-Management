import React, { useState } from "react";
import { submitEwasteRequest } from "../api/requestsApi";

const deviceOptions = ["Laptop", "Mobile", "TV", "Printer", "Other"];
const conditionOptions = ["Working", "Damaged", "Dead"];

export default function EwasteRequestForm({ onCreated }) {
  const [form, setForm] = useState({
    deviceType: "Laptop",
    brand: "",
    model: "",
    condition: "Working",
    quantity: 1,
    pickupAddress: "",
    remarks: "",
  });
  const [images, setImages] = useState([]);
  const [previewUrls, setPreviewUrls] = useState([]);
  const [submitting, setSubmitting] = useState(false);
  const [result, setResult] = useState(null);

  const onChange = (e) => {
    const { name, value } = e.target;
    setForm((s) => ({ ...s, [name]: value }));
  };

  const onFileChange = (e) => {
    const files = Array.from(e.target.files || []);
    setImages(files);
    setPreviewUrls(files.map((f) => URL.createObjectURL(f)));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSubmitting(true);
    setResult(null);

    try {
      const payload = new FormData();
      const dataBlob = new Blob([JSON.stringify(form)], { type: "application/json" });
      payload.append("data", dataBlob);
      images.forEach((file) => payload.append("images", file));

      const res = await submitEwasteRequest(payload);
      setResult({ success: true, data: res });
      if (typeof onCreated === "function") onCreated(res);

      // reset
      setForm({
        deviceType: "Laptop",
        brand: "",
        model: "",
        condition: "Working",
        quantity: 1,
        pickupAddress: "",
        remarks: "",
      });
      setImages([]);
      setPreviewUrls([]);
    } catch (err) {
      console.error("submit error:", err);
      setResult({
        success: false,
        error: err.response?.data || err.message || "Unknown error",
      });
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <>
      <style>{`
        .ewaste-form {
          max-width: 600px;
          margin: 50px auto;
          padding: 30px;
          background: #ffffff;
          border-radius: 16px;
          box-shadow: 0 6px 16px rgba(0, 128, 0, 0.15);
          font-family: "Poppins", sans-serif;
          transition: all 0.3s ease;
        }

        .ewaste-form:hover {
          transform: translateY(-2px);
          box-shadow: 0 8px 20px rgba(0, 128, 0, 0.2);
        }

        .ewaste-form h2 {
          text-align: center;
          margin-bottom: 25px;
          color: #2e7d32;
          font-size: 24px;
          font-weight: 600;
        }

        .form-grid {
          display: grid;
          grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
          gap: 16px;
        }

        label {
          display: flex;
          flex-direction: column;
          font-weight: 500;
          color: #333;
        }

        input, select, textarea {
          padding: 10px 12px;
          border: 1px solid #c8e6c9;
          border-radius: 10px;
          background-color: #f9fff9;
          font-size: 15px;
          color: #333;
          margin-top: 5px;
          transition: border-color 0.3s ease;
        }

        input:focus, select:focus, textarea:focus {
          border-color: #2e7d32;
          outline: none;
          background-color: #ffffff;
        }

        textarea {
          resize: none;
          height: 90px;
        }

        button {
          width: 100%;
          background: linear-gradient(90deg, #43a047, #2e7d32);
          color: white;
          font-size: 16px;
          font-weight: 600;
          padding: 12px;
          border: none;
          border-radius: 10px;
          cursor: pointer;
          transition: background 0.3s ease, transform 0.2s;
        }

        button:hover {
          background: linear-gradient(90deg, #2e7d32, #1b5e20);
          transform: translateY(-1px);
        }

        .image-previews {
          display: flex;
          flex-wrap: wrap;
          gap: 10px;
          grid-column: 1 / -1;
        }

        .result.success {
          color: #2e7d32;
          font-weight: 600;
          text-align: center;
          margin-top: 15px;
        }

        .result.error {
          color: #d32f2f;
          font-weight: 500;
          text-align: center;
          margin-top: 10px;
        }

        @media (max-width: 768px) {
          .ewaste-form {
            margin: 30px 15px;
            padding: 20px;
          }
        }
      `}</style>

      <div className="ewaste-form card">
        <h2>Submit E-waste Request</h2>

        <form onSubmit={handleSubmit} className="form-grid">
          <label>
            Device Type
            <select name="deviceType" value={form.deviceType} onChange={onChange}>
              {deviceOptions.map((d) => (
                <option key={d} value={d}>{d}</option>
              ))}
            </select>
          </label>

          <label>
            Brand
            <input name="brand" value={form.brand} onChange={onChange} />
          </label>

          <label>
            Model
            <input name="model" value={form.model} onChange={onChange} />
          </label>

          <label>
            Condition
            <select name="condition" value={form.condition} onChange={onChange}>
              {conditionOptions.map((c) => (
                <option key={c} value={c}>{c}</option>
              ))}
            </select>
          </label>

          <label>
            Quantity
            <input type="number" name="quantity" min="1" value={form.quantity} onChange={onChange} />
          </label>

          <label>
            Images (optional)
            <input type="file" accept="image/*" multiple onChange={onFileChange} />
          </label>

          <div className="image-previews">
            {previewUrls.map((u, idx) => (
              <img
                key={idx}
                src={u}
                alt={`preview-${idx}`}
                style={{
                  width: 80,
                  height: 80,
                  objectFit: "cover",
                  borderRadius: "10px",
                  border: "1px solid #c8e6c9",
                }}
              />
            ))}
          </div>

          <label style={{ gridColumn: "1 / -1" }}>
            Pickup Address
            <textarea name="pickupAddress" value={form.pickupAddress} onChange={onChange} rows={3} />
          </label>

          <label style={{ gridColumn: "1 / -1" }}>
            Remarks
            <textarea name="remarks" value={form.remarks} onChange={onChange} rows={3} />
          </label>

          <div style={{ gridColumn: "1 / -1" }}>
            <button type="submit" disabled={submitting}>
              {submitting ? "Submitting..." : "Submit Request"}
            </button>
          </div>
        </form>

        {result && (
          <div className={`result ${result.success ? "success" : "error"}`} style={{ marginTop: 12 }}>
            {result.success ? (
              <div>Request created successfully. ID: {result.data.requestId}</div>
            ) : (
              <div>Error: {JSON.stringify(result.error)}</div>
            )}
          </div>
        )}
      </div>
    </>
  );
}
